package kr.co.gaonnuri.event.reservation.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.event.reservation.store.ReserveStore;

@Repository
public class ReserveStoreLogic implements ReserveStore {

	@Override
	public int insertReserve(SqlSession session, Reserve reserve) {
		int result = session.insert("ReserveMapper.insertReserve", reserve);
		return result;
	}

	@Override
	public Reserve selectReserveByNo(SqlSession session, int reserveNo) {
		Reserve reserve = session.selectOne("ReserveMapper.selectReserveByNo", reserveNo);
		return reserve;
	}

}
