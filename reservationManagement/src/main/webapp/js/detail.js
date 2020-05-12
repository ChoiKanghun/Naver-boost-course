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



function addTemplateUnderUlClassNameImage(json){
	var template = document.querySelector("#container_visual_template").innerHTML;
	var ulClassNameVisualImg = document.querySelector(".visual_img");
	var bindTemplate = Handlebars.compile(template);
	var offSpan = document.querySelector(".off").querySelector("span");
	var imageCount = 0;
	ulClassNameVisualImg.innerHTML += json.items.reduce(function(prev, next){
		return prev + bindTemplate(next);
	}, "")
	
	//class .figure_pagination area init
	document.querySelector(".figure_pagination").firstElementChild.innerText = "1";
	offSpan.innerText = json.items.length;
	
	ulClassNameVisualImg.querySelectorAll("li").forEach(()=>{
		imageCount++;
	});
	ulClassNameVisualImg.style.width = (ulClassNameVisualImg.offsetWidth * imageCount) + "px";
	ulClassNameVisualImg.style.transition = "transform 2s ease-in-out";
}

function getDetailPageAPIAjax(url){
	var oReq = new XMLHttpRequest;

	oReq.open('GET', url);
	oReq.setRequestHeader("Content-type", "application/json");
	oReq.responseType = "text";
	oReq.addEventListener('load', function(){
		addTemplateUnderUlClassNameImage(JSON.parse(this.responseText));
	});
	oReq.send();
}
getDetailPageAPIAjax("/reservationManagement/api/detail_page_items?id=" + getParamFromUrl("id"));

/*-----------prev, next button add event listner----------*/
function getNextUlClassNameVisualImg(flag){
	var currentImageNumberSpan = document.querySelector(".figure_pagination").firstElementChild;
	var curr = currentImageNumberSpan.innerText * 1;
	var limitCurr = document.querySelector(".off").querySelector("span").innerText * 1;
	var ulClassNameVisualImg = document.querySelector(".visual_img");
	const VISUAL_IMG_WIDTH = 414;

	if (flag === "toTheRight"){
		if (++curr > limitCurr){
			 curr = 1;
		}
	}
	if (flag === "toTheLeft"){
		if (--curr <= 0)
			curr = limitCurr;
	}
	ulClassNameVisualImg.style.transform 
		= "translate3d(-" + (curr - 1) * VISUAL_IMG_WIDTH + "px, 0px, 0px)";
	currentImageNumberSpan.innerText = "" + curr;
}

document.querySelector(".btn_prev").addEventListener('click',
		function(){
			getNextUlClassNameVisualImg("toTheLeft");
		}
);
document.querySelector(".btn_nxt").addEventListener('click', 
		function(){
			getNextUlClassNameVisualImg("toTheRight");
		}
);
