package com.bdp.adm.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.adm.biz.TknDictBiz;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping(value = "/tkn")
public class TknDictCtrl {
	
	@Autowired
	private TknDictBiz tknDictService;
	
	@RequestMapping(value = "/tknDictList", method = RequestMethod.GET)
	public String getTknDictList() {
		
		return "tknDictList";
	}
	
	// Select
	@RequestMapping(value = "/selectAllTknDict", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAllTknDict() throws JsonProcessingException {
		
		return tknDictService.selectAllTknDict();
	}
	
	// Edit
	@RequestMapping(value = "/editTknDict", method = RequestMethod.POST)
	public String editTknDict(@RequestBody String responseData) throws URISyntaxException {
		String regPtn4Action = "action=";
		String tempData = new java.net.URI(responseData).getPath();
		String[] data = tempData.split("&");
		String action = data[0].split(regPtn4Action)[1];
		
		if(action.equals("create")) {
			System.out.println("### CREATE ###");
			System.out.println("CREATE RESULT: " + tknDictService.insertTknDict(data));
		}
		else if (action.equals("edit")) {
			System.out.println("### EDIT ###");
			System.out.println("EDIT RESULT: " + tknDictService.updataeTknDict(data));
		}
		else if (action.equals("remove")) {
			System.out.println("### REMOVE ###");
			System.out.println("DELETE RESULT: " + tknDictService.deleteTknDict(data));
		}
		
		return "redirect:/tkn/selectAllTknDict";
	}
	
	
	

}
