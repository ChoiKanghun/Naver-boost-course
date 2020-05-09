package kr.or.connect.todo.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import kr.or.connect.todo.dto.TodoDto;


public class TodoDao {
	private static final String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbuser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public int updateTodo(TodoDto todoDto) {
		int insertCount = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)){
			if (todoDto.getType().equals("TODO")) {
				ps.setString(1, "DOING");
			}else {
				ps.setString(1, "DONE");
			}
			ps.setLong(2, todoDto.getId());
			insertCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return insertCount;
	}
	
	public int addTodo(TodoDto todoDto) {
		int insertCount = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO todo(title, name, sequence) VALUES(?, ?, ?)";
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setString(1, todoDto.getTitle());
			ps.setString(2, todoDto.getName());
			ps.setInt(3, todoDto.getSequence());
			insertCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return insertCount;
	}
	
	public List<TodoDto> getTodos(){
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM todo ORDER BY regDate";
		try(Connection conn = DriverManager.getConnection(dburl, dbuser, dbpasswd);
				PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String regDate = rs.getString("regDate");
					int sequence = rs.getInt("sequence");
					String title = rs.getString("title");
					String type = rs.getString("type");

					
					TodoDto todoDto = new TodoDto(id, name, regDate, sequence, title, type);
					list.add(todoDto);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		
		return list;
	}
}