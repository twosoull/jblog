package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public void adminCategoryWrite(CategoryVo categoryVo) {
		System.out.println("[CategoryService] : adminCategoryWrite()" );
		
		categoryDao.insertCategory(categoryVo);
		
	}

	
		
}
