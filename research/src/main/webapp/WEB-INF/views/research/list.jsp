<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<fmt:formatDate value="<%=new Date() %>" pattern="yyMMdd" var="now" />

<style type="text/css">
.title:visited, .title:link {
	color: black;
	text-decoration: none;
}

.title:hover {
	color: black;
	text-decoration: underline;
}

tr {
	border-bottom: 1px solid black;
	height: 40px;
}
</style>

<div id="content" style="width: 1000px; margin: 0 auto; min-height: 663px;">

	<div class="head" style="font-size: 20pt; font-weight: 500;">설문조사 목록</div>
	
	<table style="width: 100%; margin-top: 30px; table-layout: fixed; border-top: 2px solid blue;">
	
		<!-- List Header -->
		<tr>
			<th style="width: 5%;">NO</th>
			<th style="width: 50%;">제목</th>
			<th style="width: 10%;">시작일</th>
			<th style="width: 10%;">마감일</th>
			<th style="width: 10%;">완료여부</th>
			<th style="width: 5%;">조회수</th>
			<th style="width: 10%;">결과확인</th>
		</tr>
		
		<!-- List Body -->
		<c:forEach var="r" items="${rlist }">
			<fmt:formatDate value="${r.surEndDate }" pattern="yyMMdd" var="end" />
			<tr style="border-bottom: 1px solid #ccc;">
				<td style="text-align: center;">${r.surSeq }</td>
				<td style="padding-left: 20px; overflow: hidden; text-overflow: ellipsis;"><a class="title" href="/research/view?surSeq=${r.surSeq }" style="white-space: pre;"><c:out value="${r.surTitle }" escapeXml="true" /></a></td>
				<td style="text-align: center;"><fmt:formatDate value="${r.surStartDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;"><fmt:formatDate value="${r.surEndDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;">
					<c:if test="${end lt now }">완료</c:if>
					<c:if test="${end ge now }">진행중</c:if>
				</td>
				<td style="text-align: center;">${r.hit }</td>
				<td style="text-align: center;"><input type="button" value="결과보기" /></td>
			</tr>
		</c:forEach>
		
		
	</table>
	
	<div id="btnBox" style="text-align: right; margin-top: 30px;">
		<input type="button" value="목록" />
		<input type="button" value="등록" onclick="registSurvey()" style="color: white; background-color: black;" />
	</div>






</div>

<script>
function registSurvey(){
	location.href = "/research/create";
}


</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />