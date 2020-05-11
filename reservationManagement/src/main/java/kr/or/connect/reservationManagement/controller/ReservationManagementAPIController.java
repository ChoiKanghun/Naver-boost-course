package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Items;
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

	//get promotion info
	@GetMapping(path = "/api/promotions", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getPromotionInformation(){
		List<Items> items = reservationManagementService.getPromotionInfo();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
	
	//get categories info
	@GetMapping(path = "/api/categories", produces = "application/json; chaset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategories(){
		List<Items> items = reservationManagementService.getCategoriesInfoGroupByCategoryId();
		Map<String, Object> resBody = new HashMap<>();
		
		resBody.put("items", items);
		return (resBody);
	}
}
