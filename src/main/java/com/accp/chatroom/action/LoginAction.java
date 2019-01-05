package com.accp.chatroom.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.accp.chatroom.biz.WebMessageBiz;
import com.accp.chatroom.pojo.user;

@Controller
@RequestMapping("/c/user")
public class LoginAction {
	@Autowired
	private WebMessageBiz biz;
	
	@GetMapping("go")
	public String goToLogin() {
		return "login";
	}
	@GetMapping("gojob")
	public String goToJob() {
		return "job";
	}
	
	@PostMapping("Login")
	public String Login(user User,HttpSession session,Model model) {
		try {
			user obj = biz.LoginUser(User);
			session.setAttribute("user",obj);
			return "index";
		} catch (Exception e) {
			model.addAttribute("email", User.getEmail());
			model.addAttribute("pwd", User.getPassword());
			model.addAttribute("msg", e.getMessage());
			return "login";
		}
	}
}
