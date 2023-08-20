package kr.co.gaonnuri.event.reservation.store;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.event.reservation.domain.Reserve;

public interface ReserveStore {

	/**
	 * 행사 예매 등록 Service
	 * @param session
	 * @param reserve
	 * @return
	 */
	int insertReserve(SqlSession session, Reserve reserve);

}
