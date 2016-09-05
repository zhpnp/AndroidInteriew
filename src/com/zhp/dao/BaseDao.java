package com.zhp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.databasehelper.CreateNewTable;

public class BaseDao {
	public static final int INDEX_BEFORE_FIRST = -1;
	
	private SQLiteDatabase mDb;
	private CreateNewTable mTable;
	private Context mContext;

	// private List<Note> mList;

	public BaseDao(Context context) {
		this.mContext = context;
		mTable = CreateNewTable.getCreateNewTable(context);
		mDb = mTable.getReadableDatabase();
	}

	public SQLiteDatabase getSQLiteDatabase() {
		return mDb;
	}

	public Context getmContext() {
		return mContext;
	}

	public void close() {
		mDb.close();
	}
}
