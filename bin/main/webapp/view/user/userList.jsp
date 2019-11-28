<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>유저정보 테이블</title>
	<!-- Datatables JS libraries import -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/dataTables.buttons.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
	<script src="resources/lib/dataTables/js/dataTables.editor.min.js"></script>
	
	<!-- Extra libraries for Excel Export -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.print.min.js"></script>
	
<%-- 	<!-- Common -->
	<%@ include file="/view/common/common.jsp"%> --%>
	
	<!-- Datatables CSS libraries import -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" />
	<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css" />
	<link rel="stylesheet" href="resources/lib/dataTables/css/editor.dataTables.min.css" type="text/css" />
		
	<style>
	.editable {
		min-width: 60px !important;
	}
	
	table th {
		min-width: 60px !important;
	}
	</style>
</head>
<body>
<!-- Table Name: cboard_demo.dashboard_user -->
	<div class="tableTitle">유저정보 테이블 </div>
	
	<table id="user" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>로그인ID</th> <!-- user_id  -->
                <th>회사명</th> <!-- business_code  -->
                <th>성명</th> <!-- user_name  -->
                <th>직급</th> <!-- user_title_cd  -->
                <th>부서</th> <!-- user_dept_cd  -->
                <th>현재상태</th> <!-- user_status  -->
                <th>삭제여부</th> <!-- del_cd  -->
                <th>마지막<br/>로그인 시간</th> <!-- user_last_date  -->
                <th>유효기간<br/>시작일</th> <!-- user_start_date  -->
                <th>유효기간<br/>종료일</th> <!-- user_end_date  -->
                <th>사용자 패스워드 시도</th> <!-- rbac_policy  -->
                <th>사용자 상태정보 정상여부</th> <!-- user_state_info  -->
                <th>적재일자</th> <!-- 13. load_date  -->
                <th>적재시간</th> <!-- load_time  -->
                <th>적재자명</th> <!-- load_name  -->
                <th>수정일자</th> <!-- 16. update_date  -->
                <th>수정시간</th> <!-- update_time  -->
                <th>수정자명</th> <!-- update_name  -->
            </tr>
        </thead>
    </table>
    
    <div class="fileUpload">
		<form id="import" action="/adm/common/import" method="post" enctype="multipart/form-data">
			엑셀 인서트<input multiple="multiple" type="file" name="file" /><input type="hidden" name="type" value="user" />
	        <input type="submit" value="Upload"/>
    	</form>
	</div>
    
    <script src="resources/js/user/dataTables4User.js"></script>
</body>
</html>