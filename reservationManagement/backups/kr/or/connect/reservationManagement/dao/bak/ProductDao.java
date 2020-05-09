package kr.or.connect.reservationManagement.dao.bak;
/*package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.COUNT_ALL_PRODUCT;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_ALL_PRODUCT;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.Product;
@Repository
public class ProductDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper
		= BeanPropertyRowMapper.newInstance(Product.class);
	
	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public int countProduct() {
		return jdbc.queryForObject(COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
	}
	
	public List<Product> selectAll(){
		return jdbc.query(SELECT_ALL_PRODUCT, Collections.emptyMap(), rowMapper);
	}
}
*/