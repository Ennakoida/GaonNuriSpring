package kr.co.gaonnuri.event.reservation.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.event.reservation.domain.ReserveData;
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

	@Override
	public List<Reserve> selectAllReservesById(SqlSession session, String userId) {
		List<Reserve> rList = session.selectList("ReserveMapper.selectAllReservesById", userId);
		return rList;
	}

	@Override
	public List<ReserveData> selectOptionsbyPlace(SqlSession session, String selectedPlace) {
		List<ReserveData> rDataList = session.selectList("ReserveDataMapper.selectOptionsbyPlace", selectedPlace);
		return rDataList;
	}
}
