const PROMOTION_WIDTH = 414;

function promotion_image_move(image_count){
	var curr = 0;
	var visual_img = document.querySelector(".visual_img");

	//recursively executes setTimeout Method to make effect
	function set_t_out(){
		setTimeout(function(){
		if (curr != 0)
		{
			visual_img.style.transition = "transform 2s ease-in-out";
		}
		else
		{
			visual_img.style.transition = "";
		}
		visual_img.style.transform = "translate3d(-" + (curr) * PROMOTION_WIDTH + "px, 0px, 0px)";

		//when the page get the last promotion image, 
		//it goes back to the first one
		curr++;
		if( curr === image_count) {
			curr = 0;
		}
		set_t_out();
	}, 2000);
}
	//executes the function
	set_t_out();

}

//draws promotion images
function addThImages(oReq){
	var json = JSON.parse(oReq.responseText);
	var promotionItemHTML = document.querySelector("#promotionImages").innerHTML;
	var promotionItemResult = "";
	var visual_img = document.querySelector(".visual_img");
	var image_count = 0;

	//get product image url from json and add to src path
	//add promotionImages templates under '.visual_img'
	for(var i = 0; i < json.items.length; i++){
		promotionItemResult += promotionItemHTML.replace("{src}", json.items[i].productImageUrl);
	};
	visual_img.innerHTML = promotionItemResult;
	
	//count all the li tags to expand the promotion image area
	visual_img.querySelectorAll("li").forEach(function() {
		image_count++;
	});
	visual_img.style.width = (visual_img.offsetWidth * image_count) + "px";
	
	//show images one by one
	promotion_image_move(image_count);

};

//get responseData and send it to parameter of function 'addThImages'
function sendAjax(url){
	var oReq = new XMLHttpRequest;
	oReq.open('GET', url)
	oReq.setRequestHeader("Content-type", "application/json");
	oReq.responseType = "text";
	oReq.addEventListener('load', function(){
		addThImages(this);
	});
	oReq.send();
};

//sends ajax
sendAjax("/reservationManagement/api/promotions");