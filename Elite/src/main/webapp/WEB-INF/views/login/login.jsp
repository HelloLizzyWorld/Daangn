<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/login.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/join.css">
<script src="http://localhost:9000/daangn/resources/js/login.js"></script>
<script src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<script>

	var lock = '${lock}';
	var warn_msg ='${warn_msg}';
	
	$(document).ready(function() {

		if(lock == 1){
			alert("로그인에 지속 실패하여, 10초동안 로그인이  불가능합니다.");
		}else if(warn_msg =="black"){
			alert("블랙리스트다!!!!!!!!!!!!!!!!!!!");
		}
	});

/*$("#bth_login").prop("disabled", true);*/
</script>
</head>
<body>

	<!-- header -->
	<div class="header"></div>

	<div class="page_aticle">
		
		<div class="head_join">
			<h2 class="tit">로그인</h2>
			<div class="join_line"></div>
		</div>
		
		<div id="ViewTimer"></div>
		
		<div class="login_p"></div>
		<div class="member_join">
			<div class="boardWrite" id="login_height">
				<form name="loginForm" action="login_proc.carrot" method="post">
					<table class="tbl_comm" id="login_f">
						<tr>

							<td class="memberCols2"><input type="text" name="id"
								class="input_css" id="id" maxlength="16" placeholder="아이디" value="">
						</tr>
						<tr>

							<td class="memberCols2"><input type="password" name="pass"
								class="input_css" id="pass" maxlength="16" placeholder="비밀번호" value="1234">
								<p class="txt_guide">
									<span class="txt txt_case1">10자 이상 입력</span> 
									<span class="txt txt_case2">영문/숫자/특수문자(공백 제외)만 허용하며, 2개 이상 조합</span>
									<span class="txt txt_case3">동일한 숫자 3개 이상 연속 사용 불가</span>
								</p>
							</td>
						</tr>
					</table>
				</form>
				<div>
					<div class="id_pass_join_menu">
						<a href="http://localhost:9000/daangn/login_id_pass.carrot"
								class="id_pass_menu">아이디 · 비밀번호 찾기</a>
						<a href="http://localhost:9000/daangn/join.carrot"
								class="join_menu">회원가입</a>
					</div>
					<c:if test="${msg == 'fail'}">
						<div class="fail_msg">
						아이디 또는 비밀번호를 다시 확인하세요.<br>
						당근마켓에 등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.
						</div>
					</c:if>
				</div>
				<div class="btn_join_div">
					<button type="button" class="btn_join1" id="bth_login" style="width: 420px;">로그인</button>
					<br>
				</div>
			</div>
		</div>
	</div>
	

	<!-- footer -->
	<div class="footer"></div>
</body>
</html>
