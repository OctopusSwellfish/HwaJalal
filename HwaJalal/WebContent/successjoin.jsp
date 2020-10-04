<%@ page language="java" contentType="text/html; charset=UTF-8"
import="eunju.domain.MemberVO"
    pageEncoding="UTF-8"%>
    
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::가입을 축하합니다:::</title>
	
</head>
<body>

<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>
	<%MemberVO mb = (MemberVO)request.getAttribute("member"); %>
	<h3>가입을 축하합니다!</h3>
	<p><%= mb.getMemberName() %>님의 가입 정보</p>
	<p>아이디 <%=mb.getMemberloginID() %></p>
	<p>이름 <%=mb.getMemberName() %></p>
	<p>생일 <%=mb.getMemberBirth() %></p>
	
	
	<a href="/eunju_free/login.html">로그인 하기</a><p></p> 
	<a href="/eunju_free/NotificationServlet?key=list">공지사항 확인하기</a><p></p>
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>
	
</body>
</html>