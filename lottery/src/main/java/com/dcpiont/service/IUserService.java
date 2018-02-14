package com.dcpiont.service;

import com.dcpiont.module.User;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface IUserService {
	User selectUserByName(String userName);

	User loginAuth(User user);

	int registUser(User user) throws Exception;

	int deleteUser(int userId);

	int updateUser(User user);
}
