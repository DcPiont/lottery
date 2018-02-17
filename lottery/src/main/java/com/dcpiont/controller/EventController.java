package com.dcpiont.controller;

import com.dcpiont.module.Event;
import com.dcpiont.module.EventVO;
import com.dcpiont.module.ReturnT;
import com.dcpiont.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DcPiont on 2018/2/11.
 */
@Controller("EventController")
@RequestMapping("event")
public class EventController {
	@Autowired
	private IEventService eventService;

	@RequestMapping("addEvent")
	@ResponseBody
	public ReturnT addEvent(@RequestBody Event event) throws Exception{
		int res = eventService.addEvent(event);
		if(res >0){
			return new ReturnT(event);
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"创建抽奖失败");
		}
	}
	@RequestMapping("getAllEvent")
	@ResponseBody
	public ReturnT getAllEvent() throws Exception{
		List<Event> list = eventService.getAllEvent();
		return new ReturnT(list);
	}

	@RequestMapping("getLatestStartedEvent")
	@ResponseBody
	public ReturnT getLatestStartedEvent() throws Exception{
		EventVO eventVO = eventService.getLatestStartedEvent();
		return new ReturnT(eventVO);
	}

	@RequestMapping("getEventById")
	@ResponseBody
	public ReturnT getEventById(@RequestParam("eventId")int eventId){
		Event event = eventService.getEventById(eventId);
		if(event != null){
			return new ReturnT(event);
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"该抽奖不存在");
		}
	}

	@RequestMapping("deleteEvent")
	@ResponseBody
	public ReturnT deleteEventId(@RequestParam("eventId")int eventId){
		int res = eventService.deleteEvent(eventId);
		if(res >0){
			return ReturnT.SUCCESS;
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"删除抽奖失败");
		}
	}

	@RequestMapping("stopEvent")
	@ResponseBody
	public ReturnT stopEvent(@RequestParam("eventId")int eventId){
		int res = eventService.stopEvent(eventId);
		if(res >0){
			return ReturnT.SUCCESS;
		}else {
			return new ReturnT(ReturnT.FAIL_CODE,"暂停抽奖失败");
		}
	}

	@RequestMapping("index")
	public ModelAndView showIndex(HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null){
			return  new ModelAndView("login");
		}
		String idString = ""+session.getAttribute("userId");
		int userId = Integer.parseInt(idString);
		if(userId == 0){
			return null;
		}else if(userId == 1){
			return  new ModelAndView("adminIndex");
		}else {
			return  new ModelAndView("index");
		}
	}

	@RequestMapping("detail")
	public ModelAndView showDetail(@RequestParam("eventId")int eventId, HttpServletRequest request){
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null){
			return  new ModelAndView("login");
		}
		String idString = "" + session.getAttribute("userId");
		int userId = Integer.parseInt(idString);
		if(userId == 0){
			return null;
		}else {
			ModelAndView modelAndView = new ModelAndView();
			if(userId == 1){
				modelAndView.setViewName("adminDetail");
			}else {
				modelAndView.setViewName("detail");
			}
			modelAndView.addObject("eventId",eventId);
			return  modelAndView;
		}
	}

}
