$(document).ready(function() {
	var editor   = null;
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/trend/editTrendKeywords",
				type: "POST",
				data: data,
				dataType: "json",
				contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
				success: function(res, b, c) {
					
					if (!res || !res.data || res.error) {
						alert("처리에 문제가 발생하였습니다.");
						success(res, b, c);
						curTable.draw();
						return false;
					}
					success(res, b, c);
					curEditor.close();
					curTable.clear().rows.add(res.data).draw();
				},
				error: function(xhr, b, c) {
					alert("데이터 처리에 오류가 발생했습니다.");
				}
			});
    	},
        table: "#trendKeywords",
        fields: [ {
	            label: "인덱스",
	            name: "trendKeywordsIdx",
			}, {
	        	type: "select",
	            label: "회사명:",
	            name: "businessCode",
	            fieldInfo: "필수 입력란입니다.",
	            options: [
	            	{label: "신한은행", value: "SH"},
	            	{label: "신한카드", value: "LC"},
	            	{label: "신한생명", value: "SL"},
	            	{label: "신한금융투자", value: "GS"},
	            	{label: "신한DS", value: "SY"},
	            	{label: "공통", value: "CM"}
	            ]
            },  {
                label: "키워드:",
                name: "keyword",
                fieldInfo: "필수 입력란입니다."
            }, {
                label: "등록자 정보:",
                name: "registrantInfo",
                fieldInfo: "필수 입력란입니다."
            }, {
            	type: "select",
                label: "삭제여부:",
                name: "delCd",
                options: [
                	{label: "N",  value: "N"},
                	{label: "Y",  value: "X"}
                ]
            }, {
                label: "버전:",
                name: "version",
                fieldInfo: "필수 입력란입니다."
            }
        ]
    } );
    
    window.curEditor = editor;
	onRequestAllTrendKeyword();
    
    function onRequestAllTrendKeyword() {
    	$.ajax({
			url : myUrl + "adm/trend/selectAllTrendKeywords",
	    	type: "GET",
	    	dataType: "json",
	        success: function (json) {
	        	
	        	onDrawTable(json.data);
	           
	        },
	        error: function (xhr, error, thrown) {
	            error( xhr, error, thrown );
	        }
    	});
    }
    
    function onDrawTable(data) {
    	// DataTables initialization
        var curTable = $('#trendKeywords').DataTable({
            dom: "Bfrtip",
            data :data,
            columnDefs: [
            	{targets: [1], visible: false}
            ],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "trendKeywordsIdx" },
                { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    if(data == "CM") return "공통";
                    return "";      
                } },
                { data: "keyword" },
                { data: "registrantInfo" },
                { data: "version" },
                { data: "delCd" },
                { data: "loadDate" },
                { data: "loadName" },
                { data: "updateDate" },
                { data: "updateName" }
            ],
            select: {
                style:    'os',
                selector: 'td:first-child'
            },
            buttons: [
            	{ extend: "create", editor: editor, formTitle: '삽입', formButtons: "삽입" },
                { extend: "edit",   editor: editor, formTitle: '수정', formButtons: "수정" },
                { extend: "remove", editor: editor, formTitle: '삭제', formMessage: "데이터를 삭제하시겠습니까?", formButtons: "삭제"},
                { extend: 'collection', text: 'Export', buttons: ['excel'] }
            ]
        });
        
        editor.on('onInitCreate', function () {
        	editor.hide('delCd');
        	editor.hide('trendKeywordsIdx');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.show('delCd');
        	editor.hide('trendKeywordsIdx');
    	});

        window.curTable = curTable;
    }
    

 	
} );