<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-04
  Time: 오전 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>게시판 수정</h1>
<div>
    <form name="frmModify" id="frmModify" method="post" action="/bbs/modify" onsubmit="checkAll()">
        <input type="hidden" name="idx" id="idx" value="${bbsDTO.idx}"/>
        <input type="hidden" name="user_id" id="user_id" value="${bbsDTO.user_id}">
        <input type="hidden" name="read_cnt" id="read_cnt" value="${bbsDTO.read_cnt}">
        <table>
            <tr>
                <td><span>제목 : </span></td>
                <td><input type="text" name="title" id="title" value="${bbsDTO.title}" maxlength="200" placeholder="글제목을 입력하세요."/></td>
                <span id="subtitle" STYLE="display: ${bbsDTO.title==""?"block":"none"}">제목을 입력하세요.</span>
            </tr>
            <tr>
                <td> <span>등록일: </span></td>
                <td><input type="date" name="display_date" id="display_date" value="${bbsDTO.display_date}" /> </td>
                <span id="subdisplay_date" STYLE="display: ${bbsDTO.display_date==""?"block":"none"}">등록일을 입력하세요.</span>
            </tr>
            <tr>
                <td><span>글 내용  </span></td>
                <td><textarea name="content" id="content" rows="10" cols="80">${bbsDTO.content}</textarea></td>
                <span id="subcontent" STYLE="display: ${bbsDTO.content==""?"block":"none"}">내용을 입력하세요.</span>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">수정</button>
                    <button type="button" onclick="location.href='/bbs/view?idx=${bbsDTO.idx}'">취소</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<script>
    function checkAll(){
        let title, content, display_date;
        if(document.getElementById("title")!=null){
            document.getElementById("subtitle").style.display="none";
            title = document.getElementById("title").value;
        }

        if(document.getElementById("content")!=null){
            document.getElementById("subcontent").style.display="none";
            content = document.getElementById("content").value;
        }

        if(document.getElementById("display_date")!=null){
            document.getElementById("subdisplay_date").style.display="none";
            display_date = document.getElementById("display_date").value;
        }
        if(title == null || title == ""){
            document.getElementById("subtitle").style.display="block";
            event.preventDefault();
        }
        if(content == null || content == ""){
            document.getElementById("subcontent").style.display="block";
            console.log("test");
            event.preventDefault();
        }
        if(display_date == null || display_date == ""){
            document.getElementById("subdisplay_date").style.display="block";
            event.preventDefault();
        }
        let today = new Date();
        if(today.getDate() < new Date(display_date).getDate()){
            console.log("날짜는 현재일까지만");
            event.preventDefault();
        }

    }
</script>
</body>
</html>
