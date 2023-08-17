package kr.co.gaonnuri.notice.service;

import java.util.List;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageData;

public interface NoticeService {
	
	/**
	 * 공지사항 작성 (추가)
	 * @param notice
	 * @return
	 */
	public int insertNotice(Notice notice);

	/**
	 * 공지사항 수정
	 * @param notice
	 * @return
	 */
	public int updateNotice(Notice notice);

	/**
	 * 공지사항 삭제
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo);

	/**
	 * 공지사항 전체 목록 조회
	 * @param currentPage
	 * @return PageData
	 */
	public PageData selectNoticeList(int currentPage);

	/**
	 * 공지사항 검색
	 * @param noticeSubject
	 * @return 검색 결과 List
	 */
	public List<Notice> selectNoticeSearch(String noticeSubject);
	
	/**
	 * 공지사항 검색 결과 전체 목록 조회
	 * @param currentPage
	 * @param noticeSubject
	 * @return PageData
	 */
	public PageData selectSearchNoticeList(int currentPage, String noticeSubject);

	/**
	 * 공지사항 번호로 검색 (상세 조회)
	 * @param noticeNo
	 * @return Notice 객체
	 */
	public Notice selectOneByNo(int noticeNo);
}
