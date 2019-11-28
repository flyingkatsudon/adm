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

import com.bdp.adm.biz.RoleResBiz;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/roleRes")
public class RoleResCtrl {
	
	@Autowired
	private RoleResBiz roleResService;
	
	@RequestMapping(value = "/roleResList", method = RequestMethod.GET)
	public String getRoleResList() {
		
		return "roleResList";
	}
	
	// Select
	@RequestMapping(value = "/selectAllRoleRes", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAllRoleRes(String action) throws JsonProcessingException {
		
		return roleResService.selectAllRoleRes(action);
	}
	
	// Edit
	@RequestMapping(value = "/editRoleRes", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String editRoleRes(@RequestBody String responseData, HttpServletRequest request) throws URISyntaxException, JsonMappingException, IOException {
		
		try {
			UserVO user = (UserVO) request.getSession().getAttribute("user");
			String currentUser = user.getUniqueHash();
			
			String RegPtn4Action = "action=";
			String tempData = new java.net.URI(responseData).getPath();
			tempData = URLDecoder.decode(tempData, "UTF-8");
			String[] data = tempData.split("&");
			String action = data[0].split(RegPtn4Action)[1];
			int res = 0;
			
			if(action.equals("create")) {
				System.out.println("### CREATE ###");
				res = roleResService.insertRoleRes(data, currentUser);
				System.out.println("CREATE RESULT: " + res);
			}
			else if (action.equals("edit")) {
				System.out.println("### EDIT ###");
				res = roleResService.updateRoleRes(data, currentUser);
				System.out.println("EDIT RESULT: " + res);
			}
			else if (action.equals("remove")) {
				System.out.println("### REMOVE ###");
				res = roleResService.deleteRoleRes(data, currentUser);
				System.out.println("DELETE RESULT: " + res);
			}
			
			if (res != 1) {
				throw new Exception("Res is not 1");
			}
			
			return this.selectAllRoleRes(action);
			
		} catch (Exception e) {		
			System.out.println(e.getMessage().substring(0, 50) + "...");
			Map<String, Object> tmpData  = new HashMap<String, Object>();
			tmpData.put("error", "데이터 입력이 잘못되었거나, 중복된 데이터를 입력하셨습니다.");
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(tmpData);
		}

	}
	
	// Meta
	@RequestMapping(value = "/getRoleResMeta", method = RequestMethod.GET,  produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getRoleResMeta() throws URISyntaxException, JsonMappingException, IOException {
		
		try {
			Map<String, Object> map = roleResService.getRoleResMeta();
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(map);
			
		} catch (Exception e) {		
			System.out.println(e.getMessage().substring(0, 50) + "...");
			Map<String, Object> tmpData  = new HashMap<String, Object>();
			tmpData.put("error", "Occured Error : " + e.getMessage());
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(tmpData);
		}
	}

	
	

}
