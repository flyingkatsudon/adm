<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.bdp.adm.vo.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	UserVO user = (UserVO) request.getSession().getAttribute("user");

	String userId = "";
	
	if (user == null) {
		response.sendRedirect("/adm/logout");
	} else {
		userId = user.getUserId();	
	}
%>
<div>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;현재 로그인 중인 사용자<br>&nbsp;&nbsp;
	<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=userId%></strong><br><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="/adm/logout"><input type="button" value="사용자 로그아웃"/></a>
</div>

<form name="userInfo">
	<input type="hidden" name="businessCode" value="<%=user.getBusinessCode()%>"/>
</form>
