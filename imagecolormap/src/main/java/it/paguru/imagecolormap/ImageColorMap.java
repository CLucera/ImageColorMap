package it.paguru.imagecolormap;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Created by carlo on 26/10/2016.
 */

public class ImageColorMap extends ImageView
{
    private Bitmap colorMapBitmap;
    private OnClickColorMapListener listener;
    private GestureDetector gestureDetector;

    public ImageColorMap(Context context)
    {
        super(context);
        init(null);
    }

    public ImageColorMap(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs);
    }

    public ImageColorMap(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageColorMap(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public OnClickColorMapListener getOnClickColorListener()
    {
        return listener;
    }

    public void setOnClickColorListener(OnClickColorMapListener listener)
    {
        this.listener = listener;
    }

    public void setImageResources(int resource, int mapResource) {
        super.setImageResource(resource);
        if (mapResource > 0) {
            colorMapBitmap = BitmapFactory.decodeResource(getResources(), mapResource);
        }
    }

    private void init(AttributeSet attributeSet)
    {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.ImageColorMap, 0, 0);
            int resourceBitmap = typedArray.getResourceId(R.styleable.ImageColorMap_colorMap, -1);
            if (resourceBitmap > 0) {
                colorMapBitmap = BitmapFactory.decodeResource(getResources(), resourceBitmap);
            }
        }
        setClickable(true);
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener()
        {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e)
            {
                if (listener != null) {
                    if (colorMapBitmap != null && getDrawable() != null) {
                        //get bounds and ratio
                        Rect bounds = getDrawable().getBounds();
                        float imageHeight = getDrawable().getIntrinsicHeight();
                        float imageWidth = getDrawable().getIntrinsicWidth();
                        float scaledH = bounds.height();
                        float scaledW = bounds.width();
                        float verticalRatio = imageHeight / scaledH;
                        float horizontalRatio = imageWidth / scaledW;

                        //get correct touched point offset
                        float yOffset = e.getY() - bounds.top;
                        float xOffset = e.getX() - bounds.left;

                        //get the absolute bitmapCoordinate
                        float absoluteY = (int) (yOffset * verticalRatio);
                        float absoluteX = (int) (xOffset * horizontalRatio);

                        //check if the coordinate are inside the map
                        if (colorMapBitmap.getHeight() < absoluteY || colorMapBitmap.getWidth() < absoluteX) {
                            throw new IllegalStateException("Coordinate out of map bounds");
                        }

                        //get the touched pixel and check the pixel color
                        int touchedPixel = colorMapBitmap.getPixel((int) absoluteX, (int) absoluteY);
                        listener.onColorMapClicked(Color.red(touchedPixel), Color.blue(touchedPixel), Color.green(touchedPixel));
                    } else {
                        Log.w(getClass().getSimpleName(), "ImageMap missing! listener not firing");
                    }
                }
                return super.onSingleTapConfirmed(e);
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public interface OnClickColorMapListener
    {
        public void onColorMapClicked(int r, int g, int b);
    }
}
