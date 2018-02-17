package com.dcpiont.dao;

import com.dcpiont.module.Event;
import com.dcpiont.module.EventVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface EventDao {
	int addEvent(Event event);

	List<Event> getAllEvent();

	Event getEventById(int id);

	int deleteEventById(@Param("id")int id);

	int stopEvent(int eventId);

	EventVO getLatestStartedEvent();
}
