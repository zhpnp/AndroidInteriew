package com.zhp.d11_androidinteriewskill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.zhp.utils.BaseActivity;

/**
 * 笔记的编辑活动页面
 * @author zhp
 *
 */
public class NoteEditActivity extends BaseActivity implements OnClickListener {
	public static final String NOTE_CONTENT = "content";

	private ImageButton ibtn_back, ibtn_ok;
	private EditText et;
	private String content;//用于记录笔记内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note_edit);
		init();
	}

	public void init() {
		ibtn_back = (ImageButton) findViewById(R.id.ibtn_note_back);
		ibtn_ok = (ImageButton) findViewById(R.id.ibtn_note_ok);
		ibtn_back.setOnClickListener(this);
		ibtn_ok.setOnClickListener(this);

		et = (EditText) findViewById(R.id.et_note_edit);
		//设置EditText的内容，从跳转的Activity获取
		content = getIntent().getStringExtra(NOTE_CONTENT);
		et.setText(content);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibtn_note_back:
			//如果用户修改了笔记内容后直接退出，则提示用户不会保存新的内容
			if (et.length() > 0 && !et.getText().toString().equals(content)) {
				createDialog();
			}else{
				finish();
			}
			break;
		case R.id.ibtn_note_ok:
			if(et.length() == 0){
				Toast.makeText(this, "没有内容，不能保存", Toast.LENGTH_LONG).show();
			}else{
				String text = et.getText().toString();
				Intent it = new Intent();
				it.putExtra(NOTE_CONTENT, text);
				setResult(RESULT_OK, it);//回传值
				Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		}

	}
	
	/**
	 * 用于显示提示用户不保存内容的对话框
	 */
	public void createDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage("直接返回将不会保存修改内容或者新建笔记，确定要直接返回吗？");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		builder.setNegativeButton("取消", null);
		builder.show();
	}

}
