window.addEventListener('DOMContentLoaded', () => {
  /*------url로부터 (display_info_)id 파싱------*/
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
  //displayInfoId를 전역변수처럼 사용.
  var displayInfoId = getParamValueFromUrl("id");

  /*--------뒤로가기 버튼 링크 추가-------*/
  document.querySelector(".btn_back").href = "detail?id=" + displayInfoId;
  /*--------이미지 부분 추가 하기 -----------*/
  function addTitle(json) {
    document.querySelector(".top_title .title").innerHTML +=
      json.displayInfo.productDescription;
  }

  function addTemplateUnderSectionStoreDetails(json) {
    var storeDetailTemplate = document.querySelector("#template_under_section_store_details").innerHTML;
    var divClassNameSectionStoreDetails = document.querySelector(".section_store_details");
    var storeDetailBindTemplate = Handlebars.compile(storeDetailTemplate);

    divClassNameSectionStoreDetails.innerHTML += storeDetailBindTemplate(json);
    //최상단 제목 추가
    addTitle(json);
  }

  function addTemplateUnderUlClassNameVisualImg(json) {
    var visualTemplate = document.querySelector("#container_visual_template").innerHTML;
    var ulClassNameVisualImg = document.querySelector(".visual_img");
    var visualBindTemplate = Handlebars.compile(visualTemplate);

    ulClassNameVisualImg.innerHTML += visualBindTemplate(json);
    //이미지 내에 있어야 할 글자 추가.
    addTemplateUnderSectionStoreDetails(json);
  }

  function getDisplayInfoByAjax(url) {
    var oReq = new XMLHttpRequest;

    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener('load', function() {
      //화면 상단에 이미지 추가.
      addTemplateUnderUlClassNameVisualImg(JSON.parse(this.responseText));
    });
    oReq.send();

  }
  getDisplayInfoByAjax("/reservationManagement/api/products/" + displayInfoId);

  /*------- +, - 버튼 이벤트 추가 --------*/

  
  function addPlusMinusButtonEvent() {
	//값에 콤마(,)를 더해줌. ex) 10000->10,000
	function addCommas(val){
		  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g,",");
	}
    var plusMinusButtonWrappers = document.querySelectorAll(".clearfix");

    //plus, minus 버튼이 있는 칸 하나하나에 이벤트를 추가.
    plusMinusButtonWrappers.forEach(function(val) {
      var PlusMinusButtons = val.querySelectorAll("a");
  	  var totalItemCount = document.querySelector("#totalCount");
  	  
      PlusMinusButtons.forEach(function(btn) {
    	var itemPrice = btn.parentElement.parentElement.parentElement
    		.querySelector(".qty_info_icon .product_price .price");
    	var parseNumberItemPrice 
    		= Number(itemPrice.innerText.replace(/,/g, ""));
    	var totalItemPrice
    		= btn.parentElement.parentElement.querySelector(".total_price");
  
    	//버튼이 '+'인 경우
        if (btn.title === "더하기") {
          btn.addEventListener("click", function(b) {
            var count = this.parentElement.querySelector(".count_control_input");
            var countValue = count.value * 1;
            
            //더하기 전 개수가 0개 일 때
            if (countValue == 0) {
              this.parentElement.querySelector(".ico_minus3")
                .classList.remove("disabled");
              this.parentElement.querySelector(".count_control_input")
                .classList.remove("disabled");
              this.parentElement.parentElement.querySelector(".individual_price")
              	.classList.add("on_color");
            }
            count.value = ++countValue;
            //더하기를 누르면 더하기 버튼 밑의 총 금액도 증가.
            var parseNumberTotalItemPrice = Number(totalItemPrice.innerText.replace(/,/g,""));
            totalItemPrice.innerText 
            	= addCommas(parseNumberTotalItemPrice + parseNumberItemPrice);
            //총 개수 증가.
            totalItemCount.innerText = Number(totalItemCount.innerText) + 1;
          })
        } else { //버튼이 '-'인 경우
          btn.addEventListener("click", function(btn) {
            var count = this.parentElement.querySelector(".count_control_input");
            var countValue = count.value * 1;
            
            //1을 감소시킨 값이 0보다 클 때만 이벤트 발생.
            if (--countValue >= 0) {
            	//1을 감소시킨 값이 0일 때 -와 count 부분에 disabled 클래스 추가.
              if (countValue == 0) {
                this.parentElement.querySelector(".ico_minus3")
                  .classList.add("disabled");
                this.parentElement.querySelector(".count_control_input")
                  .classList.add("disabled");
                this.parentElement.parentElement.querySelector(".individual_price")
              		.classList.remove("on_color");
              }
              count.value = countValue;
              var parseNumberTotalItemPrice = Number(totalItemPrice.innerText.replace(/,/g,""));
              totalItemPrice.innerText 
          		= addCommas(parseNumberTotalItemPrice - parseNumberItemPrice);
              //총 개수 감소.
              totalItemCount.innerText = Number(totalItemCount.innerText) - 1;
            }
          })
        }
      })
    })
  }
  addPlusMinusButtonEvent();

  /*--------유효성 검사--------*/
  //전화번호
  function inspectTel(){
	  var tel = document.querySelector("#tel");
	  
	  tel.addEventListener("mouseleave", function(){
		  var regExpTel = /^\d{3}-\d{3,4}-\d{4}$/;
		  var warningMessage = this.parentElement.querySelector("#tel_warning");

		  if (this.value != "" && !regExpTel.test(this.value)){
			  warningMessage.style.display = "inline-block";
			  setTimeout(function(){
				  warningMessage.style.display = "none";
			  }, 1000);
		  } else {
			  warningMessage.style.display = "none";
		  }
	  })
  }
  //이메일
  function inspectEmail(){
	  var email = document.querySelector("#email");
	
	  email.addEventListener("mouseleave", function(){
 		 var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
 		 var warningMessage = this.parentElement.querySelector("#email_warning");
 		 
 		 if (this.value != "" && !regExpEmail.test(this.value)){
 			 warningMessage.style.display = "inline-block";
 			 setTimeout(function(){
 				 warningMessage.style.display = "none";
 			 }, 1000);
 		 } else {
 			 warningMessage.style.display = "none";
 		 }
	  });
  }
  inspectTel();
  inspectEmail();
  
  /*---------약관 더 보기---------*/
  function showMoreAgreement(){
	  var btnAgreements = document.querySelectorAll(".btn_agreement");
	  
	  btnAgreements.forEach(function(btn){
		  btn.addEventListener("click", function(){
			  this.parentElement.classList.toggle("open");
		  })
	  })
  }
  showMoreAgreement();
  
  /*----------예약하기 클래스 토글----------*/
  function toggleReserveButton(){
	  var agreeButton = document.querySelector(".chk_txt_label");
	  
	  agreeButton.addEventListener("click", function(){
		  var reserveButtonWrapper = document.querySelector(".bk_btn_wrap");
		  
		  reserveButtonWrapper.classList.toggle("disable");
		  if (reserveButtonWrapper.classList.contains("disable")){
			  //20200526 리무브 이벤트부터!
		  }
	  })
  }
  toggleReserveButton();
  
});