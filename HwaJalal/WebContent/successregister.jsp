<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="eunju.domain.CosmeticVO"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::화장품 등록 완료:::</title>
<link rel="stylesheet" href="resources/every.css" type="text/css"></link>
	<link href="https://fonts.googleapis.com/css?family=Song+Myung&amp;subset=korean" rel="stylesheet">
	
</head>
<body>

	<%
		if(session.getAttribute("memberVO") == null) {
	 		response.sendRedirect("index.html");
		}
	%>
	
	<div class="logo"><img src="resources/logosmall.png"></div>
	<%CosmeticVO cv = (CosmeticVO)request.getAttribute("cosmetic"); %>
	<h3>화장품 등록 완료</h3>
	<p>이름 : <%=cv.getCosmeticName() %></p>
	<p>브랜드 : <%=cv.getCosmeticBrand() %></p>
	<p>타입 : <%=cv.getCosmeticType() %></p>
	<p>용기 : <%=cv.getCosmeticShape() %></p>
	<p>오픈일자 : <%=cv.getCosmeticOpendate() %></p>
	<p>유통기한 : <%=cv.getCosmeticExpirationdate() %></p>
	<p>개봉시 유통기한 : <%=cv.getCosmeticOpenExpirationdate() %></p>
	<p>작성날짜 : <%=cv.getCosmeticDate() %></p>
	
	<a href="/eunju_free/CosmeticServlet?key=showmyall">내가 등록한 화장품 목록 보기</a><p></p>
	
	<a href="/eunju_free/MemberServlet?key=mypage">마이페이지로 돌아가기</a><p></p>
	
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>
</body>
</html>