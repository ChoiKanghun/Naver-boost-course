document.addEventListener("DOMContentLoaded", () => {
  var test = document.querySelector("#test");
  var categoryTab = document.querySelector(".event_tab_lst");
  var start = 0;
  var moreBtn = document.querySelector(".more");
  var currentCategory = document.querySelector("#current_category").value;

  function drawBasicCategory(json) {
	var columns = document.querySelectorAll(".lst_event_box");
	var leftColumn = columns[0];
	var rightColumn = columns[1];
	var itemListHTML = document.querySelector("#itemList").innerHTML;
    var itemListResult = "";

    for (var i = 0; i < json.items.length; i++) {
      itemListResult = itemListHTML.replace("{display_info_id}", json.items[i].displayInfoId)
        .replace("{src}", json.items[i].productImageUrl)
        .replace("{description}", json.items[i].productDescription)
        .replace("{placeName}", json.items[i].placeName)
        .replace("{description}", json.items[i].productDescription)
        .replace("{content}", json.items[i].productContent);
      if (i % 2 != 0)
    	  rightColumn.innerHTML += itemListResult;
      else
    	  leftColumn.innerHTML += itemListResult;
      start++;
    }
    if (start == json.totalCount)
      document.querySelector(".more").style.display = "none";
  }


  function makeHtmlContent(url, oReq, categoryId) {
    var json = JSON.parse(oReq.responseText);
    var columns = document.querySelectorAll(".lst_event_box");
    var eventTabLstLi = document.querySelector(".event_tab_lst").querySelectorAll("li");
    var pink = document.querySelector(".event_lst_txt").querySelector(".pink");

    columns.forEach(function(content) {
      content.querySelectorAll("li").forEach(function(li){
    	  li.remove();
      })
    });
    eventTabLstLi.forEach(function(list) {
      list.firstElementChild.className =
        (list.dataset.category == categoryId) ? 'anchor active' : 'anchor';
    });
    pink.innerText = json.totalCount + "개";
    drawBasicCategory(json);
  }

  function sendProductsAjax(url, flag, categoryId) {
    var oReq = new XMLHttpRequest;

    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener("load", function() {
      if (flag === "tab")
        makeHtmlContent(url, this, categoryId);
      if (flag === "more")
        drawBasicCategory(JSON.parse(oReq.responseText));
    });
    oReq.send();

  }

  categoryTab.addEventListener("click", function(evt) {
    var categoryId = "";

    if (evt.target.tagName === "LI")
    	categoryId = evt.target.dataset.category;
    else if (evt.target.tagName === "A")
    	categoryId = evt.target.parentElement.dataset.category;
    else if (evt.target.tagName === "SPAN")
    	categoryId = evt.target.parentElement.parentElement.dataset.category;
    currentCategory = categoryId;
    start = 0;
    sendProductsAjax("/reservationManagement/api/products?categoryId=" + categoryId, "tab", categoryId);
    document.querySelector(".more").style.display = "block";
  });

  sendProductsAjax("/reservationManagement/api/products?categoryId=0", "tab", 0);

/*-----------------more button-----------*/
  moreBtn.addEventListener("click", function(evt) {
    var categoryId = currentCategory;

    sendProductsAjax("/reservationManagement/api/products?start=" + start + "&categoryId=" + categoryId, "more", categoryId);
  })


});