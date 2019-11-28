$(document).ready(function() {
	
	var optRole  = [];
	var editor   = null;
	
	function onSetMeta(data) {
		
		for (var i = 0; data.role && i < data.role.length; i++) {
			optRole.push({
				label: data.role[i].roleName,
				value: data.role[i].roleId
			});
		}
		
		if (data.bCode != "SY") {
			optRole.splice(0, 1);
		}
		
		editor = new $.fn.dataTable.Editor({
	    	
	    	ajax: function ( method, url, data, success, error ) {
	    		
	    		$.ajax({
					url : myUrl + "adm/userRole/editUserRole",
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
	        table: "#userRole",
	        fields: [ {
                label: "인덱스",
                name: "userRoleId"
	            }, {
	                label: "사번:",
	                name: "userId"
	            }, {
	            	type: "select",
	                label: "권한명:",
	                name: "roleId",
	                options: optRole
	            }, {
	            	type: "select",
	                label: "회사명: ",
	                name: "businessCode",
	                options: [
	                	{label: "신한은행", value: "SH"},
	                	{label: "신한카드", value: "LC"},
	                	{label: "금융투자", value: "GS"},
	                	{label: "신한생명", value: "SL"},
	                	{label: "신한DS", value: "SY"}
	                ]
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
		onRequestAllUserRole();
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
    
    function onRequestAllUserRole() {
    	$.ajax({
			url : myUrl + "adm/userRole/selectAllUserRole",
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
    
    	var curTable = $('#userRole').DataTable( {
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
    	        },
    	        {
    	        	render: function (data, type, row) {
    	        		return row.businessCode + data;
    	        	},
    	        	targets: 2
    	        }
    	    ],
    	    columns: [
    	    	{
                    data: null,
                    defaultContent: '',
                    className: 'select-checkbox',
                    orderable: false
                },
                { data: "userRoleId" },
                { data: "userId" },
                { data: "roleName" },
                { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    return "";      
                } },
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
    	    	//{ extend: "create", editor: editor, formTitle: '삽입', formButtons: "삽입" },
    	        { extend: "edit",   editor: editor, formTitle: '수정', formButtons: "수정" },
    	        { extend: "remove", editor: editor, formTitle: '삭제', formMessage: "데이터를 삭제하시겠습니까?", formButtons: "삭제"},
    	        { extend: 'collection', text: 'Export', buttons: ['excel'] }
    	    ]
    	}); 
        
        editor.on('onInitCreate', function () {
        	//editor.hide('delCd');
        	editor.hide('userRoleId');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.hide('userRoleId');
        	editor.disable('businessCode');
        	editor.disable('userId');
        	//editor.show('delCd');
    	});
        
        editor.on( 'preSubmit', function ( e, data, action ) {
        	var businessCode = this.field( 'businessCode' ).val();
        	var roleId = this.field('roleId').val();
        	
        	if ( (businessCode != "SY") && (roleId == "1") ) {
        		alert("IT관리자는 신한DS만 가능합니다.")
        		return false;
        	}
        });

        window.curTable = curTable;
    }	
    
} );