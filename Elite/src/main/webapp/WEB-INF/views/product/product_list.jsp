<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/product.css">
<link rel="stylesheet" type="text/css" href="http://localhost:9000/daangn/resources/css/am-pagination.css">
<script src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<script src="http://localhost:9000/daangn/resources/js/am-pagination.js"></script>
<script>
$('document').ready(function() {

	// 시/도 선택시 구/군 설정
 	$("#region1").change(function() {
 		var sido = $("#region1").val();
 		$("#region2").find("option").remove();
 		//$("#region3").hide();
 		
 		if(sido != "0"){
 			$(location).attr('href', "http://localhost:9000/daangn/product_list.carrot?state=4&keyword=" + sido);
 		}else{
 			$(location).attr('href', "http://localhost:9000/daangn/product_list.carrot?state=1&keyword=all");
 		}
	});
	
	// 구/군/시 선택시 동 설정
 	$("#region2").change(function() {
 		var sido = $("#region1").val();
 		var sigungu = $("#region2").val();
 		
 		//$("#region3").show();
  		//$("#region3").find("option").remove();
 		
 		if(sigungu != "0"){
 			$(location).attr('href', "http://localhost:9000/daangn/product_list.carrot?state=5&keyword=" + sido + sigungu);
 			
/*  			$.ajax({
 				url : "juso_hname.carrot",
 				type : "get",
 				data : "sigungu=" + sigungu,
 				dataType : "text",
 				success : function(data){
 					var hname = JSON.parse(data);
 					$("#region3").append("<option value='0'>동을 선택하세요</option>");
 					for(var i in hname) {
 						var html = "<option value='"+ hname[i].hname +"'>"+ hname[i].hname + "</option>";
 						$("#region3").append(html);
 					}
 				}
 			}); */
 		}else{
 			//$("#region3").append("<option value='0'>동을 선택하세요</option>");
 			$(location).attr('href', "http://localhost:9000/daangn/product_list.carrot?state=1&keyword=all");
 		} 
	});

	var pager = jQuery('#ampaginationsm').pagination({

		maxSize : 7, // max page size
		totals : '${dbCount}', // total pages	
		page : '${rpage}', // initial page		
		pageSize : '${pageSize}', // max number items per page

		// custom labels		
		lastText : '&raquo;&raquo;',
		firstText : '&laquo;&laquo;',
		prevText : '&laquo;',
		nextText : '&raquo;',

		btnSize : 'sm' // 'sm'  or 'lg'		
	});

	jQuery('#ampaginationsm').on(
			'am.pagination.change',
			function(e) {
				jQuery('.showlabelsm').text(
						'The selected page no: ' + e.page);
				$(location).attr(
						'href',
						"http://localhost:9000/daangn/product_list.carrot?rpage=" + e.page 
								+ "&state=" + "${state}" + "&keyword=" + "${keyword}");
			});

});
</script>
</head>
<body>

	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>

	<section id="content" style="margin-bottom:60px;"> 
	<c:choose>
		<c:when test="${state == '1'}">
			<h1 class="head-title" id="hot-articles-head-title">당근마켓 최근 매물</h1>
		</c:when>
		<c:when test="${state == '2'}">
			<h1 class="head-title" id="hot-articles-head-title">${keyword}</h1>
		</c:when>
		<c:when test="${state == '3'}">
			<h1 class="head-title" id="hot-articles-head-title">'${keyword}'에 대한 검색 결과</h1>
		</c:when>
		<c:when test="${state == '4'}">
			<h1 class="head-title" id="hot-articles-head-title">${keyword} 중고 매물</h1>
		</c:when>
		<c:when test="${state == '5'}">
			<h1 class="head-title" id="hot-articles-head-title">${fn:substring(keyword, 0, 2)} ${fn:substring(keyword, 2, 6)} 중고 매물</h1>
		</c:when>
	</c:choose>
	<div class="title-line-divider"></div>

	<nav id="hot-articles-navigation"> 
	
	<c:choose>
		<c:when test="${state == 4}">
			<select name="region1" id="region1" class="hot-articles-nav-select" style="width: 198px" value="${keyword}">
				<option value="${keyword}">${keyword}</option>
					<c:forEach var="juso" items="${sido}">
						<option value="${juso.sido}">${juso.sido}</option>
					</c:forEach>
				<option value="0">지역을 선택하세요</option>
			</select> 		
			<select name="region2" id="region2" class="hot-articles-nav-select" style="width: 198px">
				<option value="0">동네를 선택하세요</option>
					<c:forEach var="juso" items="${sigungu}">
						<option value="${juso.sigungu}">${juso.sigungu}</option>
					</c:forEach>
			</select> 
		</c:when>
	
		<c:when test="${state == 5}">
			<select name="region1" id="region1" class="hot-articles-nav-select" style="width: 198px">
				<option value="${fn:substring(keyword, 0, 2)}">${fn:substring(keyword, 0, 2)}</option>
					<c:forEach var="juso" items="${sido}">
						<option value="${juso.sido}">${juso.sido}</option>
					</c:forEach>
				<option value="0">지역을 선택하세요</option>
			</select> 		
			<select name="region2" id="region2" class="hot-articles-nav-select" style="width: 198px">
				<option value="${fn:substring(keyword, 2, 6)}">${fn:substring(keyword, 2, 6)}</option>
					<c:forEach var="juso" items="${sigungu}">
						<option value="${juso.sigungu}">${juso.sigungu}</option>
					</c:forEach>
				<option value="0">동네를 선택하세요</option>
			</select> 
		</c:when>
		
		<c:otherwise>
			<select name="region1" id="region1" class="hot-articles-nav-select" style="width: 198px">
			<option value="0">지역을 선택하세요</option>
				<c:forEach var="juso" items="${sido}">
					<option value="${juso.sido}">${juso.sido}</option>
				</c:forEach>
			</select> 
			<select name="region2" id="region2" disabled="disabled" class="hot-articles-nav-select" style="width: 198px">
				<option value="0">동네를 선택하세요</option>
			</select> 
		</c:otherwise>
	</c:choose>
	
<!-- 	<select name="region3" id="region3" class="hot-articles-nav-select" style="width: 198px; display:none;">
		<option value="0">동을 선택하세요</option>
	</select>  -->
		
	</nav> 
	
	<section class="cards-wrap"> 
		<c:forEach var="vo" items="${list}">
			<article class="card-top "> 
				<a class="card-link " href="http://localhost:9000/daangn/board_content.carrot?pid=${vo.pid}">
					<div class="card-photo">
					
					<c:choose>
						<c:when test="${not empty vo.fileArray}">
							<c:forEach var="file" items="${vo.fileArray}" begin = "0" end = "0">
								<img  alt="${vo.ptitle}" src="http://localhost:9000/daangn/resources/upload/${file.pfile}"/>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<img alt="${vo.ptitle}" src="https://seoul-p-studio.bunjang.net/product/101832538_1_1557381938_w152.jpg" />
						</c:otherwise>
					</c:choose>
						<%-- <img alt="${vo.ptitle}" src="http://localhost:9000/daangn/resources/upload/${vo.pfile }"> --%>
						
					</div>
					<div class="card-desc">
						<h2 class="card-title">${vo.ptitle}</h2>
						<div class="card-region-name">${vo.addr_sido} ${vo.addr_sigungu}</div>
						
						<c:choose>
							<c:when test="${vo.ptype ne '판매'}">
								<div class="card-price">${vo.ptype}</div>
							</c:when>
							<c:otherwise>
								<div class="card-price"><fmt:formatNumber type="number" maxFractionDigits="3" value="${vo.pprice}" />원</div>
							</c:otherwise>
						</c:choose>
						
						<div class="card-counts">
							<span> 관심 ${vo.cart} </span> ∙ <span> 채팅 ${vo.msg} </span>
						</div>
					</div>
				</a> 
			</article>
		</c:forEach>
	<div id="ampaginationsm" style="text-align: center;"></div>

	</section> <!-- end of cards-wrap --> </section>
	<!-- end of content -->
	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>

</body>
</html>