package com.dcpiont.dao;

import com.dcpiont.module.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface UserDao {
	int registUser(@Param("user")User user);

	User loginAuth(@Param("user")User user);

}
