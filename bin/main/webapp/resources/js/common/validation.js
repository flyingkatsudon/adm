
/***  
 * @Script Name : validation.js
 * @Description : 공통 자바스크립트 
 * @Modification Information  
 * @
 * @  수정일      수정자       수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2018.12.26   황인일       최초생성
 * 
 *  Copyright (C) 2018 by SAMSUNG SDS co.,Ltd. All right reserved.
 */

/// 에러메시지 포멧 정의 ///
var NO_BLANK = "{name+은는} 필수 입력값입니다.";
var NOT_VALID = "{name+이가} 올바르지 않습니다";
var NO_SPECIAL_CHAR = "{name}에 \" < > -- $ % ; | + .. \\\\  는 허용되지 않는 문자입니다.";
var specialCharPattern = /\"|\<|\>|\--|\$|\%|\;|\||\+|\.\.|\\\\/;
var orgClassName = null;
var ssn1 = null;


/**
 * form의 필수값, 입력 형태를 체크한다.
 *
 * @param  : form
 * @return : YYYYMMDD(년월일:yyyymmdd)
 */
function validate(form)
{
  try
  {
      for (var i = 0; i < form.elements.length; i++ ) 
      {
    	  
          var el = form.elements[i];
          var filter = el.getAttribute("FILTER");
          // 필수 항목 체크
          if (el.getAttribute("CHKREQ") == "true") 
          {
             var trimval = el.value == null ? null : el.value.trim();
              if (trimval == null || trimval == "")
                  return doError(el,NO_BLANK,"sel");
          }
          if(el.tagName.toUpperCase() != "OBJECT")
          {
        	  if( filter != "off" )
              {
        		  if(specialCharPattern.test(el.value)
        				  &&(el.getAttribute("NAME")!="_ETEExt_SEED_"
        					 &&el.getAttribute("NAME")!="INIpluginData"))
        			  return doError(el,NO_SPECIAL_CHAR);
              }
          }
          if(el.value.length != 0 && !checkValidation(el))
        	  return false;
      }

      return true;
  }
  
  catch(e)
  {
	  debugger;
      e.addLocation("validate", "resources/lib/validation/validation.js"); 
      throw e;
  }
  
  
}

function doError(el,type,action) 
{
  var pattern = /{([a-zA-Z0-9_]+)\+?([가-힝]{2})?}/;                    
  var name = (hname = el.getAttribute("HNAME")) ? hname : el.getAttribute("NAME");
  pattern.exec(type);
  var tail = (RegExp.$2) ? josa(eval(RegExp.$1),RegExp.$2) : "";
  alert(type.replace(pattern,eval(RegExp.$1) + tail));
  /*
  if (action == "sel") 
      el.select();
  else if (action == "del")
      el.value = "";
  */
  el.focus();
  return false;
}


function josa(str,tail) 
{
  return (str.hasFinalConsonant()) ? tail.substring(0,1) : tail.substring(1,2);
}


/*
 * object의 Validation 체크와 포맷을 맞춤
 *
 * Parameter : object
 * Return    : boolean
 */
function checkValidation(obj)
{
	var name = "";
	
    try
    {
        objVal = trim(obj.value);

        switch(obj.getAttribute("types"))
        {
        	case "":
        		break;
        	
        	case null:
        		break;
            
        	case "DATE":    // 날짜
            	objVal = delDateDelim(objVal);
                if(isValidDate(objVal)) 
                    addDateDelim(objVal);
                else
                {
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var alertMsg  = "의 날짜 형식이 올바르지 않거나 유효하지 않습니다.\t\n";
                	
               		alert(name + alertMsg);
                	obj.focus();
                    return false;
                }
                break;

            case "DATEP":    // 날짜
            	objVal=delDateDelim(objVal);
                if(isValidDate(objVal))
                {
                	if (objVal - getCurrentDate()  > 0) {
                		alert("오늘 날짜 이후로 입력하실 수 없습니다.\t");
                		obj.focus();
                	    return false;
                	}
                    addDateDelim(objVal);
                }
                else
                {
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var alertMsg  = "의 날짜 형식이 올바르지 않거나 유효하지 않습니다.\t\n";
                	
               		alert(name + alertMsg);
                	obj.focus();
                    return false;
                }
                addDateDelim(objVal);
                break;

            case "TIME":    // 시간
                delTimeDelim(objVal);
                if(isValidTime(obj.value)) 
                    addTimeDelim(objVal);
                else
                {
            		name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
        			
            		var alertMsg  = "의 시간 입력이 잘못되었습니다.\t\n\n";
               		alert(name + alertMsg);
                	obj.focus();
                	
                    return false;
                }
                break;

            case "HCHR":    // 한글 2자 이상
                if(!isKorean(obj.value))              {   
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	
                	var alertMsg  = " 한글 2자 이상만 입력하실 수 있습니다.\t\t\n\n";
                	
               		alert(name + tail + alertMsg);
                	obj.focus();
                	
                	return false; }
                else if(getByteLength(obj.value) < 4) {
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	
                	var alertMsg  = " 한글 2자 이상만 입력하실 수 있습니다.\t\t\n\n";
                	
               		alert(name + tail + alertMsg);
                	obj.focus();
                	return false; }
                break;

            case "CHAR":    // 한글만
                if(!isKorean(obj.value))              {   
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	
                	var alertMsg  = " 한글만 입력하실 수 있습니다.\t\t\n\n";
                	
               		alert(name + tail + alertMsg);
                	obj.focus();
                 	
                	return false; }
                break;
                
            case "HCNO":    // 한글 또는 숫자
            	 if(!isKoreanOrNum(obj.value))              {   
                 	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                 	var tail = josa(name,'은는');
                 	
                 	var alertMsg  = " 한글과 숫자만 입력하실 수 있습니다.\t\t\n\n";
                 	
                		alert(name + tail + alertMsg);
                 	obj.focus();
                  	
                 	return false; }
                 break;

            case "NUMB":    // 숫자
                if(!isNum(obj.value))                 {
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	var alertMsg  = " 숫자만 입력하실 수 있습니다.\t\t\n\n";
                	
               		alert(name + tail + alertMsg);
                	obj.focus();
                	return false; 
                	}
                break;

            case "ECNO":    // 숫자와 영문만
                if(!isAlphaOrNum(objVal))             {  
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	var alertMsg  = " 영문과 숫자만 입력하실 수 있습니다.\t\n\n";

               		alert(name + tail + alertMsg);
                	obj.focus();
                	return false; }
                break;

            case "USER":    // 사용자ID
                if(!isUserID(objVal))             {  
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	var alertMsg  = " 사용자 계정 생성 시 사용하는 문자만 입력하실 수 있습니다.\t\n\n";

               		alert(name + tail + alertMsg);
                	obj.focus();
                	return false; }
                break;
            
            case "TELNO":    // 숫자, '-', '(', ')'
                if(!isTelNum(obj.value))              {   
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	var alertMsg  = " 숫자와 -, ( )만 입력하실 수 있습니다.\t\t\n\n";
            		
               		alert(name + tail + alertMsg);
                	obj.focus();
                	return false; } 
                break;

            case "EVAL_BOTH":    // 숫자와 영문 (반드시 혼용)
                if(!isAlphaNum(obj.value))            {   
                	
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                    var alertMsg  = " 영문과 숫자만 입력하실 수 있습니다.\t\n\n";

               		alert(name + tail + alertMsg);
                	obj.focus();
                	
                	return false; } 
                break;

    
            case "SSN1":    // 주민등록번호 앞자리
                if(!isNum(obj.value))          { doError(obj,NOT_VALID); return false; }
                else if(obj.value.length != 6) { doError(obj,NOT_VALID); return false; }
                ssn1 = obj.value;
                break;

            case "SSN2":    // 주민등록번호 뒷자리
                if(!isNum(obj.value))          { doError(obj,NOT_VALID); return false; }
                else if(obj.value.length != 7) { doError(obj,NOT_VALID); return false; }
                ssn = ssn1 + obj.value;
               /* if(!isValidSSN(ssn)) 
                { 
                	return false;
                }*/
                break; 

            case "SSN":     // 주민등록번호
                if(obj.value == "" || obj.value.length != 14 || !isNum(obj.value.substring(0, 6)) || obj.value.substring(6, 7) != '-' || !isNum(obj.value.substring(7, 14)))
                {
                	doError(obj,NOT_VALID);
                    return false;
                }
                break;
                
            case "URL":    // URL
                if(!isURL(obj.value))               {
                	
                	name = (hname = obj.getAttribute("HNAME")) ? hname : obj.getAttribute("NAME");
                	var tail = josa(name,'은는');
                	var alertMsg  = " URL 형식만 입력하실 수 있습니다.\t\t\n\n";
//	                	alertMsg += "입력범위 : 0 ~ 9, a ~ Z, ., =, /, ?, &, _ \n";

               		alert(name + tail + alertMsg);
                	obj.focus();
                	
                	return false; }
                break;                
        }
        return true;
    }
    catch(e)
    {
        e.addLocation("checkValidation", "resources/lib/validation/validation.js"); 
        throw e;
    }
}

/// 스트링 객체에 메소드 추가 ///
String.prototype.trim = function(str) 
{ 
    str = this != window ? this : str; 
    return str.replace(/^\s+/g,'').replace(/\s+$/g,''); 
}

String.prototype.hasFinalConsonant = function(str)
{
    str = this != window ? this : str; 
    var strTemp = str.substr(str.length-1);
    return ((strTemp.charCodeAt(0)-16)%28!=0);
}
    
String.prototype.bytes = function(str) 
{
    var len = 0;
    str = this != window ? this : str;
    for(j=0; j<str.length; j++) 
    {
        var chr = str.charAt(j);
        len += (chr.charCodeAt() > 128) ? 2 : 1
    }
    return len;
}