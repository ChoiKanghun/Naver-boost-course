<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>toDoList</title>
  <link href="todoForm.css" rel="stylesheet" type="text/css" />
  <style>

  </style>
</head>

<body>
   <h1>할일 등록</h1>

  <section id="mainSection">
  
  <form method = "post" action = "todoaddservlet" id = "todoAddPost">
  <br>
  어떤일인가요?<br>
  <input class = "todoAddPost_title" type = "text" name = "title" placeholder="swift 공부하기 (24자까지)"
  value = "" maxlength="24" required/>
  <br><br>
  누가 할일인가요?<br>
  <input class = "todoAddPost_name" type = "text" name = "name" placeholder = "홍길동" value="" required/>
  <br><br>
  우선순위를 선택하세요<br>
   <input class = "todoAddPost_sequence" type ="radio" name="sequence" value="1" required> <span>1순위</span>
   <input class = "todoAddPost_sequence" type ="radio" name="sequence" value="2" required> <span>2순위</span>
   <input class = "todoAddPost_sequence" type ="radio" name="sequence" value="3" required> <span>3순위</span>
   <br><br>
   <input class = "backButton" type = "button" value = "&lt이전" onclick="location.href='index.jsp' "/>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   <input class = "submitButton" type = "submit" value = "제출"/>
   <input class = "initButton" type = "button" value = "내용지우기" />
  </form>
</section>
</body>

<script src="todoForm.js"></script>

</html>
