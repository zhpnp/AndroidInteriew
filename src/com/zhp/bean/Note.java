package com.zhp.bean;

/**
 * �ʼǵ�ʵ����
 * @author zhp
 *
 */
public class Note {
	private String content;//�ʼ�����
	private String date;//�ʼǴ������޸ĵ�����
	private String id;//�ʼ�id

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
