package kr.or.connect.reservationManagement.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservationManagement.dto.EnrollImageFile;
import kr.or.connect.reservationManagement.service.CommentService;
import kr.or.connect.reservationManagement.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	CommentService commentService;
	
	@Override
	public int downloadImage(MultipartFile file) throws IOException {
		final String SAVE_PATH = "c:/tmp/";
		String fileName = file.getOriginalFilename();
		String fileExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String saveFileName = FileService.genereateSaveFileName(fileExtName);
		String contentType = file.getContentType();
		
		byte[] data = file.getBytes();
		FileOutputStream fos = new FileOutputStream(SAVE_PATH + saveFileName);
		fos.write(data);
		fos.close();
		EnrollImageFile enrollImageFile = new EnrollImageFile();
		enrollImageFile.setSaveFileName(saveFileName);
		enrollImageFile.setContentType(contentType);
		enrollImageFile.setFileName(fileName);
		return commentService.enrollImageFile(enrollImageFile);
	}
}
