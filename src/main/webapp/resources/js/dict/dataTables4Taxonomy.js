$(document).ready(function() {
	
	var editor   = null;
	
    editor = new $.fn.dataTable.Editor( {
    	
    	ajax: function ( method, url, data, success, error ) {
    		
    		$.ajax({
				url : myUrl + "adm/taxo/editTaxonomy",
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
        table: "#taxonomy",
        fields: [ {
		            label: "인덱스",
		            name: "taxIdx",
        		}, {
		            label: "텍사노미아이디:",
		            name: "taxId",
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
		            label: "버전:",
		            name: "version",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "레벨1:",
		            name: "level1",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "레벨2:",
		            name: "level2",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "레벨3:",
		            name: "level3",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "레벨4:",
		            name: "level4",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "대표키워드:",
		            name: "refKeyword",
		            fieldInfo: "필수 입력란입니다."
		        }, {
		            label: "텍사노미키워드:",
		            name: "keyword",
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
	                label: "분류설명:",
	                name: "description",
	                fieldInfo: "필수 입력란입니다."
	            }
	           ]
    } );
    
    window.curEditor = editor;
	onRequestAllTaxonomy();
    
    function onRequestAllTaxonomy() {
    	$.ajax({
			url : myUrl + "adm/taxo/selectAllTaxonomy",
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
        var curTable = $('#taxonomy').DataTable({
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
                { data: "taxIdx" },
                { data: "taxId" },
                { data: 'businessCode', render: function (data, type, row) {
                    if(data == "SH") return "신한은행";
                    if(data == "LC") return "신한카드";
                    if(data == "GS") return "신한금투";
                    if(data == "SL") return "신한생명";
                    if(data == "SY") return "신한DS";
                    if(data == "CM") return "공통";
                    return "";      
                } },
                { data: "version", className: "editable" },
                { data: "level1", className: "editable" },
                { data: "level2", className: "editable" },
                { data: "level3", className: "editable" },
                { data: "level4", className: "editable" },
                { data: "refKeyword", className: "editable" },
                { data: "keyword", className: "editable" },
                { data: "delCd" },
                { data: "description" },
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
        
        editor.on('onInitCreate', function (e, data, action) {
        	editor.hide('taxIdx');
        	editor.hide('delCd');
    	});
    	
        editor.on('onInitEdit', function () {
        	editor.hide('taxIdx');
        	editor.show('delCd');
    	});
        
        editor.on( 'onOpen', function () {
        	$( 'input', this.node( 'taxId' ) ).attr( 'maxLength', 50 ).attr('hname','텍사노미아이디').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'version' ) ).attr( 'maxLength', 5 ).attr('hname','버전').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'level1' ) ).attr( 'maxLength', 50 ).attr('hname','레벨1').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'level2' ) ).attr( 'maxLength', 50 ).attr('hname','레벨2').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'level3' ) ).attr( 'maxLength', 50 ).attr('hname','레벨3').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'level4' ) ).attr( 'maxLength', 50 ).attr('hname','레벨4').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'refKeyword' ) ).attr( 'maxLength', 30 ).attr('hname','대표키워드').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'keyword' ) ).attr( 'maxLength', 30 ).attr('hname','텍사노미키워드').attr('types','').attr('CHKREQ', 'true');
        	$( 'input', this.node( 'description' ) ).attr( 'maxLength', 100 ).attr('hname','분류설명').attr('types','').attr('CHKREQ', 'true');
        	
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
        
        editor.on('preSubmit', function (e, o, action) {       	
        	if ((action=="create") || (action=="edit")) {
        		var cForm = document.getElementById("subForm");
        		if (!validate(cForm)) return false;
        	}
        	
        	/*// PK
        	var taxId = this.field("taxId").val();
        	var businessCode = this.field("businessCode").val();
        	var version = this.field("version").val();
        	
        	var lv1 = this.field("level1").val();
        	var lv2 = this.field("level2").val();
        	var lv3 = this.field("level3").val();
        	var lv4 = this.field("level4").val();
        	
        	if (!taxId) {
        		this.field("taxId").error("필수입력 값입니다");
        		return false;
        	}
        	
        	if (!businessCode) {
        		this.field("businessCode").error("필수입력 값입니다");
        		return false;
        	}
        	if (!version) {
        		this.field("version").error("필수입력 값입니다");
        		return false;
        	}
        	
        	if (lv4) {
        		if (!lv1 || !lv2 || !lv3) {
        			this.field("level1").error("값을 입력해주세요");
        			this.field("level2").error("값을 입력해주세요");	
        			this.field("level3").error("값을 입력해주세요");
        			return false;
        		}
        	}
        	
        	if (lv3) {
	    		if (!lv1 || !lv2) {
	    			this.field("level1").error("값을 입력해주세요");
	    			this.field("level2").error("값을 입력해주세요");	
	    			return false;
	    		}
        	}
        	
        	if (lv2) {
	    		if (!lv1) {
	    			this.field("level1").error("값을 입력해주세요");
	    			return false;
	    		}
        	}
        	
        	if (!lv1) {
        		this.field("level1").error("값을 입력해주세요");
        		return false;
        	}*/
        	
        });
        
        /*
        $( 'input', editor.field( 'level1' ).node() ).on( 'change', function () {
            if ( editor.field( 'level1' ).val() == '' ) {
            	editor.field('level2').val('')
            	editor.field('level3').val('')
            	editor.field('level4').val('')
            	
            	editor.disable( 'level2' );
                editor.disable( 'level3' );
                editor.disable( 'level4' );  
            }
            
            if ( editor.field( 'level1' ).val() != '' ) {
                editor.enable( 'level2' );
            }   
        });
      
        $( 'input', editor.field( 'level2' ).node() ).on( 'change', function () {
            if ( editor.field( 'level2' ).val() != '' ) {
                editor.enable( 'level3' );
            }
        });
        
        $( 'input', editor.field( 'level3' ).node() ).on( 'change', function () {
            if ( editor.field( 'level3' ).val() != '' ) {
                editor.enable( 'level4' );
            }
        });
  */
    
        window.curTable = curTable;
    }
    

 	
} );