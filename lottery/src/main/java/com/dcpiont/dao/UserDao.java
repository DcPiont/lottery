package com.dcpiont.dao;

import com.dcpiont.module.NeedBO;
import com.dcpiont.module.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface UserDao {
	User selectUserByName(String userName);

	int registUser(User user);

	User loginAuth(@Param("user")User user);

	List<NeedBO> getJoinUserList(@Param("eventId")int eventId);

	int deleteUserById(int userId);
}
