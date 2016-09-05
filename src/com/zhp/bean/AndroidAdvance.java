package com.zhp.bean;

/**
 * android进阶部分的bean类
 * @author zhp
 *
 */
public class AndroidAdvance {
	private int id;
	private String url;
	private String title;
	private String from;

	public AndroidAdvance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AndroidAdvance(int id, String url, String title, String from) {
		super();
		this.id = id;
		this.url = url;
		this.title = title;
		this.from = from;
	}

	public AndroidAdvance(String url, String title, String from) {
		super();
		this.url = url;
		this.title = title;
		this.from = from;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
