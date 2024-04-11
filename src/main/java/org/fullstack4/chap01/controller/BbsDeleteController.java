package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.service.BbsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
@Log4j2
@WebServlet(name = "BbsDeleteController", value = "/bbs/delete")
public class BbsDeleteController extends HttpServlet {
    private BbsService service = BbsService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //리퍼러
        int idx = 0;
        if(request.getParameter("idx")!=null){
            idx = Integer.parseInt(request.getParameter("idx"));
        }
        System.out.println("삭제 취소: get으로 접근하지마라, idx번호"+idx);

        response.sendRedirect("/bbs/view?idx="+idx);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idx = request.getParameter("idx")!=null?Integer.parseInt(request.getParameter("idx")):0;
        int result = 0;
        if(idx>0) {
            try {
                result = service.delete(idx);
                if(result>=0){
                    log.info("Sucess idx" + idx);
                    response.sendRedirect("/bbs/list");
                }
                else{
                    log.info("No Delete idx : "+idx);
                    request.getRequestDispatcher("/bbs/views/view?"+request.getParameter("idx")).forward(request,response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            log.info("Error idx : "+idx);
            request.getRequestDispatcher("/bbs/views/view?"+request.getParameter("idx")).forward(request,response);
        }
    }
}
