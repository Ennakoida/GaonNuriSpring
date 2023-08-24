package kr.co.gaonnuri.event.reservation.domain;

import java.sql.Date;

public class ReserveData {
	private String dataReservePlace;
	private Date dataReserveStartDate;
	private Date dataReserveEndDate;
	private String dataReserveTime;
	
	public String getDataReservePlace() {
		return dataReservePlace;
	}
	public void setDataReservePlace(String dataReservePlace) {
		this.dataReservePlace = dataReservePlace;
	}
	public Date getDataReserveStartDate() {
		return dataReserveStartDate;
	}
	public void setDataReserveStartDate(Date dataReserveStartDate) {
		this.dataReserveStartDate = dataReserveStartDate;
	}
	public Date getDataReserveEndDate() {
		return dataReserveEndDate;
	}
	public void setDataReserveEndDate(Date dataReserveEndDate) {
		this.dataReserveEndDate = dataReserveEndDate;
	}
	public String getDataReserveTime() {
		return dataReserveTime;
	}
	public void setDataReserveTime(String dataReserveTime) {
		this.dataReserveTime = dataReserveTime;
	}
	@Override
	public String toString() {
		return "행사예매데이터 [행사명=" + dataReservePlace + ", 시작일=" + dataReserveStartDate
				+ ", 종료일=" + dataReserveEndDate + ", 시간=" + dataReserveTime + "]";
	}
}
