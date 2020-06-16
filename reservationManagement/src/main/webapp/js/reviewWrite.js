/*--------뒤로가기 버튼 링크 추가-------*/
function BackButtonClass() {
  this.btn_back = document.querySelector(".btn_back");
}
BackButtonClass.prototype.addBackEvent = function() {
	var reservationEmail = document.querySelector("#email").dataset.email;

	this.btn_back.addEventListener("click", function(){
		location.href = "checkMyBook?reservationEmail=" +  reservationEmail;
	})
}
var backButtonObj = new BackButtonClass();
backButtonObj.addBackEvent();

function adfd() {
  var ratingStars = document.querySelectorAll(".rating_rdo");
  var starRank = document.querySelector(".star_rank");

  starRank.classList.remove("gray_star");
  ratingStars.forEach(function(star){
    star.addEventListener("click", function(){
      var starCount = this.value * 1;

      for (var i = 2; i <= 6; i++){
        if (i <= starCount + 1){
          this.parentElement.querySelector("input[name=rating" + i + "]").classList.add("checked");
        } else {
          this.parentElement.querySelector("input[name=rating" + i + "]").classList.remove("checked");
        }
      }
      starRank.innerHTML = starCount;
    })
  })
}
adfd();

function asdf(){
	var textAreaWrapper = document.querySelector(".review_write_info");
	var textArea = document.querySelector(".review_textarea");
	
	textAreaWrapper.addEventListener("click", function(){
		this.style.display="none";
		textArea.focus();
	})
	textArea.addEventListener("mouseleave",function(){
		if (this.value === "")
			textAreaWrapper.style.display = "block";
	})
}
asdf();
document.querySelector(".review_textarea").maxLength = "400";
document.querySelector(".review_textarea").addEventListener("input", function() {
	document.querySelector(".guide_review").firstElementChild.innerText = this.value.length;
});

document.querySelector(".bk_btn").addEventListener("click", function(){
	var comment = document.querySelector(".review_textarea").value;
	var reservationInfoId = document.querySelector("#reservationInfoId").dataset.reservationInfoId;
	var productId = document.querySelector("#productId").dataset.productId;
	
	if (comment.length < 5) {
		window.alert("리뷰를 5글자 이상 남겨주세요.");
		return ;
	}
	
	var form = document.createElement("form"); //form생성
	var hiddenField = document.createElement("input");

	form.setAttribute("charset", "UTF-8");//charset설정
	form.setAttribute("method", "POST");//method설정
	form.setAttribute("action", "");

	hiddenField.setAttribute("name", "score");
	hiddenField.setAttribute("value", "");
	form.appendChild(hiddenField);

	hiddenField.setAttribute("name", "comment");
	hiddenField.setAttribute("value", comment);
	form.appendChild(hiddenField);
	
	hiddenField.setAttribute("name", "productId");
	hiddenField.setAttribute("value", productId);
	form.appendChild(hiddenField);
	
	hiddenField.setAttribute("name", "reservationInfoId");
	hiddenField.setAttribute("value", reservationInfoId);
	form.appendChild(hiddenField);
	
	
	document.body.appendChild(form);//body에 form을 추가.
	form.submit();//form을 제출.
		
})

document.querySelector("#reviewImageFileOpenInput").accept = "image/png, image/jpg";
document.querySelector("#reviewImageFileOpenInput").addEventListener("change", function(evt){
	  var image = evt.target.files[0];
	  
	  var elThumbnailImage = document.querySelector(".item_thumb");
	  elThumbnailImage.src = window.URL.createObjectURL(image);
	  document.querySelector(".lst_thumb").firstElementChild.style.display="inline-block";
});
document.querySelector(".lst_thumb").querySelector("li > a").addEventListener("click", function(){
	this.parentElement.style.display = "none";
	document.querySelector("#reviewImageFileOpenInput").value = "";
})

document.querySelector(".top_title h2 .title").innerText = document.querySelector("#productDescription").innerText;