package com.ssafy.ltw.domain.member.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private long id;
	private String createdDate;
	private String modifiedDate;
	private String loginId;
	private String loginPw;
	private String username;
	private String email;
	private String phone;
}
