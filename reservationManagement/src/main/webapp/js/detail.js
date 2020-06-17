window.addEventListener('DOMContentLoaded', () => {
  var addImageAddTemplateObj = {
    addEventOnInfoTabList: function() {
      var LisUnderClassNameinfoTabList = document.querySelector(".info_tab_lst").querySelectorAll("li");
      var DivsUnderClassNameSectionInfoTab = document.querySelectorAll(".section_info_tab>div");

      LisUnderClassNameinfoTabList.forEach(function(listItem) {
        listItem.addEventListener('click', function() {
          LisUnderClassNameinfoTabList.forEach(function(iterateLiTag) {
            iterateLiTag.querySelector(".anchor").classList.remove("active");
          });
          this.querySelector(".anchor").classList.add("active");
          DivsUnderClassNameSectionInfoTab.forEach(function(iterateDivTag) {
            iterateDivTag.classList.add("hide");
          });
          this.classList.forEach(function(className) {
            if (className == "_detail")
              document.querySelector(".detail_area_wrap").classList.remove("hide");
            else if (className == "_path")
              document.querySelector(".detail_location").classList.remove("hide");
          })
        });
      });
    },
    addPathContent: function(json) {
      var pathTemplate = document.querySelector("#pathTemplate").innerHTML;
      var divClassNameBoxStoreInfo = document.querySelector(".box_store_info");
      var bindPathTemplate = Handlebars.compile(pathTemplate);
      var bindResultHTML = "";

      bindResultHTML = bindPathTemplate(json);
      divClassNameBoxStoreInfo.innerHTML += bindResultHTML;
      //상세정보, 오시는 길 active 클래스 추가, 삭제
      this.addEventOnInfoTabList();
    },
    addIntroduction: function(json) {
      var introductionArea = document.querySelectorAll(".detail_info_lst")[0].querySelector(".in_dsc");

      introductionArea.innerHTML += json.displayInfo.productContent;
      
      //오시는 길 정보 추가
      this.addPathContent(json);
    },
    addAverageScore: function(json) {
      var avgRating = (json.averageScore * 1).toFixed(1);

      document.querySelector(".text_value").querySelector("span").innerText = avgRating;
      document.querySelector(".graph_mask").querySelector(".graph_value").style.width = avgRating * 20 + "%";
      //아래함수에서 소개 정보 추가
      this.addIntroduction(json);
    },
    trimString: function(input, length) {
    	return input.substring(0, length);
    },
    addComments: function(json) {
      var commentsTemplate = document.querySelector("#commentTemplate").innerHTML;
      var ulClassNameListShortReview = document.querySelector(".list_short_review");
      var bindCommentsTemplate = Handlebars.compile(commentsTemplate);
      var countCommentsInfoArea = document.querySelector(".grade_area>.join_count>.green");
      var commentsLengthLimit = 0;
      var bindResultHTML = "";

      Handlebars.registerHelper('emailTrim', function(input) {
    	var trimmedEmail = this.trimString(input, 4);
        return trimmedEmail;
      }.bind(this));
      //표시해야 할 DATE FORMAT에 맞춰 출력
      Handlebars.registerHelper('customDateFormat', function(input) {
    	  var today = new Date(input);
    	  var year = today.getFullYear();
    	  var month = today.getMonth() + 1;
    	  var date = today.getDate();
    	  var resultDate = "" + year + ". " +  month + ". " + date + ".";
    	  
    	  return resultDate;
      })
      //SCORE가 소수점을 가지지 않을 때(n.0) 소수점을 표시.
      Handlebars.registerHelper('scoreToFloat', function(input) {
        return input.toFixed(1);
      });
      //코멘트 추가하기
      ulClassNameListShortReview.innerHTML = "";
      if (json.comments.length > 3)
        commentsLengthLimit = 3;
      else
        commentsLengthLimit = json.comments.length;
      for (var i = 0; i < commentsLengthLimit; i++){
        bindResultHTML += bindCommentsTemplate(json.comments[i]);
      }
      ulClassNameListShortReview.innerHTML = bindResultHTML;
      //건수 등록
      countCommentsInfoArea.innerText = json.comments.length + "건";
      //아래함수에서 평점 정보 나타냄
      this.addAverageScore(json);
    },
    addEvent: function(json) {
      var eventTemplate = document.querySelector("#eventTemplate").innerHTML;
      var eventBindTemplate = Handlebars.compile(eventTemplate);
      var divClassNameEventInfo = document.querySelector(".event_info");
      var bindResultHTML = "";

      bindResultHTML += eventBindTemplate(json.displayInfo);
      divClassNameEventInfo.innerHTML += bindResultHTML;
      //아래함수에서 리뷰 추가
      this.addComments(json);
    },
    addTemplateUnderUlClassNameImage: function(json) {
      var visualTemplate = document.querySelector("#container_visual_template").innerHTML;
      var ulClassNameVisualImg = document.querySelector(".visual_img");
      var visualBindTemplate = Handlebars.compile(visualTemplate);
      var offSpan = document.querySelector(".off").querySelector("span");
      var contentArea = document.querySelector(".store_details").querySelector(".dsc");
      var i;

      //이미지 채움.
      for (i = 0; i < 2 && i < json.productImages.length; i++)
    	  ulClassNameVisualImg.innerHTML += visualBindTemplate(json.productImages[i]);
      var countImages = (i == 1 ? 1 : 2);
      //이미지에 글자 채움.
      document.querySelectorAll(".visual_txt_tit").forEach((v) => {
        v.firstElementChild.innerText = json.displayInfo.productDescription;
      })
      //전체 이미지 개수 표시
      offSpan.innerText = " " + countImages;
      //.figure_pagination 영역 초기화
      ulClassNameVisualImg.style.width = (ulClassNameVisualImg.offsetWidth * countImages) + "px";
      ulClassNameVisualImg.style.transition = "transform 2s ease-in-out";
      document.querySelector(".figure_pagination").firstElementChild.innerText = "1";
      //설명칸에 설명 넣기
      contentArea.innerHTML += json.displayInfo.productContent;
      //아래 함수로 이벤트 정보 추가하기
      this.addEvent(json);
    },
    getDisplayInfoByAjax: function(url) {
      var oReq = new XMLHttpRequest;

      oReq.open('GET', url);
      oReq.setRequestHeader("Content-type", "application/json");
      oReq.responseType = "text";
      oReq.addEventListener('load', function() {
        addImageAddTemplateObj.addTemplateUnderUlClassNameImage(JSON.parse(this.responseText));
      });
      oReq.send();
    },
    getParamValueFromUrl: function(paramName) {
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
  }

  var addEventToPrevNextBtnObj = {
    /*-----------prev, next 버튼에 이벤트 등록----------*/
    getNextUlClassNameVisualImg: function(flag) {
      var currentImageNumberSpan = document.querySelector(".figure_pagination").firstElementChild;
      var curr = currentImageNumberSpan.innerText * 1;
      var limitCurr = document.querySelector(".off").querySelector("span").innerText * 1;
      var ulClassNameVisualImg = document.querySelector(".visual_img");
      const VISUAL_IMG_WIDTH = 414;
      
      if (flag === "toTheRight")
        if (++curr > limitCurr)
          curr = 1;
      if (flag === "toTheLeft")
        if (--curr <= 0)
          curr = limitCurr;
      ulClassNameVisualImg.style.transform = "translate3d(-" + (curr - 1) * VISUAL_IMG_WIDTH + "px, 0px, 0px)";
      currentImageNumberSpan.innerText = "" + curr;
    },
    addEventToPrevNextBtn: function() {
      document.querySelector(".btn_prev").addEventListener('click', function() {
        this.getNextUlClassNameVisualImg("toTheLeft");
      }.bind(this));
      document.querySelector(".btn_nxt").addEventListener('click', function() {
        this.getNextUlClassNameVisualImg("toTheRight");
      }.bind(this));
    }
  }

  var addDisplayInfoIdObj = {
    addDisplayInfoId: function() {
      /*--------------- review 뒤에 id 부여하기. ------------ */
      document.querySelector(".btn_review_more").href = ("review?id=" + addImageAddTemplateObj.getParamValueFromUrl("id"));
      /*-----예매하기에 리다이렉트 이벤트 추가 -------*/
      document.querySelector(".bk_btn").addEventListener("click", function() {
        location.href = "reserve?id=" + addImageAddTemplateObj.getParamValueFromUrl("id");
      })
    }
  }

  /*-------------- 펼쳐보기, 접기 ---------------*/
  var viewMoreWithJquery = {
    viewMore: function() {
      $(".bk_more").each(function() {
        $(this).on('click', function() {
          if ($("._open").css("display") == "none") {
            $("._open").css("display", "block");
            $("._close").css("display", "none");
          } else {
            $("._open").css("display", "none");
            $("._close").css("display", "block");
          }
          $(".store_details").toggleClass("close3");
        })
      });
    }
  }
  
  addImageAddTemplateObj.getDisplayInfoByAjax(
    "/reservationManagement/api/products/" + addImageAddTemplateObj.getParamValueFromUrl("id"));
  addEventToPrevNextBtnObj.addEventToPrevNextBtn();
  addDisplayInfoIdObj.addDisplayInfoId();
  viewMoreWithJquery.viewMore();
});
