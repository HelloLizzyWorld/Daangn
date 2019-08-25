<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//Dth HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dth">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>당근마켓 - 우리 동네 중고 직거래 마켓</title>
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/index.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/mypage.css">
<link rel="stylesheet" type="text/css"
	href="http://localhost:9000/daangn/resources/css/join.css">
<script src="http://localhost:9000/daangn/resources/js/jquery-3.3.1.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>

$(document).ready(function() {
	
	 $(function(){
	        $("#password-success").hide();
	        $("#password-failed").hide();
	        
	        $("input#cpass").keyup(function(){
	            var pwd1=$("#pass").val();
	            var pwd2=$("#cpass").val();
	            if(pwd1 != "" || pwd2 != ""){
	                if(pwd1 == pwd2){
	                	
	                    $("#password-success").show();
	                    $("#password-failed").hide();
	                   
	                }else{
	                    $("#password-success").hide();
	                    $("#password-failed").show();
	                 
	              }    
	            }
	        });
	    }); 
	
	$("#btn_update1").click(function() {
		if($("#name").val() == ""){
			alert("이름을 입력해주세요.");
			$("#name").focus();
			return false;
		}else if($("#email").val() == ""){
			alert("이메일을 입력해주세요.")
			$("#email").focus();
			return false;
		}else if($("#phone").val() == ""){
			alert("핸드폰 번호를 입력해주세요.");
			$("#phone").focus();
			return false;
		}
	
		$("#id").removeAttr("disabled");
		var id = $("#id").val();
		var nick = $("#nick").val();
		var pass = $("#pass").val();
		var name = $("#name").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var gender = $('input[name="gender"]:checked').val();
		var birth_year = $("#birth_year").val();
		var birth_month = $("#birth_month").val();
		var birth_day = $("#birth_day").val();
		var birth = birth_year +"/"+ birth_month +"/"+ birth_day;
		var sample6_detailAddress = $("#sample6_detailAddress").val();
		var sample6_address = $("#sample6_address").val();
		var address = sample6_address + sample6_detailAddress;
		
			
	window.location.href = "http://localhost:9000/daangn/mypage_info3.carrot?id="+id+
																			"&nick="+nick+
																			"&pass="+pass+
																			"&gender="+gender+
																			"&birth="+birth+
																			"&name="+name+
																			"&email="+email+
																			"&phone="+phone+
																			"&address="+address;
			alert("회원정보가 수정되었습니다.")
			

	});
	
	
	
	
	
	//이메일 중복체크
	$("#btn_overlap1").on('click', function(){
		if($("#email").val()==""){
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return false;
		}else{
			var email = $("#email").val();
			$.ajax({
				url:"email_check.carrot",
				type: "get",
				data: "email="+email,
				dataType: "text",
				success:function(data){
					
					if(data == 1){
						$("#check_email_result").text("이미 사용중인 이메일입니다.").css("color","red").css("padding-top","10px");
						$("#email").val("");
						$("#eamil").focus();
					}else{
						$("#check_email_result").text("사용가능한 이메일입니다.").css("color","blue").css("padding-top","10px");
					}
				}
					
			});
		
		}
		
	});
	
	$("#btn_overlap2").on('click',function(){
		
		if($("#nick").val()== ""){
			alert("닉네임을 입력해주세요.");
			$("#nick").focus();
			return false;
		}else{
			var nick = $("#nick").val();
			
			$.ajax({
				url:"nick_check.carrot",
				type:"get",
				data:"nick="+nick,
				success:function(data){
					
					 if(data == 1){
						$("#nick_result").text("이미 사용중인 닉네임입니다.").css("color","red").css("padding-top","10px");
						$("#nick").val("");
						$("#nick").focus();
					}else{
						$("#nick_result").text("사용가능한 닉네임입니다.").css("color","blue").css("padding-top","10px");
						$("#nick_check").val('0');
					} 
				}
				
			})
		}
		
	});
	
		
		 
	    var h_birth = $("#h_birth").val(); // ex) 2019/06/16 자를 문자열을 가지고 온다!
	    var birth_y = h_birth.split("/");  // 자를 기준을 split 함수 안에 넣어준다!
	    
	    $("#birth_year").val(birth_y[0]);  // input value 안에 값을 넣어준다 !
	    $("#birth_month").val(birth_y[1]); // * split 함수는 잘라서 배열로 반환해줌 ! 
	    $("#birth_day").val(birth_y[2]);
	
	    var h_address = $("#h_address").val();
	    var address_y = h_address.split("로");
	  
	    $("#sample6_address").val(address_y[0]+"로");
		$("#sample6_detailAddress").val(address_y[1]);
		
	    $("#btn_address").click(function(){
			
			new daum.Postcode({
			    oncomplete: function(data) {
			    	
					$('input[name=addr_sido]').val(data.sido);
					$('input[name=addr_sigungu]').val(data.sigungu);
					$('input[name=addr_bname]').val(data.bname);
					//alert(JSON.stringify(myObj));
					
			    	 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadAddress;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.jibunAddress;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                   
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                   
	                
	                } 

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	              
	                document.getElementById("sample6_address").value = addr;
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("sample6_detailAddress").focus();
	                
	               
			    }
			 
				}).open();
			
		});
	    
	    $("#btn_delete").click(function() {
			
			var id = $("#id").val();
		
			var result = confirm("회원탈퇴를 하시면 회원님의 모든 데이터(개인정보, 매너온도)가 삭제 되어집니다.\n그래도 회원을 탈퇴하시겠습니까?");
			
			if(result){
				window.location.href = "http://localhost:9000/daangn/mypage_info_cancel.carrot?id="+id;
			}
			
		});
	    
	    
	    
	});

</script>
</head>
<body>
	<div id="mypage_content" >
	<!-- style="height: 1050px;" -->
		<!-- 메인로고 -->
		<div id="top_div">
			<div id="title_div"></div>
		</div>

		<!-- 마이페이지 상단 박스 -->
		<jsp:include page="mypage_box.jsp"></jsp:include>


		<div class="mypage_menu">
			<div class="page-tabs">
				<!-- 수평 메뉴 -->
				<div class="tabs">
					<a href="http://localhost:9000/daangn/mypage_info.carrot" class="tab active">개인정보</a> 
					<a href="http://localhost:9000/daangn/mypage_cart.carrot" class="tab">관심목록</a>
					<a href="http://localhost:9000/daangn/mypage_sales.carrot" class="tab">판매내역</a> 
					<a href="http://localhost:9000/daangn/mypage_purchase.carrot" class="tab">구매내역</a>
				</div>

				<!-- 기본정보 수정 -->
				
					
					<div
						style="border-bottom: 2px solid #4D4D4D; margin-top: 70px; margin-bottom: 70px;">
						<h3 style="color: #4D4D4D; text-align: left;">기본정보</h3>
					</div>
					<form name="info_form" action="mypage_info.carrot" method="post">
					<div class="member_join">
						<div class="boardWrite">
							<table class="tbl_comm">
								<tr>
									<td class="memberCols1">아이디
										<p class="star">*</P>
									</td>
									<td class="memberCols2"><input type="text" name="id"
										value="${vo.id}" maxlength="16" label="아이디" id="id" disabled>

										<p class="txt_guide">
											<span class="txt txt_case1">6자 이상의 영문 혹은 영문과 숫자를 조합</span> <span
												class="txt txt_case2">아이디 중복확인</span>
										</p></td>
								</tr>
								<tr>
								<td class="memberCols1">닉네임
									<p class="star">*</P>
								</td>
								<td class="memberCols2">
								<input type="text" name="nick" id="nick" value="${vo.nick }" maxlength="16" label="닉네임" placeholder="예: 당근당근" id="nick">
							
									<button type="button" class="btn_overlap" id="btn_overlap2">중복확인</button>
									
							</td>		
							</tr>
							<tr>
								<td></td>
								<td id="nick_result" style="display:inline-block;"></td>
							</tr>
								
								
								<tr>
									<td class="memberCols1">새 비밀번호
									</td>
									<td class="memberCols2"><input type=password name="pass"
										maxlength="16" class="reg_pw" id="pass"></td>
								</tr>
								<tr>
									<td class="memberCols1" style="width: 150px;">새 비밀번호 확인 <!-- <p class="star">*</P> -->
									</td>
									<td class="memberCols2"><input type=password
										name="pass" maxlength="16" class="confirm_pw" id="cpass">
									</td>
								</tr>
								<tr>
							<td></td>
							<td class="password-success" id="password-success" style="color:blue; padding-top:8px">비밀번호가 일치합니다.</td>
							<td class="password-failed" id="password-failed" style="color:red; padding-top:8px">비밀번호가 일치하지 않습니다.</td>
						</tr>
								<tr>
									<td class="memberCols1">이름
										<p class="star">*</P>
									</td>
									<td class="memberCols2"><input type="text" name="name"
										value="${vo.name}" id="name"></td>
								</tr>
								<tr>
									<td class="memberCols1">이메일
										<p class="star">*</P>
									</td>
									<td class="memberCols2"><input type="text" name="email"
										id="email" value="${vo.email}" size=30>
											
										<button type="button" class="btn_overlap" id="btn_overlap1">이메일중복확인</button></td>
								</tr>
								<tr>
									<td></td>
									<td id="check_email_result" style="display:inline-block;">
										
									</td>
								</tr>
								<tr class="mobile">
									<td class="memberCols1">휴대폰
										<p class="star">*</P>
									</td>
									<td class="memberCols2">
										<div class="phone_num">
											<input type="text" value="${vo.phone}" pattern="[0-9]*" name="phone"
												class="inp" id="phone"> 
										</div>
									</td>
								</tr>
								<tr class="address">
									<td class="memberCols1">주소
										<p class="star">*</P>
									</td>
									<td class="admin_address_btn">
										<button type="button" class="btn_address" id="btn_address">
											<img
												src="http://localhost:9000/daangn/resources/images/Search-icon.png"
												style="width: 15px; height: 15px; padding-right: 5px;">주소검색
										</button>
									</td>
								</tr>
								<tr>
								<td></td>
								<td>
									<!-- <input type="text" id="sample6_postcode" placeholder="우편번호"> -->
									<!-- <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> -->

									<input type="text" id="sample6_address" placeholder="주소" name="address1" value=""><br>
								</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address2" value=""> 
									<input type="hidden" value="" name="addr_sido" id="sido">
									<input type="hidden" value="" name="addr_sigungu">
									<input type="hidden" value="" name="addr_bname">
									<!-- 	<input type="text" id="sample6_extraAddress" placeholder="참고항목"> -->
									<input type="hidden" id="h_address" value="${vo.address}">
								</td>
							</tr>
							</table>


							<div
								style="border-bottom: 2px solid #4D4D4D; width: 1050px; margin-left: -215px; margin-top: 70px; margin-bottom: 70px;">
								<h3 style="color: #4D4D4D; text-align: left;">추가정보</h3>
							</div>
							<table>

								<tr class="select_sex">
								<td class="memberCols1">성별</td>
								<td class="memberCols2">
									<div class="group_radio">
										<c:choose>
										<c:when test="${vo.gender == '남자'}">
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="남자" checked>
										<span class="text_position">남자</span>
										</label>										
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="여자">
										<span class="text_position">여자</span>
										</label>
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="선택안함">
										<span class="text_position">선택안함</span>
										</label> <!-- <input type="hidden" type=radio name=sex label="성별" value=""> -->
										</c:when>
										<c:when test="${vo.gender == '여자'}">
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="남자">
										<span class="text_position">남자</span>
										</label>										
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="여자" checked>
										<span class="text_position">여자</span>
										</label>
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="선택안함">
										<span class="text_position">선택안함</span>
										</label> <!-- <input type="hidden" type=radio name=sex label="성별" value=""> -->
										</c:when>
										<c:otherwise>
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="남자">
										<span class="text_position">남자</span>
										</label>										
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="여자">
										<span class="text_position">여자</span>
										</label>
										<label class="label_radio">
										<input type=radio name="gender" id="gender" label="성별" value="선택안함" checked>
										<span class="text_position">선택안함</span>
										</label>
										</c:otherwise>
										</c:choose>
										
									</div>
								</td>
							</tr>
								<tr class="birth">
								<td class="memberCols1">생년월일</td>
								<td class="memberCols2">
									<div class="birth_day">
										<input type="text" name=birth_year id="birth_year" value=""
											label="생년월일" size=4 maxlength=4 placeholder="YYYY"
											style="width: 60px; padding-left: 25px; padding-top: 3px;">
										<span class="bar"><span>/</span></span> 
										
										<input type="text" name=birth_month id="birth_month" value="" label="생년월일" size=2
											maxlength=2 placeholder="MM" style="width: 60px; padding-top: 3px;"> 
											<span class="bar"><span>/</span> </span> 
										
										<input type="text" name=birth_day id="birth_day" value="" label="생년월일" size=2 maxlength=2 placeholder="DD"
											style="width: 60px; padding-top: 3px;">
									</div>
									<input type="hidden" id="h_birth" value="${vo.birth}">
										
								</td>

							</tr>
							</table>
						</div>
						<div class="btn_join_div" style ="margin-top: 150px;">
							<button type="button" class="btn_join1" id="btn_join">취소</button>
							<button type="reset" class="btn_join1" id="btn_delete">탈퇴하기</button>
							
							<button type="button" class="btn_join1" id="btn_update1">회원정보수정</button>
							
						</div>
					</div>
				</form>
				</div><!-- update -->
			</div>
			<!-- page tab div -->
		</div>
		<!-- end of mypage_menu -->
	
	
	<div style="height: 700px;"></div>
</body>
</html>