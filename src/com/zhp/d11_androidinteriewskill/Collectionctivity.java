package com.zhp.d11_androidinteriewskill;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zhp.adapter.BaseItemAdapter;
import com.zhp.bean.BaseItem;
import com.zhp.dao.BaseItemDao;
import com.zhp.dao.RawSourceBaseDao;
import com.zhp.fragment.FragmentAndroidBase;
import com.zhp.fragment.FragmentJavaBase;
import com.zhp.utils.BaseActivity;

public class Collectionctivity extends BaseActivity {

	private ListView lv_java;
	private ListView lv_and;
	private BaseItemAdapter mJavaAdapter;
	private BaseItemAdapter mAndAdapter;

	private List<BaseItem> mJavaList;
	private List<BaseItem> mAndList;

	private BaseItemDao mDao;

	private TextView tv;
	private LinearLayout ll;

	public static void startCollectionActivity(Context context) {
		Intent it = new Intent(context, Collectionctivity.class);
		context.startActivity(it);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collectionctivity);
		mDao = new BaseItemDao(this, null);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv_java = (ListView) findViewById(R.id.lv_item_collection_java);
		lv_and = (ListView) findViewById(R.id.lv_item_collection_and);

		mJavaList = mDao.getListOfColletion(RawSourceBaseDao.TABLE_ONE);
		mAndList = mDao.getListOfColletion(RawSourceBaseDao.TABLE_TWO);

		tv = (TextView) findViewById(R.id.tv_no_collection);
		ll = (LinearLayout) findViewById(R.id.ll_collection);

		if (mJavaList.size() == 0 && mAndList.size() == 0) {
			tv.setVisibility(View.VISIBLE);
			ll.setVisibility(View.GONE);
		}

		mJavaAdapter = new BaseItemAdapter(this,
				R.layout.item_list_base_android, mJavaList);
		mAndAdapter = new BaseItemAdapter(this,
				R.layout.item_list_base_android, mAndList);

		lv_java.setAdapter(mJavaAdapter);
		lv_and.setAdapter(mAndAdapter);

		setListViewItemListen();
	}

	private void setListViewItemListen() {
		lv_java.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ItemActivity
						.startItemActivity(Collectionctivity.this,
								FragmentJavaBase.JAVA_BASE_TITLE,
								ItemActivity.JAVA_BASE, mJavaList.get(position)
										.getId());
			}
		});

		lv_and.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ItemActivity.startItemActivity(Collectionctivity.this,
						FragmentAndroidBase.ANDROID_BASE_TITLE,
						ItemActivity.ANDROID_BASE, mAndList.get(position)
								.getId());
			}
		});

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mDao.close();
		super.onDestroy();
	}

}
