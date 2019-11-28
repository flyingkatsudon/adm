$(document).ready(function() {
	var editor   = null;
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/stopword/editStopword",
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
        table: "#stopword",
        fields: [ {
	            label: "키워드Id:",
	            name: "keywordId"
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
	        }, {
	            label: "카테고리코드:",
	            name: "categoryCode",
	            fieldInfo: "필수 입력란입니다."
	        }, {
	            type: "select",
	        	label: "불용유형:",
	            name: "stopType",
	            fieldInfo: "필수 입력란입니다.",
	            options: [
	            	{label: "문서단위", value: "D"},
	            	{label: "분석단위", value: "A"},	            	
	            ]
	        }, {
	            label: "키워드:",
	            name: "keyword",
	            fieldInfo: "필수 입력란입니다.",
	        }, {
	            label: "등록자정보:",
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
	onRequestAllStopword();
    
    function onRequestAllStopword() {
    	$.ajax({
			url : myUrl + "adm/stopword/selectAllStopword",
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
        var curTable = $('#stopword').DataTable({
            dom: "Bfrtip",
            data :data,
            //order: [[ 1, 'asc' ]],
            columnDefs: [
            	{
            		targets: [1],
            		visible: false, 
            	}
            ],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "keywordId" },
                { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    if(data == "CM") return "공통";
                    return "";      
                }},
                { data: "categoryCode" },
                { data: "stopType", render: function (data, type, row) {
                    if(data == "D") return "문서단위";
                    if(data == "A") return "분석단위";
                    return "";      
                }},
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
        	editor.hide('keywordId');
        	editor.hide('delCd');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.hide('keywordId');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'categoryCode' ) ).attr( 'maxLength', 16 ).attr('hname','카테고리 코드').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'keyword' ) ).attr( 'maxLength', 30 ).attr('hname','키워드').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'registrantInfo' ) ).attr( 'maxLength', 20 ).attr('hname','등록자정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'version' ) ).attr( 'maxLength', 5 ).attr('hname','버전').attr('types','').attr('CHKREQ', 'true');
        	
        	var currentUserBusinessCode = $("input[name=businessCode]", document.userInfo).val();
        	
        	if (currentUserBusinessCode == "SH") {
        		//$("select#DTE_Field_businessCode option[value='SH']").remove(); 
            	$("select#DTE_Field_businessCode option[value='LC']").remove();
            	$("select#DTE_Field_businessCode option[value='GS']").remove();
            	$("select#DTE_Field_businessCode option[value='SL']").remove(); 
            	$("select#DTE_Field_businessCode option[value='SY']").remove();
        	}
        	else if (currentUserBusinessCode == "LC") {
        		$("select#DTE_Field_businessCode option[value='SH']").remove(); 
            	//$("select#DTE_Field_businessCode option[value='LC']").remove();
            	$("select#DTE_Field_businessCode option[value='GS']").remove();
            	$("select#DTE_Field_businessCode option[value='SL']").remove(); 
            	$("select#DTE_Field_businessCode option[value='SY']").remove();	
        	}
        	else if (currentUserBusinessCode == "GS") {
        		$("select#DTE_Field_businessCode option[value='SH']").remove(); 
            	$("select#DTE_Field_businessCode option[value='LC']").remove();
            	//$("select#DTE_Field_businessCode option[value='GS']").remove();
            	$("select#DTE_Field_businessCode option[value='SL']").remove(); 
            	$("select#DTE_Field_businessCode option[value='SY']").remove();	
        	}
        	else if (currentUserBusinessCode == "SL") {
        		$("select#DTE_Field_businessCode option[value='SH']").remove(); 
            	$("select#DTE_Field_businessCode option[value='LC']").remove();
            	$("select#DTE_Field_businessCode option[value='GS']").remove();
            	//$("select#DTE_Field_businessCode option[value='SL']").remove(); 
            	$("select#DTE_Field_businessCode option[value='SY']").remove();
        	}
        	
        });
        
        editor.on( 'preSubmit', function ( e, o, action ) {
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}
        });

        window.curTable = curTable;
    }
    

 	
} );