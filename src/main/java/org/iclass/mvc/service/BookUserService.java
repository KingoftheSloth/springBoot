package org.iclass.mvc.service;

import java.awt.print.Book;
import java.util.Map;

import org.iclass.mvc.dao.BookUserMapper;
import org.iclass.mvc.dto.BookUser;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE) // 생성자 접근 권한 
public class BookUserService {
private final BookUserMapper dao; 

public int join(BookUser dto) {
	return dao.join(dto);
	
}
public BookUser login(Map<String,String> map) {
return dao.login(map);	
}
}
