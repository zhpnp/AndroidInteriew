package com.zhp.d11_androidinteriewskill;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.zhp.utils.BaseActivity;

/**
 * …¡∆¡ΩÁ√Ê
 * @author zhp
 *
 */
public class FlashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        init();
    }

    public void init(){
    	new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent it = new Intent(FlashActivity.this,MainActivity.class);
				startActivity(it);
				finish();
			}
		}, 2000);
    }

    
}
