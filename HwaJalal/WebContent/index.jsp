<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>::화잘알, 화장품 잘 알고 사용하자.:::</title>

	<link rel="stylesheet" href="resources/welcome.css" type="text/css"></link>
</head>
<body>


<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>

<div class="main-box">
		<p>화장품, 기한 잘 알고 사용하자.</p>
		<p>화장품의 유효기간을 등록해서 관리하는 웹 서비스입니다!</p>
		<p>등록하고 싶은 화장품이 있으면 먼저 회원가입하세요!</p>
		<a href="/eunju_free/join.html">회원가입</a><p></p>
		<%
		if(session.getAttribute("memberVO") == null) {
			out.print("<a href=\"/eunju_free/login.html\">로그인</a><p></p>");
		}else {
			out.print("<a href=\"/eunju_free/MemberServlet?key=mypage\">마이페이지 가기</a><p></p>");
		}
		%>
		<a href="/eunju_free/NotificationServlet?key=list">공지사항</a><p></p>
		
		<a href="/eunju_free/make.jsp">만든이</a>
	
	</div>

</body>
</html>