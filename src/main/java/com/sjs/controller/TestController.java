package com.sjs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sjs.entity.UserBean;
import com.sjs.jedis.JedisTemplate;
import com.sjs.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController{
	
	private static Logger log = Logger.getLogger(TestController.class);
	
	@Autowired
	private JedisTemplate jedis;
	
	@Autowired TestService service;
	
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest req, Model model){
		ModelAndView mv = new ModelAndView();
		try {
//			service.get("hello");
			mv.addObject("test","hello world");
			mv.setViewName("/test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/set")
	public @ResponseBody Map set(HttpServletRequest req, Model model){
		try {
			String name = req.getParameter("name");
			String age = req.getParameter("age");
			jedis.set("name", name);
			jedis.set("age", age);
			
			UserBean user = new UserBean();
			user.setName(name);
			user.setAge(Integer.parseInt(age));
			jedis.setObject("user", user);
			
			List list = new ArrayList();
			list.add(name);
			list.add(age);
			list.add(new ArrayList());
			list.add(new HashMap());
			jedis.setObject("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.succData("");
	}

	@RequestMapping("/get")
	public ModelAndView get(HttpServletRequest req, Model model){
		ModelAndView mv = new ModelAndView();
		try {
			String name = jedis.get("name");
			String age = jedis.get("age");
			if(StringUtils.isEmpty(name)) {
				mv.addObject("name","fail");
			}else {
				mv.addObject("name",name);
			}
			if(StringUtils.isEmpty(age)) {
				mv.addObject("age","fail");
			}else {
				mv.addObject("age",age);
			}
			mv.addObject("test", "get method");
			mv.setViewName("/test");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/json")
	public @ResponseBody Map getJson(HttpServletRequest req, HttpServletResponse resp){
		String result = "result: ";
		Map<String, String> resMap = new HashMap<String, String>();
		try {
			String name = jedis.get("name");
			String age = jedis.get("age");
			UserBean user = (UserBean) jedis.getObject("user");
			List list = (List) jedis.getObject("list");
			resMap.put("name", name);
			resMap.put("age", age);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.succData(resMap);
	}
}
