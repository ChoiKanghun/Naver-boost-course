package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.CommentImages;
import kr.or.connect.reservationManagement.dto.Comments;
import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.DisplayInfo;
import kr.or.connect.reservationManagement.dto.DisplayInfoImage;
import kr.or.connect.reservationManagement.dto.Items;
import kr.or.connect.reservationManagement.dto.ProductImages;
import kr.or.connect.reservationManagement.dto.ProductPrices;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;

public interface ReservationManagementService {

	int LIMIT = 4;
	int LIMIT_COMMENT = 3;
	int getAllCountOfProduct();	
	int getAllCountProductByCategoryId(Integer categoryId);
	
/*	List<Items> getAllProducts();
	*/
	List<Items> getLimitedProducts(Integer start);
	List<Items> getLimitedProductsByCategoryId(Integer start, Integer categoryId);
	List<Items> getPromotionInfo();
	List<Items> getCategoriesInfoGroupByCategoryId();
/*	
	List<DetailPageItems> getDetailListItems(Integer id);
	List<ReservationComments> getUserCommentsById(Integer id);
	List<ReservationComments> getLimitedUserCommentsById(Integer id, Integer limit);
*/	
	List<CommentImages> getCommentImages(Integer reservationUserCommentId);
	List<Comments> getComments(Integer displayInfoId);
	List<ProductImages> getProductImages(Integer displayInfoId);
	List<ProductPrices> getProductPrices(Integer displayInfoId);
	DisplayInfoImage getDisplayInfoImage(Integer displayInfoId);
	DisplayInfo getDisplayInfo(Integer displayInfoId);
	List<Reservations> getReservations(String reservationEmail);
	List<DeleteReservationPrices> getDeleteResultPrices(Integer reservationId);
	DeleteReservationResult getDeleteResult(Integer reservationId);
	ReserveItem reserveAnItem(ReserveItem reserveItem, List<ReserveItemPrice> prices);
	ReserveItem cancelReservation(Integer reservationInfoId);
	List<ReserveItemPrice> getResereveItemPriceByReservationInfoId(Integer reservationInfoId);
}
