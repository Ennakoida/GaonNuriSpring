package kr.co.gaonnuri.hanbok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.hanbok.service.HanbokService;
import kr.co.gaonnuri.user.domain.User;
import kr.co.gaonnuri.user.service.UserService;

@Controller
public class HanbokController {
	
	@Autowired
	private HanbokService service;
	@Autowired
	private UserService userService;
	
	// 대여 가능 한복 목록 페이지
	@RequestMapping(value="/hanbok/rental.do", method=RequestMethod.GET)
	public String showRentalMenuForm() {
		return "hanbok/rentals";
	}
	
	// 한복 상세 페이지 (로그인 필요)
	@RequestMapping(value="/hanbok/detail.do", method=RequestMethod.GET)
	public String showRentalDetailForm(String userId
									 , @RequestParam("select-place") String rentalPlace
									 , @RequestParam("select-date") @DateTimeFormat(pattern="yyyy-MM-dd") java.util.Date rentalUtilDate
									 , Model model) {
		
		try {
			java.sql.Date rentalDate = new java.sql.Date(rentalUtilDate.getTime());
			User user = userService.selectOneById(userId);
			
			if(user != null) {
				Hanbok hanbok = new Hanbok(rentalPlace, rentalDate);
				model.addAttribute("hanbok", hanbok);
				return "hanbok/detail";
			} else {
				// 회원일 경우에만 대여 가능
				model.addAttribute("msg", "한복 대여");
				model.addAttribute("url", "/user/login.do");
				return "common/needLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 한복 대여 기능
	@RequestMapping(value="/hanbok/detail.do", method=RequestMethod.POST)
	public String rentalHanbok(@RequestParam("userId") String rentalUser
								, String rentalPlace
								, @RequestParam("rentalDate") @DateTimeFormat(pattern="yyyy-MM-dd") java.util.Date rentalUtilDate
								, @RequestParam("top-color") String rentalTopColor
								, @RequestParam("top-size") String rentalTopSize
								, @RequestParam("pants-color") String rentalPantsColor
								, @RequestParam("pants-size") String rentalPantsSize
								, @RequestParam(value="accessories", required = false) String rentalAccessories
								, Model model) {
		
		try {
			java.sql.Date rentalDate = new java.sql.Date(rentalUtilDate.getTime());
			String rentalHanbok = "[ 여성용 ] 여름 한복 세트";
			int rentalPrice = 89000;
			Hanbok hanbok = new Hanbok(rentalUser, rentalPlace, rentalDate, rentalHanbok, rentalTopColor, rentalTopSize, rentalPantsColor, rentalPantsSize, rentalAccessories, rentalPrice);
			int result = service.insertHanbok(hanbok);
			
			if(result > 0) {
				model.addAttribute("msg", "한복 대여");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("msg", "한복 대여");
				model.addAttribute("url", "/hanbok/rental.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("msg", e.getMessage());
				return "common/errorMessage";
		}
			
	}
	
}
