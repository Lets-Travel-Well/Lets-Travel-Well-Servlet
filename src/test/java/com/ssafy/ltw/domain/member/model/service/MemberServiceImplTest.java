package com.ssafy.ltw.domain.member.model.service;

import com.ssafy.ltw.domain.member.model.Member;
import com.ssafy.ltw.domain.member.model.dao.MemberDao;
import com.ssafy.ltw.domain.member.model.dao.MemberDaoImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceImplTest {
    private final MemberService memberService = MemberServiceImpl.getMemberService();
    
    @Test
    @DisplayName("id로 이름 조회하기")
    //@Test
    public void findUserNameById() throws Exception {
        //Given
        Long findId = 1L;
        //When
        System.out.println("here");

        Member findMember = memberService.findUserNameById(findId);
        System.out.println(findMember);
        //Then
//        assertThat(findName).isEqualTo("kch");
        }
}