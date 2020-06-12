package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ProductPrices;

@Repository
public class ProductPricesDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductPrices> rowMapper
		= BeanPropertyRowMapper.newInstance(ProductPrices.class);

	public ProductPricesDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<ProductPrices> getProductPrices(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_PRODUCT_PRICES_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
