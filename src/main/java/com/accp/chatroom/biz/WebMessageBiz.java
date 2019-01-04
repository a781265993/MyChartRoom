package com.accp.chatroom.biz;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.accp.chatroom.dao.IWebMessageDao;
import com.accp.chatroom.pojo.friend;
import com.accp.chatroom.pojo.user;

@Service("messageBiz")
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED, readOnly = true)
public class WebMessageBiz  {

	@Autowired
	private IWebMessageDao dao;
	
	//登陆
	public user LoginUser(user User) {
		user obj = dao.LoginUser(User);
		if(obj == null) {
			throw new RuntimeException("并未存在该用户!");
		} else if(!obj.getPassword().equals(User.getPassword())){
			throw new RuntimeException("密码输入错误!");
		}
		obj.setType(1);
		dao.updateUserType(obj);
		return obj;
	}
	//退出或者断线(在对象type设置为0)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void OverUser(user User) {
		dao.updateUserType(User);
	}
	//用户所有好友显示
	public List<friend> queryFriendList(@Param("obj")user obj) {
		return dao.queryFriendList(obj);
	}

}
