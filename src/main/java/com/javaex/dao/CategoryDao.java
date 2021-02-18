package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.CategoryVo;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//첫 카테고리 생성
	public void insertfirCategory(String id) {
		System.out.println("[CategoryDao] :insertCategory");
		
		sqlSession.insert("category.insertCategory",id);
		
	}

	public List<CategoryVo> selectCategoryList(String userId) {
		System.out.println("[CategoryDao] :selectCategory");
		
		return sqlSession.selectList("category.selectCategoryList", userId);
		
		
	}

}
