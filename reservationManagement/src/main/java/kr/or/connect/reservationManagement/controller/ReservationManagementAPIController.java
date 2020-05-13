package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.DetailPageItems;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ReservationComments;
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
	
	@GetMapping(path = "/api/comments_by_id", produces = "application/json; charset=utf-8")
	public Map<String, Object> getUserCommentsById(
			@RequestParam(required = true) int id){
		List<ReservationComments> items = reservationManagementService.getUserCommentsById(id);
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	@GetMapping(path = "/api/limited_comments_by_id", produces = "application/json; charset=utf-8")
	public Map<String, Object> getLimitedUserCommentsById(
			@RequestParam(required = true) int id){
		int limit = reservationManagementService.LIMIT_COMMENT;
		List<ReservationComments> items = reservationManagementService.getLimitedUserCommentsById(id, limit);
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
}
