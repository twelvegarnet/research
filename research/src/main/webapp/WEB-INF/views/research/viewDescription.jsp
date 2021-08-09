<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="sr" items="${srList }">
	
	<c:choose>
		<c:when test="${not empty sr.description }">
			<br>
			<div>*&nbsp;<c:out value="${sr.description }" escapeXml="true" /></div>
		</c:when>
		<c:otherwise>
			<div>작성된 선택사유 내역이 없습니다.</div>
		</c:otherwise>
	</c:choose>
	
</c:forEach>

<br>

</body>
</html>