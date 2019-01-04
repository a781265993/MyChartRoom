package com.accp.chatroom.pojo;

/**
 * 
* @ClassName: friend 
* @Description: 好友信息基本描述类
* @author 筠颜
* @date 2018年12月26日 下午11:04:49 
*
 */
public class friend {
	private int uId;//用户ID
	private int fId;//好友ID
	private int type;//申请状态
	
	private user owner;//用户
	private user aide;//好友
	
	
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public friend(int uId, int fId, int type) {
		super();
		this.uId = uId;
		this.fId = fId;
		this.type = type;
	}
	public friend() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
