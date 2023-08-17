package kr.co.gaonnuri.notice.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageData;
import kr.co.gaonnuri.notice.service.NoticeService;
import kr.co.gaonnuri.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertNotice(Notice notice) {
		int result = nStore.insertNotice(sqlSession, notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = nStore.updateNotice(sqlSession, notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nStore.deleteNotice(sqlSession, noticeNo);
		return result;
	}

	@Override
	public PageData selectNoticeList(int currentPage) {
		List<Notice> nList = nStore.selectNoticeList(sqlSession, currentPage);
		String pageNavi = nStore.generatePageNavi(sqlSession, currentPage);
		PageData pd = new PageData(nList, pageNavi);
		return pd;
	}

	@Override
	public List<Notice> selectNoticeSearch(String noticeSubject) {
		List<Notice> nList = nStore.selectNoticeSearch(sqlSession, noticeSubject);
		return nList;
	}

	@Override
	public PageData selectSearchNoticeList(int currentPage, String noticeSubject) {
		List<Notice> sList = nStore.selectSearchNoticeList(sqlSession, currentPage, noticeSubject);
		String pageNavi = nStore.generateSearchPageNavi(sqlSession, currentPage, noticeSubject);
		PageData pd = new PageData(sList, pageNavi);
		return pd;
	}

	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nStore.selectOneByNo(sqlSession, noticeNo);
		return notice;
	}

}
