
$(document).ready(function() {
	$(".content").css('vertical-align', 'top');
	
	
	var dataset;
	$.ajax({
		url: myUrl + "adm/taxo/selectTaxonomyTree",
		type: "POST",
		//async: false,
		success: function(result){
			var data = JSON.parse(result)
		    var d =  data.rawdata;
	        var m   = {};

	        for (var i = 0; i < d.length; i++) {
	           if (!m[d[i].level1]) m[d[i].level1] = {};
	           if (!m[d[i].level1][d[i].level2]) m[d[i].level1][d[i].level2] = {};
	           if (!m[d[i].level1][d[i].level2][d[i].level3]) m[d[i].level1][d[i].level2][d[i].level3] = [];
	           //if (d[i].level4 == "-") continue;
	           m[d[i].level1][d[i].level2][d[i].level3].push(d[i].level4);
	        }
	        console.log(m);
	        
	        $wrap = $(".tree");
	        $root = $("<ul class='myUL'></ul>");
	        $cRoot = null;
	        
	        $wrap.append($root);
	        var runCnt = 0;
	        for (var lv1 in m) {
	        	
	        	$curNode = $("<li><span class='caret' style='font-size: 14px;'>" + lv1 + "</span></li>");
	        	$root.append($curNode);
	        	$cRoot = $("<ul class='nested'></ul>");
	        	$curNode.append($cRoot);
	        	for (var lv2 in m[lv1]) {
	        		$curNode2 = $("<li><span class='caret' style='font-size: 14px;'>" + lv2 + "</span></li>");
	        		$cRoot.append($curNode2);
	        		$c2Root = $("<ul class='nested'></ul>");
	        		$curNode2.append($c2Root);
	        		for (var lv3 in m[lv1][lv2]) {
	        			$curNode3 = $("<li><span class='caret' style='font-size: 14px;'>" + lv3 + "</span></li>");
	        			$c2Root.append($curNode3);
	        			$c3Root = $("<ul class='nested'></ul>");
		        		$curNode3.append($c3Root);
		        		var lv4List = m[lv1][lv2][lv3];
		        		for (var i = 0; i < lv4List.length; i++) {
		        			//$curNode4 = $("<li><span class='caret'>" + lv4List[i] + "</span></li>");
		        			$curNode4 = $("<li><span>" + lv4List[i] + "</span></li>");
		        			$c3Root.append($curNode4);
		        		}
	        		}
	        	}
	        }
	        
	    	var toggler = document.getElementsByClassName("caret");
	    	for (var i = 0; i < toggler.length; i++) {
	    	  toggler[i].addEventListener("click", function() {
	    		var tNode = this.parentElement.querySelector(".nested");
	    		if (!tNode) return;
	    		tNode.classList.toggle("active");
	    	    this.classList.toggle("caret-down");
	    	  });
	    	}
		},
		error: function(error) {
			alert("data error");
		}
	});
	

});


