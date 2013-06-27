package com.capsule.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NameCardView extends ImageView {

	private static final int BG_CIRCLE_X = 20;
	private static final int BG_CIRCLE_D = 100;
	private static final int HEAD_CIRCLE_MARGIN = 5;
	
	private Paint topPaint = null;
	private Paint bottomPaint = null;
	private Paint erasePaint = null;
	private Path path = null;
	
	int latestWidth  = 0;
	int latestHeight = 0;
	
	public NameCardView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	public NameCardView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public NameCardView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	protected void init(){
		topPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		int color = Color.parseColor("#DCDEDF");
		topPaint.setColor(color);
		topPaint.setAlpha(191);
		
		bottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		int color2 = Color.parseColor("#E6E6E6");
		bottomPaint.setColor(color2);
		
		erasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		erasePaint.setColor(Color.BLACK);
		erasePaint.setXfermode(new PorterDuffXfermode(Mode.DST_OUT));
		
		path = new Path();
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		// TODO Auto-generated method stub
		if(isSizeChange())
		{
			saveSize();			
			drawBackground(canvas);
			
			final Drawable drawable = getDrawable();
			if(drawable !=null){
				drawHead(canvas,(BitmapDrawable)drawable);
			}
			return;
		}
		super.onDraw(canvas);
	}

	private boolean isSizeChange()
	{
		return this.getWidth()!=latestWidth || this.getHeight() != latestHeight;
	}
	
	private void saveSize()
	{
		latestWidth = getWidth();
		latestHeight = getHeight();
	}
	
	private void drawBackground(Canvas canvas){

		RectF recF = getBgCircle();
		
		
		path.reset();
		path.moveTo(0, 0);
		path.lineTo(latestWidth,0);
		path.lineTo(latestWidth, latestHeight/2);
		path.arcTo(recF, 0, 180);
		path.lineTo(0, latestHeight/2);
		path.close();
		canvas.drawPath(path, topPaint);
		
		Bitmap tempBitmap=Bitmap.createBitmap(latestWidth,latestHeight,Config.ARGB_4444);
		Canvas temptCanvas=new Canvas(tempBitmap);
		temptCanvas.drawColor(Color.TRANSPARENT);
		
		path.reset();
		path.moveTo(0, latestHeight/2);
		path.lineTo(latestWidth,latestHeight/2);
		path.lineTo(latestWidth, latestHeight);
		path.lineTo(0, latestHeight);
		path.close();
		temptCanvas.drawPath(path, bottomPaint);
		
		path.reset();
		path.arcTo(recF, 0, 180);
		temptCanvas.drawPath(path,erasePaint);
		
		canvas.drawBitmap(tempBitmap,0,0,null);
		tempBitmap.recycle();
	}
	
	private RectF getBgCircle(){
		int y = (latestHeight-BG_CIRCLE_D)/2;
		return new RectF(
				BG_CIRCLE_X,
				y,
				BG_CIRCLE_X+BG_CIRCLE_D,
				y+BG_CIRCLE_D);
	}
	
	private RectF getHeadCircle()
	{
		RectF rectC = getRectFC(getBgCircle());
		rectC.top +=HEAD_CIRCLE_MARGIN;
		rectC.left +=HEAD_CIRCLE_MARGIN;
		rectC.right -=HEAD_CIRCLE_MARGIN;
		rectC.bottom -=HEAD_CIRCLE_MARGIN;
		return rectC;
	}
	
	private void drawHead(Canvas canvas,BitmapDrawable maiDrawable){
		Bitmap tempBitmap=Bitmap.createBitmap(latestWidth,latestHeight,Config.ARGB_4444);
		Canvas temptCanvas=new Canvas(tempBitmap);
		temptCanvas.drawColor(Color.TRANSPARENT);
		
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	    paint.setColor(Color.BLACK);
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    
	    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
	    paint.setColor(Color.BLACK);
	    
		RectF rectC = getHeadCircle();
		temptCanvas.drawRoundRect(rectC, rectC.height(), rectC.height(), paint2);
		//temptCanvas.drawRect(getRectC(rectC),paint2);
		temptCanvas.drawBitmap(
				maiDrawable.getBitmap(), 
				null, 
				getRectC(rectC), 
				paint);
		
		
		canvas.drawBitmap(tempBitmap,0,0,null);
		tempBitmap.recycle();
	}
	
	private RectF getRectFC(RectF rectF)
	{
		float l, s,top,left,right,bottom;
		if(rectF.height()>rectF.width())
		{
			l = rectF.height();
			s = rectF.width();
			
			left = rectF.left;
			top = rectF.top+(l-s)/2;
			bottom = rectF.top+(l+s)/2;
			right = rectF.left + s;
			
		}else{
			s = rectF.height();
			l = rectF.width();
			
			top = rectF.top;
			left = rectF.left+(l-s)/2;
			right = rectF.left+(l+s)/2;
			bottom = rectF.top + s;
		}
		
		RectF rectC = new RectF(left,top,right,bottom);
	  return rectC;
	}

	private Rect getRectC(RectF rectF)
	{
		/*return new Rect(0,0,200,200);*/
		
		return new Rect(
				(int)rectF.left,
				(int)rectF.top,
				(int)rectF.right,
				(int)rectF.bottom
				);
	}
}
