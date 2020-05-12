function getParamFromUrl(paramName){
	var resultParamValue = "";
	//location.search는 url에서 ?를 찾아내줌.
	var paramString = location.search.substr(1);
	var splitStringToArray = paramString.split("&");
	for (var i = 0; i < splitStringToArray.length; i++){
		var tempArray = splitStringToArray[i].split("=");
		if (tempArray[0] == paramName)
			resultParamValue = tempArray[1];
	}
	return resultParamValue;
}