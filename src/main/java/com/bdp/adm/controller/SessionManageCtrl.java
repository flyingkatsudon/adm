package com.bdp.adm.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.adm.biz.SessionManageBiz;

@Controller
@RequestMapping(value = "/sm")
public class SessionManageCtrl {
	
	@Autowired
	private SessionManageBiz sessionManageBiz;

	@RequestMapping(value = "/checkSn", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkSn(HttpServletRequest request) {
		return sessionManageBiz.checkSn(request);	
	}
}
