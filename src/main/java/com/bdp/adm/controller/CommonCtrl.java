package com.bdp.adm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bdp.adm.biz.CommonBiz;
import com.bdp.adm.biz.CommonDictBiz;

@Controller
@RequestMapping(value = "/common")
public class CommonCtrl {
	
	@Autowired
	private CommonBiz commonBiz;
	
	@Autowired
	private CommonDictBiz commonDictBiz;
	
	@RequestMapping("/export")
	@ResponseBody
	public void exportXlsx(@RequestParam("tableNo") int tableNo, HttpServletResponse response) throws Throwable {
		commonBiz.exportXlsx(tableNo, response);
	}
	
	@RequestMapping(value="/import", method= RequestMethod.POST, headers = ("content-type=multipart/*"))
	@ResponseBody
    public String importXlsx(@RequestParam("file") MultipartFile[] multipartFiles, @RequestParam("type") String type, HttpServletRequest request) throws Throwable {
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String resultJSON = "";
		
		if(type.equals("dict")) {
			ResponseEntity<ArrayList<Object>> result = ResponseEntity.ok(commonDictBiz.importXlsx(multipartFiles));
			ArrayList<Object> list = result.getBody();					
			resultMap.put("result",  result.getBody().get(0));
		} else if(type.equals("user")) {
			ResponseEntity<ArrayList<Object>> result = ResponseEntity.ok(commonBiz.importXlsx(multipartFiles));
				
			ArrayList<Object> list = result.getBody();					
			resultMap.put("result",  result.getBody().get(0));
		}
		
		resultJSON = mapper.writeValueAsString(resultMap);
		
		return resultJSON;

    }
}
