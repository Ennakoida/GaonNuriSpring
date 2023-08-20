package kr.co.gaonnuri.notice.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageInfo;

public interface NoticeStore {
	
	/**
	 * 공지사항 작성 (추가) Store
	 * @param sqlSession
	 * @param notice
	 * @return
	 */
	public int insertNotice(SqlSession sqlSession, Notice notice);

	/**
	 * 공지사항 수정 Store
	 * @param sqlSession
	 * @param notice
	 * @return
	 */
	public int updateNotice(SqlSession sqlSession, Notice notice);

	/**
	 * 공지사항 삭제 Store
	 * @param sqlSession
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(SqlSession sqlSession, int noticeNo);

	/**
	 * 공지사항 번호로 조회 Store
	 * @param sqlSession
	 * @param noticeNo
	 * @return
	 */
	public Notice selectOneByNo(SqlSession sqlSession, int noticeNo);
	
	/**
	 * 공지사항 목록 조회 Store
	 * @param sqlSession
	 * @param pInfo
	 * @return
	 */
	public List<Notice> selectNoticeList(SqlSession sqlSession, PageInfo pInfo);

	/**
	 * 공지사항 전체 갯수 조회 Store
	 * @param sqlSession
	 * @return
	 */
	public int selectListCount(SqlSession sqlSession);

	/**
	 * 공지사항 조건에 따라 키워드로 검색 Store
	 * @param sqlSession
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	public List<Notice> selectNoticesByKeyword(SqlSession sqlSession, PageInfo pInfo, Map<String, String> paramMap);
	
	/**
	 * 공지사항 검색 게시물 전체 갯수 Store
	 * @param sqlSession
	 * @param paramMap
	 * @return
	 */
	public int selectListCount(SqlSession sqlSession, Map<String, String> paramMap);

	
}
