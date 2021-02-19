package com.javaex.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.CategoryService;
import com.javaex.vo.CategoryVo;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BlogService blogService;
	
	// 카테고리 폼
		@RequestMapping(value = "/{userId}/admin/category", method = { RequestMethod.GET, RequestMethod.POST })
		public String adminCategoryForm(@PathVariable String userId, Model model) {
			System.out.println("[BlogController] : adminCategoryForm()");
			Map<String, Object> bMap = blogService.checkBlog(userId);

			boolean blog = (boolean) bMap.get("blog");
			
			if (blog == true) {

				model.addAttribute("bMap", bMap);

				return "/blog/admin/blog-admin-cate";
			} else {
				return "redirect:/";
			}

		}
		
		@RequestMapping(value = "/{userId}/admin/category/write", method = { RequestMethod.GET, RequestMethod.POST })
		public String adminCategoryWrite(@PathVariable String userId, @ModelAttribute CategoryVo categoryVo) {
			System.out.println("[BlogController] : adminCategoryWrite");
			System.out.println(categoryVo);
			System.out.println(userId);

			categoryService.adminCategoryWrite(categoryVo);
			
			return "";
		}
		
		
		
		
		
		
		
}
