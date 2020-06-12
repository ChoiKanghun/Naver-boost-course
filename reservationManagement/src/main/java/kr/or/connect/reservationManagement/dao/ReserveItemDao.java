package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_ALL_RESERVATION_INFO_BY_RESERVATION_INFO_ID;
import static kr.or.connect.reservationManagement.dao.Sqls.UPDATE_CANCEL_FLAG_BY_RESERVATION_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ReserveItem;

@Repository
public class ReserveItemDao {
	private SimpleJdbcInsert insertReservationInfo;
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReserveItem> rowMapper
		= BeanPropertyRowMapper.newInstance(ReserveItem.class);

	public ReserveItemDao(DataSource dataSource) {
		insertReservationInfo = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");
		jdbc = new NamedParameterJdbcTemplate(dataSource);

	}
	
	public int reserveAnItem(ReserveItem reserveItem) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reserveItem);
		return insertReservationInfo.executeAndReturnKey(params).intValue();
	}
	
	public ReserveItem cancelReserve(Integer reservationInfoId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationInfoId", reservationInfoId);
		jdbc.update(UPDATE_CANCEL_FLAG_BY_RESERVATION_INFO_ID, params);
		return jdbc.queryForObject(SELECT_ALL_RESERVATION_INFO_BY_RESERVATION_INFO_ID, params, rowMapper);
	}
	
}
