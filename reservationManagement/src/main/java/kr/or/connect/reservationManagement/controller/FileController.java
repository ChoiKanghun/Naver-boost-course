package kr.or.connect.reservationManagement.controller;

import java.io.IOException;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/file")
public class FileController {
	@GetMapping
	public void downloadImageBySaveFileName(@RequestParam String saveFileName,
			HttpServletResponse response) throws IOException {
		StringBuilder sb = new StringBuilder("file:///c:/tmp/");
		sb.append(saveFileName);
        URL fileUrl = new URL(sb.toString());
        IOUtils.copy(fileUrl.openStream(), response.getOutputStream());
	}
}
