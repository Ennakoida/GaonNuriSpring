package kr.co.gaonnuri.event.reservation.service.impl;

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
}
