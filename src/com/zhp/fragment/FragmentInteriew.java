package com.zhp.fragment;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.zhp.adapter.InteriewAdapter;
import com.zhp.bean.Interiew;
import com.zhp.d11_androidinteriewskill.AddInteriewActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.dao.InteriewDao;
import com.zhp.popuewindow.DeletePopueWindow;
import com.zhp.service.AlarmInteriewService;

public class FragmentInteriew extends Fragment implements OnClickListener {
	public static final int REQUEST_CODE_ADD = 3;
	public static final int REQUEST_CODE_UPDATE = 4;

	private View view;
	private ImageButton ibtn_add;
	private TextView tv;

	private ListView lv;
	private InteriewAdapter mAdapter;
	private List<Interiew> mList;
	private InteriewDao mDao;
	
	private int which;//记录选中的面试的位置

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		init();
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_interiew, null);
		return view;
	}

	public void init() {
		ibtn_add = (ImageButton) view.findViewById(R.id.ibtn_interiew_add);
		ibtn_add.setOnClickListener(this);
		tv = (TextView) view.findViewById(R.id.tv_no_interiew);
		lv = (ListView) view.findViewById(R.id.lv_interiew);

		mDao = new InteriewDao(getActivity());
		mList = mDao.getInteriew();

		if (mList.size() > 0) {
			mAdapter = new InteriewAdapter(getActivity(),
					R.layout.interiew_list_item, mList);
			lv.setAdapter(mAdapter);
		} else {
			lv.setVisibility(View.GONE);
			tv.setVisibility(View.VISIBLE);
		}

		listViewItemListener();
	}

	private void listViewItemListener() {
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getActivity(), AddInteriewActivity.class);
				it.putExtra(AddInteriewActivity.INTERIEW_CONTENT,
						mList.get(position));
				which = position;
//				System.out.println("job--->" + mList.get(position).getJob());
				startActivityForResult(it, REQUEST_CODE_UPDATE);
			}
		});

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				DeletePopueWindow window = new DeletePopueWindow(getActivity(),
						new DeletePopueWindow.Callback() {
							@Override
							public void delete() {
								// TODO Auto-generated method stub
								Interiew interiew = mList.remove(position);
								mAdapter.notifyDataSetChanged();
								mDao.deleteNote(new String[] { interiew.getId() });
								if(mList.size()==0){
									lv.setVisibility(View.GONE);
									tv.setVisibility(View.VISIBLE);
								}
								Intent service = new Intent(getActivity(),AlarmInteriewService.class);
								service.putExtra(AddInteriewActivity.INTERIEW_CONTENT, interiew);
								service.putExtra(AlarmInteriewService.CANCEL, true);
								getActivity().startService(service);
							}
						});
				window.showPopueWidow(view);

				return true;
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it = new Intent(getActivity(), AddInteriewActivity.class);
		startActivityForResult(it, REQUEST_CODE_ADD);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK) {
			Interiew interiew = (Interiew) data
					.getSerializableExtra(AddInteriewActivity.INTERIEW_CONTENT);
//			System.out.println(interiew.getJob());
			if(requestCode == REQUEST_CODE_ADD){
				if(mList.size() == 0){
					mAdapter = new InteriewAdapter(getActivity(), R.layout.interiew_list_item, mList);
					lv.setAdapter(mAdapter);
					lv.setVisibility(View.VISIBLE);
					tv.setVisibility(View.GONE);
				}
				mList.add(interiew);
				mAdapter.notifyDataSetChanged();
				mDao.insertInteriew(interiew);
			}
			if(requestCode == REQUEST_CODE_UPDATE){
				String id = mList.remove(which).getId();
				mList.add(which, interiew);
				mAdapter.notifyDataSetChanged();
				mDao.updateInteriew(interiew, new String[]{id});
			}
		}

	}

}
