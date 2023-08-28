package kr.co.gaonnuri.qna.store.logic;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.qna.domain.PageInfo;
import kr.co.gaonnuri.qna.domain.Qna;
import kr.co.gaonnuri.qna.store.QnaStore;

@Repository
public class QnaStoreLogic implements QnaStore{

	@Override
	public int insertQna(SqlSession session, Qna qna) {
		int result = session.insert("QnaMapper.insertQna", qna);
		return result;
	}

	@Override
	public int updateQna(SqlSession session, Qna qna) {
		int result = session.update("QnaMapper.updateQna", qna);
		return result;
	}

	// Q&A 조회수 증가
	@Override
	public int updateViewCount(SqlSession session, Qna qnaView) {
		int result = session.update("QnaMapper.updateViewCount", qnaView);
		return result;
	}

	@Override
	public int deleteQna(SqlSession session, int qnaNo) {
		int result = session.delete("QnaMapper.deleteQna", qnaNo);
		return result;
	}

	@Override
	public Qna selectOneByNo(SqlSession session, int qnaNo) {
		Qna qna = session.selectOne("QnaMapper.selectOneByNo", qnaNo);
		return qna;
	}

	@Override
	public List<Qna> selectAllQnasById(SqlSession session, String userId) {
		List<Qna> qList = session.selectList("QnaMapper.selectAllQnasById", userId);
		return qList;
	}

	// Q&A 전체 목록
	@Override
	public List<Qna> selectQnaList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Qna> nList = session.selectList("QnaMapper.selectQnaList", null, rowBounds);
		return nList;
	}
	
	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("QnaMapper.selectListCount");
		return result;
	}

	// Q&A 검색 결과 목록
	@Override
	public List<Qna> selectQnasByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Qna> searchList = session.selectList("QnaMapper.selectQnasByKeyword", paramMap, rowBounds);
		return searchList;
	}

	@Override
	public int selectListCount(SqlSession session, Map<String, String> paramMap) {
		int result = session.selectOne("QnaMapper.selectListByKeywordCount", paramMap);
		return result;
	}

}
