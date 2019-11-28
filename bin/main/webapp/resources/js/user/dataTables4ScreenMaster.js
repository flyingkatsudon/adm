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
		
		editor = new $.fn.dataTable.Editor({
	    	
	    	ajax: function ( method, url, data, success, error ) {
	    		
	    		$.ajax({
					url : myUrl + "adm/sm/editScreenMaster",
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
	        table: "#screenMaster",
	        fields: [ {
	                label: "스크린 정보:",
	                name: "screenNo",
	                fieldInfo: "최대 3자리: 필수 입력란입니다."
	            }, {
	                label: "스크린 관련 프로그램명:",
	                name: "screenProgramId"
	            }, {
	                label: "스크린 화면정보 내용:",
	                name: "screenLayoutTxt",
	            }, {
	            	type: "select",
	                label: "권한명:",
	                name: "roleId",
	                options: optRole
	            }, {
	            	type: "select",
	                label: "회사명: ",
	                name: "userCompanyCd",
	                options: [
	                	{label: "신한은행", value: "SH"},
	                	{label: "신한카드", value: "LC"},
	                	{label: "금융투자", value: "GS"},
	                	{label: "신한생명", value: "SL"},
	                	{label: "신한DS",  value: "SY"}
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
		onRequestAllScreenMaster();
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
    
    function onRequestAllScreenMaster() {
    	$.ajax({
			url : myUrl + "adm/sm/selectAllScreenMaster",
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
    
    	var curTable = $('#screenMaster').DataTable( {
    	    dom: "Bfrtip",
    	    data :data,
    	    columnDefs: [
    	        {
    	            targets: [ 7 ],
    	            orderData: [ 7, 8 ]
    	        },
    	        {
    	            targets: [ 10 ],
    	            orderData: [ 10, 11 ]
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
                { data: "screenProgramId" },
                { data: "screenLayoutTxt" },
                { data: "roleName" },
                { data: 'userCompanyCd', render: function (data, type, row) {
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
    	    	{ extend: "create", editor: editor, formTitle: '삽입', formButtons: "삽입" },
    	        { extend: "edit",   editor: editor, formTitle: '수정', formButtons: "수정" },
    	        { extend: "remove", editor: editor, formTitle: '삭제', formMessage: "데이터를 삭제하시겠습니까?", formButtons: "삭제"},
    	        { extend: 'collection', text: 'Export', buttons: ['excel'] }
    	    ]
    	}); 
        
        editor.on('onInitCreate', function () {
        	editor.hide('delCd');
        	editor.enable('screenNo');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.disable('screenNo');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'screenNo' ) ).attr( 'maxLength', 3 ).attr('hname','스크린 정보').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'screenProgramId' ) ).attr( 'maxLength', 50 ).attr('hname','스크린 프로그램명').attr('types','').attr('CHKREQ', 'false');
        	$( 'input', this.node( 'screenLayoutTxt' ) ).attr( 'maxLength', 30 ).attr('hname','스크린정보').attr('types','').attr('CHKREQ', 'false');
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