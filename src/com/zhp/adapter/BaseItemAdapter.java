package com.zhp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhp.bean.BaseItem;
import com.zhp.d11_androidinteriewskill.R;

/**
 * java基础部分的ListView的适配器
 * @author zhp
 *
 */
public class BaseItemAdapter extends ArrayAdapter<BaseItem> {

	public BaseItemAdapter(Context context, int resource,
			List<BaseItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		TextView tv = null;
		BaseItem baseItem = getItem(position);
		if(convertView == null){
			view = View.inflate(getContext(), R.layout.item_list_base_java, null);
			tv = (TextView)view.findViewById(R.id.tv_item_list_base);
			view.setTag(tv);
		}else{
			view = convertView;
			tv = (TextView) view.getTag();
		}
		tv.setText(baseItem.getItem());
		return view;
	}

}
