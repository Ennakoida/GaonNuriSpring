package kr.co.gaonnuri.hanbok.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.hanbok.store.HanbokStore;

@Repository
public class HanbokStoreLogic implements HanbokStore {

	@Override
	public int insertHanbok(SqlSession session, Hanbok hanbok) {
		int result = session.insert("HanbokMapper.insertHanbok", hanbok);
		return result;
	}

}
