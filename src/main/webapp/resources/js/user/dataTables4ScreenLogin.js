$(document).ready(function() {
	
	// Editor initialization
    var editor = new $.fn.dataTable.Editor( {
    	ajax: myUrl + "adm/sl/editScreenLogin",
        type: "POST",
        table: "#screenLogin",
        fields: [ {
                label: "스크린 정보:",
                name: "screenNo"
            }, {
                label: "스크린 로그인 사용자:",
                name: "screenLoginUser"
            }, {
                label: "스크린 로그인 시간:",
                name: "screenLoginTime",
            }
        ]
    } );
 
    // Activate an inline edit on click of a table cell
    /*$('#screenLogin').on( 'click', 'tbody td.editable', function (e) {
    	editor.inline( this );
    } );*/
    

 	// DataTables initialization
    $('#screenLogin').DataTable( {
        dom: "Bfrtip",
        ajax: myUrl + "adm/sl/selectAllScreenLogin",
        type: "GET",
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
            { data: "screenLoginUser"  },
            { data: "screenLoginTime" },
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
            //{ extend: "edit",   editor: editor, formTitle: '수정', formButtons: "수정" },
            //{ extend: "remove", editor: editor, formTitle: '삭제', formMessage: "데이터를 삭제하시겠습니까?", formButtons: "삭제"}
        	{ extend: 'collection', text: 'Export', buttons: ['excel'] }
        ]
    }); 
} );