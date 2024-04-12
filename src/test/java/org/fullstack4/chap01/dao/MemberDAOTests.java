package org.fullstack4.chap01.dao;

import org.fullstack4.chap01.dto.BbsVO;
import org.fullstack4.chap01.dto.MemberVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class MemberDAOTests {
    private MemberDAO memberDAO;

    @BeforeEach
    public void ready(){
        memberDAO= new MemberDAO();
    }


    @Test
    public void testView() throws Exception{
        MemberVO memberVO = memberDAO.login("test","1234");
        System.out.println("vo : " +memberVO);
    }
}
