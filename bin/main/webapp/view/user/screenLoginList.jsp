<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<!-- Datatables JS libraries import -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/dataTables.buttons.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
	<script src="resources/lib/dataTables/js/dataTables.editor.min.js"></script>
	
	<!-- Datatables CSS libraries import -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" />
	<link rel="stylesheet" href="https://cdn.datatables.net/buttons/1.5.4/css/buttons.dataTables.min.css" />
	<link rel="stylesheet" href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css" />
	<link rel="stylesheet" href="resources/lib/dataTables/css/editor.dataTables.min.css" type="text/css" />
	
	<!-- Extra libraries for Excel Export -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.html5.min.js"></script>
	<script src="https://cdn.datatables.net/buttons/1.5.4/js/buttons.print.min.js"></script>
	
</head>
<body>
<!-- Table Name: MT.Transaction_Screen_Login -->
	<div class="tableTitle">화면 로그인 정보 테이블</div>
	
	<table id="screenLogin" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>스크린 정보</th> <!-- screen_no -->
                <th>스크린 로그인사용자</th> <!-- screen_login_user -->
                <th>스크린 로그인 시간</th> <!-- screen_login_time -->
                <th>삭제여부</th> <!-- del_cd  -->
                <th>적재일자</th> <!-- 4. load_date -->
                <th>적재시간</th> <!-- load_time -->
                <th>적재자명</th> <!-- load_name -->
                <th>수정일자</th> <!-- 7. update_date -->
                <th>수정시간</th> <!-- update_time -->
                <th>수정자명</th> <!-- update_name -->
            </tr>
        </thead>
    </table>
    
    <!-- <div class="fileUpload">
		<form id="import" action="/adm/common/import" method="post" enctype="multipart/form-data">
			엑셀 인서트<input multiple="multiple" type="file" name="file" /><input type="hidden" name="type" value="user" />
	        <input type="submit" value="Upload"/>
    	</form>
	</div> -->
    
    <script src="resources/js/user/dataTables4ScreenLogin.js"></script>
</body>
</html>