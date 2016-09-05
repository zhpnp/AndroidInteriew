package com.zhp.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zhp.bean.Note;

public class NoteDao extends BaseDao {
	public static final String TABLE_NOTE = "note";
	public static final String NOTE_COLUNM_CONTENT = "content";
	public static final String NOTE_COLUMN_DATE = "last";
	public static final String NOTE_COLUMN_ID = "id";
	
	
	private SQLiteDatabase mDb;
//	private List<Note> mList;
	
	public NoteDao(Context context){
		super(context);
		mDb = getSQLiteDatabase();
	}

	
	public Cursor queryAll(){
		Cursor cursor = mDb.query(TABLE_NOTE, null, null, null, null, null, null);
		return cursor;
	}
	
	public Note qurey(String[] selectionArgs){
		Cursor cursor = mDb.query(TABLE_NOTE, null, "id=?", selectionArgs, null, null, null);
		cursor.moveToFirst();
		String content = cursor.getString(cursor.getColumnIndex(NOTE_COLUNM_CONTENT));
		String date = cursor.getString(cursor.getColumnIndex(NOTE_COLUMN_DATE));
		String id = cursor.getString(cursor.getColumnIndex(NOTE_COLUMN_ID));
		cursor.close();
		return new Note(content, date,id);
	}
	
	public long insertNote(Note note){
		ContentValues values = getValues(note);
		long row = mDb.insert(TABLE_NOTE, null, values);
		return row;
	}
	
	public int deleteNote(String[] whereArgs){
		int rows = mDb.delete(TABLE_NOTE, "id=?", whereArgs);
		return rows;
	}

	public int updateNote(Note note,String [] whereArgs){
		ContentValues values = getValues(note);
		int rows = mDb.update(TABLE_NOTE, values, "id=?", whereArgs);
		return rows;
	}
	
	private ContentValues getValues(Note note) {
		// TODO Auto-generated method stub
		ContentValues contentValues = new ContentValues();
		contentValues.put(NOTE_COLUNM_CONTENT, note.getContent());
		contentValues.put(NOTE_COLUMN_DATE, note.getDate());
		return contentValues;
	}
	
	public List<Note> getNotes(){
		Cursor cursor = queryAll();
		List<Note> list = new ArrayList<Note>();
		cursor.moveToPosition(INDEX_BEFORE_FIRST);
		while(cursor.moveToNext()){
			String content = cursor.getString(cursor.getColumnIndex(NOTE_COLUNM_CONTENT));
			String date = cursor.getString(cursor.getColumnIndex(NOTE_COLUMN_DATE));
			String id = cursor.getString(cursor.getColumnIndex(NOTE_COLUMN_ID));
			list.add(new Note(content, date,id));
		}
		cursor.close();
		return list;
	}
	
}
