package kr.co.gaonnuri.notice.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.notice.domain.Notice;

public interface NoticeStore {
	public int insertNotice(SqlSession sqlSession, Notice notice);

	public int updateNotice(SqlSession sqlSession, Notice notice);

	public int deleteNotice(SqlSession sqlSession, int noticeNo);

	// 공지사항 전체 목록
	public List<Notice> selectNoticeList(SqlSession sqlSession, int currentPage);
	
	public String generatePageNavi(SqlSession sqlSession, int currentPage);
	
	// 검색 목록
	public List<Notice> selectNoticeSearch(SqlSession sqlSession, String noticeSubject);
	
	public List<Notice> selectSearchNoticeList(SqlSession sqlSession, int currentPage, String noticeSubject);
	
	public String generateSearchPageNavi(SqlSession session, int currentPage, String noticeSubject);
	
	public Notice selectOneByNo(SqlSession sqlSession, int noticeNo);
}
