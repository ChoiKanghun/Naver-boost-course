package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.COUNT_ALL_PRODUCT;
import static kr.or.connect.reservationManagement.dao.Sqls.COUNT_PRODUCT_BY_CATEGORY_ID;
import static kr.or.connect.reservationManagement.dao.Sqls.PROMOTION_INFO;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_ALL_PRODUCTS;
import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_CATEGORIES_INFO_GROUP_BY_CATEGORY_ID;
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
public class ItemsDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Items> rowMapper
		= BeanPropertyRowMapper.newInstance(Items.class);
	
	public ItemsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	//get LIMITED result of everything from product table and place_name from display_info
	public List<Items> selectLimitedProducts(Integer start, Integer limit){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbc.query(SELECT_LIMIT_PRODUCTS, params, rowMapper);
	}

	//get LIMITED result of everyting from product table and place_name from display_info
	//WHERE CategoryId = ?
	public List<Items> selectLimitedProductsByCategoryId(Integer start, Integer limit, Integer categoryId){
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryId", categoryId);
		
		return jdbc.query(SELECT_LIMIT_PRODUCTS_BY_CATEGORY_ID, params, rowMapper);
	}
	
	
	// get everything from product table and place_name from display_info
	public List<Items> selectAllProducts(){
		return jdbc.query(SELECT_ALL_PRODUCTS, Collections.emptyMap(), rowMapper);
	}
	

	//get category info group by id
	public List<Items> selectCategoriesInfoGroupByCategoryId(){
		return jdbc.query(SELECT_CATEGORIES_INFO_GROUP_BY_CATEGORY_ID, Collections.emptyMap(), rowMapper);
	}
	
	
	//get Count of everything from product table
	public int getCountOfProduct() {
		return jdbc.queryForObject(COUNT_ALL_PRODUCT, Collections.emptyMap(), Integer.class);
	}
	
	//get Count of records from product WHERE categoryId = ?
	public int getCountOfProductByCategoryId(Integer categoryId) {
		Map<String, Object> params = new HashMap<>();
		params.put("categoryId", categoryId);
		
		return jdbc.queryForObject(COUNT_PRODUCT_BY_CATEGORY_ID, params, Integer.class);
	}
	

	//get all the promotion info
		public List<Items> getPromotionInfo(){
			return jdbc.query(PROMOTION_INFO, Collections.emptyMap(), rowMapper);
		}



		
		

}
