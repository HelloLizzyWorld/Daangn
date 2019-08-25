<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/mypage.css">
<script
	src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<title>마이페이지 판매 테스트</title>
<script>
	function openForm() {
		document.getElementById("myModal").style.display = "block";
	}

	function closeForm() {
		document.getElementById("myModal").style.display = "none";
	}
</script>
<script>
	$(document).ready(
			function() {
				$("#myModal").hide();
				$('#item_status').on('change', function() {
					alert($('#item_status').val());
				});

				/* 거래 후기 남기기 - pid, ptitle */
				$(".review_btn").click(function() {
					var pid = $(this).attr("id").substr(4, 4);
					$.ajax({
						url : "mypage_review.carrot",
						type : "post",
						data : "pid=" + pid,
						dataType : "json",
						success : function(data) {
							if (data != null) {
								$(".ptitle").text(data.ptitle);
								$(".id_to").text("To."+data.id);
								$("#src_name2").attr("src","http://localhost:9000/daangn/resources/upload/"+data.pfile);
							}
						}
					});
				});
				
				//내가남긴거래후기보기
				$(".review_btn").click(function() {
					var pid = $(this).attr("id").substr(4, 4);
					$.ajax({
						url : "my_review.carrot",
						type : "post",
						data : "pid=" + pid,
						dataType : "json",
						success : function(data) {
 							if (data != null) {
 								$("#src_name").attr("src","http://localhost:9000/daangn/resources/images/"+data.rstate);
								$(".rdetail").text(data.rdetail);
								$(".rcomment").html(data.rcomment);
								
								
								
								
							} 
						}
					});
				});

				
				
				$("#search-cnt").change(function () {
					var op = $(this).val();
					window.location.href = op;
				});
				
				
			});
</script>
<style>
.sbtb {
	width: 350px;
	height: 30px;
	text-align: center;
	margin-bottom: 5px;
	font-size: 15px;
	border: 1px solid #c6cdd2;
	background: white;
	color: #7e8f9a;
}

.review_btn {
	color: #f76707;
	font-weight: bold;
}

label {
	border: solid 1px #7e8f9a;
	color: #7e8f9a;
	border-radius: 3px;
	padding: 3px 0px 3px 0px;
}

input:checked+label {
	border: 1px solid #f76707;
}

#b, #c, #e, #g, #i, #k, #a, #d, #f, #h, #j, #l {
	visibility: hidden;
}

.characters {
	display: grid;
}
</style>
</head>
<body>

	<div id="mypage_content">
		<div id="top_div">
			<div id="title_div"></div>
		</div>

		<!-- 마이페이지 상단 박스 -->
		<jsp:include page="mypage_box.jsp"></jsp:include>

		<div class="mypage_menu">
			<div class="page-tabs">
				<!-- 수평 메뉴 -->
				<div class="tabs">
					<a
						href="http://localhost:9000/daangn/mypage_info_pass_check.carrot"
						class="tab">개인정보</a> <a
						href="http://localhost:9000/daangn/mypage_cart.carrot" class="tab">관심목록</a>
					<a href="http://localhost:9000/daangn/mypage_sales.carrot"
						class="tab support">판매내역</a> <a
						href="http://localhost:9000/daangn/mypage_purchase.carrot"
						class="tab active">구매내역</a>
				</div>

				<div class="mypage_sales">
					<div class="search_box">
						<select id="search-cnt" class="form-control select_width"
							name="account" style="width: 150px;">
							<option value="http://localhost:9000/daangn/mypage_purchase.carrot">구매후기 대기중</option>
							<option value="http://localhost:9000/daangn/review_finish.carrot" selected>구매후기 완료</option>
						</select>
					</div>

					<div class="wrapper-content">
						<div class="div_area"></div>

						<div class="div_area">
							<div class="ibox">
								<div class="ibox-content">
									<table class="table">
										<!-- <thead>
								<tr>
									<th class="text-center">사진</th>
									<th class="text-center">물품명</th>
									<th class="text-center">가격</th>
									<th class="text-center">찜/댓글</th>
									<th class="text-center">구입일</th>
									<th class="text-center"></th>
								</tr>
							</thead> -->
										<tbody>
										<c:choose>
										<c:when test="${not empty list}">
										<c:forEach var="vo" items="${list}">
												<tr>
													<td class="text-center"><a
														href="/sale/product/edit?pid=101832538"> <img
															src="http://localhost:9000/daangn/resources/upload/${vo.pfile}"
															width="152" height="152" />
													</a></td>

													<td class="text-center title"
														style="max-width: 400px; font-weight: bold;"><a
														style="max-width: 400px"
														href="/sale/product/edit?pid=101832538" class="title_link">${vo.ptitle}</a>
													</td>
													<td class="text-center">
													<fmt:formatNumber value="${vo.pprice}" pattern="###,###,###"/>
													
													</td>
													<!-- <td class="text-center"><img
														src="http://localhost:9000/daangn/resources/images/하트.png"
														style="width: 30px; height: 30px;">0<img
														src="http://localhost:9000/daangn/resources/images/댓글.png"
														style="width: 30px; height: 30px;">0</td> -->
													<td class="text-center">${vo.bdate}</td>
													<td class="text-center">
														<ul class='func_group'>
															<li class='func_group_item'><button type="button"
																	class="review_btn" id="btn_${vo.pid}"
																	onclick="openForm()">작성한 후기 보기</button> <input
																type="hidden" id="pid_rr" value="${vo.pid}"></li>


														</ul>
													</td>
												</tr>
											</c:forEach>
											</c:when>
											<c:otherwise>
												<tr style="font-weight: bold; text-align: center;">
											<td style="padding-top: 100px;">
											구매 내역이 없습니다.
											</td>
											</tr>
											</c:otherwise>
											</c:choose>
										</tbody>
									</table>
									<!-- * Review Modal * -->
									<div class="modal-content" id="myModal"
										style="border:1px solid gray; margin-top: -400px;">
										<div class="modal-header" style="padding-bottom: 10px;">
											<button type="button" class="close" data-dismiss="modal"
												onclick="closeForm()" style="margin-right: 500px;">&times;</button>
											<h2 class="modal-title">내가 남긴 거래 후기</h2>
										</div>
										<div class="modal-body">
											<div
												style="text-align: left; background: #f2f2f2; height: 70px; border: 1px solid #d9d9d9;">
												<ul
													style="display: inline-block; margin-left: 30px; padding-top: 10px;">
													<li><img
														id="src_name2" src=""
														style="width: 50px; height: 50px;"></li>
												</ul>
												<div style="display: inline-block;">
													<ul
														style="text-align: left; display: inline-block; margin-left: 10px;">
														<li
															style="font-size: 13px; color: gray; position: relative; top: -10px;">거래한
															상품</li>
														<li style="top: -7px; position: relative;" class="ptitle"></li>
													</ul>
												</div>
											</div>
											
												<ul style="color: gray; font-weight: bold; margin-bottom: 15px;">
													<li style="color:black; font-size: 20px; margin-top: 20px;" class="id_to"></li>
												</ul>
												
												<ul>
													<li><img style="width: 100px; height: 100px;" id="src_name" src="http://localhost:9000/daangn/resources/images/bestcolor.png"></li>
												</ul>
												<ul style="color:#f76707; font-size: 17px; font-weight: bold; margin-bottom: 20px;">
													<li>별로예요!</li>
												</ul>
												<ul style="font-size: 17px; font-weight: bold;">
													
													<li class="rcomment">
														
													</li>
												</ul>
												<ul style="border-top:1px solid gray; padding-top: 25px; margin-top: 25px;" >
													<li class="rdetail"></li>
												</ul>	
													
													
												</div>
												<!-- /best,good -->

											
											
												<input type="hidden" id="test" value="">
											
										</div>

									</div>

									<div class="modal-footer"></div>



								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- page tab div -->
	</div>
	<!-- end of mypage_menu -->
	</div>
	<!-- end of mypage_content -->
	
	<div style="height:500px;">&nbsp;</div>
	
</body>
</html>