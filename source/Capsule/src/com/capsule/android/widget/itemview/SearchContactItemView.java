package com.capsule.android.widget.itemview;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.capsule.android.R;
import com.capsule.android.widget.item.SearchContactItem;

public class SearchContactItemView extends RelativeLayout implements ItemView{

	private TextView nameView = null;
	private TextView actionView = null;
	
	public SearchContactItemView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	

	public SearchContactItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public SearchContactItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}


	public void prepareItemView() {
	
		nameView = (TextView)findViewById(R.id.search_result_name);
		actionView = (TextView)findViewById(R.id.search_result_action);
	}

	public void setObject(Item item) {
		// TODO Auto-generated method stub
		final SearchContactItem mItem = (SearchContactItem)item;
		nameView.setText(mItem.user.getName());
		actionView.setText(getActionStr(mItem));

	}
	
	private String getActionStr(SearchContactItem item)
	{
		if(item.user.isFriend())
		{
			return "查看";
		}
		
		if(item.user.isUser())
		{
			return "添加";
		}
		
		return "邀请";
	}
}
