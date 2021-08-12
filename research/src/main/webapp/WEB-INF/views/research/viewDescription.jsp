<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>

<c:forEach var="sr" items="${srList }">
	
	<c:choose>
		<c:when test="${not empty sr.description }">
			<br>
			<div class="description">*&nbsp;<c:out value="${sr.description }" escapeXml="true" /></div>
		</c:when>
	</c:choose>
	
</c:forEach>

<div id="noDescription" style="display: none;">
	<label>* 작성된 선택사유가 없습니다.</label>
</div>

<br>

<script>
const descriptionCnt = $("div[class=description]").length;
console.log(descriptionCnt);

if(descriptionCnt == 0){
	$("#noDescription").attr("style", "display: block;");
}
</script>

</body>
</html>