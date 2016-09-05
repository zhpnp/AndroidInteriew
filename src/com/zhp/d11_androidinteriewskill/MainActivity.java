package com.zhp.d11_androidinteriewskill;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.zhp.adapter.MainViewPagerAdapter;
import com.zhp.dao.BaseDao;
import com.zhp.fragment.FragmentAbout;
import com.zhp.fragment.FragmentInteriew;
import com.zhp.fragment.FragmentItem;
import com.zhp.fragment.FragmentMenu;
import com.zhp.fragment.FragmentNote;
import com.zhp.viewpager.ParentViewPager;

/**
 * 主活动界面
 * 
 * @author zhp
 * 
 */
public class MainActivity extends SlidingFragmentActivity implements
		OnClickListener {

	// 声明一些控件
	private ParentViewPager pvp;
	private ImageButton ibtn_item, ibtn_note, ibtn_interiew, ibtn_about;
	private TextView tv_item, tv_note, tv_interiew, tv_about;

	// 声明控件数组
	private ImageButton[] ibtns = { ibtn_item, ibtn_note, ibtn_interiew,
			ibtn_about };
	private TextView[] tvs = { tv_item, tv_note, tv_interiew, tv_about };
	private int[] ibtnIds = { R.id.ibtn_item, R.id.ibtn_note,
			R.id.ibtn_interiew, R.id.ibtn_about };
	private int[] tvIds = { R.id.tv_item, R.id.tv_note, R.id.tv_interiew,
			R.id.tv_about };

	// 声明碎片即ViewPager的适配器
	private MainViewPagerAdapter mAdapter;
	private FragmentNote mFragmentNote;
	private FragmentInteriew mFragmentInteriew;
	private FragmentAbout mFragmentAbout;
	private FragmentItem mFragmentItem;
	private FragmentManager mFt;
	private List<Fragment> mList;

	// 声明侧滑菜单的对象
	private SlidingMenu mMenu;

	private BaseDao mDao;// 声明数据库操作对象

	private int currentPager;// 记录当前页面

	// private int historyPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.fragment_menu);
		init(savedInstanceState);
	}

	/**
	 * 初始化方法
	 * @param savedInstanceState 
	 */
	public void init(Bundle savedInstanceState) {
		initFragment();
		initView();
		initMenu(savedInstanceState);
		mDao = new BaseDao(this);
	}

	/**
	 * 初始化菜单，并进行设置
	 * @param savedInstanceState 
	 */
	private void initMenu(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mMenu = getSlidingMenu();
		
		if(savedInstanceState == null){
			FragmentMenu fragmentMenu = new FragmentMenu();
			getSupportFragmentManager().beginTransaction().replace(R.id.frame_menu, fragmentMenu).commit();
		}

		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setShadowDrawable(R.drawable.shadow);
		mMenu.setShadowWidthRes(R.dimen.shadow_width);
		mMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		mMenu.setBehindScrollScale(0f);
		mMenu.setFadeDegree(0f);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

	}

	/**
	 * 初始化控件及其状态
	 */
	public void initView() {
		pvp = (ParentViewPager) findViewById(R.id.vp_main);
		for (int i = 0; i < ibtns.length; i++) {
			ibtns[i] = (ImageButton) findViewById(ibtnIds[i]);
			ibtns[i].setOnClickListener(this);
		}

		for (int i = 0; i < tvs.length; i++) {
			tvs[i] = (TextView) findViewById(tvIds[i]);
		}

		mFt = getSupportFragmentManager();
		mAdapter = new MainViewPagerAdapter(mFt, mList);

		pvp.setAdapter(mAdapter);
		setViewPagerListener();
		currentPager = 0;
		pvp.setCurrentItem(currentPager);
		ibtns[0].setImageResource(R.drawable.item_pressed);
		tvs[currentPager].setTextColor(0xff4169E1);
	}

	/**
	 * 重置按钮状态为未点击状态
	 */
	private void resetButtonStateToNormal() {
		ibtns[0].setImageResource(R.drawable.item_normal);
		ibtns[1].setImageResource(R.drawable.note_normal);
		ibtns[2].setImageResource(R.drawable.interiew_normal);
		ibtns[3].setImageResource(R.drawable.about_normal);
		tvs[0].setTextColor(0x99999999);
		tvs[1].setTextColor(0x99999999);
		tvs[2].setTextColor(0x99999999);
		tvs[3].setTextColor(0x99999999);
	}

	/**
	 * 初始化碎片，并添加到list中
	 */
	public void initFragment() {
		mList = new ArrayList<Fragment>();
		mFragmentItem = new FragmentItem();
		mFragmentNote = new FragmentNote();
		mFragmentInteriew = new FragmentInteriew();
		mFragmentAbout = new FragmentAbout();

		mList.add(mFragmentItem);
		mList.add(mFragmentNote);
		mList.add(mFragmentInteriew);
		mList.add(mFragmentAbout);
	}

	/**
	 * 设置外层ViewPager的监听
	 */
	private void setViewPagerListener() {
		// TODO Auto-generated method stub
		pvp.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				resetButtonStateToNormal();
				currentPager = position;// 记录当前页面
				mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);// 设置向右滑不会弹出菜单，解决与ViewPager滑动冲突
				switch (position) {
				case 0:
					ibtns[0].setImageResource(R.drawable.item_pressed);
					// mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				case 1:
					ibtns[1].setImageResource(R.drawable.note_pressed);
					break;
				case 2:
					ibtns[2].setImageResource(R.drawable.interiew_pressed);
					break;
				case 3:
					ibtns[3].setImageResource(R.drawable.about_pressed);
					break;
				}
				tvs[currentPager].setTextColor(0xff4169E1);

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});
	}

	/**
	 * 按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		resetButtonStateToNormal();// 点击时重置按钮状态
		switch (v.getId()) {
		case R.id.ibtn_item:
			currentPager = 0;
			ibtns[0].setImageResource(R.drawable.item_pressed);
			break;
		case R.id.ibtn_note:
			currentPager = 1;
			ibtns[1].setImageResource(R.drawable.note_pressed);
			break;
		case R.id.ibtn_interiew:
			currentPager = 2;
			ibtns[2].setImageResource(R.drawable.interiew_pressed);
			break;
		case R.id.ibtn_about:
			currentPager = 3;
			ibtns[3].setImageResource(R.drawable.about_pressed);
			break;
		}
		tvs[currentPager].setTextColor(0xff4169E1);// 设置和按钮一起的文字的颜色
		pvp.setCurrentItem(currentPager);// 切换ViewPager页面
	}

	public SlidingMenu getMenu() {
		return mMenu;
	}

	public void menu(View v) {
		mMenu.toggle();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mDao.close();// 关闭数据库
		super.onDestroy();
	}

}
