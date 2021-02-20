package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.PostVo;


@Repository
public class PostDao {
	
	@Autowired
	private SqlSession sqlSession;	
	
	public int selectPostCnt(int cateNo) {
		System.out.println("[PostDao] : selectPostCnt()");
		
		return sqlSession.selectOne("post.selectPostCnt",cateNo);
	
	}

}
