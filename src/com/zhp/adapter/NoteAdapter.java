package com.zhp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhp.bean.Note;
import com.zhp.d11_androidinteriewskill.R;

/**
 * 自定义的笔记列表的适配器
 * @author zhp
 *
 */
public class NoteAdapter extends ArrayAdapter<Note> {

	public NoteAdapter(Context context, int resource, List<Note> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHodler vh = null;
		Note note = getItem(position);
		if(convertView == null){
			view = View.inflate(getContext(), R.layout.note_list_item, null);
			vh = new ViewHodler();
			vh.tv_content = (TextView)view.findViewById(R.id.tv_note_content);
			vh.tv_date = (TextView)view.findViewById(R.id.tv_note_date);
			view.setTag(vh);
		}else{ 
			view = convertView;
			vh = (ViewHodler) view.getTag();
		}
		
		vh.tv_content.setText(note.getContent());
		vh.tv_date.setText(note.getDate());
		
		return view;
	}

	class ViewHodler {
		TextView tv_content;
		TextView tv_date;

	}

}
