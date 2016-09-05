package com.zhp.d11_androidinteriewskill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.zhp.utils.BaseActivity;

public class WebActivity extends BaseActivity {
	public static final String KEY_URL = "url";

	private ProgressBar pb;
	private WebView wv;

	private String url;

	public static void startWebActivity(Context context, String url) {
		Intent it = new Intent(context, WebActivity.class);
		it.putExtra(KEY_URL, url);
		context.startActivity(it);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		init();
	}

	private void init() {
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		pb = (ProgressBar) findViewById(R.id.pb_web);
		pb.setDrawingCacheBackgroundColor(0xff4169E1);
		wv = (WebView) findViewById(R.id.wv);
		setWebView();
	}

	private void setWebView() {
		// TODO Auto-generated method stub
		url = getIntent().getStringExtra(KEY_URL);
		wv.getSettings().setJavaScriptEnabled(true);
		wv.getSettings().setUseWideViewPort(true);
		wv.getSettings().setLoadWithOverviewMode(true);
		wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		wv.getSettings().setDomStorageEnabled(true);
		
		wv.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub

				view.loadUrl(url);
				return true;
			}

		});

		wv.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				// TODO Auto-generated method stub
				pb.setProgress(newProgress);
				if (pb.getProgress() == pb.getMax()) {
					pb.setVisibility(View.GONE);
				}
			}

		});
		wv.loadUrl(url);

	}

}
