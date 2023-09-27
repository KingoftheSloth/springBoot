package org.iclass.mvc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.Community;
@Mapper
public interface CommunityMapper {
	//글목록 페이징
	List<Community> pagelist(Map<String,Integer> map);
	int count();
	//글 수정,글 읽기
	Community selectByIdx(long idx);
	//글 읽기 
	void setReadCount(long idx);
	//글 쓰기
	int insert(Community vo);
	
	int delete(long idx);
	
	int update(Community vo);
	
//	comments();
}
