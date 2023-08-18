package kr.co.gaonnuri.notice.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageData;
import kr.co.gaonnuri.notice.domain.PageInfo;
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
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> notice = nStore.selectNoticeList(sqlSession, pInfo);
		return notice;
	}

	@Override
	public List<Notice> selectNoticeSearch(String noticeSubject) {
		List<Notice> nList = nStore.selectNoticeSearch(sqlSession, noticeSubject);
		return nList;
	}

	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nStore.selectOneByNo(sqlSession, noticeNo);
		return notice;
	}

	@Override
	public int getListCount() {
		int result = nStore.selectListCount(sqlSession);
		return result;
	}

}
