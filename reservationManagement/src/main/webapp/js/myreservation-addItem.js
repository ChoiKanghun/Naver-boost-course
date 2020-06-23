function HandlebarsClass() {};
HandlebarsClass.prototype.addHandlebarHelpers = function() {
  Handlebars.registerHelper('parseDateAndDay', function(stringDate) {
    var year = stringDate.substr(0, 4);
    var month = stringDate.substr(5, 2);
    var day = stringDate.substr(8, 2);
    var parsedDate = new Date(year, month - 1, day);
    var indexOfToday = parsedDate.getDay();
    var weekday = new Array(7);
    weekday[0] = "일";
    weekday[1] = "월";
    weekday[2] = "화";
    weekday[3] = "수";
    weekday[4] = "목";
    weekday[5] = "금";
    weekday[6] = "토";
    var resultDate = weekday[indexOfToday];

    return parsedDate.toLocaleDateString() + "(" + resultDate + ")";
  });
}

var CancellationButtonClass = function() {}
CancellationButtonClass.prototype.deleteCancellationButtonFromUndeletableItems = function() {
  var undeletableItems = document.querySelectorAll(".used");

  undeletableItems.forEach(function(item) {
	var reservationEmail = document.querySelector(".btn_my").innerText;

    if (item.classList.contains("cancel")) {
      item.querySelectorAll(".card_item").forEach(function(article) {
        article.querySelector(".booking_cancel").style.display = "none";
      })
    } else {
      item.querySelectorAll(".card_item").forEach(function(article) {
        var reservationInfoId = article.querySelector(".booking_number").dataset.reservationInfoId;
        var productId = article.querySelector(".product_id").dataset.productId;
        var productDescription = article.querySelectorAll(".item_dsc")[1].innerText;

        article.querySelector(".booking_cancel").firstElementChild.innerText = "예매자 리뷰 남기기";
        article.querySelector(".booking_cancel").firstElementChild.addEventListener("click", function() {
          location.href = "reviewWrite?reservationEmail=" + reservationEmail +
            "&productId=" + productId +
            "&reservationInfoId=" + reservationInfoId +
            "&productDescription=" + productDescription;
        })
      })
    }
  });
}

function ReloadPageClass() {}
ReloadPageClass.prototype.reloadPageAfterSubmitCancellation = function(reservationInfoId) {
  var reservationEmail = document.querySelector(".btn_my").innerText;
  var form = document.createElement("form");

  form.setAttribute("charset", "UTF-8");
  form.setAttribute("method", "POST");
  form.setAttribute("action", "/reservationManagement/checkMyBook");

  var hiddenField = document.createElement("input");
  hiddenField.setAttribute("type", "hidden");
  hiddenField.setAttribute("name", "reservationEmail");
  hiddenField.setAttribute("value", reservationEmail);
  form.appendChild(hiddenField);
  document.body.appendChild(form);
  form.submit();
}

function AjaxClass() {}
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
AjaxClass.prototype.sendCancelAjax = function(reservationInfoId) {
  if (confirm("정말 삭제하시겠습니까??") == true) {
    var oReqCancel = new XMLHttpRequest;

    oReqCancel.open('PUT', "/reservationManagement/api/cancelReservation?reservationInfoId=" + reservationInfoId);
    oReqCancel.setRequestHeader("Content-type", "application/json");
    oReqCancel.addEventListener("load", function() {
      var reloadPageObj = new ReloadPageClass();
      reloadPageObj.reloadPageAfterSubmitCancellation(reservationInfoId);
    })
    oReqCancel.send();
  } else {
    return false;
  }
}
AjaxClass.prototype.getReservationInfoByAjax = function(url) {
  var oReq = new XMLHttpRequest;

  oReq.open('GET', url);
  oReq.setRequestHeader("Content-type", "application/json");
  oReq.responseType = "text";
  oReq.addEventListener('load', function() {
    var json = JSON.parse(this.responseText);
    var articleObj = new ArticleClass();
    var cancellationButtonObj = new CancellationButtonClass();
    var handlebarsObj = new HandlebarsClass();

    handlebarsObj.addHandlebarHelpers();
    articleObj.addSummaryContents(json);
    articleObj.addReserveInfo(json);
    cancellationButtonObj.deleteCancellationButtonFromUndeletableItems();
  });
  oReq.send();
}

function CancelReservationClass() {

}
CancelReservationClass.prototype.cancelMyReservation = function(json) {
  var btnWrapper = document.querySelectorAll(".booking_cancel");

  btnWrapper.forEach(function(b) {
    var article = b.parentElement.parentElement.parentElement.parentElement.parentElement;
    if (!(article.classList.contains("used"))) {
      b.querySelector(".btn").addEventListener('click', function() {
        var reservationInfoId = this.dataset.reservationInfoId;
        var sendCancelAjaxObj = new AjaxClass();

        sendCancelAjaxObj.sendCancelAjax(reservationInfoId);
      })
    }
  })
}

function ArticleClass() {
  this.arrayToSaveWhereToAddArticle = [];
}
ArticleClass.prototype.addSummaryContents = function(json) {
  var total = document.querySelector(".ico_book2").parentElement.querySelector(".figure");
  total.innerHTML = json.size;
  var cancelledPerformCount = 0;
  var toBePerformedCount = 0;
  var alreadyPerformedCount = 0;
  var reservationsArray = json.reservations;
  var now = new Date().setMonth(new Date().getMonth());

  reservationsArray.forEach(function(oneReservation, index) {
    var year = oneReservation.reservationDate.substr(0, 4);
    var month = oneReservation.reservationDate.substr(5, 2);
    var day = oneReservation.reservationDate.substr(8, 2);
    var performDate = new Date(year, month - 1, day).getTime();

    var test = oneReservation.cancelFlag;

    if (oneReservation.cancelFlag) {
      cancelledPerformCount++;
      this.arrayToSaveWhereToAddArticle[index] = "cancel";
    } else if (now > performDate) {
      alreadyPerformedCount++;
      this.arrayToSaveWhereToAddArticle[index] = "alreadyPerformed";
    } else {
      toBePerformedCount++;
      this.arrayToSaveWhereToAddArticle[index] = "toBePerformed";
    }
  }.bind(this));
  var toBePerformedTarget = document.querySelector(".ico_book_ss").parentElement.querySelector(".figure");
  var alreadyPerformedTarget = document.querySelector(".ico_check").parentElement.querySelector(".figure");
  var cancelledPerformTarget = document.querySelector(".ico_back").parentElement.querySelector(".figure");

  toBePerformedTarget.innerHTML = toBePerformedCount;
  alreadyPerformedTarget.innerHTML = alreadyPerformedCount;
  cancelledPerformTarget.innerHTML = cancelledPerformCount;
}
ArticleClass.prototype.addReserveInfo = function(json) {
  var articleTemplateHTML = document.querySelector("#article_template").innerHTML;
  var articleBindTemplate = Handlebars.compile(articleTemplateHTML);

  json.reservations.forEach(function(oneReservation, index) {
    var targetToAddArticle;
    var flagString = this.arrayToSaveWhereToAddArticle[index];

    if (flagString === "cancel")
      targetToAddArticle = document.querySelector(".card.used.cancel");
    else if (flagString === "alreadyPerformed")
      targetToAddArticle = document.querySelectorAll(".card.used")[0];
    else
      targetToAddArticle = document.querySelector(".card.confirmed");
    targetToAddArticle.innerHTML += articleBindTemplate(oneReservation);
  }.bind(this));
  var cancelReservationObj = new CancelReservationClass();

  cancelReservationObj.cancelMyReservation(json);
}

function CheckMyBookExistsClass() {}
CheckMyBookExistsClass.prototype.checkMyBookExists = function() {
  var reservationEmail = document.querySelector(".btn_my").innerText;
  
  if (reservationEmail === "" || reservationEmail === "none") {
    document.querySelector(".wrap_mylist").style.display = "none";
    document.querySelector(".my_summary").style.display = "none";
  } else {
    var reservationAjaxObj = new AjaxClass();
    var url = "/reservationManagement/api/reservations?reservationEmail=" + reservationEmail;

    document.querySelector(".err").style.display = "none";
    reservationAjaxObj.getReservationInfoByAjax(url);
  }
}

window.addEventListener('DOMContentLoaded', () => {
  var checkMyBookExistsObj = new CheckMyBookExistsClass();
  checkMyBookExistsObj.checkMyBookExists();
});
