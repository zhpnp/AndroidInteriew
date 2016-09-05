package com.zhp.fragment;

import java.io.File;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.zhp.d11_androidinteriewskill.Collectionctivity;
import com.zhp.d11_androidinteriewskill.ContactActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.utils.ActivityCollection;

public class FragmentMenu extends Fragment implements OnClickListener {
	private View view;
	private Button btn_setting, btn_collection, btn_about, btn_exit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_menu, null);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_setting = (Button) view
				.findViewById(R.id.btn_drawlayout_clearcache);
		btn_collection = (Button) view
				.findViewById(R.id.btn_drawlayout_mycollection);
		btn_about = (Button) view.findViewById(R.id.btn_drawlayout_about);
		btn_exit = (Button) view.findViewById(R.id.btn_drawlayout_exit);
		btn_about.setOnClickListener(this);
		btn_collection.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_setting.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_drawlayout_clearcache:
			File file = getActivity().getCacheDir();
			clearWebViewCache(file);
			Toast.makeText(getActivity(), "Çå³ý³É¹¦", Toast.LENGTH_SHORT).show();
			break;
		case R.id.btn_drawlayout_mycollection:
			Collectionctivity.startCollectionActivity(getActivity());
			break;
		case R.id.btn_drawlayout_about:
			ContactActivity.startContactActivity(getActivity());
			break;
		case R.id.btn_drawlayout_exit:
			ActivityCollection.finishAll();
			getActivity().finish();
			break;
		}
	}

	private void clearWebViewCache(File file) {
		if (file.exists() && file.isDirectory()) {
			for (File dir : file.listFiles()) {
				if (dir.isDirectory()) {
					clearWebViewCache(dir);
				} else {
					dir.delete();
				}
			}
		} else {
			file.delete();
		}
	}
}
