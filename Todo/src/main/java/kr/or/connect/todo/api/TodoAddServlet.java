package kr.or.connect.todo.api;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todo.dao.TodoDao;
import kr.or.connect.todo.dto.TodoDto;

/**
 * Servlet implementation class TodoAddServlet
 */
@WebServlet("/todoaddservlet")
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String stringSequence = request.getParameter("sequence");
		int sequence = Integer.parseInt(stringSequence);
		
		TodoDto todoDto = new TodoDto();
		todoDto.setName(name);
		todoDto.setTitle(title);
		todoDto.setSequence(sequence);
		
		TodoDao todoDao = new TodoDao();
		todoDao.addTodo(todoDto);
		
		response.sendRedirect("main");
		
	}

}
