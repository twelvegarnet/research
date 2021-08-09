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
	
	<div style="text-align: right;">
		<c:if test="${sessionScope.userGrade eq 'admin' }">
			<label>관리자 ${sessionScope.userName }님 안녕하세요</label>
		</c:if>
		<c:if test="${sessionScope.userGrade eq 'normal' }">
			<label>${sessionScope.userName } 회원님 안녕하세요</label>
		</c:if>
		<c:if test="${sessionScope.userGrade eq null }">
			<label>비회원님 안녕하세요</label>
		</c:if>
		<c:if test="${sessionScope.userGrade eq null }">
			<input type="button" value="로그인" style="margin: 5px;" onclick="login()" />
		</c:if>
		<c:if test="${sessionScope.userGrade ne null }">
			<input type="button" value="로그아웃" style="margin: 5px;" onclick="logout()" />
		</c:if>
	</div>
	
	<table style="width: 100%; margin-top: 30px; table-layout: fixed; border-top: 2px solid blue;">
	
		<!-- List Header -->
		<tr>
			<th style="width: 5%;">NO</th>
			<th style="width: 48%;">제목</th>
			<th style="width: 10%;">시작일</th>
			<th style="width: 10%;">마감일</th>
			<th style="width: 10%;">완료여부</th>
			<th style="width: 5%;">참여수</th>
			<th style="width: 5%;">참여</th>
			<th style="width: 7%;">결과보기</th>
		</tr>
		
		<!-- List Body -->
		<c:forEach var="r" items="${rlist }">
			<fmt:formatDate value="${r.surStartDate }" pattern="yyMMdd" var="start" />
			<fmt:formatDate value="${r.surEndDate }" pattern="yyMMdd" var="end" />
			<tr style="border-bottom: 1px solid #ccc;">
				<td style="text-align: center;">${r.surSeq }</td>
				
					<td style="padding-left: 20px; overflow: hidden; text-overflow: ellipsis;"><a class="title" href="/research/view?surSeq=${r.surSeq }" style="white-space: pre;"><c:out value="${r.surTitle }" escapeXml="true" /></a></td>
				
				
				<td style="text-align: center;"><fmt:formatDate value="${r.surStartDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;"><fmt:formatDate value="${r.surEndDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;">
					<c:if test="${now lt start }">설문예정</c:if>
					<c:if test="${now ge start && now le end }">진행중</c:if>
					<c:if test="${now gt end }">완료</c:if>
				</td>
				<td style="text-align: center;">${r.hit }</td>
				<td style="text-align: center;"><input type="button" value="참여" onclick="goSurvey(${r.surSeq}, ${now }, ${start }, ${end })" /></td>
				<td style="text-align: center;"><input type="button" value="결과" onclick="viewResult(${r.surSeq}, ${now }, ${start }, ${end })" /></td>
			</tr>
		</c:forEach>
		
		
	</table>
	
	<div id="btnBox" style="text-align: right; margin: 30px 10px 0px 0px;">
		<c:if test="${sessionScope.userGrade eq 'admin' }">
			<input type="button" value="등록" onclick="registSurvey()" style="color: white; background-color: black;" />
		</c:if>
		<input type="button" value="목록" onclick="originList()" />
	</div>






</div>

<script>
function login(){
	location.href = "/research/login";
}

function logout(){
	location.href = "/research/logout";
}



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

function viewResult(seq_no, today, start, end){
	if( today < start ){
		
		alert("설문조사 시작일 전입니다.");
		return false
		
	} else if ( today > end ){
		
		location.href = "/research/viewResult?surSeq="+seq_no;
		
	} else {
		
		if( "admin" === "${sessionScope.userGrade}" ){
			
			location.href = "/research/viewResult?surSeq="+seq_no;

			
		} else if ( 'normal' === '${sessionScope.userGrade}'){
			
			alert("설문조사 종료 후 확인 가능합니다.");
			return false
		} else {
			
			alert("로그인 후 이용가능합니다");
			return false
		}
	}
}



function registSurvey(){
	
	if( 'admin' === '${sessionScope.userGrade }' ){
		location.href = "/research/create";
	} else {
		alert("관리자만 설문조사를 등록할 수 있습니다.");
	}
}

function originList(){
	location.href = "/research/list";
}


</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />