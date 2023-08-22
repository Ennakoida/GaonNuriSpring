package kr.co.gaonnuri.event.reservation.service;

import kr.co.gaonnuri.event.reservation.domain.Reserve;

public interface ReserveService {

	/**
	 * 행사 예매 등록 Service
	 * @param reserve
	 * @return
	 */
	int insertReserve(Reserve reserve);

	/**
	 * 예매 번호로 예매 내역 조회 Service
	 * @param reserveNo
	 * @return
	 */
	Reserve selectReserveByNo(int reserveNo);

}
