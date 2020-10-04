<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="eunju.domain.*,eunju.persistence.*, java.util.*"%>
      <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::공지사항:::</title>
	<link rel="stylesheet" href="list.css" type="text/css"></link>
	
</head>
<body>

<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>


<h3>등록된 공지사항</h3>

<table>
		<tr>
		<td class="a">순번</td><td class="a">제목</td><td class="a">내용</td><td class="a">날짜</td>
		</tr>
		<%
			List<NotificationVO> notiList = 
			(List<NotificationVO>)request.getAttribute("NotificationList");
			for(NotificationVO nv : notiList ) { 
		%>
			<tr>
			<td><%=nv.getNotificationID()%></td>
			<td><%=nv.getNotificationTitle()%></td>
			<td><%=nv.getNotificationContent()%></td>
			
			<td><%=nv.getNotificationDate()%></td>
			
			</tr>
		
		<%} %>
	</table>
	
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>
</body>
</html>