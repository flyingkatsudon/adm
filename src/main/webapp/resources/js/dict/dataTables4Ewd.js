$(document).ready(function() {
	var editor   = null;
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/ewd/editEwd",
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
        table: "#ewd",
        fields: [ {
		            label: "인덱스:",
		            name: "ewdIdx"
        		}, {
		            label: "유의어집합ID:",
		            name: "synsetId",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "단어ID:",
		            name: "wordId",
		            fieldInfo: "필수 입력란입니다."
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
		            label: "단어:",
		            name: "word",
		            fieldInfo: "필수 입력란입니다.",
		        }, {
		            label: "품사정보:",
		            name: "posInfo",
		            fieldInfo: "필수 입력란입니다.",
		        }, {
		            label: "속성정보:",
		            name: "attributeInfo",
		            fieldInfo: "필수 입력란입니다.",
		        }, {
		            label: "프로토타입 정보:",
		            name: "protoInfo",
		            fieldInfo: "숫자를 입력해주세요."
		        }, {
		            label: "도메인 정보:",
		            name: "domainInfo",
		            fieldInfo: "필수 입력란입니다.",
		        }, {
		            label: "온톨로지 정보:",
		            name: "ontologyInfo",
		            fieldInfo: "필수 입력란입니다.",
		        }, {
		            label: "극성:",
		            name: "polarity",
		            fieldInfo: "필수 입력란입니다. (최대 1자리)"
		        }, {
		            label: "기본값:",
		            name: "preVal",
		            fieldInfo: "필수 입력란입니다. (숫자입력)"
		        }, {
		            label: "가중치:",
		            name: "weight",
		            fieldInfo: "숫자를 입력해주세요.",
		            fieldInfo: "필수 입력란입니다. (숫자입력)"
		        }, {
		            label: "분포값:",
		            name: "zVal",
		            fieldInfo: "숫자를 입력해주세요.",
		            fieldInfo: "필수 입력란입니다. (숫자입력)"
		        }, {
		            label: "의미값:",
		            name: "sVal",
		            fieldInfo: "숫자를 입력해주세요.",
		            fieldInfo: "필수 입력란입니다. (숫자입력)"
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
	onRequestAllEwd();
    
    function onRequestAllEwd() {
    	$.ajax({
			url : myUrl + "adm/ewd/selectAllEwd",
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
        var curTable = $('#ewd').DataTable({
            dom: "Bfrtip",
            data :data,
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
                { data: "ewdIdx" },
                { data: "synsetId", className: "editable" },
                { data: "wordId", className: "editable" },
                { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    if(data == "CM") return "공통";
                    return "";      
                } },
               
                { data: "word", className: "editable" },
                { data: "posInfo", className: "editable" },
                { data: "attributeInfo", className: "editable" },
                { data: "protoInfo" },
                { data: "domainInfo", className: "editable" },
                { data: "ontologyInfo" },
                { data: "polarity" },
                { data: "preVal", className: "editable" },
                { data: "weight", className: "editable" },
                { data: "zVal", className: "editable" },
                { data: "sVal", className: "editable" },
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
        	editor.hide('ewdIdx');
        	editor.hide('delCd');
        	editor.enable('protoInfo');
        	editor.enable('preVal');
        	editor.enable('weight');
        	editor.enable('zVal');
        	editor.enable('sVal');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.hide('ewdIdx');
        	editor.disable('protoInfo');
        	editor.disable('preVal');
        	editor.disable('weight');
        	editor.disable('zVal');
        	editor.disable('sVal');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'synsetId' ) ).attr( 'maxLength', 10 ).attr('hname','유의어집합ID').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'wordId' ) ).attr( 'maxLength', 5 ).attr('hname','단어ID').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'word' ) ).attr( 'maxLength', 15 ).attr('hname','단어').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'posInfo' ) ).attr( 'maxLength', 50 ).attr('hname','품사정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'attributeInfo' ) ).attr( 'maxLength', 50 ).attr('hname','속성정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'protoInfo' ) ).attr( 'maxLength', 100 ).attr('hname','프로토타입 정보').attr('types','NUMB').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'domainInfo' ) ).attr( 'maxLength', 30 ).attr('hname','도메인 정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'ontologyInfo' ) ).attr( 'maxLength', 50 ).attr('hname','온톨로지 정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'polarity' ) ).attr( 'maxLength', 1 ).attr('hname','극성').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'preVal' ) ).attr( 'maxLength', 100 ).attr('hname','기본값').attr('types','NUMB').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'weight' ) ).attr( 'maxLength', 100 ).attr('hname','가중치').attr('types','NUMB').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'zVal' ) ).attr( 'maxLength', 100 ).attr('hname','분포값').attr('types','NUMB').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'sVal' ) ).attr( 'maxLength', 100  ).attr('hname','의미값').attr('types','NUMB').attr('CHKREQ', 'true');
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
        
        editor.on( 'preSubmit', function ( e, data, action ) {
        	
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}
        	
        	var d = data.data;
        	for (var el in d) {
        		for (var col in d[el]) {
        			if( col=='loadDate' || col=='loadTime' || col=='loadName' || col=='updateDate' || col=='updateTime' || col=='updateName' ) break;
        			var curCol    = d[el][col];
        			var curField  = this.field(col);
        			var filedInfo = curField.fieldInfo();
        			var curValue  = curField.val();
        			if (!filedInfo) continue;
        			
        			var maxLen = filedInfo.split(" ")[1].split("자리")[0];
    				maxLen = parseInt(maxLen);
    				 
    				if (typeof maxLen == "number" && maxLen && curValue.length > maxLen) {
    					var newValue = curField.val().substring(0, maxLen);
    					 this.field(col).val(newValue);
    					 d[el][col] = newValue;
    				}
        		}
        	}
        });
        

        window.curTable = curTable;
    }
    

 	
} );