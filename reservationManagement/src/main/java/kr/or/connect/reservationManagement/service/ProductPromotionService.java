package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;

public interface ProductPromotionService {
	int LIMIT = 4;
	int getAllCountOfProduct();	
	int getAllCountProductByCategoryId(Integer categoryId);
	List<Items> getLimitedProducts(Integer start);
	List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId);
	List<Items> getPromotionInfo();
	List<Items> getCategoriesInfoGroupByCategoryId();
	List<ProductImages> getProductImages(Integer displayInfoId);
	List<ProductPrices> getProductPrices(Integer displayInfoId);
	DisplayInfoImage getDisplayInfoImage(Integer displayInfoId);
	DisplayInfo getDisplayInfo(Integer displayInfoId);
}
