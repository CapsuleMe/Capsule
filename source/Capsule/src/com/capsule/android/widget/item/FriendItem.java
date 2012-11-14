package com.capsule.android.widget.item;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.capsule.model.Friend;
import com.cyrilmottier.android.greendroid.R;

public class FriendItem extends Item {

	public Friend friend = null;
	
	public FriendItem(Friend friend)
	{
		this.friend = friend;
	}
	
	@Override
	public ItemView newView(Context context, ViewGroup parent) {
		// TODO Auto-generated method stub
		return createCellFromXml(context, R.layout.capsule_friend_item_view, parent);
	}
}
