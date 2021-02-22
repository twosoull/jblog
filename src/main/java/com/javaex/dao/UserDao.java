package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
		
	public int insert(UserVo userVo) {
		System.out.println("[UserDao] : insert");
		System.out.println("id= " + userVo.getId());
		System.out.println("PASSWORD = " + userVo.getPassword());
		System.out.println("name = " + userVo.getUserName());
			
		return sqlSession.insert("user.insertUser",userVo);
		
	
	}

	public UserVo selectUserOne(UserVo userVo) {
		// TODO Auto-generated method stub
		System.out.println("[UserDao] : insert");
		System.out.println("id= " + userVo.getId());
		System.out.println("PASSWORD = " + userVo.getPassword());
		
		return sqlSession.selectOne("user.selectUserOne",userVo);
	}

	public List<UserVo> selectUserList() {
		System.out.println("[UserDao] : selectUserList()");
		
		return sqlSession.selectList("user.selectUserList");
		
	
	}
	
	//id 중복체크
	public UserVo selectUserOne(String id) {
		System.out.println("[UserDao] : selectUserOne()");
		
		return sqlSession.selectOne("user.selectUserOne2",id);
	}

	
		
}
