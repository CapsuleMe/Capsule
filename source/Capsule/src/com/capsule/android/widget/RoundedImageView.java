package com.capsule.android.widget;

import com.capsule.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * An ImageView that allows a pixel corner radius
 * to be specified. The image's corners will be rounded
 * to this radius, with transparent behind the image.
 *
 * <b>WARNING:</b> This is only supported for bitmap
 * image sources. If other types of drawable are set
 * as the image source, the corners <b>will not be
 * rounded.</b>
 * @author alex
 *
 */
public class RoundedImageView extends ImageView {

	protected float mCornerRadius;
	protected boolean mCircle;

	public RoundedImageView(Context context) {
		super(context);
	}

	public RoundedImageView(Context context, AttributeSet attributes) {
		super(context, attributes);
		TypedArray array = context.obtainStyledAttributes(attributes, R.styleable.RoundedImageView);
		if (array != null) {
			mCornerRadius = array.getDimension(R.styleable.RoundedImageView_corner_radius, 0);
			mCircle = array.getBoolean(R.styleable.RoundedImageView_circle, false);
			array.recycle();
		}
	}

	/**
	 * Sets the corner radius for rounded image corners in
	 * absolutely display pixels.
	 * @param cornerRadius
	 */
	public void setCornerRadius(float cornerRadius) {
		mCornerRadius = cornerRadius;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// Round some corners betch!
		Drawable maiDrawable = getDrawable();
		
		if(! (maiDrawable instanceof BitmapDrawable)){
			super.onDraw(canvas);
			return;
		}
		
		if(mCircle)
		{
			drawCircle(canvas,(BitmapDrawable) maiDrawable);
			return;
		}
		
		drawRound(canvas,(BitmapDrawable) maiDrawable);
	}
	
	
	private void drawRound(Canvas canvas,BitmapDrawable maiDrawable)
	{
		Paint paint = maiDrawable.getPaint();
        final int color = 0xff000000;
        Rect bitmapBounds = maiDrawable.getBounds();
        final RectF rectF = new RectF(bitmapBounds);
        // Create an off-screen bitmap to the PorterDuff alpha blending to work right
		int saveCount = canvas.saveLayer(rectF, null,
                Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		// Resize the rounded rect we'll clip by this view's current bounds
		// (super.onDraw() will do something similar with the drawable to draw)
		getImageMatrix().mapRect(rectF);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
   
        canvas.drawRoundRect(rectF, mCornerRadius, mCornerRadius, paint);

		Xfermode oldMode = paint.getXfermode();
		// This is the paint already associated with the BitmapDrawable that super draws
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        super.onDraw(canvas);
        paint.setXfermode(oldMode);
        canvas.restoreToCount(saveCount);
	}
	
	private void drawCircle(Canvas canvas,BitmapDrawable maiDrawable)
	{
		Paint paint = maiDrawable.getPaint();
        final int color = 0xff000000;
        Rect bitmapBounds = maiDrawable.getBounds();
        final RectF rectF = new RectF(bitmapBounds);
        // Create an off-screen bitmap to the PorterDuff alpha blending to work right
		int saveCount = canvas.saveLayer(rectF, null,
                Canvas.MATRIX_SAVE_FLAG |
                Canvas.CLIP_SAVE_FLAG |
                Canvas.HAS_ALPHA_LAYER_SAVE_FLAG |
                Canvas.FULL_COLOR_LAYER_SAVE_FLAG |
                Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		// Resize the rounded rect we'll clip by this view's current bounds
		// (super.onDraw() will do something similar with the drawable to draw)
		getImageMatrix().mapRect(rectF);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
   
        
        
        RectF rectC = getRectC(rectF);
        canvas.drawRoundRect(rectC, rectC.height(), rectC.height(), paint);

		Xfermode oldMode = paint.getXfermode();
		// This is the paint already associated with the BitmapDrawable that super draws
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        super.onDraw(canvas);
        paint.setXfermode(oldMode);
        canvas.restoreToCount(saveCount);
	}
	
	private RectF getRectC(RectF rectF)
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
}