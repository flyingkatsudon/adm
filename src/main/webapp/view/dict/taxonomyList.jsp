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
	<div class="tableTitle">문서 텍사노미 분류 테이블</div>
	
	<table id="taxonomy" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <th></th>
                <th>인덱스</th>
                <th>텍사노미 아이디</th>
                <th>회사명</th>
                <th>버전</th>
                <th>레벨1</th>
                <th>레벨2</th>
                <th>레벨3</th>
                <th>레벨4</th>
                <th>대표 키워드</th>
                <th>텍사노미 키워드</th>
                <th>삭제여부</th>
                <th>분류설명</th>
                <th>적재일자</th> <!-- load_date  -->
                <th>적재자명</th> <!-- load_name  -->
                <th>수정일자</th> <!-- update_date  -->
                <th>수정자명</th> <!-- update_name  -->
            </tr>
        </thead>
    </table>
    <div class="fileUpload">
		<form id="import" action="/adm/common/import" method="post" enctype="multipart/form-data">
			엑셀 인서트<input multiple="multiple" type="file" name="file" /><input type="hidden" name="type" value="dict" />
	        <input type="submit" value="upload"/>
    	</form>
	</div>
    <script src="resources/js/dict/dataTables4Taxonomy.js"></script>
</body>
</html>