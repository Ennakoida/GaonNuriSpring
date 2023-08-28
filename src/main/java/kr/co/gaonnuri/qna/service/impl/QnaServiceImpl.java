package kr.co.gaonnuri.qna.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.qna.domain.PageInfo;
import kr.co.gaonnuri.qna.domain.Qna;
import kr.co.gaonnuri.qna.service.QnaService;
import kr.co.gaonnuri.qna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	private QnaStore qStore;
	@Autowired
	private SqlSession session;

	@Override
	public int insertQna(Qna qna) {
		int result = qStore.insertQna(session, qna);
		return result;
	}

	@Override
	public int updateQna(Qna qna) {
		int result = qStore.updateQna(session, qna);
		return result;
	}

	// Q&A 조회수 증가
	@Override
	public int updateViewCount(Qna qnaView) {
		int result = qStore.updateViewCount(session, qnaView);
		return result;
	}

	@Override
	public int deleteQna(int qnaNo) {
		int result = qStore.deleteQna(session, qnaNo);
		return result;
	}

	// Q&A 상세 조회
	@Override
	public Qna selectOneByNo(int qnaNo) {
		Qna qna = qStore.selectOneByNo(session, qnaNo);
		return qna;
	}

	@Override
	public List<Qna> selectAllQnasById(String userId) {
		List<Qna> qList = qStore.selectAllQnasById(session, userId);
		return qList;
	}

	// Q&A 전체 목록
	@Override
	public List<Qna> selectQnaList(PageInfo pInfo) {
		List<Qna> qna = qStore.selectQnaList(session, pInfo);
		return qna;
	}

	@Override
	public int getListCount() {
		int result = qStore.selectListCount(session);
		return result;
	}

	// Q&A 검색 결과 목록
	@Override
	public List<Qna> searchQnasByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Qna> searchList = qStore.selectQnasByKeyword(session, pInfo, paramMap);
		return searchList;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = qStore.selectListCount(session, paramMap);
		return result;
	}
}
