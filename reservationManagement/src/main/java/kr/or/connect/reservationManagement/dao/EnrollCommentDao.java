package kr.or.connect.reservationManagement.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.EnrollComment;

@Repository
public class EnrollCommentDao {
	private SimpleJdbcInsert insertCommentInfo;

	public EnrollCommentDao(DataSource dataSource) {
		insertCommentInfo = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment")
				.usingGeneratedKeyColumns("id");
	}
	
	public int enrollComment(EnrollComment enrollComment) {
		enrollComment.setCreateDate(new Date());
		enrollComment.setModifyDate(new Date());
		SqlParameterSource params = new BeanPropertySqlParameterSource(enrollComment);
		return insertCommentInfo.executeAndReturnKey(params).intValue();
	}
}
