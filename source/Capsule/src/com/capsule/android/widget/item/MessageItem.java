package com.capsule.android.widget.item;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.capsule.android.R;
import com.capsule.model.Message;

public class MessageItem extends Item {

	public Message msg = null;
	
	public MessageItem(Message msg)
	{
		this.msg = msg;
	}
	
	@Override
	public ItemView newView(Context context, ViewGroup parent) {
		// TODO Auto-generated method stub
		return createCellFromXml(context, R.layout.capsule_message_item_view, parent);
	}

	
}
