<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::새 화장품 등록하기:::</title>

</head>
<body>


<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>


	<%
		if(session.getAttribute("memberVO") == null) {
	 		response.sendRedirect("index.html");
		}
	%>
	<h3>화장품 정보 등록하기</h3>
	
	<form action="/eunju_free/CosmeticServlet" method="post">
		<input type="hidden" name="key" value="register">
		<p> 이름 : <input type="text" name="cosmeticname" required></p>
		<p> 브랜드 : <input type="text" name="cosmeticbrand"></p>
		
		<p>
		 타입 :
		<label for="base">기초</label>
			<input type="radio" name="cosmetictype" value="기초">
		<label for="foundation">베이스</label>
			<input type="radio" name="cosmetictype" value="베이스">
		<label for="color">색조</label>
			<input type="radio" name="cosmetictype" value="색조">
		<label for="cleansing">클렌징</label>
			<input type="radio" name="cosmetictype" value="클렌징">
		</p>
		<p>용기 모양 :
		<label for="pot">단지</label>
			<input type="radio" name="cosmeticshape" value="단지">
		<label for="tube">튜브</label>
			<input type="radio" checked name="cosmeticshape" value="튜브">
		<label for="pump">펌핑</label>
			<input type="radio" checked name="cosmeticshape" value="펌핑">
		<label for="spray">분사</label>
			<input type="radio" checked name="cosmeticshape" value="분사">
		<label for="spray">스패츌러/틴트</label>
			<input type="radio" checked name="cosmeticshape" value="스패츌러/틴트">
		<label for="spray">쿠션</label>
			<input type="radio" checked name="cosmeticshape" value="쿠션">
		<label for="spray">립스틱</label>
			<input type="radio" checked name="cosmeticshape" value="쿠션">
		</p>
		
		<p>개봉 날짜 :<input type="date" name="cosmeticopendate"></p>
		<p>유효 기간 : <input type="date" name="cosmeticexpirationdate"></p>
		<p>개봉 후 사용 기간 :<input type="date" name="cosmeticopenexpirationdate"></p>
		
		<input type="submit" value="등록">
		
		
	</form>
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>
</body>
</html>