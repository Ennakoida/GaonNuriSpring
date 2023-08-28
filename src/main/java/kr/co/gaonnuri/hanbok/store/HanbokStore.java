package kr.co.gaonnuri.hanbok.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.hanbok.domain.Hanbok;

public interface HanbokStore {

	/**
	 * 한복 대여 Store
	 * @param session
	 * @param hanbok
	 * @return
	 */
	int insertHanbok(SqlSession session, Hanbok hanbok);

	/**
	 * 대여 번호로 대여 내역 조회 Store
	 * @param session
	 * @param rentalNo
	 * @return
	 */
	Hanbok selectRentalByNo(SqlSession session, int rentalNo);
	

	/**
	 * 회원 아이디를 이용하여 회원의 한복 대여 내역 조회 Store
	 * @param session
	 * @param userId
	 * @return
	 */
	public List<Hanbok> selectAllRentalsById(SqlSession session, String userId);
}
