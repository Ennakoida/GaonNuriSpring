package kr.co.gaonnuri.notice.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageInfo;
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
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pInfo) {
		int limit = pInfo.getRecordCountPerPage();
		int offset = (pInfo.getCurrentPage() - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}
	
	@Override
	public int selectListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.selectListCount");
		return result;
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
	public Notice selectOneByNo(SqlSession sqlSession, int noticeNo) {
		Notice notice = sqlSession.selectOne("NoticeMapper.selectOneByNo", noticeNo);
		return notice;
	}
}
