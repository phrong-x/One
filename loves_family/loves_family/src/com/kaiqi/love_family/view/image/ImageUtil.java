package com.kaiqi.love_family.view.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class ImageUtil {

    /**
     * Round the corners of a {@link Bitmap}
     * 
     * @param source
     * @param radius
     * @return rounded corner bitmap
     */
//    public static Bitmap roundCorners(final Bitmap source, float radius) {
//        int width = source.getWidth();
//        int height = source.getHeight();
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.WHITE);
//        Bitmap clipped = Bitmap.createBitmap(width, height, Config.ARGB_8888);
//        Canvas canvas = new Canvas(clipped);
//        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
//        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
//        Bitmap rounded = Bitmap.createBitmap(width, height, Config.ARGB_8888);
//        canvas = new Canvas(rounded);
//        canvas.drawBitmap(source, 0, 0, null);
//        canvas.drawBitmap(clipped, 0, 0, paint);
//        source.recycle();
//        clipped.recycle();
//        return rounded;
//    }
    
    /**
     * Round the corners of a {@link Bitmap}
     * 
     * @param source
     * @param radius
     * @return rounded corner bitmap
     */
    public static Bitmap roundCorners(Bitmap source, float radius) {
        int width = source.getWidth();
        int height = source.getHeight();
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        Bitmap clipped = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(clipped);
        canvas.drawRoundRect(new RectF(0, 0, width, height), radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, 0, 0, paint);
        source.recycle();
        return clipped;
    }
    
    public static Bitmap mergeBitmap(Bitmap background, Bitmap foreground) {
        int w = background.getWidth() > foreground.getWidth() ? background.getWidth() : foreground.getWidth();
        int h = background.getHeight() > foreground.getHeight() ? background.getHeight() : foreground.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(foreground, 0, 0, null);
        background.recycle();
        foreground.recycle();
        return bitmap;
    }
}
