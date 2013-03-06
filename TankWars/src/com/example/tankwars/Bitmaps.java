package com.example.tankwars;

import android.graphics.Bitmap;
import android.util.FloatMath;

public class Bitmaps{
    /**
    * An easy way to rescale any bitmap
    * @param img
    * @param newWidth set to 0 if unknown
    * @param newHeight set to 0 if unknown
    * @return returns a rescaled bitmap
    */
    public static Bitmap resizeBitmap (Bitmap img, int newWidth, int newHeight) {
            float ratio = 0;

            if (newWidth == 0) {
                ratio = (float)newHeight / img.getHeight();     

                if(ratio > 1)
                        ratio = 1/ratio;

                newWidth = (int)( FloatMath.ceil(ratio) * img.getWidth() );
                
            }

            if (newHeight == 0) {
                ratio = (float)newWidth / img.getWidth();

            if(ratio > 1)
                ratio = 1/ratio;

                newHeight = (int)( FloatMath.ceil(ratio) * img.getHeight() );
            }

                return Bitmap.createScaledBitmap(img, newWidth, newHeight, true);
    }

        

}