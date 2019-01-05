package com.accp.chatroom.pojo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class TblJob {

	private String jname;
	private String jgroup;
	private String jcron;
	private String jdes;
	private Integer jstate;
	@JSONField(format="yyyy-MM-dd")
	private Date jdate;
	private String juser;

	public TblJob() {
		super();
	}

	public Integer getJstate() {
		return jstate;
	}

	public void setJstate(Integer jstate) {
		this.jstate = jstate;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
	}

	public String getJgroup() {
		return jgroup;
	}

	public void setJgroup(String jgroup) {
		this.jgroup = jgroup;
	}

	public String getJcron() {
		return jcron;
	}

	public void setJcron(String jcron) {
		this.jcron = jcron;
	}

	public String getJdes() {
		return jdes;
	}

	public void setJdes(String jdes) {
		this.jdes = jdes;
	}

	public Date getJdate() {
		return jdate;
	}

	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}

	public String getJuser() {
		return juser;
	}

	public void setJuser(String juser) {
		this.juser = juser;
	}

}
