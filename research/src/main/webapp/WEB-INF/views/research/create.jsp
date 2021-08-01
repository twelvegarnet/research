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
</style>
<!-- CSS end -->



<!-- Content start -->
<div id="content" style="width: 1000px; margin: 0 auto; min-height: 400px;">

	<div class="head" style="font-size: 20pt; font-weight: 700;">설문조사 등록</div>

	<table style="width: 100%; margin-top: 30px; table-layout: fixed; border-top: 2px solid blue;">
		<tr>
			<td class="center silver" colspan="1">제목</td>
			<td colspan="3"><input type="text" name="surTitle" class="input-text" style="width: 97%;"></td>
		</tr>
		<tr>
			<td class="center silver" colspan="1">시작일</td>
			<td colspan="1"><input type="text" class="input-text" readonly /></td>
			<td class="center silver" colspan="1">종료일</td>
			<td colspan="1"><input type="text" class="input-text" readonly /></td>
		</tr>
		<tr>
			<td class="center silver" colspan="1">문항 생성하기</td>
			<td colspan="3">
				<select id="questionCnt" style="padding: 4px; margin-left: 10px;" name="questionCnt"></select>
				<input type="button" onclick="addSurveyQuestion()" value="+" style="margin-left: 10px;" />
			</td>
		</tr>
		
	</table>
	
	<!-- 추가된 문제 리스트를 담을 div -->
	<div id="surveyQuestionList" style="margin-top: 20px; width: 95%;"></div>


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
	const selectTag = document.getElementById("questionCnt");
	
	for(let i=1; i<=optionCnt; i++){
		
		/* option 태그 생성 */
		let optionTag = document.createElement('option');
		optionTag.setAttribute("value", i);
		optionTag.innerText = i;
		
		/* selectTag에 삽입 */
		selectTag.append(optionTag);
	}
	
}





/* 선택된 값에 따른 문제생성 폼 출력하기 */

/* 버튼을 누를 때마다 문제 추가하기 */
function addSurveyQuestion(){
	
	/* 출력할 문제의 수 얻어오기 */
	const printOptionCnt = document.getElementById("questionCnt").value;
	
	/* 화면에 출력하기 위한 div 얻어오기 */
	const surveyQuestionList = document.getElementById("surveyQuestionList");
	
	/* 기존의 태그들 삭제하기 */
	surveyQuestionList.innerText = '';
	
	/* for문으로 선택된 항목 수만큼 문제 생성 */
	for(let i=1; i<=printOptionCnt; i++){

		/* 문제 정보를 담을 div 생성 */
		const surq = document.createElement('div');
		surq.setAttribute("id", "surq"+i);
		surq.setAttribute("class", "margin");

			/* 문제 번호와 문제 제목을 표시할 div 생성 */
			const surq_head = document.createElement('div');
			
			/* 문제 번호와 문제 제목 input 생성 */
			const surq_no = i+".";
			const surq_title = document.createElement('input');
			surq_title.setAttribute("type", "text");
			surq_title.setAttribute("class", "input-text");
			surq_title.setAttribute("style", "width: 95.2%; margin-bottom: 5px;");
			surq_title.setAttribute("required", "required");
			
			/* 문제번호와 문제 제목 div에 삽입 */
			surq_head.append(surq_no);
			surq_head.append(surq_title);
			
			/* 생성된 div를 문제 정보 div에 삽입 */
			surq.append(surq_head);
			
		
			/* 문제 선택지 5개와 선택사유 생성 및 삽입 */
				
			const surq_title1 = document.createElement('div');
			const surq_input1 = document.createElement('input');
			surq_title1.innerText = "①.";
			surq_input1.setAttribute("type", "text");
			surq_input1.setAttribute("class", "input-text margin");
			surq_input1.setAttribute("name", "surq_title1");
			surq_input1.setAttribute("style", "width: 95%;");
			surq_input1.setAttribute("required", "required;");
			surq_title1.append(surq_input1);
			
			
			const surq_title2 = document.createElement('div');
			const surq_input2 = document.createElement('input');
			surq_title2.innerText = "②.";
			surq_input2.setAttribute("type", "text");
			surq_input2.setAttribute("class", "input-text margin");
			surq_input2.setAttribute("name", "surq_title2");
			surq_input2.setAttribute("style", "width: 95%;");
			surq_title2.append(surq_input2);
			
			
			const surq_title3 = document.createElement('div');
			const surq_input3 = document.createElement('input');
			surq_title3.innerText = "③.";
			surq_input3.setAttribute("type", "text");
			surq_input3.setAttribute("class", "input-text margin");
			surq_input3.setAttribute("name", "surq_title3");
			surq_input3.setAttribute("style", "width: 95%;");
			surq_title3.append(surq_input3);
			
	
			const surq_title4 = document.createElement('div');
			const surq_input4 = document.createElement('input');
			surq_title4.innerText = "④.";
			surq_input4.setAttribute("type", "text");
			surq_input4.setAttribute("class", "input-text margin");
			surq_input4.setAttribute("name", "surq_title4");
			surq_input4.setAttribute("style", "width: 95%;");
			surq_title4.append(surq_input4);
			
			
			const surq_title5 = document.createElement('div');
			const surq_input5 = document.createElement('input');
			surq_title5.innerText = "⑤.";
			surq_input5.setAttribute("type", "text");
			surq_input5.setAttribute("class", "input-text margin");
			surq_input5.setAttribute("name", "surq_title5");
			surq_input5.setAttribute("style", "width: 95%;");
			surq_title5.append(surq_input5);
			
			
			const description = document.createElement('div');
			description.innerText = "선택사유";
			const des_input = document.createElement('input');
			des_input.setAttribute("type", "text");
			des_input.setAttribute("class", "input-text");
			des_input.setAttribute("name", "description");
			des_input.setAttribute("style", "width: 89.7%;");
			description.append(des_input);
		
			surq.append(surq_title1);
			surq.append(surq_title2);
			surq.append(surq_title3);
			surq.append(surq_title4);
			surq.append(surq_title5);
			surq.append(description);
			

			/* 생성된 모든 태그들을 화면상의 div에 삽입하여 출력하기 */
		surveyQuestionList.append(surq);
		
		/* 마무리 hr태그 삽입 */
		const hr = document.createElement('hr');
		const br = document.createElement('br');
		
		surveyQuestionList.append(hr);
		surveyQuestionList.append(br);
		
	}
	
		
}
</script>

<c:import url="/WEB-INF/views/layout/footer.jsp" />











