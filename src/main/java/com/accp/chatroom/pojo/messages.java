package com.accp.chatroom.pojo;

/**
 * 
* @ClassName: messages 
* @Description: 消息基础信息描述类
* @author 筠颜
* @date 2018年12月26日 下午11:03:39 
*
 */
public class messages {
	private int uId;//用户ID
	private int fId;//好友ID
	private String content;//消息内容
	private int readtype;//阅读状态
	private int deletetype;//删除状态

	private user owner;//用户
	private user aide;//好友
	
	public int getReadtype() {
		return readtype;
	}
	public void setReadtype(int readtype) {
		this.readtype = readtype;
	}
	public int getDeletetype() {
		return deletetype;
	}
	public void setDeletetype(int deletetype) {
		this.deletetype = deletetype;
	}
	public user getOwner() {
		return owner;
	}
	public void setOwner(user owner) {
		this.owner = owner;
	}
	public user getAide() {
		return aide;
	}
	public void setAide(user aide) {
		this.aide = aide;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getfId() {
		return fId;
	}
	public void setfId(int fId) {
		this.fId = fId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public messages(int uId, int fId, String content) {
		super();
		this.uId = uId;
		this.fId = fId;
		this.content = content;
	}
	public messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
