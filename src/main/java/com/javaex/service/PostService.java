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

	public PostVo adminPost(int postNo) {
		System.out.println("[PostService] : adminPost()");
		return postDao.selectOnePost(postNo);

	}

	public PostVo adminLastPost(String userId, int cateNo) {
		System.out.println("[PostService] : adminLastPost()");
		List<CategoryVo> categoryList = categoryDao.selectCategoryList(userId);

		System.out.println(categoryList);
		
		if (cateNo == 0) {
			for (int i = categoryList.size() - 1; 0 <= i; i--) {

				cateNo = categoryList.get(i).getCateNo();
				System.out.println("for :" + cateNo);
			}
		}

		List<PostVo> postList = postDao.selectPostList(cateNo);

		System.out.println("postList = " + postList);

		int postNo = 0;
		for (int i = postList.size() - 1; 0 <= i; i--) {

			postNo = postList.get(i).getPostNo();
			System.out.println("for:" + postNo);
		}

		return postDao.selectOnePost(postNo);

	}

}
