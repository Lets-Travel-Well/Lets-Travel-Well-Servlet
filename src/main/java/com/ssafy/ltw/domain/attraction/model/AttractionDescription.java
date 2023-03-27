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
public class AttractionDescription {
	private int contentId;
	private String homepage;
	private String overview;
	private String telname;

}
