package kr.or.connect.reservationManagement.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.dto.ReserveItem;
import kr.or.connect.reservationManagement.service.ReservationService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationsAPIController {
	@Autowired
	private ReservationService reservationService;

	@GetMapping(path = "")
	public Map<String, Object> getReservationsByReservationEmail(
			@RequestParam String reservationEmail) {
		Map<String, Object> resBody = new HashMap<>();
		List<Reservations> reservations = reservationService.getReservations(reservationEmail);

		reservationService.setDisplayInfoOfReservations(reservations);
		resBody.put("reservations", reservations);
		resBody.put("size", reservations.size());
		return (resBody);
	}

	@PostMapping(path = "")
	public ReserveItem reservation(@ModelAttribute ReserveItem reserveItem,
			@RequestParam String reservationYearMonthDay) {
		reserveItem.setReservationDate(reservationYearMonthDay);
		reserveItem = reservationService.reserveAnItem(reserveItem, reserveItem.getReserveItemPrices());
		return reserveItem;
	}
}