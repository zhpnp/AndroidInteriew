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
 * ����֪ͨ���͹㲥
 */
public class InteriewNotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		//ͨ��intent��ȡ����Ҫ��֪ͨ��Interiew����
		Interiew interiew = (Interiew) intent
				.getSerializableExtra(AddInteriewActivity.INTERIEW_CONTENT);
		//����֪ͨ������
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		
		builder.setTicker("��������һ������");
		builder.setContentTitle("��˾��" + interiew.getCompany());
		String time = DateTransformUtil.timeOlny( interiew.getInteriewDate());//��ʱ�����ʾ����Ϊֻ��ʾСʱ������
		builder.setContentText("ʱ�䣺" +time);
		builder.setSmallIcon(R.drawable.ic_launcher);
		//����֪ͨ�ĵ���¼�
		Intent activity = new Intent(context, AddInteriewActivity.class);
		activity.putExtra(AddInteriewActivity.INTERIEW_CONTENT, interiew);
		PendingIntent pi = PendingIntent.getActivity(context,
				Integer.parseInt(interiew.getId()), activity,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		builder.setContentIntent(pi);
		builder.setDefaults(Notification.DEFAULT_ALL);
		Notification notification = builder.build();
		//����֪ͨ
		manager.notify(Integer.parseInt(interiew.getId()), notification);

	}

}
