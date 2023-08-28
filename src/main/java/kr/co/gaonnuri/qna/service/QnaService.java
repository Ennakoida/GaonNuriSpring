package kr.co.gaonnuri.qna.service;

import java.util.List;
import java.util.Map;

import kr.co.gaonnuri.qna.domain.PageInfo;
import kr.co.gaonnuri.qna.domain.Qna;

public interface QnaService {

	/**
	 * Q&A 작성 (추가) Service
	 * @param qna
	 * @return
	 */
	public int insertQna(Qna qna);

	/**
	 * Q&A 수정 Service
	 * @param qna
	 * @return
	 */
	public int updateQna(Qna qna);

	/**
	 * Q&A 조회수 증가 Service
	 * @param qnaView
	 * @return
	 */
	public int updateViewCount(Qna qnaView);

	/**
	 * Q&A 삭제 Service
	 * @param qnaNo
	 * @return
	 */
	public int deleteQna(int qnaNo);
	
	/**
	 * Q&A 번호로 조회 Service
	 * @param qnaNo
	 * @return Qna 객체
	 */
	public Qna selectOneByNo(int qnaNo);
	
	/**
	 * 회원 아이디를 이용해 Q&A 목록 조회 Service
	 * @param userId
	 * @return
	 */
	public List<Qna> selectAllQnasById(String userId);

	/**
	 * Q&A 목록 조회 Service
	 * @param pInfo 
	 * @return
	 */
	public List<Qna> selectQnaList(PageInfo pInfo);

	/**
	 * Q&A 전체 갯수 조회 Service
	 * @return
	 */
	public int getListCount();

	/**
	 * Q&A 조건에 따라 키워드로 검색 Service
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Qna> searchQnasByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * Q&A 검색 게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);

}
