package com.dcpiont.service;

import com.dcpiont.module.Event;
import com.dcpiont.module.EventVO;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
public interface IEventService {
	List<Event> getAllEvent() throws Exception;

	Event getEventById(int id);

	int addEvent(Event event) throws Exception;

	int deleteEvent(int eventId);

	int stopEvent(int eventId);

	EventVO getLatestStartedEvent();
}
