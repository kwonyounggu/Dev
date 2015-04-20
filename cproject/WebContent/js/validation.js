function validateEmail(email)
{
 //var re = /^(([^%#&+<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
 var re=/^([a-zA-Z0-9\._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
 return email.match(re)
}
function validatePassword(pwd)
{	//var re = /^[A-Za-z]\w{3,}[A-Za-z0-9]$/;
	var re = /^[A-Za-z0-9]\w{3,}[A-Za-z0-9]$/;//alpha-numeric combinations without any order
	return pwd.match(re);
}
function verify2Passwords(pwd,cpwd)
{	var is_ok=true;

	if(pwd==null || cpwd==null || !validatePassword(pwd) || !validatePassword(cpwd) ||pwd!=cpwd)
	{	
		is_ok=false;
	}
//	else if(pwd.value.length<4) 
//	{
//		is_ok=false;
//	}
	return is_ok;		
}
function removeSpecialChars(str)
{ 
	return str.replace(/[^a-zA-Z 0-9 가-힣 ():.?\[\]-]+/g,'');
}
function isNull(obj, msg) 
{
	if(obj.value == "") 
	{
		if(msg) 
		{
			alert(msg);
		}
		obj.focus();
		return true;
	}
	return false;
}

function isNumber(obj) 
{
	var str = obj.value;
	if(str.length == 0) return false;

	for(var i=0; i < str.length; i++) 
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9')) return false;
	
	return true;
}
function isPhoneNumber(obj) 
{
	var str = obj.value;
	if(str.length < 9) return false; //02-123-1234: minimum length

	if(!('0' <= str.charAt(0) && str.charAt(0) <= '9')) return false;
	for(var i=1; i < str.length; i++) 
		if(!(('0' <= str.charAt(i) && str.charAt(i) <= '9') || str.charAt(i)=='-')) return false;
	
	return true;
}
function isCellPhoneNumber(obj) 
{
	var str = obj.value;
	if(str.length == 0) return true; //02-123-1234: minimum length
	else if(str.length<9) return false;
	
	if(!('0' <= str.charAt(0) && str.charAt(0) <= '9')) return false;
	for(var i=1; i < str.length; i++) 
		if(!(('0' <= str.charAt(i) && str.charAt(i) <= '9') || str.charAt(i)=='-')) return false;
	
	return true;
}
function isSame(obj1, obj2) 
{
	//alert("obj1="+obj1.value+" obj2="+obj2.value);
	var str1 = obj1.value;
	var str2 = obj2.value;
	if(str1.length == 0 || str2.length == 0) return false;

	if(str1 == str2) return true;
	return false;
}

function isShort(obj, len, msg) 
{
	var str = obj.value;
	if(str.length < len) 
	{
		if(msg) 
		{
			alert(msg);	
		}
		obj.focus();
		obj.select();
		return true;
	}
	return false;
}

function isAlphabet(obj) 
{
	var str = obj.value;
	if(str.length == 0)
		return false;

	str = str.toUpperCase();
	for(var i=0; i < str.length; i++) {
		if(!('A' <= str.charAt(i) && str.charAt(i) <= 'Z'))
			return false;
	}
	return true;
}

function isAlphaNumeric(obj) 
{
	var str = obj.value;
	if(str.length == 0) return false;

	str = str.toUpperCase();
	for(var i=0; i < str.length; i++) {
		if(!(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') ||
			('0' <= str.charAt(i) && str.charAt(i) <= '9')))
			return false;
	}
	return true;
}
//check korean name without any space in the middle
function isKoreanName(name)
{
	 if(name.value.length==0 || name.value.length==1) return false;
	 for (var i=0; i<name.value.length; i++)
		 if ((name.value.charAt(i) >= "가") && (name.value.charAt(i) <= "힣")) continue; 
		 else return false;
	 return i<6 ? true: false;//up to 5 chars it's ok
}
//check english name including numbers unless the 1st char is a digit
function isEngName(obj) 
{
	var str = obj.value;
	if(str.length == 0) return false;

	str = str.toUpperCase();
	if(!('A' <= str.charAt(0) && str.charAt(0) <= 'Z')) return false;

	for(var i=1; i < str.length; i++) {
		if(!(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') ||
			('0' <= str.charAt(i) && str.charAt(i) <= '9')))
			return false;
	}
	return true;
}
function isID(obj) 
{
	var str = obj.value;
	if(str.length == 0)
		return false;

	str = str.toUpperCase();
	if(!('A' <= str.charAt(0) && str.charAt(0) <= 'Z')) return false;

	for(var i=1; i < str.length; i++) {
		if(!(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') ||
			('0' <= str.charAt(i) && str.charAt(i) <= '9') ||
			(str.charAt(i) == '_')||
			(str.charAt(i) == '.')))
			return false;
	}
	return true;
}
function is_postal_code(entry)
{ // CANADIAN CODES ONLY
	var strlen=entry.length; if(strlen!=6) {return false;}
	var entry=entry.toUpperCase();    // in case of lowercase characters
	// Check for legal characters in string - note index starts at zero
	if('ABCEGHJKLMNPRSTVXY'.indexOf(entry.charAt(0))<0) {return false;}
	if('0123456789'.indexOf(entry.charAt(1))<0) {return false;}
	if('ABCDEFGHJKLMNPQRSTUVWXYZ'.indexOf(entry.charAt(2))<0) {return false;}
	if('0123456789'.indexOf(entry.charAt(3))<0) {return false;}
	if('ABCDEFGHJKLMNPQRSTUVWXYZ'.indexOf(entry.charAt(4))<0) {return false;}
	if('0123456789'.indexOf(entry.charAt(5))<0) {return false;}
	return true;
}
function check_alphanumeric(cid) 
{	if(cid=="") return false;
	var alpha_numeric = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'+'1234567890'; 
		var pass=true;
	for (var i=0; i < cid.length; i++) 
	{	if (alpha_numeric.indexOf(cid.charAt(i)) < 0) 
		{	pass=false;
			break; 
		}
	}	
	return pass;
}
// Removes leading whitespaces
function LTrim( value ) {
	
	var re = /\s*((\S+\s*)*)/;
	return value.replace(re, "$1");
	
}

// Removes ending whitespaces
function RTrim( value ) {
	
	var re = /((\s*\S+)*)\s*/;
	return value.replace(re, "$1");
	
}

// Removes leading and ending whitespaces
function trim( value ) {
	
	return LTrim(RTrim(value));
	
}

function check_address(cid) 
{	cid=trim(cid);
	if(cid==""||cid.length<1) return false;
	var alpha_numeric = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'+'#-1234567890.'+' '; 
		var pass=true;
	for (var i=0; i < cid.length; i++) 
	{	if (alpha_numeric.indexOf(cid.charAt(i)) < 0) 
		{	pass=false;
			break; 
		}
	}	
	return pass;
}
/*****************************************
find a special character:
return fale if it contains a special char
return true if it is ok for name.
******************************************/
function check_name(name) 
{	
	if(name=="") return false;
	
	var non_name = "~!@#$%^&*()+=-[]\\\';,./{}|\":<>?"+"1234567890"; 
	var is_ok=true;
	for (var i=0; i < name.length; i++) 
	{	if (non_name.indexOf(name.charAt(i))!=-1) 
		{	is_ok=false;
			break; 
		}
	}	
	return is_ok;
}

function isEmail(obj) 
{
	var str = obj.value;
	if(str == "")
		return false;

	var i = str.indexOf("@");
	if(i < 0)
		return false;

	i = str.indexOf(".");
	if(i < 0)
		return false;
	
	return true;
}

function isCardNumber(obj)
{
	var str = obj.value;
	if(str.length != 16)
		return false;

	for(var i=0; i < 16; i++) 
	{
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9')) 
			return false;
	}
	return true;
}
