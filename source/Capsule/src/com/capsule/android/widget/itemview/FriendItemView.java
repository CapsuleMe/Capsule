package com.capsule.android.widget.itemview;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.capsule.android.R;
import com.capsule.android.UserInfoActivity;
import com.capsule.android.cache.ImgCache;
import com.capsule.android.widget.RoundedImageView;
import com.capsule.android.widget.item.FriendItem;

public class FriendItemView extends RelativeLayout implements ItemView, OnClickListener{

	public static final String TestImgUrl =
			"http://nunu.in/media/2010/04/Elisha-Cuthbert-girl-next-door-latest-pictures-wallpapers-photos-gallery-full-elisha-cuthbert-face.jpg";
	private static final int delay = 1000;
	
	private FriendItem fitem = null;
	private RoundedImageView mHeadView = null;
	private TextView mNameView = null;
	private TextView mAddressView = null;
	private TextView mTimeView = null;
	private TextView mDistanceView = null;
	
	public FriendItemView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	

	public FriendItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public FriendItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	public void prepareItemView() {
		// TODO Auto-generated method stub
		mHeadView = (RoundedImageView)findViewById(R.id.friend_head);
		mNameView = (TextView)findViewById(R.id.friend_name);
		mAddressView = (TextView)findViewById(R.id.friend_address);
		mTimeView = (TextView)findViewById(R.id.friend_time);
		mDistanceView = (TextView)findViewById(R.id.friend_distance);
		
		mHeadView.setUrl(TestImgUrl);
		mHeadView.setDefaultImageDrawable(ImgCache.getInstance().getDefaultFace());
		
		setOnClickListener(this);
	}

	public void setObject(Item item) {
		// TODO Auto-generated method stub
		fitem = (FriendItem)item;
		mNameView.setText(fitem.friend.getName());
		mAddressView.setText(fitem.friend.getLocation().getAddress());
		mTimeView.setText("10分钟前");
		mDistanceView.setText("10.2m");
		
		
		
	}


	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(fitem == null)
			return;

		Context ctx = this.getContext();
		ctx.startActivity(new Intent(ctx,UserInfoActivity.class));
	}

}
