package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.GET_DELETE_RESULT;
import static kr.or.connect.reservationManagement.dao.Sqls.GET_DELETE_RESULT_PRICES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
@Repository
public class DeleteResultDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DeleteReservationResult> rowMapperForDeleteResult
		= BeanPropertyRowMapper.newInstance(DeleteReservationResult.class);
	private RowMapper<DeleteReservationPrices> rowMapperForPrices
		= BeanPropertyRowMapper.newInstance(DeleteReservationPrices.class);
	
	public DeleteResultDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DeleteReservationResult getDeleteResult(Integer reservationId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationId", reservationId);
		return jdbc.queryForObject(GET_DELETE_RESULT, params, rowMapperForDeleteResult);
	}
	
	public List<DeleteReservationPrices> getDeleteResultPrices(Integer reservationId){
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationId", reservationId);
		return jdbc.query(GET_DELETE_RESULT_PRICES, params, rowMapperForPrices);
	}
}
