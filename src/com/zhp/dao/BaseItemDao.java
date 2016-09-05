package com.zhp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.bean.BaseItem;
import com.zhp.d11_androidinteriewskill.ItemActivity;

public class BaseItemDao extends RawSourceBaseDao {
	public static final String TABLE_BASE_TCOLUMN_ID = "itemid";
	public static final String TABLE_BASE_COLUMN_ITEM = "item";
	public static final String TABLE_BASE_COLUMN_ANSWER = "answer";
	public static final String TABLE_BASE_COLUMN_ISCOLLECTION = "iscollection";

	private String table;
	private SQLiteDatabase mDb;

	public BaseItemDao(Context context, String table) {
		super(context);
		this.table = table;
		mDb = super.getmSqLiteDatabase();
	}

	public BaseItem query(String[] selectionAgrs) {
		Cursor cursor = mDb.query(table, null, "itemid=?", selectionAgrs, null,
				null, null);
		BaseItem item = null;
		if (cursor != null && cursor.getCount() != 0) {
			cursor.moveToFirst();
			int id = cursor
					.getInt(cursor.getColumnIndex(TABLE_BASE_TCOLUMN_ID));
			String question = cursor.getString(cursor
					.getColumnIndex(TABLE_BASE_COLUMN_ITEM));
			String answer = cursor.getString(cursor
					.getColumnIndex(TABLE_BASE_COLUMN_ANSWER));
			int collectionType = cursor.getInt(cursor
					.getColumnIndex(TABLE_BASE_COLUMN_ISCOLLECTION));
			item = new BaseItem(id, question, answer, collectionType);
		}
		return item;
	}

	public List<BaseItem> getListOfColletion(String table) {
		List<BaseItem> list = new ArrayList<BaseItem>();
		Cursor cursor = queryAll(table);
		addCollectionToList(list, cursor);
		return list;
	}

	private void addCollectionToList(List<BaseItem> list, Cursor cursor) {
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToPosition(INDEX_BEFORE_FIRST);
			while (cursor.moveToNext()) {
				int isCollection = cursor.getInt(cursor
						.getColumnIndex(TABLE_BASE_COLUMN_ISCOLLECTION));
				if (isCollection == ItemActivity.COLLECTION_TRUE) {
					int id = cursor.getInt(cursor
							.getColumnIndex(TABLE_BASE_TCOLUMN_ID));
					String question = cursor.getString(cursor
							.getColumnIndex(TABLE_BASE_COLUMN_ITEM));
					String answer = cursor.getString(cursor
							.getColumnIndex(TABLE_BASE_COLUMN_ANSWER));
					int collectionType = cursor.getInt(cursor
							.getColumnIndex(TABLE_BASE_COLUMN_ISCOLLECTION));
					list.add(new BaseItem(id, question, answer, collectionType));
				}

			}
		}
	}

	public void updateBaseItem(int isCollection, String[] whereArgs) {
		ContentValues values = new ContentValues();
		values.put(TABLE_BASE_COLUMN_ISCOLLECTION, isCollection);
		mDb.update(table, values, "itemid=?", whereArgs);
	}

	public List<BaseItem> getList() {
		List<BaseItem> list = new ArrayList<BaseItem>();
		Cursor cursor = queryAll(table);
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToPosition(INDEX_BEFORE_FIRST);
			while (cursor.moveToNext()) {
				int id = cursor.getInt(cursor
						.getColumnIndex(TABLE_BASE_TCOLUMN_ID));
				String question = cursor.getString(cursor
						.getColumnIndex(TABLE_BASE_COLUMN_ITEM));
				String answer = cursor.getString(cursor
						.getColumnIndex(TABLE_BASE_COLUMN_ANSWER));
				int collectionType = cursor.getInt(cursor
						.getColumnIndex(TABLE_BASE_COLUMN_ISCOLLECTION));
				list.add(new BaseItem(id, question, answer, collectionType));
			}

		}
		return list;
	}

}
