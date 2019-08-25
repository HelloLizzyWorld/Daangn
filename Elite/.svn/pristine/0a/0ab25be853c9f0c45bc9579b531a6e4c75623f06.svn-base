<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/notice.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/admin.css">
</head>

<body>
	<%-- <c:import url="http://localhost:9000/daangn/header.carrot" /> --%>
	<div class="admin">
		<div class="admin_h2">
			<h2 id="admin_span_member">공지사항<span>당근마켓의 새로운 소식들과 유용한 정보를 한곳에서 확인하세요.</span></h2>
			<div class="admin_line_member"></div>
		</div>
		<br>
		
		
		<table class="notice_content_table">
			<tr>
				<th>제목</th>
				<td colspan=3>${ vo.ntitle }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${ vo.ndate }</td>
				<th class="content_hits">조회수</th>
				<td>${ vo.nhits }</td>
			</tr>
			<tr>
				<td colspan="4" class="notice_content_td">
					<p>
						${ vo.ncontent }<br>
						<c:if test="${ vo.nfile != null && vo.nfile !='' }">
							<img src="http://localhost:9000/daangn/resources/upload/${ vo.nfile }" style="max-width:600px; max-height:300px; margin:5px;">
						</c:if>
					</p>
				</td>
			</tr>
		</table>
		
		<div class="notice_content_div">
			<a href="http://localhost:9000/daangn/notice.carrot?nid=${nid}&rno=${rno}&rpage=${rpage}">
				<button type="button" id="notice_content_btn">목록</button>
			</a>
		</div>
		
		<table class="pre_next">
			<c:forEach items="${list}" var="vo">
				<c:choose>
					<c:when test="${vo.pt !=null }">
						<tr>
							<th><img
								src="http://localhost:9000/daangn/resources/images/iconfinder_icon_set_outlinder-04_2519697.png">이전글</th>
							<td class="prev"><a
								href="http://localhost:9000/daangn/notice_content.carrot?nid=${vo.pp}&rno=${rno}&rpage=${rpage}">
									${vo.pt}</a></td>
						</tr>
					</c:when>
					<c:otherwise>
						<th><img
							src="http://localhost:9000/daangn/resources/images/iconfinder_icon_set_outlinder-04_2519697.png">이전글</th>
						<td class="prev">이전글이 없습니다.</td>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${vo.nt !=null }">
						<tr>
							<th><img
								src="http://localhost:9000/daangn/resources/images/iconfinder_icon_set_outlinder-02_2519700.png">다음글</th>
							<td class="next"><a
								href="http://localhost:9000/daangn/notice_content.carrot?nid=${vo.np}&rno=${rno}&rpage=${rpage}">
									${vo.nt}</a></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th><img
								src="http://localhost:9000/daangn/resources/images/iconfinder_icon_set_outlinder-02_2519700.png">다음글</th>
							<td class="next">다음글이 없습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</table>
		
	</div>

	<%-- <c:import url="http://localhost:9000/daangn/footer.carrot" /> --%>
</body>
</html>