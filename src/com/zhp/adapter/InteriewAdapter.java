package com.zhp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhp.bean.Interiew;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.utils.DateTransformUtil;

/**
 * 自定义的面试ListView适配器
 * @author zhp
 *
 */
public class InteriewAdapter extends ArrayAdapter<Interiew> {

	public InteriewAdapter(Context context, int resource, List<Interiew> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder vh = null;
		Interiew interiew = getItem(position);
		if (convertView == null) {
			view = View
					.inflate(getContext(), R.layout.interiew_list_item, null);
			vh = new ViewHolder();
			vh.tv_company = (TextView) view
					.findViewById(R.id.tv_interiew_company);
			vh.tv_time = (TextView) view.findViewById(R.id.tv_interiew_date);
			vh.tv_date = (TextView) view
					.findViewById(R.id.tv_interiew_createdate);
			view.setTag(vh);
		} else {
			view = convertView;
			vh = (ViewHolder) view.getTag();
		}
		vh.tv_company.setText(interiew.getCompany());
		vh.tv_time.setText(DateTransformUtil.cutYear(interiew.getInteriewDate()));
		vh.tv_date.setText(interiew.getDate());
		return view;
	}

	class ViewHolder {
		TextView tv_company;
		TextView tv_time;
		TextView tv_date;
	}

}
