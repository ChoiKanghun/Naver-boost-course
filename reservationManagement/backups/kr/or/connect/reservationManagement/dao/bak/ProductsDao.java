package kr.or.connect.reservationManagement.dao.bak;
/*package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.COUNT_ALL_PRODUCT;
import static kr.or.connect.reservationManagement.dao.Sqls.COUNT_PRODUCT_BY_CATEGORY_ID;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_ALL_PRODUCTS;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_LIMIT_PRODUCTS;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.Items;

@Repository
public class ProductsDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Items> rowMapper
		= BeanPropertyRowMapper.newInstance(Items.class);
	
	public ProductsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
//get Count of everything from product table
	public int getAllCountProduct() {
		return jdbc.queryForObject(COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
	}
//get Count of records from product WHERE categoryId = ?
	public int getAllCountProductByCategoryId(Integer categoryId) {
		Map<String, String> params = new HashMap<>();
		params.put("categoryId", categoryId.toString());
		
		return jdbc.queryForObject(COUNT_PRODUCT_BY_CATEGORY_ID, params, Integer.class);
	}
//get LIMITED result of everything from product table and place_name from display_info
	public List<Items> selectLimit(Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbc.query(SELECT_LIMIT_PRODUCTS, params, rowMapper);
	}

//get LIMITED result of everyting from product table and place_name from display_info
//WHERE CategoryId = ?

	public List<Items> selectLimitByCategoryId(Integer start, Integer limit, Integer categoryId){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		
		return jdbc.query(SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	}
	
	
// get everyting from product table and place_name from display_info
	public List<Items> selectAll(){
		return jdbc.query(SELECT_ALL_PRODUCTS, Collections.emptyMap(), rowMapper);
	}
	

}*/
