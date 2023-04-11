package com.ssafy.ltw.domain.myattraction.model.dto;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyAttractionDto {
    private int attractionInfoId;
    private String title;
    private String firstImage;
    private String addr1;
    private String zipcode;
    private double latitude;
    private double longitude;
}
