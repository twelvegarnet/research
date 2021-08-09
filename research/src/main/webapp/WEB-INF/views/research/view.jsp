<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:formatDate value="<%=new Date() %>" pattern="yyMMdd" var="now" />

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
<div id="content" style="width: 1000px; margin: 0 auto; min-height: 663px;">

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
			<td class="center" colspan="1">참여자 수</td>
			<td colspan="1">${survey.hit }</td>
		</tr>
		<tr>
			<td class="center" colspan="1">문항수</td>
			<td colspan="5" style="padding: 8px;">${survey.surCnt }개</td>
		</tr>
		
	</table>
	
	<form action="/research/submit" method="POST" name="surveyContent">
	
		<div id="surqList" style="margin-top: 10px;">
			<fmt:formatDate value="${survey.surStartDate }" pattern="yyMMdd" var="start" />
			<fmt:formatDate value="${survey.surEndDate }" pattern="yyMMdd" var="end" />
			
			<c:forEach var="sc" items="${surveyContent }" begin="0" end="${survey.surCnt }" varStatus="status">
				<input type="hidden" name="surqSeq${status.count }" value="${sc.SURQ_SEQ }" />
				<div class="padding">${status.count}.&nbsp;<c:out value="${sc.SURQ_TITLE }" escapeXml="true" /></div>
				
				<c:choose>
					<c:when test="${sc.CHOOSENUM eq 1 }">
						<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="1" checked="checked" disabled="disabled" />①&nbsp;<c:out value="${sc.SURQ_TITLE1 }" escapeXml="true" /></div>
						<c:set value="${sc.CHOOSENUM }" var="doSurveyChk" />
					</c:when>
					<c:otherwise>
						<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="1" disabled="disabled" />①&nbsp;<c:out value="${sc.SURQ_TITLE1 }" escapeXml="true" /></div>
					</c:otherwise>
				</c:choose>
				
				<c:choose>
					<c:when test="${sc.CHOOSENUM eq 2 }">
						<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="2" checked="checked" disabled="disabled" />②&nbsp;<c:out value="${sc.SURQ_TITLE2 }" escapeXml="true" /></div>
					</c:when>
					<c:otherwise>
						<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="2" disabled="disabled" />②&nbsp;<c:out value="${sc.SURQ_TITLE2 }" escapeXml="true" /></div>
					</c:otherwise>
				</c:choose>
				
				<c:if test="${not empty sc.SURQ_TITLE3 }">
					<c:choose>
						<c:when test="${sc.CHOOSENUM eq 3 }">
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="3" checked="checked" disabled="disabled" />③&nbsp;<c:out value="${sc.SURQ_TITLE3 }" escapeXml="true" /></div>
						</c:when>
					<c:otherwise>
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="3" disabled="disabled" />③&nbsp;<c:out value="${sc.SURQ_TITLE3 }" escapeXml="true" /></div>
					</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:if test="${not empty sc.SURQ_TITLE4 }">
					<c:choose>
						<c:when test="${sc.CHOOSENUM eq 4 }">
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="4" checked="checked" disabled="disabled" />④&nbsp;<c:out value="${sc.SURQ_TITLE4 }" escapeXml="true" /></div>
						</c:when>
						<c:otherwise>
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="4" disabled="disabled" />④&nbsp;<c:out value="${sc.SURQ_TITLE4 }" escapeXml="true" /></div>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:if test="${not empty sc.SURQ_TITLE5 }">
					<c:choose>
						<c:when test="${sc.CHOOSENUM eq 5 }">
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="5" checked="checked" disabled="disabled" />⑤&nbsp;<c:out value="${sc.SURQ_TITLE5 }" escapeXml="true" /></div>
						</c:when>
						<c:otherwise>
							<div class="padding inline-block"><input type="radio" name="chooseNum${status.count }" value="5" disabled="disabled" />⑤&nbsp;<c:out value="${sc.SURQ_TITLE5 }" escapeXml="true" /></div>
						</c:otherwise>
					</c:choose>
				</c:if>
				
				<c:if test="${not empty sc.DESCRIPTION }">
					<div style="width: 80%; margin-top: 20px;">선택사유 : <c:out value="${sc.DESCRIPTION }" escapeXml="true" /></div>
				</c:if>
				
				<hr>
			</c:forEach>
			
		</div>
	
	</form>
	
	<div id="btnBox" class="center" style="margin: 20px 0px;">
		<c:if test="${sessionScope.userGrade eq 'admin'}">
			<input type="button" onclick="updateSur()" value="설문조사 수정" />
			<input type="button" onclick="deleteSur()" value="설문조사 삭제" />
		</c:if>
		
		<c:if test="${empty doSurveyChk }">
			<input type="button" onclick="goSurvey(${survey.surSeq }, ${now }, ${start }, ${end } )" value="설문조사 참여" />
		</c:if>
		<c:if test="${not empty doSurveyChk }">
			<input type="button" onclick="updateResult()" value="설문조사 결과 수정" />
			<input type="button" onclick="deleteResult()" value="설문조사 결과 삭제" />
		</c:if>
		<input type="button" onclick="location.href='/research/list'" value="목록" />
	</div>
	
	<input type="hidden" id="start" value="<fmt:formatDate value="${survey.surStartDate }" pattern="yyyy-MM-dd" />" />
	<input type="hidden" id="end" value="<fmt:formatDate value="${survey.surEndDate }" pattern="yyyy-MM-dd" />" />
	
</div> <!-- content end -->

<script>

/* 오늘 날짜 미리 얻어놓기 */
const date = new Date();
const year = date.getFullYear();
let month = date.getMonth()+1;
if(month < 10){
	month = "0" + month;
}
let day = date.getDate();
if(day < 10){
	day = "0" + day;
}

const getToday = (year + "" + month + "" + day)*1; // 오늘 날짜


function goSurvey(seq_no, today, start, end ){
	
	if( '' === '${sessionScope.userGrade}'){
		
		alert("로그인 후 이용가능합니다.");
		return false
	}
	
	
	if( today < start ){
		
		alert("설문조사 시작일 전입니다.");
		return false
		
	} else if ( today > end ) {
		
		alert("해당 설문조사는 종료되었습니다.");
		return false
		
	} else {
		
		location.href = "/research/doSurvey?surSeq="+seq_no;
	}
}



/* 설문조사 수정 - 관리자권한 (이미 설문조사가 시작되었다면 수정할 수 없음) */
function updateSur(){

	
	const getToday = (year + "" + month + "" + day)*1; // 오늘 날짜
	
	const startDate = document.querySelector("#start").value.replace(/-/g,"")*1; // 설문조사 시작일
	
	if(startDate <= getToday){
		alert("이미 시작된 설문은 수정할 수 없습니다.");
		return false;
	}
	
	location.href = "/research/updateSurveyContent?surSeq="+${survey.surSeq};
}

/* 설문조사 삭제 - 관리자권한 (이미 설문조사가 시작되었다면 삭제할 수 없음) */
function deleteSur(){
	
	const startDate = document.querySelector("#start").value.replace(/-/g,"")*1; // 설문조사 시작일
	
	if(startDate < getToday){
		alert("이미 시작된 설문은 삭제할 수 없습니다.");
		return false;
	}
	
	const finalChk = confirm("정말 삭제하시겠습니까?");
	
	if(finalChk == true){
		location.href = "/research/deleteSurvey?surSeq="+${survey.surSeq};
	}
	
}


/* 설문조사 결과 수정 - 모든 회원 권한 (이미 설문조사가 종료되었다면 수정할 수 없음) */
function updateResult(){
	
	const endDate = document.querySelector("#end").value.replace(/-/g,"")*1; // 설문조사 종료일
	
	if(endDate < getToday){
		alert("이미 종료된 설문은 설문결과를 수정할 수 없습니다.");
		return false
	}
	
	const finalChk = confirm("수정하시겠습니까?")
	
	if(finalChk == true){
		location.href = "/research/updateSurveyResult?surSeq="+${survey.surSeq};
	}
}


/* 설문조사 결과 삭제 - 모든 회원 권한 (이미 설문조사가 종료되었다면 삭제할 수 없음) */
function deleteResult(){
	
	const endDate = document.querySelector("#end").value.replace(/-/g,"")*1; // 설문조사 종료일
	
	if(endDate < getToday){
		alert("이미 종료된 설문은 설문결과를 삭제할 수 없습니다.");
		return false
	}
	
	const finalChk = confirm("삭제하시겠습니까?")
	
	if(finalChk == true){
		location.href = "/research/deleteSurveyResult?surSeq="+${survey.surSeq};
	}
}
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />
















