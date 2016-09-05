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

import com.zhp.adapter.BaseItemAdapter;
import com.zhp.bean.BaseItem;
import com.zhp.d11_androidinteriewskill.ItemActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.dao.BaseItemDao;
import com.zhp.dao.RawSourceBaseDao;

public class FragmentAndroidBase extends Fragment {
	public static final String ANDROID_BASE_TITLE = "Android»ù´¡";

	private View view;

	private ListView lv;
	private BaseItemAdapter mAdapter;

	private List<BaseItem> mList;
	private BaseItemDao mDao;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv = (ListView) view.findViewById(R.id.lv_item_viewpager_android_base);
		mDao = new BaseItemDao(getActivity(),RawSourceBaseDao.TABLE_TWO);
		mList = mDao.getList();

		mAdapter = new BaseItemAdapter(getActivity(),
				R.layout.item_list_base_android, mList);
		lv.setAdapter(mAdapter);
		setListViewItemListner();
	}

	private void setListViewItemListner() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ItemActivity.startItemActivity(getActivity(), ANDROID_BASE_TITLE,
						ItemActivity.ANDROID_BASE, mList.get(position).getId());
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.fragment_item_viewpager_android_base,
				container, false);
		return view;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mDao.close();
		super.onDestroy();
	}

}
