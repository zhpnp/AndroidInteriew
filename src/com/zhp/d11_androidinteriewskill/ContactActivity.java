package com.zhp.d11_androidinteriewskill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zhp.utils.BaseActivity;

public class ContactActivity extends BaseActivity {

	private String text = "本人也是一个新手，写这个项目主要用于锻炼自己以及跟大家分享交流，"
			+ "如果大家有什么建议或者指导都可以联系我，同时我也希望能跟大家交流技术心得。最后貼上我的联系方式（其实这个活动有点多余- -）";

	private String qq = "联系方式：503421548@qq.com"
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
