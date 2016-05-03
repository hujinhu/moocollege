package com.moocollege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moocollege.dao.QyAppMapper;
import com.moocollege.dto.QyApp;
import com.moocollege.dto.QyAppCriteria;
import com.moocollege.service.IQyAppService;

@Service
@Transactional
public class QyMenuServiceImpl implements IQyAppService {

	@Autowired
	private QyAppMapper qyAppMapper;

	@Override
	public List<QyApp> listByType(int type) {
		QyAppCriteria example = new QyAppCriteria();
		example.createCriteria().andTypeEqualTo(type);
		return qyAppMapper.selectByExample(example);
	}

	@Override
	public QyApp getByAppId(int appId) {
		return qyAppMapper.selectByPrimaryKey(appId);
	}

}