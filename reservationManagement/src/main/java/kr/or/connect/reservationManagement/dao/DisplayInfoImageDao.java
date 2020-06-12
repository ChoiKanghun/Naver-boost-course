package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.DisplayInfoImage;

@Repository
public class DisplayInfoImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapper
		= BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	
	public DisplayInfoImageDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
