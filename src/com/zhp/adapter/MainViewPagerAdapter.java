package com.zhp.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 自定义的ViewPager适配器，用于主界面
 * @author zhp
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> mList;
	
	public MainViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.mList = list;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return mList.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

}
