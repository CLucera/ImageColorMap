# ImageColorMap
ImageView extension used to simple create complex shape buttons, use 2 equal sized image to find which part of the imageview was clicked.
the OnClickColorMapListener interface helps to translate the tap on the shown image in the color code of the map image.

## Code Example

the Implementation is really simple, you can use it from xml or directly in java code, the usage is really similar to a normal ImageView

***XML IMPLEMENTATION:***
```xml
<it.paguru.imagecolormap.ImageColorMap
        android:id="@+id/imageColorMap"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:src="@drawable/image_shown"
        app:colorMap="@drawable/image_map"
        />
```

***JAVA IMPLEMENTATION:***
```java
ImageColorMap imageColorMap = new ImageColorMap(context);
imageColorMap.setImageResources(R.drawable.image_shown, R.drawable.image_map);
```

the only difference is the colorMap attribute in the xml or the .setImageResources(int, int) methods.

The Listener can be implemented with the setOnClickColorListener(ImageColorMap.OnClickColorMapListener) method

```java
imageColorMap.setOnClickColorListener(new ImageColorMap.OnClickColorMapListener()
        {
            @Override
            public void onColorMapClicked(int r, int g, int b)
            {
                Toast.makeText(MainActivity.this, "clicked on R: " + r + " G: " + g + " B: " + b, Toast.LENGTH_LONG).show();
            }
        });
```

## Installation

you can simple grab the library using gradle adding this row in the app module build.gradle file
```groovy
compile 'it.paguru:imagecolormap:0.0.2
```

## Tests

Clone the repository from Android studio and run the sample MainActivity.

## Contributors

want to contribute? feel free to write me at [carlolucera@gmail.com](mailto:carlolucera@gmail.com)

## License

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

