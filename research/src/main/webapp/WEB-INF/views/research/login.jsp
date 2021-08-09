<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="/WEB-INF/views/layout/header.jsp" />

<!-- content start -->
<div id="content" style="width: 1000px; margin: 0 auto; min-height: 663px;">

<div class="head" style="font-size: 20pt; font-weight: 500; margin-top: 20px;">로그인</div>
<hr>

<div id="loginBtnBox" style="text-align: center;">

	<input type="button" onclick="goList()" value="리스트로 돌아가기" />
	<hr>
	
	
	<input type="button" onclick="adminLogin(1)" value="조옥현" />
	<input type="button" onclick="adminLogin(2)" value="장동찬" />
	
	<hr>
	
	<input type="button" onclick="userLogin(1)" value="김태호" />
	<input type="button" onclick="userLogin(2)" value="주민" />
	<input type="button" onclick="userLogin(3)" value="김수환" />
	<input type="button" onclick="userLogin(4)" value="박성민" />
	<input type="button" onclick="userLogin(5)" value="채승원" />
	<input type="button" onclick="userLogin(6)" value="이현지" />
	<input type="button" onclick="userLogin(7)" value="변상연" />
	<input type="button" onclick="userLogin(8)" value="이빛나" />
	<input type="button" onclick="userLogin(9)" value="한동민" />
	<input type="button" onclick="userLogin(10)" value="김현민" />

</div>

<script>
function adminLogin(no){
	location.href = "/research/adminLogin?userId=admin"+no;
}

function userLogin(no){
	location.href = "/research/userLogin?userId=user"+no;
}

function goList(){
	location.href = "/research/list";
}
</script>

</div> <!-- content end -->

<c:import url="/WEB-INF/views/layout/footer.jsp" />