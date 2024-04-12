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
    <title>게시판 등록</title>
</head>
<body>
<h1>게시판 등록</h1>
<div>
    <form name="frmRegist" id="frmRegist" method="post" action="/bbs/regist">
        <input type="hidden" name="user_id" id="user_id" value="test"/>
        <table>
            <tr>
                <td><span>제목 : </span></td>
                <td><input type="text" name="title" id="title" value="${title}" maxlength="200" placeholder="글제목을 입력하세요."/></td>
                <span STYLE="display: ${title==""?"block":"none"}">제목을 입력하세요.</span>
            </tr>
            <tr>
                <td><span>글 내용  </span></td>
                <td><textarea name="content" id="content" rows="10" cols="80">${content}</textarea></td>
                <span STYLE="display: ${content==""?"block":"none"}">내용을 입력하세요.</span>
            </tr>
            <tr>
                <td> <span>등록일: </span></td>
                <td><input type="date" name="display_date" id="display_date" value="${display_date}" /> </td>
                <span STYLE="display: ${display_date==""?"block":"none"}">등록일을 입력하세요.</span>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">등록</button>
                    <button type="reset" onclick="location.href='/bbs/list'">취소</button>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
