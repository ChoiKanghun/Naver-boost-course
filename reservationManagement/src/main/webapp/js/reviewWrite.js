/*--------뒤로가기 버튼 링크 추가-------*/
function BackButtonClass() {
  this.btn_back = document.querySelector(".btn_back");
}
BackButtonClass.prototype.addBackEvent = function() {
  var reservationEmail = document.querySelector("#email").dataset.email;

  this.btn_back.addEventListener("click", function() {
    location.href = "checkMyBook?reservationEmail=" + reservationEmail;
  })
}

function RatingStarClass() {}
RatingStarClass.prototype.fillRatingStars = function() {
  var ratingStars = document.querySelectorAll(".rating_rdo");
  var starRank = document.querySelector(".star_rank");

  starRank.classList.remove("gray_star");
  ratingStars.forEach(function(star) {
    star.addEventListener("click", function() {
      var starCount = this.value * 1;

      for (var i = 2; i <= 6; i++) {
        if (i <= starCount + 1) {
          this.parentElement.querySelector("input[name=rating" + i + "]").classList.add("checked");
        } else {
          this.parentElement.querySelector("input[name=rating" + i + "]").classList.remove("checked");
        }
      }
      starRank.innerHTML = starCount;
    })
  })
}

function TextAreaClass() {}
TextAreaClass.prototype.textAreaHovering = function() {
  var textAreaWrapper = document.querySelector(".review_write_info");
  var textArea = document.querySelector(".review_textarea");

  textAreaWrapper.addEventListener("click", function() {
    this.style.display = "none";
    textArea.focus();
  })
  textArea.addEventListener("mouseleave", function() {
    if (this.value === "")
      textAreaWrapper.style.display = "block";
  })
}
TextAreaClass.prototype.setMaxLength = function() {
  document.querySelector(".review_textarea").maxLength = "400";
}
TextAreaClass.prototype.showTextLength = function() {
  document.querySelector(".review_textarea").addEventListener("input", function() {
    document.querySelector(".guide_review").firstElementChild.innerText = this.value.length;
  });
}

function BookingClass() {}
BookingClass.prototype.addSubmitEvt = function() {
  document.querySelector(".bk_btn").addEventListener("click", function() {
    var comment = document.querySelector(".review_textarea").value;
    var reservationInfoId = document.querySelector("#reservationInfoId").dataset.reservationInfoId;
    var productId = document.querySelector("#productId").dataset.productId;
    var score = document.querySelector(".star_rank").innerText * 1;

    var regExp = /^.{5,400}$/;
    if (!regExp.test(comment)) {
      window.alert("리뷰를 5글자 이상, 400자 이하로 남겨주세요.");
      return;
    }
    if (score < 0 || score > 5) {
      window.alert("평점은 반드시 0점 이상, 5점 이하여야 합니다.");
      return;
    }
    var form = document.createElement("form"); //form생성
    var imageFile = document.querySelector("#reviewImageFileOpenInput");

    imageFile.setAttribute("name", "file");
    form.appendChild(imageFile);
    form.setAttribute("charset", "UTF-8"); //charset설정
    form.setAttribute("method", "POST"); //method설정
    form.setAttribute("enctype", "multipart/form-data")
    form.setAttribute("action", "api/" + reservationInfoId + "/comments");

    var inputScore = document.createElement("input");
    inputScore.setAttribute("name", "score");
    inputScore.setAttribute("value", score);
    form.appendChild(inputScore);

    var inputComment = document.createElement("input");
    inputComment.setAttribute("name", "comment");
    inputComment.setAttribute("value", comment);
    form.appendChild(inputComment);

    var inputProductId = document.createElement("input");
    inputProductId.setAttribute("name", "productId");
    inputProductId.setAttribute("value", productId);
    form.appendChild(inputProductId);

    document.body.appendChild(form); //body에 form을 추가.
    form.submit(); //form을 제출.
  })
}

function ReviewImageClass() {}
ReviewImageClass.prototype.setAcceptableType = function() {
  document.querySelector("#reviewImageFileOpenInput").accept = "image/png, image/jpg";
}
ReviewImageClass.prototype.initThumbnailImgArea = function() {
  document.querySelector(".lst_thumb").querySelector("li > a").addEventListener("click", function() {
    this.parentElement.style.display = "none";
    document.querySelector("#reviewImageFileOpenInput").value = "";
  })
}
ReviewImageClass.prototype.showThumbnail = function() {
  document.querySelector("#reviewImageFileOpenInput").addEventListener("change", function(evt) {
    var image = evt.target.files[0];

    var elThumbnailImage = document.querySelector(".item_thumb");
    elThumbnailImage.src = window.URL.createObjectURL(image);
    document.querySelector(".lst_thumb").firstElementChild.style.display = "inline-block";
  });
}

window.addEventListener('DOMContentLoaded', () => {
  var backButtonObj = new BackButtonClass();
  backButtonObj.addBackEvent();

  var ratingStarObj = new RatingStarClass();
  ratingStarObj.fillRatingStars();

  var textAreaObj = new TextAreaClass();
  textAreaObj.textAreaHovering();
  textAreaObj.setMaxLength();
  textAreaObj.showTextLength();

  var bookingObj = new BookingClass();
  bookingObj.addSubmitEvt();

  var reviewImageObj = new ReviewImageClass();
  reviewImageObj.setAcceptableType();
  reviewImageObj.initThumbnailImgArea();
  reviewImageObj.showThumbnail();

  document.querySelector(".top_title h2 .title").innerText = document.querySelector("#productDescription").innerText;
})
