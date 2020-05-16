package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_COMMENT_IMAGE_BY_RESERVATION_USER_COMMENT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.CommentImages;

@Repository
public class CommentImagesDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImages> rowMapper
		= BeanPropertyRowMapper.newInstance(CommentImages.class);
	
	public CommentImagesDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<CommentImages> getCommentImages(Integer reservationUserCommentId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationUserCommentId", reservationUserCommentId);
		return jdbc.query(SELECT_COMMENT_IMAGE_BY_RESERVATION_USER_COMMENT_ID, params, rowMapper);
	}
}
