package kr.or.connect.reservationManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.DisplayInfoDao;
import kr.or.connect.reservationManagement.dao.DisplayInfoImageDao;
import kr.or.connect.reservationManagement.dao.ItemsDao;
import kr.or.connect.reservationManagement.dao.ProductImagesDao;
import kr.or.connect.reservationManagement.dao.ProductPricesDao;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.service.ProductPromotionService;

@Service
public class ProductPromotionServiceImpl implements ProductPromotionService {

	@Autowired
	ItemsDao productsDao;
	@Autowired
	DisplayInfoDao displayInfoDao;
	@Autowired
	DisplayInfoImageDao displayInfoImageDao;
	@Autowired
	ProductImagesDao productImagesDao;
	@Autowired
	ProductPricesDao productPricesDao;

	@Override
	public List<Items> getLimitedProducts(Integer start){
		return productsDao.selectLimitedProducts(start, ProductPromotionService.LIMIT);
	}

	@Override
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId){
		return productsDao.selectLimitedProductsByCategoryId(start, ProductPromotionService.LIMIT, categoryId);
	}
	
	@Override
	public List<Items> getCategoriesInfoGroupByCategoryId(){
		return (productsDao.selectCategoriesInfoGroupByCategoryId());
	}

	@Override
	public int getAllCountOfProduct() {
		return productsDao.getCountOfProduct();
	}
	
	@Override
	public int getAllCountProductByCategoryId(Integer categoryId) {
		return productsDao.getCountOfProductByCategoryId(categoryId);
	}

	@Override
	public List<Items> getPromotionInfo(){
		return (productsDao.getPromotionInfo());
	}

	@Override
	public List<ProductImages> getProductImages(Integer displayInfoId){
		return productImagesDao.getProductImages(displayInfoId);
	}
	
	@Override
	public List<ProductPrices> getProductPrices(Integer displayInfoId){
		return productPricesDao.getProductPrices(displayInfoId);
	}
	
	@Override
	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId) {
		return displayInfoImageDao.getDisplayInfoImage(displayInfoId);
	}
	
	@Override
	public DisplayInfo getDisplayInfo(Integer displayInfoId) {
		return displayInfoDao.getDisplayInfo(displayInfoId);
	}
	
	@Override
	public void setPriceType(List<ProductPrices> productPrices) {
		for (ProductPrices productPrice : productPrices) {
			String priceTypeName = productPrice.getPriceTypeName();
			switch (priceTypeName) {
				case "A" : productPrice.setPriceTypeName("성인"); break;
				case "B" : productPrice.setPriceTypeName("유아"); break;
				case "Y" : productPrice.setPriceTypeName("청소년"); break;
				case "S" : productPrice.setPriceTypeName("셋트"); break;
				case "D" : productPrice.setPriceTypeName("장애인"); break;
				case "C" : productPrice.setPriceTypeName("지역주민"); break;
				case "E" : productPrice.setPriceTypeName("어얼리버드"); break;
				case "V" : productPrice.setPriceTypeName("VIP"); break;
				case "R" : productPrice.setPriceTypeName("R석"); break;
				default : productPrice.setPriceTypeName("invalid"); break;
			}
		}
	}

}
