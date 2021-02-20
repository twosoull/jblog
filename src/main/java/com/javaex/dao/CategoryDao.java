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
		
		sqlSession.insert("category.insertfirCategory",id);
		
	}

	public List<CategoryVo> selectCategoryList(String userId) {
		System.out.println("[CategoryDao] :selectCategory");
		
		return sqlSession.selectList("category.selectCategoryList", userId);
		
		
	}
	
	public void insertCategory(CategoryVo categoryVo) {
		System.out.println("[CategoryDao] : insertCategory()");
		
		sqlSession.insert("category.insertCategory",categoryVo);
		
	}
	
	//vo postCnt 포함
	public CategoryVo selectCategory(int cateNo) {
		System.out.println("[CategoryDao] : selectCategory()");
		
		return sqlSession.selectOne("category.selectCategory",cateNo);
	}
	//list postCnt 포함
	public List<CategoryVo> selectCategoryList2(String id) {
		System.out.println("[CategoryDao] : selectCategoryList2()");
		
		return sqlSession.selectList("category.selectCategoryList2",id);
		
	}
	
	//카테고리 삭제
	public int deleteCaterory(int cateNo) {
		System.out.println("[CategoryDao] : deleteCaterory()");
		
		return sqlSession.delete("category.deleteCategory",cateNo);
		
		 
	}

}
