package org.fullstack4.chap01.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogoutController", value = "/member/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        Cookie cookies[] = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("autologin")) {
                Cookie cookie1 = cookie;
                cookie1.setPath("/");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
            }
            else if(cookie.getName().equals("autologinflag")) {
                Cookie cookie2 = cookie;
                cookie2.setMaxAge(0);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
            else if(cookie.getName().equals("saveid")) {
                Cookie cookie3 = cookie;
                cookie3.setPath("/");
                cookie3.setMaxAge(0);
                response.addCookie(cookie3);
            }
            else if(cookie.getName().equals("saveidflag")) {
                Cookie cookie4 = cookie;
                cookie4.setPath("/");
                cookie4.setMaxAge(0);
                response.addCookie(cookie4);
            }
        }
        response.sendRedirect("/bbs/list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
