package com.capsule.android.widget.itemview;

import greendroid.widget.AsyncImageView;
import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.capsule.android.R;
import com.capsule.android.widget.item.FriendItem;

public class FriendItemView extends RelativeLayout implements ItemView {

	private AsyncImageView mHeadView = null;
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
		mHeadView = (AsyncImageView)findViewById(R.id.friend_head);
		mNameView = (TextView)findViewById(R.id.friend_name);
		mAddressView = (TextView)findViewById(R.id.friend_address);
		mTimeView = (TextView)findViewById(R.id.friend_time);
		mDistanceView = (TextView)findViewById(R.id.friend_distance);
		
	}

	public void setObject(Item item) {
		// TODO Auto-generated method stub
		final FriendItem fitem = (FriendItem)item;
		mNameView.setText(fitem.friend.getUser().getName());
		mAddressView.setText(fitem.friend.getLocation().getAddress());
		mTimeView.setText("10分钟前");
		mDistanceView.setText("10.2m");
		
		
	}

}
