package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.GET_DELETE_RESULT_PRICES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
@Repository
public class ReserveItemPriceDao {
	private SimpleJdbcInsert insertPrices;
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReserveItemPrice> rowMapper
		= BeanPropertyRowMapper.newInstance(ReserveItemPrice.class);
	
	public ReserveItemPriceDao(DataSource dataSource) {
		insertPrices = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_info_price")
				.usingGeneratedKeyColumns("id");
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public int reserveAnItemPrice(ReserveItemPrice reserveItemPrice) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reserveItemPrice);
		return insertPrices.executeAndReturnKey(params).intValue();
	}
	
	public List<ReserveItemPrice> getPricesByReservationInfoId(Integer reservationInfoId){
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationId", reservationInfoId);
		return jdbc.query(GET_DELETE_RESULT_PRICES, params, rowMapper);
	}

}
