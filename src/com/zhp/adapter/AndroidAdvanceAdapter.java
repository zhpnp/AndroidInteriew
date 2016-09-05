package com.zhp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhp.bean.AndroidAdvance;
import com.zhp.d11_androidinteriewskill.R;
/**
 * android进阶部分ListView的适配器
 * @author zhp
 *
 */
public class AndroidAdvanceAdapter extends ArrayAdapter<AndroidAdvance> {

	public AndroidAdvanceAdapter(Context context, int resource,
			List<AndroidAdvance> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder vh = null;
		AndroidAdvance advance = getItem(position);
		if(convertView == null){
			view = View.inflate(getContext(), R.layout.item_list_advance_android, null);
			vh = new ViewHolder();
			vh.tv_title = (TextView)view.findViewById(R.id.tv_item_list_advance_title);
			vh.tv_from = (TextView)view.findViewById(R.id.tv_item_list_advance_from);
			view.setTag(vh);
		}else{
			view = convertView;
			vh = (ViewHolder) view.getTag();
		}
		vh.tv_title.setText(advance.getTitle());
		vh.tv_from.setText(advance.getFrom());
		return view;
	}
	
	class ViewHolder{
		TextView tv_title;
		TextView tv_from;
		
	}

}
