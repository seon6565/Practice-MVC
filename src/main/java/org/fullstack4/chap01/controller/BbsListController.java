package org.fullstack4.chap01.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.dto.BbsDTO;
import org.fullstack4.chap01.service.BbsService;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(name = "BbsListController", value = "/bbs/list")
public class BbsListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        log.info("appName : " + servletContext.getAttribute("appName"));

        List<BbsDTO> dtoList = null;
        try {
            dtoList = BbsService.INSTANCE.bbsList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("list",dtoList);


        request.getRequestDispatcher("/WEB-INF/views/bbs/list.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
