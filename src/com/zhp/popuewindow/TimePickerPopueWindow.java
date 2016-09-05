package com.zhp.popuewindow;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

import com.zhp.bean.SelectDate;
import com.zhp.d11_androidinteriewskill.R;

public class TimePickerPopueWindow extends PopupWindow {
	private View view;
	private LayoutInflater mInflater;

	private TextView tv;
	private DatePicker dp;
	private TimePicker tp;
	private Button btn_ok;
	private Button btn_cancel;

	private SelectDate mDate;

	public TimePickerPopueWindow(Context context, SelectDate date) {
		super(context);
		this.mDate = date;
		mInflater = LayoutInflater.from(context);
		view = mInflater.inflate(R.layout.timepicker_popuewindow, null);
		init(context);
	}

	private void init(Context context) {
		initPop((Activity) context);
		initView();
		dateChange();
	}

	private void initView() {
		// TODO Auto-generated method stub
		dp = (DatePicker) view.findViewById(R.id.dp_interiew_pop_time);
		ViewGroup gp1 = (ViewGroup) dp.getChildAt(0);
		ViewGroup gp2 = (ViewGroup) gp1.getChildAt(0);
		gp2.getChildAt(0).setVisibility(View.GONE);// 设置年份不显示

		tp = (TimePicker) view.findViewById(R.id.tp_interiew_pop_time);
		tp.setIs24HourView(true);

		tv = (TextView) view
				.findViewById(R.id.tv_interiew_pop_timepick_showtime);
		btn_ok = (Button) view.findViewById(R.id.btn_interiew_pop_timepick_ok);
		btn_cancel = (Button) view
				.findViewById(R.id.btn_interiew_pop_timepick_cancel);
		if (mDate.getYear() == 0) {
			mDate = new SelectDate(dp.getYear(), dp.getMonth() + 1,
					dp.getDayOfMonth(), tp.getCurrentHour(),
					tp.getCurrentMinute());
		}
		
		initTimePicker();
		tv.setText(mDate.toString());
	}

	private void initTimePicker() {
		tp.setCurrentHour(mDate.getHourOfDay());
		tp.setCurrentMinute(mDate.getMinuteOfHour());

		dp.init(mDate.getYear(), mDate.getMonthOfYear() - 2,
				mDate.getDayOfMonth(), null);
	}

	private void dateChange() {
		tp.setOnTimeChangedListener(new OnTimeChangedListener() {
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				mDate.setHourOfDay(hourOfDay);
				mDate.setMinuteOfHour(minute);
				tv.setText(mDate.toString());
			}
		});

		dp.init(dp.getYear(), dp.getMonth() + 1, dp.getDayOfMonth(),
				new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						mDate.setYear(year);
						mDate.setMonthOfYear(monthOfYear + 1);
						mDate.setDayOfMonth(dayOfMonth);
						tv.setText(mDate.toString());
					}
				});
	}

	public void onTimePickerSelectListener(final CallbackListener callback) {
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.select(mDate);
				dismiss();
			}
		});
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});

	}

	private void initPop(Activity context) {
		// TODO Auto-generated method stub
		setContentView(view);
		DisplayMetrics metrics = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int height = metrics.heightPixels;
		int width = metrics.widthPixels;
		setWidth(width);
		setHeight((int) (height * 0.75));

		setFocusable(true);
		setTouchable(true);

		update();
		setAnimationStyle(R.style.InteriewAlarm);

	}

	public void showPopueWindow(View parent) {
		if (this.isShowing()) {
			this.dismiss();
		} else {
			this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
		}
	}

	public interface CallbackListener {
		void select(SelectDate date);
		// void cancel();
	}
}
