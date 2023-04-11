package com.ssafy.ltw.domain.member.model;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Salt {
    private Long id;
    private Long memberId;
    private String salt;
}
