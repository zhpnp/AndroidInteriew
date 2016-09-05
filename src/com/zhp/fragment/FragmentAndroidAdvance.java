package com.zhp.fragment;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.zhp.adapter.AndroidAdvanceAdapter;
import com.zhp.bean.AndroidAdvance;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.d11_androidinteriewskill.WebActivity;
import com.zhp.dao.AndroidAdvanceDao;

public class FragmentAndroidAdvance extends Fragment {

	private View view;

	private ListView lv;
	private AndroidAdvanceAdapter mAdapter;

	private List<AndroidAdvance> mList;
	private AndroidAdvanceDao mAdvanceDao;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv = (ListView) view
				.findViewById(R.id.lv_item_viewpager_android_advance);
		mAdvanceDao = new AndroidAdvanceDao(getActivity());
		mList = mAdvanceDao.getList();

		mAdapter = new AndroidAdvanceAdapter(getActivity(),
				R.layout.item_list_advance_android, mList);

		lv.setAdapter(mAdapter);

		setListViewClickListener();
	}

	private void setListViewClickListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				WebActivity.startWebActivity(getActivity(), mList.get(arg2)
						.getUrl());
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(
				R.layout.fragment_item_viewpager_android_advance, container,
				false);

		return view;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

		mAdvanceDao.close();
		super.onDestroy();
	}

}
