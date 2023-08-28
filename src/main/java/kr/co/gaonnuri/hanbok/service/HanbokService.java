package kr.co.gaonnuri.hanbok.service;

import java.util.List;

import kr.co.gaonnuri.hanbok.domain.Hanbok;

public interface HanbokService {

	/**
	 * 한복 대여 Service
	 * @param hanbok
	 * @return
	 */
	int insertHanbok(Hanbok hanbok);

	/**
	 * 대여 번호로 대여 내역 조회 Service
	 * @param rentalNo
	 * @return
	 */
	Hanbok selectRentalByNo(int rentalNo);

	/**
	 * 회원 아이디를 이용하여 회원의 한복 대여 내역 조회 Service
	 * @param userId
	 * @return Hanbok 리스트
	 */
	public List<Hanbok> selectAllRentalsById(String userId);

}
