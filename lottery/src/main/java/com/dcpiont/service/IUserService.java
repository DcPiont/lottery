package com.dcpiont.service;

import com.dcpiont.module.User;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface IUserService {
	public User loginAuth(User user);

	public int registUser(User user) throws Exception;
}
