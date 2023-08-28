package kr.co.gaonnuri.event.reservation.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.event.reservation.service.ReserveService;
import kr.co.gaonnuri.event.reservation.store.ReserveStore;

@Service
public class ReserveServiceImpl implements ReserveService{

	@Autowired
	private SqlSession session;
	@Autowired
	private ReserveStore rStore;
	
	@Override
	public int insertReserve(Reserve reserve) {
		int result = rStore.insertReserve(session, reserve);
		return result;
	}

	@Override
	public Reserve selectReserveByNo(int reserveNo) {
		Reserve reserve = rStore.selectReserveByNo(session, reserveNo);
		return reserve;
	}
	
	@Override
	public List<Reserve> selectAllReservesById(String userId) {
		List<Reserve> rList = rStore.selectAllReservesById(session, userId);
		return rList;
	}
}
