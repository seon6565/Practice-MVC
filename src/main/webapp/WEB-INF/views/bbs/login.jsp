<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2024-04-12
  Time: 오전 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta charset="UTF-8">

    <style>
        .subtext{
            display: none;
        }
    </style>
</head>
<body>
<form action="/member/login" method="post" id="frmlogin" name="frmlogin">
    아이디 <input type="text" name="id" id="id" value="${cookie.saveid.value}"><br>
    비밀번호 <input type="text" name="pwd" id="pwd"><br>
    <input type="checkbox" id="saveid" name="saveid" value="saveid" ${cookie.saveidflag.value}>아이디 저장
    <input type="checkbox" id="autologin" name="autologin" value="autologin" ${cookie.autologinflag.value}> 자동 로그인<br>
    <button type="submit"> 로그인</button>
    <button type="button"> 비밀번호 찾기</button>
    <button type="button"> 회원가입</button>
    <p class="subtext" style="display: ${loginfail}">로그인 실패</p>
</form>
</body>
</html>
