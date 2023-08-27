package kr.co.gaonnuri.qna.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.gaonnuri.qna.domain.Reply;
import kr.co.gaonnuri.qna.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	@Autowired
	private ReplyService rService;
	
	// 댓글 등록
	@RequestMapping(value="/add.do", method=RequestMethod.POST)
	public String insertReply(@ModelAttribute Reply reply
							  , HttpSession session
							  , Model model) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("userId");
			if(replyWriter != null) {
				reply.setReplyWriter(replyWriter);
				int result = rService.insertReply(reply);
				url = "/qna/detail.do?qnaNo=" + reply.getRefQnaNo();
				
				if(result > 0) {
					return "redirect:"+ url;
				} else {
					model.addAttribute("msg", "댓글 등록을 실패했습니다.");
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
				return "redirect:" + url;
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

}
