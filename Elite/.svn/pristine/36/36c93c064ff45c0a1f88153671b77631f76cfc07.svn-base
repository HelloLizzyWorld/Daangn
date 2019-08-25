<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/board.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/board_content.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/chat.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style>
.like_img{
width:40px;
height:30px;
}
ul#chat_ul{
	position: relative;
    left: 690px;
    bottom: 39px;
    width: 200px;
    max-width: 400px;
    display: none;
    border: 1px solid black;
    border-radius: 3px;
   }
ul#chat_ul li.chat_li{
	list-style: disc;
    font-size: 13pt;
    padding: 8px 5px 8px 5px;
    margin-left: 30px;
}
li.chat_li:hover{
	text-decoration : underline;
	cursor : pointer;
}
</style>
<script>
var buyer_id;
 $(document).ready(function(){
	 $("#btn_chatting").click(function(){
		 var log_id = '${svo.id}';
		 var seller_id = '${vo.id}';
		 var pid = '${vo.pid}';
		
		 if(log_id == seller_id){
			 $.ajax({
				 url: 'chat_member_list.carrot',
				 data: 'pid='+pid,
				 dataType: 'json',
				 type: 'post',
				 success:function(data){
					if(data.length != 0){
						if(log_id == seller_id){
							$("#chat_ul").toggle();
							for(var i=0; i<data.length; i++){
								if(data[i].id != seller_id){
								 	$("#chat_ul").append("<li id='"+data[i].id+"' class='chat_li' onclick='openForm()'>"+data[i].id+"</li>");
								}
								 $("#btn_chatting").click(function(){
									 $(".chat_li").remove();
								 });
								 
							 }
							$(".chat_li").click(function(){
								active = true;
								buyer_id = $(this).attr("id");
								onshow2();		
								
						    });
						 }
					 }else{
						 alert("채팅방 참가자가 없습니다.")
					 }
				 }
			 })
		 }else if(log_id != seller_id){
			 active = true;
			 onshow();
		 }
		 
			 
	 });
	 
	 
	 $("#btn_out_chat").click(function(){
		 $("#sendText").val("");
		 $(".chat-column").remove();
         onhide();
		 
	 });
	 
	 $('#sendText').keypress(function(e){
		 $("#sendText").focus();
	   	 if(e.keyCode == 13){
	   		 var message = $("#sendText").val();
	   		 var pid = '${vo.pid}';
	   		 var log_id = '${svo.id}';
	   		 var seller_id = '${vo.id}';
	   		 var my_chat = "<div class='chat-column company'><div class='item'><div class='chat-icon'>"+log_id+"</div></div><div class='item'>";
			 var my_chat_msg = "<div class='chat-message'>";
			 var my_chat_date = "</div><div class='message-date'>";
			 
			 var Now = new Date();
			 var NowTime = Now.getFullYear();
			 var month = Now.getMonth()+1;
			 var date = Now.getDate();
			 if(month <10){
				 NowTime += '.0' + month;
			 }else{
				 NowTime += '.' + month;
			 }
			 if(date <10){
				 NowTime += '.0' + date;
			 }else{
				 NowTime += '.' + date;
			 }
			 NowTime += ' ' + Now.getHours();
			 NowTime += ':' + Now.getMinutes();

	   		 $.ajax({
	   			url:'insert_chat.carrot',
	   			type:'post',
	   			data:'message='+message+'&pid='+pid+'&id='+log_id+'&rid='+buyer_id+'&sid='+seller_id,
	   			dataType:'text',
	   			contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	   			success:function(data){
	   				if(data){
	   					$(".chatContent").append(my_chat + my_chat_msg + message + my_chat_date + NowTime + '</div></div></div>');
	   				}
	   				$("#sendText").val("");
	   				$("#sendText").focus();
	   				$(".chatContent").scrollTop($(".chatContent")[0].scrollHeight-40);
	   			}
	   		 });
	   	 }
	  });
	  /* $('.sendText').click(function() {
	   	 $('.sendText').val("");
	  }); */
	 
	 $(".like_img").click(function(){
		var pid = '${pid}';
		 $.ajax({
		 url:"mypage_cart2.carrot",
		 type:"get",
		 data:"pid="+pid,
		 success:function(data){
			 if(data=="success"){
			 	alert("관심목록에 추가되었습니다");
		      	$(".like_img").attr('src','http://localhost:9000/daangn/resources/images/like2.jpg');
			 }else{
				 alert("관심목록에서 삭제 되었습니다.")
		      	$(".like_img").attr('src','http://localhost:9000/daangn/resources/images/like1.jpg');								 
			 }
			 
			} 
		}); 
		 
 	}); 
 });
 
	var slideIndex = 1;
	showDivs(slideIndex);
	
	function plusDivs(n) {
	  showDivs(slideIndex += n);
	}
	
	function showDivs(n) {
		var i;
		var x = document.getElementsByClassName("mySlides");
		var dots = document.getElementsByClassName("board_img_dot");
		if (n > x.length) {slideIndex = 1}
		if (n < 1) {slideIndex = x.length}
		for (i = 0; i < x.length; i++) {
		  x[i].style.display = "none";  
		}
		for (i = 0; i < dots.length; i++) {
		    dots[i].className = dots[i].className.replace(" active", "");
		}
		x[slideIndex-1].style.display = "block";  
		dots[slideIndex-1].className += " active";
	}
	
</script>
<script>

var btn = document.getElementById("btn_chatting");

function openForm() {
	  document.getElementById("container").style.display = "block";
	  /* document.getElementById("ampaginationsm").style.display = "none"; */
	}

function closeForm() {
  document.getElementById("container").style.display = "none";
  
  /* document.getElementById("ampaginationsm").style.display = "block"; */
}

window.onclick = function(event) {
  if (event.target == document.getElementById("myModal")) {
	  document.getElementById("myModal").style.display = "none";
  }
}

/*setinterval 조작*/
var timer = null; 
var active = false;

function onhide(){//clearInterval 시키기
	
    if(timer != null){
        clearInterval(onshow);
        clearInterval(onshow2);
        timer = null;
    }
}

function onshow(){//setInterval 시키기
    if(true == active){
        timer = setInterval(getChatData,1000);
	}
}
function onshow2(){//setInterval 시키기
    if(true == active){
        timer = setInterval(getChatData2,1000);
	}
}

function getChatData(){
	 $(".chat-column").remove();
	 
	 var pid = '${ vo.pid }';
	 var log_id = '${svo.id}';
	 var seller_id = '${vo.id}';
	 var img = 'http://localhost:9000/daangn/resources/upload/${vo.mfile}';
	 var my_chat = "<div class='chat-column company'><div class='item'><div class='chat-icon'>"+log_id+"</div></div><div class='item'>";
	 var my_chat_msg = "<div class='chat-message'>";
	 var my_chat_date = "</div><div class='message-date'>";
	 var your_chat = '<div class="chat-column customer"><div class="item"><div class="chat-icon">'+seller_id+'</div></div>';
	 var your_chat_msg = '<div class="item"><div class="chat-message">';
	 var your_chat_date = '</div><div class="message-date">';
	 
	 $.ajax({
		 url: 'board_chat_proc.carrot',
		 type: 'get',
		 data: 'pid='+pid+'&id='+log_id+'&sid='+seller_id+'&rid='+buyer_id,
		 dataType: 'json',
		 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		 success:function(data){
				 for(var i=0; i<data.length;i++){
						if(data[i].id != seller_id){
							$(".chatContent").append(my_chat + my_chat_msg + data[i].message + my_chat_date + data[i].mdate + '</div></div></div>');
						}else{
							$(".chatContent").append(your_chat + your_chat_msg + data[i].message + your_chat_date + data[i].mdate + '</div></div></div>');
						}
					}
			 $(".chatContent").scrollTop($(".chatContent")[0].scrollHeight-40);
		 }
	 });
}

function getChatData2(){
	$(".chat-column").remove();
	 
	 var pid = '${ vo.pid }';
	 var log_id = '${svo.id}';
	 var seller_id = '${vo.id}';
	 var img = 'http://localhost:9000/daangn/resources/upload/${vo.mfile}';
	 var my_chat = "<div class='chat-column company'><div class='item'><div class='chat-icon'>"+seller_id+"</div></div><div class='item'>";
	 var my_chat_msg = "<div class='chat-message'>";
	 var my_chat_date = "</div><div class='message-date'>";
	 var your_chat = '<div class="chat-column customer"><div class="item"><div class="chat-icon">'+buyer_id+'</div></div>';
	 var your_chat_msg = '<div class="item"><div class="chat-message">';
	 var your_chat_date = '</div><div class="message-date">';
	 $.ajax({
		 url: 'board_chat_proc.carrot',
		 type: 'get',
		 data: 'pid='+pid+'&id='+log_id+'&sid='+seller_id+'&rid='+buyer_id,
		 dataType: 'json',
		 contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		 success:function(data){
			 for(var i=0; i<data.length;i++){
					if(data[i].id == seller_id){
						$(".chatContent").append(my_chat + my_chat_msg + data[i].message + my_chat_date + data[i].mdate + '</div></div></div>');
					}else{
						$(".chatContent").append(your_chat + your_chat_msg + data[i].message + your_chat_date + data[i].mdate + '</div></div></div>');
					}
			 }
			 $(".chatContent").scrollTop($(".chatContent")[0].scrollHeight-40);
		 }
	 });
}
</script>
</head>
<body>
	<div>
		<div class="container" id="container">
			<div class="chat_preview" id="chat_preview">
			<!-- BEGIN : chatHeader -->
				<div class="chatHeader">
					<a href="#" class="BackBtn"><i class="fa fa-angle-left"
						aria-hidden="true"></i></a>
					<div class="headerTitle">
						<div class="Txt"> 
							<div>${ vo.ptitle }
								<button type="button" id="btn_out_chat" onclick="closeForm()">나가기</button>
							</div>
						</div>
					</div>
				</div>
				<!-- END : chatHeader -->
		
				<!-- BEGIN : chatContent -->
				<div class="chatContent">
					<%-- <c:forEach var="vo" items="${ list }">
						<c:choose>
							<c:when test="${ vo.id != svo.id }">
								<div class="chat-column customer">
									<div class="item">
										<div class="chat-icon">상대</div>
									</div>
									<div class="item">
										<div class="chat-message">${ vo.message }</div>
										<div class="message-date">${ vo.mdate }</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="chat-column company">
									<div class="item">
										<div class="chat-icon">나</div>
									</div>
									<div class="item">
										<div class="chat-message">${ vo.message }</div>
										<div class="message-date">${ vo.mdate }</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach> --%>
				</div>
				<!-- END : chatContent -->
		
				<!-- BEGIN : chatSend -->
				<!-- <form name="sendChatForm" action="#" method="get"> -->
				<div class="chatSend">
					<input type="text" class="sendText" id="sendText" placeholder="입력 후 엔터키를 눌러주세요.">
					<div id="send-message">
						<i class="fa fa-long-arrow-right" aria-hidden="true"></i>
					</div>
				</div>
			<!-- </form> -->
			<!-- END : chatSend -->
			</div>
		</div>

		<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>
		<article id="content" data-id="25814395">

	</a> </section> <section id="article-description">
	
		<!-- slide show -->
		<div class="board_slide_div">
			<div class="board_img_div">
				<c:choose>
					<c:when test="${vo.fileArray != null }">
						<c:forEach var='file' items="${vo.fileArray}">
							<img class="mySlides" src="http://localhost:9000/daangn/resources/upload/${file.pfile}"> 
						</c:forEach>
						<button class="btn_slide_left" onclick="plusDivs(-1)">
							<img src="http://localhost:9000/daangn/resources/images/ar_left.png" width="40">
						</button>
						<button class="btn_slide_right" onclick="plusDivs(1)">
							<img src="http://localhost:9000/daangn/resources/images/ar_right.png" width="40">
						</button>
					</c:when>
					<c:otherwise>
						<img class="mySlides" src="https://seoul-p-studio.bunjang.net/product/101832538_1_1557381938_w152.jpg"> 
					</c:otherwise>
				</c:choose>
				
				<!-- <button class="btn_slide_left" onclick="plusDivs(-1)">
					<img src="http://localhost:9000/daangn/resources/images/ar_left.png" width="40">
				</button>
				<button class="btn_slide_right" onclick="plusDivs(1)">
					<img src="http://localhost:9000/daangn/resources/images/ar_right.png" width="40">
				</button> -->
				<!-- <p class="board_dot_p" style="text-align: center">
					<span class="board_img_dot active" onclick="currentSlide(1)"></span> 
					<span class="board_img_dot" onclick="currentSlide(2)"></span>
					<span class="board_img_dot" onclick="currentSlide(3)"></span>
					<span class="board_img_dot" onclick="currentSlide(4)"></span>
				</p> -->
			</div>
			
		</div>
		
		<section id="article-profile"> 
		<a id="article-profile-link" href="/users/4220452">
			<h3 class="hide">프로필</h3>
			<div class="space-between">
				<div>
					<div id="article-profile-image">
					<c:choose>
						<c:when test="${ vo.mfile != null && vo.mfile != '' }">
							<img src="http://localhost:9000/daangn/resources/upload/${ vo.mfile }" />
						</c:when>
						<c:otherwise>
							<img src="http://localhost:9000/daangn/resources/images/rabbit.svg" />
						</c:otherwise>
					</c:choose>
						
					</div>
					<div id="article-profile-left">
						<div id="nickname">${vo.name }</div>
						<div id="region-name">${ vo.address }</div>
					</div>
				</div>
				<div id="article-profile-right">
					<dl id="temperature-wrap">
						<dd>
							<img src="http://localhost:9000/daangn/resources/images/manner.jpg">
						</dd>
					</dl>
					<div class="meters">
						<div class="bar bar-color-03" style="width: 37%;"></div>
					</div>
					<div class="face face-03"></div>
				</div>
			</div>
		</a> </section> <section id="article-description">
		<div id="reserved" class="status">${ vo.pstate }</div>
		<c:choose>
	<c:when test="${result!= false}">
	<img src="http://localhost:9000/daangn/resources/images/like2.jpg" class="like_img">
	</c:when>
	<c:otherwise>
	<img src="http://localhost:9000/daangn/resources/images/like1.jpg" class="like_img">
	</c:otherwise>
	</c:choose>
		
		<h1 id="article-title">${ vo.ptitle }</h1>
		<p id="article-category">
			${ vo.pcate } ·
			<c:choose>
				<c:when test="${ vo.pdate != '0' }">
					<time>${ vo.pdate }일 전</time>
				</c:when>
				<c:otherwise>
					<time>오늘</time>
				</c:otherwise>
			</c:choose>
		</p>
		<p id="article-price">${ vo.pprice }원</p>
		<div id="article-detail">
			<p>${ vo.pcontent }</p>
		</div>
		<p id="article-counts">관심 ${ vo.cart } ∙ 조회 ${ vo.phits } · 채팅 ${ vo.msg }</p>
		</section> 
		<section id="article-comments">
			<div id="chatting_div">
			
			<c:choose>
				<c:when test="${svo.id == vo.id }">
					<button type="button" id="btn_chatting">채팅방 입장</button>
				</c:when>
				<c:otherwise>
					<button type="button" id="btn_chatting" onclick="openForm()">채팅방 입장</button>
				</c:otherwise>
			</c:choose>
			<ul id="chat_ul"></ul>
			<%-- <c:choose>
				<c:when test="${ vo.id == svo.id }">
					<a target="_blank" href="http://localhost:9000/daangn/board_chat2.carrot?pid=${ vo.pid }"><button type=button id="btn_chatting">채팅방 입장</button></a>
				</c:when>
				<c:otherwise>
					<a target="_blank" href="http://localhost:9000/daangn/board_chat3.carrot?pid=${ vo.pid }"><button type=button id="btn_chatting">채팅방 입장</button></a>
				</c:otherwise>
			</c:choose> --%>
			</div>
		</section> 
		</article>
		<section id="article-detail-related">
		<h3>인기 상품</h3>
		<section class="cards-wrap">
		<c:forEach var="pvo" items="${ list }" begin="1" end="6">
			 <article class="card ">
			<a class="card-link ga-click" data-event-label="25825665"
				data-event-category="show_article_from" data-event-action="related"
				href="http://localhost:9000/daangn/board_content.carrot?pid=${ pvo.pid }">
				<div class="card-photo">
					<c:choose>
						<c:when test="${ pvo.fileArray[0] != null }">
							<img src="http://localhost:9000/daangn/resources/upload/${ pvo.fileArray[0].pfile }" style="width: 209px;height: 160px;"/>
						</c:when>
						<c:otherwise>
							<img src="https://seoul-p-studio.bunjang.net/product/101832538_1_1557381938_w152.jpg" style="width: 209px;height: 160px;">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="card-desc">
					<h2 class="card-title">${ pvo.ptitle }</h2>
					<div class="card-region-name">${ pvo.address }</div>
					<div class="card-price ">${ pvo.pprice }</div>
					<div class="card-counts">
						<span> 관심 ${ pvo.cart } </span> ∙ <span> 조회 ${ pvo.phits } </span> ∙ <span> 채팅 ${ pvo.msg } </span>
					</div>
				</div>
			</a></article> 
		</c:forEach>
		<div style="margin-bottom : 100px;">
			<a href="#"><span style="float : right; margin-right: 20px;">더 구경하러 가기</span></a>
		</div>
		</section> 
		</section>
	</div>
		<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>

</body>
</html>