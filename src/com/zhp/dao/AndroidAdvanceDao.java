package com.zhp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.bean.AndroidAdvance;

/**
 * android进阶部分的数据库操作类，继承RawSourceBaseDao
 * @author zhp
 *
 */
public class AndroidAdvanceDao extends RawSourceBaseDao {
	public static final String TABLE_THREE_COLUMN_ID = "id";
	public static final String TABLE_THREE_COLUMN_TITLE = "title";
	public static final String TABLE_THREE_COLUMN_FROM = "from";
	public static final String TABLE_THREE_COLUMN_URL = "url";

	private SQLiteDatabase mDb;
	private String table;

	public AndroidAdvanceDao(Context context) {
		super(context);
		mDb = super.getmSqLiteDatabase();
		table = TABLE_THREE;
	}
	
	/**
	 * 
	 * @param selectionArgs 查询的条件（id）的值
	 * @return 返回封装了所需数据的对象
	 */
	public AndroidAdvance query(String[] selectionArgs) {
		Cursor cursor = mDb.query(table, null, "id=?", selectionArgs, null,
				null, null);
		AndroidAdvance advance = null;
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			int id = cursor
					.getInt(cursor.getColumnIndex(TABLE_THREE_COLUMN_ID));
			String title = cursor.getString(cursor
					.getColumnIndex(TABLE_THREE_COLUMN_TITLE));
			String from = cursor.getString(cursor
					.getColumnIndex(TABLE_THREE_COLUMN_FROM));
			String url = cursor.getString(cursor
					.getColumnIndex(TABLE_THREE_COLUMN_URL));

			advance = new AndroidAdvance(id, url, title, from);
		}

		return advance;
	}
	
	/**
	 * 查询所有，并将每条数据封装到对象中，最终使用集合保存数据
	 * @return 保存有封装了数据的对象集合
	 */
	public List<AndroidAdvance> getList(){
		List<AndroidAdvance> list = new ArrayList<AndroidAdvance>();
		Cursor cursor = queryAll(table);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToPosition(-1);
			while(cursor.moveToNext()){
				int id = cursor
						.getInt(cursor.getColumnIndex(TABLE_THREE_COLUMN_ID));
				String title = cursor.getString(cursor
						.getColumnIndex(TABLE_THREE_COLUMN_TITLE));
				String from = cursor.getString(cursor
						.getColumnIndex(TABLE_THREE_COLUMN_FROM));
				String url = cursor.getString(cursor
						.getColumnIndex(TABLE_THREE_COLUMN_URL));
				
				list.add(new AndroidAdvance(id, url, title, from));
			}
		}
		
		return list;
	}

}
