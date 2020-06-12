package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.Comments;

public interface CommentService {
	int LIMIT_COMMENT = 3;
	float setCommentImages(List<Comments> comments);
	List<Comments> getComments(Integer displayInfoId);

}
