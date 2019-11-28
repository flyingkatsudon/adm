$(document).ready(function() {
	var editor   = null;
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/role/editRole",
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
        table: "#role",
        fields: [ {
        		label: "권한코드:",
                name: "roleId",
                fieldInfo: "최대 1자리: 필수 입력란입니다."
            }, {
                label: "권한명:",
                name: "roleName"
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
	onRequestAllRole();
    
    function onRequestAllRole() {
    	$.ajax({
			url : myUrl + "adm/role/selectAllRole",
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
        var curTable = $('#role').DataTable({
            dom: "Bfrtip",
            data :data,
            columnDefs: [
    	        {
    	            targets: [ 3 ],
    	            orderData: [ 3, 4 ]
    	        },
    	        {
    	            targets: [ 6 ],
    	            orderData: [ 6, 7 ]
    	        }
    	    ],
            columns: [
                {
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "roleId", className: "editable" },
                { data: "roleName", className: "editable" },
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
        	editor.hide('delCd');
        	editor.enable('roleId');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.disable('roleId');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'roleId' ) ).attr( 'maxLength', 1 ).attr('hname','권한코드').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'roleName' ) ).attr( 'maxLength', 10 ).attr('hname','권한코드').attr('types','').attr('CHKREQ', 'false');
        });
        
        editor.on( 'preSubmit', function ( e, data, action ) {
        	
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}  
        	
        	var d = data.data;
        	for (var el in d) {
        		for (var col in d[el]) {
        			if( col=='loadDate' || col=='loadTime' || col=='loadName' || col=='updateDate' || col=='updateTime' || col=='updateName' || col=='userId' ) break;
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