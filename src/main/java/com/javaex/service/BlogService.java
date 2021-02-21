package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.CategoryDao;
import com.javaex.dao.PostDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private PostDao postDao;

	public void createBlog(UserVo userVo) {
		System.out.println("[BlogService ] : createBlog");

		String id = userVo.getId();
		String blogTitle = userVo.getUserName() + "의 블로그입니다";
		
		BlogVo blogVo = new BlogVo(id, blogTitle);

		blogDao.insertBlog(blogVo);
		
		
		//블로그가 만들어지면 카테고리도 함께 만들어진다고 생각해
		//userController가 아닌 이곳에 배치
		
		categoryDao.insertfirCategory(id);

	}
	
	//id값을 주고 blog 테이블 값을 vo로 받는다
	public List<CategoryVo> blogCateList(String userId) {
		System.out.println("[BlogService ] : blog");

		List<CategoryVo> categoryList = categoryDao.selectCategoryList(userId);

		System.out.println(categoryList);
	
		
		return categoryList ;
		
	}
	// 블로그 유무확인 + 블로그 네임 아이디 정보
		public Map<String,Object> checkBlog(String userId) {
			System.out.println("[BlogService ] : check");
			BlogVo blogVo = blogDao.selectOne(userId);
			boolean blog;
			if (blogVo != null) {
				blog = true;

			} else {
				blog = false;
			}
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("blog",blog);
			map.put("blogVo",blogVo);
			
			return map;
		}
		
		//기본설정수정
		public void basicUpdate(String userId, String blogTitle, MultipartFile file) {
			System.out.println("[BlogService ] : basicUpdate");
			System.out.println("확인 :"+file.getOriginalFilename());
			
			
			
			String orgName = file.getOriginalFilename();
			
			//logofile 이름만들기
			if(!"".equals(orgName)) {
				
				String logoFile = "";
			BlogVo blogVo = new BlogVo(userId, blogTitle);
			
			System.out.println("in");
			String pathName = "C:\\javaStudy\\workspace_web\\jblog\\webapp\\assets\\uploadfile";
			String exName = orgName.substring(orgName.lastIndexOf("."));
			
			//저장할때의 이름
			logoFile =  System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
			blogVo.setLogoFile(logoFile);
			
		
			
				try {
					byte[] fileData= file.getBytes();
					
					OutputStream out = new FileOutputStream(pathName+"\\"+logoFile);
					BufferedOutputStream bos = new BufferedOutputStream(out);
					
					bos.write(fileData);
					bos.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				blogDao.update(blogVo);
			}else if("".equals(orgName)){
				System.out.println("ㅎㅇㅎㅇ");
				BlogVo blogVo = new BlogVo(userId, blogTitle,"");
				blogDao.update(blogVo);
			}
			
			
			
		}

		public List<PostVo> blogPostList(int cateNo, List<CategoryVo> categoryList) {
			System.out.println("[BlogService ] : blogPostList");
			System.out.println(cateNo);
			
			if(cateNo == 0){
				for(int i = categoryList.size()-1; 0<= i; i--) {
					
					cateNo = categoryList.get(i).getCateNo();
					System.out.println("for :" + cateNo);
				}
			}
			System.out.println("after :" + cateNo);
			
			List<PostVo> list = postDao.selectPostList(cateNo);
			
			System.out.println(list);
			return list;
		}
		
		
}
