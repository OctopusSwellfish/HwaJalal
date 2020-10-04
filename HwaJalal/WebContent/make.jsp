<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@taglib uri="/WEB-INF/tld/notificationTag.tld" prefix="mytag" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::만든 이:::</title>
</head>
<body>

<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>


<h3>만든 이</h3>
<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>
<p><mytag:print/></p>
</body>
</html>