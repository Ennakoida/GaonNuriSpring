package kr.co.gaonnuri.notice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageInfo;
import kr.co.gaonnuri.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	// 공지사항 목록 페이지
	@RequestMapping(value="/notice/notice.do", method=RequestMethod.GET)
	public String showNoticeList(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
								 , Model model) {
		try {
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			
			if(!nList.isEmpty()) {
				model.addAttribute("nList", nList);
				model.addAttribute("pInfo", pInfo);
				return "notice/notice";
			} else {
				return "redirect:/notice/noticeNone.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 페이징 처리
	public PageInfo getPageInfo(int currentPage, int totalCount) {
		PageInfo pi = null;
		int recordCountPerPage = 10;
		int naviCountPerPage = 5; // 한 페이지당 보여줄 네비게이터의 갯수
		int naviTotalCount;
		int startNavi;
		int endNavi;
		
		naviTotalCount = (int)((double)totalCount / recordCountPerPage + 0.9);
		startNavi = (((int)((double)currentPage / naviCountPerPage + 0.9)) - 1) * naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		
		pi = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
	}
	
	// 공지사항 검색 기능
	@RequestMapping(value="/notice/search.do", method=RequestMethod.GET)
	public String showSearchNotice(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
								 , @RequestParam("searchCondition") String searchCondition
								 , @RequestParam("searchKeyword") String searchKeyword
								 , Model model) {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("searchCondition", searchCondition);
			paramMap.put("searchKeyword", searchKeyword);
			
			int totalCount = service.getListCount(paramMap);
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			
			List<Notice> searchList = service.searchNoticesByKeyword(pInfo, paramMap);
			
			if(!searchList.isEmpty()) {
				model.addAttribute("searchCondition", searchCondition);
				model.addAttribute("searchKeyword", searchKeyword);
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("sList", searchList);
				return "notice/searchNotice";
			} else {
				return "notice/noticeNone";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 공지사항이 존재하지 않을 때 페이지
	@RequestMapping(value="/notice/none.do", method=RequestMethod.GET)
	public String showSearchNotice(@RequestParam("searchCondition") String searchCondition
			 					   , @RequestParam(value="searchKeyword", required=false) String searchKeyword 
			 					   , Model model) {
		model.addAttribute("searchCondition", searchCondition);
		model.addAttribute("searchKeyword", searchKeyword);
		return "notice/noticeNone";
	}
	
	// 공지사항 상세 조회 페이지
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public String showDetailNotice(@RequestParam("noticeNo") int noticeNo, Model model) {
		try {
			Notice notice = service.selectOneByNo(noticeNo);
			
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/noticeDetail";
			} else {
				model.addAttribute("msg", "공지사항 상세 조회");
				model.addAttribute("url", "/notice/notice.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}

	// 공지사항 작성 페이지
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.GET)
	public String showInsertNoticeForm() {
		return "notice/noticeInsert";
	}
	
	// 공지사항 작성 기능
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.POST)
	public String InsertNotice(@RequestParam("notice-subject") String noticeSubject
							   , @RequestParam("notice-content") String noticeContent
							   , Model model) {
		try {
			Notice notice = new Notice(noticeSubject, noticeContent);
			int result = service.insertNotice(notice);
			
			if(result > 0) {
				return "redirect:/notice/notice.do";
			} else {
				model.addAttribute("msg", "공지사항 작성");
				model.addAttribute("url", "/notice/notice.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 공지사항 수정 페이지
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.GET)
	public String showModifyNoticeForm(int noticeNo, Model model) {
		try {
			Notice notice = service.selectOneByNo(noticeNo);
			
			if(notice != null) {
				model.addAttribute("notice", notice);
				return "notice/noticeModify";
			} else {
				model.addAttribute("msg", "수정할 데이터 불러오기");
				model.addAttribute("url", "/notice/notice.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 공지사항 수정 기능
	@RequestMapping(value="/notice/modify.do", method=RequestMethod.POST)
	public String modifyNotice(int noticeNo
							   , @RequestParam("notice-subject") String noticeSubject
							   , @RequestParam("notice-content") String noticeContent
							   , RedirectAttributes redirect
							   , Model model) {
		try {
			Notice notice = new Notice(noticeNo, noticeSubject, noticeContent);
			int result = service.updateNotice(notice);
			
			if(result > 0) {
				// 성공하면 상세 페이지로 이동
				redirect.addAttribute("noticeNo", noticeNo);
				return "redirect:/notice/detail.do";
			} else {
				// 실패하면 에러메세지 출력
				model.addAttribute("msg", "공지사항 수정이 완료되지 않았습니다.");
				model.addAttribute("url", "/notice/update.do?noticeNo=" + noticeNo);
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	
	// 공지사항 삭제 페이지
	@RequestMapping(value="/notice/delete.do", method=RequestMethod.GET)
	public String deleteNotice(int noticeNo, Model model) {
		try {
			int result = service.deleteNotice(noticeNo);
			if(result > 0)
			{
				//성공시 목록으로 이동
				return "redirect:/notice/notice.do";
			} else {
				model.addAttribute("msg", "공지사항 삭제");
				model.addAttribute("url", "/notice/notice.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
}
