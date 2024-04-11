package org.fullstack4.chap01.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "BbsModifyControoler", value = "/bbs/modify")
public class BbsModifyControoler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean check_flag = true;
        request.setCharacterEncoding("UTF-8");
        String title= request.getParameter("title");
        String reg_date= request.getParameter("reg_date");
        String content= request.getParameter("content");
        String[] hobbie= request.getParameterValues("hobbie");
        String sex= request.getParameter("sex");


        if(title == null || title.isEmpty()){
            check_flag= false;
        }
        else if(reg_date == null|| reg_date.isEmpty()){
            check_flag= false;
        }
        else if(content == null|| content.isEmpty()){
            check_flag= false;
        }
        else if(hobbie == null){
            check_flag= false;
        }
        else if(sex == null|| sex.isEmpty()){
            check_flag= false;
        }

        request.setAttribute("title",title);
        request.setAttribute("reg_date",reg_date);
        request.setAttribute("content",content);
        request.setAttribute("sex",sex);

        String lists[];
        if(request.getParameterValues("hobbie")!=null) {
            lists = request.getParameterValues("hobbie");

            String aflag = "";
            String bflag = "";
            String cflag = "";
            String dflag = "";
            String eflag = "";

            for (String list : lists) {
                if (list.equals("여행")) {
                    aflag = "checked";
                } else if (list.equals("독서")) {
                    bflag = "checked";
                } else if (list.equals("수영")) {
                    cflag = "checked";
                } else if (list.equals("잠자기")) {
                    dflag = "checked";
                } else if (list.equals("게임")) {
                    eflag = "checked";
                }
            }
            request.setAttribute("hobbie",hobbie);
            request.setAttribute("aflag",aflag);
            request.setAttribute("bflag",bflag);
            request.setAttribute("cflag",cflag);
            request.setAttribute("dflag",dflag);
            request.setAttribute("eflag",eflag);
        }

        if(check_flag){
            System.out.println("수정 완료");
            response.sendRedirect("/bbs/list");

        }
        else{
            System.out.println("수정 실패");
            request.getRequestDispatcher("/WEB-INF/views/bbs/modify.jsp").forward(request,response);


        }
    }
}
