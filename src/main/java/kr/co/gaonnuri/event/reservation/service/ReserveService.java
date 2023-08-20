package kr.co.gaonnuri.event.reservation.service;

import kr.co.gaonnuri.event.reservation.domain.Reserve;

public interface ReserveService {

	/**
	 * 행사 예매 등록 Service
	 * @param reserve
	 * @return
	 */
	int insertReserve(Reserve reserve);

}
