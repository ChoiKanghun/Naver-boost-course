package kr.or.connect.reservationManagement.dao;

import static kr.or.connect.reservationManagement.dao.Sqls.GET_SAVEFILENAME_BY_RESERVATIONUSERINFOID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.GetSaveFileNameByReservationUserCommentImageId;


@Repository
public class GetSaveFileNameByReservationUserCommentImageIdDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<GetSaveFileNameByReservationUserCommentImageId> rowMapper
	= BeanPropertyRowMapper.newInstance(GetSaveFileNameByReservationUserCommentImageId.class);
	
	public GetSaveFileNameByReservationUserCommentImageIdDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<GetSaveFileNameByReservationUserCommentImageId> getSaveFileNameByReservationUserCommentImageId(int reservationUserCommentImageId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("reservation_user_comment_image_id", reservationUserCommentImageId);
		
		return jdbc.query(GET_SAVEFILENAME_BY_RESERVATIONUSERINFOID, params, rowMapper);
	}
}
