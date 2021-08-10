<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />
<link rel="stylesheet" href="/resources/css/pagination.css">

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
			<label>로그인을 하셔야 이용가능합니다.</label>
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
			<th style="width: 43%;">제목</th>
			<th style="width: 10%;">작성자</th>
			<th style="width: 10%;">시작일</th>
			<th style="width: 10%;">마감일</th>
			<th style="width: 10%;">완료여부</th>
			<th style="width: 5%;">참여수</th>
			<!-- <th style="width: 5%;">참여</th> -->
			<th style="width: 7%;">결과보기</th>
		</tr>
		
		<!-- List Body -->
		<c:forEach var="s" items="${slist }">
			<fmt:formatDate value="${s.surStartDate }" pattern="yyMMdd" var="start" />
			<fmt:formatDate value="${s.surEndDate }" pattern="yyMMdd" var="end" />
			<tr style="border-bottom: 1px solid #ccc;">
				<td style="text-align: center;">${s.rownum }</td>
				<td style="padding-left: 20px; overflow: hidden; text-overflow: ellipsis;"><a class="title" href="/research/view?surSeq=${s.surSeq }" style="white-space: pre;"><c:out value="${s.surTitle }" escapeXml="true" /></a></td>
				<td style="text-align: center;">${s.writer }</td>
				<td style="text-align: center;"><fmt:formatDate value="${s.surStartDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;"><fmt:formatDate value="${s.surEndDate }" pattern="yyyy.MM.dd" /></td>
				<td style="text-align: center;">
					<c:if test="${now lt start }">설문예정</c:if>
					<c:if test="${now ge start && now le end }">진행중</c:if>
					<c:if test="${now gt end }">완료</c:if>
				</td>
				<td style="text-align: center;">${s.hit }</td>
				<%-- <c:if test="${s.didSur eq 0}">
					<td style="text-align: center;"><input type="button" value="참여" onclick="goSurvey(${s.surSeq}, ${now }, ${start }, ${end })" /></td>
				</c:if>
				<c:if test="${s.didSur ne 0}">
					<td style="text-align: center;"><input type="button" value="참여" onclick="alreadySubmit(${s.surSeq })" /></td>
				</c:if> --%>
				<td style="text-align: center;"><input type="button" value="결과" onclick="viewResult(${s.hit }, ${s.surSeq}, ${now }, ${start }, ${end })" /></td>
			</tr>
		</c:forEach>
		
		
	</table>
	
	
	<div id="btnBox" style="text-align: center; margin: 30px 10px 0px 0px;">
		
		<select id="category" name="category" style="height: 25px;">
			<option value="title">제목</option>
			<option value="writer">작성자</option>
		</select>
		<input type="text" id="search" name="search" style="height: 19px;" />
		<input type="button" onclick="search()" value="검색" style="height: 25px;" />
		
		<input type="button" value="목록" onclick="originList()" style="margin-right: 10px; float: right;" />
		<c:if test="${sessionScope.userGrade eq 'admin' }">
			<input type="button" value="등록" onclick="registSurvey()" style="margin-right: 10px; color: white; background-color: black; float: right;" />
		</c:if>
		
	</div>

	<c:import url="/WEB-INF/views/research/paging.jsp" />





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

function alreadySubmit(sur_seq){
	
	const submitChk = confirm("이미 참여한 설문조사입니다. 상세보기로 이동하시겠습니까?");

	if(submitChk == true){
		location.href = "/research/view?surSeq="+sur_seq;
	}
}

function viewResult(hit, seq_no, today, start, end){
	
	if(hit == 0){
		alert("아직 아무도 참여하지 않은 설문조사입니다.");
		return false
	}
	
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



function search(){
	let category = $("#category").val();
	let search = $("#search").val();
	
	location.href = "/research/list?category="+category+"&search="+search;
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