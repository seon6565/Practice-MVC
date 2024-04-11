package org.fullstack4.chap01.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BbsDeleteController", value = "/bbs/delete")
public class BbsDeleteController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //리퍼러
        System.out.println("삭제 취소: get으로 접근하지마라, idx번호"+request.getParameter("idx"));
        request.getRequestDispatcher("/bbs/views/view?"+request.getParameter("idx")).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //삭제
        System.out.println("삭제 완료: post, idx번호"+request.getParameter("idx"));
        response.sendRedirect("/bbs/list");
    }
}
