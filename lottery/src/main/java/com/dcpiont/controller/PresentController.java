package com.dcpiont.controller;

import com.dcpiont.module.Present;
import com.dcpiont.module.ReturnT;
import com.dcpiont.service.IPresentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ReturnT addPresent(@RequestBody Present present) {
		int res = presentService.addPresent(present);
		if (res > 0) {
			return ReturnT.SUCCESS;
		}
		return new ReturnT(ReturnT.FAIL_CODE, "添加礼物失败");
	}

	@RequestMapping("getAllPresent")
	@ResponseBody
	public ReturnT getAllPresentByEventId(@RequestParam("eventId") int eventId) throws Exception {
		List<Present> list = presentService.getAllPresentByEventId(eventId);
		return new ReturnT(list);
	}

	@RequestMapping("addNeed")
	@ResponseBody
	public ReturnT addPresentNeed(@RequestParam("userId") int userId, @RequestParam("presentId") int presentId, @RequestParam("eventId") int eventId) {
		int res = presentService.addPresentNeed(userId, presentId, eventId);
		if (res > 0) {
			return ReturnT.SUCCESS;
		}
		return new ReturnT(ReturnT.FAIL_CODE, "添加心愿失败");
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
