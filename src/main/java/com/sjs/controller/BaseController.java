package com.sjs.controller;

import java.util.HashMap;
import java.util.Map;

import com.sjs.utils.JsonUtils;

public class BaseController {

	public Map succData(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "0");
		result.put("msg", "成功");
		result.put("data", obj);
//		String res = JsonUtils.obj2JsonString(result);
//		return res;
		return result;
	}
	
	public Map failData(Object obj) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", "1");
		result.put("msg", "失败");
		result.put("data", obj);
//		String res = JsonUtils.obj2JsonString(result);
//		return JsonUtils.obj2JsonString(result);
		return result;
	}
	
	public String otherData(Object obj, String code, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", obj);
		return JsonUtils.obj2JsonString(obj);
	}
}
