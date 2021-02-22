package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CommentDao;
import com.javaex.vo.CommentVo;

@Service
public class CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	public List<CommentVo> lastComment(int postNo) {
		System.out.println("[CommentService] : lastComment()");
		
		return commentDao.selectCommentList(postNo);
	}

}
