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

public class FragmentJavaBase extends Fragment {

	public static final String JAVA_BASE_TITLE = "Java»ù´¡";

	private View view;

	private ListView lv;
	private BaseItemAdapter mAdapter;

	private BaseItemDao mDao;
	private List<BaseItem> mList;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv = (ListView) view.findViewById(R.id.lv_item_viewpager_java);
		mDao = new BaseItemDao(getActivity(),RawSourceBaseDao.TABLE_ONE);
		mList = mDao.getList();
		mAdapter = new BaseItemAdapter(getActivity(),
				R.layout.item_list_base_java, mList);
		lv.setAdapter(mAdapter);

		setListViewClickListener();
	}

	private void setListViewClickListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ItemActivity.startItemActivity(getActivity(), JAVA_BASE_TITLE,
						ItemActivity.JAVA_BASE, mList.get(position).getId());
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.fragment_item_viewpager_java,
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
