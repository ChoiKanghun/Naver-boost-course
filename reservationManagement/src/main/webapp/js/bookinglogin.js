function bookingFormSetting(){
	var reserveInput = document.querySelector("#resrv_id");
	var confirmForm = document.querySelector("#form1");
	
    reserveInput.setAttribute("name", "reservationEmail");
	confirmForm.setAttribute("action", "/reservationManagement/checkMyBook");
	confirmForm.setAttribute("method", "POST");
}
bookingFormSetting();