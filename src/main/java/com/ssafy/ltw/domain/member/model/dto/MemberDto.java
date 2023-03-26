package com.ssafy.ltw.domain.member.model.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    private String loginId;
    private String username;
    private String email;
    private String phone;
}
