package com.bdp.adm.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.adm.biz.ScreenLoginBiz;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping(value = "/sl")
public class ScreenLoginCtrl {
	@Autowired
	private ScreenLoginBiz screenLoginBiz;
	
	@RequestMapping(value = "/screenLoginList", method = RequestMethod.GET)
	public String getScreenLoginList() {
		
		return "screenLoginList";
	}
	
	// Data Request for EWD
	@RequestMapping(value = "/selectAllScreenLogin", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAllScreenLogin() throws JsonProcessingException {
		
		return screenLoginBiz.selectAllScreenLogin();
	}
	
	// On Edit
	@RequestMapping(value = "/editScreenLogin", method = RequestMethod.POST)
	public String editScreenLogin(@RequestBody String responseData, HttpServletRequest request) throws URISyntaxException {
		
		UserVO user = (UserVO) request.getSession().getAttribute("user");
		String currentUser = user.getUniqueHash();	
		String RegPtn4Action = "action=";
		String tempData = new java.net.URI(responseData).getPath();
		String[] data = tempData.split("&");
		String action = data[0].split(RegPtn4Action)[1];
		
		if(action.equals("create")) {
			System.out.println("### CREATE ###");
			System.out.println("CREATE RESULT: " + screenLoginBiz.insertScreenLogin(data, currentUser));
		}
		else if (action.equals("edit")) {
			System.out.println("### EDIT ###");
			System.out.println("EDIT RESULT: " + screenLoginBiz.updateScreenLogin(data, currentUser));
		}
		else if (action.equals("remove")) {
			System.out.println("### REMOVE ###");
			System.out.println("DELETE RESULT: " + screenLoginBiz.deleteScreenLogin(data, currentUser));
		}
		
		return "redirect:/sl/selectAllScreenLogin";
	}
	

}
