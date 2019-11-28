<%@page import="java.util.ResourceBundle"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bdp.adm.vo.UserVO"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<%
	UserVO user = (UserVO) request.getSession().getAttribute("user");
	
	String changePwd = (String) request.getSession().getAttribute("changePwd");
	String starter = (String) request.getSession().getAttribute("starter");
	
	if (user != null && user.getUserId() != null) {
		if (changePwd != null && changePwd.equals("changePwd")) {
			request.getSession().invalidate();
			response.sendRedirect("logout");
		}
		if (starter != null && starter.equals("starter")) response.sendRedirect("starter.jsp");
	}
%>
</head>
<script>
$(document).ready(function(){
	
	var msg = '<c:out value="${msg}"/>';
	
	if (msg != null && msg != "") {
		alert(msg);
		window.location.href="logout";
	}
	
	var businessCode = getCookie('businessCode');
	var codes = $('#codes').val().split(',');
	var names = $('#names').val().split(',');
	var options = '';
	
	console.log(getCookie('businessCode'));
	$('#businessCode').change(function(){
		$('input[name=v1]').val('');
		$('input[name=v2]').val('');
		setCookie('businessCode', $(this).val(), 7); /* name=Ethan, 7일 뒤 만료됨 */
	});
	
	for(var i=0; i<codes.length; i++){
		if (codes[i] == businessCode)
			options += '<option value="' + codes[i] + '" selected>' + decodeURI(escape(names[i])) + '</option>';
		else
			options += '<option value="' + codes[i] + '">' + decodeURI(escape(names[i])) + '</option>';
	}
	
	$('#businessCode').html(options);

	function setCookie(cookie_name, value, days) {

		  var exdate = new Date();
		  exdate.setDate(exdate.getDate() + days * 60 * 60 * 24 * 1000);

		  var cookie_value = escape(value) + ((days == null) ? '' : ';    expires=' + exdate.toUTCString());
		  document.cookie = cookie_name + '=' + cookie_value;

	}

	function getCookie(cookie_name) {
	  var x, y;
	  var val = document.cookie.split(';');

	  for (var i = 0; i < val.length; i++) {
	    x = val[i].substr(0, val[i].indexOf('='));
	    y = val[i].substr(val[i].indexOf('=') + 1);
	    x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기

	    if (x == cookie_name) {
	      return unescape(y); // unescape로 디코딩 후 값 리턴

	    }

	  }
	}
});
</script>

<style>
body {
	padding-top:10%;
}

input, select{
	width: 200px;
	border: 1px solid #cccccc;
	height: 35px;
	padding: 5px;
	border-radius: 5px;
	margin-top:10px;
	cursor:pointer;
}

.chgbtn:hover{
	color: #fff;
	font-weight: bold;
	background-color: green;
}
</style>
<body>
	<center>
	<div class="container">
	<input type="hidden" id="codes" value="<spring:eval expression="@properties['business.codes']"></spring:eval>"/>
	<input type="hidden" id="names" value="<spring:eval expression="@properties['business.names']"></spring:eval>"/>
		<h3><span><b>Shinhan</b></span>&nbsp;|&nbsp;<span>관리자페이지</span></h3>
		<form action="/adm/process" method="post">
			<div>
				<select id="businessCode" name="v0"></select>
			</div>
			<div>
				<input type="text" placeholder="사&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;번" name="v1">
			</div>
			<div>
				<input type="password" placeholder="비밀번호" name="v2"/>
			</div>
			<div>
				<input class="chgbtn" type="submit" id="submit" value="로그인">
			</div>
		</form>
	</div>
	</center>
</body>
</html>