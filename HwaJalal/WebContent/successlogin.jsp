<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::<%=session.getAttribute("name")%>님, 반갑습니다:::</title>
	
	<link href="https://fonts.googleapis.com/css?family=Song+Myung&amp;subset=korean" rel="stylesheet">
	
	<link rel="stylesheet" href="every.css" type="text/css"></link>
</head>
<body>

	<div class="logo"><img src="logosmall.png"></div>
	<%
		if(session.getAttribute("memberVO") == null) {
	 		response.sendRedirect("index.html");
		}
	%>
	
	<h3><%=session.getAttribute("name")%>님 환영합니다!</h3>
	
	<a href="/eunju_free/CosmeticServlet?key=showmyall">내가 등록한 화장품 보러가기</a><p></p>
	<a href="/eunju_free/cosmeticregister.jsp">새 화장품 등록하기</a><p></p>
	<a href="/eunju_free/NotificationServlet?key=list">공지사항 확인</a><p></p>
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a><p></p>
	<a href="/eunju_free/MemberServlet?key=logout">로그아웃</a><p></p>
	
	<% if(session.getAttribute("admin")!=null) {
	out.print("<a href=\"/eunju_free/CosmeticServlet?key=showalldata\">전체 화장품 목록 보기</a><p></p>");
	out.print("<a href=\"/eunju_free/MemberServlet?key=register\">공지사항 등록</a>");
	 } %>

	
</body>
</html>