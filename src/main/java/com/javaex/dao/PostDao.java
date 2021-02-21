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

	public void insertPost(PostVo postVo) {
		System.out.println("[PostDao] : insertPost()");
		System.out.println(postVo);
		
		sqlSession.insert("post.insertPost",postVo);
		
	}

	public List<PostVo> selectPostList(int cateNo) {
		System.out.println("[PostDao] : selectPostList()");
		
		return sqlSession.selectList("post.selectPostList",cateNo);
		
	}

	public PostVo selectOnePost(int postNo) {
		System.out.println("[PostDao] : selectOnePost()");
		
		return sqlSession.selectOne("post.selectOnePost",postNo);
		
	}

}
