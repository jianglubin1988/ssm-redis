package com.sjs.dao;

import org.springframework.stereotype.Repository;

@Repository(value="TestDao")
public interface TestDao {

	public void set(String key, String value);
	
	public String get(String key);
}
