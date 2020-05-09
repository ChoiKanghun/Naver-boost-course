<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="kr.or.connect.todo.dto.TodoDto"%>
 <%@ page import = "java.util.Date" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>

 <%
      String todo = (String) request.getAttribute("listTodo");
      String doing = (String) request.getAttribute("listDoing");
      String done = (String) request.getAttribute("listDone");
      ObjectMapper mapper = new ObjectMapper();
      TodoDto[] todoList = mapper.readValue(todo, TodoDto[].class);
      TodoDto[] doingList = mapper.readValue(doing, TodoDto[].class);
      TodoDto[] doneList = mapper.readValue(done, TodoDto[].class);
 %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>toDoList</title>
  <link href="main.css" rel="stylesheet" type="text/css" />
  <style>

  </style>
</head>

<body>
  <header id = mainHeader>
    <div class = "mainHeader_enrollTodo">
      <a href = "todoformservlet">새로운 TODO 등록</a>
    </div>
    <div class = "mainHeader_mustDo">
      나의 해야할 일들
    </div>
  </header>
    <ul id = "ul_todo">
     <li class = "ul_title"><p></p>TODO</li>
    
        <%
		for(TodoDto todoDto : todoList){
			request.setAttribute("todo_id", todoDto.getId());
			request.setAttribute("todo_type", todoDto.getType());
			
			request.setAttribute("todo_title", todoDto.getTitle());
			request.setAttribute("todo_regDate", todoDto.getRegDate().substring(0,10).replaceAll("-","."));
			request.setAttribute("todo_name", todoDto.getName());
			request.setAttribute("todo_sequence", todoDto.getSequence());

		%>

	 <li id = "li_${requestScope.todo_id }">
		<span class="title"><b>${todo_title}</b></span><br>
		<span class ="content">등록날짜:${todo_regDate }, ${todo_name }, 우선순위${todo_sequence }</span>
	 <button id = "btn_${requestScope.todo_id }" class = "btn">-&gt;</button>
	 </li>
		<%
		}
		%>

    </ul>
    <ul id = "ul_doing">
      <li class = "ul_title"><p></p>DOING</li>
       <%
		for(TodoDto todoDto : doingList){
			request.setAttribute("doing_id", todoDto.getId());
			request.setAttribute("doing_type", todoDto.getType());	
	
			request.setAttribute("doing_title", todoDto.getTitle());
			request.setAttribute("doing_regDate", todoDto.getRegDate().substring(0,10).replaceAll("-","."));
			request.setAttribute("doing_name", todoDto.getName());
			request.setAttribute("doing_sequence", todoDto.getSequence());
		%>

	 <li id = "li_${requestScope.doing_id }">
		<span class="title"><b>${doing_title}</b></span><br>
		<span class ="content">등록날짜:${doing_regDate }, ${doing_name }, 우선순위${doing_sequence }</span>
		
	 <button id = "btn_${requestScope.doing_id }" class = "btn">-&gt;</button>
	 </li>
	<%} %>
	</ul>
    <ul id = "ul_done">
      <li class = "ul_title"><p></p>DONE</li>
      <% for(TodoDto todoDto : doneList){
		request.setAttribute("done_id", todoDto.getId());
		request.setAttribute("done_type", todoDto.getType());
		
		request.setAttribute("done_title", todoDto.getTitle());
		request.setAttribute("done_regDate", todoDto.getRegDate().substring(0,10).replaceAll("-","."));
		request.setAttribute("done_name", todoDto.getName());
		request.setAttribute("done_sequence", todoDto.getSequence());
		%>
        <li id = "li_${requestScope.done_id }">
        <span class="title"><b>${done_title}</b></span><br>
		<span class ="content">등록날짜:${done_regDate }, ${done_name }, 우선순위${done_sequence }</span>
	
        </li>
        <%} %>
    </ul>
        
    
</body>
<script src="main.js"></script>

</html>