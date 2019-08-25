<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="afile" value="${fn:split(vo.afile,',')}"/>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>

<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/admin.css">
<script	src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		$(".btn_asner_return1").click(function(){
			if($("#answer_return_text").val()==""){
				alert("답변을 입력해주세요.");
				$("#answer_return_text").focus();
				return false;
			}
			
			var result = confirm("답변을 등록하시겠습니까?");
			if(result) answerReturnForm.submit();
		});
		
	});
</script>
</head>
<body>
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>
	<div class="admin">
		<div class="admin_h2">
			<h2 id="admin_span_member">관리자 페이지</h2>
		</div>
			<form name="answerReturnForm" action="admin_answer_return_proc.carrot" method="post">
				<table class="admin_answer_return_table">
					<tr class="admin_answer_return_tr">
						<th>이름</th>
						<td>${ vo.name }</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>아이디</th>
						<td>${ vo.name }</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>연락처</th>
						<td>${ vo.phone }</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>유형</th>
						<td>${ vo.atype }</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>제목</th>
						<td>${ vo.atitle }</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>내용</th>
						<td>
							<p>${ vo.acontent }<p>
							<c:if test="${ vo.afile != null && vo.afile != '' }">
								<c:forEach var="file" items="${ afile }">
									<img src="http://localhost:9000/daangn/resources/upload/${ file }" style="max-width:500px; max-height:300px;"><br/>
								</c:forEach>
							</c:if>
						</td>
					</tr>
					<tr class="admin_answer_return_tr">
						<th>답변</th>
						<td>
							<textarea cols="74" rows="10" id="answer_return_text" name="reply">${ vo.reply }</textarea>
							<input type="hidden" name="aid" value="${ vo.aid }">
						</td>
					</tr>
				</table>
				<div class="answer_return_div">
					<c:choose>
						<c:when test="${ vo.reply != null && vo.reply != '' }">
							<button	type="button" class="btn_asner_return1 update">수정하기</button>
						</c:when>
						<c:otherwise>
							<button	type="button" class="btn_asner_return1">등록하기</button>
						</c:otherwise>
					</c:choose>
					<a href="http://localhost:9000/daangn/admin_answer.carrot?rpage=${ rpage }">
						<button	type="button" class="btn_asner_return2">이전페이지</button>
					</a>
				</div>
			</form>
	</div>

	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>
</body>
</html>