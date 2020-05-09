package kr.or.connect.reservationManagement.dao.bak;
/*package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_BY_PRODUCT_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.ProductImage;

@Repository
public class ProductImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductImage> rowMapper
		= BeanPropertyRowMapper.newInstance(ProductImage.class);
	
	public ProductImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ProductImage selectProductImageByProductId(Integer id, String type){
		Map<String, String> params = new HashMap<>();
		params.put("id", id.toString());
		params.put("type", type);
		
		return jdbc.queryForObject(SELECT_BY_PRODUCT_ID, params, rowMapper);
		
		
	}
}
*/