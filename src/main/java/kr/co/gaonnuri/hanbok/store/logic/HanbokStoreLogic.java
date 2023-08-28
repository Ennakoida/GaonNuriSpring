package kr.co.gaonnuri.hanbok.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.hanbok.store.HanbokStore;

@Repository
public class HanbokStoreLogic implements HanbokStore {

	@Override
	public int insertHanbok(SqlSession session, Hanbok hanbok) {
		int result = session.insert("HanbokMapper.insertHanbok", hanbok);
		return result;
	}

	@Override
	public Hanbok selectRentalByNo(SqlSession session, int rentalNo) {
		Hanbok hanbok = session.selectOne("HanbokMapper.selectRentalByNo", rentalNo);
		return hanbok;
	}

	@Override
	public List<Hanbok> selectAllRentalsById(SqlSession session, String userId) {
		List<Hanbok> hList = session.selectList("HanbokMapper.selectAllRentalsById", userId);
		return hList;
	}

}
