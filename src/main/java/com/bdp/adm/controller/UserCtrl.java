package com.bdp.adm.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bdp.adm.biz.UserBiz;
import com.bdp.adm.biz.UserRoleBiz;
import com.bdp.adm.vo.UserVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping(value = "/user")
public class UserCtrl {
	
	@Autowired
	private UserBiz userBiz;
	
	@Autowired
	private UserRoleBiz userRoleService;
	
	@Autowired
	private DataSourceTransactionManager dataSourceTransactionManager;
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String getUserList() {
		
		return "userList";
	}
	
	// Select
	@RequestMapping(value = "/selectAllUser", method = RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String selectAllUser(String action) throws JsonProcessingException, IOException {
		if(action == null) action=null;
		return userBiz.selectAllUser(action);
	}
	
	// Edit
	@RequestMapping(value = "/editUser", method = RequestMethod.POST,  produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String editUser(@RequestBody String responseData, HttpServletRequest request) throws URISyntaxException, JsonMappingException, IOException {
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = dataSourceTransactionManager.getTransaction(def);
		
		try {
			UserVO user = (UserVO) request.getSession().getAttribute("user");
			String currentUser = user.getUniqueHash();
	
			String RegPtn4Action = "action=";
			String tempData = new java.net.URI(responseData).getPath();
			tempData = URLDecoder.decode(tempData, "UTF-8");			
			String[] data = tempData.split("&");
			String action = data[0].split(RegPtn4Action)[1];
			int res = 0;
			int res4UserRole = 0;
			
			if(action.equals("create")) {
				System.out.println("### CREATE ###");
				res = userBiz.insertUser(data, currentUser);
				System.out.println("CREATE RESULT for dashboard_user: " + res);
				
				res4UserRole = userRoleService.insertUserRole(data, currentUser);
				System.out.println("CREATE RESULT for dashboard_user_role: " + res4UserRole);
				
				if (res + res4UserRole != 2) {
					throw new Exception("Transaction error");
				}		
			}
			else if (action.equals("edit")) {
				System.out.println("### EDIT ###");
				res = userBiz.updateUser(data, currentUser);
				System.out.println("EDIT RESULT: " + res);
			}
			else if (action.equals("remove")) {
				System.out.println("### REMOVE ###");
				res = userBiz.deleteUser(data, currentUser);
				System.out.println("DELETE RESULT: " + res);
			}
			else if (action.equals("initUserStat")) {
				System.out.println("### INIT USER STATUS ###");
				res = userBiz.initUserStat(data, currentUser);
				System.out.println("INIT USER STATUS: " + res);
				for (String i:data) {
					System.out.println("initUserStat" + i);					
				}
			}
			
			if (res != 1 ) {
				throw new Exception("Res is not 1");
			}
			dataSourceTransactionManager.commit(status);
			return this.selectAllUser(action);
			
		} catch (Exception e) {
						
			System.out.println(e.getMessage());
			
			Map<String, Object> tmpData  = new HashMap<String, Object>();
			tmpData.put("error", "데이터 입력이 잘못되었거나, 중복된 데이터를 입력하셨습니다.");
			
			dataSourceTransactionManager.rollback(status);
			
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(tmpData);
		}
	}
	
	
	
	
	// Meta
	@RequestMapping(value = "/getUserMeta", method = RequestMethod.GET,  produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getUserMeta() throws URISyntaxException, JsonMappingException, IOException {
		
		try {
			Map<String, Object> map = userBiz.getUserMeta();
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(map);
			
		} catch (Exception e) {		
			System.out.println(e.getMessage().substring(0, 50) + "...");
			Map<String, Object> tmpData  = new HashMap<String, Object>();
			tmpData.put("error", "데이터 입력이 잘못되었거나, 중복된 데이터를 입력하셨습니다.");
			ObjectMapper mapper = new ObjectMapper();
			
			return mapper.writeValueAsString(tmpData);
		}
	}
	
	
}
