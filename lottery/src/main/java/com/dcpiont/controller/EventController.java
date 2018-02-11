package com.dcpiont.controller;

import com.dcpiont.module.Event;
import com.dcpiont.module.ReturnT;
import com.dcpiont.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Controller("EventController")
@RequestMapping("event")
public class EventController {
	@Autowired
	private EventServiceImpl eventService;

	@RequestMapping("addEvent")
	@ResponseBody
	public ReturnT addEvent(@RequestBody Event event) throws Exception{
		int res = eventService.addEvent(event);
		if(res >0){
			return ReturnT.SUCCESS;
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"创建事件失败");
		}
	}
	@RequestMapping("getAllEvent")
	@ResponseBody
	public ReturnT getAllEvent() throws Exception{
		List<Event> list = eventService.getAllEvent();
		return new ReturnT(list);
	}

	@RequestMapping("getEventById")
	@ResponseBody
	public ReturnT getEventById(@RequestParam("eventId")int eventId){
		Event event = eventService.getEventById(eventId);
		if(event != null){
			return new ReturnT(event);
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"该事件不存在");
		}
	}

}
