package com.ssafy.ltw.domain.attraction.model;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionInfo {
    private int contentId;
    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String zipcode;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readCount;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mlevel;
    // 스크랩 되어 있는가 확인 하는 것
    private boolean isScrap;
}