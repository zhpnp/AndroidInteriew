package com.zhp.fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.zhp.adapter.NoteAdapter;
import com.zhp.bean.Note;
import com.zhp.d11_androidinteriewskill.NoteEditActivity;
import com.zhp.d11_androidinteriewskill.R;
import com.zhp.dao.NoteDao;
import com.zhp.popuewindow.DeletePopueWindow;

public class FragmentNote extends Fragment implements OnClickListener {
	public static final int NOTE_ADD = 1;
	public static final int NOTE_UPDATE = 2;

	private List<Note> mList;
	private ListView lv;
	private NoteAdapter mAdapter;
	
	private TextView tv;
	private ImageButton ibtn;
	
	private View view;
	
	private NoteDao mDao;

	private int position;// 记录当前被选中的笔记的位置

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_note, null);
		init();
		return view;
	}

	private void init() {
		// TODO Auto-generated method stub
		lv = (ListView) view.findViewById(R.id.lv_note);
		tv = (TextView) view.findViewById(R.id.tv_no_note);
		ibtn = (ImageButton) view.findViewById(R.id.ibtn_note_add);
		ibtn.setOnClickListener(this);

		mDao = new NoteDao(getActivity());
		mList = mDao.getNotes();

		mAdapter = new NoteAdapter(getActivity(), R.layout.note_list_item,
				mList);
		lv.setAdapter(mAdapter);
		if (mList.size() == 0) {
			lv.setVisibility(View.GONE);
			tv.setVisibility(View.VISIBLE);
		}
		setListViewListener();
	}

	private void setListViewListener() {
		// TODO Auto-generated method stub
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				position = arg2;
				Intent it = new Intent(getActivity(), NoteEditActivity.class);
				it.putExtra(NoteEditActivity.NOTE_CONTENT, mList.get(arg2)
						.getContent());
				startActivityForResult(it, NOTE_UPDATE);
			}
		});

		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, final int position, long id) {
				// TODO Auto-generated method stub
				DeletePopueWindow window = new DeletePopueWindow(
						getActivity(), new DeletePopueWindow.Callback() {
							@Override
							public void delete() {
								// TODO Auto-generated method stub
								Note note = mList.remove(position);
								mAdapter.notifyDataSetChanged();
								if (mList.size() == 0) {//如果集合中没有元素，则设置ListView为不可见，tv（提示用户没有笔记的文字）可见
									tv.setVisibility(View.VISIBLE);
									lv.setVisibility(View.GONE);
								}
								mDao.deleteNote(new String[] { note.getId() });

							}
						});

				window.showPopueWidow(view);
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent it = new Intent(getActivity(), NoteEditActivity.class);
		startActivityForResult(it, NOTE_ADD);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (resultCode == Activity.RESULT_OK) {
			String content = data.getStringExtra(NoteEditActivity.NOTE_CONTENT);
			Note note = new Note(content, getDate());
			if (requestCode == NOTE_ADD) {
				if (mList.size() == 0) {
					tv.setVisibility(View.GONE);
					lv.setVisibility(View.VISIBLE);
				}
				mList.add(note);
				mAdapter.notifyDataSetChanged();
				mDao.insertNote(note);
			} else if (requestCode == NOTE_UPDATE) {
				String id = mList.get(position).getId();
				mList.remove(position);
				mList.add(position, note);
				mAdapter.notifyDataSetChanged();
				mDao.updateNote(note, new String[] { id });
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public static String getDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		return format.format(new Date());
	}

}
