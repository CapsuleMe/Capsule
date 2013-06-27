package com.capsule.android.widget.itemview;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.capsule.android.R;
import com.capsule.android.cache.ImgCache;
import com.capsule.android.widget.RoundedImageView;
import com.capsule.android.widget.item.MessageItem;

public class MessageItemView extends RelativeLayout implements ItemView{

	private RoundedImageView mHeadView = null;
	private TextView mNameView = null;
	private TextView mContentView = null;
	private TextView mTimeView = null;
	
	public MessageItemView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	

	public MessageItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public MessageItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	public void prepareItemView() {
		// TODO Auto-generated method stub
		mHeadView = (RoundedImageView)findViewById(R.id.message_head);
		mNameView = (TextView)findViewById(R.id.message_name);
		mContentView = (TextView)findViewById(R.id.message_content);
		mTimeView = (TextView)findViewById(R.id.message_time);
		
		mHeadView.setUrl(FriendItemView.TestImgUrl);
		mHeadView.setDefaultImageDrawable(ImgCache.getInstance().getDefaultFace());
	}

	public void setObject(Item item) {
		// TODO Auto-generated method stub
		final MessageItem mitem = (MessageItem)item;
		mNameView.setText(mitem.msg.getUser().getName());
		mContentView.setText(mitem.msg.getContent());
		mTimeView.setText("10∑÷÷”«∞");
		
		
	}

}
