package com.bdp.adm.security.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.bdp.adm.biz.SessionManageBiz;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

	private static final Logger logger = LoggerFactory.getLogger(LogoutSuccessHandler.class);
	
	@Autowired
	private SessionManageBiz sessionManageBiz;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		ArrayList<Object> userList = sessionManageBiz.getUserList();
		
		if(userList.size() != 0) {
			for(int i=0; i<userList.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) userList.get(i);
				
				if(map.get("sessionId").equals(request.getSession().getId())){
					sessionManageBiz.getUserList().remove(i);
					break;
				}
			}
		}
		
		logger.info("onLogoutSuccess: {}", request);
		super.onLogoutSuccess(request, response, authentication);
	}
}
