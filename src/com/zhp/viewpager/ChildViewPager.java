package com.zhp.viewpager;

import android.content.Context;
//import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ChildViewPager extends ParentViewPager {

	private float lastX;// 记录上一次的x坐标值

	private boolean slidingLeft;
	private boolean slidingRight;

	public ChildViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ChildViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = event.getX();
			this.getParent().requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:

			if (this.getCurrentItem() == 0) {
				if (lastX < event.getX() && !slidingRight) {
					this.getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					slidingRight = true;
					this.getParent().requestDisallowInterceptTouchEvent(true);
					lastX = event.getX();
				}
			} else if (this.getCurrentItem() == this.getAdapter().getCount() - 1) {
				if (lastX > event.getX() && !slidingLeft) {
					this.getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					slidingLeft = true;
					this.getParent().requestDisallowInterceptTouchEvent(true);
					lastX = event.getX();
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			this.getParent().requestDisallowInterceptTouchEvent(false);
			this.lastX = event.getX();

			slidingLeft = false;
			slidingRight = false;

			break;
		}

		super.onTouchEvent(event);
		return true;
	}

}
