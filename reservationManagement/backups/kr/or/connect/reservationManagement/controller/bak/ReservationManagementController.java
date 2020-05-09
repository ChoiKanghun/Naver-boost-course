package kr.or.connect.reservationManagement.controller.bak;
/*package kr.or.connect.reservationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.Product;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.PromotionImage;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Controller
public class ReservationManagementController {

	@Autowired
	ReservationManagementService reservationManagementService;
	
	@GetMapping(path = "/mainpage")
	public String mainpage(ModelMap model) {
		
		List<PromotionImage> promotionThList = reservationManagementService.getPromotionThImages();
		int productCount = reservationManagementService.getProductCount();
		List<DisplayInfo> displayInfoList = reservationManagementService.getAllDisplayInfo();
		List<Product> productList = reservationManagementService.getAllProduct();
		List<Items> products = reservationManagementService.getAllProducts();
		
		System.out.println("Controller /mainpage : " + products);
		model.addAttribute("promotionThList", promotionThList);
		model.addAttribute("productCount", productCount);
		model.addAttribute("displayInfoList", displayInfoList);
		model.addAttribute("productList", productList);
		model.addAttribute("products", products);
		
		return "mainpage";
	}
}
*/