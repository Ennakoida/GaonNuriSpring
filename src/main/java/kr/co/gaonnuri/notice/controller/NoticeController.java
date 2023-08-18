package kr.co.gaonnuri.notice.controller;

import java.util.List;

import javax.servlet.jsp.tagext.PageData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 공지사항 검색 페이지
	@RequestMapping(value="/notice/search.do", method=RequestMethod.GET)
	public String showSearchNotice(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
								 //, @RequestParam("searchCondition") String searchCondition
								 , @RequestParam("searchKeyword") String searchKeyword
								 , Model model) {
		try {
			page = page != null ? page : "1";
			int currentPage = Integer.parseInt(page);
			PageData pd = service.selectSearchNoticeList(currentPage, noticeSubject);
			List<Notice> sList = pd.getnList();
			String sPageNavi = pd.getPageNavi();
			
			if(!sList.isEmpty()) {
				model.addAttribute("noticeSearch", noticeSubject);
				model.addAttribute("sList", sList);
				model.addAttribute("sPageNavi", sPageNavi);
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
	public String showSearchNotice(@RequestParam("notice-search") String noticeSearch, Model model) {
		model.addAttribute("noticeSearch", noticeSearch);
		return "notice/noticeNone";
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
				return "notice/notice";
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
	public void showModifyNoticeForm(String noticeNum, Model model) {
		int noticeNo = Integer.parseInt(noticeNum);
		Notice notice = service.selectOneByNo(noticeNo);
		
		if(notice != null) {
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/noticeModify.jsp").forward(request, response);
		} else {
		}
	}
	
	// 공지사항 수정 기능
	
	
	
	// 공지사항 삭제 페이지
	@RequestMapping(value="/notice/delete.do", method=RequestMethod.GET)
	public String deleteNotice(@RequestParam("noticeNo") int noticeNo, Model model) {
		try {
	//		int noticeNo = Integer.parseInt(noticeNum);
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
