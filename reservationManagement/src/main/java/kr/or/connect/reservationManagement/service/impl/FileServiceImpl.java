package kr.or.connect.reservationManagement.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.service.FileService;

@Service
public class FileServiceImpl implements FileService {

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
		saveFileName += fileExtName;
		return saveFileName;
	}
}
