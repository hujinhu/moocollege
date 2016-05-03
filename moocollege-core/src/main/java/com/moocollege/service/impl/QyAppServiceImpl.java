package com.moocollege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moocollege.dao.QyMenuMapper;
import com.moocollege.dto.QyMenu;
import com.moocollege.dto.QyMenuCriteria;
import com.moocollege.service.IQyMenuService;

@Service
@Transactional
public class QyAppServiceImpl implements IQyMenuService {

	@Autowired
	private QyMenuMapper qyMenuMapper;
 
	@Override
	public List<QyMenu> listAll() {
		return qyMenuMapper.selectByExample(new QyMenuCriteria());
	}

}