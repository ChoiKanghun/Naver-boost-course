package kr.or.connect.reservationManagement.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.Items;

@Repository
public class GetSaveFileNameByReservationUserCommentImageIdDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Items> rowMapper
	= BeanPropertyRowMapper.newInstance(Items.class);
}
