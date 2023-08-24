package kr.co.gaonnuri.notice.service;

import java.util.List;
import java.util.Map;

import kr.co.gaonnuri.notice.domain.Notice;
import kr.co.gaonnuri.notice.domain.PageInfo;

public interface NoticeService {
	
	/**
	 * 공지사항 작성 (추가) Service
	 * @param notice
	 * @return
	 */
	public int insertNotice(Notice notice);

	/**
	 * 공지사항 수정 Service
	 * @param notice
	 * @return
	 */
	public int updateNotice(Notice notice);

	/**
	 * 공지사항 조회수 증가 Service
	 * @param noticeView
	 * @return
	 */
	public int updateViewCount(Notice noticeView);

	/**
	 * 공지사항 삭제 Service
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo);
	
	/**
	 * 공지사항 번호로 조회 Service
	 * @param noticeNo
	 * @return Notice 객체
	 */
	public Notice selectOneByNo(int noticeNo);
	
	/**
	 * 공지사항 목록 조회 Service
	 * @param pInfo 
	 * @return
	 */
	public List<Notice> selectNoticeList(PageInfo pInfo);

	/**
	 * 공지사항 전체 갯수 조회 Service
	 * @return
	 */
	public int getListCount();

	/**
	 * 공지사항 조건에 따라 키워드로 검색 Service
	 * @param pInfo
	 * @param paramMap
	 * @return
	 */
	List<Notice> searchNoticesByKeyword(PageInfo pInfo, Map<String, String> paramMap);

	/**
	 * 공지사항 검색 게시물 전체 갯수 Service
	 * @param paramMap
	 * @return
	 */
	int getListCount(Map<String, String> paramMap);
}
