package kr.or.connect.reservationManagement.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ReserveItem;

@Repository
public class ReserveItemDao {
	private SimpleJdbcInsert insertReservationInfo;


	public ReserveItemDao(DataSource dataSource) {
		this.insertReservationInfo = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info")
				.usingGeneratedKeyColumns("id");

	}
	
	public int reserveAnItem(ReserveItem reserveItem) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reserveItem);
		return insertReservationInfo.executeAndReturnKey(params).intValue();
	}

	
}
