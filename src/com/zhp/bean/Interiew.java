package com.zhp.bean;

import java.io.Serializable;

/**
 * 用于封装面试管理的一些数据的实体类
 * @author zhp
 *
 */
public class Interiew implements Serializable {
	/**
	 * 该类的序列号
	 */
	private static final long serialVersionUID = 110L;
	private String id;//面试对象的id
	private String company;//面试的公司
	private String date;//创建该面试管理的日期
	private String interiewDate;//面试的日期
	private String job;//面试的职位
	private String remark;//备注
	private int type;//提醒时间的类型

	public Interiew(String company, String date, String interiewDate,
			String job, String remark, int type) {
		super();
		this.company = company;
		this.date = date;
		this.interiewDate = interiewDate;
		this.job = job;
		this.remark = remark;
		this.type = type;
	}

	public Interiew(String id, String company, String date,
			String interiewDate, String job, String remark, int type) {
		super();
		this.id = id;
		this.company = company;
		this.date = date;
		this.interiewDate = interiewDate;
		this.job = job;
		this.remark = remark;
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Interiew() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInteriewDate() {
		return interiewDate;
	}

	public void setInteriewDate(String interiewDate) {
		this.interiewDate = interiewDate;
	}

}
