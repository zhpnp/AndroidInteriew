package com.zhp.popuewindow;

import android.app.Activity;
import android.content.Context;
//import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.zhp.d11_androidinteriewskill.R;

public class DeletePopueWindow extends PopupWindow {
	private View view;
	private LayoutInflater inflater;

	public DeletePopueWindow(Context context, final Callback callback) {
		super(context);
		// TODO Auto-generated constructor stub
		inflater = LayoutInflater.from(context);
		view = inflater.inflate(R.layout.delete_popuewindow, null);
		Button btn = (Button) view.findViewById(R.id.btn_note_delete);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				callback.delete();
				dismiss();
			}
		});
		init((Activity) context);
	}

	public void init(Activity context) {
		setContentView(view);
		setWidth(LayoutParams.WRAP_CONTENT);
		setHeight(LayoutParams.WRAP_CONTENT);

		setFocusable(true);
		setTouchable(true);

		update();
		setAnimationStyle(R.style.note_delete);//…Ë÷√∂Øª≠

	}

	public void showPopueWidow(View parent) {
		if (this.isShowing()) {
			this.dismiss();
		} else {
			this.showAsDropDown(parent);
		}

	}

	public interface Callback {
		void delete();
	}

}
