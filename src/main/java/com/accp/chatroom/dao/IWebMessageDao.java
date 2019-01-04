package com.accp.chatroom.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.accp.chatroom.pojo.friend;
import com.accp.chatroom.pojo.user;

public interface IWebMessageDao {
	//登陆
	public user LoginUser(@Param("obj")user obj);
	//修改登陆状态
	public void updateUserType(@Param("obj")user obj);
	//用户所有好友显示
	public List<friend> queryFriendList(@Param("obj")user obj);
	
	//查询好友（未实现）
	public void queryFriend(@Param("obj")user obj);
	//添加好友（未实现）
	public void saveFriend(@Param("obj")user obj);
	//删除好友（未实现）
	public void deleteFriend(@Param("obj")user obj);
	//储存聊天记录（未实现）
	public void saveMessage();
	//查看聊天记录（未实现）
	public void queryMessage(@Param("obj")user obj);
}
