package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.EnrollComment;
import kr.or.connect.reservationManagement.dto.EnrollCommentImage;
import kr.or.connect.reservationManagement.dto.EnrollImageFile;

public interface CommentService {
	int LIMIT_COMMENT = 3;
	float setCommentImages(List<Comments> comments);
	List<Comments> getComments(Integer displayInfoId);
	int enrollComment(EnrollComment enrollComment);
	int enrollImageFile(EnrollImageFile enrollImageFile);
	int enrollCommentImage(EnrollCommentImage enrollCommentImage);
}
