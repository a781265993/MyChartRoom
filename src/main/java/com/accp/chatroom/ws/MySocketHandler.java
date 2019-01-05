package com.accp.chatroom.ws;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.accp.chatroom.biz.WebMessageBiz;
import com.accp.chatroom.pojo.friend;
import com.accp.chatroom.pojo.sending;
import com.accp.chatroom.pojo.user;
import com.accp.chatroom.util.JsonObjectUtil;
import com.accp.chatroom.ws.cfg.HttpSessionConfigurator;
import com.alibaba.fastjson.JSON;

@ServerEndpoint(value = "/ws/sys", configurator = HttpSessionConfigurator.class)
@Controller
public class MySocketHandler {
	/*
	 * 采用RSTL风格 参数使用@PathParam("参数名")
	 */
	private final static Map<Integer, Session> usersMap = new ConcurrentHashMap<Integer, Session>();// 线程安全
	private Integer uId = null;// 用户的编号

	public static ApplicationContext ac;// 非常重要

	// 开启连接

	@OnOpen
	public void onopen(Session session, EndpointConfig config) {
		WebMessageBiz messageBiz = (WebMessageBiz) ac.getBean("messageBiz");
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		final user _user = ((user) httpSession.getAttribute("user"));
		if (_user != null) {
			this.uId = _user.getId();
			usersMap.put(this.uId, session);// 存入会话信息
			// 刷新好友列表:每30m一次
			new Thread() {
				public void run() {
					// 在线数量
					int Online = 0;
					// 离线数量
					int Offline = 0;
					// 申请数量
					int ApplyFor = 0;
					for (friend f : messageBiz.queryFriendList(_user)) {
						if (f.getType() == 0) {
							ApplyFor++;
						} else if (f.getAide().getType() == 1) {
							Online++;
						} else if (f.getAide().getType() == 0) {
							Offline++;
						}
					}
					JsonObjectUtil util = new JsonObjectUtil();
					util.setType("users");
					try {
						util.setContent(messageBiz.queryFriendList(_user));
						usersMap.get(uId).getBasicRemote().sendText(util.toJSONString());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					while (true) {
						try {
							// 在线数量
							int online = 0;
							// 离线数量
							int offline = 0;
							// 申请数量
							int applyFor = 0;
							for (friend f : messageBiz.queryFriendList(_user)) {
								if (f.getType() == 0) {
									online++;
								} else if (f.getAide().getType() == 1) {
									offline++;
								} else if (f.getAide().getType() == 0) {
									applyFor++;
								}
							}
							if (online > Online || offline > Offline || applyFor > ApplyFor) {
								util.removeContent();
								util.setContent(messageBiz.queryFriendList(_user));
								usersMap.get(uId).getBasicRemote().sendText(util.toJSONString());
								Online = online;
								Offline = offline;
								ApplyFor = applyFor;
							}
							Thread.sleep(30 * 1000);
						} catch (Exception e) {
							break;
						}
					}
				}
			}.start();
			// 刷新聊天消息

		} else {
			this.onerror(session, new Throwable());
		}
	}

	// 关闭连接
	@OnClose
	public void onclose(Session session) {
		WebMessageBiz messageBiz = (WebMessageBiz) ac.getBean("messageBiz");
		System.out.println("长连接关闭");
		if (uId != null) {
			usersMap.remove(uId);
			user User = new user();
			User.setId(uId);
			User.setType(0);
			messageBiz.OverUser(User);
		}
	}

	// 连接出现异常
	@OnError
	public void onerror(Session session, Throwable e) {
		System.out.println("通讯异常");
		try {
			session.getBasicRemote().sendText("Over");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 发送消息 这个方法参数的不能更改
	@OnMessage
	public void onmessage(String msg, Session session) {
		// 发消息 pong
		try {
			if (!"ping".equals(msg)) {
				sending obj = JSON.parseObject(msg, sending.class);
				if ("sendUser".equals(obj.getType())) {
					sendUser(obj.getUid(), obj.getContent());
				} else if ("sendUsers".equals(obj.getType())) {
					sendUsers(obj.getContent());
				}
			}
		} catch (Exception e) {
			System.out.println("发送异常");
		}

	}

	// 单发
	private void sendUser(Integer uid, String msg) {
		try {
			usersMap.get(uid).getBasicRemote().sendText(msg);
		} catch (IOException e) {
			return;
		}
	}

	// 群发
	private void sendUsers(String msg) {
		for (Session session : usersMap.values()) {
			try {
				session.getBasicRemote().sendText(msg);
			} catch (IOException e) {
				continue;
			}
		}
	}
}
