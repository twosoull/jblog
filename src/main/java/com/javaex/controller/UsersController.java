package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.BlogService;
import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UsersController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	// 회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController ] : joinForm");

		return "user/joinForm";
	}

	// 회원가입 + 블로그 생성
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController ] : join");
		//회원가입 (users 테이블 저장)
		userService.join(userVo);
		//블로그생성
		blogService.createBlog(userVo);
		
		//카테고리생성은 블로그가 생성되면 같이 만들어진다고 생각해서 blogService에 배치
		
		
		
		return "user/joinSuccess";
	}

	// 로그인 폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController ] : loginForm");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo,HttpSession session) {
		System.out.println("[UserController ] : login");

		UserVo authUser = userService.login(userVo);
		System.out.println("authUser = " + authUser);

		if (authUser == null) {
			return "redirect:/user/loginForm?result=fail";
		} else {
			
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		}
	}
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {
		System.out.println("[UserController ] : logout");
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
}
