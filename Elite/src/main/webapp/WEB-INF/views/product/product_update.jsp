<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/regist.css">
<link rel="stylesheet"href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://localhost:9000/daangn/resources/js/product_update.js"></script>
<title>당근마켓 - 우리 동네 중고 직거래</title>
<script>
$(document).ready(function() {
	
	// DB에 있는 값 불러오기 - pcontent
 	var pcontent = $("textarea[name='pcontent']").val();
	pcontent = pcontent.split('<br/>').join("\r\n"); // <br>태그 => 개행문자
	$("textarea[name='pcontent']").html(pcontent); 
	
	// DB에 있는 값 불러오기 - pcate
		$('.category').each(function() {
		if($(this).find('.cat_text').text() == '${vo.pcate}'){
			$(this).children('.cat_link').css('background-color', '#dbdbdb');
			$(this).siblings().children('.cat_link').css('background-color', '#fff');
		}
	});

	// DB에 있는 값 불러오기 - ptype
	$('.item_status_list li').each(function() {
		if($(this).find('.text').text() == '${vo.ptype}'){
			$(this).children('div').addClass(' click');
			$(this).siblings().children('div').removeClass(' click');
			
			if($(this).find('.text').text() != "판매"){
				$(".price_area").css("display","none");
			}
		}
	}); 
	
	//파일 새로 올릴 경우
	$("#newFileUpload").click(function(){
		$("#myModal_1").css("display", "none");
	});
	
	//파일 유지할 경우
	$("#keepFile").click(function(){
		$("#myModal_1").css("display", "none");
		
		if(confirm('물품을 등록하시겠습니까?')){
			$("input[name='pprice']").val(uncomma($("input[name='pprice']").val())); // pprice 콤마 제거
			updateForm('keep'); // 폼 전송
		}else{
			location.reload(); // 맨 처음 화면으로 이동
		}
	});
});
</script>
<style>
div.title_div {
	width: 1050px;
	height: 110px;
	margin-bottom: 50px;
	border-bottom: 1px solid #cccccc;
}

div.title_div h2.title_h2 {
	font-size: 40px;
	color: #4d4d4d;
	letter-spacing: -0.6px;
	padding-top: 35px;
}

div.line_member {
	color: white;
	margin-left: -180px;
	width: 1050px;
	margin-bottom: 50px;
}

div.title_div h2.title_h2 span, div.title_div h2.title_h2 a {
	color: #4d4d4d;
	font-size: 11pt;
	font-weight: normal;
	padding-left: 17px;
}

div.title_div h2.title_h2 a {
	text-decoration: underline;
}

div.title_div h2.title_h2 a:hover {
	font-weight: bold;
}

.state_img {
	display: block;
	margin: auto;
}
/* modal */
.modal_area {
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	padding-top: 100px; /* Location of the box */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

#modal_content_1 {
	text-align: center;
}

.modal-content-sale {
	background-color: white;
	font-family: 'Open Sans', 'Helvetica Neue', Helvetica, Arial, sans-serif;
	width: 478px;
	padding: 17px;
	border-radius: 5px;
	position: fixed;
	left: 50%;
	top: 50%;
	margin-left: -256px;
	margin-top: -100px;
	overflow: hidden;
	z-index: 99999;
}

.modal_btn_area {
	text-align: center;
}

.modal_btn_area button {
	border: 1px solid #404040;
	background-color: #404040;
	color: white;
	width: 120px;
	height: 50px;
	margin: 20px;
	font-size: 13pt;
	border-radius: 5px;
}

.modal_btn_area button.modal_btn:hover {
	background-color: rgb(109, 199, 61);
	border: 1px solid rgb(109, 199, 61);
}

.file_question1, .file_question2 {
	display: inline-block;
	font-size: 15pt;
	font-family: 'AppleSDGothicNeoL00';
}

.file_question1 {
	margin: 50px 0px 10px 0px;
}

.file_question2 {
	margin-bottom: 50px;
}
</style>
</head>
<body>

	<!-- header -->
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>
	<div id="mypage_content">
		<div id="top_div">
			<div id="title_div"></div>
		</div>
		<%-- <c:import url="http://localhost:9000/daangn/box.carrot" /> --%>
		<%-- <jsp:include page="../mypage/mypage_box.jsp"></jsp:include> --%>

		<div class="main">
			<div class="title_div">
				<h2 class="title_h2">물품등록
					<span>당근마켓에 물품을 등록해보세요!</span>
					<a href="http://localhost:9000/daangn/mypage_info_pass_check.carrot">나의당근으로 돌아가기</a>
				</h2>
				<div class="line_member"></div>
			</div>

			<!-- 내용 시작 -->
			<div class="content_frame">
				<div class="content">

					<!-- 수평 메뉴 -->
					<div class="page-tabs" style="height: 50px;">
						<div class="tabs">
							<a href="#" class="tab active" id="cat">카테고리 선택</a> 
							<a href="#" class="tab" id="des">상세정보입력</a>
							<a href="#" class="tab" id="pre">사진올리기</a>
						</div>
					</div>
					 
					<!-- 폼시작 -->
					<form id="product_update" name="product_update" action="product_update_proc.carrot" method="post" enctype="multipart/form-data">
						<!-- 카테고리 선택 -->
						<div class="regist_box" id="category">
							<ul class="cat_list">
								<li class="cat_space"></li>
								<li class="cat_space"></li>
								<li class="cat_space"></li>
								<li class="cat_space"></li>
								<li class="cat_space"></li>

								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hfa0000.png">
										<span class="cat_text">자동차용품</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hba0000.png">
										<span class="cat_text">핸드메이드</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hae0000.png">
										<span class="cat_text">유아동/완구</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_had0000.png">
										<span class="cat_text">뷰티</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_haa0000.png">
										<span class="cat_text">여성의류</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hab0000.png">
										<span class="cat_text">남성의류</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hac0000.png">
										<span class="cat_text">신발/가방/잡화</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hak0000.png">
										<span class="cat_text">휴대폰/태블릿</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hai0000.png">
										<span class="cat_text">컴퓨터/주변기기</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_haj0000.png">
										<span class="cat_text">카메라</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hah0000.png">
										<span class="cat_text">디지털/가전</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_han0000.png">
										<span class="cat_text">게임</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hao0000.png">
										<span class="cat_text">스포츠/레저</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_haf0000.png">
										<span class="cat_text">가구</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hag0000.png">
										<span class="cat_text">생활</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hat0000.png">
										<span class="cat_text">골동품/희귀품</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hga0000.png">
										<span class="cat_text">여행/숙박</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_haq0000.png">
										<span class="cat_text">티켓,상품권</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hda0000.png">
										<span class="cat_text">재능/서비스</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hap0000.png">
										<span class="cat_text">도서</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hax0000.png">
										<span class="cat_text">스타굿즈</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_haw0000.png">
										<span class="cat_text">문구</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hav0000.png">
										<span class="cat_text">피규어/키덜트</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hal0000.png">
										<span class="cat_text">CD/DVD</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_ham0000.png">
										<span class="cat_text">음향기기/악기</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_has0000.png">
										<span class="cat_text">예술/미술</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hea0000.png">
										<span class="cat_text">반려동물</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hau0000.png">
										<span class="cat_text">포장식품</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_hzz0000.png">
										<span class="cat_text">기타</span>
									</a>
								</li>
								<li class="category">
									<a class="cat_link" href="#null">
										<img class="cat_img" src="http://localhost:9000/daangn/resources/images/cate_h0000.png">
										<span class="cat_text">삽니다</span>
									</a>
								</li>
							</ul>

						</div>

						<div class="btn_box" id="cat_btn">
							<button type="button" class="btn_ btn_next">다음</button>
							<a href="http://localhost:9000/daangn/mypage.carrot"></a>
						</div>

						<!-- 상세 정보 입력 -->
						<div class="regist_box" id="description">
							<div class="description">
								<table class="des_table">
									<tr>
										<td class="des_label">
										<label>제목</label></td>
										<td class="des_input">
											<input type=text name="ptitle" label="제목" maxlength="25" class="reg_title" placeholder="제목을 입력해주세요." value="${vo.ptitle}"/>
										</td>
									</tr>

									<tr>
										<td class="des_label"><label>거래종류</label></td>
										<td class="des_input">
											<div class="des_sell_box">
												<ul class="item_status_list">
													<li>
														<div class="text_wrapper click">
															<div class="text">판매</div>
														</div>
													</li>
													<li>
														<div class="text_wrapper">
															<div class="text">무료나눔</div>
														</div>
													</li>
													<li>
														<div class="text_wrapper">
															<div class="text">삽니다</div>
														</div></li>
													<li>
														<div class="text_wrapper">
															<div class="text">가격제안</div>
														</div>
													</li>
												</ul>
											</div>
										</td>
									</tr>

									<tr class="price_area">
										<td class="des_label"><label></label></td>
										<td class="des_input">
											<div class="box_price">
												<input name="pprice" class="sell_type_input"
													maxlength="12" placeholder="판매희망 가격을 입력하세요" type="text" value="${vo.pprice}" />
												<span class="box_prive_text">원</span>
											</div>
										</td>
									</tr>

									<tr>
										<td class="des_label"><label>내용</label></td>
										<td class="des_input">
											<textarea name="pcontent" maxlength="1000" class="reg_intro"
												cols="30" rows="10">${vo.pcontent}</textarea>
										</td>
									</tr>

								</table>
							</div>
						</div>
						
						<div class="btn_box" id="des_btn">
							<button type="button" class="btn_ btn_next">다음</button>
							<button type="button" class="btn_">뒤로</button>
						</div>

						<!-- 사진 올리기 -->
						<div class="regist_box" id="presentation">
						
						<!-- modal -->
							<div class="modal_area" id="myModal_1">
								<div class="modal-content-sale" id="modal_content_1">
									<span class="file_question1">물품의 사진을 </span><br> 
									<span class="file_question2">새로 올리시겠습니까?</span>
									<div class="modal_btn_area">
										<button type="button" class="modal_btn" id="newFileUpload">새로올리기</button>
										<button type="button" class="modal_btn" id="keepFile">유지하기</button>
									</div>
								</div>
							</div>
						
							<div class="img_area">
								<div class="presentation">
									<ul>
										<input accept="image/jpeg, image/png" class="multiple_input" id="upFile" multiple="multiple" name="file1" type="file"/>

								     	<c:forEach begin = "1" end = "8">
					   						<li class="item">
												<div class="up_img_box">
													<img alt="아이템 업로드 이미지" class="up_img item_img"
														src="https://ccimage.hellomarket.com/web/2018/auto/img_car_pic_basic.png">
												</div>
											</li>
					      				</c:forEach>

									</ul>
									<div class="up_img_label">대표이미지</div>
									<div class="up_img_description">
										<span class="up_img_description_title"> * 첫번째 사진은 직접 촬영한 아이템 사진을 등록해주세요.</span> 
										<span> &nbsp; - 판매자의 아이템 보유 유무와 실물 상태 확인을 위해 <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;직접 촬영한 실제 아이템 사진을 첫번째 이미지로 첨부해주시기 바랍니다. </span> 
										<span class="img_hover_margin"> &nbsp; - 아이템 첫번째 이미지에 실제 사진이 첨부되지 않은 경우, 사전안내 없이 수정 또는 삭제될 수 있습니다.</span> <br> <br>
										<span class="img_hover_margin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(삽니다.	무형의 아이템 제외)</span>
									</div>
								</div>
							</div>
						</div>
						<!-- script로 insert -->
						<input type="hidden" name="pcate" value="${vo.pcate}" /> 
						<input type="hidden" name="ptype" value="${vo.ptype}" />
						<input type="hidden" name="pid" value="${vo.pid}" />

						<div class="btn_box" id="pre_btn">
							<button type="button" class="btn_ btn_next">등록</button>
							<button type="button" class="btn_">뒤로</button>
						</div>

					</form>
					<!-- 폼 끝 -->
				</div>
			</div>
		</div>
	</div>

	<div class="footer"></div>
	<!-- footer -->
	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>
</body>
</html>