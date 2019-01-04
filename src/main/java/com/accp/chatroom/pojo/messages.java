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
	private user friend;//好友信息
	private String content;//消息内容
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
	public user getFriend() {
		return friend;
	}
	public void setFriend(user friend) {
		this.friend = friend;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public messages(int uId, int fId, user friend, String content) {
		super();
		this.uId = uId;
		this.fId = fId;
		this.friend = friend;
		this.content = content;
	}
	public messages() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
