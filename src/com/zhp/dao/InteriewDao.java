package com.zhp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.bean.Interiew;

public class InteriewDao extends BaseDao {
	public static final String TABLE_INTERIEW = "interiew";
	
	public static final String INTERIEW_COLUMN_ID = "id";
	public static final String INTERIEW_COLUMN_COMPANY = "company";
	public static final String INTERIEW_COLUMN_ADDDATE = "addtime";
	public static final String INTERIEW_COLUMN_INTERIEWDATE = "interiewdate";
	public static final String INTERIEW_COLUMN_JOB = "job";
	public static final String INTERIEW_COLUMN_REMARK = "remark";
	public static final String INTERIEW_COLUMN_TYPE = "type";
	
	private SQLiteDatabase mDb;
	
	public InteriewDao(Context context) {
		super(context);
		mDb = getSQLiteDatabase();
	}

	public Cursor queryAll(){
		Cursor cursor = mDb.query(TABLE_INTERIEW, null, null, null, null, null, null);
		return cursor;
	}
	
	public Interiew qurey(String[] selectionArgs){
		Cursor cursor = mDb.query(TABLE_INTERIEW, null, "id=?", selectionArgs, null, null, null);
		cursor.moveToFirst();
		String id = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_ID));
		String company = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_COMPANY));
		String date = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_ADDDATE));
		String interiewDate = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_INTERIEWDATE));
		String job = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_JOB));
		String remark = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_REMARK));
		int type = cursor.getInt(cursor.getColumnIndex(INTERIEW_COLUMN_TYPE));
		cursor.close();
		return new Interiew(id, company, date, interiewDate, job, remark, type);
	}
	
	public long insertInteriew(Interiew interiew){
		ContentValues values = getValues(interiew);
		long row = mDb.insert(TABLE_INTERIEW, null, values);
		return row;
	}
	
	public int deleteNote(String[] whereArgs){
		int rows = mDb.delete(TABLE_INTERIEW, "id=?", whereArgs);
		return rows;
	}

	public int updateInteriew(Interiew interiew,String [] whereArgs){
		ContentValues values = getValues(interiew);
		int rows = mDb.update(TABLE_INTERIEW, values, "id=?", whereArgs);
		return rows;
	}
	
	private ContentValues getValues(Interiew interiew) {
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
		contentValues.put(INTERIEW_COLUMN_COMPANY, interiew.getCompany());
		contentValues.put(INTERIEW_COLUMN_ADDDATE, interiew.getDate());
		contentValues.put(INTERIEW_COLUMN_INTERIEWDATE, interiew.getInteriewDate());
		contentValues.put(INTERIEW_COLUMN_JOB, interiew.getJob());
		contentValues.put(INTERIEW_COLUMN_TYPE, interiew.getType());
		contentValues.put(INTERIEW_COLUMN_REMARK, interiew.getRemark());
		return contentValues;
	}
	
	public int getCount(){
		Cursor cursor = queryAll();
		return cursor.getCount();
	}
	
	public List<Interiew> getInteriew(){
		Cursor cursor = queryAll();
		List<Interiew> list = new ArrayList<Interiew>();
		cursor.moveToPosition(INDEX_BEFORE_FIRST);
		while(cursor.moveToNext()){
			String id = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_ID));
			String company = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_COMPANY));
			String date = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_ADDDATE));
			String interiewDate = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_INTERIEWDATE));
			String job = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_JOB));
			String remark = cursor.getString(cursor.getColumnIndex(INTERIEW_COLUMN_REMARK));
			int type = cursor.getInt(cursor.getColumnIndex(INTERIEW_COLUMN_TYPE));
			list.add(new Interiew(id, company, date, interiewDate, job, remark, type));
		}
		cursor.close();
		return list;
	}
}
