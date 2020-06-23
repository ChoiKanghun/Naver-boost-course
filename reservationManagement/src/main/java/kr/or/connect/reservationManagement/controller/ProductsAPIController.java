package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.service.CommentService;
import kr.or.connect.reservationManagement.service.ProductPromotionService;

@RestController
@RequestMapping("/api/products")
public class ProductsAPIController {
	@Autowired
	private ProductPromotionService productPromotionService;
	@Autowired
	private CommentService commentService;
	
	@GetMapping(path="", produces = "application/json; charset=utf-8")
	public Map<String, Object> getItemsTotalCount(@RequestParam(defaultValue = "0") int start,
			@RequestParam(defaultValue = "0") int categoryId) {
		Map<String, Object> resBody = new HashMap<>();
		if (categoryId == 0) {
			List<Items> items = productPromotionService.getLimitedProducts(start);
			int totalCount = productPromotionService.getAllCountOfProduct();

			resBody.put("items", items);
			resBody.put("totalCount", totalCount);
		} else {
			List<Items> items = productPromotionService.getLimitedProductsByCategoryId(start, categoryId);
			int totalCountByCategoryId = productPromotionService.getAllCountProductByCategoryId(categoryId);

			resBody.put("items", items);
			resBody.put("totalCount", totalCountByCategoryId);
		}
		return resBody;
	}
	
	@GetMapping(path = "/{displayInfoId}")
	public Map<String, Object> getProductsByDisplayInfoId(@PathVariable(name = "displayInfoId") int displayInfoId) {
		Map<String, Object> resBody = new HashMap<>();
		List<Comments> comments = commentService.getComments(displayInfoId);
		float averageScore = commentService.setCommentImages(comments);

		DisplayInfo displayInfo = productPromotionService.getDisplayInfo(displayInfoId);
		DisplayInfoImage displayInfoImage = productPromotionService.getDisplayInfoImage(displayInfoId);
		List<ProductImages> productImages = productPromotionService.getProductImages(displayInfoId);
		List<ProductPrices> productPrices = productPromotionService.getProductPrices(displayInfoId);
		productPromotionService.setPriceType(productPrices);

		resBody.put("comments", comments);
		resBody.put("averageScore", averageScore);
		resBody.put("displayInfo", displayInfo);
		resBody.put("displayInfoImage", displayInfoImage);
		resBody.put("productImages", productImages);
		resBody.put("productPrices", productPrices);
		return (resBody);
	}
}