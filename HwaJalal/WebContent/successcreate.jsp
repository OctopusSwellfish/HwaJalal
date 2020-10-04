<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="eunju.domain.*"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::공지사항 등록:::</title>
</head>
<body>
<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>
	<% if(session.getAttribute("admin")==null) {
		response.sendRedirect("index.html");
	 } %>

<%NotificationVO nv = (NotificationVO)request.getAttribute("notification"); %>
<h3>등록한 공지사항</h3>
<h5>제목</h5>
<p><%=nv.getNotificationTitle() %></p>
<h5>내용</h5>
<p><%=nv.getNotificationContent() %></p>


	<a href="/eunju_free/NotificationServlet?key=list">공지사항 확인</a><p></p>
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a><p></p>

</body>
</html>