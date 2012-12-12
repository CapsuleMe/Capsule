package com.capsule.android.widget.item;

import greendroid.widget.item.Item;
import greendroid.widget.itemview.ItemView;
import android.content.Context;
import android.view.ViewGroup;

import com.capsule.android.R;
import com.capsule.model.TempUser;

public class SearchContactItem extends Item {

	public TempUser user = null;
	
	public SearchContactItem(TempUser user)
	{
		this.user = user;
	}
	
	@Override
	public ItemView newView(Context context, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.createCellFromXml(context, R.layout.capsule_search_contact_item_view, parent);
	}

}
