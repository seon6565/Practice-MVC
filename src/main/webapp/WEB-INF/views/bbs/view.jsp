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
<h1>게시판 상세</h1>
<div>${dto.idx}</div>
<div>${dto.user_id}</div>
<div>${dto.title}</div>
<div>${dto.content}</div>
<div>${dto.display_date}</div>
<div>${dto.reg_date}</div>
<div>${dto.modify_date}</div>
<div>${dto.read_cnt}</div>
<div>
    <button type="button" onclick="location.href='/bbs/list'">리스트</button>
    <button type="button" onclick="location.href='/bbs/modify?idx=1'">수정</button>
    <form method="post" name="frm" action="/bbs/delete?idx=1" style="display: inline">
    <button type="submit">삭제</button>
    </form>
</div>
</body>
</html>
