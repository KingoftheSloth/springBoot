package org.iclass.mvc.controller;

import java.lang.ProcessBuilder.Redirect;
import java.time.LocalDate;


import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
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
	public void write(Model model) {

	}
	@PostMapping("/write")
	public String save(@ModelAttribute Community dto, RedirectAttributes reAttr) {
		log.info("dto: {}", dto);
		service.insert(dto);
		reAttr.addFlashAttribute("message", "글 등록이 완료되었습니다.");
		return "redirect:/community/list";
	}

	@GetMapping({"/read","/update"})
	public void read(PageRequestDTO pageRequestDTO, long idx, Model model){
		Community community = service.read(idx);
		model.addAttribute("dto",community);
		//요청이 /read 이면 뷰 리졸버가 read.html로 요청 전달
		//요청이 /update 이면 뷰 리졸버가 update.html로 요청 전달

	}

	/*@PostMapping("/update")
	public String save(@ModelAttribute("dto") Community dto) {
		String updatedTitle = dto.getTitle();
		String updatedContent = dto.getContent();

		dto.setTitle(updatedTitle);
		dto.setContent(updatedContent);

		service.update(dto);

		return "redirect:/community/list";
	}*/
	@PostMapping("/update") //pageRequestDTO를 파라미터로 받아서 수정 후에도 검색이유지되도록 합니다.
		public String modify(PageRequestDTO pageRequestDTO, Community community ,
		 RedirectAttributes redirectAttributes){
			String link = pageRequestDTO.getLink();

			service.update(community);
			redirectAttributes.addFlashAttribute("result","글을 수정 했습니다.");
			redirectAttributes.addAttribute("idx", community.getIdx());
			return "redirect:/community/read?" + link;

		}



	@PostMapping("/delete")
	public String remove(PageRequestDTO pageRequestDTO, Long idx, RedirectAttributes
						 redirectAttributes){
		service.delete(idx);
		redirectAttributes.addFlashAttribute("result","글을 삭제했습니다.("+idx+ "번)");
		return "redirect:/community/list?"+pageRequestDTO.getLink();
	}

	/*@PostMapping("delete")
	public void delete(long idx){
	service.delete(idx);
		return ;
	}*/
}
