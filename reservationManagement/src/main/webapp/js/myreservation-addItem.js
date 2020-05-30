function deleteCancellationButton(){
	var unDeletableItems = document.querySelectorAll(".used");
	
	unDeletableItems.forEach(function(item){
		item.querySelectorAll(".card_item").forEach(function(article){
			article.querySelector(".booking_cancel").style.display = "none";
		})
	});
}

function addReserveInfo(json, arrayToSaveWhereToAddArticle){
	var articleTemplateHTML = document.querySelector("#article_template").innerHTML;
	var articleBindTemplate = Handlebars.compile(articleTemplateHTML);
	
	Handlebars.registerHelper('parseDateAndDay', function(stringDate){
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
	json.reservations.forEach(function(oneReservation, index){
		var targetToAddArticle;
		var flagString = arrayToSaveWhereToAddArticle[index];

		if (flagString === "cancel")
			targetToAddArticle = document.querySelector(".card.used.cancel");
		else if (flagString === "alreadyPerformed")
			targetToAddArticle = document.querySelectorAll(".card.used")[0];
		else
			targetToAddArticle = document.querySelector(".card.confirmed");
		targetToAddArticle.innerHTML += articleBindTemplate(oneReservation);
	});
	deleteCancellationButton();
}

function addSummaryContents(json){
	var total = document.querySelector(".ico_book2").parentElement.querySelector(".figure");
	total.innerHTML = json.size;
	var cancelledPerformCount = 0;
	var toBePerformedCount = 0;
	var alreadyPerformedCount = 0;
	var reservationsArray = json.reservations;
	var now = new Date().setMonth(new Date().getMonth());
	var arrayToSaveWhereToAddArticle = [];
	
	reservationsArray.forEach(function(oneReservation, index){
		var year = oneReservation.reservationDate.substr(0, 4);
		var month = oneReservation.reservationDate.substr(5, 2);
		var day = oneReservation.reservationDate.substr(8, 2);
		var performDate = new Date(year, month - 1, day).getTime();

		if (json.cancelFlag){
			cancelledPerformCount++;
			arrayToSaveWhereToAddArticle[index] = "cancel";
		}
		else if (now > performDate){
			alreadyPerformedCount++;
			arrayToSaveWhereToAddArticle[index] = "alreadyPerformed";
		}
		else{
			toBePerformedCount++;
			arrayToSaveWhereToAddArticle[index] = "toBePerformed";
		}
	});
	var toBePerformedTarget = document.querySelector(".ico_book_ss").parentElement.querySelector(".figure");
	var alreadyPerformedTarget = document.querySelector(".ico_check").parentElement.querySelector(".figure");
	var cancelledPerformTarget = document.querySelector(".ico_back").parentElement.querySelector(".figure");
	
	toBePerformedTarget.innerHTML = toBePerformedCount;
	alreadyPerformedTarget.innerHTML = alreadyPerformedCount;
	cancelledPerformTarget.innerHTML = cancelledPerformCount;
	
	addReserveInfo(json, arrayToSaveWhereToAddArticle);
}

function getReservationInfoByAjax(url) {
    var oReq = new XMLHttpRequest;

    oReq.open('GET', url);
    oReq.setRequestHeader("Content-type", "application/json");
    oReq.responseType = "text";
    oReq.addEventListener('load', function() {
      addSummaryContents(JSON.parse(this.responseText));
    });
    oReq.send();
}

function checkMyBookExists(){
	var reservationEmail = document.querySelector("#reservationEmailFromServer").innerText;

	if (reservationEmail === "" || reservationEmail === "none"){
		document.querySelector(".wrap_mylist").style.display = "none";
		document.querySelector(".my_summary").style.display = "none";
	}
	else {
		document.querySelector(".err").style.display = "none";
		getReservationInfoByAjax("/reservationManagement/api/reservations?reservationEmail=" + reservationEmail);
	}
}

checkMyBookExists()


