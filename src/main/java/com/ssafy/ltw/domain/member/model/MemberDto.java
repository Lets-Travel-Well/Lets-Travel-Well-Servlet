package com.ssafy.ltw.domain.member.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDto {
	private long id;
	private String createdDate;
	private String modifiedDate;
	private String loginId;
	private String loginPw;
	private String memberName;
	private String email;
	private String phone;
}
