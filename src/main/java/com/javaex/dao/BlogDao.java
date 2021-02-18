package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(BlogVo blogVo) {
		System.out.println("[BlogDao] : insertBlog");
		System.out.println(blogVo);
		
		int count = sqlSession.insert("blog.insertBlog",blogVo);
		
		System.out.println(count);
	}
	
	//userId를 이용한 값받기	
	public BlogVo selectOne(String userId) {
		System.out.println("[BlogDao] : selectOne");
		System.out.println(userId);
		
		return sqlSession.selectOne("blog.selectBlogOne",userId);
		
	}

}
