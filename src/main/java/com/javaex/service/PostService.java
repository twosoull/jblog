package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public List<CategoryVo> adminWriteForm(String userId) {
		System.out.println("[PostService] : adminWriteForm()");
		
		return categoryDao.selectCategoryList(userId);
		
	}



	public void adminwrite(PostVo postVo) {
		System.out.println("[PostService] : adminWrite()");
		
		postDao.insertPost(postVo);
		
	}

}
