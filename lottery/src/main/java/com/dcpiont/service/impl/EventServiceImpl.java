package com.dcpiont.service.impl;

import com.dcpiont.dao.EventDao;
import com.dcpiont.module.Event;
import com.dcpiont.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Service("EventService")
public class EventServiceImpl implements IEventService {
	@Autowired
	private EventDao eventDao;

	public List<Event> getAllEvent() throws Exception{
		return eventDao.getAllEvent();
	}

	public Event getEventById(int id) {
		return eventDao.getEventById(id);
	}

	public int addEvent(Event event) throws Exception{
		return eventDao.addEvent(event);
	}
}
