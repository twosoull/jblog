package com.javaex.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.UserService;


@Controller
public class BlogController {
		
		
		@Autowired
		private BlogService blogService;
		
		@RequestMapping(value="/{userId}", method = {RequestMethod.GET,RequestMethod.POST})
		public String blog(@PathVariable String userId,Model model,HttpSession session) {
			System.out.println("BlogController");
			
			
			Map<String,Object> bMap = blogService.blog(userId);
			
			if(bMap != null) {
				//model.addAttribute("userList",userList);
				model.addAttribute("bMap",bMap);
				
				return "/blog/blog-main";				
			}else {
				return "redirect:/";
			}
			
			
		}
}
