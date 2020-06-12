package kr.or.connect.reservationManagement.service;

import java.util.List;

import kr.or.connect.reservationManagement.dto.DeleteReservationPrices;
import kr.or.connect.reservationManagement.dto.DeleteReservationResult;
import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.dto.ReserveItemPrice;

public interface ReservationService {
	List<Reservations> getReservations(String reservationEmail);
	List<DeleteReservationPrices> getDeleteResultPrices(Integer reservationId);
	DeleteReservationResult getDeleteResult(Integer reservationId);
	ReserveItem reserveAnItem(ReserveItem reserveItem, List<ReserveItemPrice> prices);
	ReserveItem cancelReservation(Integer reservationInfoId);
	List<ReserveItemPrice> getResereveItemPriceByReservationInfoId(Integer reservationInfoId);
}
