<%@page import="com.bdp.adm.vo.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>ADM</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

$(document).ready(function(){
	
	$('#mod').click(function(e){

			var param = {
					userId: $("input[name=userId]").val(),
					userPassword: $("input[name=userPassword]").val(),
					oldUserPassword: $("input[name=oldUserPassword]").val(),
					businessCode: $("input[name=businessCode]").val(),
					checkPw: $("input[name=checkPw]").val()
			}
			
			if(validate(param)){
				$.ajax({
					url: 'update',
					type : 'POST',
		        	contentType: 'application/json; charset=utf-8',
					data : JSON.stringify(param),
					success: function(response){
						console.log(response);
						alert(response.msg);
						window.location.href = response.location;
					}, 
					error: function(response) {
						console.log(response);
					},
					complete: function() {
					}
				});
			} else {
				return false;
			}
	});
	
	var validate = function(param) {
		
		// 비밀번호 확인
		if (param.userPassword == 'undefined' || param.userPassword == '') {
			alert('비밀번호를 확인하세요');
			return false;
		}

		// 새 비밀번호와 일치하는지 확인
		if (param.userPassword != param.checkPw) {
			console.log(param.userPassword + "/" + param.checkPw);
			alert('비밀번호가 일치하지 않습니다');
			return false;
		} 
		// 일치한다면
		else {
			var rule = /(?=.*[a-z])(?=.*[A-Z])(?=.*[#$^+=!*()@%&]).{8,20}$/;
			
			// 비밀번호 유효성 검사
			if (param.userPassword == 'init12345') {
				alert('초기 비밀번호로 변경할 수 없습니다');
				return false;
			} else if (!rule.test(param.userPassword)){
				alert('비밀번호는 8자리 이상, 대소문자, 특수문자를 포함하여 입력하세요');
				return false;
			} else if (param.userPassword == 'init12345') {
				alert('초기 비밀번호로 변경할 수 없습니다');
				return false;
			} else {
				return true;
			}
		}
	}
});

</script>

<style>
body {
	padding-top:10%;
}

input {
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
</head>
<%
	request.getSession().setAttribute("changePwd", "changePwd");

	String msg = (String) request.getAttribute("msg");
	String starter = (String) request.getSession().getAttribute("starter");
	UserVO user = (UserVO) request.getSession().getAttribute("user");
	
	String userId = "";
	String userPassword = "";
	String businessCode = "";

	if (user == null) {
		request.getSession().invalidate();
		response.sendRedirect("/adm/logout");
	} else {
		userId = user.getUserId();
		userPassword = user.getUserPassword();
		businessCode = user.getBusinessCode();
	}
	
	if (starter != null && starter.equals("starter")) {
		request.getSession().setAttribute("changePwd", null);
		response.sendRedirect("starter.jsp");
	}
	if (userId == null || userPassword == null) {
		request.getSession().invalidate();
		response.sendRedirect("/adm/logout");
	}
%>
<body>
	<center>
	<div>
		<h3>초기 비밀번호는 반드시 변경이 필요합니다</h3>
		<div>
			<h6 style="color: red">* 비밀번호는 8자리 이상, 대소문자, 특수문자를 포함하여 입력하세요</h5>
			<input type="text" name="userId" value="<%=userId %>" readOnly>
			<input type="hidden" name="oldUserPassword" value="<%=userPassword %>"/>
			<input type="hidden" name="businessCode" value="<%=businessCode %>"/>
		</div>
		<div>
			<input type="password" placeholder="새 비밀번호" name="userPassword" autocomplete="new-password">
		</div>
		<div>
			<input type="password" placeholder="비밀번호 확인" name="checkPw" autocomplete="new-password">
		</div>
		<br/><br/>
		<div style="display:flex; justify-content: center;">
			<div><input class="chgbtn" type="button" id="mod" value="변경하기"/></div>
			&nbsp;&nbsp;&nbsp;
			<div><a href="/adm/logout"><input class="chgbtn" type="button" value="취소"/></a></div>
		</div>
		
	</div>
	</center>
</body>
</html>
