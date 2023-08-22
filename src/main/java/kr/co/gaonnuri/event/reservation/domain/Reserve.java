package kr.co.gaonnuri.event.reservation.domain;

import java.sql.Date;
import java.sql.Timestamp;

public class Reserve {
	private int reserveNo;
	private String reserveUser;
	private String reservePlace;
	private Date reserveDate;
	private String reserveTime;
	private int reservePeople;
	private String reserveName;
	private String reservePhone;
	private String reserveEmail;
	private Timestamp makeReservationDate;
	
	public Reserve() {	}
	
	public Reserve(String reserveUser, String reservePlace, Date reserveDate, String reserveTime, int reservePeople, 
			String reserveName, String reservePhone, String reserveEmail) {
		super();
		this.reserveUser = reserveUser;
		this.reservePlace = reservePlace;
		this.reserveDate = reserveDate;
		this.reserveTime = reserveTime;
		this.reservePeople = reservePeople;
		this.reserveName = reserveName;
		this.reservePhone = reservePhone;
		this.reserveEmail = reserveEmail;
	}

	public int getReserveNo() {
		return reserveNo;
	}
	public void setReserveNo(int reserveNo) {
		this.reserveNo = reserveNo;
	}
	public String getReserveUser() {
		return reserveUser;
	}
	public void setReserveUser(String reserveUser) {
		this.reserveUser = reserveUser;
	}
	public String getReservePlace() {
		return reservePlace;
	}
	public void setReservePlace(String reservePlace) {
		this.reservePlace = reservePlace;
	}
	public Date getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(String reserveTime) {
		this.reserveTime = reserveTime;
	}
	public int getReservePeople() {
		return reservePeople;
	}
	public void setReservePeople(int reservePeople) {
		this.reservePeople = reservePeople;
	}
	public String getReserveName() {
		return reserveName;
	}
	public void setReserveName(String reserveName) {
		this.reserveName = reserveName;
	}
	public String getReservePhone() {
		return reservePhone;
	}
	public void setReservePhone(String reservePhone) {
		this.reservePhone = reservePhone;
	}
	public String getReserveEmail() {
		return reserveEmail;
	}
	public void setReserveEmail(String reserveEmail) {
		this.reserveEmail = reserveEmail;
	}
	public Timestamp getMakeReservationDate() {
		return makeReservationDate;
	}
	public void setMakeReservationDate(Timestamp makeReservationDate) {
		this.makeReservationDate = makeReservationDate;
	}

	@Override
	public String toString() {
		return "행사예매 [번호=" + reserveNo + ", 행사=" + reservePlace + ", 날짜=" + reserveDate + ", 시간=" + reserveTime
				+ ", 인원=" + reservePeople + ",  예매자이름=" + reserveName + ", 예매자전화번호=" 
				+ reservePhone + ", 예매자이메일=" + reserveEmail + ", 예매한날짜=" + makeReservationDate 
				+ "]";
	}
	
}
