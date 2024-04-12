package org.fullstack4.chap01.service;

import org.fullstack4.chap01.dao.BbsDAO;
import org.fullstack4.chap01.dao.MemberDAO;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.dto.BbsVO;
import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.dto.MemberVO;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public enum MemberService {
    INSTANCE;
    private MemberDAO dao;
    private ModelMapper modelMapper;

    MemberService(){
        dao = new MemberDAO();
        modelMapper = new ModelMapper();
    }

    public MemberDTO login(String id, String pwd) throws Exception {
        MemberVO memberVO = dao.login(id,pwd);
        if(memberVO != null) {
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }
        return null;
    }

    public MemberDTO cookielogin(String id) throws Exception {
        MemberVO memberVO = dao.cookielogin(id);
        if(memberVO != null) {
            MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
            return memberDTO;
        }
        return null;
    }


}
