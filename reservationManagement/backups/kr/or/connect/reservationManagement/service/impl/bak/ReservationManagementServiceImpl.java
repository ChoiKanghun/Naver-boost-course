package kr.or.connect.reservationManagement.service.impl.bak;
/*package kr.or.connect.reservationManagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservationManagement.dao.DisplayInfoDao;
import kr.or.connect.reservationManagement.dao.ProductDao;
import kr.or.connect.reservationManagement.dao.ProductImageDao;
import kr.or.connect.reservationManagement.dao.ProductsDao;
import kr.or.connect.reservationManagement.dao.PromotionImageDao;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.Product;
import kr.or.connect.reservationManagement.dto.ProductImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.PromotionImage;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Service
public class ReservationManagementServiceImpl implements ReservationManagementService {

	@Autowired
	PromotionImageDao promotionImageDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	DisplayInfoDao displayInfoDao;
	
	@Autowired
	ProductsDao productsDao;
	
	@Autowired
	ProductImageDao productImageDao;

//---------------PromotionImageDao---------------------------
	@Override
	@Transactional
	public List<PromotionImage> getPromotionThImages() {
		List<PromotionImage> list = promotionImageDao.selectTh();
		return list;
	}
	
//-----------------ProductsDao-------------------------
	@Override
	@Transactional
	public List<Items> getAllProducts(){
		List<Items> list = productsDao.selectAll();
		return list;
	}
	
	@Override
	@Transactional
	public int getAllCountProduct() {
		return productsDao.getAllCountProduct();
	}
	
	@Override
	@Transactional
	public int getAllCountProductByCategoryId(Integer categoryId) {
		return productsDao.getAllCountProductByCategoryId(categoryId);
	}
	
	
	@Override
	@Transactional
	public List<Items> getLimitedProducts(Integer start){
		List<Items> list = productsDao.selectLimit(start, ReservationManagementService.LIMIT);
		
		return list;
	}

	@Override
	@Transactional
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId){
		List<Items> list = productsDao.selectLimitByCategoryId(start, ReservationManagementService.LIMIT, categoryId);
		
		return list;
	}
//---------------------------------ProductDao----------------------------------------
	@Override
	@Transactional
	public int getProductCount() {
		return productDao.countProduct();
	}
	
	@Override
	@Transactional
	public List<Product> getAllProduct() {
		return productDao.selectAll();
	}

//----------------------------------DisplayInfoDao------------------------------------
	@Override
	@Transactional
	public List<DisplayInfo> getAllDisplayInfo(){
		return displayInfoDao.selectAll();
	}

//------------------------ProductImageDao--------------------------------
	@Override
	@Transactional
	public ProductImage getProductImageByProductId(Integer id, String type){
		ProductImage productImage = productImageDao.selectProductImageByProductId(id, type);
		
		return productImage;
	}
	
}*/
