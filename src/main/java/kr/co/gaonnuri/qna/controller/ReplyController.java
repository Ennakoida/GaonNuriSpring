package kr.co.gaonnuri.qna.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gaonnuri.qna.domain.Reply;
import kr.co.gaonnuri.qna.service.QnaService;
import kr.co.gaonnuri.qna.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService rService;
	@Autowired
	private QnaService qService;
	
	// 댓글 등록
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
	public String insertReply(@ModelAttribute Reply reply
							  , HttpSession session
							  , Model model) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("userId"); // 로그인 한 사용자
			String qnaWriter = qService.selectOneByNo(reply.getRefQnaNo()).getQnaWriter(); // 질문 게시글의 작성자
			url = "/qna/detail.do?qnaNo=" + reply.getRefQnaNo();
			
			if(replyWriter != null) { // 로그인을 했을 때
				if(replyWriter.equals("admin") || replyWriter.equals(qnaWriter)) { // 로그인 한 사용자가 관리자(admin) 또는 질문 게시글의 작성자일 경우에만 댓글 작성 가능 
					reply.setReplyWriter(replyWriter);
					int result = rService.insertReply(reply);
					
					if(result > 0) {
						return "redirect:"+ url;
					} else {
						model.addAttribute("msg", "댓글 등록을 실패했습니다.");
						model.addAttribute("url", url);
						return "common/serviceFailed";
					}					
				} else {
					model.addAttribute("msg", "해당 글에 대한 댓글 작성 권한이 없어 댓글 작성");
					model.addAttribute("url", url);
					return "common/serviceFailed";
				}
			} else {
				model.addAttribute("msg", "댓글 작성");
				model.addAttribute("url", "/user/login.do");
				return "common/needLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 댓글 수정
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String updateReply(@ModelAttribute Reply reply
							  , HttpSession session
							  , Model model) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("userId");
			if(!replyWriter.equals("")) {
				reply.setReplyWriter(replyWriter);
				url = "/qna/detail.do?qnaNo=" + reply.getRefQnaNo();
				int result = rService.updateReply(reply);
				if(result > 0) {
					return "redirect:" + url;					
				} else {
					model.addAttribute("msg", "댓글 수정");
					return "common/errorMessage";
				}
			} else {
				model.addAttribute("msg", "댓글 수정");
				model.addAttribute("url", "/user/login.do");
				return "common/needLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}
	
	// 댓글 삭제
	@RequestMapping(value="/delete.do", method=RequestMethod.GET)
	public String deleteReply(@ModelAttribute Reply reply
							  , HttpSession session
							  , Model model) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("userId");
			String qnaWriter = qService.selectOneByNo(reply.getRefQnaNo()).getQnaWriter();
			if(replyWriter != null) {
				if(replyWriter.equals("admin") || replyWriter.equals(qnaWriter)) {
					url = "/qna/detail.do?qnaNo=" + reply.getRefQnaNo();
					int result = rService.deleteReply(reply.getReplyNo());
					if(result > 0) {
						return "redirect:" + url;
					} else {
						model.addAttribute("msg", "댓글 삭제");
						return "common/errorMessage";
					}
				} else {
					model.addAttribute("msg", "해당 글에 대한 댓글 삭제 권한이 없어 댓글 삭제");
					model.addAttribute("url", url);
					return "common/serviceFailed";
				}
			} else {
				model.addAttribute("msg", "댓글 삭제");
				model.addAttribute("url", "/user/login.do");
				return "common/needLogin";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorMessage";
		}
	}

}
