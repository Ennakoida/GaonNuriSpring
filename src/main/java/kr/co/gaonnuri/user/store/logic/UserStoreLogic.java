package kr.co.gaonnuri.user.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.user.domain.User;
import kr.co.gaonnuri.user.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore {

	@Override
	public int insertUser(SqlSession sqlSession, User user) {
		int result = sqlSession.insert("UserMapper.insertUser", user);
		return result;
	}

	@Override
	public int updateUser(SqlSession sqlSession, User user) {
		int result = sqlSession.update("UserMapper.updateUser", user);
		return result;
	}

	@Override
	public int updatePw(SqlSession sqlSession, User updatePass) {
		int result = sqlSession.update("UserMapper.updatePw", updatePass);
		return result;
	}

	@Override
	public int deleteUser(SqlSession sqlSession, String userId) {
		int result = sqlSession.delete("UserMapper.deleteUser", userId);
		return result;
	}

	@Override
	public User selectCheckLogin(SqlSession sqlSession, User user) {
		User uOne = sqlSession.selectOne("UserMapper.selectCheckLogin", user);
		return uOne;
	}

	@Override
	public User selectIdByPhone(SqlSession sqlSession, String userPhone) {
		User uOne = sqlSession.selectOne("UserMapper.selectIdByPhone", userPhone);
		return uOne;
	}

	@Override
	public int selectPwByIdPhone(SqlSession sqlSession, User user) {
		int resultCount = sqlSession.selectOne("UserMapper.selectPwByIdPhone", user);
		return resultCount;
	}

	@Override
	public User selectOneById(SqlSession sqlSession, String userId) {
		User user = sqlSession.selectOne("UserMapper.selectOneById", userId);
		return user;
	}

	@Override
	public List<User> selectAllUser(SqlSession sqlSession) {
		List<User> uList = sqlSession.selectList("UserMapper.selectAllUser");
		return uList;
	}
}
