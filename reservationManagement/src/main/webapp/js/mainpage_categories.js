document.addEventListener("DOMContentLoaded", () => {
  var test = document.querySelector("#test");
  var category_tab = document.querySelector(".event_tab_lst");
  var start = 0;
  var moreBtn = document.querySelector(".more");
  var current_category = document.querySelector("#current_category").value;

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
      
      if (i % 2 == 0)
    	  rightColumn.innerHTML += itemListResult;
      else
    	  leftColumn.innerHTML += itemListResult;
      start++;
    }
    if (start == json.totalCount)
      document.querySelector(".more").style.display = "none";
  }


  function makeHtmlContent(url, oReq, category_id) {
    var json = JSON.parse(oReq.responseText);
    var columns = document.querySelectorAll(".lst_event_box");
    var event_tab_lst_li = document.querySelector(".event_tab_lst").querySelectorAll("li");
    var pink = document.querySelector(".event_lst_txt").querySelector(".pink");

    columns.forEach(function(content) {
      content.querySelectorAll("li").forEach(function(li){
    	  li.remove();
      })
    });
    event_tab_lst_li.forEach(function(list) {
      list.firstElementChild.className =
        (list.dataset.category == category_id) ? 'anchor active' : 'anchor';
    });
    pink.innerText = json.totalCount + "개";
    drawBasicCategory(json);
  }

  function sendProductsAjax(url, flag, category_id) {
    var oReq = new XMLHttpRequest;

    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener("load", function() {
      if (flag === "tab")
        makeHtmlContent(url, this, category_id);
      if (flag === "more")
        drawBasicCategory(JSON.parse(oReq.responseText));
    });
    oReq.send();

  }

  category_tab.addEventListener("click", function(evt) {
    var category_id = "";

    if (evt.target.tagName === "UL") {
      category_id = evt.target.firstChild.dataset.category;
    } else if (evt.target.tagName === "LI") {
      category_id = evt.target.dataset.category;
    } else if (evt.target.tagName === "A") {
      category_id = evt.target.parentElement.dataset.category;
    } else if (evt.target.tagName === "SPAN") {
      category_id = evt.target.parentElement.parentElement.dataset.category;
    }
    current_category = category_id;
    start = 0;
    sendProductsAjax("/reservationManagement/api/products?categoryId=" + category_id, "tab", category_id);
    document.querySelector(".more").style.display = "block";
  });



  sendProductsAjax("/reservationManagement/api/products?categoryId=0", "tab", 0);

/*-----------------more button-----------*/
  moreBtn.addEventListener("click", function(evt) {
    var category_id = current_category;

    sendProductsAjax("/reservationManagement/api/products?start=" + start + "&categoryId=" + category_id, "more", category_id);
  })


});