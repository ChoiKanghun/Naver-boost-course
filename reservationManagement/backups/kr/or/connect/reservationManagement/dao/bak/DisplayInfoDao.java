package kr.or.connect.reservationManagement.dao.bak;
/*package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.SELECT_ALL_DISPLAY_INFO;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.DisplayInfo;

@Repository
public class DisplayInfoDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> rowMapper
		= BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<DisplayInfo> selectAll() {
		return jdbc.query(SELECT_ALL_DISPLAY_INFO, Collections.emptyMap(), rowMapper);
	}
}
*/