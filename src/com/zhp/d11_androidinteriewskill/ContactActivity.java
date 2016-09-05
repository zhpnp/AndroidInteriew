package com.zhp.d11_androidinteriewskill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zhp.utils.BaseActivity;

public class ContactActivity extends BaseActivity {

	private String text = "����Ҳ��һ�����֣�д�����Ŀ��Ҫ���ڶ����Լ��Լ�����ҷ�������"
			+ "��������ʲô�������ָ����������ϵ�ң�ͬʱ��Ҳϣ���ܸ���ҽ��������ĵá�����N���ҵ���ϵ��ʽ����ʵ�����е����- -��";

	private String qq = "��ϵ��ʽ��503421548@qq.com"
			+ "\n\u3000\u3000503776961@qq.com"
			+ "\n\u3000\u30001026103323@qq.com";

	public static void startContactActivity(Context context) {
		Intent it = new Intent(context, ContactActivity.class);
		context.startActivity(it);

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact);
		TextView tv = (TextView) findViewById(R.id.tv_contact);
		TextView email = (TextView) findViewById(R.id.tv_email);

		tv.setText("\u3000\u3000" + text);
		email.setText(qq);
	}

}
