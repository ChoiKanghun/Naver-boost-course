window.addEventListener('DOMContentLoaded', () => {

	function addTemplateUnderSectionStoreDetails(json){
		debugger;
		var storeDetailTemplate = document.querySelector("#template_under_section_store_details").innerHTML;
		var divClassNameSectionStoreDetails = document.querySelector(".section_store_details");
		var storeDetailBindTemplate = Handlebars.compile(storeDetailTemplate);
		debugger;
		divClassNameSectionStoreDetails.innerHTML += storeDetailBindTemplate(json);
	}
	
	function addTemplateUnderUlClassNameVisualImg(json) {
	    var visualTemplate = document.querySelector("#container_visual_template").innerHTML;
	    var ulClassNameVisualImg = document.querySelector(".visual_img");
	    var visualBindTemplate = Handlebars.compile(visualTemplate);

	    ulClassNameVisualImg.innerHTML += visualBindTemplate(json);
	    addTemplateUnderSectionStoreDetails(json);
	  }
	
	  function getDisplayInfoByAjax(url) {
		    var oReq = new XMLHttpRequest;

		    oReq.open('GET', url);
		    oReq.setRequestHeader("Content-type", "application/json");
		    oReq.responseType = "text";
		    oReq.addEventListener('load', function() {
		      addTemplateUnderUlClassNameVisualImg(JSON.parse(this.responseText));
		    });
		    oReq.send();
		    
		  }
	  
	function getParamValueFromUrl(paramName) {
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

  getDisplayInfoByAjax("/reservationManagement/api/products/" + getParamValueFromUrl("id"));
});


