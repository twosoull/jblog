package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;
	
	public CategoryVo adminCategoryWrite(CategoryVo categoryVo) {
		System.out.println("[CategoryService] : adminCategoryWrite()" );
		
		categoryDao.insertCategory(categoryVo);
		int cateNo=categoryVo.getCateNo();
		
		return categoryDao.selectCategory(cateNo);
		
		
	}

	public List<CategoryVo> adminCategoryList(String id) {
		System.out.println("[CategoryService] : CategoryService()" );
		
		return categoryDao.selectCategoryList2(id);
		
	
	}
	
	//삭제
	public int adminCategoryRemove(int cateNo) {
		System.out.println("[CategoryService] : adminCategoryRemove()");
		/*
		int postCnt = postDao.selectPostCnt(cateNo);
		System.out.println(postCnt);
		*/
		
		int count = 0;
		try {
			count = categoryDao.deleteCaterory(cateNo);
		}catch (Exception e) {
			count = 0;
		}
		System.out.println("count = "+count);
		return count;
	}

	
		
}
