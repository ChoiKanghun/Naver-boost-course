package kr.or.connect.reservationManagement.controller.bak;
/*package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImage;
import kr.or.connect.reservationManagement.service.ReservationManagementService;


@RestController
public class ReservationManagementAPIController {
	
	@Autowired
	ReservationManagementService reservationManagementService;
	
	@GetMapping(path = "/productImages/{id}")
	@ResponseBody
	public String productImages(@PathVariable(name = "id") int id,
			@RequestParam(name = "type", required=true, defaultValue="0")String type) {
		ProductImage productImage = reservationManagementService.getProductImageByProductId(id, type);

		String imageLocation 
		= "img/" + productImage.getProductId() + "_" + productImage.getType() + "_" 
				+ productImage.getId() + ".png";  
		
		return imageLocation;
	}
	
	@GetMapping(path = "/products", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> reservationManagementProducts(
			@RequestParam(name = "start", required = false, defaultValue="0")int start,
			@RequestParam(name = "categoryId", required = false, defaultValue = "0")int categoryId)
	{
		Map <String, Object> map = new HashMap<>();
		if (categoryId == 0)
		{
			List<Items> items = reservationManagementService.getLimitedProducts(start);
			int totalCount = reservationManagementService.getAllCountProduct();

			map.put("items", items);
			map.put("totalCount", totalCount);

			System.out.println("API GET /products(categoryId=0) : " + items);
			System.out.println("API GET /products c_id=" + totalCount);

		}
		else if (categoryId == -1) {
			List<Items> items = reservationManagementService.getAllProducts();
			map.put("items", items);
		}
		else
		{
			List<Items> items = reservationManagementService.getLimitedProductsByCategoryId(start, categoryId);
			int totalCountByCategoryId = reservationManagementService.getAllCountProductByCategoryId(categoryId);
			
			map.put("items", items);
			map.put("totalCount", totalCountByCategoryId);
			
			System.out.println("API GET /products (categoryId=" + categoryId + ") : " + items);
			System.out.println("API GET /products c_id=" + totalCountByCategoryId);

		}
		return map;
	}
}
*/