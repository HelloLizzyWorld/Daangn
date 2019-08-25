<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/mypage.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/join.css">
<script
	src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<script>
/* 	$(document).ready(
			function() {
				$('.mypage_box').each(function() {
					if ($(this).index() == 1) {
						$(this).show();
					} else {
						$(this).hide();
					}
				});

				$('.tabs a').click(
						function() {

							var sqn = $(this).index() + 1;

							if ($(this).index() == 0) {
								$(this).addClass(' active').siblings()
										.removeClass(' active');
								$(this).siblings().removeClass(' support');
							} else {
								$(this).addClass(' active').siblings()
										.removeClass(' active');
								$(this).prev().addClass(' support').siblings()
										.removeClass(' support');
							}

							$('.mypage_box').each(function() {
								if (sqn == $(this).index()) {
									$(this).show();
								} else {
									$(this).hide();

								}
							})
						})
			}); */
</script>
</head>
<body>
	<!-- header -->
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>

	<div id="mypage_content" style="height: 1050px;">
		<!-- 메인로고 -->
		<div id="top_div">
			<div id="title_div"></div>
		</div>
		<!-- 마이페이지 상단 박스 -->
		<%-- <c:import url="http://localhost:9000/daangn/box.carrot" /> --%>
		<jsp:include page="mypage_box.jsp"></jsp:include>


		<div class="mypage_menu">
			<div class="page-tabs">
				<!-- 수평 메뉴 -->
				<div class="tabs">
					<a href="#" class="tab active">개인정보</a> 
					<a href="#" class="tab">관심목록</a>
					<a href="#" class="tab">판매내역</a> 
					<a href="#" class="tab">구매내역</a>
				</div>
				
				<!-- 개인정보 -->
			 	<div class="mypage_box">
					<%-- <c:import url="http://localhost:9000/daangn/mypage_info.carrot" /> --%>
					<jsp:include page="mypage_info.jsp"></jsp:include>
				</div> 
					
				<!-- 관심목록 -->
				<div class="mypage_box">
					<%-- <c:import url="http://localhost:9000/daangn/mypage_cart.carrot" /> --%>
					<jsp:include page="mypage_cart.jsp"></jsp:include>
				</div> 
				
				<!-- 판매내역 -->
				<div class="mypage_box">
					<%-- <c:import url="http://localhost:9000/daangn/sales.carrot" /> --%>
					<jsp:include page="mypage_sales.jsp"></jsp:include>
				</div>
				
				<!-- 구매내역 -->
				<div class="mypage_box">
					<%-- <c:import url="http://localhost:9000/daangn/mypage_purchase.carrot"/> --%>
					<jsp:include page="mypage_purchase.jsp"></jsp:include>
				</div>

			</div>
			<!-- page tab div -->

		</div>
		<!-- end of mypage_menu -->

	</div>
	<!-- end of mypage_content -->

	<!-- footer -->
	<div style="height: 100px;"></div>
	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>


</body>
</html>