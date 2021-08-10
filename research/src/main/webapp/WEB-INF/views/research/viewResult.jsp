<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- content start -->
<div id="content" style="width: 1000px; margin: 0 auto; min-height: 663px;">
	
	<div class="head" style="font-size: 20pt; font-weight: 500; margin-top: 20px;">설문조사 결과</div>
	<hr>
	
	
	<c:forEach var="sc" items="${surveyContent }" begin="0" end="${survey.surCnt }" varStatus="status">
		<fmt:formatNumber value="${sc.NUM1 }" pattern=".00" var="num1" />
		<fmt:formatNumber value="${sc.NUM2 }" pattern=".00" var="num2" />
		<fmt:formatNumber value="${sc.NUM3 }" pattern=".00" var="num3" />
		<fmt:formatNumber value="${sc.NUM4 }" pattern=".00" var="num4" />
		<fmt:formatNumber value="${sc.NUM5 }" pattern=".00" var="num5" />
		<fmt:formatNumber value="${sc.TOTALCNT }" pattern=".00" var="totalCnt" />
		
		<div id="surveyResult">
		
	 		<div>${status.count }.&nbsp;<c:out value="${sc.SURQ_TITLE }" escapeXml="true" /></div>
	 		
	 		<div>
		 		<div style="width: 100%; display: inline-block; overflow: hidden; text-overflow: ellipsis; white-space: pre;">①&nbsp;<c:out value="${sc.SURQ_TITLE1 }" escapeXml="true" /></div>
			 	<c:if test="${sc.NUM1 ne 0}">
			 		<div style="width: <fmt:formatNumber value="${num1/totalCnt*100 }" pattern=".0" />%; background-color: red; display: inline-block; line-height: 15px;">&nbsp;</div>
			 		<fmt:formatNumber value="${num1/totalCnt*100 }" pattern=".0" />%
			 	</c:if>
			 	<c:if test="${sc.NUM1 eq 0}">
			 		<div style="width: 5%; background-color: red; display: inline-block; line-height: 15px;">&nbsp;</div>
			 		0%
			 	</c:if>
			 	<input type="button" value="선택이유" onclick="viewDescription(${sc.SUR_SEQ}, ${sc.SURQ_SEQ}, 1)" style="float: right;" />
		 	</div>

	 		<div>
	 			<div style="width: 100%; display: inline-block; overflow: hidden; text-overflow: ellipsis; white-space: pre;">②&nbsp;<c:out value="${sc.SURQ_TITLE2 }" escapeXml="true" /></div>
			 	<c:if test="${sc.NUM2 ne 0}">
		 			<div style="width: <fmt:formatNumber value="${num2/totalCnt*100 }" pattern=".0" />%; background-color: blue; display: inline-block; line-height: 15px;">&nbsp;</div>
			 		<fmt:formatNumber value="${num2/totalCnt*100 }" pattern=".0" />%
			 	</c:if>
			 	<c:if test="${sc.NUM2 eq 0}">
		 			<div style="width: 5%; background-color: blue; display: inline-block; line-height: 15px;">&nbsp;</div>
			 		0%
			 	</c:if>
			 	<input type="button" value="선택이유" onclick="viewDescription(${sc.SUR_SEQ}, ${sc.SURQ_SEQ}, 2)" style="float: right;" />
	 		</div>
	 		
	 		<c:if test="${not empty sc.SURQ_TITLE3 }">
	 			<div>
	 				<div style="width: 100%; display: inline-block; overflow: hidden; text-overflow: ellipsis; white-space: pre;">③&nbsp;<c:out value="${sc.SURQ_TITLE3 }" escapeXml="true" /></div>
			 		<c:if test="${sc.NUM3 ne 0}">
		 				<div style="width: <fmt:formatNumber value="${num3/totalCnt*100 }" pattern=".0" />%; background-color: orange; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		<fmt:formatNumber value="${num3/totalCnt*100 }" pattern=".0" />%
				 	</c:if>
				 	<c:if test="${sc.NUM3 eq 0}">
		 				<div style="width: 5%; background-color: orange; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		0%
				 	</c:if>
			 		<input type="button" value="선택이유" onclick="viewDescription(${sc.SUR_SEQ}, ${sc.SURQ_SEQ}, 3)" style="float: right;" />
	 			</div>
	 		</c:if>
	 		
	 		<c:if test="${not empty sc.SURQ_TITLE4 }">
	 			<div>
	 				<div style="width: 100%; display: inline-block; overflow: hidden; text-overflow: ellipsis; white-space: pre;">④&nbsp;<c:out value="${sc.SURQ_TITLE4 }" escapeXml="true" /></div>
			 		<c:if test="${sc.NUM4 ne 0}">
		 				<div style="width: <fmt:formatNumber value="${num4/totalCnt*100 }" pattern=".0" />%; background-color: purple; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		<fmt:formatNumber value="${num4/totalCnt*100 }" pattern=".0" />%
				 	</c:if>
				 	<c:if test="${sc.NUM4 eq 0}">
		 				<div style="width: 5%; background-color: purple; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		0%
				 	</c:if>
			 		<input type="button" value="선택이유" onclick="viewDescription(${sc.SUR_SEQ}, ${sc.SURQ_SEQ}, 4)" style="float: right;" />
	 			</div>
	 		</c:if>
	 		
	 		<c:if test="${not empty sc.SURQ_TITLE5 }">
	 			<div>
	 				<div style="width: 100%; display: inline-block; overflow: hidden; text-overflow: ellipsis; white-space: pre;">⑤&nbsp;<c:out value="${sc.SURQ_TITLE5 }" escapeXml="true" /></div>
			 		<c:if test="${sc.NUM5 ne 0}">
		 				<div style="width: <fmt:formatNumber value="${num5/totalCnt*100 }" pattern=".0" />%; background-color: gray; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		<fmt:formatNumber value="${num5/totalCnt*100 }" pattern=".0" />%
				 	</c:if>
				 	<c:if test="${sc.NUM5 eq 0}">
		 				<div style="width: 5%; background-color: gray; display: inline-block; line-height: 15px;">&nbsp;</div>
				 		0%
				 	</c:if>
			 		<input type="button" value="선택이유" onclick="viewDescription(${sc.SUR_SEQ}, ${sc.SURQ_SEQ}, 5)" style="float: right;" />
	 			</div>
	 		</c:if>
		</div>
		<hr><br>
	</c:forEach>
	
	
	
	<div style="text-align: center;"><input type="button" value="돌아가기" onclick="backToList()" /></div>
	
<script>
function viewDescription(sur_seq, surq_seq, choose_num){
	
	/* 팝업창 옵션 */
	const option = 'top=100, width=500, height=500, status=no, scrollbars=yes, menubar=no, toolbar=no, resizable=no';
	
	/* 보내야하는 것 */
	
	
	/* 팝업 호출 */
	window.open("/research/viewDescription?surSeq="+sur_seq+"&surqSeq="+surq_seq+"&chooseNum="+choose_num , "선택이유 모아보기", option);
}


function backToList(){
	location.href = "/research/list";
}

</script>

</div> <!-- content end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />