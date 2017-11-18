package com.example.hadideknache.financeapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hadideknache on 2017-09-20.
 */

public class CustomText extends android.support.v7.widget.AppCompatTextView {
    String[] colors = {"#FFFF00","#FF00FF","#FF0000","#808080","#00FFFF","#000080","#00FF00","#008080","#008000","#800080"};
    String textToChange;
    String coloredText;

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
