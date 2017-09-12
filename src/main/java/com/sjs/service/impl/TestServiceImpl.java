package com.sjs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sjs.dao.TestDao;
import com.sjs.service.TestService;

@Service("TestService")
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;
	
//	@Cacheable(value="common",key="#value")  
	public void set(String key, String value) {
		dao.set(key, value);
	}

//	@CachePut(value="common",key="#key")
	public String get(String key) {
		System.out.println("get method called: "+ key);
		return dao.get(key);
	}


}
