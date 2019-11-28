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
</head>
<body>
	<div class="tableTitle">형태소 사전 정보 테이블</div>
	
	<table id="tknDict" class="display" cellspacing="0" width="100%">
        <thead>
            <tr>
                <!-- <th></th>
                <th>word_id</th>
                <th>word</th>
                <th>business_code</th>
                <th>left_idx</th>
                <th>right_idx</th>
                <th>cost</th>
                <th>pos_info</th>
                <th>ner_info</th>
                <th>fin_syllable</th>
                <th>alias_list</th>
                <th>cmpnd_info</th>
                <th>add_info1</th>
                <th>add_info2</th>
                <th>cmpnd_summary</th>
                <th>cmpnd_detail</th>
                <th>version</th>
                <th>load_date</th>
                <th>update_date</th> -->
                <th></th>
                <th>단어 ID</th>
                <th>단어</th>
                <th>회사명</th>
               <!--  <th>좌향인덱스</th>
                <th>우향인덱스</th>
                <th>코스트</th> -->
                <th>품사 정보</th>
                <th>개체명 정보</th>
                <!-- <th>종음절 정보</th> -->
                <th>alias 리스트</th>
                <th>합성 여부</th>
                <th>추가 정보1</th>
                <th>추가 정보2</th>
               <!--  <th>합성 정보 요약</th> -->
                <th>합성 정보 상세</th>
                <th>버전</th>
                <th>적재일자</th> <!-- load_date  -->
                <th>적재시간</th> <!-- load_time  -->
                <th>적재자명</th> <!-- load_name  -->
                <th>수정일자</th> <!-- update_date  -->
                <th>수정시간</th> <!-- update_time  -->
                <th>수정자명</th> <!-- update_name  -->
            </tr>
        </thead>
    </table>
    
    <div class="fileUpload">
		<form id="import" action="/adm/common/import" method="post" enctype="multipart/form-data">
			엑셀 INSERT<input multiple="multiple" type="file" name="file" /><input type="hidden" name="type" value="dict" />
	        <input type="submit" value="Upload"/>
    	</form>
	</div>
    
    <script src="resources/js/dict/dataTables4TknDict.js"></script>
</body>
</html>