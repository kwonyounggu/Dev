//-----------------------------------------------------------------------------//
/** 스크립트 설명
  <input 에 들어가는 항목들
  hname="아이디"      : 항목의 한글이름(에러메시지를 보여줄때 등) 
  option="hangul"    : 특수한 검사를 수행할 옵션(email, phone, userid, hangul, engonly, jumin, bizno) 
  required           : 필수항목인지 여부를 나타내는 속성 
  maxbyte="10"       : maxlength와 달리 한글 2글자는 4바이트로 인식
*/
//-----------------------------------------------------------------------------//


//-------------------- 필드 컬러 정의 --------------------------------------------//
var FldDefaultColor;
var FildBackColor;
//-------------------- 필드 컬러 정의 --------------------------------------------//



//------------- 에러메시지 포멧 정의 --------------------------------------------//
var NO_BLANK        = "{name+은는} 필수항목입니다";
var NOT_VALID       = "{name+이가} 올바르지 않습니다";
var TOO_LONG        = "{name}의 길이가 초과되었습니다";
var NOT_PASS_CHECK  = "{name+은는} 입력하신 비밀번호와 동일하지 않습니다.";
var NOT_DOMAIN      = "{name+은는} 특수문자를 사용하실 수 없습니다. 도메인이름은 영문자 (a-z),숫자,하이픈(-) 만 사용가능합니다. 단,하이픈(-)은 문자사이에만 사용하실 수 있습니다.";
var NOT_USERID      = "{name+은는} 4자이상 12자 미만이어야 하고,\n 영문,숫자, _ 문자만 사용할 수 있습니다";



//------------- 특수 패턴 검사 함수 매핑(option 검사) ---------------------------//
var funcs             = new Array();
funcs['email']        = isValidEmail;
funcs['phone']        = isValidPhone;
funcs['userid']       = isValidUserid;
funcs['hangul']       = hasHangul;       //한글입력
funcs['number']       = isNumeric;       //숫자만 입력
funcs['float']        = isFloat;         //실수만 입력
funcs['engonly']      = alphaOnly;       //영문만 입력
funcs['engname']      = alphaName;       //영문이름
funcs['jumin']        = isValidJumin;    //주민번호
funcs['bizno']        = isValidBizNo;    //사업자등록번호
funcs['engaddress']   = notHangul; 
funcs['domain']       = isValidDomain;   //영문도메인
funcs['ipaddress']    = isValidIPaddr;   //ip주소
funcs['dnsname']      = isValidDNS;      //ip주소
funcs['domaincheck']  = isValidDomaina;  //도메인형식
funcs['banknumber']   = isValidBank;     //계좌번호형식


//------------- START 패턴 검사 함수들 ---------------------------------------//

function isValidDomaina(el) { 

  var pattern = /^([a-zA-Z0-9]{1,})\.?([a-zA-Z0-9]{1,})\.?([a-zA-Z0-9]{1,})$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isValidBank(el) {  

  var pattern = /^[0-9\-]+$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isValidDNS(el) { 

  var pattern = /^([a-zA-Z0-9]{1,50})\.?([a-zA-Z0-9]{1,50})\.?([a-zA-Z0-9]{1,50})$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isValidIPaddr(el) {  

  var pattern = /^([0-9]{1,3})\.?([0-9]{1,3})\.?([0-9]{1,3})\.?([0-9]{1,3})$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}


function isValidEmail(el) { 

  var pattern = /^[_a-zA-Z0-9-\.\-]+@[\.a-zA-Z0-9-\-]+\.[a-zA-Z\-]+$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isValidUserid(el) {     

  var pattern = /^[a-zA-Z]{1}[a-zA-Z0-9_\-]{3,11}$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_USERID);
}

function hasHangul(el) {

  var pattern = /^[가-힣]/;
  return (pattern.test(el.value)) ? true : doError(el,"{name+은는} 반드시 한글을 포함해야 합니다");
}
function isContainingKoreanChar(el) 
{

	  var pattern = /^[가-힣]/;
	  return pattern.test(el.value);
}
function notHangul(el) {//한글 입력 안됨

  var pattern = /[가-힣]/;
  return (!pattern.test(el.value)) ? true : doError(el,"{name+은는} 한글은 입력하실 수 없습니다");
}


function alphaOnly(el) {//반드시 영문만

  var pattern = /^[a-zA-Z]+$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function alphaName(el) {//영문과 띄어쓰기

  var pattern = /^[a-zA-Z ]+$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_VALID);
}

function isNumeric(el) {//반드시 숫자만

  var pattern = /^[0-9]+$/;
  return (pattern.test(el.value)) ? true : doError(el,"{name+은는} 반드시 숫자로만 입력해야 합니다");
}

function isFloat(el) {//실수형

  var pattern = /^[0-9.]+$/;
  return (pattern.test(el.value)) ? true : doError(el,"{name+은는} 반드시 실수형으로 입력해야 합니다");
}

function isValidDomain(el) {//영문 도메인 관련

  var pattern = /^[a-z|A-Z|0-9]+[a-z|A-Z|0-9|\-]+[a-z|A-Z|0-9]$/;
  return (pattern.test(el.value)) ? true : doError(el,NOT_DOMAIN);
}

function isValidHanDomain(el) {//한글 도메인 관련

  var pattern = /[가-힣]+[가-힣]$/;
  return (pattern.test(el.value)) ? true : doError(el,"{name+은는} 반드시 한글로 입력해야 합니다");
}

function isValidJumin(el) {

    var pattern = /^([0-9]{6})-?([0-9]{7})$/; 
  var num = el.value;
    if (!pattern.test(num)) return doError(el,NOT_VALID); 
    num = RegExp.$1 + RegExp.$2;

  var sum = 0;
  var last = num.charCodeAt(12) - 0x30;
  var bases = "234567892345";
  for (i=0; i<12; i++) {
    if (isNaN(num.substring(i,i+1))) return doError(el,NOT_VALID);
    sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
  }
  var mod = sum % 11;
  return ((11 - mod) % 10 == last) ? true : doError(el,NOT_VALID);

}

function isValidJumin2(el) {


    var pattern = /^([0-9]{6})([0-9]{7})$/; 
	var num = el.value;
    if (!pattern.test(num)) return false; 
    num = RegExp.$1 + RegExp.$2;

	var sum = 0;
	var last = num.charCodeAt(12) - 0x30;
	var bases = "234567892345";
	for (i=0; i<12; i++) {
		if (isNaN(num.substring(i,i+1))) return false;
		sum += (num.charCodeAt(i) - 0x30) * (bases.charCodeAt(i) - 0x30);
	}
	var mod = sum % 11;
	return ((11 - mod) % 10 == last) ? true : false;

}

function isValidBizNo(el) { 

    var pattern = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/; 
  var num = el.value;
    if (!pattern.test(num)) return doError(el,NOT_VALID); 
    num = RegExp.$1 + RegExp.$2 + RegExp.$3;
    var cVal = 0; 
    for (var i=0; i<8; i++) { 
        var cKeyNum = parseInt(((_tmp = i % 3) == 0) ? 1 : ( _tmp  == 1 ) ? 3 : 7); 
        cVal += (parseFloat(num.substring(i,i+1)) * cKeyNum) % 10; 
    } 
    var li_temp = parseFloat(num.substring(i,i+1)) * 5 + '0'; 
    cVal += parseFloat(li_temp.substring(0,1)) + parseFloat(li_temp.substring(1,2)); 
    return (parseInt(num.substring(9,10)) == 10-(cVal % 10)%10) ? true : doError(el,NOT_VALID); 
}

function isValidBizNo2(el) { 

    var pattern = /([0-9]{3})-?([0-9]{2})-?([0-9]{5})/; 
	var num = el.value;
    if (!pattern.test(num)) return false; 
    num = RegExp.$1 + RegExp.$2 + RegExp.$3;
    var cVal = 0; 
    for (var i=0; i<8; i++) { 
        var cKeyNum = parseInt(((_tmp = i % 3) == 0) ? 1 : ( _tmp  == 1 ) ? 3 : 7); 
        cVal += (parseFloat(num.substring(i,i+1)) * cKeyNum) % 10; 
    } 
    var li_temp = parseFloat(num.substring(i,i+1)) * 5 + '0'; 
    cVal += parseFloat(li_temp.substring(0,1)) + parseFloat(li_temp.substring(1,2)); 
    return (parseInt(num.substring(9,10)) == 10-(cVal % 10)%10) ? true : false;
}

function isValidPhone(el) {

  var pattern = /^([0]{1}[0-9]{1,2})-?([1-9]{1}[0-9]{2,3})-?([0-9]{4})$/;
  if (pattern.exec(el.value)) {
    if(RegExp.$1 == "010" || RegExp.$1 == "011" || RegExp.$1 == "016" || RegExp.$1 == "017" || RegExp.$1 == "018" || RegExp.$1 == "019") {
      el.value = RegExp.$1 + "-" + RegExp.$2 + "-" + RegExp.$3;
    }
    return true;
  } else {
    return doError(el,NOT_VALID);
  }
}

//- END 패턴 검사 함수들 -------------------------------------------------



// 에러메시지 처리
function doError(el,type,action) {
  var pattern = /{([a-zA-Z0-9_]+)\+?([가-힣]{2})?}/;
  var name = (hname = el.getAttribute("HNAME")) ? hname : el.getAttribute("NAME");
  pattern.exec(type);
  var tail = (RegExp.$2) ? josa(eval(RegExp.$1),RegExp.$2) : "";
  alert(type.replace(pattern,eval(RegExp.$1) + tail));
  
  if (action == "sel") {
    el.select();
  } else if (action == "del") {
    el.value = "";
  }        
  
  el.style.backgroundColor = "FFC352";
       
  el.focus();
  return false;
}

// 스트링 객체에 메소드 추가
String.prototype.trim = function(str) { 
  str = this != window ? this : str; 
  return str.replace(/^\s+/g,'').replace(/\s+$/g,''); 
}

String.prototype.hasFinalConsonant = function(str) {
  str = this != window ? this : str; 
  var strTemp = str.substr(str.length-1);
  return ((strTemp.charCodeAt(0)-16)%28!=0);
}

function josa(str,tail) {
  return (str.hasFinalConsonant()) ? tail.substring(0,1) : tail.substring(1,2);
}

// 필수항목에 빨간별표 체크하는 부분
function requiredCheck(form) {
   window.alert(form);
    for (var j = 0; j < form.elements.length; j++) {            
        if (form.elements[j].getAttribute("required") != null) {
            form.elements[j].className                = "required";
            form.elements[j].style.backgroundPosition = "top right";
            form.elements[j].style.backgroundRepeat   = "no-repeat";
        }
    }    
}

function validateForm(form) {

  for (checki = 0; checki < form.elements.length; checki++ ) { 
    if(form.elements[checki].type == "text") {
      form.elements[checki].style.backgroundColor = "white";        
    }
  }
        
  for (checki = 0; checki < form.elements.length; checki++ ) {        
    
    var el = form.elements[checki];
    el.value = el.value.trim();               

    if (el.getAttribute("REQUIRED") != null) {
      if (el.value == null || el.value == "") {
        return doError(el,NO_BLANK);
      }
    }

    if (el.getAttribute("MAXBYTE") != null && el.value != "") {
      var len = 0;
      for(j=0; j<el.value.length; j++) {
        var str = el.value.charAt(j);
        len += (str.charCodeAt() > 128) ? 2 : 1
      }
      if (len > parseInt(el.getAttribute("MAXBYTE"))) {
        maxbyte = el.getAttribute("MAXBYTE");
        return doError(el,TOO_LONG);
      }
    }
               
    if (el.getAttribute("OPTION") != null && el.value != "") {
      if (!funcs[el.getAttribute("OPTION")](el)) return false;
    }
    
  }
  
  return true;
}









// ==================================================================
// 천단위 콤마찍기

function validate_Price() {
	var obj;
	if(document.all) {
		obj = event.srcElement;
	} 
	else {
		obj = this;
	}

	if (event.keyCode != 39 && event.keyCode != 37) {
		obj.value = ValidatePrice(obj.value);
	}

	return true;
}

function ValidatePrice(value) {
	
	value = UnComma(value);
	return Comma(value);
}



function Comma(input) {   
	var inputString = new String;  
	var outputString = new String;  
	var counter = 0;  
	var decimalPoint = 0;  
	var end = 0;  
	var modval = 0;   
	
	inputString = input.toString();  
	outputString = '';  
	decimalPoint = inputString.indexOf('.', 1);   
	if(decimalPoint == -1) 
	{     
		end = inputString.length - (inputString.charAt(0)=='0' ? 1:0);     
		for (counter=1;counter <=inputString.length; counter++)     
		{        
			var modval =counter - Math.floor(counter/3)*3;        
			outputString = (modval==0 && counter <end ? ',' : '') + inputString.charAt(inputString.length - counter) + outputString;     
		}  
	}  
	else 
	{     
		end = decimalPoint - ( inputString.charAt(0)=='-' ? 1 :0);     
		for (counter=1; counter <= decimalPoint ; counter++)     
		{        
			outputString = (counter==0  && counter <end ? ',' : '') +  inputString.charAt(decimalPoint - counter) + outputString;     
		}     
		for (counter=decimalPoint; counter < decimalPoint+3; counter++)     
		{        
			outputString += inputString.charAt(counter);     
		} 
	}    
	return (outputString);
} 


function UnComma(input) {   
	var inputString = new String;   
	var outputString = new String;   
	var outputNumber = new Number;   
	var counter = 0;   
	
	if (input == '')   
	{	
		return 0;
	}   
	inputString=input;   
	outputString='';   
	for (counter=0;counter <inputString.length; counter++)   
	{      
		outputString += (inputString.charAt(counter) != ',' ?inputString.charAt(counter) : '');   
	}   
	outputNumber = parseFloat(outputString);   
	return (outputNumber);
}


// 천단위 콤마찍기
// ==================================================================







// ==================================================================
// 숫자만 입력 받기

function ValidateKeynumber() {
	if ((event.keyCode < 48) || (event.keyCode > 57) || event.keyCode == 46 ) event.returnValue = false;
}



// 숫자만 입력 받기
// ==================================================================







// ==================================================================
// 실수형만 입력 받기

function ValidateKeyfloat(obj)
{
	if ((event.keyCode < 48) || (event.keyCode > 57))
	{
		if(event.keyCode != 46)
		{
			event.returnValue = false;
		}
	}
}



// 실수형만 입력 받기
// ==================================================================







var init_true;

function validateInitialized(){

	init_true = true;
//window.alert(init_true);
	for (var i = 0; i < document.forms.length; i++) {

		// onsubmit 이벤트가 있다면 저장해 놓는다.
		if (document.forms[i].onsubmit) document.forms[i].oldsubmit = document.forms[i].onsubmit;

		document.forms[i].onsubmit = validateForm;

		for (var j = 0; j < document.forms[i].elements.length; j++){
			// 필수 입력일 경우는 * 배경이미지를 준다.
			document.forms[i].elements[j].style.backgroundColor = FldDefaultColor ? FldDefaultColor : 'white';

			if (document.forms[i].elements[j].getAttribute("REQUIRED") != null) {
        
				//window.alert(document.forms[i].elements[j].name);
				
				if(document.forms[i].elements[j].getAttribute("NOCOLOR") == null) {

					document.forms[i].elements[j].style.backgroundColor = FildBackColor ? FildBackColor : '#eeeeee';
					document.forms[i].elements[j].className = "formbox03 required";
					document.forms[i].elements[j].style.backgroundPosition = "top right";
					document.forms[i].elements[j].style.backgroundRepeat = "no-repeat";

				}

			}

		}

	}

}