package com.zhp.service;

import java.util.Calendar;
//import java.util.List;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

import com.zhp.bean.Interiew;
import com.zhp.bean.SelectDate;
import com.zhp.broadcast.InteriewNotificationReceiver;
import com.zhp.d11_androidinteriewskill.AddInteriewActivity;

public class AlarmInteriewService extends IntentService {

	private static final long HALF_HOUR = 1800000;

	public static final String CANCEL = "cancel";

	// private List<Object> mList;

	public AlarmInteriewService() {
		super("AlarmInteriewService");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		createNewAlarm(intent);
	}

	private void createNewAlarm(Intent intent) {
		SelectDate date = (SelectDate) intent
				.getSerializableExtra(AddInteriewActivity.INTERIEW_DATE);

		Interiew interiew = (Interiew) intent
				.getSerializableExtra(AddInteriewActivity.INTERIEW_CONTENT);

		boolean isCancel = intent.getBooleanExtra(CANCEL, false);

		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		Intent receiver = new Intent(this, InteriewNotificationReceiver.class);
		receiver.putExtra(AddInteriewActivity.INTERIEW_CONTENT, interiew);
		System.out.println("id-->" + interiew.getId());
		PendingIntent pi = PendingIntent.getBroadcast(this,
				Integer.parseInt(interiew.getId()), receiver,
				PendingIntent.FLAG_UPDATE_CURRENT);
		if (isCancel) {
			manager.cancel(pi);
		} else {
			long alarmTime = getAlarmTime(date, interiew);
			if (alarmTime == -1) {
				return;
			} else {
				manager.set(AlarmManager.RTC_WAKEUP, alarmTime, pi);
			}
		}
	}

	private long getAlarmTime(SelectDate date, Interiew interiew) {
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		// 设置面试的日期及时间
		calendar.set(date.getYear(), date.getMonthOfYear() - 1,
				date.getDayOfMonth(), date.getHourOfDay(),
				date.getMinuteOfHour());
		// 获取到面试时间的毫秒值
		long interiewTime = calendar.getTimeInMillis();
		long current = System.currentTimeMillis();
		if (current >= interiewTime) {
			return -1;
		}
		long i = (interiewTime - current) / 1000 / 60;
		System.out.println(i + "分钟");
		// 设置提醒时间，为面试时间减去提前提醒的时间
		long alarmTime = interiewTime - interiew.getType() * HALF_HOUR;
		return alarmTime;
	}

}
