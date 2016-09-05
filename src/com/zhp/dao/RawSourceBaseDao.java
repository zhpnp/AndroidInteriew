package com.zhp.dao;

import com.zhp.databasehelper.OpenRawDatabase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 操作raw文件夹下的数据库文件的类
 * @author zhp
 *
 */
public class RawSourceBaseDao {
	public static final int INDEX_BEFORE_FIRST = -1;
	
	public static final String TABLE_ONE = "java_base";
	public static final String TABLE_TWO = "android_base";
	public static final String TABLE_THREE = "android_advance";

	private Context context;
	private SQLiteDatabase mSqLiteDatabase;

	public RawSourceBaseDao(Context context) {
		super();
		this.context = context;
		mSqLiteDatabase = OpenRawDatabase.getOpenRawDatabase(context).getDb();
	}
	
	public Context getContext() {
		return context;
	}

	public SQLiteDatabase getmSqLiteDatabase() {
		return mSqLiteDatabase;
	}
	
	public Cursor queryAll(String table){
		Cursor cursor = mSqLiteDatabase.query(table, null, null, null, null, null, null);
		return cursor;
	}
	
	public void close(){
		
		mSqLiteDatabase.close();
	}

}
