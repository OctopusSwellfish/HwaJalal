<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
          <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::공지사항 등록:::</title>
	<link rel="stylesheet" href="every.css" type="text/css"></link>
	<link href="https://fonts.googleapis.com/css?family=Song+Myung&amp;subset=korean" rel="stylesheet">
	
</head>
<body>
	<% if(session.getAttribute("admin")==null) {
		response.sendRedirect("index.html");
	 } %>
	 
	<div class="logo"><img src="logosmall.png"></div>
	 
	 <h3>공지사항 등록하기</h3>
	<form action="/eunju_free/NotificationServlet" method="post">
		<input type="hidden" name="key" value="createNotification"/>
		<input type="text" size="80" name="title"><p></p>
		<textarea name="content" rows="16" cols="80" style="resize: none;">
		</textarea>
		<p></p>
		<input type="submit" value="작성">
	</form>
	
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>

</body>
</html>