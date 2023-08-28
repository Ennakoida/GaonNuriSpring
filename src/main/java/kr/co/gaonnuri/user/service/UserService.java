package kr.co.gaonnuri.user.service;

import java.util.List;

import kr.co.gaonnuri.event.reservation.domain.Reserve;
import kr.co.gaonnuri.hanbok.domain.Hanbok;
import kr.co.gaonnuri.user.domain.User;

public interface UserService {
	
	/**
	 * 회원 등록 Service
	 * @param user
	 * @return
	 */
	public int insertUser(User user);

	/**
	 * 회원 정보 수정 Service
	 * @param user
	 * @return
	 */
	public int updateUser(User user);

	/**
	 * 비밀번호 찾기 시, 임의의 새로운 비밀번호로 회원 비밀번호 수정 Service
	 * @param updatePass
	 * @return
	 */
	public int updatePw(User updatePass);

	/**
	 * 회원 정보 삭제 Service
	 * @param userId
	 * @return
	 */
	public int deleteUser(String userId);

	/** 
	 * 로그인 하려는 회원 정보 조회 Service
	 * @param user
	 * @return member 객체
	 */
	public User selectCheckLogin(User user);
	
	/**
	 * 아이디 찾기 시, 회원 전화번호와 일치하는 아이디 찾기 Service
	 * @param userPhone
	 * @return member 객체
	 */
	public User selectIdByPhone(String userPhone);
	
	/**
	 * 비밀번호 찾기 시, 회원 아이디와 전화번호로 일치하는 비밀번호 찾기 (비밀번호 수정에 필요) Service
	 * @param user
	 * @return
	 */
	public int selectPwByIdPhone(User user);

	/**
	 * 회원 아이디를 이용하여 회원 데이터 전체 조회 Service
	 * @param userId
	 * @return member 객체
	 */
	public User selectOneById(String userId);

	/**
	 * 전체 회원 정보 조회 Service
	 * @return
	 */
	public List<User> selectAllUser();

}
