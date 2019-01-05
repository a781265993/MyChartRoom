package com.accp.chatroom.pojo;

/**
 * 
* @ClassName: sending 
* @Description: 消息基础信息描述类
* @author 筠颜
* @date 2019年1月4日 上午11:51:35 
*
 */
public class sending {
	
	private String type;//发送消息类型
	private String content;//发送消息内容
	private Integer uid;//发件人id
	private Integer fid;//收件人id
	
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	
}
