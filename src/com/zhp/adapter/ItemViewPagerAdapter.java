package com.zhp.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * 试题碎片页面ViewPager的适配器
 * @author zhp
 *
 */
public class ItemViewPagerAdapter extends FragmentStatePagerAdapter {
	
	private List<Fragment> mList;
	private String[] items = {"Java基础","Android基础","Android进阶"};
	
	public ItemViewPagerAdapter(FragmentManager fm,List<Fragment> list) {
		super(fm);
		this.mList = list;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return items[position];
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
