package kr.or.connect.reservationManagement.controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.service.FileService;

@RestController
@RequestMapping("/api/file")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@GetMapping
	public void downloadImageBySaveFileName(@RequestParam int imageId,
			HttpServletResponse response) throws IOException {
		String saveFileName = fileService.getSaveFileNameByReservationUserCommentImageId(imageId);
		StringBuilder sb = new StringBuilder("file:///c:/tmp/");
		sb.append(saveFileName);
        URL fileUrl = new URL(sb.toString());
        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
	}
}
