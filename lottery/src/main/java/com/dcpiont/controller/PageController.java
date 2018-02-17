package com.dcpiont.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by DcPiont on 2018/2/15.
 */
@Controller("PageController")
@RequestMapping("/")
public class PageController {

	@RequestMapping(value = {"","login"})
	public ModelAndView showLoginPage(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
}
