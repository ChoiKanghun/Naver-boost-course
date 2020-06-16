package kr.or.connect.reservationManagement.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservationManagement.dto.EnrollImageFile;

@Repository
public class EnrollImageFileDao {
	private SimpleJdbcInsert enrollImageFileInsert;
	
	public EnrollImageFileDao(DataSource dataSource) {
		enrollImageFileInsert = new SimpleJdbcInsert(dataSource)
				.withTableName("file_info")
				.usingGeneratedKeyColumns("id");
	}
	
	public int enrollImageFile(EnrollImageFile enrollImageFile) {
		enrollImageFile.setCreateDate(new Date());
		enrollImageFile.setModifyDate(new Date());
		enrollImageFile.setDeleteFlag(false);
		SqlParameterSource params = new BeanPropertySqlParameterSource(enrollImageFile);
		return enrollImageFileInsert.executeAndReturnKey(params).intValue();
	}
}
