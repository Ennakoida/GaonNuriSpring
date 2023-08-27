package kr.co.gaonnuri.qna.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.gaonnuri.qna.domain.PageInfo;
import kr.co.gaonnuri.qna.domain.Qna;
import kr.co.gaonnuri.qna.domain.Reply;
import kr.co.gaonnuri.qna.service.QnaService;
import kr.co.gaonnuri.qna.service.ReplyService;

@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@Autowired
	private QnaService service;
	@Autowired
	private ReplyService rService;
	
	// Q&A 목록 페이지
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public String showQnaList(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			 					, Model model) {
		try {
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Qna> qList = service.selectQnaList(pInfo);
			
			if(!qList.isEmpty()) {
			model.addAttribute("qList", qList);
			model.addAttribute("pInfo", pInfo);
			return "qna/list";
			} else {
				model.addAttribute("qList", qList);
				model.addAttribute("pInfo", pInfo);
				return "qna/list";
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
	
	// Q&A 검색 기능
	@RequestMapping(value="/search.do", method=RequestMethod.GET)
	public String showSearchQna(@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
								 , @RequestParam("searchCondition") String searchCondition
								 , @RequestParam("searchKeyword") String searchKeyword
								 , Model model) {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("searchCondition", searchCondition);
			paramMap.put("searchKeyword", searchKeyword);
			
			int totalCount = service.getListCount(paramMap);
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			
			List<Qna> searchList = service.searchQnasByKeyword(pInfo, paramMap);
			
			if(!searchList.isEmpty()) {
				model.addAttribute("searchCondition", searchCondition);
				model.addAttribute("searchKeyword", searchKeyword);
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("sList", searchList);
				return "qna/searchQna";
			} else {
				model.addAttribute("searchCondition", searchCondition);
				model.addAttribute("searchKeyword", searchKeyword);
				model.addAttribute("sList", searchList);
				model.addAttribute("pInfo", pInfo);
				return "qna/searchQna";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// Q&A 상세 조회 페이지
	@RequestMapping(value="/detail.do", method=RequestMethod.GET)
	public String showDetailQna(int qnaNo
									, String downloadFile
									, HttpServletRequest request
									, HttpServletResponse response
									, Model model) {
		try {
			Qna qna = service.selectOneByNo(qnaNo);
			Qna qnaView = new Qna(qnaNo, qna.getqViewCount() + 1);
			int result = service.updateViewCount(qnaView);
			qna.setqViewCount(qnaView.getqViewCount());
			
			List<Reply> replyList = rService.selectReplyList(qnaNo);
			if(replyList.size() > 0) {
				model.addAttribute("rList", replyList);
			}
			
			if(qna != null && result > 0) {
				model.addAttribute("qna", qna);
				return "qna/qnaDetail";
			} else if (result <= 0){
				model.addAttribute("msg", "조회수 갱신");
				model.addAttribute("url", "/qna/list.do");
				return "common/serviceFailed";
			} else {
				model.addAttribute("msg", "Q&A 상세 조회");
				model.addAttribute("url", "/qna/list.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}

	// Q&A 작성 페이지
	@RequestMapping(value="/insert.do", method=RequestMethod.GET)
	public String showInsertQnaForm() {
		return "/qna/qnaInsert";
	}
	
	// Q&A 작성 기능
	@RequestMapping(value="/insert.do", method=RequestMethod.POST)
	public String InsertQna(@RequestParam("qna-subject") String qnaSubject
							   , @RequestParam("qna-content") String qnaContent
							   , @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
							   , HttpSession session
							   , HttpServletRequest request
							   , Model model) {
		try {
			Qna qna = new Qna();
			
			if(!uploadFile.getOriginalFilename().equals("")) {
				Map<String , Object> nMap = this.saveFile(uploadFile, request);
				
				String fileName = (String)nMap.get("fileName"); 
				String fileRename = (String)nMap.get("fileRename");
				String savePath = (String)nMap.get("filePath");
				long fileLength = (long)nMap.get("fileLength");
				
				qna.setQnaFileName(fileName);
				qna.setQnaFileRename(fileRename);
				qna.setQnaFilePath(savePath);
				qna.setQnaFileLength(fileLength);
			}
			
			String qnaWriter = (String)session.getAttribute("userId");
			qna.setQnaWriter(qnaWriter);
			qna.setQnaSubject(qnaSubject);
			qna.setQnaContent(qnaContent);
			int result = service.insertQna(qna);
			
			if(result > 0) {
				return "redirect:/qna/list.do";
			} else {
				model.addAttribute("msg", "Q&A 작성");
				model.addAttribute("url", "/qna/list.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// Q&A 수정 페이지
	@RequestMapping(value="/modify.do", method=RequestMethod.GET)
	public String showModifyQnaForm(int qnaNo, Model model) {
		try {
			Qna qna = service.selectOneByNo(qnaNo);
			
			if(qna != null) {
				model.addAttribute("qna", qna);
				return "qna/qnaModify";
			} else {
				model.addAttribute("msg", "수정할 데이터 불러오기");
				model.addAttribute("url", ".do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// Q&A 수정 기능
	@RequestMapping(value="/modify.do", method=RequestMethod.POST)
	public String modifyQna(int qnaNo
							   , @RequestParam("qna-subject") String qnaSubject
							   , @RequestParam("qna-content") String qnaContent
							   , @ModelAttribute Qna qna
							   , @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
							   , HttpServletRequest request
							   , RedirectAttributes redirect
							   , Model model) {
		try {
			if(!uploadFile.getOriginalFilename().equals("")) {
				String fileRename = qna.getQnaFileRename();
				if(fileRename != null) {
					this.deleteFile(request, fileRename);
				}
				Map<String, Object> infoMap = this.saveFile(uploadFile, request);
				String qnaFilename = (String)infoMap.get("fileName");
				String qnaFileRename = (String)infoMap.get("fileRename");
				String qnaFilePath = (String)infoMap.get("filePath");
				long qnaFileLength = (long)infoMap.get("fileLength");
				
				qna.setQnaFileName(qnaFilename);
				qna.setQnaFileRename(qnaFileRename);
				qna.setQnaFilePath(qnaFilePath);
				qna.setQnaFileLength(qnaFileLength);
			}
			
			qna.setQnaSubject(qnaSubject);
			qna.setQnaContent(qnaContent);		
			
			int result = service.updateQna(qna);
			
			if(result > 0) {
				// 성공하면 상세 페이지로 이동
				redirect.addAttribute("qnaNo", qnaNo);
				return "redirect:/qna/detail.do";
			} else {
				// 실패하면 에러메세지 출력
				model.addAttribute("msg", "Q&A 수정이 완료되지 않았습니다.");
				model.addAttribute("url", "/update.do?qnaNo=" + qnaNo);
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	
	// Q&A 삭제 페이지
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String deleteQna(int qnaNo
								, HttpServletRequest request
								, Model model) {
		try {
			Qna qna = service.selectOneByNo(qnaNo);
			int result = service.deleteQna(qnaNo);
			if(result > 0) {
				this.deleteFile(request, qna.getQnaFileRename());
				return "redirect:/qna/list.do";
			} else {
				model.addAttribute("msg", "Q&A 삭제");
				model.addAttribute("url", "/qna/list.do");
				return "common/serviceFailed";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// Q&A 첨부파일 처리 메소드
	public Map<String, Object> saveFile(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		String fileName = uploadFile.getOriginalFilename();
		
		String root = request.getSession().getServletContext().getRealPath("resources"); 
		String saveFolder = root + "\\GN_QnaFiles"; // 가온누리 Q&A 폴더
		File folder = new File(saveFolder);
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strResult = sdf.format(new Date(System.currentTimeMillis()));
		
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1); 
		String fileRename = "Q" + strResult + "." + ext;
		String savePath = saveFolder + "\\" + fileRename;
		File file = new File(savePath);
		
		uploadFile.transferTo(file);
		
		long fileLength = uploadFile.getSize();
		infoMap.put("fileName", fileName);
		infoMap.put("fileRename", fileRename);
		infoMap.put("filePath", savePath);
		infoMap.put("fileLength", fileLength);
		return infoMap;
	}

	private void deleteFile(HttpServletRequest request, String fileName) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delFilepath = root + "\\GN_QnaFiles\\" + fileName;
		File file = new File(delFilepath);
		if(file.exists()) {
			file.delete();
		}
	}
}