package com.example.hadideknache.financeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * This class is used for creating a custom text, by extending the textview and drawing on it
 * Created by Hadi Deknache on 2017-09-20.
 */

public class CustomText extends android.support.v7.widget.AppCompatTextView {

    public CustomText(Context context) {
        super(context);
    }
    public CustomText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        /*textToChange=text.toString();
        for (int i=0; i<=text.length();i++){
            coloredText += Html.fromHtml("<font color='"+colors[i]+"'><b>"+text.charAt(i)+"/b></font>");
            coloredText+= text.

        }*/
        super.setText(text, type);
    }

    /**
     * Overridden method which a line is drawn beneath the text
     * @param canvas the canvas of the textview
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = getPaint();
        paint.setStrokeWidth(10);
        int x = (int) (0);
        int stopX = canvas.getWidth();
        int y = getHeight()-20;
        int stopY = getHeight()-20;

        canvas.drawLine(x,y,stopX,stopY,paint);

    }
}
