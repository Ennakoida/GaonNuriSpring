package kr.co.gaonnuri.user.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.user.domain.User;

public interface UserStore {
	/**
	 * 회원 등록 Store
	 * @param sqlSession
	 * @param user
	 * @return
	 */
	public int insertUser(SqlSession sqlSession, User user);

	/**
	 * 회원 정보 수정 Store
	 * @param sqlSession
	 * @param user
	 * @return
	 */
	public int updateUser(SqlSession sqlSession, User user);

	/**
	 * 비밀번호 찾기 시, 임의의 새로운 비밀번호로 회원 비밀번호 수정 Store
	 * @param sqlSession
	 * @param updatePass
	 * @return
	 */
	public int updatePw(SqlSession sqlSession, User updatePass);

	/**
	 * 회원 정보 삭제 Store
	 * @param sqlSession
	 * @param userId
	 * @return
	 */
	public int deleteUser(SqlSession sqlSession, String userId);

	/**
	 * 로그인 하려는 회원 정보 조회 Store
	 * @param sqlSession
	 * @param user
	 * @return
	 */
	public User selectCheckLogin(SqlSession sqlSession, User user);

	/**
	 * 아이디 찾기 시, 회원 전화번호와 일치하는 아이디 찾기 Store
	 * @param sqlSession
	 * @param userPhone
	 * @return
	 */
	public User selectIdByPhone(SqlSession sqlSession, String userPhone);

	/**
	 * 비밀번호 찾기 시, 회원 아이디와 전화번호로 일치하는 비밀번호 찾기 (비밀번호 수정에 필요) Store
	 * @param sqlSession
	 * @param user
	 * @return
	 */
	public int selectPwByIdPhone(SqlSession sqlSession, User user);
	
	/**
	 * 회원 아이디를 이용하여 회원 데이터 전체 조회 Store
	 * @param sqlSession
	 * @param userId
	 * @return
	 */
	public User selectOneById(SqlSession sqlSession, String userId);

	/**
	 * 회원 아이디를 이용하여 회원의 행사 예매 내역 조회 Store
	 * @param sqlSession
	 * @param userId
	 * @return
	 */
	public List<Reserve> selectAllReservesById(SqlSession sqlSession, String userId);

	/**
	 * 회원 아이디를 이용하여 회원의 한복 대여 내역 조회 Store
	 * @param sqlSession
	 * @param userId
	 * @return
	 */
	public List<Hanbok> selectAllRentalsById(SqlSession sqlSession, String userId);

}
