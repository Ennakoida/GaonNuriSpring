package kr.co.gaonnuri.hanbok.service;

import kr.co.gaonnuri.hanbok.domain.Hanbok;

public interface HanbokService {

	/**
	 * 한복 대여 Service
	 * @param hanbok
	 * @return
	 */
	int insertHanbok(Hanbok hanbok);

}
