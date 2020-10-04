<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="eunju.domain.*, eunju.persistence.*, java.util.*"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>:::내 화장품 목록:::</title>
</head>
<body>

<c:import url="every.jsp" var="everyurl" />
<c:out value="${everyurl}" escapeXml="false"/>

	<%
		if(session.getAttribute("memberVO") == null) {
	 		response.sendRedirect("index.html");
		}
	%>
	<h3><%=session.getAttribute("name")%>님께서 등록하신 화장품 목록</h3>
	
	
<table>
		<tr>
		<td class="a">이름</td><td class="a">브랜드</td><td class="a">타입</td><td class="a">모양</td>
		<td class="a">유효기간</td><td class="a">개봉기간</td><td class="a">개봉 후 유효기간</td><td class="a">등록날짜</td>
		<%
			List<CosmeticVO> cosmeList = 
			(List<CosmeticVO>)request.getAttribute("CosmeticList");
			for(CosmeticVO cv : cosmeList ) { 
		%>
			<tr>
			<td><%=cv.getCosmeticName()%></td>
			<td><%=cv.getCosmeticBrand()%></td>
			
			<td><%=cv.getCosmeticType()%></td>
			<td><%=cv.getCosmeticShape()%></td>
			<td><%=cv.getCosmeticExpirationdate()%></td>
			
			<td><%=cv.getCosmeticOpendate()%></td>
			<td><%=cv.getCosmeticOpenExpirationdate()%></td>
			<td><%=cv.getCosmeticDate()%></td>
			
			</tr>
		
		<%} %>
	</table>
	
 
	<a href="/eunju_free/MemberServlet?key=mypage">마이페이지로 돌아가기</a><p></p>
	
	<a href="/eunju_free/MemberServlet?key=gotohome">홈 가기</a>

</body>
</html>