package org.iclass.mvc.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;

import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/community")  // url이 community로 시작하면 DispatcherServlet 
@Slf4j							   // 으로부터 CommunityController가 위임받아 처리합니다. 
public class CoummunityController {

	private final CommunityService service; 
	
	private CoummunityController(CommunityService service) {
		this.service = service;
		
	}
	//여기서부터는 요청을 처리하는 핸들러 메서드입니다. 
	//URL 설계 : 글 목록 조회 URL은 /community/list, 글쓰기 /community/write
	//			글읽기 /community/read
@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "1") // 파라미터 값이 없으면 오류. 기본값 설정합니다.
			int page, Model model) { // 메서드 인자 int page 는 url의 파라미터 입니다.
	// Model : view 로 전달될 데이터를 주로 저장합니다. 
	// addAttribute 메서드 : request.setAttribute 와 동일. (session 도 Model 에 사용 가능. 로그인에서합니다.)
	model.addAttribute("list", service.pagelist(page).get("list"));
	model.addAttribute("paging", service.pagelist(page).get("paging"));
	model.addAttribute("today", LocalDate.now());
}
@GetMapping("/read") // url community/read?idx=961&page=1
public void read(int idx,
		@ModelAttribute("page") // 파라미터로 받은 값 Model 객체- model.addAttribute와 같이 저장합니다. 
int page, Model model) {
	model.addAttribute("vo", service.read(idx));
	model.addAttribute("cmtlist", service.commentsList(idx));
}

@GetMapping("/write")
public void write() {
	// 글쓰기 GET 요청은 view name만 지정하고 끝.
}
@PostMapping("/write")
public String save(Community dto
		, RedirectAttributes reAttr
		) { //parameter가 많을 때, 그것들을 필드로 갖는 dto 또는 map으로 전달받기 
	log.info("dto: {}", dto);
	service.insert(dto);
	reAttr.addFlashAttribute("message", "글 등록이 완료되었습니다.");
	return "redirect:/community/list";
}
// location.href = 'list.jsp'는 자바스크립트 - 클라이언트 브라우저에서 주소를 변경 
// response.sendRedirect("list.jsp") 는 서버에서 클라이언트에게 새로 보낼 요청을 강제로 실행.
// 				ㄴ POST 요청을 처리한 후에는 redirect를 합니다.

@PostMapping("/update")
public void update(long idx, @ModelAttribute("page") int page, Model model) {
	model.addAttribute("vo", service.selectByIdx(idx));
	// int page 는 @ModelAttribute로 model.addAttribute("page",page); 를 대신해서 
	// 				update.jsp로 전달합니다. 
}

@PostMapping("/save")
public String updateAction(int page, Community dto, //Model model) {
	RedirectAttributes redirectAttributes ) {
	service.update(dto);
	// return "redirect:/community/list?page="+page;
	// 수정 후 다시 글 상세보기
	redirectAttributes.addAttribute("idx", dto.getIdx());
	redirectAttributes.addAttribute("page", page);
	redirectAttributes.addFlashAttribute("message", "글 수정이 완료됐수정.");
	return "redirect:/community/read";
	}

@PostMapping("/delete")
public String delete(@ModelAttribute("page") int page, Long idx
		, RedirectAttributes reAttr) {
	service.delete(idx);
	reAttr.addFlashAttribute("message", "삭제 캐리");
	reAttr.addFlashAttribute("page" , page); 
	//@ModelAttribute 와 RedirectAttributes  충돌하므로 직접 코딩함.
	return "redirect:/community/list";
}


 @PostMapping("/comments") public String comments(int page, int f,
 CommunityComments dto, RedirectAttributes redirectAttributes) {
  
  log.info(">>>>>>>>>>>>>>>> dto : {}" , dto); 
  service.comments(dto,f);
  redirectAttributes.addAttribute("page",page);
  redirectAttributes.addAttribute("idx", dto.getMref());
  
  //return "redirect:/community/read?page="+page+"&idx="+dto.getMref(); return
  return "redirect:/community/read"; // 리다이렉트 애트리뷰트 사용하므로 쿼리스트링 안씁니다. 
  }
}

