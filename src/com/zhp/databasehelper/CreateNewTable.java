package com.zhp.databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateNewTable extends SQLiteOpenHelper {

	private static final String TABLE_NOTE = "create table note(id integer primary key autoincrement,"
			+ "content varchar(80),last date);";
	
	private static final String TABLE_INTERIEW = "create table interiew(id integer primary key autoincrement,"
			+ "company varchar(30),addtime date,interiewdate date,job varchar(20),remark varchar(80),type integer);";
	
//	private static final String TABLE_COLLECTION = "create table collection(id integer primary key autoincrement,"
//			+ "type integer(2),itemid integer(3),iscollection boolean);";

	private static CreateNewTable mCreateNewTable;

	private CreateNewTable(Context context) {
		super(context, "interiewsnote.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	public static CreateNewTable getCreateNewTable(Context context) {
		if (mCreateNewTable != null) {
			return mCreateNewTable;
		}
		mCreateNewTable = new CreateNewTable(context);
		return mCreateNewTable;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(TABLE_NOTE);
		db.execSQL(TABLE_INTERIEW);
//		db.execSQL(TABLE_COLLECTION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
