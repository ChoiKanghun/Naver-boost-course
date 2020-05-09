var btns = document.querySelectorAll(".btn");

var httpRequest;

function alertContents() 
{
  if (httpRequest.readyState === XMLHttpRequest.DONE) {
    if (httpRequest.status === 200) {
    	console.log("success!");
    } else {
      	console.log('request에 뭔가 문제가 있어요.');
    }
  }
};

function moveToDone(){
	var li_id = this.id.replace('btn_', '');
	var btnValue = this.value;
	
	function makeRequest() 
	{
	    httpRequest = new XMLHttpRequest();
	    
	    if(!httpRequest) {
	      alert('cannot make XMLHTTP instance');
	      return false;
	    }
	    httpRequest.onreadystatechange = alertContents;

	    httpRequest.open('POST', 'todotypeservlet');
	    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    httpRequest.send('id=' + li_id + '&type=' + btnValue);
	}
	
	makeRequest();
	
	document.querySelector("#ul_done").appendChild(document.querySelector('#li_' + li_id));
};

function moveToDoing(){
	
	var li_id = this.id.replace('btn_', '');
    document.querySelector('#ul_doing').appendChild(document.querySelector('#li_' + li_id));
    
	var btnValue = this.value;

	function makeRequest() 
	{
	    httpRequest = new XMLHttpRequest();
	    
	    if(!httpRequest) {
	      alert('cannot make XMLHTTP instance');
	      return false;
	    }
	    
	    httpRequest.onreadystatechange = alertContents;
	    
	    httpRequest.open('POST', 'todotypeservlet');
	    httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	    httpRequest.send('id=' + li_id + '&type=' + btnValue);
	}
    
	makeRequest();
	
	this.value = "DOING";
    this.addEventListener('click', moveToDone);
    this.removeEventListner('click', moveToDoing);
};

var commonButton_todo = document.querySelectorAll("#ul_todo button");
[].forEach.call(commonButton_todo, function(btn){
	btn.addEventListener('click', moveToDoing);
});
var commonButton_doing = document.querySelectorAll("#ul_doing button");
[].forEach.call(commonButton_doing, function(btn){
	btn.addEventListener('click', moveToDone);
});
