package com.capsule.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;


public class MapBarTextView extends TextView {

    private Paint mPaint;
    
    public MapBarTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true); 
        mPaint.setStyle(Paint.Style.STROKE); 
        mPaint.setStrokeWidth(6);  
        mPaint.setColor(Color.parseColor("#1A8DCC"));
    }
    @Override  
    public void onDraw(Canvas canvas) {  
        super.onDraw(canvas);  
        //画底线  
        canvas.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight(), mPaint);  
    }  
}
