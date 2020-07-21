document.addEventListener("DOMContentLoaded", () => {
  const PROMOTION_WIDTH = document.querySelector(".visual_img").offsetWidth;

  
  function promotionImageMove(imageCount) {
    var curr = 0;
    var visualImg = document.querySelector(".visual_img");

    function set_t_out() {
      setTimeout(function() {
        if (curr != 0)
        	visualImg.style.transition = "transform 2s ease-in-out";
        else
        	visualImg.style.transition = "";
        visualImg.style.transform = "translate3d(-" + (curr) * PROMOTION_WIDTH + "px, 0px, 0px)";

        curr++;
        if (curr === imageCount) {
          curr = 0;
        }
        set_t_out();
      }, 2000);
    }
    set_t_out();
  }

  function addThImages(oReq) {
    var json = JSON.parse(oReq.responseText);
    var promotionItemHTML = document.querySelector("#promotionImages").innerHTML;
    var promotionItemResult = "";
    var visualImg = document.querySelector(".visual_img");
    var imageCount = 0;

    for (var i = 0; i < json.items.length; i++) {
      promotionItemResult += promotionItemHTML.replace("{src}", "api/file/savefilename?saveFileName=" + json.items[i].productImageUrl);
    };
    visualImg.innerHTML = promotionItemResult;
    visualImg.querySelectorAll("li").forEach(function() {
    	imageCount++;
    });
    visualImg.style.width = (PROMOTION_WIDTH * imageCount) + "px";
    promotionImageMove(imageCount);

  };

  function sendAjax(url) {
    var oReq = new XMLHttpRequest;
    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener('load', function() {
      addThImages(this);
    });
    oReq.send();
  };

  sendAjax("/reservationManagement/api/promotions");
});
