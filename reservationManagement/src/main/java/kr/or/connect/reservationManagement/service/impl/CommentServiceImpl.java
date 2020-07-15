package kr.or.connect.reservationManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.CommentImagesDao;
import kr.or.connect.reservationManagement.dao.CommentsDao;
import kr.or.connect.reservationManagement.dao.EnrollCommentDao;
import kr.or.connect.reservationManagement.dao.EnrollCommentImageDao;
import kr.or.connect.reservationManagement.dao.EnrollImageFileDao;
import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.EnrollComment;
import kr.or.connect.reservationManagement.dto.EnrollCommentImage;
import kr.or.connect.reservationManagement.dto.EnrollImageFile;
import kr.or.connect.reservationManagement.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentImagesDao commentImagesDao;
	@Autowired
	CommentsDao commentsDao;
	@Autowired
	EnrollCommentDao enrollCommentDao;
	@Autowired
	EnrollImageFileDao enrollImageFileDao;
	@Autowired
	EnrollCommentImageDao enrollCommentImageDao;
	
	@Override
	public void setCommentImages(List<Comments> comments) {
		for(int i = 0; i < comments.size(); i++) {
			comments.get(i).setCommentImages(commentImagesDao.getCommentImages(comments.get(i).getCommentId()));
		}
		return;
	}
	
	@Override
	public float getAverageScore(List<Comments> comments) {
		float averageScore = 0;
		float division = 0;
		
		for(int i = 0; i < comments.size(); i++) {
			if (comments.get(i).getScore() != null) {
				averageScore += comments.get(i).getScore();
				division += 1;
			}
		}
		if (division != 0)
			averageScore = averageScore / division;
		return averageScore;
	}
	
	@Override
	public List<Comments> getComments(Integer displayInfoId){
		return commentsDao.getComments(displayInfoId);
	}
	
	@Override
	public int enrollComment(EnrollComment enrollComment) {
		return enrollCommentDao.enrollComment(enrollComment);
	}
	
	@Override
	public int enrollImageFile(EnrollImageFile enrollImageFile) {
		return enrollImageFileDao.enrollImageFile(enrollImageFile);
	}
	
	@Override
	public int enrollCommentImage(EnrollCommentImage enrollCommentImage) {
		return enrollCommentImageDao.enrollCommentImage(enrollCommentImage);
	}
}
