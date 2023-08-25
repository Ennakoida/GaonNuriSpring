package kr.co.gaonnuri.qna.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.gaonnuri.qna.service.QnaService;
import kr.co.gaonnuri.qna.store.QnaStore;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaStore qStore;
	@Autowired
	private SqlSession session;
	
}
