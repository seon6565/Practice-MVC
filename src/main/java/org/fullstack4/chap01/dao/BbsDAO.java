package org.fullstack4.chap01.dao;

import lombok.Cleanup;
import org.fullstack4.chap01.dto.BbsVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BbsDAO {
    public String getTime(){
        String now = null;

        try(Connection conn = ConnectionUtil.INSTANCE.getConnection();
                PreparedStatement psmt = conn.prepareStatement("select now()");
                ResultSet rs = psmt.executeQuery();)
        {
            rs.next();
            now = rs.getString(1);
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            e.printStackTrace();
        }
        return now;
    }

    public String getTime2() throws Exception{
        String now = null;
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement psmt = conn.prepareStatement("select now()");
        @Cleanup ResultSet rs = psmt.executeQuery();
        rs.next();
        now = rs.getString(1);

        return now;
    }

    public void regist(BbsVO vo) throws Exception{
         StringBuilder sb = new StringBuilder();
         sb.append("insert into tbl_bbs ");
         sb.append(" (user_id,title,content,display_date, reg_date,read_cnt) ");
         sb.append(" values(?,?,?,?,now(),?)");

         @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
         @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
         pstmt.setString(1,vo.getUser_id());
         pstmt.setString(2,vo.getTitle());
         pstmt.setString(3,vo.getContent());
         pstmt.setString(4,vo.getDisplay_date());
         pstmt.setInt(5,0);

         pstmt.executeUpdate();
    }

    public List<BbsVO> list() throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("Select idx,user_id,title,content,display_date,read_cnt,reg_date ");
        sb.append(" from tbl_bbs ");
        sb.append(" order by idx desc");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        @Cleanup ResultSet rs = pstmt.executeQuery();

        List<BbsVO> bbsList = new ArrayList<>();
        while(rs.next()){
            BbsVO vo = BbsVO.builder().idx(rs.getInt("idx")).user_id(rs.getString("user_id")).title(rs.getString("title"))
                    .content(rs.getString("content")).display_date(rs.getString("display_date")).read_cnt(rs.getInt("read_cnt")).reg_date(rs.getDate("reg_date").toLocalDate()).build();
            bbsList.add(vo);
        }
        return bbsList;
    }

    public BbsVO view(int idx) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("Select idx,user_id,title,content,display_date,read_cnt,reg_date ");
        sb.append(" from tbl_bbs ");
        sb.append(" where idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setInt(1,idx);
        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();
        BbsVO vo = BbsVO.builder().idx(rs.getInt("idx")).user_id(rs.getString("user_id")).title(rs.getString("title"))
                .content(rs.getString("content")).display_date(rs.getString("display_date")).read_cnt(rs.getInt("read_cnt")).reg_date(rs.getDate("reg_date").toLocalDate()).build();

        return vo;
    }

    public void modify(BbsVO vo) throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("update tbl_bbs ");
        sb.append(" set user_id = ? , title = ? , content = ? , display_date = ? , read_cnt= ? , modify_date = now() ");
        sb.append(" where idx = ? ");

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1,vo.getUser_id());
        pstmt.setString(2,vo.getTitle());
        pstmt.setString(3,vo.getContent());
        pstmt.setString(4,vo.getDisplay_date());
        pstmt.setInt(5,vo.getRead_cnt());
        pstmt.setInt(6,vo.getIdx());
        int result = pstmt.executeUpdate();
        System.out.println("==========================");
        System.out.println("BbsDAO >> modify(BbsVO vo) :" + result);
        System.out.println("==========================");
    }

    public void delete(int idx) throws Exception{
        String sql = "delete from tbl_bbs where idx = ? ";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,idx);
        int result = pstmt.executeUpdate();
        System.out.println("==========================");
        System.out.println("BbsDAO >> delete(BbsVO vo)  idx :" +idx +" result : "+ result);
        System.out.println("==========================");
    }
}
