package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		System.out.println("[UserService] : join()");
		
		return userDao.insert(userVo);
	}

	public UserVo login(UserVo userVo) {
		System.out.println("[UserService] : login()");
		
		return userDao.selectUserOne(userVo);
	}

	public List<UserVo> list() {
		System.out.println("[UserService] : list()");
		
		return userDao.selectUserList();
	}

	public String checkid(String id) {
		System.out.println("[UserService] : checkid()");
		
		UserVo userVo = userDao.selectUserOne(id);
		System.out.println(userVo);
		
		String result = "";
		if(userVo == null) {
			result = "can";
		}else {
			result = "cant";
		}
		
		return result;
	}

}
