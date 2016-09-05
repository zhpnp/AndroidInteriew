package com.zhp.databasehelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.d11_androidinteriewskill.R;

public class OpenRawDatabase {
	public static final int SIZE = 1024;

	private Context context;
	private String path;
	private final String FILE_NAME = "interiewskill.db";
	private File file;
	private int version = 1;

	private static OpenRawDatabase mOpenRawDatabase;//µ¥ÀýÄ£Ê½

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public static OpenRawDatabase getOpenRawDatabase(Context context) {
		if (mOpenRawDatabase == null) {
			mOpenRawDatabase = new OpenRawDatabase(context);
			return mOpenRawDatabase;
		}
		return mOpenRawDatabase;
	}

	private OpenRawDatabase(Context context) {
		super();
		this.context = context;
		path = context.getFilesDir().getPath() + "/" + FILE_NAME;
	}

	public SQLiteDatabase getDb() {
		SQLiteDatabase db = null;
		file = new File(path);
		if (file.exists()) {
			db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
			if (db.getVersion() > version) {
				getDatabaseFromRes();
			}
		} else {
			getDatabaseFromRes();
			db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
		}
		return db;
	}

	private void getDatabaseFromRes() {
		FileOutputStream fos = null;
		InputStream in = null;
		try {
			in = context.getResources().openRawResource(R.raw.interiewskill);
			fos = new FileOutputStream(file);
			byte[] buf = new byte[SIZE];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
				fos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
