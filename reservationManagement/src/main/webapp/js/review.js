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
	  
	  ulClassNameListShortReview.innerHTML += json.items.reduce(function(prev, next) {
	    return prev + bindTemplate(next);
	  }, "")


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
