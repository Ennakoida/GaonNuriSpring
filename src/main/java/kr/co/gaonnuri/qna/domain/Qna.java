package kr.co.gaonnuri.qna.domain;

import java.sql.Date;

public class Qna {
	private int qnaNo;
	private String qnaSubject;
	private String qnaContent;
	private String qnaWriter;
	private String qnaFileName;
	private String qnaFileRename;
	private String qnaFilePath;
	private long qnaFileLength;
	private Date qCreateDate;
	private Date qUpdateDate;
	private int qViewCount;

	public Qna() {}

	public Qna(String qnaSubject, String qnaContent) {
		super();
		this.qnaSubject = qnaSubject;
		this.qnaContent = qnaContent;
	}

	public Qna(int qnaNo, int qViewCount) {
		super();
		this.qnaNo = qnaNo;
		this.qViewCount = qViewCount;
	}

	public Qna(int qnaNo, String qnaSubject, String qnaContent) {
		super();
		this.qnaNo = qnaNo;
		this.qnaSubject = qnaSubject;
		this.qnaContent = qnaContent;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaSubject() {
		return qnaSubject;
	}

	public void setQnaSubject(String qnaSubject) {
		this.qnaSubject = qnaSubject;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	
	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public String getQnaFileName() {
		return qnaFileName;
	}

	public void setQnaFileName(String qnaFileName) {
		this.qnaFileName = qnaFileName;
	}

	public String getQnaFileRename() {
		return qnaFileRename;
	}

	public void setQnaFileRename(String qnaFileRename) {
		this.qnaFileRename = qnaFileRename;
	}

	public String getQnaFilePath() {
		return qnaFilePath;
	}

	public void setQnaFilePath(String qnaFilePath) {
		this.qnaFilePath = qnaFilePath;
	}

	public long getQnaFileLength() {
		return qnaFileLength;
	}

	public void setQnaFileLength(long qnaFileLength) {
		this.qnaFileLength = qnaFileLength;
	}

	public Date getqCreateDate() {
		return qCreateDate;
	}

	public void setqCreateDate(Date qCreateDate) {
		this.qCreateDate = qCreateDate;
	}

	public Date getqUpdateDate() {
		return qUpdateDate;
	}

	public void setqUpdateDate(Date qUpdateDate) {
		this.qUpdateDate = qUpdateDate;
	}

	public int getqViewCount() {
		return qViewCount;
	}

	public void setqViewCount(int qViewCount) {
		this.qViewCount = qViewCount;
	}

	@Override
	public String toString() {
		return "Q&A [번호=" + qnaNo + ", 제목=" + qnaSubject + ", 내용=" + qnaContent + ", 작성자=" + qnaWriter + ", 파일이름=" + qnaFileName 
				+ ", 파일리네임=" + qnaFileRename + ", 파일경로=" + qnaFilePath + ", 파일크기=" + qnaFileLength + ", 작성일=" + qCreateDate
				+ ", 수정일=" + qUpdateDate + ", 조회수=" + qViewCount + "]";
	}
}
