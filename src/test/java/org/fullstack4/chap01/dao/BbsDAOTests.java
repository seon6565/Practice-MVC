package org.fullstack4.chap01.dao;

import lombok.Cleanup;
import org.fullstack4.chap01.dto.BbsVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BbsDAOTests {
    private BbsDAO dao;

    @BeforeEach
    public void ready(){
        dao= new BbsDAO();
    }

    @Test
    public void testGetTime() throws Exception{
        System.out.println("BBSDAO.getTime : " +dao.getTime());
    }

    @Test
    public void testGetTime2() throws Exception{
        System.out.println("BBSDAO.getTime2 : " +dao.getTime2());
    }

    @Test
    public void testRegist() throws Exception{
        BbsVO vo = BbsVO.builder().user_id("test").title("게시글 타이틀 테스트").content("게시글 내용 테스트").display_date(LocalDate.now().toString()).read_cnt(0).build();
        dao.regist(vo);
    }

    @Test
    public void testList() throws Exception{
        List<BbsVO> list = dao.list();
        list.forEach(vo->System.out.println("vo : "+ vo));
    }

    @Test
    public void testView() throws Exception{
        BbsVO vo = dao.view(1);
        System.out.println("vo : " +vo);
    }

    @Test
    public void testModify() throws Exception{
        BbsVO vo = BbsVO.builder().idx(1).user_id("test").title("게시글 타이틀 테스트 수정").content("게시글 내용 테스트 수정").display_date(LocalDate.now().toString()).read_cnt(0).build();
        dao.modify(vo);
    }

    @Test
    public void testDelete() throws Exception{
        dao.delete(1);
    }
}
