var test = document.querySelector("#test");

var category_tab = document.querySelector(".event_tab_lst");
var start = 0;
var moreBtn = document.querySelector(".more");
var current_category = document.querySelector("#current_category").value;

function drawBasicCategory(json){
	var itemListHTML = document.querySelector("#itemList").innerHTML;

	var itemListResult = "<ul class='lst_event_box'>";

	//replace all brackets in itemList template.innerHTML
	//json.items.length would be 1~4
	for(var i = 0; i < json.items.length; i++){
		itemListResult += itemListHTML.replace("{id}", json.items[i].productId)
							.replace("{src}", json.items[i].productImageUrl)
							.replace("{description}", json.items[i].productDescription)
							.replace("{placeName}", json.items[i].placeName)
							.replace("{description}", json.items[i].productDescription)
							.replace("{content}", json.items[i].productContent);
		itemListResult += "</ul>";
		document.querySelector(".more").insertAdjacentHTML("beforebegin", itemListResult);
		itemListResult = "<ul class='lst_event_box'>";



		//start count will be used to delete 'more' button from browser
		start++;
	}
	
	
	if (start == json.totalCount)
		document.querySelector(".more").style.display="none";
}


function makeHtmlContent(url, oReq, category_id){
	//get json text
	var json = JSON.parse(oReq.responseText);
	var lst_event_boxes = document.querySelectorAll(".lst_event_box");
	var event_tab_lst_li = document.querySelector(".event_tab_lst").querySelectorAll("li");
	var pink = document.querySelector(".event_lst_txt").querySelector(".pink");

	//remove all the content in .lst_event_box
	lst_event_boxes.forEach(function(content){content.remove();	});
	
	//change category anchor 
	event_tab_lst_li.forEach(function(list){
		list.firstElementChild.className = 
			(list.dataset.category == category_id) ? 'anchor active' : 'anchor'; 
	});
	
	pink.innerText = json.totalCount + "개";
	
	//draw basic contents of categories 
	drawBasicCategory(json);
	
}


	//send Ajax to get JSON info
function sendProductsAjax(url, flag, category_id){
	var oReq = new XMLHttpRequest;
	
	oReq.open('GET', url);
	oReq.setRequestHeader("Content-type", "application/json");
	oReq.responseType = "text";
	oReq.addEventListener("load", function(){

		if (flag === "tab")
			makeHtmlContent(url, this, category_id);
		if (flag === "more")
			drawBasicCategory(JSON.parse(oReq.responseText));
	});
	oReq.send();

}

category_tab.addEventListener("click", function(evt){
	var category_id = "";

	//get category_id by event bubbling, delegation
	if (evt.target.tagName === "UL"){
		category_id = evt.target.firstChild.dataset.category;
	}else if(evt.target.tagName === "LI"){
		category_id = evt.target.dataset.category;
	}else if(evt.target.tagName === "A"){
		category_id = evt.target.parentElement.dataset.category;
	}else if(evt.target.tagName === "SPAN"){
		category_id = evt.target.parentElement.parentElement.dataset.category;
	}
	
	//current_category is global variable;
	current_category = category_id;
	
	//sendAjax to change the tab;
	start = 0;
	sendProductsAjax("/reservationManagement/api/products?categoryId=" + category_id, "tab", category_id);

	
	//in case more button's style.display is already none
	document.querySelector(".more").style.display="block";
});



	//when the page loads
document.addEventListener("DOMContentLoaded", function(){
	//browser draws main List to ".event_tab_lst"
	sendProductsAjax("/reservationManagement/api/products?categoryId=0", "tab", 0);
});


/* ------------------ more btn ----------------------- */

moreBtn.addEventListener("click", function(evt){
	var category_id = current_category;

	
	//sendAjax to get more category contents
	sendProductsAjax("/reservationManagement/api/products?start=" + start + "&categoryId=" + category_id, "more", category_id);
})


