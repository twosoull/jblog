package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.javaex.service.BlogService;
import com.javaex.service.PostService;
import com.javaex.vo.CategoryVo;
import com.javaex.vo.PostVo;
import com.javaex.vo.UserVo;

@Controller
public class PostController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private PostService postService;
	
	// 글작성 폼
		@RequestMapping(value = "/{userId}/admin/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
		public String adminWriteForm(@PathVariable String userId, Model model) {
			System.out.println("[BlogController] : adminWriteForm()");

			Map<String, Object> bMap = blogService.checkBlog(userId);
			
			boolean blog = (boolean) bMap.get("blog");
			
			

			if (blog == true) {
				List<CategoryVo> categoryList =postService.adminWriteForm(userId);
				System.out.println(categoryList);
				bMap.put("categoryList", categoryList);
				
				model.addAttribute("bMap", bMap);
			
				return "/blog/admin/blog-admin-write";
			} else {
				return "redirect:/";
			}
		}
		//post 생성
		@RequestMapping(value = "/{userId}/admin/write", method = { RequestMethod.GET, RequestMethod.POST })
		public String adminWrite(@PathVariable String userId,
								 @ModelAttribute PostVo postVo) {
			System.out.println("[BlogController] : adminWrite");
			postService.adminwrite(postVo);
			
		
			return "redirect:/{userId}/admin/writeForm";
			
		}
		@ResponseBody
		@RequestMapping(value = "/{userId}/admin/post", method = { RequestMethod.GET, RequestMethod.POST })
		public PostVo adminPost(@PathVariable String userId,
								@RequestParam(value="postNo",required=false,defaultValue = "0")int postNo) {
			System.out.println("[BlogController] : adminpost");
			PostVo postVo = postService.adminPost(postNo);
			
			return postVo;
		}
		
		@ResponseBody
		@RequestMapping(value = "/{userId}/admin/lastpost", method = { RequestMethod.GET, RequestMethod.POST })
		public PostVo adminLastPost(@PathVariable String userId,
									@RequestParam(value="cateNo",required=false,defaultValue = "0")int cateNo) {
			System.out.println("[BlogController] : adminpost");
			
			
			
			PostVo postVo = postService.adminLastPost(userId,cateNo);
			
			
			return postVo;
		}
}	
