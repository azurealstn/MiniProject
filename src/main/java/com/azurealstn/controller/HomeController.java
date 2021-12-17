package com.azurealstn.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.azurealstn.beans.UserBean;

@Controller
public class HomeController {
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		System.out.println("minsu="+loginUserBean);
		return "redirect:/main";
	}
}
