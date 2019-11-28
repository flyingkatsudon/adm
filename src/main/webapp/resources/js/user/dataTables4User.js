$(document).ready(function() {
	
	var optDept  = [];
	var optTitle = [];
	var optStatus = [];
	
	var editor   = null;
	
	function onSetMeta(data) {
		
		for (var i = 0; data.dept && i < data.dept.length; i++) {
			optDept.push({
				label: data.dept[i].userDeptName,
				value: data.dept[i].userDeptCd
			});
		}
		
		for (var i = 0; data.title && i < data.title.length; i++) {
			optTitle.push({
				label: data.title[i].userTitleName,
				value: data.title[i].userTitleCd
			});
		}
		
		for (var i = 0; data.status && i < data.status.length; i++) {
			optStatus.push({
				label: data.status[i].userStatusName,
				value: data.status[i].userStatusCd
			});
		}
		
		
		editor = new $.fn.dataTable.Editor({
	    	
	    	ajax: function ( method, url, data, success, error ) {
	    		
	    		$.ajax({
					url : myUrl + "adm/user/editUser",
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
	    	
	        table: "#user",
	        fields: [ {
	            	type: "select",
	                label: "회사명: ",
	                name: "businessCode",
	                options: [
	                	{label: "신한은행", value: "SH"},
	                	{label: "신한카드", value: "LC"},
	                	{label: "금융투자", value: "GS"},
	                	{label: "신한생명", value: "SL"},
	                	{label: "신한DS",  value: "SY"}
	                ]
	            }, {
	                label: "로그인ID:",
	                name: "userId",
	                fieldInfo: "필수 입력란입니다."
	            }, {
	                label: "성명:",
	                name: "username"
	            }, {
	            	type: "select",
	                label: "직급:",
	                name: "userTitleCd",
	                options: optTitle
	            }, {
	            	type: "select",
	                label: "부서:",
	                name: "userDeptCd",
	                options: optDept
	            }, {
	            	type: "select",
	                label: "사용자 현재상태:",
	                name: "userStatus",
	                options: optStatus 
	            }, {
	            	type: "select",
	                label: "삭제여부:",
	                name: "delCd",      
	                options: [
	                	{label: "N",  value: "N"},
	                	{label: "Y",  value: "X"}
	                ]
	            }, {
	                label: "사용자 유효기간 시작일:",
	                name: "userStartDate",
	                fieldInfo: "입력예: 1999-01-01"
	            }, {
	                label: "사용자 유효기간 종료일:",
	                name: "userEndDate",
	                fieldInfo: "입력예: 1999-01-01"
	            }
	        ]        
	    });
		window.curEditor = editor;
		onRequestAllUser();
	}
	
	$.ajax({
		url : myUrl + "adm/user/getUserMeta",
		type: "GET",
		success: function(res, b, c) {
			res = JSON.parse(res);
			onSetMeta(res);
		},
		error: function(xhr, b, c) {
			alert("데이터 처리에 오류가 발생했습니다.");
		}
	});
    
    function onRequestAllUser() {
    	$.ajax({
			url : myUrl + "adm/user/selectAllUser",
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
    
    	var curTable = $('#user').DataTable( {
    	    dom: "Bfrtip",
    	    data :data,
    	    columnDefs: [
    	        {
    	            targets: [ 13 ],
    	            orderData: [ 13, 14 ]
    	        },
    	        {
    	            targets: [ 16 ],
    	            orderData: [ 16, 17 ]
    	        },
    	        {
    	        	render: function (data, type, row) {
    	        		return row.businessCode + data;
    	        	},
    	        	targets: 1
    	        }
    	    ],
    	    columns: [
    	        {
    	            data: null,
    	            defaultContent: '',
    	            className: 'select-checkbox',
    	            orderable: false
    	        },
    	        { data: "userId" },
    	        { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    return "";      
                } },
    	        { data: "username" },
    	        { data: "userTitleName" },
    	        { data: "userDeptName" },
    	        { data: "userStatusName" },
    	        { data: "delCd" },
    	        { data: "userLastDate" },
    	        { data: "userStartDate" },
    	        { data: "userEndDate" },
    	        { data: "rbacPolicy" },
    	        { data: "userStateInfo" },            
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
    	        //{ extend: "edit",   editor: editor, formTitle: '수정', formButtons: "수정" },
    	    	{ extend: "edit",   
    	    	  editor: editor,
    	    	  formTitle: '수정',
    	    	  formButtons: [
    	    		  { 
    	    			  label: '초기화', fn: function() { 
    	    				  //this.field('userId').val();
    	    				  //this.field('businesscode').val();
    	    				  this.submit(null, null, function(data) {
    	    					  data.action = "initUserStat";    	    					  
    	    				  	  }, false	  
    	    				  );    	    				  
    	    			  }  
    	    		  }, '수정'
    	    	  ] 
    	    	},
    	        { extend: "remove", editor: editor, formTitle: '삭제', formMessage: "데이터를 삭제하시겠습니까?", formButtons: "삭제" },
    	        { extend: 'collection', text: 'Export', buttons: ['excel'] }
    	    ]
    	}); 
    	
        editor.on('onInitCreate', function () {
        	editor.enable('userId');
        	editor.hide('delCd');
        	editor.enable('businessCode');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.disable('userId');
        	editor.disable('businessCode');
        	editor.show('delCd');	
    	});
        
        editor.on( 'onOpen', function () {
        	// Get the 'input' element from the 'code' input field and set an attribute on it
        	$( 'input', this.node( 'userId' ) ).attr( 'maxLength', 10 ).attr('hname','유저아이디').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'username' ) ).attr( 'maxLength', 4 ).attr('hname','성명').attr('types','CHAR').attr('CHKREQ', 'false');
        	$( 'input', this.node( 'userStartDate' ) ).attr( 'maxLength', 10 ).attr('hname','사용자 시작일').attr('types','DATE').attr('CHKREQ', 'false');
        	$( 'input', this.node( 'userEndDate' ) ).attr( 'maxLength', 10 ).attr('hname','사용자 종료일').attr('types','DATE').attr('CHKREQ', 'false');
        }); 
               
        editor.on( 'preSubmit', function ( e, o, action ) {
        	var userId = this.field('userId');
        	var username = this.field('username');
        	        	
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}
        	
            
        	if ((action == "create") && ( userId.val().length > 10))
	        	userId.error("사번은 최대 10자리 까지입니다.");		  
            
        	if ( this.inError() ) return false;
        });
              
        window.curTable = curTable;
    }	
    
} );