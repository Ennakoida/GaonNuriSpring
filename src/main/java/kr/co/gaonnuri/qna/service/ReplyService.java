package kr.co.gaonnuri.qna.service;

import java.util.List;

import kr.co.gaonnuri.qna.domain.Reply;

public interface ReplyService {

	/**
	 * Q&A 댓글 등록 Service
	 * @param reply
	 * @return
	 */
	int insertReply(Reply reply);

	/**
	 * Q&A 댓글 수정 Service
	 * @param reply
	 * @return
	 */
	int updateReply(Reply reply);

	/**
	 * Q&A 댓글 삭제 Service
	 * @param replyNo
	 * @return
	 */
	int deleteReply(int replyNo);

	/** 
	 * Q&A 댓글 전체 조회 Service
	 * @return
	 */
	List<Reply> selectReplyList(int refQnaNo);

}
