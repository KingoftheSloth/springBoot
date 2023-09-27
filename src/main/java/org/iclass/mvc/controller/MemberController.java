package org.iclass.mvc.controller;

import org.iclass.mvc.dto.BookUser;
import org.iclass.mvc.service.BookUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController { // 회원가입을 구현해보기 

	private final BookUserService service;
	private MemberController(BookUserService service) {
		this.service = service;
	}
	
	@RequestMapping("/test")
	public void test(@SessionAttribute("user") BookUser user) { // 로그인 방법 1로 되돌려 놓고 테스트 합니다.
		//LoginController 에서 저장된 user 세션 애트리뷰트를 가져 옵니다.
		log.info("user test : {}" , user);		
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String registerAction(BookUser dto,
			RedirectAttributes redirectAttributes){
		log.info("dto : {}" , dto);
		service.join(dto);
		redirectAttributes.addFlashAttribute("message", "가입 성공 이제 꺼져");
		return "redirect:/member/welcome";
		
	}
	
	@GetMapping("/welcome")
		public void welcome() {
			
		}
	}
