package kr.co.gaonnuri.qna.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.qna.domain.Reply;

public interface ReplyStore {
	
	/**
	 * Q&A 댓글 등록 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int insertReply(SqlSession session, Reply reply);

	/**
	 * Q&A 댓글 수정 Store
	 * @param session
	 * @param reply
	 * @return
	 */
	int updateReply(SqlSession session, Reply reply);

	/**
	 * Q&A 댓글 전체 조회 Store
	 * @param session
	 * @return
	 */
	List<Reply> selectReplyList(SqlSession session, int refQnaNo);

}
