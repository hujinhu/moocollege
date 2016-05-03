package com.moocollege.service;

import java.util.List;

import com.moocollege.dto.QyApp;

public interface IQyAppService {


	List<QyApp> listByType(int i);

	/**
	 * 根据ID查找应用
	 * @param appId
	 * @return 
	 */
	QyApp getByAppId(int appId);

}
