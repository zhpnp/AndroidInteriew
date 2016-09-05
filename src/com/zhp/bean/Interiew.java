package com.zhp.bean;

import java.io.Serializable;

/**
 * ���ڷ�װ���Թ����һЩ���ݵ�ʵ����
 * @author zhp
 *
 */
public class Interiew implements Serializable {
	/**
	 * ��������к�
	 */
	private static final long serialVersionUID = 110L;
	private String id;//���Զ����id
	private String company;//���ԵĹ�˾
	private String date;//���������Թ��������
	private String interiewDate;//���Ե�����
	private String job;//���Ե�ְλ
	private String remark;//��ע
	private int type;//����ʱ�������

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
