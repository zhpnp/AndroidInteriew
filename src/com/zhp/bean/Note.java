package com.zhp.bean;

/**
 * 笔记的实体类
 * @author zhp
 *
 */
public class Note {
	private String content;//笔记内容
	private String date;//笔记创建或修改的日期
	private String id;//笔记id

	public Note(String content, String date, String id) {
		super();
		this.content = content;
		this.date = date;
		this.id = id;
	}

	public Note(String content, String date) {
		super();
		this.content = content;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
