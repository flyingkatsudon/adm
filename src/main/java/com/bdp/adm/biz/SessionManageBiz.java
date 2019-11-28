package com.bdp.adm.biz;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface SessionManageBiz {
	
	public ArrayList<Object> getUserList();
	
	public void setUserList(ArrayList<Object> userList);
	
	public Map<String, Object> checkSn(HttpServletRequest request);
}
