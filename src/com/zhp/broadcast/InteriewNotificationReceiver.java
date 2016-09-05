package com.zhp.broadcast;

import com.zhp.bean.Interiew;
import com.zhp.d11_androidinteriewskill.AddInteriewActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.utils.DateTransformUtil;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * 
 * @author zhp
 * 面试通知发送广播
 */
public class InteriewNotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//通过intent获取到需要被通知的Interiew对象
		Interiew interiew = (Interiew) intent
				.getSerializableExtra(AddInteriewActivity.INTERIEW_CONTENT);
		//创建通知管理类
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		
		builder.setTicker("您今天有一个面试");
		builder.setContentTitle("公司：" + interiew.getCompany());
		String time = DateTransformUtil.timeOlny( interiew.getInteriewDate());//将时间的显示设置为只显示小时跟分钟
		builder.setContentText("时间：" +time);
		builder.setSmallIcon(R.drawable.ic_launcher);
		//设置通知的点击事件
		Intent activity = new Intent(context, AddInteriewActivity.class);
		activity.putExtra(AddInteriewActivity.INTERIEW_CONTENT, interiew);
		PendingIntent pi = PendingIntent.getActivity(context,
				Integer.parseInt(interiew.getId()), activity,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		builder.setContentIntent(pi);
		builder.setDefaults(Notification.DEFAULT_ALL);
		Notification notification = builder.build();
		//发送通知
		manager.notify(Integer.parseInt(interiew.getId()), notification);

	}

}
