<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/admin.css">

<script
	src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>

</head>
<body>
	<!-- header -->
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>
	<div class="admin">
	<div class="admin_h2">
		<h2 id="admin_span_main">관리자 페이지</h2>
	</div>
	<br>

	<div class="admin_div">
		<a href="http://localhost:9000/daangn/admin_member.carrot">
			<button	type="button" id="admin_btn1">회원관리</button>
		</a> 
		<a href="http://localhost:9000/daangn/admin_notice.carrot">
			<button type="button" id="admin_btn2">공지사항</button>
		</a>
		<a href="http://localhost:9000/daangn/admin_answer.carrot">
			<button type="button" id="admin_btn3">상담내역</button>
		</a>
	</div>

	</div>
	<div class="footer"></div>
	<!-- footer -->
	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>
</body>
</html>