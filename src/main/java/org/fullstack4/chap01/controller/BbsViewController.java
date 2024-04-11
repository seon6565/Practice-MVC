package org.fullstack4.chap01.controller;

import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.service.BbsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BbsViewController", value = "/bbs/view")
public class BbsViewController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=============================");
        System.out.println("/bbs/view");
        System.out.println("=============================");
        int idx = Integer.parseInt(request.getParameter("idx"));
        BbsDTO dto = BbsService.INSTANCE.view(idx);
        request.setAttribute("dto",dto);

        request.getRequestDispatcher("/WEB-INF/views/bbs/view.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
