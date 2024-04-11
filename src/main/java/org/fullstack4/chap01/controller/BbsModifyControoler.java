package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.service.BbsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@Log4j2
@WebServlet(name = "BbsModifyControoler", value = "/bbs/modify")
public class BbsModifyControoler extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = request.getParameter("idx")!=null?Integer.parseInt(request.getParameter("idx")):0;
        //Id Check
        if(idx<=0){

        }
        else {
            try {
                BbsDTO bbsDTO = service.view(idx);
                request.setAttribute("bbsDTO",bbsDTO);
            } catch (Exception e) {
                log.info("수정에러 : " + e.getMessage());
                e.printStackTrace();
            }

            request.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean check_flag = true;
        request.setCharacterEncoding("UTF-8");

        int idx = request.getParameter("idx")!=null?Integer.parseInt(request.getParameter("idx")):0;
        String user_id = request.getParameter("user_id")!=null?request.getParameter("user_id"):"";
        int read_cnt = request.getParameter("read_cnt")!=null?Integer.parseInt(request.getParameter("read_cnt")):0;
        String title= request.getParameter("title");
        String display_date= request.getParameter("display_date");
        String content= request.getParameter("content");

        if(title == null || title.isEmpty()){
            check_flag= false;
        }
        else if(display_date == null|| display_date.isEmpty()){
            check_flag= false;
        }
        else if(content == null|| content.isEmpty()){
            check_flag= false;
        }
        int result = 0;
        BbsDTO bbsDTO = BbsDTO.builder().idx(idx).user_id(user_id).read_cnt(read_cnt).title(title).display_date(display_date).content(content).build();
        if(check_flag){
            try {
                result = service.modify(bbsDTO);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if(result>0) {
                    System.out.println("수정 완료");
                    response.sendRedirect("/bbs/list");
            }else{
                System.out.println("수정 실패");
            }
        }
        else{
            request.setAttribute("bbsDTO",bbsDTO);
            System.out.println("수정 입력값 유효하지 않음.");
            request.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(request,response);


        }
    }
}
