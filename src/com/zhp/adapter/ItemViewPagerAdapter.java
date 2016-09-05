package com.zhp.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * ������Ƭҳ��ViewPager��������
 * @author zhp
 *
 */
public class ItemViewPagerAdapter extends FragmentStatePagerAdapter {
	
	private List<Fragment> mList;
	private String[] items = {"Java����","Android����","Android����"};
	
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
