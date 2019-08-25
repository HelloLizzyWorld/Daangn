<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<style>
#content {
	width: 677px;
	margin: 0 auto;
	text-align: center;
	padding-top: 60px;
}

#content #error-text {
	padding-bottom: 60px
}

#content #error-text #error-text-title {
	display: block;
	font-size: 36px;
	margin: 20px 0
}

#content #error-text #error-text-desc {
	margin: 20px 0
}

#content .error-image {
	background:
		url("http://localhost:9000/daangn/resources/images/error_page_image.png")
		no-repeat;
	background-size: 240px 240px;
	width: 240px;
	height: 240px;
	margin: 0 auto
}

#content #go-to-home #go-to-home-link {
	font-size: 18px;
	color: #ff8a3d;
	text-decoration: none
}

#content #go-to-home #go-to-home-link:hover {
	font-weight: bold
}

@media only screen and (min-device-width: 320px) {
	#content .error-image {
		background-image:
			url("http://localhost:9000/daangn/resources/images/error_page_image.png")
	}
}

@media only screen and (-webkit-min-device-pixel-ratio: 2) and (min-width:
		320px) , only screen and (min--moz-device-pixel-ratio: 2) and
		(min-width: 320px) , only screen and (min-device-pixel-ratio: 2) and
		(min-width: 320px) , only screen and (-webkit-min-device-pixel-ratio:
		2) , only screen and (min-resolution: 2dppx) {
	#content .error-image {
		background-image:
			url("http://localhost:9000/daangn/resources/images/error_page_image.png")
	}
}

@media only screen and (-webkit-min-device-pixel-ratio: 3) and (min-width:
		320px) , only screen and (min--moz-device-pixel-ratio: 3) and
		(min-width: 320px) , only screen and (min-device-pixel-ratio: 3) and
		(min-width: 320px) , only screen and (-webkit-min-device-pixel-ratio:
		3) , only screen and (min-resolution: 3dppx) {
	#content .error-image {
		background-image:
			url("http://localhost:9000/daangn/resources/images/error_page_image.png")
	}
}
</style>
</head>
<body>
	<div id="content" class="">
		<div class="error-image"></div>
		<div id="error-text">
			<h2 id="error-text-title">앗! 이런...</h2>
			<p id="error-text-desc">
				신고접수되어 서비스를 이용할수 없습니다.<br/>
				관련 접수 내용은 메일을 통해 발송해드렸습니다.<br/>
							
			</p>
			<div id="go-to-home">
				<a id="go-to-home-link" href="http://localhost:9000/daangn/index.carrot">홈으로 이동</a>
			</div>
		</div>
	</div>
</body>
</html>