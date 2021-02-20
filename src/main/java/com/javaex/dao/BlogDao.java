package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(BlogVo blogVo) {
		System.out.println("[BlogDao] : insertBlog");
		System.out.println(blogVo);
		
		sqlSession.insert("blog.insertBlog",blogVo);
		
	}
	
	//userId를 이용한 값받기	
	public BlogVo selectOne(String userId) {
		System.out.println("[BlogDao] : selectOne");
		System.out.println(userId);
		
		return sqlSession.selectOne("blog.selectBlogOne",userId);
		
	}

	public void update(BlogVo blogVo) {
		System.out.println("[BlogDao] : update");
		System.out.println(blogVo+"gg");
		
		sqlSession.update("blog.updateBlog",blogVo);
		
		
	}

}
