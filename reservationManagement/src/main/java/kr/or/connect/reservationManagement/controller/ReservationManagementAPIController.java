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
	ReservationManagementService reservationManagementService;

	//API for : /products
	@GetMapping(path = "/api/products", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reservationManagementProducts(
			@RequestParam(defaultValue="0") int start,
			@RequestParam(defaultValue = "0") int categoryId)
	{
		Map <String, Object> map = new HashMap<>();
		if (categoryId == 0)
		{
			List<Items> items = reservationManagementService.getLimitedProducts(start);
			int totalCount = reservationManagementService.getAllCountProduct();

			map.put("items", items);
			map.put("totalCount", totalCount);
		}
		else
		{
			List<Items> items = reservationManagementService.getLimitedProductsByCategoryId(start, categoryId);
			int totalCountByCategoryId = reservationManagementService.getAllCountProductByCategoryId(categoryId);
			
			map.put("items", items);
			map.put("totalCount", totalCountByCategoryId);
			

		}
		return map;
	}

	//get promotion info
	@GetMapping(path = "/api/promotions", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getPromotionInformation(){
		List<Items> items = reservationManagementService.getPromotionInfo();
		Map<String, Object> map = new HashMap<>();
		
		map.put("items", items);
		return (map);
	}
	
	//get categories info
	@GetMapping(path = "/api/categories", produces = "application/json; chaset=utf-8")
	@ResponseBody
	public Map<String, Object> getCategories(){
		List<Items> items = reservationManagementService.getCategories();
		Map<String, Object> map = new HashMap<>();
		
		map.put("items", items);
		return (map);
	}
}
