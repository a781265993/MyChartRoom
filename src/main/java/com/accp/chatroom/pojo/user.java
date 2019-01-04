package com.accp.chatroom.pojo;

/**
 * 
* @ClassName: user 
* @Description: 用户基本信息描述类
* @author 筠颜
* @date 2018年12月26日 下午11:01:30 
*
 */
public class user {
	private int id;//用户编号
	private String email;//用户邮箱
	private String password;//用户密码
	private String name;//用户姓名
	private String imgName;//头像全名称
	private String imgPath;//头像完整目录
	private int type;//用户状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public user(int id, String email, String password, String name, String imgName, String imgPath, int type) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.type = type;
	}
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
