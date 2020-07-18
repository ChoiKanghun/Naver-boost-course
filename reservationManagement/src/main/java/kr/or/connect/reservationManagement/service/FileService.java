package kr.or.connect.reservationManagement.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public String genereateSaveFileName(String fileExtName);
	int saveImage(MultipartFile file) throws IOException;
	String getSaveFileNameByReservationUserCommentImageId(int reservationUserCommentImageId);
}
