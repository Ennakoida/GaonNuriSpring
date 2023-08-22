package kr.co.gaonnuri.hanbok.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.hanbok.service.HanbokService;
import kr.co.gaonnuri.hanbok.store.HanbokStore;

@Service
public class HanbokServiceImpl implements HanbokService {
	
	@Autowired
	private HanbokStore hStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int insertHanbok(Hanbok hanbok) {
		int result = hStore.insertHanbok(session, hanbok);
		return result;
	}

	@Override
	public Hanbok selectRentalByNo(int rentalNo) {
		Hanbok hanbok = hStore.selectRentalByNo(session, rentalNo);
		return hanbok;
	}

}
