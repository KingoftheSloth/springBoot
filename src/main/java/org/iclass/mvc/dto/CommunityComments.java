package org.iclass.mvc.dto;

import java.sql.Timestamp;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunityComments {
	private int idx;
	private long mref;
	private String writer;
	private String content;
	private LocalDate createdAt;
	private String ip;
	private int heart;

}