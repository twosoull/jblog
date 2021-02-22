package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CommentVo;

@Repository
public class CommentDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentVo> selectCommentList(int postNo) {
		System.out.println("[CommentDao] : selectCommentList");
		
		return sqlSession.selectList("comment.selectCommentList",postNo);
		
	}

}
