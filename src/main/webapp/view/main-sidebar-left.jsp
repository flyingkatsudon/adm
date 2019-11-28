<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

	<!-- Common -->
	<%@ include file="/view/common/common.jsp"%>

<body style="height:100%">
	<div class="menu_container" style="">
		<!-- Dictionary Info. -->
		
    	<sec:authorize access="hasAuthority('1') or hasAuthority('2')">
    		<div class="menu_title">사전 관리</div>		
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/ewdList.jsp" href="#">감정어 사전</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/dictWordList.jsp" href="#">형태소 사전</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/stopwordList.jsp" href="#">불용어 정보</a></div>
			
			<div class="menu_title">텍사노미 관리</div>	
			<div class="menu_row"><a id="taxonomyTree" href="#" class="go_btn" target-url="/view/dict/tree.jsp" href="#">텍사노미 트리구조</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/taxonomyList.jsp" href="#">문서 텍사노미 분류</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/docFilterList.jsp" href="#">문서정보 필터</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/crawlKeywordsList.jsp" href="#">크롤러 대상 키워드</a></div>
	    	<!-- <div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/trendKeywordsList.jsp" href="#">트렌드 키워드</a></div>
	    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/dict/levKeywordsList.jsp" href="#">은카생금 키워드</a></div> -->
    	 	
	    	<!-- User Info. -->
	    	<sec:authorize access="hasAuthority('1')">
	    	<div class="menu_title">사용자 관리</div>	
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/userList.jsp" href="#">유저정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/titleNameList.jsp" href="#">직급정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/deptNameList.jsp" href="#">부서정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/statusNameList.jsp" href="#">사용자 상태정보</a></div>
		    
		    <div class="menu_title">권한 관리</div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/roleList.jsp" href="#">권한정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/roleResList.jsp" href="#">권한 세부정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/userRoleList.jsp" href="#">사용자-권한 연결정보</a></div>
    			<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/screenMasterList.jsp" href="#">화면 마스터 정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/screenLinkList.jsp" href="#">화면-세부권한 연결정보</a></div>
		    
		     <div class="menu_title">기타</div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/screenLoginList.jsp" href="#">화면 로그인 정보</a></div>
		    	<div class="menu_row"><a href="#" class="go_btn" target-url="/view/user/screenArchiList.jsp" href="#">화면 레벨관리 정보</a></div>
		    	
		    </sec:authorize>
    	</sec:authorize>
	</div>
    
    <form id="submitForm" action="/adm/starter.jsp" method="post">
		<input type="hidden" name="pageChange" value=""/>
    </form>
    
 	<script type="text/javascript">
 	
		$(document).ready(function() {
 			
 			$(".go_btn").on("click", function() {
 				$url = $(this).attr("target-url");
 				if (!$url) return;
 				
 				$("input[name=pageChange]").val($url);
 				$("#submitForm").submit();
 			});
 		});
 		
 	</script>
</body>
