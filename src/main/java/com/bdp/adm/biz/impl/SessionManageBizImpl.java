package com.bdp.adm.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bdp.adm.biz.SessionManageBiz;
import com.bdp.adm.vo.UserVO;

public final class SessionManageBizImpl implements SessionManageBiz {
	
	private static ArrayList<Object> userList = new ArrayList<Object>();
	
	@Override
	public ArrayList<Object> getUserList() {
		return userList;
	}
	
	@Override
	public void setUserList(ArrayList<Object> userList) {
		this.userList = userList;
	}
	
	@Override
	public Map<String, Object> checkSn(HttpServletRequest request) {
		
		String sessionId = request.getSession().getId();
		UserVO curUser = (UserVO) request.getSession().getAttribute("user");
		String userId = "";
		
		if (curUser != null) userId = curUser.getUserId();
		else return null;
		
		Map<String, Object> retnMap   = new HashMap<String, Object>();	
		
		boolean status = true;
		
		if (userList.size() == 0) {
			status = true;
		} else {
			for(Object o : userList) {
				Map<String, Object> map = (Map<String, Object>) o;
				UserVO user = (UserVO) map.get("user");

				if (!map.get("sessionId").equals(sessionId) && user.getUserId().equals(userId)) {
					status = false;
					retnMap.put("loginIp", user.getLoginIp());
					break;
				}
			}
		}

		retnMap.put("status", status);
		return retnMap;
	}
}
