package kr.co.gaonnuri.event.reservation.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.event.reservation.domain.Reserve;

public interface ReserveStore {

	/**
	 * 행사 예매 등록 Store
	 * @param session
	 * @param reserve
	 * @return
	 */
	int insertReserve(SqlSession session, Reserve reserve);

	/**
	 * 예매 번호로 예매 내역 조회 Store
	 * @param session
	 * @param reserveNo
	 * @return
	 */
	Reserve selectReserveByNo(SqlSession session, int reserveNo);

	/**
	 * 회원 아이디를 이용하여 회원의 행사 예매 내역 조회 Store
	 * @param session
	 * @param userId
	 * @return
	 */
	public List<Reserve> selectAllReservesById(SqlSession session, String userId);
}
