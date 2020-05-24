package kr.or.connect.reservationManagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservationManagement.dao.CommentImagesDao;
import kr.or.connect.reservationManagement.dao.CommentsDao;
import kr.or.connect.reservationManagement.dao.DeleteResultDao;
import kr.or.connect.reservationManagement.dao.DetailPageItemsDao;
import kr.or.connect.reservationManagement.dao.DisplayInfoDao;
import kr.or.connect.reservationManagement.dao.DisplayInfoImageDao;
import kr.or.connect.reservationManagement.dao.ItemsDao;
import kr.or.connect.reservationManagement.dao.ProductImagesDao;
import kr.or.connect.reservationManagement.dao.ProductPricesDao;
import kr.or.connect.reservationManagement.dao.ReservationCommentsDao;
import kr.or.connect.reservationManagement.dao.ReservationsDao;
import kr.or.connect.reservationManagement.dao.ReserveItemDao;
import kr.or.connect.reservationManagement.dto.CommentImages;
import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.DetailPageItems;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.dto.ReservationComments;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Service
public class ReservationManagementServiceImpl implements ReservationManagementService {

	@Autowired
	ItemsDao productsDao;
	@Autowired
	DetailPageItemsDao detailPageItemsDao;
	@Autowired
	ReservationCommentsDao reservationCommentsDao;
	@Autowired
	CommentImagesDao commentImagesDao;
	@Autowired
	CommentsDao commentsDao;	
	@Autowired
	DisplayInfoDao displayInfoDao;
	@Autowired
	DisplayInfoImageDao displayInfoImageDao;
	@Autowired
	ProductImagesDao productImagesDao;
	@Autowired
	ProductPricesDao productPricesDao;
	@Autowired
	ReservationsDao reservationsDao;
	@Autowired
	DeleteResultDao deleteResultDao;
	@Autowired
	ReserveItemDao reserveItemDao;
	
	@Override
	public List<Items> getAllProducts(){
		return productsDao.selectAllProducts();
	}
	
	@Override
	public List<Items> getLimitedProducts(Integer start){
		return productsDao.selectLimitedProducts(start, ReservationManagementService.LIMIT);
	}

	@Override
	public List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId){
		return productsDao.selectLimitedProductsByCategoryId(start, ReservationManagementService.LIMIT, categoryId);
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
	public List<DetailPageItems> getDetailListItems(Integer id){
		return (detailPageItemsDao.getDetailPageItemsById(id));
	}
	
	@Override
	public List<ReservationComments> getUserCommentsById(Integer id){
		return reservationCommentsDao.getUserCommentsById(id);
	}
	
	@Override
	public List<ReservationComments> getLimitedUserCommentsById(Integer id, Integer limit){
		return reservationCommentsDao.getLimitedUserCommentById(id, limit);
	}
	
	@Override
	public List<CommentImages> getCommentImages(Integer reservationUserCommentId){
		return commentImagesDao.getCommentImages(reservationUserCommentId);
	}
	
	@Override
	public List<Comments> getComments(Integer displayInfoId){
		return commentsDao.getComments(displayInfoId);
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
	public List<Reservations> getReservations(String reservationEmail){
		return reservationsDao.getReservations(reservationEmail);
	}

	@Override
	public DeleteReservationResult getDeleteResult(Integer reservationId) {
		return deleteResultDao.getDeleteResult(reservationId);
	}
	
	@Override
	public List<DeleteReservationPrices> getDeleteResultPrices(Integer reservationId) {
		return deleteResultDao.getDeleteResultPrices(reservationId);
	}
	
	@Override
	public ReserveItem reserveAnItem(ReserveItem reserveItem,
			String reservationYearMonthDay) {
		reserveItem.setCreateDate(new Date());
		reserveItem.setModifyDate(new Date());
		reserveItem.setReservationDate(reservationYearMonthDay);
		int reservationInfoId = reserveItemDao.reserveAnItem(reserveItem);
		reserveItem.setReservationInfoId(reservationInfoId);
		
		return reserveItem;
	}
}
