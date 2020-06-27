/*----------예약하기 클래스 ----------*/
function CheckReservableClass() {}
CheckReservableClass.prototype.checkReservable = function(name, tel, email) {
  var regExpTel = /^\d{3}-\d{3,4}-\d{4}$/;
  var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  var bookButtonWrapper = document.querySelector(".bk_btn_wrap");
  var agreeButton = document.querySelector(".chk_txt_label");
  var totalCount = document.getElementById("totalCount");

  if (name == "" || !regExpTel.test(tel) || !regExpEmail.test(email) ||
    !agreeButton.classList.contains("checked") || totalCount.innerHTML == 0) {
    bookButtonWrapper.classList.add("disable");
    return;
  } else {
    bookButtonWrapper.classList.remove("disable");
  }
}

function ReserveButtonClass() {}
ReserveButtonClass.prototype.toggleReserveButton = function() {
  var inputTags = document.querySelectorAll(".inline_form");
  var clearfixes = document.querySelectorAll(".clearfix");
  var inputTel = document.getElementById("tel");
  var inputName = document.getElementById("name");
  var inputEmail = document.getElementById("email");
  var checkReservableObj = new CheckReservableClass();

  // inputTag에 이름, 전화번호, 이메일을 입력할 때마다 예약가능한지 확인
  inputTags.forEach(function(inputTag) {
    inputTag.addEventListener("mouseleave", function() {
      checkReservableObj.checkReservable(inputName.value, inputTel.value, inputEmail.value);
    })
  });
  // +, - 버튼이 클릭될 때에도 확인.
  clearfixes.forEach(function(clearfix) {
    clearfix.addEventListener("click", function() {
      checkReservableObj.checkReservable(inputName.value, inputTel.value, inputEmail.value);
    });
  })
}
ReserveButtonClass.prototype.addReserveEvent = function(json) {
  document.querySelector(".bk_btn_wrap").addEventListener("click", function() {
    if (!this.classList.contains("disable")) {
      var form = document.createElement("form");
      form.setAttribute("charset", "UTF-8");
      form.setAttribute("method", "POST");
      form.setAttribute("action", "/reservationManagement/api/reservations");

      var hiddenFieldProductId = document.createElement("input");
      hiddenFieldProductId.setAttribute("type", "hidden");
      hiddenFieldProductId.setAttribute("name", "productId");
      hiddenFieldProductId.setAttribute("value", json.displayInfo.productId);
      form.appendChild(hiddenFieldProductId);

      var hiddenFieldDisplayInfoId = document.createElement("input");
      hiddenFieldDisplayInfoId.setAttribute("type", "hidden");
      hiddenFieldDisplayInfoId.setAttribute("name", "displayInfoId");
      hiddenFieldDisplayInfoId.setAttribute("value", json.displayInfo.displayInfoId);
      form.appendChild(hiddenFieldDisplayInfoId);

      var hiddenFieldReservationEmail = document.createElement("input");
      var reservationEmail = document.querySelector("#email").value
      hiddenFieldReservationEmail.setAttribute("type", "hidden");
      hiddenFieldReservationEmail.setAttribute("name", "reservationEmail");
      hiddenFieldReservationEmail.setAttribute("value", reservationEmail);
      form.appendChild(hiddenFieldReservationEmail);

      var hiddenFieldReservationName = document.createElement("input");
      var reservationName = document.querySelector("#name").value
      hiddenFieldReservationName.setAttribute("type", "hidden");
      hiddenFieldReservationName.setAttribute("name", "reservationName");
      hiddenFieldReservationName.setAttribute("value", reservationName);
      form.appendChild(hiddenFieldReservationName);

      var hiddenFieldReservationTel = document.createElement("input");
      var reservationTel = document.querySelector("#tel").value
      hiddenFieldReservationTel.setAttribute("type", "hidden");
      hiddenFieldReservationTel.setAttribute("name", "reservationTel");
      hiddenFieldReservationTel.setAttribute("value", reservationTel);
      form.appendChild(hiddenFieldReservationTel);

      var hiddenFieldReservationYearMonthDay = document.createElement("input");
      var reservationYearMonthDay = document.querySelector(".inline_txt").querySelector("b").innerHTML;
      hiddenFieldReservationYearMonthDay.setAttribute("type", "hidden");
      hiddenFieldReservationYearMonthDay.setAttribute("name", "reservationYearMonthDay");
      hiddenFieldReservationYearMonthDay.setAttribute("value", reservationYearMonthDay);
      form.appendChild(hiddenFieldReservationYearMonthDay);

      var qtys = document.querySelectorAll(".qty");

      qtys.forEach(function(qty, index) {
        if (qty.querySelector(".total_price").innerText != "0") {
          var count = qty.querySelector(".count_control_input").value;

          if (count != 0) {
            var productPriceId = json.productPrices[index].productPriceId;
            var hiddenFieldCount = document.createElement("input");
            hiddenFieldCount.setAttribute("type", "hidden");
            hiddenFieldCount.setAttribute("name", "reserveItemPrices[" + index + "].count");
            hiddenFieldCount.setAttribute("value", Number(count));
            form.appendChild(hiddenFieldCount);

            var hiddenFieldProductPriceId = document.createElement("input");
            hiddenFieldProductPriceId.setAttribute("type", "hidden");
            hiddenFieldProductPriceId.setAttribute("name", "reserveItemPrices[" + index + "].productPriceId");
            hiddenFieldProductPriceId.setAttribute("value", productPriceId);
            form.appendChild(hiddenFieldProductPriceId);
          }
        }
      })

      document.body.appendChild(form);
      form.submit();
    } else {
      var warningMessage = "형식에 맞지 않는 필수정보가 입력됐거나 약관에 동의하지 않으셨습니다.";
      if (!document.querySelector(".unbookable")) {
        this.insertAdjacentHTML("beforebegin",
          '<div class = "unbookable" style="color:red">' +
          warningMessage + "</div>");
      }
      document.querySelector(".unbookable").style.display = "inline-block";
      setTimeout(function() {
        document.querySelector(".unbookable").style.display = "none";
      }, 1000)
    }
  })
}

/*---------약관 더 보기---------*/
function TermsAgreementClass() {
  this.btnAgreements = document.querySelectorAll(".btn_agreement");
  this.agreeButton = document.querySelector(".chk_txt_label");
}
TermsAgreementClass.prototype.showMoreAgreement = function() {
  this.btnAgreements.forEach(function(btn) {
    btn.addEventListener("click", function() {
      this.parentElement.classList.toggle("open");
    })
  })
}
// 이용약관에 동의할 때 필수정보들이 다 입력됐는지 확인.
TermsAgreementClass.prototype.OnClickCheckReservable = function() {
  var checkReservableObj = new CheckReservableClass();
  var inputTel = document.getElementById("tel");
  var inputName = document.getElementById("name");
  var inputEmail = document.getElementById("email");

  this.agreeButton.addEventListener("click", function() {
    this.classList.toggle("checked");
    checkReservableObj.checkReservable(inputName.value, inputTel.value, inputEmail.value);
  });
}

/*--------유효성 검사--------*/
function CheckValidityClass() {}
// 전화번호
CheckValidityClass.prototype.inspectTel = function() {
  var tel = document.querySelector("#tel");

  tel.addEventListener("mouseleave", function() {
    var regExpTel = /^\d{3}-\d{3,4}-\d{4}$/;
    var warningMessage = this.parentElement.querySelector("#tel_warning");

    if (this.value != "" && !regExpTel.test(this.value)) {
      warningMessage.style.display = "inline-block";
      setTimeout(function() {
        warningMessage.style.display = "none";
      }, 1000);
    } else {
      warningMessage.style.display = "none";
    }
  })
}
CheckValidityClass.prototype.inspectEmail = function() {
  var email = document.querySelector("#email");

  email.addEventListener("mouseleave", function() {
    var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    var warningMessage = this.parentElement.querySelector("#email_warning");

    if (this.value != "" && !regExpEmail.test(this.value)) {
      warningMessage.style.display = "inline-block";
      setTimeout(function() {
        warningMessage.style.display = "none";
      }, 1000);
    } else {
      warningMessage.style.display = "none";
    }
  });
}
/* 핸들바 이벤트들을 추가할 클래스 */
function HandlebarsClass() {}
HandlebarsClass.prototype.addHandlebarHelpers = function() {
  // 숫자를 금액처럼 표시해줌.
  Handlebars.registerHelper('priceFormatter', function(input) {
    return input.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  });
  // 현재날짜부터 현재날짜 + 2개월까지 표시
  Handlebars.registerHelper('reservatablePeriod', function(input) {
    let startDate = new Date();
    let endDate = new Date();
    var weekday = new Array(7);
    var resultPeriod = "";

    endDate.setMonth(endDate.getMonth() + 2);
    weekday[0] = "일";
    weekday[1] = "월";
    weekday[2] = "화";
    weekday[3] = "수";
    weekday[4] = "목";
    weekday[5] = "금";
    weekday[6] = "토";

    resultPeriod = startDate.toLocaleDateString() + "(" + weekday[startDate.getDay()] +
      ") ~" + endDate.toLocaleDateString() + "(" + weekday[endDate.getDay()] + ")";
    return resultPeriod;
  });
  Handlebars.registerHelper('discountedPrice', function(price, discountRate) {
    var discounted = (price * (100 - discountRate) / 100);

    return discounted.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  })
}

/*--------뒤로가기 버튼 링크 추가-------*/
function BackButtonClass() {
  this.btn_back = document.querySelector(".btn_back");
}
BackButtonClass.prototype.getParamValueFromUrl = function(paramName) {
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

var backButtonObj = new BackButtonClass();
backButtonObj.btn_back.href = "detail?id=" + backButtonObj.getParamValueFromUrl("id");

/*--------이미지, 설명 부분 추가 하기 -----------*/
function TitleClass() {}
TitleClass.prototype.addTitle = function(json) {
  document.querySelector(".top_title .title").innerHTML +=
    json.displayInfo.productDescription;
  // 예매내용에 오늘을 기준으로 +5일 만큼 랜덤 일자 추가.
}

function TicketBodyClass() {}
TicketBodyClass.prototype.addTicketBody = function(json) {
  var ticketBodyTemplate = document.querySelector("#template_under_ticket_body").innerHTML;
  var divClassNameTicketBody = document.querySelector(".ticket_body");
  var ticketBodyBindTemplate = Handlebars.compile(ticketBodyTemplate);

  divClassNameTicketBody.innerHTML += ticketBodyBindTemplate(json);
  // 최상단 제목 추가
  var titleObj = new TitleClass();
  titleObj.addTitle(json);
}
TicketBodyClass.prototype.addPlusMinusButtonEvent = function() {
  // 값에 콤마(,)를 더해줌. ex) 10000->10,000
  function addCommas(val) {
    return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  }
  var plusMinusButtonWrappers = document.querySelectorAll(".clearfix");

  // plus, minus 버튼이 있는 칸 하나하나에 이벤트를 추가.
  plusMinusButtonWrappers.forEach(function(val) {
    var PlusMinusButtons = val.querySelectorAll("a");
    var totalItemCount = document.querySelector("#totalCount");

    PlusMinusButtons.forEach(function(btn) {
      var itemPrice = btn.parentElement.parentElement.parentElement
        .querySelector(".qty_info_icon .product_price .price");
      var parseNumberItemPrice = Number(itemPrice.innerText.replace(/,/g, ""));
      var totalItemPrice = btn.parentElement.parentElement.querySelector(".total_price");

      // 버튼이 '+'인 경우
      if (btn.title === "더하기") {
        btn.addEventListener("click", function(b) {
          var count = this.parentElement.querySelector(".count_control_input");
          var countValue = count.value * 1;

          // 더하기 전 개수가 0개 일 때
          if (countValue == 0) {
            this.parentElement.querySelector(".ico_minus3")
              .classList.remove("disabled");
            this.parentElement.querySelector(".count_control_input")
              .classList.remove("disabled");
            this.parentElement.parentElement.querySelector(".individual_price")
              .classList.add("on_color");
          }
          count.value = ++countValue;
          // 더하기를 누르면 더하기 버튼 밑의 총 금액도 증가.
          var parseNumberTotalItemPrice = Number(totalItemPrice.innerText.replace(/,/g, ""));
          totalItemPrice.innerText = addCommas(parseNumberTotalItemPrice + parseNumberItemPrice);
          // 총 개수 증가.
          totalItemCount.innerText = Number(totalItemCount.innerText) + 1;
        })
      } else { // 버튼이 '-'인 경우
        btn.addEventListener("click", function(btn) {
          var count = this.parentElement.querySelector(".count_control_input");
          var countValue = count.value * 1;

          // 1을 감소시킨 값이 0보다 클 때만 이벤트 발생.
          if (--countValue >= 0) {
            // 1을 감소시킨 값이 0일 때 -와 count 부분에 disabled 클래스 추가.
            if (countValue == 0) {
              this.parentElement.querySelector(".ico_minus3")
                .classList.add("disabled");
              this.parentElement.querySelector(".count_control_input")
                .classList.add("disabled");
              this.parentElement.parentElement.querySelector(".individual_price")
                .classList.remove("on_color");
            }
            count.value = countValue;
            var parseNumberTotalItemPrice = Number(totalItemPrice.innerText.replace(/,/g, ""));
            totalItemPrice.innerText = addCommas(parseNumberTotalItemPrice - parseNumberItemPrice);
            // 총 개수 감소.
            totalItemCount.innerText = Number(totalItemCount.innerText) - 1;
          }
        })
      }
    })
  })
}

function ProductDescriptionClass() {}
ProductDescriptionClass.prototype.addProductDescription = function(json) {
  var storeDetailTemplate = document.querySelector("#template_under_section_store_details").innerHTML;
  var divClassNameSectionStoreDetails = document.querySelector(".section_store_details");
  var storeDetailBindTemplate = Handlebars.compile(storeDetailTemplate);

  divClassNameSectionStoreDetails.innerHTML += storeDetailBindTemplate(json);
  // +, - 버튼이 있는 ticket body부분 구현.
  var ticketBodyObj = new TicketBodyClass();
  ticketBodyObj.addTicketBody(json);
  ticketBodyObj.addPlusMinusButtonEvent();
}

function ImageClass() {}
ImageClass.prototype.addImages = function(json) {
  var visualTemplate = document.querySelector("#container_visual_template").innerHTML;
  var ulClassNameVisualImg = document.querySelector(".visual_img");
  var visualBindTemplate = Handlebars.compile(visualTemplate);

  var handlebarsObj = new HandlebarsClass();
  handlebarsObj.addHandlebarHelpers();

  // 이미지 및 이미지 내에 있어야 할 글자 추가.
  ulClassNameVisualImg.innerHTML += visualBindTemplate(json);
  // 이미지 밑에 있어야 할 내용 추가.
  var productDescriptionObj = new ProductDescriptionClass();
  productDescriptionObj.addProductDescription(json);
}

function AjaxClass() {}
AjaxClass.prototype.getDisplayInfoByAjax = function(url) {
  var oReq = new XMLHttpRequest;

  oReq.open('GET', url);
  oReq.setRequestHeader("Content-type", "application/json");
  oReq.responseType = "text";
  oReq.addEventListener('load', function() {
    var json = JSON.parse(this.responseText);
    // 화면 상단에 이미지 추가.
    var imageObj = new ImageClass();
    imageObj.addImages(json);

    var checkValidityObj = new CheckValidityClass();
    checkValidityObj.inspectTel();
    checkValidityObj.inspectEmail();

    var termsAgreementObj = new TermsAgreementClass();
    termsAgreementObj.showMoreAgreement();
    termsAgreementObj.OnClickCheckReservable();

    var reserveButtonObj = new ReserveButtonClass();
    reserveButtonObj.toggleReserveButton();
    reserveButtonObj.addReserveEvent(json);
  });
  oReq.send();
}
AjaxClass.prototype.getParamValueFromUrl = function(paramName) {
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

window.addEventListener('DOMContentLoaded', () => {
  var getDisplayInfoObj = new AjaxClass();
  getDisplayInfoObj.getDisplayInfoByAjax("/reservationManagement/api/products/" + getDisplayInfoObj.getParamValueFromUrl("id"));
});