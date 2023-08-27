package kr.co.gaonnuri.qna.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.qna.domain.PageInfo;
import kr.co.gaonnuri.qna.domain.Qna;

public interface QnaStore {

	/**
	 * Q&A 작성 (추가) Store
	 * @param session
	 * @param qna
	 * @return
	 */
	public int insertQna(SqlSession session, Qna qna);

	/**
	 * Q&A 수정 Store
	 * @param session
	 * @param qna
	 * @return
	 */
	public int updateQna(SqlSession session, Qna qna);

	/** 
	 * Q&A 조회수 증가 Store
	 * @param session
	 * @param qnaView
	 * @return
	 */
	public int updateViewCount(SqlSession session, Qna qnaView);

	/**
	 * Q&A 삭제 Store
	 * @param session
	 * @param qnaNo
	 * @return
	 */
	public int deleteQna(SqlSession session, int qnaNo);

	/**
	 * Q&A 번호로 조회 Store
	 * @param session
	 * @param qnaNo
	 * @return
	 */
	public Qna selectOneByNo(SqlSession session, int qnaNo);
	
	/**
	 * Q&A 목록 조회 Store
	 * @param session
	 * @param pInfo
	 * @return
	 */
	public List<Qna> selectQnaList(SqlSession session, PageInfo pInfo);

	/**
	 * Q&A 전체 갯수 조회 Store
	 * @param session
	 * @return
	 */
	public int selectListCount(SqlSession session);

	/**
	 * Q&A 조건에 따라 키워드로 검색 Store
	 * @param session
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	public List<Qna> selectQnasByKeyword(SqlSession session, PageInfo pInfo, Map<String, String> paramMap);
	
	/**
	 * Q&A 검색 게시물 전체 갯수 Store
	 * @param session
	 * @param paramMap
	 * @return
	 */
	public int selectListCount(SqlSession session, Map<String, String> paramMap);

}
