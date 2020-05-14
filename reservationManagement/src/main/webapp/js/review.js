function getParamFromUrl(paramName) {
  var resultParamValue = "";
  //location.search는 url에서 ?를 찾아내줌.
  var paramString = location.search.substr(1);
  var splitedString = paramString.split("&");
  for (var i = 0; i < splitedString.length; i++) {
    var tempArray = splitedString[i].split("=");
    if (tempArray[0] == paramName)
      resultParamValue = tempArray[1];
  }
  return resultParamValue;
}

//주소로부터 id 가져와서 버튼클릭 시 뒤로 갈 수 있도록 구현.
document.querySelector(".btn_back").href = ("detail?id=" + getParamFromUrl("id"));

function addTemplateUnderUlClassNameListShortReview(json){
	  var template = document.querySelector("#commentTemplate").innerHTML;
	  var ulClassNameListShortReview = document.querySelector(".list_short_review");
	  var bindTemplate = Handlebars.compile(template);
	  var liCount = json.items.length;
	  var sumOfRating = 0;
	  var avgRating = 0;

	  Handlebars.registerHelper('emailTrim', function(input) {
		    var resultString = input.substring(0,4);
		    return new Handlebars.SafeString(resultString);
	  });
	  ulClassNameListShortReview.innerHTML += json.items.reduce(function(prev, next) {
	    return prev + bindTemplate(next);
	  }, "");
	  document.querySelector(".join_count").querySelector(".green").innerText
	  	= liCount + "건";
	  json.items.forEach(function(v){
		  sumOfRating += v.score * 1;
	  }); 
	  avgRating = (sumOfRating / liCount).toFixed(1);
	  document.querySelector(".text_value").querySelector("span").innerText 
	  	= avgRating;
	  document.querySelector(".graph_mask").querySelector(".graph_value").style.width 
	  	= avgRating * 20 + "%";
}

function getReservationCommentsAPIAjax(url) {
  var oReq = new XMLHttpRequest;

  oReq.open('GET', url);
  oReq.setRequestHeader("Content-type", "application/json");
  oReq.responseType = "text";
  oReq.addEventListener('load', function() {
    addTemplateUnderUlClassNameListShortReview(JSON.parse(this.responseText));
  });
  oReq.send();
}

getReservationCommentsAPIAjax("/reservationManagement/api/comments_by_id?id=" + getParamFromUrl("id"));

