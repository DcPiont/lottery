package com.dcpiont.controller;

import com.dcpiont.module.ReturnT;
import com.dcpiont.module.User;
import com.dcpiont.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping("/")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping("auth")
	@ResponseBody
	public ReturnT loginAuth(@RequestBody User user, HttpServletRequest request) throws Exception {
		User res = userService.loginAuth(user);
		if (res == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "用户名或密码错误");
		}
		HttpSession session = request.getSession();
		session.setAttribute("userId", res.getId());
		session.setAttribute("userName", res.getUserName());
		return ReturnT.SUCCESS;
	}

	@RequestMapping("signUp")
	@ResponseBody
	public ReturnT registUser(@RequestBody User user, HttpServletRequest request) throws Exception {
		User tempUser = userService.selectUserByName(user.getUserName());
		if (tempUser != null) {
			return new ReturnT(ReturnT.FAIL_CODE, "用户已存在");
		}
		int res = userService.registUser(user);
		if (res > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getUserName());
			return ReturnT.SUCCESS;
		} else {
			return new ReturnT(ReturnT.FAIL_CODE, "注册失败");
		}
	}

	@RequestMapping("deleteUser")
	@ResponseBody
	public ReturnT deleteUser(@RequestParam("userId") int userId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int currId = Integer.parseInt((String) session.getAttribute("userId"));
		if (currId == 1) {
			//管理员才能删除用户
			int res = userService.deleteUser(userId);
			if (res > 0) {
				return ReturnT.SUCCESS;
			} else {
				return new ReturnT(ReturnT.FAIL_CODE, "删除用户失败");
			}
		} else {
			return new ReturnT(ReturnT.FAIL_CODE, "尚无该权限");
		}
	}

	/**
	 * 更新用户密码
	 * @param user
	 * @return
	 *
	 * TODO 待后续改为统一异常管理
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public ReturnT updatePassword(@RequestBody User user, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = Integer.parseInt((String)session.getAttribute("userId"));
		if(user.getId() == userId) {
			int res = userService.updateUser(user);
			if (res > 0) {
				return ReturnT.SUCCESS;
			}else {
				return new ReturnT(ReturnT.FAIL_CODE, "更新用户信息失败");
			}
		}else {
			return new ReturnT(ReturnT.FAIL_CODE, "请先登录");
		}
	}
}
