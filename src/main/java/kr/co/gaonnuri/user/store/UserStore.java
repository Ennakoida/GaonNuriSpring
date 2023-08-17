package kr.co.gaonnuri.user.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.user.domain.User;

public interface UserStore {
	public int insertUser(SqlSession sqlSession, User user);

	public int updateUser(SqlSession sqlSession, User user);

	public int updatePw(SqlSession sqlSession, User updatePass);

	public int deleteUser(SqlSession sqlSession, String userId);

	public User selectCheckLogin(SqlSession sqlSession, User user);

	public User selectIdByPhone(SqlSession sqlSession, String userPhone);

	public int selectPwByIdPhone(SqlSession sqlSession, User user);
	
	public User selectOneById(SqlSession sqlSession, String userId);

	public List<Reserve> selectAllReservesById(SqlSession sqlSession, String userId);

	public List<Hanbok> selectAllRentalsById(SqlSession sqlSession, String userId);

}
