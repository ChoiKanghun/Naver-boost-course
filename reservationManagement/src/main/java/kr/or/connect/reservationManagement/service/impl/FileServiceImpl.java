package kr.or.connect.reservationManagement.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

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
	public String genereateSaveFileName(String fileExtName) {
		String saveFileName = "";
		Calendar calendar = Calendar.getInstance();
		
		saveFileName += calendar.get(Calendar.YEAR);
		saveFileName += calendar.get(Calendar.MONTH);
		saveFileName += calendar.get(Calendar.DATE);
		saveFileName += calendar.get(Calendar.HOUR);
		saveFileName += calendar.get(Calendar.MINUTE);
		saveFileName += calendar.get(Calendar.SECOND);
		saveFileName += calendar.get(Calendar.MILLISECOND);
		saveFileName += (int)(Math.random() * 1000);
		saveFileName += fileExtName;
		return saveFileName;
	}
	
	@Override
	public int saveImage(MultipartFile file) throws IOException {
		FileServiceImpl fileServiceImpl = new FileServiceImpl();
		final String SAVE_PATH = "/tmp/";
		String fileName = file.getOriginalFilename();
		String fileExtName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String saveFileName = fileServiceImpl.genereateSaveFileName(fileExtName);
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
