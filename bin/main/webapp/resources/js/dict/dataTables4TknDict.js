//
// Pipelining function for DataTables. To be used to the `ajax` option of DataTables
//
$.fn.dataTable.pipeline = function ( opts ) {
    // Configuration options
    var conf = $.extend( {
        pages: 1,     // number of pages to cache
        url: myUrl+"adm/tkn/selectAllTknDict",      // script url
        data: null,   // function or object with parameters to send to the server
                      // matching how `ajax.data` works in DataTables
        method: 'GET' // Ajax HTTP method
    }, opts );
 
    // Private variables for storing the cache
    var cacheLower = -1;
    var cacheUpper = null;
    var cacheLastRequest = null;
    var cacheLastJson = null;
 
    return function ( request, drawCallback, settings ) {
        var ajax          = false;
        var requestStart  = request.start;
        var drawStart     = request.start;
        var requestLength = request.length;
        var requestEnd    = requestStart + requestLength;
         
        if ( settings.clearCache ) {
            // API requested that the cache be cleared
            ajax = true;
            settings.clearCache = false;
        }
        else if ( cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper ) {
            // outside cached data - need to make a request
            ajax = true;
        }
        else if ( JSON.stringify( request.order )   !== JSON.stringify( cacheLastRequest.order ) ||
                  JSON.stringify( request.columns ) !== JSON.stringify( cacheLastRequest.columns ) ||
                  JSON.stringify( request.search )  !== JSON.stringify( cacheLastRequest.search )
        ) {
            // properties changed (ordering, columns, searching)
            ajax = true;
        }
         
        // Store the request for checking next time around
        cacheLastRequest = $.extend( true, {}, request );
 
        if ( ajax ) {
            // Need data from the server
            if ( requestStart < cacheLower ) {
                requestStart = requestStart - (requestLength*(conf.pages-1));
 
                if ( requestStart < 0 ) {
                    requestStart = 0;
                }
            }
             
            cacheLower = requestStart;
            cacheUpper = requestStart + (requestLength * conf.pages);
 
            request.start = requestStart;
            request.length = requestLength*conf.pages;
 
            // Provide the same `data` options as DataTables.
            if ( typeof conf.data === 'function' ) {
                // As a function it is executed with the data object as an arg
                // for manipulation. If an object is returned, it is used as the
                // data object to submit
                var d = conf.data( request );
                if ( d ) {
                    $.extend( request, d );
                }
            }
            else if ( $.isPlainObject( conf.data ) ) {
                // As an object, the data given extends the default
                $.extend( request, conf.data );
            }
 
            settings.jqXHR = $.ajax( {
                "type":     conf.method,
                "url":      conf.url,
                "data":     request,
                "dataType": "json",
                "cache":    false,
                "success":  function ( json ) {
                    cacheLastJson = $.extend(true, {}, json);
 
                    if ( cacheLower != drawStart ) {
                        json.data.splice( 0, drawStart-cacheLower );
                    }
                    if ( requestLength >= -1 ) {
                        json.data.splice( requestLength, json.data.length );
                    }
                     
                    drawCallback( json );
                }
            } );
        }
        else {
            json = $.extend( true, {}, cacheLastJson );
            json.draw = request.draw; // Update the echo for each response
            json.data.splice( 0, requestStart-cacheLower );
            json.data.splice( requestLength, json.data.length );
 
            drawCallback(json);
        }
    }
};

$(document).ready(function() {
	
	// Editor initialization
    editor = new $.fn.dataTable.Editor( {
    	ajax: myUrl + "adm/tkn/editTknDict",
        type: "POST",
        table: "#tknDict",
        fields: [ {
                label: "wordId:",
                name: "wordId"
            }, {
                label: "word:",
                name: "word"
            }, {
            	type: "select",
                label: "회사명:",
                name: "businessCode",
                options: [
                	{label: "신한은행", value: "SH"},
                	{label: "신한카드", value: "LC"},
                	{label: "신한생명", value: "SL"},
                	{label: "신한금융투자", value: "GS"},
                	{label: "신한DS", value: "SY"},
                	{label: "공통", value: "CM"}
                ]
            }, {
                label: "leftIdx:",
                name: "leftIdx"
            }, {
                label: "rightIdx:",
                name: "rightIdx"
            }, {
                label: "cost:",
                name: "cost"
            }, {
                label: "posInfo:",
                name: "posInfo"
            }, {
                label: "nerInfo:",
                name: "nerInfo"
            }, {
                label: "finSyllable:",
                name: "finSyllable"
            }, {
                label: "aliasList:",
                name: "aliasList"
            }, {
                label: "cmpndInfo:",
                name: "cmpndInfo"
            }, {
                label: "addInfo1:",
                name: "addInfo1"
            }, {
                label: "addInfo2:",
                name: "addInfo2"
            }, {
                label: "cmpndSummary:",
                name: "cmpndSummary"
            }, {
                label: "cmpndDetail:",
                name: "cmpndDetail"
            }, {
                label: "version:",
                name: "version"
            },/*, {
                label: "load_date:",
                name: "load_date"
            }, {
                label: "update_date:",
                name: "update_date"
            }*/
        ]
    } );
 
    // Activate an inline edit on click of a table cell
    $('#tknDict').on( 'click', 'tbody td.editable', function (e) {
        editor.inline( this );
    } );
    
    
 	// DataTables initialization
    $('#tknDict').DataTable( {
    	processing: true,
        serverSide: true,
        dom: "Bfrtip",
        //ajax: myUrl + "adm/tkn/selectAllTknDict",
        ajax: $.fn.dataTable.pipeline( {
            url: myUrl+"adm/tkn/selectAllTknDict",
            pages: 1 // number of pages to cache
        } ),
        type: "GET",
        //order: [[ 1, 'asc' ]],
        columns: [
            {
                data: null,
                defaultContent: '',
                className: 'select-checkbox',
                orderable: false
            },
            { data: "wordId", className: "editable" },
            { data: "word", className: "editable" },
            { data: 'businessCode', render: function (data, type, row) {
                if(data == "SH") return "신한은행";
                if(data == "LC") return "신한카드";
                if(data == "GS") return "신한금투";
                if(data == "SL") return "신한생명";
                if(data == "DY") return "신한DS";
                if(data == "CM") return "공통";
                return "";      
            } },
            /*{ data: "leftIdx" },
            { data: "rightIdx" },
            { data: "cost" },*/
            { data: "posInfo", className: "editable" },
            { data: "nerInfo", className: "editable" },
            /*{ data: "finSyllable"},*/
            { data: "aliasList", className: "editable" },
            { data: "cmpndInfo", className: "editable" },
            { data: "addInfo1", className: "editable" },
            { data: "addInfo2", className: "editable" },
            { data: "cmpndSummary", className: "editable"},
           /* { data: "cmpndDetail"},*/
            { data: "version", className: "editable" },
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
    } );
} );