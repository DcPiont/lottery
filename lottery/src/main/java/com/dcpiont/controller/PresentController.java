package com.dcpiont.controller;

import com.dcpiont.module.NeedBO;
import com.dcpiont.module.Present;
import com.dcpiont.module.PresentVO;
import com.dcpiont.module.ReturnT;
import com.dcpiont.service.IPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by DcPiont on 2018/2/13.
 */
@Controller("PresentController")
@RequestMapping("present")
public class PresentController {
	@Autowired
	private IPresentService presentService;

	@RequestMapping("addPresent")
	@ResponseBody
	public ReturnT addPresent(@RequestBody Present present, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null){
			return new ReturnT(ReturnT.FAIL_CODE, "session信息错误");
		}
		String idString = ""+session.getAttribute("userId");
		int userId = Integer.parseInt(idString);
		present.setUserId(userId);
		int res = presentService.addPresent(present);
		if (res > 0) {
			return ReturnT.SUCCESS;
		}
		return new ReturnT(ReturnT.FAIL_CODE, "添加礼物失败");
	}

	@RequestMapping("getAllPresent")
	@ResponseBody
	public ReturnT getAllPresentByEventId(@RequestParam("eventId") int eventId) throws Exception {
		List<PresentVO> list = presentService.getAllPresentByEventId(eventId);
		return new ReturnT(list);
	}

	@RequestMapping("getSelectedPresent")
	@ResponseBody
	public ReturnT getSelectedPresent(@RequestParam("eventId") int eventId, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null){
			return new ReturnT(ReturnT.FAIL_CODE, "session信息错误");
		}
		String idString = ""+session.getAttribute("userId");
		int userId = Integer.parseInt(idString);
		NeedBO needBO = presentService.getUserNeedPresent(eventId,userId);
		return new ReturnT(needBO);
	}

	@RequestMapping("getWinner")
	@ResponseBody
	public ReturnT getPresentWinner(@RequestParam("eventId") int eventId) throws Exception {
		List<PresentVO> list = presentService.getPresentWinner(eventId);
		return new ReturnT(list);
	}

	@RequestMapping(value = "addNeed", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ReturnT addPresentNeed(@RequestParam("presentId") int presentId, @RequestParam("eventId") int eventId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("userId") == null){
			return new ReturnT(ReturnT.FAIL_CODE, "session信息错误");
		}
		String idString = ""+session.getAttribute("userId");
		int userId = Integer.parseInt(idString);
		int res = presentService.addPresentNeed(userId, presentId, eventId);
		return new ReturnT(ReturnT.SUCCESS_CODE, "result:" + res);
	}

	@RequestMapping("lottery")
	@ResponseBody
	public ReturnT lottery(@RequestParam("eventId") int eventId) {
		int res = presentService.lottery(eventId);
		if (res > 0) {
			return ReturnT.SUCCESS;
		}
		return new ReturnT(ReturnT.FAIL_CODE, "计奖失败");
	}

}
