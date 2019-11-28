package com.bdp.adm.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdp.adm.biz.TrendKeywordsBiz;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/trend")
public class TrendKeywordsCtrl {
	@Autowired
	private TrendKeywordsBiz trendKeywordsService;
	
	@RequestMapping(value = "/trendKeywordsList", method = RequestMethod.GET)
	public String getTrendKeywordsList() {
				
		return "trendKeywordsList";
	}
	
	// Select
	@RequestMapping(value = "/selectAllTrendKeywords", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAllTrendKeywords(String action) throws JsonProcessingException {
		//if(action == null) action=null;
		return trendKeywordsService.selectAllTrendKeywords(action);
	}
	
	// Edit
	@RequestMapping(value = "/editTrendKeywords", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String editTrendKeywords(@RequestBody String responseData, HttpServletRequest request) throws URISyntaxException, JsonMappingException, IOException {
		
		try {
			UserVO user = (UserVO) request.getSession().getAttribute("user");
			String currentUser = user.getUniqueHash();
			String regPtn4Action = "action=";
			String tempData = new java.net.URI(responseData).getPath();
			tempData = URLDecoder.decode(tempData, "UTF-8");
			String[] data = tempData.split("&");
			String action = data[0].split(regPtn4Action)[1];
			int res = 0;
			
			if(action.equals("create")) {
				System.out.println("### CREATE ###");
				res = trendKeywordsService.insertTrendKeywords(data, currentUser);
				System.out.println("CREATE RESULT: " + res);
			}
			else if (action.equals("edit")) {
				System.out.println("### EDIT ###");
				res = trendKeywordsService.updateTrendKeywords(data, currentUser);
				System.out.println("EDIT RESULT: " + res);
			}
			else if (action.equals("remove")) {
				System.out.println("### REMOVE ###");
				res = trendKeywordsService.deleteTrendKeywords(data, currentUser);
				System.out.println("DELETE RESULT: " + res);
			}
			
			return this.selectAllTrendKeywords(action);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
			
			Map<String, Object> tmpData  = new HashMap<String, Object>();
			tmpData.put("error", "데이터 입력이 잘못되었거나, 중복된 데이터를 입력하셨습니다.");
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(tmpData);
		}
		
		
	}
	

}
