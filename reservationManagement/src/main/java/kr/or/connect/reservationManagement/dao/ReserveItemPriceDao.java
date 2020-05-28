package kr.or.connect.reservationManagement.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
@Repository
public class ReserveItemPriceDao {
	private SimpleJdbcInsert insertPrices;
	
	public ReserveItemPriceDao(DataSource dataSource) {
		this.insertPrices = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
	}
	public int reserveAnItemPrice(ReserveItemPrice reserveItemPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reserveItemPrice);
		return insertPrices.executeAndReturnKey(params).intValue();
	}
}
