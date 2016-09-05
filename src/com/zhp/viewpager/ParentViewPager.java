package com.zhp.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

public class ParentViewPager extends ViewPager {

	public ParentViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ParentViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 重写该方法，用于解决ViewPager嵌套造成的内层ViewPager不能滑屏的问题
	 */
	@Override
	protected boolean canScroll(View v, boolean b, int dx, int x, int y) {
		// TODO Auto-generated method stub
		if (v != this && v instanceof ViewPager) {//判断是否为内层ViewPager
			ViewPager vp = (ViewPager) v;
			int currentItem = vp.getCurrentItem();
			int countItem = vp.getAdapter().getCount();
			if (currentItem == (countItem - 1) && dx < 0 || currentItem == 0
					&& dx > 0) {//判断内层ViewPager是否滑动到了第一页或者最后一页，并且用户想滑动到外层的ViewPager

				return false;
			}
			return true;
		}

		return super.canScroll(v, b, dx, x, y);
	}

}
