package kr.co.gaonnuri.user.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.user.domain.User;
import kr.co.gaonnuri.user.service.Hanbok;
import kr.co.gaonnuri.user.service.Reserve;
import kr.co.gaonnuri.user.service.UserService;
import kr.co.gaonnuri.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserStore uStore;
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertUser(User user) {
		int result = uStore.insertUser(sqlSession, user);
		return result;
	}

	@Override
	public int updateUser(User user) {
		int result = uStore.updateUser(sqlSession, user);
		return result;
	}

	@Override
	public int updatePw(User updatePass) {
		int result = uStore.updatePw(sqlSession, updatePass);
		return result;
	}

	@Override
	public int deleteUser(String userId) {
		int result = uStore.deleteUser(sqlSession, userId);
		return result;
	}

	@Override
	public User selectCheckLogin(User user) {
		User uOne = uStore.selectCheckLogin(sqlSession, user);
		return uOne;
	}

	@Override
	public User selectIdByPhone(String userPhone) {
		User uOne = uStore.selectIdByPhone(sqlSession, userPhone);
		return uOne;
	}

	@Override
	public int selectPwByIdPhone(User user) {
		int resultCount = uStore.selectPwByIdPhone(sqlSession, user);
		return resultCount;
	}

	@Override
	public User selectOneById(String userId) {
		User user = uStore.selectOneById(sqlSession, userId);
		return user;
	}

	@Override
	public List<Reserve> selectAllReservesById(String userId) {
		List<Reserve> rList = uStore.selectAllReservesById(sqlSession, userId);
		return rList;
	}

	@Override
	public List<Hanbok> selectAllRentalsById(String userId) {
		List<Hanbok> hList = uStore.selectAllRentalsById(sqlSession, userId);
		return hList;
	}

}
