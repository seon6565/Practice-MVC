package org.fullstack4.chap01.filter;


import lombok.extern.log4j.Log4j2;
import org.fullstack4.chap01.service.MemberService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/bbs/*"})
public class LoginCheckFilter implements Filter {
    MemberService service = MemberService.INSTANCE;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("Login Check Filter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if(session.getAttribute("memberDTO")==null) {
            Cookie cookies[] = req.getCookies();
            Cookie logincookie = null;
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("autologin")) {
                    logincookie = cookie;
                }
            }
            if (logincookie != null) {
                try {
                    session.setAttribute("memberDTO", service.cookielogin(logincookie.getValue()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (session.getAttribute("memberDTO") == null) {
                res.sendRedirect("/member/login");
            }
            else{
                chain.doFilter(request, response);
            }
        }
        else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
