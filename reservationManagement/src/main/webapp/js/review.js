window.addEventListener('DOMContentLoaded', () => {

  var addCommentsObj = {
    getParamValueFromUrl : function(paramName){
      var resultParamValue = "";
      //location.search는 url에서 ?를 찾아내줌.
      var paramString = location.search.substr(1);
      var splitedString = paramString.split("&");
      for (var i = 0; i < splitedString.length; i++) {
        var tempArray = splitedString[i].split("=");
        if (tempArray[0] == paramName)
          resultParamValue = tempArray[1];
      }
      return resultParamValue;
    },
    addAverageScore : function(json){
      var avgRating = (json.averageScore * 1).toFixed(1);
      var title = document.querySelector(".top_title .title");
      var homepage = "#";

      document.querySelector(".text_value").querySelector("span").innerText = avgRating;
      document.querySelector(".graph_mask").querySelector(".graph_value").style.width = avgRating * 20 + "%";
      title.innerText = json.displayInfo.productDescription;
      //title에 홈페이지 링크 걸기
      if (json.displayInfo.homepage) {
        homepage = json.displayInfo.homepage;
      }
      title.href = homepage;
    },
    addComments : function(json){
      var commentsTemplate = document.querySelector("#commentTemplate").innerHTML;
      var ulClassNameListShortReview = document.querySelector(".list_short_review");
      var bindCommentsTemplate = Handlebars.compile(commentsTemplate);
      var countCommentsInfoArea = document.querySelector(".grade_area>.join_count>.green");
      var commentsLengthLimit = 0;
      var bindResultHTML = "";

      //핸들바에 {{reservationEmail}}용 함수 등록.
      Handlebars.registerHelper('emailTrim', function(input) {
        return new Handlebars.SafeString(input.substring(0, 4));
      });
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
      for (var i = 0; i < json.comments.length; i++) {
        bindResultHTML += bindCommentsTemplate(json.comments[i]);
      }
      ulClassNameListShortReview.innerHTML = bindResultHTML;
      //건수 등록
      countCommentsInfoArea.innerText = json.comments.length + "건";
      //아래함수에서 평점 정보 나타냄
      this.addAverageScore(json);
    },
    getDisplayInfoByAjax : function(url){
      var oReq = new XMLHttpRequest;

      oReq.open('GET', url);
      oReq.setRequestHeader("Content-type", "application/json");
      oReq.responseType = "text";
      oReq.addEventListener('load', function() {
    	addCommentsObj.addComments(JSON.parse(this.responseText));
      });
      oReq.send();
    }
  }
  addCommentsObj.getDisplayInfoByAjax("/reservationManagement/api/products/" + addCommentsObj.getParamValueFromUrl("id"));
  /* ----------주소로부터 id 가져와서 버튼클릭 시 뒤로 갈 수 있도록 구현. --------------*/
  document.querySelector(".btn_back").href = ("detail?id=" + addCommentsObj.getParamValueFromUrl("id"));

});
