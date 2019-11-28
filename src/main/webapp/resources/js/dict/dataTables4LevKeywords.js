$(document).ready(function() {
	var editor   = null;
	
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/lk/editLevKeywords",
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
        table: "#levKeywords",
        fields: [ 
	        		{
		                label: "인덱스:",
		                name: "seqKeyword"
	            	}, 
	            	{
	            		label: "레벨1:",
	            		name: "level1",
	            		fieldInfo: "최대 2자리: 필수 입력란입니다."
	            	}, 
	            	{
	            		label: "레벨2:",
	            		name: "level2",
	            		fieldInfo: "최대 2자리: 필수 입력란입니다."
	            	}, 
	            	{
	            		label: "레벨3:",
	            		name: "level3",
	            		fieldInfo: "최대 2자리: 필수 입력란입니다."
	            	}, 
	            	{
	            		label: "키워드1:",
	            		name: "keyword1"
	            	}, 
	            	{
	            		label: "키워드2:",
	            		name: "keyword2"
	            	}, 
	            	{
	            		label: "키워드3:",
	            		name: "keyword3"
	            	}, 
	            	{
	            		label: "키워드4:",
	            		name: "keyword4"
	            	}, 
	            	{
	            		label: "키워드5:",
	            		name: "keyword5"
	            	}, 
	            	{
	            		label: "키워드6:",
	            		name: "keyword6"
	            	}, 
	            	{
	            		label: "키워드7:",
	            		name: "keyword7"
	            	}, 
	            	{
	            		label: "키워드8:",
	            		name: "keyword8"
	            	}, 
	            	{
	            		label: "키워드9:",
	            		name: "keyword9"
	            	}, 
	            	{
	            		label: "키워드10:",
	            		name: "keyword10"
	            	}, 
	            	{
	            		label: "키워드11:",
	            		name: "keyword11"
	            	}, 
	            	{
	            		label: "키워드12:",
	            		name: "keyword12"
	            	}, 
	            	{
	            		label: "키워드13:",
	            		name: "keyword13"
	            	}, 
	            	{
	            		label: "키워드14:",
	            		name: "keyword14"
	            	}, 
	            	{
	            		label: "키워드15:",
	            		name: "keyword15"
	            	}, 
		            {
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
	onRequestAllLevelKeyword();
    
    function onRequestAllLevelKeyword() {
    	$.ajax({
			url : myUrl + "adm/lk/selectAllLevKeywords",
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
        var curTable = $('#levKeywords').DataTable({
            dom: "Bfrtip",
            data :data,
            //order: [[ 10, 'desc' ], [ 12, 'desc' ]],
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
                { data: "seqKeyword" },
                { data: "level1" },
                { data: "level2" },
                { data: "level3" },
                { data: "keyword1" },
                { data: "keyword2" },
                { data: "keyword3" },
                { data: "keyword4" },
                { data: "keyword5" },
                { data: "keyword6" },
                { data: "keyword7" },
                { data: "keyword8" },
                { data: "keyword9" },
                { data: "keyword10" },
                { data: "keyword11" },
                { data: "keyword12" },
                { data: "keyword13" },
                { data: "keyword14" },
                { data: "keyword15" },
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
        	editor.hide('seqKeyword');
        	editor.hide('delCd');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.hide('seqKeyword');
        	editor.show('delCd');
    	});
        
        editor.on( 'preSubmit', function ( e, data, action ) {
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