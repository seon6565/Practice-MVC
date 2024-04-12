package org.fullstack4.chap01.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.BbsVO;
import org.fullstack4.chap01.dto.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class MemberDAO {
    public MemberVO login(String user_id, String pwd) throws Exception{
        StringBuilder sb = new StringBuilder();
        MemberVO vo = null;
        sb.append("SELECT user_id, NAME, pwd, jumin, addr1, addr2, birthday, job_code, mileage ,user_state,reg_date,leave_date,pwd_change_date ");
        sb.append(" FROM tbl_member ");
        sb.append(" WHERE user_id = ? ");
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1,user_id);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            if (rs.getString("pwd").equals(pwd)) {
                vo = MemberVO.builder().user_id(rs.getString("user_id")).name(rs.getString("name")).pwd(rs.getString("pwd"))
                        .jumin(rs.getString("jumin")).addr1(rs.getString("addr1")).addr2(rs.getString("addr2"))
                        .birthday(rs.getString("birthday")).job_code(rs.getString("job_code")).mileage(rs.getInt("mileage"))
                        .user_state(rs.getString("user_state"))
                        .reg_date(rs.getString("reg_date")).leave_date(rs.getString("leave_date")).pwd_change_date(rs.getString("pwd_change_date"))
                        .build();
            }
        }

        return vo;
    }

    public MemberVO cookielogin(String user_id) throws Exception{
        StringBuilder sb = new StringBuilder();
        MemberVO vo = null;
        sb.append("SELECT user_id, NAME, pwd, jumin, addr1, addr2, birthday, job_code, mileage ,user_state,reg_date,leave_date,pwd_change_date ");
        sb.append(" FROM tbl_member ");
        sb.append(" WHERE user_id = ? ");
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString(1,user_id);
        @Cleanup ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            vo = MemberVO.builder().user_id(rs.getString("user_id")).name(rs.getString("name")).pwd(rs.getString("pwd"))
                        .jumin(rs.getString("jumin")).addr1(rs.getString("addr1")).addr2(rs.getString("addr2"))
                        .birthday(rs.getString("birthday")).job_code(rs.getString("job_code")).mileage(rs.getInt("mileage"))
                        .user_state(rs.getString("user_state"))
                        .reg_date(rs.getString("reg_date")).leave_date(rs.getString("leave_date")).pwd_change_date(rs.getString("pwd_change_date"))
                        .build();

        }

        return vo;
    }

}
