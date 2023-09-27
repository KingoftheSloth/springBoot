package org.iclass.mvc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.mvc.dto.CommunityComments;
@Mapper
public interface CommunityCommentsMapper {

	int insert (CommunityComments dto);
	int delete(long idx);
	long maxOf();
	List<CommunityComments> commentsList(long idx);
	int setCommentCount(long idx);
}
