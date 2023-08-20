package kr.co.gaonnuri.user.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.user.domain.User;
import kr.co.gaonnuri.user.service.UserService;

@Controller
@SessionAttributes("userId")
public class UserController {

	@Autowired
	private UserService service;
	
	// 회원가입 페이지 
	@RequestMapping(value="/user/enroll.do", method=RequestMethod.GET)
	public String showRegisterForm() {
		return "user/enroll";
	}
	
	// 회원가입 기능
	@RequestMapping(value="/user/enroll.do", method=RequestMethod.POST)
	public String enrollUser(@RequestParam("user-id") String userId
						     , @RequestParam("user-pw") String userPw
						     , @RequestParam("user-name") String userName
						     , @RequestParam("user-phone") String userPhone
						     , @RequestParam("user-email") String userEmail
						     , @RequestParam(value="user-event-yn", required=false) String userEventYn
						     , Model model) {
		
		try {
			userEventYn = userEventYn != null ? "Y" : "N";
			User user = new User(userId, userPw, userName, userPhone, userEmail, userEventYn);
			
			// 회원가입
			int result = service.insertUser(user);
			
			if(result > 0) {
				// 성공
				model.addAttribute("msg", "회원가입");
				model.addAttribute("url", "/user/login.do");
				return "common/serviceSuccess";
			} else {
				// 실패
				model.addAttribute("msg", "회원가입");
				model.addAttribute("url", "/user/enroll.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 로그인 페이지
	@RequestMapping(value="/user/login.do", method=RequestMethod.GET)
	public String showLoginForm() {
		return "user/login";
	}
	
	// 로그인 기능
	@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
	public String LoginUser(@RequestParam("user-id") String userId
							, @RequestParam("user-pw") String userPw
							, Model model) {
		try {
			User user = new User(userId, userPw);
			User uOne = service.selectCheckLogin(user);
			if(uOne != null) {
				// 로그인 성공
				model.addAttribute("userId", uOne.getuserId()); // 세션에 아이디 저장
				model.addAttribute("msg", "로그인");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceSuccess";
			} else {
				// 로그인 실패
				model.addAttribute("msg", "로그인");
				model.addAttribute("url", "/user/login.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 로그아웃 기능
	@RequestMapping(value="/user/logout.do", method=RequestMethod.GET)
	public String userLogout(SessionStatus session, Model model) {
		try {
			if(session != null) {
				session.setComplete();
				if(session.isComplete()) {
					// 세션 만료 유효성 체크
				}
				return "redirect:/index.jsp";
			} else {
				model.addAttribute("msg", "로그아웃");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 아이디 찾기 페이지
	@RequestMapping(value="/user/findId.do", method=RequestMethod.GET)
	public String showFindIdForm() {
		return "user/find-id";
	}
	
	// 아이디 찾기 기능
	@RequestMapping(value="/user/findId.do", method=RequestMethod.POST)
	public String FindUserId(@RequestParam("user-phone") String userPhone, Model model) {
		try {
			User uOne = service.selectIdByPhone(userPhone);
			
			if(uOne != null) {
				model.addAttribute("what", "아이디");
				model.addAttribute("found", uOne.getuserId());
				model.addAttribute("url", "/user/login.do");
				return "user/successFinding";
			} else {
				model.addAttribute("what", "아이디");
				model.addAttribute("msg", "일치하는 아이디가 없습니다.");
				model.addAttribute("url", "/user/findId.do");
				return "user/failedFinding";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 비밀번호 찾기 페이지
	@RequestMapping(value="/user/findPw.do", method=RequestMethod.GET)
	public String showFindPwForm() {
		return "user/find-pw";
	}
	
	// 비밀번호 찾기 기능
	@RequestMapping(value="/user/findPw.do", method=RequestMethod.POST)
	public String FindUserPw(@RequestParam("user-id") String userId
							 , @RequestParam("user-phone") String userPhone
							 , Model model) {
		try {
			Random rnd =new Random();
			StringBuffer buf =new StringBuffer();
			
			String newPassword = null; // 임의의 새 비밀번호
			
			User user = new User();
			user.setuserId(userId);
			user.setuserPhone(userPhone);
			int resultCount = service.selectPwByIdPhone(user);
			
			if(resultCount == 1) {
				for(int i=0;i<10;i++){ // 임의의 새 비밀번호 (영문자열) 추출
			        buf.append((char)((int)(rnd.nextInt(26))+97));
				}
				newPassword = buf.toString();
				User updatePass = new User(userId, newPassword);
				service.updatePw(updatePass); // 임의의 새 비밀번호 업데이트
				
				model.addAttribute("what", "새로운 비밀번호");
				model.addAttribute("found", newPassword);
				model.addAttribute("url", "/user/login.do");
				return "user/successFinding";
			} else {
				model.addAttribute("what", "비밀번호");
				model.addAttribute("msg", "일치하는 가입 정보가 없습니다. 아이디 / 전화번호를 확인해주세요.");
				model.addAttribute("url", "/user/findPw.do");
				return "user/failedFinding";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 마이페이지 페이지
	@RequestMapping(value="/user/myInfo.do", method=RequestMethod.GET)
	public String showMyInfo(@RequestParam("userId") String userId, Model model) {
		try {
			// 내 정보
			User user = service.selectOneById(userId);
			
			// 행사 예매 내역
			List<Reserve> rList = service.selectAllReservesById(userId);
			
			// 한복 대여 내역
			List<Hanbok> hList = service.selectAllRentalsById(userId);
			
			// 나의 질문
			
			if(user != null) {
				model.addAttribute("user", user);
				model.addAttribute("rList", rList);
				model.addAttribute("hList", hList);
				return "user/my-info";
			} else {
				model.addAttribute("msg", "마이페이지 조회");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 회원 정보 수정 기능
	@RequestMapping(value="/user/modify.do", method=RequestMethod.POST)
	public String modifyUser(@RequestParam("user-id") String userId
						       , @RequestParam("user-pw") String userPw
						       , @RequestParam("user-phone") String userPhone
						       , @RequestParam("user-email") String userEmail
						       , @RequestParam(value="user-event-yn", required=false) String userEventYn
						       , Model model) {
		try {
			userEventYn = userEventYn != null ? "Y" : "N";
			User user = new User(userId, userPw, userPhone, userEmail, userEventYn);
			
			int result = service.updateUser(user);
			
			if(result > 0) {
				model.addAttribute("msg", "내 정보 수정");
				model.addAttribute("url", "/user/myInfo.do?userId=" + userId);
				return "common/serviceSuccess";
			} else {
				model.addAttribute("msg", "내 정보 수정");
				model.addAttribute("url", "/user/myInfo.do?userId=" + userId);
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 회원 탈퇴 기능
	@RequestMapping(value="/user/delete.do", method=RequestMethod.GET)
	public String deleteUser(SessionStatus session
							 , @RequestParam("userId") String userId
							 , Model model) {
		try {
			int result = service.deleteUser(userId);
			
			if(result > 0) {
				if(session != null) {
					session.setComplete();
					if(session.isComplete()) {
						// 세션 만료 유효성 체크
					}
				}
				model.addAttribute("msg", "회원 탈퇴");
				model.addAttribute("url", "/index.jsp");
				return "common/serviceSuccess";
			} else {
				model.addAttribute("msg", "회원 탈퇴");
				model.addAttribute("url", "/user/myInfo.do?userId=" + userId);
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
}
