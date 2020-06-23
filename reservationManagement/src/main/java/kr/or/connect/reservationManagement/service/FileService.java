package kr.or.connect.reservationManagement.service;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	public static String genereateSaveFileName(String fileExtName) {
		String saveFileName = "";
		Calendar calendar = Calendar.getInstance();
		
		saveFileName += calendar.get(Calendar.YEAR);
		saveFileName += calendar.get(Calendar.MONTH);
		saveFileName += calendar.get(Calendar.DATE);
		saveFileName += calendar.get(Calendar.HOUR);
		saveFileName += calendar.get(Calendar.MINUTE);
		saveFileName += calendar.get(Calendar.SECOND);
		saveFileName += calendar.get(Calendar.MILLISECOND);
		saveFileName += fileExtName;
		return saveFileName;
	};
	int downloadImage(MultipartFile file) throws IOException;
}
