package com.ssafy.ltw.domain.myattraction.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyAttraction {
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private Long memberId;
    private Long attractionInfoId;
}
