$(document).ready(function() {
	
	var optRole  = [];
	var optResType = [];
	
	var editor   = null;
	
	function onSetMeta(data) {
		for (var i = 0; data.role && i < data.role.length; i++) {
			optRole.push({
				label: data.role[i].roleName,
				value: data.role[i].roleId
			});
		}
		
		for (var i = 0; data.resType && i < data.resType.length; i++) {
			optResType.push({
				label: data.resType[i].resTypeName,
				value: data.resType[i].resType
			});
		}
				
		editor = new $.fn.dataTable.Editor({
	    	
	    	ajax: function ( method, url, data, success, error ) {
	    		
	    		$.ajax({
					url : myUrl + "adm/roleRes/editRoleRes",
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
	        table: "#roleRes",
	        fields: [ {
                	label: "인덱스:",
                	name: "roleResId"
            	}, {
	        		type: "select",
	                label: "권한명:",
	                name: "roleId",
	                options: optRole
	            }, {
	            	type: "select",
	                label: "세부권한:",
	                name: "resType",
	                options: optResType
	            },  {
	                label: "권한 부연설명:",
	                name: "auRbacText"
	            }, {
	            	type: "select",
	                label: "삭제여부:",
	                name: "delCd",
	                options: [
	                	{label: "N",  value: "N"},
	                	{label: "Y",  value: "X"}
	                ]
	            }
	        ]
	    });
		window.curEditor = editor;
		onRequestAllRoleRes();
	}
	
	$.ajax({
		url : myUrl + "adm/roleRes/getRoleResMeta",
		type: "GET",
		success: function(res, b, c) {
			res = JSON.parse(res);
			onSetMeta(res);
		},
		error: function(xhr, b, c) {
			alert("데이터 처리에 오류가 발생했습니다.");
		}
	});
    
    function onRequestAllRoleRes() {
    	$.ajax({
			url : myUrl + "adm/roleRes/selectAllRoleRes",
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
    
    	var curTable = $('#roleRes').DataTable( {
    	    dom: "Bfrtip",
    	    data :data,
    	    columnDefs: [
    	        {
    	            "targets": [ 1 ],
    	            "visible": false
    	        },
    	        {
    	            targets: [ 6 ],
    	            orderData: [ 6, 7 ]
    	        },
    	        {
    	            targets: [ 9 ],
    	            orderData: [ 9, 10 ]
    	        }
    	    ],
    	    columns: [
    	    	{
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "roleResId" },
                { data: "roleName" },
                { data: "resTypeName" },
                { data: "auRbacText" },
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
        	editor.hide('roleResId');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.disable('roleResId');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	// Get the 'input' element from the 'code' input field and set an attribute on it
        	$( 'input', this.node( 'auRbacText' ) ).attr( 'maxLength', 30 ).attr('hname','권한 부연설명').attr('types','').attr('CHKREQ', 'false');
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