package kr.or.connect.reservationManagement.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.connect.reservationManagement.dto.Reservations;
import kr.or.connect.reservationManagement.service.ReservationService;

@Controller
public class ReservationManagementController {

	@Autowired
	ReservationService reservationService;
	
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
	
	@RequestMapping(path = "/checkMyBook")
	public String checkMyBook(
			@RequestParam String reservationEmail,
			RedirectAttributes redirectAttr, 
			HttpSession session) {
		List<Reservations> reservations = reservationService.getReservations(reservationEmail);
		if (reservations.size() <= 0)
	  		redirectAttr.addFlashAttribute("reservationEmail", "none");
		else {
			redirectAttr.addFlashAttribute("reservationEmail", reservationEmail);
			session.setAttribute("sessionReservationEmail", reservationEmail);
		}
		return "redirect:myreservation";
	}

	@GetMapping(path = "reviewWrite")
	public String reviewWrite(@RequestParam String reservationEmail, 
			@RequestParam String reservationInfoId,
			@RequestParam String productId,
			@RequestParam String productDescription,
			HttpServletRequest request) {
		request.setAttribute("reservationEmail", reservationEmail);
		request.setAttribute("productId", productId);
		request.setAttribute("reservationInfoId", reservationInfoId);
		request.setAttribute("productDescription", productDescription);
		return "reviewWrite";
	}
}
