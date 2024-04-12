package org.fullstack4.chap01.controller;

import org.fullstack4.chap01.dto.MemberDTO;
import org.fullstack4.chap01.service.MemberService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/member/login")
public class LoginController extends HttpServlet {
    MemberService service = MemberService.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/bbs/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =null;
        String pwd = null;
        String saveid = null;
        String autologin = null;
        if(request.getParameter("id")!=null){
            id=request.getParameter("id");
        }
        if(request.getParameter("pwd")!=null){
            pwd=request.getParameter("pwd");
        }
        if(id != null && pwd !=null) {
            try {
                MemberDTO memberDTO = service.login(id, pwd);
                if(memberDTO !=null) {
                    HttpSession session = request.getSession();
                    if(request.getParameter("saveid")!=null){
                        saveid=id;
                        Cookie cookie = new Cookie("saveid",saveid);
                        cookie.setMaxAge(60*60*24*3);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                        Cookie cookie2 = new Cookie("saveidflag","checked");
                        cookie2.setMaxAge(60*60*24*3);
                        cookie2.setPath("/");
                        response.addCookie(cookie2);
                    }
                    if(request.getParameter("autologin")!=null){
                        autologin=id;
                        Cookie cookie3 = new Cookie("autologin",autologin);
                        cookie3.setPath("/");
                        cookie3.setMaxAge(60*60*24*3);
                        response.addCookie(cookie3);
                        Cookie cookie4 = new Cookie("autologinflag","checked");
                        cookie4.setPath("/");
                        cookie4.setMaxAge(60*60*24*3);
                        response.addCookie(cookie4);
                    }
                    session.setAttribute("memberDTO", memberDTO);

                    response.sendRedirect("/bbs/list");
                }
                else{
                    request.setAttribute("loginfail","block");
                    request.getRequestDispatcher("/WEB-INF/views/bbs/login.jsp").forward(request,response);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            request.setAttribute("loginfail","block");
            request.getRequestDispatcher("/WEB-INF/views/bbs/login.jsp").forward(request,response);
        }

    }
}
