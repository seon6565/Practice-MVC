package org.fullstack4.chap01.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dao.BbsDAO;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.dto.BbsVO;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public enum BbsService {
    INSTANCE;
    private BbsDAO dao;
    private ModelMapper modelMapper;

    BbsService(){
        dao = new BbsDAO();
        modelMapper = new ModelMapper();
    }
    public List<BbsDTO> bbsList() throws Exception {
//        List<BbsDTO> bbsDTOS = IntStream.range(0,10).mapToObj(i->{
//            BbsDTO dto = new BbsDTO();
//            dto.setIdx(i);
//            dto.setUser_id("webuser");
//            dto.setTitle("Bbs Title..."+i);
//            dto.setContent("Bbs content..."+i);
//            dto.setDisplay_date(LocalDate.now().toString());
//            dto.setRead_cnt(0);
//            //DB에서 가져오는 것으로 변경
//            return dto;
//        }).collect(Collectors.toList());

        List<BbsVO> bbsVOList = dao.list();
        List<BbsDTO> bbsDTOList = bbsVOList.stream().map(vo->modelMapper.map(vo, BbsDTO.class)).collect(Collectors.toList());

        return bbsDTOList;
    }

    public BbsDTO view(int idx) throws Exception {
//        BbsDTO dto = new BbsDTO();
//        dto.setIdx(idx);
//        dto.setUser_id("webuser");
//        dto.setTitle("Bbs Title..."+idx);
//        dto.setContent("Bbs content..."+idx);
//        dto.setDisplay_date(LocalDate.now().toString());
//        dto.setRead_cnt(0);

        BbsVO bbsVO = dao.view(idx);
        BbsDTO bbsDTO = modelMapper.map(bbsVO, BbsDTO.class);
        return bbsDTO;
    }

    public int regist(BbsDTO bbsDTO) throws Exception{
        BbsVO bbsVO = modelMapper.map(bbsDTO, BbsVO.class);
        return dao.regist(bbsVO);
    }

    public int modify(BbsDTO bbsDTO) throws Exception{
        BbsVO bbsVO = modelMapper.map(bbsDTO, BbsVO.class);
        return dao.modify(bbsVO);
    }

    public int delete(int idx) throws Exception{
        return dao.delete(idx);
    }
}
