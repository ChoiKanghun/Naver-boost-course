package kr.or.connect.reservationManagement.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.EnrollCommentImage;

@Repository
public class EnrollCommentImageDao {
	private SimpleJdbcInsert enrollCommentImageInsert;
	
	public EnrollCommentImageDao(DataSource dataSource) {
		enrollCommentImageInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("reservation_user_comment_image")
				.usingGeneratedKeyColumns("id");
	}
	
	public int enrollCommentImage(EnrollCommentImage enrollCommentImage) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(enrollCommentImage);
		return enrollCommentImageInsert.executeAndReturnKey(params).intValue();
	}
}
