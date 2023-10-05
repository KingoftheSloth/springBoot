package org.iclass.mvc.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
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

@RequestMapping("/community")  // url이 community로 시작하면 DispatcherServlet
@Controller
@Log4j2// 으로부터 CommunityController가 위임받아 처리합니다.
@RequiredArgsConstructor
public class CoummunityController {
	private final CommunityService service;

	//여기서부터는 요청을 처리하는 핸들러 메서드입니다.
	//URL 설계 : 글 목록 조회 URL은 /community/list, 글쓰기 /community/write
	//			글읽기 /community/read
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
    log.info(">>>>>> pageRequestDTO : {}",pageRequestDTO);
	PageResponseDTO responseDTO = service.listWithSearch(pageRequestDTO);
 responseDTO.getList().forEach(i->{
	 log.info(">>>>> 글 : {}",i);
 });

	//list.html에 전달할 model 관련 코드 작성하시고 list.html 도 완성해 보세요 레이아웃도 적용해 보세요.
     model.addAttribute("paging", responseDTO);

	}

	@GetMapping("/write")
	public void write(){

	}
	@GetMapping("/read")
	public void read(PageRequestDTO pageRequestDTO, long idx, Model model){
		Community community = service.read(idx);
		model.addAttribute("dto",community);
	}
}
