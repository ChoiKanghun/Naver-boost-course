function setBasicEvents() {
  var reserveInput = document.querySelector("#resrv_id");
  var confirmForm = document.querySelector("#form1");

  reserveInput.setAttribute("name", "reservationEmail");
  confirmForm.setAttribute("action", "/reservationManagement/checkMyBook");
  confirmForm.setAttribute("method", "POST");
  document.querySelector("#form1").addEventListener("click", function(e) {
    e.preventDefault();
  });
}

function checkEmailValidity() {
  document.querySelector(".login_btn").addEventListener("click", function(e) {
    var reserveInput = document.querySelector("#resrv_id");
    var regExpEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

    if (regExpEmail.test(reserveInput.value)) {
      document.querySelector("#form1").submit();
    } else {
      alert("이메일 입력양식을 지켜주세요!");
    }
  })
}

window.addEventListener("DOMContentLoaded", function() {
  setBasicEvents();
  checkEmailValidity();
})
