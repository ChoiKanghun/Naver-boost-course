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