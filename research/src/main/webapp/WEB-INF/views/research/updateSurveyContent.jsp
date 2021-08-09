<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />


<!-- CSS start -->
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

.input-text {
	border: 1px solid #c6c6c6;
	color: #666;
	padding: 4px;
	margin-left: 10px;
}

.margin {
	margin: 5px;
}

tr {
	border-bottom: 1px solid #ccc;
	height: 40px;
}

.nodisplay {
	display : none;
}
</style>
<!-- CSS end -->



<!-- Content start -->
<div id="content" style="width: 1000px; margin: 0 auto; min-height: 663px;">

	<div class="head" style="font-size: 20pt; font-weight: 500; margin-top: 20px;">설문조사 수정</div>
	
	<form action="/research/updateSurveyContent" method="POST" name="surveyContent">
	
	<table style="width: 100%; margin-top: 30px; table-layout: fixed; border-top: 2px solid blue;">
		<tr>
			<td class="center silver" colspan="1">제목</td>
			<td colspan="3"><input type="text" id="surTitle" name="surTitle" class="input-text" style="width: 97%;" maxlength="50" onkeyup="chkSurTitle()" value="<c:out value='${survey.surTitle }' escapeXml='true' />" /><br>
			<!-- <label id="surTitleStandard" class="nodisplay" style="margin-left: 10px;">* 설문조사 제목은 50자 이내의 한글, 영어, 숫자로 이루어져야 합니다.</label>
			<label id="surTitleOk" class="nodisplay" style="margin-left: 10px; color: green;">* 올바른 제목입니다.</label>
			<label id="surTitleWarn" class="nodisplay" style="margin-left: 10px; color: red;">* 설문조사 제목은 50자 이내의 한글, 영어, 숫자로 이루어져야 합니다.</label> -->
			</td>
		</tr>
		<tr>
			<td class="center silver" colspan="1">시작일</td>
			<td colspan="1"><input type="date" id="startDate" name="startDate" onchange="chkToday()" style="padding: 4px; margin-left: 10px;" value="<fmt:formatDate value="${survey.surStartDate }" pattern="yyyy-MM-dd" />" />
			<input type="hidden" id="surStartDate" name="surStartDate" value="${survey.surStartDate }" /></td>
			<td class="center silver" colspan="1">종료일</td>
			<td colspan="1"><input type="date" id="endDate" name="endDate" onchange="chkAftToday()" style="padding: 4px; margin-left: 10px;" value="<fmt:formatDate value="${survey.surEndDate }" pattern="yyyy-MM-dd" />" />
			<input type="hidden" id="surEndDate" name="surEndDate" value="<fmt:formatDate value="${survey.surEndDate }" pattern="yyyy-MM-dd" />" /></td>
		</tr>
		<tr>
			<td class="center silver" colspan="1">총 문항수</td>
			<td colspan="3">
				<select id="totalSurCnt" style="padding: 4px; margin-left: 10px;" disabled="disabled"></select>
				<input type="hidden" id="surCnt" name="surCnt" />
				<label style="margin-left: 20px;">* 문제 제목과 선택지 1, 2번 문항은 필수 입력사항입니다. (해당사항 우측 * 표시)</label>
			</td>
		</tr>
		
	</table>
	
	<!-- 추가된 문제 리스트를 담을 div -->
	<div id="surveyQuestionList" style="margin: 20px 0px; width: 98%;">
	
		<c:forEach var="sc" items="${surveyContent }" begin="0" end="${survey.surCnt }" varStatus="status">
			<div id="surq${status.count }" name="surq" class="surQuestion margin">
				<div><label>문제.</label><input type="text" id="surqTitle${status.count }" name="surqTitle" value="<c:out value="${sc.surqTitle }" escapeXml="true" />" class="input-text" style="width: 92.7%; margin-bottom: 5px;" maxlength="50" /><label>&nbsp;*</label></div>
				<div><label>①.</label><input type="text" id="surqTitle1_${status.count }" name="surqTitle1" value="<c:out value="${sc.surqTitle1 }" escapeXml="true" />" class="input-text margin" style="width: 95%;" maxlength="50" /><label>*</label></div>
				<div><label>②.</label><input type="text" id="surqTitle2_${status.count }" name="surqTitle2" value="<c:out value="${sc.surqTitle2 }" escapeXml="true" />" class="input-text margin" style="width: 95%;" maxlength="50" /><label>*</label></div>
				
				<c:choose>
					<c:when test="${not empty sc.surqTitle3 }">
						<div><label>③.</label><input type="text" id="surqTitle3_${status.count }" name="surqTitle3" value="<c:out value="${sc.surqTitle3 }" escapeXml="true" />" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:when>
					<c:otherwise>
						<div><label>③.</label><input type="text" id="surqTitle3_${status.count }" name="surqTitle3" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty sc.surqTitle4 }">
						<div><label>④.</label><input type="text" id="surqTitle4_${status.count }" name="surqTitle4" value="<c:out value="${sc.surqTitle4 }" escapeXml="true" />" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:when>
					<c:otherwise>
						<div><label>④.</label><input type="text" id="surqTitle4_${status.count }" name="surqTitle4" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${not empty sc.surqTitle3 }">
						<div><label>⑤.</label><input type="text" id="surqTitle5_${status.count }" name="surqTitle5" value="<c:out value="${sc.surqTitle5 }" escapeXml="true" />" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:when>
					<c:otherwise>
						<div><label>⑤.</label><input type="text" id="surqTitle5_${status.count }" name="surqTitle5" class="input-text margin" style="width: 95%;" maxlength="50" /></div>
					</c:otherwise>
				</c:choose>
				
				<div style="text-align: right;"><input type="button" style="margin-right: 12px;" onclick="deleteSurq(${status.count})" value="문제 삭제" /></div>
			</div>
			<hr>
			<br>
		</c:forEach>
	
	</div>
	
	<input type="hidden" name="surSeq" value="${survey.surSeq }" />
	
	</form>
	
	<div id="addBtnBox" style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
		<input type="button" onclick="addSurq()" value="문제 추가" />
		<hr>
	</div>
	
	<div id="btnBox" style="text-align: center;">
		<input type="button" onclick="submitBtn()" value="수정" />
		<input type="button" onclick="javascript:history.go(-1)" value="취소" />
	</div>
	

</div>

<script>
/* config.properties 파일에 설정된 크기만큼 문항수 선택 option 태그 생성 */
 
/* 문제 최대 개수 설정값 얻어오기 */
const optionCnt = <spring:eval expression="@config['MaxQuestionCount']" />;

/* option 태그 삽입하는 함수 호출 */
createOption();

/* select 태그에 option 삽입 함수 */
function createOption(){
	
	/* 생성된 option 태그들을 넣어줄 selectTag */
	const selectTag = document.getElementById("totalSurCnt");
	const realSurCnt = document.getElementById("surCnt");
	
	for(let i=1; i<=optionCnt; i++){
		
		/* option 태그 생성 */
		let optionTag = document.createElement('option');
		optionTag.setAttribute("value", i);
		optionTag.innerText = i;
		
		/* selectTag에 삽입 */
		selectTag.append(optionTag);
	}
	
	selectTag.value = ${survey.surCnt};
	realSurCnt.value = ${survey.surCnt};
}





/* 설문조사 제목 입력할 때 마다 체크 후 알림 */
function chkSurTitle(){
	
	const surTitleChk = /[^가-힣ㄱ-ㅎa-zA-Z0-9 ]{1,}/;						/* 설문조사 제목 조건식 */
	const surTitle = document.querySelector('#surTitle').value;				/* 설문조사 제목 */
	const surTitleStandard = document.querySelector('#surTitleStandard');	/* 초기 보여줄 조건문구 */
	const surTitleOk = document.querySelector('#surTitleOk');				/* 조건에 맞을 시 보여줄 문구 */
	const surTitleWarn = document.querySelector('#surTitleWarn');			/* 조건에 맞지않을 시 보여줄 문구 */
	const nospaceSurTitle = surTitle.replace(/ /g,"");						/* 공백제거 후 설문조사 제목 */
	const noSurTitle = surTitle.replace(/[^가-힣ㄱ-ㅎa-zA-Z0-9]/g,"");		/* 공백제거 후 설문조사 제목 */
	const nodisplay = "nodisplay";											/* display: none;을 가지고있는 클래스 명 */

	/* 설문조사 제목이 공백으로만 이루어져 있는지 검사 */
	if(nospaceSurTitle === ""){
		
		/* 공백만 있을 경우 경고알람 display */
		/* surTitleStandard.classList.add(nodisplay);
		surTitleOk.classList.add(nodisplay);
		surTitleWarn.classList.remove(nodisplay); */
		
		return false;
		
	} else {
		
		/* 설문조사 제목이 있을 때 조건식 검사 */
		if(surTitleChk.test(nospaceSurTitle)){
			
			/* 한글, 영어, 숫자, 공백 이외의 문자가 포함되어 있을 때 */
			/* surTitleStandard.classList.add(nodisplay);
			surTitleOk.classList.add(nodisplay);
			surTitleWarn.classList.remove(nodisplay); */
			
			return false;
			
		} else{
			
			/* 한글, 영어, 숫자, 공백만 포함되어 있을 때 */
			/* surTitleStandard.classList.add(nodisplay);
			surTitleOk.classList.remove(nodisplay);
			surTitleWarn.classList.add(nodisplay); */
		}
	}
}





/* 시작일, 종료일 설정 */


/* 오늘 날자 string으로 받아오기 */
function getToday(){
	
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
	
	const getToday = year + "" + month + "" + day;
	
	return getToday;
}

/* 초기 설정된 날짜 다듬기 */
const originStartYear = <fmt:formatDate value="${survey.surStartDate }" pattern="yyyy" />;
const originStartMonth = <fmt:formatDate value="${survey.surStartDate }" pattern="MM" />;
const originStartDay = <fmt:formatDate value="${survey.surStartDate }" pattern="dd" />;

const finalOriginStartDate = new Date(originStartYear, originStartMonth, originStartDay);

$("#surStartDate").val(finalOriginStartDate);

const originEndYear = <fmt:formatDate value="${survey.surEndDate }" pattern="yyyy" />;
const originEndMonth = <fmt:formatDate value="${survey.surEndDate }" pattern="MM" />;
const originEndDay = <fmt:formatDate value="${survey.surEndDate }" pattern="dd" />;

const finalOriginEndDate = new Date(originEndYear, originEndMonth, originEndDay);

$("#surEndDate").val(finalOriginEndDate);


/* 시작일 설정시 오늘 날자보다 이전 날자 선택 불가 */
function chkToday(){

	/* 기존에 설정된 시작일 얻어오기 */
	const originStartDay = new Date(document.querySelector("#surStartDate").value);
	
	/* 오늘날자 얻어오기 */
	const today = getToday();
	
	/* 시작일 얻어오기 */
	const startDay = document.getElementById("startDate");
	const startDayForChk = startDay.value.replace(/\-/g,"");
	
	/* 시작일 날자타입 변환 후 전달 */
	const insertStartDate = new Date(startDay.value);
	$("#surStartDate").val(insertStartDate);
	
	/* 종료일 얻어오기 */
	const endDay = document.getElementById("endDate");
	const endDayForChk = endDay.value.replace(/\-/g,"");
	
	/* 날자 비교해서 시작일이 오늘날자보다 앞이면 초기화 */
	if(startDayForChk < today){
		alert("시작일은 오늘보다 이전 날자가 될 수 없습니다. 다시 설정해주세요.");
		$("#startDate").val(originStartDay);
		return false
	}
	
	/* 종료일의 설정 여부 확인 */
	if(endDay.value !== '') {
		/* 날자 비교해서 시작일이 종료일보다 뒤면 초기화 */
		if(startDayForChk > endDayForChk){
			alert("시작일은 종료일보다 앞이거나 같은 날이어야 합니다.");
			$("#startDate").val(originEndDay);
			return false
		}
	}
	
}



function chkAftToday(){
	
	/* 오늘날자 얻어오기 */
	const today = getToday();
	
	/* 시작일 얻어오기 */
	const startDay = document.getElementById("startDate");
	const startDayForChk = startDay.value.replace(/\-/g,"");
	
	/* 종료일 얻어오기 */
	const endDay = document.getElementById("endDate");
	const endDayForChk = endDay.value.replace(/\-/g,"");
	
	/* 종료일 날자타입 변환 후 전달 */
	const insertEndDate = new Date(endDay.value);
	$("#surEndDate").val(insertEndDate);
	
	
	/* 날자 비교해서 종료일이 시작일보다 앞이면 초기화 */
	if(endDayForChk < startDayForChk){
		alert("종료일은 시작일보다 뒤이거나 같은 날이어야 합니다.");
		$("#endDate").val(startDay.value);
		
		return false
		
	} else {
		/* 날자 비교해서 종료일이 오늘날자보다 앞이면 초기화 */
		if(endDayForChk < today){
			alert("종료일은 오늘보다 이전 날자가 될 수 없습니다. 다시 설정해주세요.");
			$("#endDate").val(startDay.value);
			return false
		} 
	}
}





function addSurq(){
	
	/* 기존의 문제 수 */
	const totalSurqCnt = $("div[name=surq]").length;
	
	/* 총 문제수가 20개로 제한한다 */
	if(totalSurqCnt >= optionCnt){
		alert("총 문제수는 "+optionCnt+"개를 넘길 수 없습니다.");
		return false
	}
	
	/* 기존에 생성된 문제 수에 추가하므로 +1 된 값을 가진다 */
	const surqCnt = totalSurqCnt+1;
	
	/* 화면에 출력하기 위한 div 얻어오기 */
	const surveyQuestionList = document.getElementById("surveyQuestionList");
	
	/* 문제 정보를 담을 div 생성 */
	const surq = document.createElement('div');
	surq.setAttribute("id", "surq"+surqCnt);
	surq.setAttribute("name", "surq");
	surq.setAttribute("class", "surQuestion margin");
	
		/* 문제 번호와 문제 제목을 표시할 div 생성 */
		const surq_head = document.createElement('div');
		
		/* 문제 번호와 문제 제목 input 생성 */
		const surq_no = "문제.";
		const surq_title = document.createElement('input');
		surq_title.setAttribute("type", "text");
		surq_title.setAttribute("id", "surqTitle"+surqCnt);
		surq_title.setAttribute("name", "surqTitle");
		surq_title.setAttribute("class", "input-text");
		surq_title.setAttribute("style", "width: 92.7%; margin-bottom: 5px;");
		surq_title.setAttribute("maxlength", "50");
		
		/* 문제번호와 문제 제목 div에 삽입 */
		surq_head.append(surq_no);
		surq_head.append(surq_title);
		surq_head.append(" *");
		
		/* 생성된 div를 문제 정보 div에 삽입 */
		surq.append(surq_head);
		
	
		/* 문제 선택지 5개와 선택사유 생성 및 삽입 */
			
		const surq_title1 = document.createElement('div');
		const surq_input1 = document.createElement('input');
		surq_title1.innerText = "①.";
		surq_input1.setAttribute("type", "text");
		surq_input1.setAttribute("id", "surqTitle1_"+surqCnt);
		surq_input1.setAttribute("name", "surqTitle1");
		surq_input1.setAttribute("class", "input-text margin");
		surq_input1.setAttribute("style", "width: 95%;");
		surq_input1.setAttribute("maxlength", "50");
		surq_title1.append(surq_input1);
		surq_title1.append("*");
		
		
		const surq_title2 = document.createElement('div');
		const surq_input2 = document.createElement('input');
		surq_title2.innerText = "②.";
		surq_input2.setAttribute("type", "text");
		surq_input2.setAttribute("id", "surqTitle2_"+surqCnt);
		surq_input2.setAttribute("name", "surqTitle2");
		surq_input2.setAttribute("class", "input-text margin");
		surq_input2.setAttribute("style", "width: 95%;");
		surq_input2.setAttribute("maxlength", "50");
		surq_title2.append(surq_input2);
		surq_title2.append("*");
		
		
		const surq_title3 = document.createElement('div');
		const surq_input3 = document.createElement('input');
		surq_title3.innerText = "③.";
		surq_input3.setAttribute("type", "text");
		surq_input3.setAttribute("id", "surqTitle3_"+surqCnt);
		surq_input3.setAttribute("name", "surqTitle3");
		surq_input3.setAttribute("class", "input-text margin");
		surq_input3.setAttribute("style", "width: 95%;");
		surq_input3.setAttribute("maxlength", "50");
		surq_title3.append(surq_input3);
		
	
		const surq_title4 = document.createElement('div');
		const surq_input4 = document.createElement('input');
		surq_title4.innerText = "④.";
		surq_input4.setAttribute("type", "text");
		surq_input4.setAttribute("id", "surqTitle4_"+surqCnt);
		surq_input4.setAttribute("name", "surqTitle4");
		surq_input4.setAttribute("class", "input-text margin");
		surq_input4.setAttribute("style", "width: 95%;");
		surq_input4.setAttribute("maxlength", "50");
		surq_title4.append(surq_input4);
		
		
		const surq_title5 = document.createElement('div');
		const surq_input5 = document.createElement('input');
		surq_title5.innerText = "⑤.";
		surq_input5.setAttribute("type", "text");
		surq_input5.setAttribute("id", "surqTitle5_"+surqCnt);
		surq_input5.setAttribute("name", "surqTitle5");
		surq_input5.setAttribute("class", "input-text margin");
		surq_input5.setAttribute("style", "width: 95%;");
		surq_input5.setAttribute("maxlength", "50");
		surq_title5.append(surq_input5);
		
		const deleteBtnArea = document.createElement('div');
		deleteBtnArea.setAttribute("style", "text-align: right;");
		const deleteBtn = document.createElement('input');
		deleteBtn.setAttribute("type", "button");
		deleteBtn.setAttribute("style", "margin-right: 12px;");
		deleteBtn.setAttribute("onclick", "deleteSurq("+surqCnt+")");
		deleteBtn.setAttribute("value", "문제 삭제");
		deleteBtnArea.append(deleteBtn);
		
		/* 마무리 hr, br 태그 */
		const hr = document.createElement('hr');
		const br = document.createElement('br');
		
	
		surq.append(surq_title1);
		surq.append(surq_title2);
		surq.append(surq_title3);
		surq.append(surq_title4);
		surq.append(surq_title5);
		surq.append(deleteBtnArea);
		surq.append(hr);
		surq.append(br);
		
	
	/* 생성된 모든 태그들을 화면상의 div에 삽입하여 출력하기 */
	surveyQuestionList.append(surq);
	
	/* select 태그의 값 변화시키기 */
	const surCntTag = document.querySelector("#totalSurCnt");
	let totalSurCntTag = Number(document.querySelector("#totalSurCnt").value) + 1;
	surCntTag.value = totalSurCntTag;
	
	/* 총 문제수 증가시키기 */
	const surCnt = document.querySelector("#surCnt");
	let totalSurCnt = Number(document.querySelector("#surCnt").value) + 1;
	surCnt.value = totalSurCnt;
}





function deleteSurq(no){
	const surqForDelete = document.querySelector("#surq"+no);
	surqForDelete.remove();
	
	/* 총 문제수 감소시키기 */
	const surCnt = document.querySelector("#surCnt");
	let totalSurCnt = Number(document.querySelector("#surCnt").value) - 1;
	surCnt.value = totalSurCnt;
}





function submitBtn(){
	
	/* 설문조사 제목 공백만 있지 않은지 체크 */
	const surTitle = document.querySelector('#surTitle');		/* 설문조사 제목 */
	const nospaceSurTitle = surTitle.value.replace(/ /g,"");	/* 공백제거 후 설문조사 제목 */
	
	if(nospaceSurTitle === ""){
		
		alert("설문조사 제목을 입력해주세요.");
		surTitle.focus();
		
		return false;
	}
	
	
	
	/* 시작일 종료일 입력되었는지 체크 */
	const startDate = document.querySelector("#startDate");
	
	if(startDate.value === ""){
		alert("설문조사 시작일을 지정해주세요.");
		startDate.focus();
		
		return false;
	}
	
	/* 시작일 object 타입으로 변경 */
	const finalStartDate = new Date(startDate.value);
	$("#surStartDate").val(finalStartDate);
	
	
	const endDate = document.querySelector("#endDate");
	
	if(endDate.value === ""){
		alert("설문조사 종료일을 지정해주세요.");
		endDate.focus();

		return false;
	}
	
	
	
	/* 총 문제 수 */
	const surqCnt = $("div[name=surq]").length;
	
	/* 생성된 문제가 없을 경우 체크 */
	if(surqCnt == 0){
		
		alert("문제를 생성해주세요.");
		
		return false;
	}
	
	
	
	/* 생성된 문항들의 각각의 제목, 선택문항 1과 2의 내용이 있는지 체크 */
	for(let i=1; i<=surqCnt; i++){
		
		/* 문제 제목 값 공백확인 */
		let surqTitle = document.querySelector("#surqTitle"+i);
		let nospaceSurqTitle = surqTitle.value.replace(/ /g,"");
		
		if(nospaceSurqTitle === ""){
			
			alert("문제 제목을 입력해주세요.");
			surqTitle.focus();
			
			return false;
		}
		
		/* 문항 제목 값 공백 확인 */
		for(let j=1; j<=2; j++){
			
			let surqOption = document.querySelector("#surqTitle"+j+"_"+i);
			let nospaceSurqOption = surqOption.value.replace(/ /g,"");
			
			if(nospaceSurqOption === ""){
				
				alert("필수 문항을 입력해주세요.");
				surqOption.focus();
				
				return false;
			}
			
		}
		
	}
	
	/* 3, 4, 5번 문항에 공백만 있을 경우 공백 전체 제거 */
	for(let k=1; k<=surqCnt; k++){
		
		let surqTitle3 = document.querySelector("#surqTitle3_"+k);
		let nospaceTitle3 = surqTitle3.value.replace(/(\s*)/g,"");
		
		if(nospaceTitle3 === ""){
			surqTitle3.value = "";
		}
		
		
		let surqTitle4 = document.querySelector("#surqTitle4_"+k);
		let nospaceTitle4 = surqTitle4.value.replace(/(\s)/g,"");
		
		if(nospaceTitle4 === ""){
			surqTitle4.value = "";
		}
		
		
		let surqTitle5 = document.querySelector("#surqTitle5_"+k);
		let nospaceTitle5 = surqTitle5.value.replace(/(\s)/g,"");
		
		if(nospaceTitle5 === ""){
			surqTitle5.value = "";
		}
		
	}
	
	
	
	/* form submit */
	const form = document.surveyContent;
	form.submit();
	
}
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />











