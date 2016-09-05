package com.zhp.utils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityCollection.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		ActivityCollection.removeActivity(this);
		super.onDestroy();
	}
	
	public void back(View v){
		finish();
	}
}
