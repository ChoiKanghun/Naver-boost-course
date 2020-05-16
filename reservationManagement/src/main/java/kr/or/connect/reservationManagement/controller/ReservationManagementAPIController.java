package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.DetailPageItems;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.service.ReservationManagementService;


@RestController
public class ReservationManagementAPIController {
	
	@Autowired
	private ReservationManagementService reservationManagementService;

	@GetMapping(path = "/api/products", produces = "application/json; charset=utf-8")
	public Map<String, Object> getItemsTotalCount(
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue = "0") int categoryId)
	{
		Map <String, Object> resBody = new HashMap<>();
		if (categoryId == 0)
		{
			List<Items> items = reservationManagementService.getLimitedProducts(start);
			int totalCount = reservationManagementService.getAllCountOfProduct();

			resBody.put("items", items);
			resBody.put("totalCount", totalCount);
		}
		else
		{
			List<Items> items = reservationManagementService.getLimitedProductsByCategoryId(start, categoryId);
			int totalCountByCategoryId = reservationManagementService.getAllCountProductByCategoryId(categoryId);
			
			resBody.put("items", items);
			resBody.put("totalCount", totalCountByCategoryId);
			

		}
		return resBody;
	}

	@GetMapping(path = "/api/promotions", produces = "application/json; charset=utf-8")
	public Map<String, Object> getPromotionInformation(){
		List<Items> items = reservationManagementService.getPromotionInfo();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	@GetMapping(path = "/api/categories", produces = "application/json; charset=utf-8")
	public Map<String, Object> getCategories(){
		List<Items> items = reservationManagementService.getCategoriesInfoGroupByCategoryId();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	@GetMapping(path = "/api/detail_page_items", produces = "application/json; charset=utf-8")
	public Map<String, Object> getDetailPageItems(
			@RequestParam(required = true) int id){
		List<DetailPageItems> items = reservationManagementService.getDetailListItems(id);
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	@GetMapping(path = "/api/products/{displayInfoId}")
	public Map<String, Object> getProductsByDisplayInfoId(
			@PathVariable(name = "displayInfoId") int displayInfoId){
		Map<String, Object> resBody = new HashMap<>();
		List<Comments> comments = reservationManagementService.getComments(displayInfoId);
		Float averageScore = 0F;
		Float division = 0F;
		
		for(int i = 0; i < comments.size(); i++) {
			comments.get(i).setCommentImages(reservationManagementService.getCommentImages(comments.get(i).getCommentId()));
			if (comments.get(i).getScore() != null) {
				averageScore += comments.get(i).getScore();
				division += 1;
			}
		}
		if (division != 0F) {
			averageScore = averageScore / division;
		}
		DisplayInfo displayInfo = reservationManagementService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = reservationManagementService.getDisplayInfoImage(displayInfoId);
		List<ProductImages> productImages = reservationManagementService.getProductImages(displayInfoId);
		List<ProductPrices> productPrices = reservationManagementService.getProductPrices(displayInfoId);
		
		resBody.put("comments", comments);
		resBody.put("averageScore", averageScore);
		resBody.put("displayInfo", displayInfo);
		resBody.put("displayInfoImage", displayInfoImage);
		resBody.put("productImages", productImages);
		resBody.put("productPrices", productPrices);
		return (resBody);
	}
	
	

}
