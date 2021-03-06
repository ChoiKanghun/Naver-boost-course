package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_RESERVATIONS_BY_RESERVATION_EMAIL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.Reservations;

@Repository
public class ReservationsDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Reservations> rowMapper 
		= BeanPropertyRowMapper.newInstance(Reservations.class);
	
	public ReservationsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Reservations> getReservations(String reservationEmail){
		Map<String, String> params = new HashMap<>();
		
		params.put("reservationEmail", reservationEmail);
		return jdbc.query(SELECT_RESERVATIONS_BY_RESERVATION_EMAIL, params, rowMapper);
	}
}
