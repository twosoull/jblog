package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.BlogService;
import com.javaex.vo.CategoryVo;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping(value = "/{userId}", method = { RequestMethod.GET, RequestMethod.POST })
	public String blog(@PathVariable String userId, Model model) {
		System.out.println("[BlogController] : blog()");
		Map<String, Object> bMap = blogService.checkBlog(userId);

		boolean blog = (boolean) bMap.get("blog");
		if (blog == true) {
			List<CategoryVo> categoryList = blogService.blogCateList(userId);

			bMap.put("categoryList", categoryList);
			model.addAttribute("bMap", bMap);

			return "/blog/blog-main";
		} else {
			return "redirect:/";
		}

	}

	// 기본설정 폼
	@RequestMapping(value = "/{userId}/admin/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasicModifyForm(@PathVariable String userId, Model model) {
		System.out.println("[BlogController] : adminBasicModifyForm()");
		Map<String, Object> bMap = blogService.checkBlog(userId);

		boolean blog = (boolean) bMap.get("blog");
		if (blog == true) {
			model.addAttribute("bMap", bMap);

			return "/blog/admin/blog-admin-basic";
		} else {
			return "redirect:/";
		}
	}
	//기본설정 수정
	@RequestMapping(value = "/{userId}/admin/basic/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminBasicModify(@RequestParam(value="blogTitle",required=false,defaultValue="")String blogTitle,
								   @RequestParam(value="file",required=false,defaultValue="")MultipartFile file,
								   @PathVariable String userId) {
		System.out.println("[BlogController] : adminBasicModify()");
		System.out.println(blogTitle);
		System.out.println(file.getOriginalFilename());
		
		blogService.basicUpdate(userId, blogTitle,file);
		
		return "redirect:/"+userId+"/admin/basic";
	}


	

}
