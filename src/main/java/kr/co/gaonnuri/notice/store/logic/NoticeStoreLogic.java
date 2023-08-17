package kr.co.gaonnuri.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore{

	@Override
	public int insertNotice(SqlSession sqlSession, Notice notice) {
		int result = sqlSession.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

	@Override
	public int updateNotice(SqlSession sqlSession, Notice notice) {
		int result = sqlSession.update("NoticeMapper.updateNotice", notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession sqlSession, int noticeNo) {
		int result = sqlSession.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}

	@Override
	public List<Notice> selectNoticeList(SqlSession sqlSession, int currentPage) {
		int limit = 10;
		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<Notice> nList = sqlSession.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public String generatePageNavi(SqlSession sqlSession, int currentPage) {
		int totalCount = getTotalCount(sqlSession); // 전체 게시물의 수
		int recordCountPerPage = 10; // 한 페이지당 수
		int naviTotalCount = 0; // 네비게이터의 수
		
		if(totalCount % recordCountPerPage > 0)
			naviTotalCount = totalCount / recordCountPerPage + 1;
		else
			naviTotalCount = totalCount / recordCountPerPage;
		
		int naviCountPerPage = 5;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
				
		if(endNavi > naviTotalCount) { 
			endNavi = naviTotalCount;
		}

		StringBuilder result = new StringBuilder();
		if(startNavi != 1) {
			result.append("<li style='cursor: pointer;' onclick=\"location.href='/notice/notice.do?currentPage=" + (startNavi - 1) + "'\"><</li> ");
		}else {
			result.append("<li><</li>");
		}

		for(int i = startNavi; i <= endNavi; i++) {
			if(currentPage == i) {
				result.append("<li style='color: #EA5455; font-weight: bold;' onclick=\"location.href='/notice/notice.do?currentPage=" + i + "'\">" + i + "</li>");
			} else {
				result.append("<li onclick=\"location.href='/notice/notice.do?currentPage=" + i + "'\">" + i + "</li>"); // \" : "를 문자열로 인식하기 위한 escape가 포함 (그냥 '로 써도 된다)
			}
		}

		if(endNavi != naviTotalCount) {
			result.append("<li style='cursor: pointer;' onclick=\"location.href='/notice/notice.do?currentPage=" + (endNavi + 1) + "'\">></li>");
		} else {
			result.append("<li>></li>");
		}

		return result.toString();
	}
	
	private int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}

	@Override
	public List<Notice> selectNoticeSearch(SqlSession sqlSession, String noticeSubject) {
		List<Notice> sList = sqlSession.selectList("NoticeMapper.selectNoticeSearch", noticeSubject);
		return sList;
	}

	@Override
	public List<Notice> selectSearchNoticeList(SqlSession sqlSession, int currentPage, String noticeSubject) {
		int limit = 10;
		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> sList = sqlSession.selectList("NoticeMapper.selectSearchNoticeList", noticeSubject, rowBounds);
		return sList;
	}

	@Override
	public String generateSearchPageNavi(SqlSession sqlSession, int currentPage, String noticeSubject) {
		int totalCount = getSearchTotalCount(sqlSession, noticeSubject); // 전체 게시물의 수
		int recordCountPerPage = 10; // 한 페이지당 수
		int naviTotalCount = 0; // 네비게이터의 수
		
		if(totalCount % recordCountPerPage > 0)
			naviTotalCount = totalCount / recordCountPerPage + 1;
		else
			naviTotalCount = totalCount / recordCountPerPage;
		
		int naviCountPerPage = 5;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
				
		if(endNavi > naviTotalCount) { 
			endNavi = naviTotalCount;
		}

		StringBuilder result = new StringBuilder();
		if(startNavi != 1) {
			result.append("<li style='cursor: pointer;' onclick=\"location.href='/notice/search.do?notice-search=" + noticeSubject + "&currentPage=" + (startNavi - 1) + "'\"><</li> ");
		}else {
			result.append("<li><</li>");
		}

		for(int i = startNavi; i <= endNavi; i++) {
			if(currentPage == i) {
				result.append("<li style='color: #EA5455; font-weight: bold;' onclick=\"location.href='/notice/search.do?notice-search=" + noticeSubject + "&currentPage=" + i + "'\">" + i + "</li>");
			} else {
				result.append("<li onclick=\"location.href='/notice/search.do?notice-search=" + noticeSubject + "&currentPage=" + i + "'\">" + i + "</li>"); // \" : "를 문자열로 인식하기 위한 escape가 포함 (그냥 '로 써도 된다)
			}
		}

		if(endNavi != naviTotalCount) {
			result.append("<li style='cursor: pointer;' onclick=\"location.href='/notice/search.do?notice-search=" + noticeSubject + "&currentPage=" + (endNavi + 1) + "'\">></li>");
		} else {
			result.append("<li>></li>");
		}

		return result.toString();
	}
	
	private int getSearchTotalCount(SqlSession sqlSession, String noticeSubject) {
		int totalCount = sqlSession.selectOne("NoticeMapper.getSearchTotalCount", noticeSubject);
		return totalCount;
	}
	
	@Override
	public Notice selectOneByNo(SqlSession sqlSession, int noticeNo) {
		Notice notice = sqlSession.selectOne("NoticeMapper.selectOneByNo", noticeNo);
		return notice;
	}
}
