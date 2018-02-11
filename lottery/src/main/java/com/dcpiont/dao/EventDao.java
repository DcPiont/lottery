package com.dcpiont.dao;

import com.dcpiont.module.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface EventDao {
	int addEvent(@Param("event")Event event);

	List<Event> getAllEvent();

	Event getEventById(int id);

	int deleteEventById(@Param("id")int id);
}
