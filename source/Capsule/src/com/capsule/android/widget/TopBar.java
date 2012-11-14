package com.capsule.android.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.capsule.android.R;

public class TopBar extends RelativeLayout {

	@SuppressWarnings("deprecation")
	public TopBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		Drawable bg = this.getResources().getDrawable(R.drawable.blue_background_normal);
		this.setBackgroundDrawable(bg);
	}
	
	public TopBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setBackground();
	}

	

	public TopBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		// TODO Auto-generated constructor stub
		//inflate(context);
		setBackground();
	}	
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int height = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 
				50, 
				getResources().getDisplayMetrics());
		this.setMeasuredDimension(width, height);
	}

	@SuppressWarnings("deprecation")
	private void setBackground()
	{
		Drawable bg = this.getResources().getDrawable(R.drawable.blue_background_normal);
		this.setBackgroundDrawable(bg);
		
		
	}
	
	/*private int getMeasureWidth()
	{
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	private int getMeasureHeight()
	{
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 
				50, 
				getResources().getDisplayMetrics());
	
	}*/
	
}
