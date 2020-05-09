package kr.or.connect.reservationManagement.dao.bak;
/*package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_TH;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.PromotionImage;

@Repository
public class PromotionImageDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<PromotionImage> rowMapper
		= BeanPropertyRowMapper.newInstance(PromotionImage.class);
	
	public PromotionImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<PromotionImage> selectTh(){
		Map<String, String> params = new HashMap<>();
		params.put("type", "th");

		return jdbc.query(SELECT_TH, params, rowMapper);
	}
}
*/