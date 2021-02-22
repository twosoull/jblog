package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.CommentService;
import com.javaex.vo.CommentVo;

@Controller
@RequestMapping(value="/comment" , method= {RequestMethod.GET,RequestMethod.POST})
public class CommentsController {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping(value="/lastComment" , method= {RequestMethod.GET,RequestMethod.POST})
	public List<CommentVo> lastComment(@RequestParam("postNo")int postNo) {
		System.out.println("[CommentsController] : lastcomment()");
		System.out.println(postNo);
		
		List<CommentVo> Commentlist = commentService.lastComment(postNo);
		
		return Commentlist;
	}
}
