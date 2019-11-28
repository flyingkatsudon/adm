$(document).ready(function() {
	
	columnMeta = {
			
	}
	var editor   = null;
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/archi/editScreenArchi",
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
        table: "#screenArchi",
        fields: [ {
        		label: "스크린 정보:",
                name: "screenNo",
                fieldInfo: "최대 3자리: 필수 입력란입니다."
            }, {
                label: "스크린 레벨계층 정보:",
                name: "screenArchi"
            },{
            	type: "select",
                label: "삭제여부:",
                name: "delCd",
                options: [ 	
                	{label: "N",  value: "N"},
                	{label: "Y",  value: "X"}
                ]
            }
        ]
    } );
    
    window.curEditor = editor;
	onRequestAllScreenArchi();
    
    function onRequestAllScreenArchi() {
    	$.ajax({
			url : myUrl + "adm/archi/selectAllScreenArchi",
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
        var curTable = $('#screenArchi').DataTable({
            dom: "Bfrtip",
            data :data,
            columnDefs: [
    	        {
    	            targets: [ 4 ],
    	            orderData: [ 4, 5 ]
    	        },
    	        {
    	            targets: [ 7 ],
    	            orderData: [ 7, 8 ]
    	        }
    	    ],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "screenNo" },
                { data: "screenArchi"}, 
                { data: "delCd" },
                { data: "loadDate" },
                { data: "loadTime" },
                { data: "loadName" },
                { data: "updateDate" },
                { data: "updateTime" },
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
        	editor.enable('screenNo');
        	editor.hide('delCd');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.disable('screenNo');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'screenNo' ) ).attr( 'maxLength', 3 ).attr('hname','스크린 정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'screenArchi' ) ).attr( 'maxLength', 10 ).attr('hname','스크린 레벨계층 정보').attr('types','').attr('CHKREQ', 'false');
        });
       
        editor.on( 'preSubmit', function ( e, data, action ) {
        	
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}
        	
        	var d = data.data;
        	for (var el in d) {
        		for (var col in d[el]) {
        			if( col=='loadDate' || col=='loadTime' || col=='loadName' || col=='updateDate' || col=='updateTime' || col=='updateName' || col =='archiIdx' ) break;
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