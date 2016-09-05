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
 * �ʼǵı༭�ҳ��
 * @author zhp
 *
 */
public class NoteEditActivity extends BaseActivity implements OnClickListener {
	public static final String NOTE_CONTENT = "content";

	private ImageButton ibtn_back, ibtn_ok;
	private EditText et;
	private String content;//���ڼ�¼�ʼ�����

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
		//����EditText�����ݣ�����ת��Activity��ȡ
		content = getIntent().getStringExtra(NOTE_CONTENT);
		et.setText(content);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibtn_note_back:
			//����û��޸��˱ʼ����ݺ�ֱ���˳�������ʾ�û����ᱣ���µ�����
			if (et.length() > 0 && !et.getText().toString().equals(content)) {
				createDialog();
			}else{
				finish();
			}
			break;
		case R.id.ibtn_note_ok:
			if(et.length() == 0){
				Toast.makeText(this, "û�����ݣ����ܱ���", Toast.LENGTH_LONG).show();
			}else{
				String text = et.getText().toString();
				Intent it = new Intent();
				it.putExtra(NOTE_CONTENT, text);
				setResult(RESULT_OK, it);//�ش�ֵ
				Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;

		}

	}
	
	/**
	 * ������ʾ��ʾ�û����������ݵĶԻ���
	 */
	public void createDialog(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("��ʾ");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setMessage("ֱ�ӷ��ؽ����ᱣ���޸����ݻ����½��ʼǣ�ȷ��Ҫֱ�ӷ�����");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		builder.setNegativeButton("ȡ��", null);
		builder.show();
	}

}
