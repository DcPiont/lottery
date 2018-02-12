package com.dcpiont.controller;

import com.dcpiont.module.ReturnT;
import com.dcpiont.module.User;
import com.dcpiont.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Controller("UserController")
@RequestMapping("user")
public class UserController {
	@Autowired
	private IUserService userService;

	@RequestMapping("login")
	public String showLoginPage(){
		return "page/login";
	}

	@RequestMapping("auth")
	@ResponseBody
	public ReturnT loginAuth(@RequestBody User user, HttpServletRequest request) throws Exception {
		User res = userService.loginAuth(user);
		if (res == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "该用户不存在");
		}
		HttpSession session = request.getSession();
		session.setAttribute("userId", res.getId());
		session.setAttribute("userName", res.getUserName());
		return ReturnT.SUCCESS;
	}

	@RequestMapping("signUp")
	@ResponseBody
	public ReturnT registUser(@RequestBody User user, HttpServletRequest request) throws Exception {
		int res = userService.registUser(user);
		if(res>0){
			HttpSession session = request.getSession();
			session.setAttribute("userId",user.getId());
			session.setAttribute("userName",user.getUserName());
			return ReturnT.SUCCESS;
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"注册失败");
		}
	}
}
