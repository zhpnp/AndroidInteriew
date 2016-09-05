package com.zhp.d11_androidinteriewskill;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zhp.bean.Interiew;
import com.zhp.bean.SelectDate;
import com.zhp.dao.InteriewDao;
import com.zhp.fragment.FragmentNote;
import com.zhp.popuewindow.InteriewAlarmPopuewindow;
import com.zhp.popuewindow.TimePickerPopueWindow;
import com.zhp.service.AlarmInteriewService;
import com.zhp.utils.BaseActivity;
import com.zhp.utils.DateTransformUtil;

/**
 * 
 * @author zhp
 *	添加和修改面试的Activity
 */
public class AddInteriewActivity extends BaseActivity implements
		OnClickListener {
	//声明一些用于Intent传值的键名
	public static final String INTERIEW_CONTENT = "interviewContent";
	public static final String INTERIEW_DATE = "interiewdate";

	private EditText et_company, et_job, et_reamrk;
	private Button btn_time, btn_alarm;
	private ImageButton ibtn_ok;
	// private LinearLayout ll;//作为popuewindow显示的父控件

	private Interiew mInteriew;
	private InteriewDao mDao;

	private int which;// 用于记录用户选择提醒时间对应的类型
	private InteriewAlarmPopuewindow ip;
	private TimePickerPopueWindow tppw;

	private SelectDate mDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_interiew);
		init();
	}

	public void init() {
		initView();
		if (getIntent().getSerializableExtra(INTERIEW_CONTENT) != null) {
			mInteriew = (Interiew) getIntent().getSerializableExtra(
					INTERIEW_CONTENT);
			initContent();
			cancelNotification();
		}
		mDao = new InteriewDao(this);
	}
	
	/**
	 * 当用户是通过通知的点击事件跳转到该页面时，该方法执行，用于取消通知的显示
	 */
	private void cancelNotification() {
		// TODO Auto-generated method stub
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.cancel(Integer.parseInt(mInteriew.getId()));

	}

	public void initView() {
		et_company = (EditText) findViewById(R.id.et_interiew_company);
		et_job = (EditText) findViewById(R.id.et_interiew_job);
		et_reamrk = (EditText) findViewById(R.id.et_interiew_remark);
		// ll = (LinearLayout)findViewById(R.id.ll_interiew_add);

		btn_time = (Button) findViewById(R.id.btn_interiew_time);
		btn_alarm = (Button) findViewById(R.id.btn_interiew_alarm);
		ibtn_ok = (ImageButton) findViewById(R.id.ibtn_interiew_ok);
		btn_time.setOnClickListener(this);
		btn_alarm.setOnClickListener(this);
		ibtn_ok.setOnClickListener(this);

	}

	/**
	 * 初始化面试的内容，包括公司，时间等
	 */
	private void initContent() {
		et_company.setText(mInteriew.getCompany());
		et_job.setText(mInteriew.getJob());
		et_reamrk.setText(mInteriew.getRemark());
		if (DateTransformUtil.cutYear(mInteriew.getInteriewDate()) == null) {
			btn_time.setText("请选择时间");
		} else {
			btn_time.setText(DateTransformUtil.cutYear(mInteriew
					.getInteriewDate()));
		}
		btn_alarm.setText(InteriewAlarmPopuewindow.ALARM_ITEMS[mInteriew
				.getType()]);
		which = mInteriew.getType();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_interiew_alarm:
			showAlarmPopueWindow();
			break;

		case R.id.btn_interiew_time:
			if (mDate == null && mInteriew == null) {
				mDate = new SelectDate();
			} else if (mDate == null && mInteriew != null) {
				mDate = DateTransformUtil
						.transform(mInteriew.getInteriewDate());
			}
			showTimePickPopueWindow(mDate);
			break;

		case R.id.ibtn_interiew_ok:
			saveData();
			Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
			finish();
			break;
		}
	}

	/**
	 * 显示面试时间选择的PopueWindow
	 * @param date 选择日期的对象
	 * 
	 */
	private void showTimePickPopueWindow(SelectDate date) {
		// TODO Auto-generated method stub
		tppw = new TimePickerPopueWindow(this, date);
		tppw.showPopueWindow(btn_time);
		tppw.onTimePickerSelectListener(new TimePickerPopueWindow.CallbackListener() {
			@Override
			public void select(SelectDate date) {
				// TODO Auto-generated method stub
				String text = DateTransformUtil.cutYear(date.toString());
				mDate = date;
				btn_time.setText(text);
			}
		});

	}
	
	/**
	 * 显示提醒时间选择的PopueWindow
	 */
	private void showAlarmPopueWindow() {
		ip = new InteriewAlarmPopuewindow(this);
		ip.showPopueWindow(btn_alarm);
		ip.setOnItemClickListenr(new InteriewAlarmPopuewindow.Callback() {
			@Override
			public void onItemChoice(int position, String alarm) {
				// TODO Auto-generated method stub
				which = position;
				btn_alarm.setText(alarm);
			}
		});
	}
	
	/**
	 * 保存数据
	 */
	public void saveData() {
		String company = et_company.getText().toString();
		String job = et_job.getText().toString();
		String time = null;
		if (mDate != null) {//防止空指针
			time = mDate.toString();
		}
		String remark = et_reamrk.getText().toString();
		String date = FragmentNote.getDate();
		int type = which;
		//新建面试时需要将对象创建出来
		if (mInteriew == null) {
			mInteriew = new Interiew();
		}
		mInteriew.setCompany(company);
		mInteriew.setDate(date);
		mInteriew.setInteriewDate(time);
		mInteriew.setJob(job);
		mInteriew.setRemark(remark);
		mInteriew.setType(type);
		//判断id号是否为空，主要为了保证新建通知提醒任务时不出现空指针
		if (mInteriew.getId() == null) {
			mInteriew.setId(mDao.getCount() + 1 + "");
		}
		//回传值给启动该活动的界面
		Intent it = new Intent();
		it.putExtra(INTERIEW_CONTENT, mInteriew);
		setResult(RESULT_OK, it);
		
		//启动服务，开启新的通知提醒任务
		if (mDate != null && type != 0) {
			Intent service = new Intent(this, AlarmInteriewService.class);
			service.putExtra(INTERIEW_DATE, mDate);
			service.putExtra(INTERIEW_CONTENT, mInteriew);
			startService(service);
		}

	}

}
