package com.zhp.popuewindow;

import com.zhp.d11_androidinteriewskill.R;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class InteriewAlarmPopuewindow extends PopupWindow {
	private View view;
	private LayoutInflater mInflater;

	private ListView lv;
	private ArrayAdapter<String> mAdapter;
	public static final String[] ALARM_ITEMS = { "������", "���Сʱǰ", "һ��Сʱǰ", "һ����Сʱǰ", "����Сʱǰ",
			"������Сʱǰ", "����Сʱǰ" };

	public InteriewAlarmPopuewindow(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		view = mInflater.inflate(R.layout.interiew_popuewindow, null);
		initView((Activity) context);
		initPop((Activity) context);
	}

	private void initPop(Activity context) {
		// TODO Auto-generated method stub
		setContentView(view);
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		setWidth(width);
		setHeight(height/2);

		setFocusable(true);
		setTouchable(true);

		update();
		setAnimationStyle(R.style.InteriewAlarm);

	}

	private void initView(Activity context) {
		// TODO Auto-generated method stub
		lv = (ListView) view.findViewById(R.id.lv_interiew_popuewindow);
		mAdapter = new ArrayAdapter<String>(context,
				R.layout.interiew_popuewindow_list,
				R.id.tv_interiew_popuewindow_list, ALARM_ITEMS);
		lv.setAdapter(mAdapter);
	}

	public void showPopueWindow(View parent){
		if(this.isShowing()){
			this.dismiss();
		}else{
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		}
		
	}
	
	/**
	 * 
	 * @param callback ������ڲ��ӿڣ������¼��Ļص�
	 */
	public void setOnItemClickListenr(final Callback callback){
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				callback.onItemChoice(position, ALARM_ITEMS[position]);
				dismiss();
			}
		});
		
	}
	
	public interface Callback{
		
		void onItemChoice(int position,String alarm);
	}
}
