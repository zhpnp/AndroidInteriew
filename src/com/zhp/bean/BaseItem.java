package com.zhp.bean;

import java.io.Serializable;

/**
 * java基础试题的bean类
 * 
 * @author zhp
 * 
 */
public class BaseItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3306L;
	
	private int id;
	private String item;
	private String answer;
	private int isCollection;

	public BaseItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BaseItem(int id, String item, String answer, int isCollection) {
		super();
		this.id = id;
		this.item = item;
		this.answer = answer;
		this.isCollection = isCollection;
	}

	public BaseItem(String item, String answer) {
		super();
		this.item = item;
		this.answer = answer;
	}

	public int isCollection() {
		return isCollection;
	}

	public void setCollection(int isCollection) {
		this.isCollection = isCollection;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
