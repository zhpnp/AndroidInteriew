package com.zhp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.bean.AndroidAdvance;

/**
 * android���ײ��ֵ����ݿ�����࣬�̳�RawSourceBaseDao
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
	 * @param selectionArgs ��ѯ��������id����ֵ
	 * @return ���ط�װ���������ݵĶ���
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
	 * ��ѯ���У�����ÿ�����ݷ�װ�������У�����ʹ�ü��ϱ�������
	 * @return �����з�װ�����ݵĶ��󼯺�
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
