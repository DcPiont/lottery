package com.dcpiont.service.impl;

import com.dcpiont.dao.UserDao;
import com.dcpiont.module.User;
import com.dcpiont.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Service("UserService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;

	public User selectUserByName(String userName) {
		return userDao.selectUserByName(userName);
	}

	public User loginAuth(User user){
		User result = userDao.loginAuth(user);
		return result;
	}
	public int registUser(User user) throws Exception{
		int res = userDao.registUser(user);
		return res;
	}

	public int deleteUser(int userId) {
		return userDao.deleteUserById(userId);
	}

	public int updateUser(User user){
		return userDao.updateUserInfo(user);
	}
}
