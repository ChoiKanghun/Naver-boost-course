function addTemplateUnderUlClassNameImage(json) {
  var template = document.querySelector("#container_visual_template").innerHTML;
  var ulClassNameVisualImg = document.querySelector(".visual_img");
  var bindTemplate = Handlebars.compile(template);
  var offSpan = document.querySelector(".off").querySelector("span");
  var contentArea = document.querySelector(".store_details").querySelector(".dsc");
  var imageCount = 0;

  ulClassNameVisualImg.innerHTML += json.items.reduce(function(prev, next) {
    return prev + bindTemplate(next);
  }, "")
  contentArea.innerHTML += json.items[0].productContent;
  // class .figure_pagination area init
  offSpan.innerText = json.items.length;
  ulClassNameVisualImg.querySelectorAll("li").forEach(() => {
    imageCount++;
  });
  ulClassNameVisualImg.style.width = (ulClassNameVisualImg.offsetWidth * imageCount) + "px";
  ulClassNameVisualImg.style.transition = "transform 2s ease-in-out";
  document.querySelector(".figure_pagination").firstElementChild.innerText = "1";
}

function getDetailPageAPIAjax(url) {
  var oReq = new XMLHttpRequest;

  oReq.open('GET', url);
  oReq.setRequestHeader("Content-type", "application/json");
  oReq.responseType = "text";
  oReq.addEventListener('load', function() {
    addTemplateUnderUlClassNameImage(JSON.parse(this.responseText));
  });
  oReq.send();
}

function getParamFromUrl(paramName) {
  var resultParamValue = "";
  // location.search는 url에서 ?를 찾아내줌.
  var paramString = location.search.substr(1);
  var splitedString = paramString.split("&");
  for (var i = 0; i < splitedString.length; i++) {
    var tempArray = splitedString[i].split("=");
    if (tempArray[0] == paramName)
      resultParamValue = tempArray[1];
  }
  return resultParamValue;
}

getDetailPageAPIAjax("/reservationManagement/api/detail_page_items?id=" + getParamFromUrl("id"));

/*-----------prev, next button add event listener----------*/

function getNextUlClassNameVisualImg(flag) {
  var currentImageNumberSpan = document.querySelector(".figure_pagination").firstElementChild;
  var curr = currentImageNumberSpan.innerText * 1;
  var limitCurr = document.querySelector(".off").querySelector("span").innerText * 1;
  var ulClassNameVisualImg = document.querySelector(".visual_img");
  const VISUAL_IMG_WIDTH = 414;

  if (flag === "toTheRight") {
    if (++curr > limitCurr) {
      curr = 1;
    }
  }
  if (flag === "toTheLeft") {
    if (--curr <= 0)
      curr = limitCurr;
  }
  ulClassNameVisualImg.style.transform = "translate3d(-" + (curr - 1) * VISUAL_IMG_WIDTH + "px, 0px, 0px)";
  currentImageNumberSpan.innerText = "" + curr;
}

document.querySelector(".btn_prev").addEventListener('click',
  function() {
    getNextUlClassNameVisualImg("toTheLeft");
  }
);
document.querySelector(".btn_nxt").addEventListener('click',
  function() {
    getNextUlClassNameVisualImg("toTheRight");
  }
);

/*-------------- 펼쳐보기, 접기 ---------------*/

$(".bk_more").each(function() {
  $(this).on('click', function() {
      if ($("._open").css("display") == "none") {
        $("._open").css("display", "block");
        $("._close").css("display", "none");
      } else {
        $("._open").css("display", "none");
        $("._close").css("display", "block");
      }
      $(".store_details").toggleClass("close3");
    })
  });

/*--------------- review 뒤에 id 부여하기. ------------ */
document.querySelector(".btn_review_more").href = ("review?id=" + getParamFromUrl("id"));

/*--------------- 한줄평 li 추가하기 --------------- */

function addTemplateUnderUlClassNameListShortReview(json){
	  var template = document.querySelector("#commentTemplate").innerHTML;
	  var ulClassNameListShortReview = document.querySelector(".list_short_review");
	  var bindTemplate = Handlebars.compile(template);
	  

	  ulClassNameListShortReview.innerHTML += json.items.reduce(function(prev, next) {
	    return prev + bindTemplate(next);
	  }, "")


}

function getLimitedReservationCommentsAPIAjax(url) {
  var oReq = new XMLHttpRequest;

  oReq.open('GET', url);
  oReq.setRequestHeader("Content-type", "application/json");
  oReq.responseType = "text";
  oReq.addEventListener('load', function() {
    addTemplateUnderUlClassNameListShortReview(JSON.parse(this.responseText));
  });
  oReq.send();
}

getLimitedReservationCommentsAPIAjax("/reservationManagement/api/limited_comments_by_id?id=" + getParamFromUrl("id"));

/*-------------평점 및 총 개수 구하기 ------------*/

function getReservationCommentsAPIAjax(url){
	var oReq = new XMLHttpRequest;

	oReq.open('GET', url);
	oReq.setRequestHeader("Content-type", "application/json");
	oReq.responseType = "text";
	oReq.addEventListener('load', function() {
	   addTemplateUnderUlClassNameListShortReview(JSON.parse(this.responseText));
	});
	oReq.send();
}