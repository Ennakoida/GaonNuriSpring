package kr.co.gaonnuri.notice.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.notice.domain.Notice;
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

	// 공지사항 조회수 증가
	@Override
	public int updateViewCount(Notice noticeView) {
		int result = nStore.updateViewCount(sqlSession, noticeView);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nStore.deleteNotice(sqlSession, noticeNo);
		return result;
	}

	// 공지사항 상세 조회
	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nStore.selectOneByNo(sqlSession, noticeNo);
		return notice;
	}

	// 공지사항 전체 목록
	@Override
	public List<Notice> selectNoticeList(PageInfo pInfo) {
		List<Notice> notice = nStore.selectNoticeList(sqlSession, pInfo);
		return notice;
	}

	@Override
	public int getListCount() {
		int result = nStore.selectListCount(sqlSession);
		return result;
	}

	// 공지사항 검색 결과 목록
	@Override
	public List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap) {
		List<Notice> searchList = nStore.selectNoticesByKeyword(sqlSession, pInfo, paramMap);
		return searchList;
	}

	@Override
	public int getListCount(Map<String, String> paramMap) {
		int result = nStore.selectListCount(sqlSession, paramMap);
		return result;
	}
}
