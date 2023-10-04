package org.iclass.mvc.dto;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PageRequestDTO {

	//page, size는 start,end 계산에 필요한 값
	private int page =1;
	private int size=5;      //size 는 한 개 페이지 글 갯수
	

	//sql 쿼리에 필요한 값
	private int start;  // 페이지 시작 글번호 rownum
	private int end; // 페이지 마지막 글번호 rownum


	
	
	//검색 파라미터
	private String[] types;//"twc"를 동적 쿼리로 전달하기 위해 배열로 변환하여 저장{"t","w","c"}
	private String keyword; // view 에서는"twc"로 전달되는 값 저장
	private boolean finished;

	@DateTimeFormat(pattern = "yyyy-MM-dd")	private LocalDate from;
	@DateTimeFormat(pattern = "yyyy-MM-dd")	private LocalDate to;
	
public static PageRequestDTO of(int page,int size,String[] types,String keyword,boolean finished,LocalDate from,LocalDate to) {  
		
		int startNo=(page-1)* size+1;    //글목록 시작행번호(rownum)
		int endNo = startNo + (size-1);
		
		
		return PageRequestDTO.builder()
				.page(page)
				.size(size)
				.start(startNo)
				.end(endNo)
				.types(types)
				.keyword(keyword)
				.finished(finished)
				.from(from)
				.to(to)
				.build();
	}
	
}
