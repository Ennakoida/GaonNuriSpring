package kr.co.gaonnuri.notice.domain;

import java.sql.Date;

public class Notice {
	
	private int noticeNo;
	private String noticeSubject;
	private String noticeContent;
	private String noticeFileName;
	private String noticeFileRename;
	private String noticeFilePath;
	private long noticeFileLength;
	private Date nCreateDate;
	private Date nUpdateDate;
	private int nViewCount;
	
	public Notice() {}
	
	public Notice(String noticeSubject, String noticeContent) {
		super();
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
	}
	
	public Notice(int noticeNo, int nViewCount) {
		super();
		this.noticeNo = noticeNo;
		this.nViewCount = nViewCount;
	}

	public Notice(int noticeNo, String noticeSubject, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeSubject = noticeSubject;
		this.noticeContent = noticeContent;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeSubject() {
		return noticeSubject;
	}

	public void setNoticeSubject(String noticeSubject) {
		this.noticeSubject = noticeSubject;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getNoticeFileName() {
		return noticeFileName;
	}

	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}

	public String getNoticeFileRename() {
		return noticeFileRename;
	}

	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public String getNoticeFilePath() {
		return noticeFilePath;
	}

	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}

	public long getNoticeFileLength() {
		return noticeFileLength;
	}

	public void setNoticeFileLength(long noticeFileLength) {
		this.noticeFileLength = noticeFileLength;
	}

	public Date getnCreateDate() {
		return nCreateDate;
	}

	public void setnCreateDate(Date nCreateDate) {
		this.nCreateDate = nCreateDate;
	}

	public Date getnUpdateDate() {
		return nUpdateDate;
	}

	public void setnUpdateDate(Date nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}

	public int getnViewCount() {
		return nViewCount;
	}

	public void setnViewCount(int nViewCount) {
		this.nViewCount = nViewCount;
	}

	@Override
	public String toString() {
		return "공지 [번호=" + noticeNo + ", 제목=" + noticeSubject + ", 내용=" + noticeContent
				+ ", 파일이름=" + noticeFileName + ", 파일리네임=" + noticeFileRename + ", 파일경로="
				+ noticeFilePath + ", 파일길이=" + noticeFileLength + ", 작성일=" + nCreateDate
				+ ", 수정일=" + nUpdateDate + ", 조회수=" + nViewCount + "]";
	}
	
}
