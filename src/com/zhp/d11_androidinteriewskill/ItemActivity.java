package com.zhp.d11_androidinteriewskill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhp.bean.BaseItem;
import com.zhp.dao.BaseItemDao;
import com.zhp.dao.RawSourceBaseDao;
import com.zhp.utils.BaseActivity;

public class ItemActivity extends BaseActivity implements OnClickListener {
	public static final String TITLE = "title";
	public static final String TYPE = "type";
	public static final String ID = "id";

	public static final int JAVA_BASE = 1;
	public static final int ANDROID_BASE = 2;

	public static final int COLLECTION_TRUE = 2;
	public static final int COLLECTION_FALSE = 1;

	private static final String SPACE_TIWCE = "\u3000\u3000";

	private Button btn_answer;
	private TextView tv_question, tv_answer, tv_name;
	private ImageButton ibtn_collection, ibtn_back;

	private BaseItemDao mBaseItemDao;

	private String question;
	private String ansewer;

	private boolean isHidden = true;// 用于判断答案是否隐藏
	private int isCollection = 1;// 判断用户是否收藏该题
	private int id;// 记录题目id

	public static void startItemActivity(Context context, String title,
			int type, int id) {
		Intent it = new Intent(context, ItemActivity.class);
		it.putExtra(TITLE, title);
		it.putExtra(TYPE, type);
		it.putExtra(ID, id);
		context.startActivity(it);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		initView();
		getData();
		initData();
	}

	private void getData() {
		// TODO Auto-generated method stub
		if (getIntent().getIntExtra(TYPE, 0) == JAVA_BASE) {
			mBaseItemDao = new BaseItemDao(this, RawSourceBaseDao.TABLE_ONE);
		} else if (getIntent().getIntExtra(TYPE, 0) == ANDROID_BASE) {
			mBaseItemDao = new BaseItemDao(this, RawSourceBaseDao.TABLE_TWO);
		}
		id = getIntent().getIntExtra(ID, 1);
		BaseItem baseItem = mBaseItemDao.query(new String[] { id + "" });
		question = baseItem.getItem();
		ansewer = baseItem.getAnswer();
		isCollection = baseItem.isCollection();

	}

	private void initData() {
		// TODO Auto-generated method stub
		tv_name.setText(getIntent().getStringExtra(TITLE));
		tv_question.setText(SPACE_TIWCE + question);
		tv_answer.setText(ansewer);
		if (isCollection == COLLECTION_TRUE) {
			ibtn_collection.setImageResource(R.drawable.collection_yes);
		} else {
			ibtn_collection.setImageResource(R.drawable.collection_no);
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_answer = (Button) findViewById(R.id.btn_item_answer);
		btn_answer.setOnClickListener(this);

		tv_question = (TextView) findViewById(R.id.tv_item_question);
		tv_answer = (TextView) findViewById(R.id.tv_item_answer);
		tv_name = (TextView) findViewById(R.id.tv_item_name);

		ibtn_collection = (ImageButton) findViewById(R.id.ibtn_item_collection);
		ibtn_back = (ImageButton) findViewById(R.id.ibtn_item_back);
		ibtn_back.setOnClickListener(this);
		ibtn_collection.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_item_answer:
			displayAnswer();
			break;
		case R.id.ibtn_item_back:
			mBaseItemDao.updateBaseItem(isCollection, new String[] { id + "" });
			finish();
			break;
		case R.id.ibtn_item_collection:
			isCollectionChecked();
		}

	}

	private void isCollectionChecked() {
		if (isCollection == COLLECTION_FALSE) {
			ibtn_collection.setImageResource(R.drawable.collection_yes);
			Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
			isCollection = COLLECTION_TRUE;
		} else {
			ibtn_collection.setImageResource(R.drawable.collection_no);
			Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
			isCollection = COLLECTION_FALSE;
		}
	}

	private void displayAnswer() {
		if (isHidden) {
			btn_answer.setText("隐藏参考答案");
			tv_answer.setVisibility(View.VISIBLE);
			isHidden = false;
		} else {
			btn_answer.setText("显示参考答案");
			tv_answer.setVisibility(View.GONE);
			isHidden = true;
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (mBaseItemDao != null) {
			mBaseItemDao.close();
		}
		super.onDestroy();
	}

}
