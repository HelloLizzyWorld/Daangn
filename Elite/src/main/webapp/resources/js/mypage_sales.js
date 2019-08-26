var buyer_id; // 구매자 id
var buyer_name; // 구매자 이름
var pid; // 상품 PK
var rstate; // 거래 후기 상태
var rcomment; // 거래 후기 선택
var rdetail; // 상세한 거래 후기
var rdetailOne; // 첫번째로 선택한 rcomment

$(document).ready(function() {
	// 판매중이 아닌 물품에 대하여 버튼 비활성화
	$(".pstate").each(function() {
		var pstate = $(this).val();
		var state_id = $(this).attr("id").substr(7, 5); // id = "pstate_${vo.pid}"
	
		if (pstate != "판매중") {
			$('#' + state_id + ' a').css('pointer-events', 'none').css('background-color', '#e7eaec').css('color', '#a9b4bc');
			/*
			 * if((pstate == "판매완료")){
			 * $(this).attr("disabled","true"); }
			 */
		}
	});
	
	// 버튼 기능 구현
	$(".func_group_item a").click(function() {
			var pid = $(this).attr("id").substr(5,5); // id = "btn1_${vo.pid}"
	
			// 물품 날짜 변경 (끌어올리기)
			if ($(this).text() == 'UP') {
				$.ajax({
					url : "product_update_date.carrot",
					type : "get",
					data : "pid=" + pid,
					dataType : "text",
					success : function(data) {
						if (data == "1") {
							location.reload();
						}
					}
				});
			}
			// 물품 수정
			else if ($(this).text() == "수정") {
				location.href = "http://localhost:9000/daangn/product_update.carrot?pid="+ pid;
			}
			// 물품 삭제
			else if ($(this).text() == "삭제") {
				var result = confirm("삭제를 진행하시겠습니까?");
	
				if (result) {
					location.href = "http://localhost:9000/daangn/product_delete.carrot?pid="+ pid;
					alert("삭제가 완료되었습니다.");
					//ajax로 구현하고 나서 result 값이 있을 때 완료 창 띄울 것
				}
			}
		});
	
	/* MODAL */
	// 판매 완료 후 구매자 선택
	$(document).on("click",".buyer",function(){
		buyer_id = $(this).attr("id").substr(6); // id="buyer_chat_data[i].id"(전역)
		buyer_name = $(this).find('.buyer_span1').text(); // chat_data[i].name(전역)
		$(this).css("background-color","#ffd7b3");
		$(this).siblings().css("background-color","white"); // 선택한 구매자 이외
	});
	
	// 거래 후기 상태 선택시 CSS
	$(".review_choice_img").click(function(){
		$("input:checkbox[name=rcomment]:checked").prop("checked", false); // 체크박스 초기화
		rstate = $(this).attr("id"); // bad, good, best(전역)
		
		if(rstate == "best"){
			best();
		}
		else if(rstate == "good"){
			good();
		}
		else if(rstate == "bad"){
			bad();
		}
	});

	// 거래 후기 선택시 CSS
	$(".best_choice div").click(function(){
		$(this).css("border", "1px solid #f76707").css("color","#f76707");
		$(this).siblings().css("border", "1px solid #8d9ba5").css("color","#8d9ba5");
		//var rcommentList = [];
		//alert(com);
		//rcommentList.push(com);
		//console.log(rcommentList);
	});
	
	// 상품 상태 변화시 (판매중, 예약중, 판매완료)
	$(document).on("change",".pstate",function(){
		pid = $(this).attr("id").substr(7, 5); // id="pstate_${vo.pid}"(전역)
		var pstate = $(this).val();
		pstate_modal(pid, pstate);
	});
});// document ready end

/* function */
// modal on
function pstate_modal(pid, pstate) {
	//판매완료 모달
	if(pstate == "판매완료"){
		product_data(pid);
		chat_list(pid);
	// 판매중, 예약중 모달
	}else{
		$("#myModal_1").css("display", "block");
		pstate_change(pid, pstate);
	}
}
// 판매 완료할 상품 정보 가져오기 - modal창에 append
function product_data(pid){
	var html = "";
	
	$.ajax({
		url : "sale_product_data.carrot",
		type : "get",
		data : "pid=" + pid,
		dataType : "text",
		success : function(data) {
			var product_data = JSON.parse(data);
			if(typeof product_data.fileArray == "undefined"){ // typeof는 변수의 데이터 타입을 반환하는 연산자(file이 없는경우)
				html = "<img src='https://seoul-p-studio.bunjang.net/product/101832538_1_1557381938_w152.jpg'>"
						+"<div class='sale-name'>"
						+"<span class='span1'> 거래한 상품 </span><br>"
						+"<span class='span2'>" + product_data.ptitle + "</span>"
						+"</div>";
			}else{
				html = "<img src='http://localhost:9000/daangn/resources/upload/" + product_data.fileArray[0].pfile + "'>"
						+"<div class='sale-name'>"
						+"<span class='span1'> 거래한 상품 </span><br>"
						+"<span class='span2'>" + product_data.ptitle + "</span>"
						+"</div>";
			}
			$(".sale-content").append(html);
		}
	});
}
//판매 완료할 상품과 채팅한 사람들의 목록 가져오기
function chat_list(pid){
	var html = "";
	
	$.ajax({
		url : "chat_list.carrot",
		type : "get",
		data : "pid=" + pid,
		dataType : "text",
		success : function(data) {
			var chat_data = JSON.parse(data);
			if(chat_data == ""){
				alert("채팅한 사람이 없습니다.");
			}else{
				for(var i in chat_data){
					if(chat_data[i].mfile == null){
						html = "<div class='buyer' id='buyer_" + chat_data[i].id + "'>"
								+ "<img class='profile_img' src='http://localhost:9000/daangn/resources/images/rabbit.svg'>"
								+ "<span class='buyer_span1'>" + chat_data[i].nick + "</span>"
								+ "<span class='buyer_span2'>" + chat_data[i].addr_bname + "</span>"
								+ "</div>";
					}else{
						html = "<div class='buyer' id='buyer_" + chat_data[i].id + "'>"
								+ "<img class='profile_img' src='http://localhost:9000/daangn/resources/upload/" + chat_data[i].mfile + "'>"
								+ "<span class='buyer_span1'>" + chat_data[i].nick + "</span>"
								+ "<span class='buyer_span2'>" + chat_data[i].addr_bname + "</span>"
								+ "</div>";
					}
					$(".buyer_list_append").append(html);
					$("#myModal_2").css("display", "block");
				}
			}
		}
	});
}

// 판매 상태 변경
function pstate_change(pid, pstate){
	$.ajax({
		url : "product_state_change.carrot",
		type : "get",
		data : "pid=" + pid + "&pstate=" + pstate,
		dataType : "text",
		success : function(data) {
			if(data != 1){
				window.location.href = 'http://localhost:9000/daangn/error.carrot';
			}
		}
	});
}

// 거래 후기 선택 (구매자를 선택해야 넘어가는 유효성 체크)
function sales_complete(){
	if(buyer_id == null){
		alert("구매자를 선택해주세요");
	}else{
		$(".buyer_review_choice_append").append("<span class='review_choice_span'>" + buyer_name + "님과 거래가 어떠셨나요?</span>");
		$(".buyer_thank_you_append").append("<span class='thank_you_span1'>" + buyer_name + "님에게 감사 인사를 남겨보세요.</span>");
		$("#myModal_2").css("display", "none");
		$("#myModal_3").css("display", "block");
	}
}
//선택 후 마지막 화면(리뷰 테이블로 폼 전송 및 상태 변경)
function finish(){
	var chkArray = new Array();

	$("input:checkbox[name=rcomment]:checked").each(function() {
		chkArray.push(this.value); // 거래 후기 선택한 값을 배열에 넣음
	});
	rdetailOne = chkArray[0]; // 전역변수
	
	rcomment = chkArray; // 전역변수
	rdetail = $("textarea[name='rdetail']:visible").val(); // 전역변수

	
	if(rcomment == ""){
		alert("구매자 평가를 선택해 주세요:)"); // 유효성 체크
	}else{
		
		$("input[name='id']").val(buyer_id);		
		$("input[name='rstate']").val(rstate);		
		$("input[name='rcomment']").val(rcomment);		
		$("input[name='rdetail']").val(rdetail);		
		$("input[name='pid']").val(pid);
		
		// serialize메소드를 이용해 폼의 객체를 받음
        var sales_review_form = $("form[name='sales_review']").serialize() ;
        
        $.ajax({
            type : 'post',
            url : 'sales_review.carrot',
            data : sales_review_form,
            success : function(data){
                if(data == true){
                	pstate_change(pid, "판매완료");
                	
                	last_modal(rstate);
                	
                	$("#myModal_3").css("display", "none");
                	$("#myModal_4").css("display", "block");
                }
            }
        });
		//$("#myModal_3").css("display", "none");
		//$("#myModal_4").css("display", "block");
	}
}
// modal off
function closeModal(state) {
	// 판매중, 예약중 모달
	if(state == "1"){
		$("#myModal_1").css("display", "none");
		location.reload();
	}
	//판매완료 모달
	else if(state == "2"){
		$("#myModal_4").css("display", "none");
		location.reload();
	}
	// location.reload();
}
//모달 창 윈도우 화면 클릭시 종료
window.onclick = function(event) {
	// 판매중, 예약중일 때에만 진행 할 것
	if (event.target == document.getElementById("myModal_1")) {
		document.getElementById("myModal_1").style.display = "none";
		location.reload();
	}else if(event.target == document.getElementById("myModal_4")){
		document.getElementById("myModal_4").style.display = "none";
		location.reload();
	}
}
//review img choice
function best() {
	$("#best").attr("src","http://localhost:9000/daangn/resources/images/best_after.jpg");
	$("#good").attr("src","http://localhost:9000/daangn/resources/images/good_before.jpg");
	$("#bad").attr("src","http://localhost:9000/daangn/resources/images/bad_before.jpg");
	$(".buyer_best_choice").css("display", "block");
	$(".buyer_bad_choice").css("display", "none");
	
}
function good() {
	$("#best").attr("src","http://localhost:9000/daangn/resources/images/best_before.jpg");
	$("#good").attr("src","http://localhost:9000/daangn/resources/images/good_after.jpg");
	$("#bad").attr("src","http://localhost:9000/daangn/resources/images/bad_before.jpg");
	$(".buyer_best_choice").css("display", "block");
	$(".buyer_bad_choice").css("display", "none");
}
function bad() {
	$("#best").attr("src","http://localhost:9000/daangn/resources/images/best_before.jpg");
	$("#good").attr("src","http://localhost:9000/daangn/resources/images/good_before.jpg");
	$("#bad").attr("src","http://localhost:9000/daangn/resources/images/bad_after.jpg");
	$(".buyer_bad_choice").css("display", "block");
	$(".buyer_best_choice").css("display", "none");
}
// 마지막 모달 창 append
function last_modal(rstate){
	var html;
	if(rstate == "best"){
		html = "<span class='result_buyer_name'>TO. " + buyer_name + "</span>"
		+ "<img class='result_choice_img' src='http://localhost:9000/daangn/resources/images/best_after.jpg'>"
		+ "<span class='result_span'>" + rdetailOne + "</span>";
		
	}else if(rstate == "good"){
		html = "<span class='result_buyer_name'>TO. " + buyer_name + "</span>"
		+ "<img class='result_choice_img' src='http://localhost:9000/daangn/resources/images/good_after.jpg'>"
		+ "<span class='result_span'>" + rdetailOne + "</span>";
		
	}else if(rstate == "bad"){
		html = "<span class='result_buyer_name'>TO. " + buyer_name + "</span>"
		+ "<img class='result_choice_img' src='http://localhost:9000/daangn/resources/images/bad_after.jpg'>"
		+ "<span class='result_span'>" + rdetailOne + "</span>";
	}
	$(".review_result").append(html);
}

/* 	
 * select 값 set 하는 jquery - list를 return하여 사용 불가능
 *$(".pstate option").each(function() {
	 	//alert('${vo.pstate}');
		if ($(this).val() == '${vo.pstate}') {
			$(this).prop("selected", "selected"); // attr적용안될경우 prop으로 
		}
}); */