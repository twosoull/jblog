package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;

	public void createBlog(UserVo userVo) {
		System.out.println("[BlogService ] : createBlog");

		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다";
		String logofile = "spring-logo.jpg";
		
		BlogVo blogVo = new BlogVo(id, blogTitle, logofile);

		blogDao.insertBlog(blogVo);
		
		
		//블로그가 만들어지면 카테고리도 함께 만들어진다고 생각해
		//userController가 아닌 이곳에 배치
		
		categoryDao.insertfirCategory(id);

	}
	
	//id값을 주고 blog 테이블 값을 vo로 받는다
	public Map<String,Object> blog(String userId) {
		System.out.println("[BlogService ] : blog");
		Map<String,Object> map = new HashMap<String,Object>();
		
		//블로그
		BlogVo blogVo = blogDao.selectOne(userId);
		//카테고리
		List<CategoryVo> categoryList = categoryDao.selectCategoryList(userId);
		
		//컨트롤러와 서비스를 떼어내고 생각했을때에
		//블로그가 있다 없다를 판단해주는 것은 서비스의 일이고
		//어디로 갈지는 컨트롤러가 판단한다
		System.out.println(categoryList);
		boolean blog;
		if(blogVo != null){
			blog = true;
			
			map.put("blogVo", blogVo);
			map.put("categoryList", categoryList);
			map.put("blog", blog);
		}else {
			blog = false;
		}
		
		//map으로 묶기
		
		
		return map;
		
	}
}
