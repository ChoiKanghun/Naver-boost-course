package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.GET_LIMITED_USER_COMMENTS_BY_ID;
import static kr.or.connect.reservationManagement.dao.Sqls.GET_USER_COMMENTS_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ReservationComments;

@Repository
public class ReservationCommentsDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationComments> rowMapper 
		= BeanPropertyRowMapper.newInstance(ReservationComments.class);
	
	public ReservationCommentsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ReservationComments> getUserCommentsById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("id", id);
		return jdbc.query(GET_USER_COMMENTS_BY_ID, params, rowMapper);
	}
	
	public List<ReservationComments> getLimitedUserCommentById(Integer id, Integer limit){
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("id", id);
		params.put("limit", limit);
		return jdbc.query(GET_LIMITED_USER_COMMENTS_BY_ID, params, rowMapper);
	}
}
