package com.zhp.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.zhp.adapter.ItemViewPagerAdapter;
import com.zhp.d11_androidinteriewskill.MainActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.dao.RawSourceBaseDao;
import com.zhp.viewpager.ChildViewPager;

public class FragmentItem extends Fragment {

	private View view;

	private ChildViewPager cvp;
	private ItemViewPagerAdapter mAdapter;
	private FragmentManager mFm;
	private List<Fragment> mList;

	private FragmentJavaBase mJavaBase;
	private FragmentAndroidBase mAndroidBase;
	private FragmentAndroidAdvance mAndroidAdvance;

	private PagerTabStrip mTabStrip;

	private RawSourceBaseDao mDao;

	public FragmentItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		view = inflater.inflate(R.layout.fragment_item, null);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		initFragment();
		initView();
		mDao = new RawSourceBaseDao(getActivity());
	}

	private void initFragment() {
		mList = new ArrayList<Fragment>();
		mJavaBase = new FragmentJavaBase();
		mAndroidBase = new FragmentAndroidBase();
		mAndroidAdvance = new FragmentAndroidAdvance();

		mList.add(mJavaBase);
		mList.add(mAndroidBase);
		mList.add(mAndroidAdvance);
	}

	private void initView() {
		cvp = (ChildViewPager) view.findViewById(R.id.vp_item);

		mFm = this.getChildFragmentManager();
		mAdapter = new ItemViewPagerAdapter(mFm, mList);

		cvp.setAdapter(mAdapter);

		initPagerTabStrip();

		setViewPagerSlidingListener();
	}

	private void setViewPagerSlidingListener() {
		// TODO Auto-generated method stub
		cvp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				MainActivity activity = (MainActivity) getActivity();
				if (position == 0) {
					// 当为第一个页面时，允许右滑弹出菜单
					activity.getMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else {
					activity.getMenu().setTouchModeAbove(
							SlidingMenu.TOUCHMODE_NONE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initPagerTabStrip() {
		// TODO Auto-generated method stub
		mTabStrip = (PagerTabStrip) view.findViewById(R.id.pts_item);

		mTabStrip.setDrawFullUnderline(false);
		mTabStrip.setTabIndicatorColor(0xff4169E1);
		mTabStrip.setTextColor(0xff4169E1);
		mTabStrip.setTextSpacing(3);

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		mDao.close();
		super.onDestroy();
	}

}
