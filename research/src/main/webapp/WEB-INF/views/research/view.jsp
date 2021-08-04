<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />



<!-- css start -->
<style type="text/css">
.center {
	text-align: center;
}

table {
	border-collapse: collapse;
}

.silver {
	background-color: #f6f6f6;
	color: #666;
}

tr {
	border-bottom: 1px solid #ccc;
	height: 40px;
}

.padding {
	padding: 5px;
}

.inline-block {
	display: inline-block;
}
</style>
<!-- css end -->



<!-- content start -->
<div id="contnet" style="width: 1000px; margin: 0 auto; min-height: 663px;">

	<div class="head" style="font-size: 20pt; font-weight: 500; margin-top: 20px;">설문조사</div>
	
	<table style="width: 100%; margin-top: 30px; table-layout: fixed; border-top: 2px solid blue;" >
		
		<tr>
			<td class="center" colspan="1">제목</td>
			<td colspan="5" style="padding: 8px;"><c:out value="${survey.surTitle }" escapeXml="true" /></td>
		</tr>
		<tr>
			<td class="center" colspan="1">시작일</td>
			<td colspan="1" style="padding: 8px;"><fmt:formatDate value="${survey.surStartDate }" pattern="yyyy-MM-dd" /></td>
			<td class="center" colspan="1">종료일</td>
			<td colspan="1" style="padding: 8px;"><fmt:formatDate value="${survey.surEndDate }" pattern="yyyy-MM-dd" /></td>
			<td class="center" colspan="1">조회수</td>
			<td colspan="1">${survey.hit }</td>
		</tr>
		<tr>
			<td class="center" colspan="1">문항수</td>
			<td colspan="5" style="padding: 8px;">${survey.surCnt }개</td>
		</tr>
		
	</table>
	
	<form action="/research/submit" method="POST" name="surveyContent">
	
		<div id="surqList">
			
			<c:forEach var="sc" items="${surveyContent }" begin="0" end="${survey.surCnt }" varStatus="status">
				<input type="hidden" name="surqSeq${status.count }" value="${sc.surqSeq }" />
				<div class="padding">${status.count}.&nbsp;<c:out value="${sc.surqTitle }" escapeXml="true" /></div>
				<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="1" />①&nbsp;<c:out value="${sc.surqTitle1 }" escapeXml="true" /></div>
				<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="2" />②&nbsp;<c:out value="${sc.surqTitle2 }" escapeXml="true" /></div>
				<c:if test="${not empty sc.surqTitle3 }">
					<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="3" />③&nbsp;<c:out value="${sc.surqTitle3 }" escapeXml="true" /></div>
				</c:if>
				<c:if test="${not empty sc.surqTitle4 }">
					<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="4" />④&nbsp;<c:out value="${sc.surqTitle4 }" escapeXml="true" /></div>
				</c:if>
				<c:if test="${not empty sc.surqTitle5 }">
					<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="5" />⑤&nbsp;<c:out value="${sc.surqTitle5 }" escapeXml="true" /></div>
				</c:if>
				<div class="padding">선택사유&nbsp;<input type="text" style="width: 693px;" name="description${status.count }" maxlength="50" /></div>
				<hr>
				<br>
			</c:forEach>
			
		</div>
	
		<input type="hidden" name="surSeq" value="${survey.surSeq }" />
		<input type="hidden" name="surCnt" value="${survey.surCnt }" />

	</form>
	
	<div id="btnBox" class="center" style="margin: 20px 0px;">
		<input type="button" onclick="submitSur()" value="제출" />
		<input type="button" onclick="javascript:history.go(-1)" value="목록" />
	</div>
	
	
</div> <!-- content end -->

<script>
function showResult(){
	console.log("결과보기 버튼 작동")	
}



function submitSur(){
	const f = document.surveyContent;
	f.submit();
}


</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />