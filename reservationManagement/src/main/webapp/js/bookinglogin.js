/*bookinglogin jsp
	bookinglogin js를 script src로 설정.
	bookinglogin jsp에서 이메일을 입력.
	제출 버튼에 클릭 이벤트 추가.
		폼 태그에 있는 resrv_id의 name을 reservationEmail로 주기.
		폼의 action을 컨트롤러 주소로 설정.

*/

function bookingFormSetting(){
	var reserveInput = document.querySelector("#resrv_id");
	var confirmForm = document.querySelector("#form1");
	
    reserveInput.setAttribute("name", "reservationEmail");
	confirmForm.setAttribute("action", "reservationManagement/api/checkMyBook");
	confirmForm.setAttribute("method", "POST");
}
bookingFormSetting();