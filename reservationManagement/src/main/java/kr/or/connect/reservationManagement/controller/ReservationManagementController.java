package kr.or.connect.reservationManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.service.ReservationManagementService;

@Controller
public class ReservationManagementController {

	@Autowired
	ReservationManagementService reservationManagementService;
	
	@GetMapping(path = "/mainpage")
	public String mainpage() {
		return "mainpage";
	}
	
	@GetMapping(path = "/detail")
	public String detailPage() {
		return "detail";
	}
	
	@GetMapping(path = "/review")
	public String reviewPage() {
		return "review";
	}
	
	@GetMapping(path = "/reserve")
	public String reservePage() {
		return "reserve";
	}
	
	@GetMapping(path = "/myreservation")
	public String myreservationPage() {
		return "myreservation";
	}
	
	@GetMapping(path = "/bookinglogin")
	public String bookingloginPage() {
		return "bookinglogin";
	}
	
	@PostMapping(path = "/checkMyBook")
	public String checkMyBook(
			@RequestParam(name="reservationEmail", required=true) String reservationEmail,
			RedirectAttributes redirectAttr) {
		List<Reservations> reservations = reservationManagementService.getReservations(reservationEmail);
		if (reservations.size() <= 0)
	  		redirectAttr.addFlashAttribute("reservationEmail", "none");
		else
			redirectAttr.addFlashAttribute("reservationEmail", reservationEmail);
		return "redirect:myreservation";
	}
}
