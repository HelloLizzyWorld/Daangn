<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/index.css">
	<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/notice.css">
	<script src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
	<script src="http://localhost:9000/daangn/resources/js/notice.js"></script>
<title>Insert title here</title>
<script>
/* 유효성 검사 */
$(document).ready(function(){
	$('#btn_question').click(function(){
		if($('#nkategorie').val()=="0"){
			alert("유형을 선택해주세요.")
			$('#nkategorie').focus();
			return false;
		}else if($('#ntitle').val()==""){
			alert("제목을 입력해주세요.")
			$('#ntitle').focus();
			return false;
		}else if($('#ncontent').val()==""){
			alert("내용을 입력해주세요.")
			$('#ncontent').focus();
			return false;
		}
		$("#nid").removeAttr("disabled");
		$("#nname").removeAttr("disabled");
		noticeQuestionForm.submit();
	});
	
	$(".btn_notice_answer").click(function(){
		/* $("#answer_content_img").attr("src","");*/
		var aid = $(this).attr("id").substr(17,4);
		/* alert("이거 눌림ㅋ = "+aid); */
		$.ajax({
			url: "notice_check_answer.carrot",
			type: "get",
			data: "aid="+aid,
			dataType: "json",
			success:function(data){
				/* var jsonObj = JSON.parse(data); */

				$("#answer_type").text(data.atype);
				$("#answer_title").text(data.atitle);
				$("#answer_content").text(data.acontent);
				if(data.afile != null){
					$("#answer_content_img").attr("src","http://localhost:9000/daangn/resources/upload/"+data.afile).css("display","block");
				}else{
					$("#answer_content_img").css("display","none");
				}
				$("#answer_check").text(data.reply);
			}
		});
	});
	$("#btn_back_answer").click(function(){
		$("#answer_content_img").css("display","none");
	});
});

</script>
<!-- <style>
	button#btn_all_question{
		width : 110px;
		height : 40px;
		border : 1px solid #404040;
		background-color : #404040;
		color : white;
		font-size : 10pt;
		font-weight : 600;
		margin : 0px 20px 20px 0px;
		border-radius : 3px;
	}
</style> -->
</head>
<body>
<!-- header -->
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>

	<div id="notice_question_div">
		<!-- 상단 제목 -->
		<h2 class="notice_question_title">고객센터</h2>
		<!-- 내용 시작 -->
		<div class="notice_question_menu">
			<!-- 수평 메뉴 -->
			<div class="question_page">
				<div class="clicks">
					<a href="#" class="click active">자주 묻는 질문</a> 
					<a href="#" class="click">1:1 문의</a>
					<a href="#" class="click">문의 내역</a>
				</div>
			</div>
			<!-- 내용 -->	
			<div class="question_contents"> 
				<div class="contents">
					<jsp:include page="notice_question_part1.jsp"></jsp:include>
				</div>
					
				<div class="contents">
					<jsp:include page="notice_question_part2.jsp"></jsp:include>
				</div>
					
				<div class="contents" id="notice_answer_div">
					<jsp:include page="notice_question_part3.jsp"></jsp:include>
				</div>
			</div>	
		</div>		
	</div>
		
<!-- footer -->
	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>
</body>
</html>
