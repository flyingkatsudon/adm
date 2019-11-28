<%@page import="com.bdp.adm.vo.UserVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ADM</title>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
	<script src="http://malsup.github.com/jquery.form.js"></script> 
	
	<!-- custom cssfile -->
	<link rel="stylesheet" href="resources/css/main.css" />
	<!-- <link rel="stylesheet" href="resources/css/tree.css" /> -->
	
	<%
	    UserVO user = (UserVO) request.getSession().getAttribute("user");
	    String select = request.getParameter("pageChange");
	    
	    String changePwd = (String) request.getSession().getAttribute("changePwd");
	    
	    if (user == null) {
			//request.getSession().invalidate();
	    	response.sendRedirect("logout");
	    } else if (changePwd != null && changePwd.equals("changePwd")) {
			//request.getSession().invalidate();
	    	response.sendRedirect("logout");
	    } else {
	    	request.getSession().setAttribute("starter", "starter");
	    	ArrayList<Object> screenList = user.getScreenList(); // 스크린 번호 리스트
			if (select == null) {
		    	if (user.getRoleId().equals("1")) select = "view/user/userList.jsp";
		    	else select = "view/dict/taxonomyList.jsp";
		    }	
	    }
	%>
		
	<script type="text/javascript">
	
window.timer = null;
$(document).ready(function(){

	$("body").on("mousemove keydown", function() {
		window.lastTime = new Date();
	});

	//checkSession();
	
	function checkSession () {
		$.ajax({
			url: 'sm/checkSn',
			type : 'POST',
        	contentType: 'application/json; charset=utf-8',
			//data: JSON.stringify({userId: userId}),
			success: function(res) {
				if (res.status) return false;
				var prefix = "";
				if (res.loginIp) prefix = res.loginIp + "에서 ";
				
				alert(prefix + "중복 로그인 되어 로그아웃 됩니다.");
				window.location.href = 'logout';
			}, 
			error: function(res) {
				alert("세션 검증에 실패하여 로그아웃 됩니다.");
				window.location.href = 'logout';
			},
			complete: function(){
				if (window.timer) clearInterval(window.timer);
				window.timer = setInterval(checkSession, 1000);
			}
		});
	}
	
	window.lastTime = new Date();
	checkTime();

	function checkTime() {
		
		if (window.actTimer) clearInterval(window.actTimer);
		var curTime = new Date();
		var subtrs  = Math.ceil((curTime - lastTime) / 1000);
		
		if (subtrs > 1800) {
			alert('30분 이상 응답이 없어 자동 로그아웃 됩니다');
			location.href = "logout";
			return false;
		}
		window.actTimer = setInterval(checkTime, 1000);
	}
	
});
	</script>
	<style>
    html, body { 
	    height:100%; 
	    margin:0px; 
    }
    
    .header, .footer {
    	width: 100%;
    	height: 10%;
    }
    
	.menu_container {
		width:150px; 
		height:100%; 
		border-right:1px solid #bbbbbb;
		text-align:center;
		padding:10px;
	}
	
	.menu_row {
		width:100%;
		margin-top: 5px;
		text-align: left;
		padding-left: 25px;
	}
	</style>
	
	<script>
	var myUrl = "http://localhost:8180/";
	/* var myUrl = "http://13.209.38.43:8180/"; */
	
	//FILE UPLOAD
	$('#import').ajaxForm({
        beforeSubmit: function (arr) {
            for (var i in arr) {
                if (arr[i].name == 'file' && arr[i].value == '') {
                    console.log('choose file');
                    return false;
                }
                console.log('upload!')
            }
        },
        error: function () {
            console.log('error');
            alert("에러가 발생하였습니다.")
        },
        success: function (response) {
            console.log(response);
            response  = JSON.parse(response);
            var total = response.result[response.result.length - 2];
            var result = response.result[response.result.length - 1];
            alert("총" + total + "건 중 " + result + "건 처리");
            location.reload();   
        }
    });
	
	</script>
	
</head>
<body>
    <table width="100%" height="100%">
        <tr>
            <td colspan="2" class="header"><jsp:include page="view/main-header.jsp" flush="false" /></td>
        </tr>

        <tr>
            <td class="sidebar"><jsp:include page="view/main-sidebar-left.jsp" flush="false" /></td>
            <td width="100%">
            <% if (select != null) { %>
            	<jsp:include page="<%=select%>" flush="false" />
            <% } %>
            </td>
        </tr>
 
        <tr>
            <td colspan="2" class="footer">
            <% if (user != null) { %>
            	<jsp:include page="view/main-footer.jsp" flush="false" />
            <% } %>
            </td>
        </tr>
    </table>
</body>
</html>