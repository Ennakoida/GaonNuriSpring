package kr.co.gaonnuri.qna.domain;

import java.sql.Date;

public class Reply {
	private int replyNo;
	private int refQnaNo;
	private String replyContent;
	private String replyWriter;
	private Date rCreateDate;
	private Date rUpdateDate;
	private char updateYn;
	
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRefQnaNo() {
		return refQnaNo;
	}
	public void setRefQnaNo(int refQnaNo) {
		this.refQnaNo = refQnaNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public Date getrCreateDate() {
		return rCreateDate;
	}
	public void setrCreateDate(Date rCreateDate) {
		this.rCreateDate = rCreateDate;
	}
	public Date getrUpdateDate() {
		return rUpdateDate;
	}
	public void setrUpdateDate(Date rUpdateDate) {
		this.rUpdateDate = rUpdateDate;
	}
	public char getUpdateYn() {
		return updateYn;
	}
	public void setUpdateYn(char updateYn) {
		this.updateYn = updateYn;
	}
	@Override
	public String toString() {
		return "댓글 [번호=" + replyNo + ", 게시글번호=" + refQnaNo + ", 내용=" + replyContent
				+ ", 작성자=" + replyWriter + ", 작성일=" + rCreateDate + ", 수정일=" + rUpdateDate
				+ ", 수정여부=" + updateYn + "]";
	}
}
