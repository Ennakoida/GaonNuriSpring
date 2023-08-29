package kr.co.gaonnuri.event.reservation.service;

import java.util.List;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.event.reservation.domain.ReserveData;

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

	/**
	 * 회원 아이디를 이용하여 회원의 행사 예매 내역 조회 Service
	 * @param userId
	 * @return Reserve 리스트
	 */
	public List<Reserve> selectAllReservesById(String userId);

	/**
	 * 행사 선택에 따른 행사 데이터 불러오기 Service
	 * @param selectedPlace
	 * @return
	 */
	List<ReserveData> selectOptionsbyPlace(String selectedPlace);

}
