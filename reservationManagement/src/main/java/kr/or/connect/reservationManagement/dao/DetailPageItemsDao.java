package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.GET_DETAIL_PAGE_ITEMS_BY_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.DetailPageItems;

@Repository
public class DetailPageItemsDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailPageItems> rowMapper
		= BeanPropertyRowMapper.newInstance(DetailPageItems.class);
	
	public DetailPageItemsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	
	public List<DetailPageItems> getDetailPageItemsById(Integer id){
		Map<String, Object> params = new HashMap<>();
		
		params.put("id", id);
		return jdbc.query(GET_DETAIL_PAGE_ITEMS_BY_ID, params, rowMapper);
	}
	
}
