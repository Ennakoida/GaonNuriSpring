package kr.co.gaonnuri.event.reservation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.event.reservation.domain.ReserveData;
import kr.co.gaonnuri.event.reservation.service.ReserveService;
import kr.co.gaonnuri.user.domain.User;
import kr.co.gaonnuri.user.service.UserService;

@Controller
public class ReserveController {
	
	@Autowired
	private ReserveService service;

	@Autowired
	private UserService userService;
	
	// 행사 예매 페이지
	@RequestMapping(value="/event/reservation.do", method=RequestMethod.GET)
	public String showReservationForm(String userId, Model model) {
//		try {
//			User user = userService.selectOneById(userId);
//			
//			if(user != null) {
//				model.addAttribute("user", user);
//				return "event/reservation";
//			} else {
//				// 회원일 경우에만 예매 가능
//				model.addAttribute("msg", "행사 예매");
//				model.addAttribute("url", "/user/login.do");
//				return "common/needLogin";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", e.getMessage());
//			return "common/errorMessage";
//		}
		
		return "event/reservation";
	}
	
	// 행사 예매 페이지 select option 전환
	@RequestMapping(value="/event/rInfoCheck.do", method=RequestMethod.GET)
	public String checkReservationInfo(String selectPlace, String userId, Model model) {
		try {
			// 행사 이름 매핑
			Map<String, String> placeMap = new HashMap<>();
	        placeMap.put("cgg", "궁궐 일상모습 재현 및 체험");
	        placeMap.put("cdg-1", "달빛기행 1차");
	        placeMap.put("cdg-2", "달빛기행 2차");
	        placeMap.put("jm-1", "묘현례 1차");
	        placeMap.put("jm-2", "묘현례 2차");
	        placeMap.put("dsg", "밤의 석조전");
	        placeMap.put("gbg-1", "별빛야행 1차");
	        placeMap.put("gbg-2", "별빛야행 2차");
	        
	        String selectedPlace = placeMap.get(selectPlace);
			List<ReserveData> rDataList = service.selectOptionsbyPlace(selectedPlace);
			
			if(!rDataList.isEmpty()) {
				User user = userService.selectOneById(userId);
				
				if(user != null) {
					model.addAttribute("user", user);
					model.addAttribute("selectPlace", selectPlace);
					model.addAttribute("rData", rDataList);
					return "event/reservation";
				} else {
					// 회원일 경우에만 예매 가능
					model.addAttribute("msg", "행사 예매");
					model.addAttribute("url", "/user/login.do");
					return "common/needLogin";
				}
			} else {
				model.addAttribute("msg", "해당 행사에 대한 데이터 불러오기");
				model.addAttribute("url", "/event/reservation.do");
				return "common/serviceFailed";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 행사 예매 기능
	@RequestMapping(value="/event/reservation.do", method=RequestMethod.POST)
	public String makeReservation(@RequestParam("userId") String reserveUser // 로그인한 사용자 아이디
								, @RequestParam("select-place") String reservePlace
								, @RequestParam("select-date") @DateTimeFormat(pattern = "yyyy-MM-dd") java.util.Date reserveUtilDate
								, @RequestParam("select-time") String reserveTime
								, @RequestParam("select-people") int reservePeople
								, @RequestParam("user-name") String reserveName
								, @RequestParam("user-phone") String reservePhone
								, @RequestParam("user-email") String reserveEmail
								, Model model) {
		try {
			java.sql.Date reserveDate = new java.sql.Date(reserveUtilDate.getTime());
			// 행사 이름 매핑
			Map<String, String> placeMap = new HashMap<>();
	        placeMap.put("cgg", "궁궐 일상모습 재현 및 체험");
	        placeMap.put("cdg-1", "달빛기행 1차");
	        placeMap.put("cdg-2", "달빛기행 2차");
	        placeMap.put("jm-1", "묘현례 1차");
	        placeMap.put("jm-2", "묘현례 2차");
	        placeMap.put("dsg", "밤의 석조전");
	        placeMap.put("gbg-1", "별빛야행 1차");
	        placeMap.put("gbg-2", "별빛야행 2차");
	        
	        String reservePlaceName = placeMap.get(reservePlace);
			Reserve reserve = new Reserve(reserveUser, reservePlaceName, reserveDate, reserveTime, reservePeople, reserveName, reservePhone, reserveEmail);
			
			int result = service.insertReserve(reserve);
			
			if(result > 0) {
				model.addAttribute("msg", "행사 예매");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("msg", "행사 예매");
				model.addAttribute("url", "/event/reservation.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
}
