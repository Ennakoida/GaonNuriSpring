package kr.co.gaonnuri.hanbok.service;

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

}
