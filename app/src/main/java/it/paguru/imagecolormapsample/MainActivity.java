package it.paguru.imagecolormapsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import it.paguru.imagecolormap.ImageColorMap;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageColorMap imageColorMap = (ImageColorMap) findViewById(R.id.imageColorMap);
        imageColorMap.setOnClickColorListener(new ImageColorMap.OnClickColorMapListener()
        {
            @Override
            public void onColorMapClicked(int r, int g, int b)
            {
                //searching for the image mapped values
                String found = "";
                switch (r) {
                    case 250:
                        found = "1";
                        break;
                    case 225:
                        found = "2";
                        break;
                    case 200:
                        found = "3";
                        break;
                    case 175:
                        found = "4";
                        break;
                    case 150:
                        found = "5";
                        break;
                    case 125:
                        found = "6";
                        break;
                    case 100:
                        found = "7";
                        break;
                    case 75:
                        found = "8";
                        break;
                    case 50:
                        found = "9";
                        break;
                    case 0:
                        found = "border";
                        break;
                    default:
                        found = "not found";
                        break;
                }
                Toast.makeText(MainActivity.this, "clicked on " + found + " redValue: " + r, Toast.LENGTH_LONG).show();
            }
        });

        //button example to change the image src and map programmatically
        final Button swapButton = (Button) findViewById(R.id.swapButton);
        swapButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean isSwapped = swapButton.isSelected();
                swapButton.setSelected(!isSwapped);
                //swapping only the shown image
                if(isSwapped) {
                    imageColorMap.setImageResources(R.drawable.image_shown, R.drawable.image_map);
                    swapButton.setText("SHOW MAP");
                } else {
                    swapButton.setText("SHOW REAL IMAGE");
                    imageColorMap.setImageResources(R.drawable.image_map, R.drawable.image_map);
                }
            }
        });
    }
}
