package kr.co.gaonnuri.qna.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.qna.domain.Reply;
import kr.co.gaonnuri.qna.service.ReplyService;
import kr.co.gaonnuri.qna.store.ReplyStore;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyStore rStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertReply(Reply reply) {
		int result = rStore.insertReply(session, reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = rStore.updateReply(session, reply);
		return result;
	}

	@Override
	public int deleteReply(int replyNo) {
		int result = rStore.deleteReply(session, replyNo);
		return result;
	}

	@Override
	public List<Reply> selectReplyList(int refQnaNo) {
		List<Reply> rList = rStore.selectReplyList(session, refQnaNo);
		return rList;
	}
}
