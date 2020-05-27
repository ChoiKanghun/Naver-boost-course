window.addEventListener('DOMContentLoaded', () => {
  function pricesClass(productPriceId, count) {
    this.productPriceId = productPriceId;
    this.count = count;
  }

  function addReserveEvent(json) {
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

        var hiddenFieldReservationTelephone = document.createElement("input");
        var reservationTelephone = document.querySelector("#tel").value
        hiddenFieldReservationTelephone.setAttribute("type", "hidden");
        hiddenFieldReservationTelephone.setAttribute("name", "reservationTelephone");
        hiddenFieldReservationTelephone.setAttribute("value", reservationTelephone);
        form.appendChild(hiddenFieldReservationTelephone);

        var hiddenFieldReservationYearMonthDay = document.createElement("input");
        var reservationYearMonthDay = document.querySelector(".inline_txt").querySelector("b").innerHTML;
        hiddenFieldReservationYearMonthDay.setAttribute("type", "hidden");
        hiddenFieldReservationYearMonthDay.setAttribute("name", "reservationYearMonthDay");
        hiddenFieldReservationYearMonthDay.setAttribute("value", reservationYearMonthDay);
        form.appendChild(hiddenFieldReservationYearMonthDay);

       
        var qtys = document.querySelectorAll(".qty");
        
        qtys.forEach(function(qty, index) {
        	var count = qty.querySelector(".count_control_input").value;
          if (count != 0){
          	var prices = new pricesClass(json.productPrices[index].productPriceId, Number(count));
          	var hiddenFieldPrices = document.createElement("input");
          	prices = JSON.stringify(prices);
          	hiddenFieldPrices.setAttribute("type", "hidden");
          	hiddenFieldPrices.setAttribute("name", "prices");
          	hiddenFieldPrices.setAttribute("value", prices);
          	form.appendChild(hiddenFieldPrices);
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

  function getDisplayInfoByAjax(url) {
    var oReq = new XMLHttpRequest;

    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener('load', function() {
      //화면 상단에 이미지 추가.
      addReserveEvent(JSON.parse(this.responseText));
    });
    oReq.send();
  }
  getDisplayInfoByAjax("/reservationManagement/api/products/" + getParamValueFromUrl("id"));

})
