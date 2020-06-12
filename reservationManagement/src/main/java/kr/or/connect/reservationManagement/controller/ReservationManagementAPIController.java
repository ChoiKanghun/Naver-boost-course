package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;
import kr.or.connect.reservationManagement.service.CommentService;
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
	
	@GetMapping(path = "/products", produces = "application/json; charset=utf-8")
	public Map<String, Object> getItemsTotalCount(@RequestParam(defaultValue="0") int start, @RequestParam(defaultValue = "0") int categoryId)
	{
		Map <String, Object> resBody = new HashMap<>();
		if (categoryId == 0)
		{
			List<Items> items = productPromotionService.getLimitedProducts(start);
			int totalCount = productPromotionService.getAllCountOfProduct();

			resBody.put("items", items);
			resBody.put("totalCount", totalCount);
		}
		else
		{
			List<Items> items = productPromotionService.getLimitedProductsByCategoryId(start, categoryId);
			int totalCountByCategoryId = productPromotionService.getAllCountProductByCategoryId(categoryId);
			
			resBody.put("items", items);
			resBody.put("totalCount", totalCountByCategoryId);
		}
		return resBody;
	}

	@GetMapping(path = "/promotions", produces = "application/json; charset=utf-8")
	public Map<String, Object> getPromotionInformation() {
		List<Items> items = productPromotionService.getPromotionInfo();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	@GetMapping(path = "/categories", produces = "application/json; charset=utf-8")
	public Map<String, Object> getCategories(){
		List<Items> items = productPromotionService.getCategoriesInfoGroupByCategoryId();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return resBody;
	}
	
	@GetMapping(path = "/products/{displayInfoId}")
	public Map<String, Object> getProductsByDisplayInfoId(
			@PathVariable(name = "displayInfoId") int displayInfoId){
		Map<String, Object> resBody = new HashMap<>();
		List<Comments> comments = commentService.getComments(displayInfoId);
		float averageScore = commentService.setCommentImages(comments);
		
		DisplayInfo displayInfo = productPromotionService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = productPromotionService.getDisplayInfoImage(displayInfoId);
		List<ProductImages> productImages = productPromotionService.getProductImages(displayInfoId);
		List<ProductPrices> productPrices = productPromotionService.getProductPrices(displayInfoId);
		
		for (ProductPrices productPrice : productPrices) {
			String priceTypeName = productPrice.getPriceTypeName();
			if (priceTypeName.equals("A"))
				productPrice.setPriceTypeName("성인");
			else if (priceTypeName.equals("Y"))
				productPrice.setPriceTypeName("청소년");
			else if (priceTypeName.equals("B"))
				productPrice.setPriceTypeName("유아");
			else if (priceTypeName.equals("S"))
				productPrice.setPriceTypeName("셋트");
			else if (priceTypeName.equals("D"))
				productPrice.setPriceTypeName("장애인");
			else if (priceTypeName.equals("C"))
				productPrice.setPriceTypeName("지역주민");
			else if (priceTypeName.equals("E"))
				productPrice.setPriceTypeName("어얼리버드");
			else if (priceTypeName.equals("V"))
				productPrice.setPriceTypeName("VIP");
			else if (priceTypeName.equals("R"))
				productPrice.setPriceTypeName("R석");
			else if (priceTypeName.equals("B"))
				productPrice.setPriceTypeName("B석");
			else if (priceTypeName.equals("S"))
				productPrice.setPriceTypeName("S석");
			else if (priceTypeName.equals("D"))
				productPrice.setPriceTypeName("평일");
		}
		resBody.put("comments", comments);
		resBody.put("averageScore", averageScore);
		resBody.put("displayInfo", displayInfo);
		resBody.put("displayInfoImage", displayInfoImage);
		resBody.put("productImages", productImages);
		resBody.put("productPrices", productPrices);
		return (resBody);
	}
	
	/*project5*/
	@GetMapping(path = "/reservations")
	public Map<String, Object> getReservationsByReservationEmail(
			@RequestParam(name="reservationEmail")String reservationEmail){
		Map<String, Object> resBody = new HashMap<>();
		List<Reservations> reservations = reservationService.getReservations(reservationEmail);
		
		for (int i = 0; i < reservations.size(); i++) {
			reservations.get(i).setDisplayInfo(
					productPromotionService.getDisplayInfo(
							reservations.get(i).getDisplayInfoId()));
		}
		resBody.put("reservations", reservations);
		resBody.put("size", reservations.size());
		return (resBody);
	}

	@PutMapping(path = "/reservations/{reservationId}")
	public DeleteReservationResult getDeleteResult(
			@PathVariable(name = "reservationId") int reservationId) {
		DeleteReservationResult deleteResult = reservationService.getDeleteResult(reservationId);
		List<DeleteReservationPrices> listPrices = reservationService.getDeleteResultPrices(reservationId);
	
		deleteResult.setPrices(listPrices);
		return deleteResult;
	}
	
	@PostMapping(path = "/reservations")
	public ReserveItem reservation(
			@ModelAttribute ReserveItem reserveItem,
			@RequestParam(name="reservationYearMonthDay", required=true) String reservationYearMonthDay) {
		reserveItem.setReservationDate(reservationYearMonthDay);
		reserveItem = reservationService.reserveAnItem(reserveItem, reserveItem.getReserveItemPrices());
		return reserveItem;
	}
	
	@PutMapping(path = "/cancelReservation")
	public ReserveItem cancelReservation(
			@RequestParam(name="reservationInfoId", required=true)int reservationInfoId) {
		ReserveItem reserveItem = reservationService.cancelReservation(reservationInfoId);
		List<ReserveItemPrice> prices = reservationService.getResereveItemPriceByReservationInfoId(reservationInfoId);
		reserveItem.setReserveItemPrices(prices);
		return reserveItem;
	}
}
