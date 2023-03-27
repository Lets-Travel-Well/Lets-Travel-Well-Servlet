package com.ssafy.ltw.domain.attraction.model;

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
public class Gugun {
	private int gugunCode;
	private String gugunName;
	private int sidoCode;
}
