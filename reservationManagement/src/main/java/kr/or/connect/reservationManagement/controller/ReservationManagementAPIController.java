package kr.or.connect.reservationManagement.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.or.connect.reservationManagement.dto.EnrollComment;
import kr.or.connect.reservationManagement.dto.EnrollCommentImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
import kr.or.connect.reservationManagement.service.CommentService;
import kr.or.connect.reservationManagement.service.FileService;
import kr.or.connect.reservationManagement.service.ProductPromotionService;
import kr.or.connect.reservationManagement.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationManagementAPIController {
	@Autowired
	private ProductPromotionService productPromotionService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private FileService fileService;
	
	@GetMapping(path = "/promotions", produces = "application/json; charset=utf-8")
	public Map<String, Object> getPromotionInformation() {
		List<Items> items = productPromotionService.getPromotionInfo();
		Map<String, Object> resBody = new HashMap<>();

		resBody.put("items", items);
		return (resBody);
	}
	@GetMapping(path = "/categories", produces = "application/json; charset=utf-8")
	public Map<String, Object> getCategories() {
		List<Items> items = productPromotionService.getCategoriesInfoGroupByCategoryId();
		Map<String, Object> resBody = new HashMap<>();

		resBody.put("items", items);
		return resBody;
	}

	/* project5 */

	@PutMapping(path = "/cancelReservation")
	public ReserveItem cancelReservation(@RequestParam int reservationInfoId) {
		ReserveItem reserveItem = reservationService.cancelReservation(reservationInfoId);
		List<ReserveItemPrice> prices = reservationService.getResereveItemPriceByReservationInfoId(reservationInfoId);
		reserveItem.setReserveItemPrices(prices);
		return reserveItem;
	}

	@PostMapping(path = "/{reservationInfoId}/comments")
	public void addComment(@ModelAttribute EnrollComment enrollComment,
			@RequestParam(name = "file", required = false) MultipartFile file, HttpServletResponse response)
			throws IOException {
		int fileId = -1;
		int imageId = -1;
		if (file != null) {
			fileId = fileService.saveImage(file);
		}
		int commentId = commentService.enrollComment(enrollComment);
		if (fileId != -1) {
			EnrollCommentImage enrollCommentImage = new EnrollCommentImage();
			enrollCommentImage.setReservationInfoId(enrollComment.getReservationInfoId());
			enrollCommentImage.setFileId(fileId);
			enrollCommentImage.setReservationUserCommentId(commentId);;
			
			imageId = commentService.enrollCommentImage(enrollCommentImage);
		}
	}
}
