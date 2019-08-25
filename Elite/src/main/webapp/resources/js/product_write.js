//파일 업로드 전역 변수
var sel_files = [];
var index = 0;
$(document).ready(function() {
	
	// 전역변수 선언 
	var cat_choice; //카테고리 선택 
	var sell_type; //판매 종류
	var sqn; //탭 인덱스
	
	/* 시작 화면 (category) */
	category();
	
	/* 상단 메뉴 선택 */
	$('.tabs a').click(function() {
		sqn = $(this).index() + 1;
		if ($(this).index() == 0) {
			$(this).addClass(' active').siblings().removeClass(' active');
			$(this).siblings().removeClass(' support');
		} else {
			$(this).addClass(' active').siblings().removeClass(' active');
			$(this).prev().addClass(' support').siblings().removeClass(' support');
		}
		
		/* 선택한 메뉴 내용 표시 */
		$('.regist_box').each(function() {
			if (sqn == 1) {
				category();
			} else if (sqn == 2) {
				description();
			} else if (sqn == 3) {
				presentation();
			}
		});
	});
	/* 버튼 이벤트 - 유효성 체크 진행 */
	$('.btn_box').click(function() {
			/* 카테고리 다음 버튼 */
			if ($(this).attr('id') == 'cat_btn') {
				if (cat_choice == null || cat_choice == "") {
					alert("카테고리를 선택해주세요");
					return false;
				} else {
					if(confirm('선택하신 카테고리는 "'+ cat_choice + '" 입니다.\n다음으로 이동하시겠습니까?')){	
						description();
						$('#des').addClass(' active').siblings().removeClass(' active');
						$('#des').prev().addClass(' support').siblings().removeClass(' support');
					}
				}
			/* 상세 정보 다음 버튼 */
			} else if ($(this).attr('id') == 'des_btn') {
				var title = $('.reg_title').val(); 
				
				if(title == "" || title == null){
					alert("제목을 입력해주세요.");
					return false;
					
				} else if((sell_type == null || sell_type == '판매') && $('.sell_type_input').val() == '') {
					alert("가격을 입력해주세요.");
					$('.sell_type_input').focus();
					return false;
					
				}else{
					
					if(confirm('등록하시려는 물품은 "'+ title	+ '" 입니다. \n다음으로 이동하시겠습니까?')){	
						presentation();
						$('#pre').addClass(' active').siblings().removeClass(' active');
						$('#pre').prev().addClass(' support').siblings().removeClass(' support');
					}
				}
					
				
			/* 사진 올리기 등록 완료 버튼 */
			} else if ($(this).attr('id') == 'pre_btn') {
				if(confirm('물품을 등록하시겠습니까?')){
					
					// view단에서 보여주었던 comma 제거
					$("input[name='pprice']").val(uncomma($("input[name='pprice']").val()));
					var pcontent = $("textarea[name='pcontent']").val();
					// 개행문자 적용하기 위해 <br>로 치환하여 적용
					pcontent = pcontent.replace(/(?:\r\n|\r|\n)/g, '<br/>');
					$("textarea[name='pcontent']").val(pcontent);
					
					// 폼 전송
					uploadForm();
				}
			}
		});
	
	/* 카테고리 선택시 배경 색깔 변경 & value값 추가 */
	$('.category').click(function() {
		$(this).children('.cat_link').css('background-color', '#dbdbdb');
		$(this).siblings().children('.cat_link').css('background-color', '#fff');
		cat_choice = $(this).find('.cat_text').text();
		$("input[name='pcate']").val(cat_choice);
	});
	
	/* 거래종류 버튼 */
	$('.item_status_list li').click(function() {
		// 거래종류 기본 value값은 '판매'로 지정
		if ($(this).index() == 0) {
			$(".price_area").css("display","");
			$(this).children('div').addClass(' click');
			$(this).siblings().children('div').removeClass(' click');
			
		} else {
			$(".price_area").css("display","none");
			$(this).children('div').addClass(' click');
			$(this).siblings().children('div').removeClass(' click');
		}
		
		sell_type = $(this).find('.text').text();
		$("input[name='ptype']").val(sell_type);
		
	});
	/* 가격 정규식 - 숫자 이외에는 입력되지 않게 함*/
	$(".sell_type_input").keyup(function(){
		$(this).val($(this).val().replace(/[^0-9]/gi,""));
		
	});
		
	/* 가격 콤마 찍기 */
	$(function(){
		var $input = $(".sell_type_input");
		$input.on('keyup', function() {
			var _this = this; // 입력 값 알아내기
			numberFormat(_this)
		  	});
		});
	
	// 파일 업로드를 위한 파일창 띄우기
	$(".up_img").on("click",fileUploadAction);
	// 파일 미리보기 실행
	$("#upFile").on("change", handleImgFileSelect);
	
});
// 콤마 찍기
function comma(str) {
  str = String(str);
  return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}
// 콤마 풀기
function uncomma(str) {
  str = String(str);
  return str.replace(/[^\d]+/g, '');
}
function numberFormat(obj) {
  obj.value = comma(uncomma(obj.value));
}
/* 카테고리 화면 */
function category() {
	$('#category').show();
	$('#cat_btn').show();
	$('#description').hide();
	$('#des_btn').hide();
	$('#presentation').hide();
	$('#pre_btn').hide();
}
/* 상세내용 화면 */
function description() {
	$('#description').show();
	$('#des_btn').show();
	$('#category').hide();
	$('#cat_btn').hide();
	$('#presentation').hide();
	$('#pre_btn').hide();
}
/* 사진 올리기 화면 */
function presentation() {
	$('#presentation').show();
	$('#pre_btn').show();
	$('#category').hide();
	$('#cat_btn').hide();
	$('#description').hide();
	$('#des_btn').hide();
}
//이미지만 눌러도 파일 업로드 실행 
function fileUploadAction() {
	$("#upFile").trigger("click");
}
// 파일 배열 & 미리보기
function handleImgFileSelect(e) {
	var files = e.target.files; // input에 있는 파일을 변수에 담음 
	var filesArr = Array.prototype.slice.call(files); // 파일 배열 복사본을 만듦 
	
	filesArr.forEach(function(f) {
		//image만 upload 가능
		if (!f.type.match("image.*")) {
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}
		sel_files.push(f); // 전역변수로 선언한 파일 배열에 담음
		var reader = new FileReader();
		reader.onload = function(e) { // 파일 읽기
			var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
					+ index
					+ ")\" id=\"img_id_"
					+ index
					+ "\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
			$(".item:eq(" + index + ")").find("div").append(html); 
			// 링크, 클릭시 삭제되는 이벤트, 파일의 id, image 주소, 이미지 이름, class 이름 추가
			
			index++; // 파일이 추가되면 index 추가
		}
		reader.readAsDataURL(f); // 현재 파일을 읽어옴
	});
}
// 파일배열 & 미리보기 삭제
function deleteImageAction(del_idx) {
	sel_files.splice(del_idx, 1); // 파일 배열의 삭제하려는 배열 인덱스 1개를 삭제
	
	var img_id = "#img_id_" + del_idx; 
	$(img_id).remove(); // 미리보기 이미지 삭제
	
	for(var i = del_idx+1; i<index; i++){
		// 삭제된 파일 다음 파일들의 index 조정(del_idx+1)
		var change = $(".item:eq(" + i + ")");// 삭제된 파일 다음 파일의 인덱스가 포함되어있는 li태그
		var move = i-1; // 한칸씩 앞으로 이동
		
		change.prev().before(change); // 현재 li의 이전 li 앞에 선택한 li를 넣기
		change.find("a").attr("id","img_id_"+move); // 링크 수정
		change.find("a").attr("onclick","deleteImageAction(" + move + ")"); // 삭제 이벤트 수정
	}
	index--; // 인덱스 1개씩 삭제
}
// 폼 전송
function uploadForm(){
   var form = $('#product_write')[0]; // form의 id
   var formData = new FormData(form); // form을 ajax로 전송하기 위해 현재 사용하고 있는 form을 넣어 FormData객체 생성 
   
   for(var i=0; i<Object.keys(sel_files).length; i++){ // 파일 배열 길이만큼
	   formData.append("fileList",sel_files[i]); // formData에 전역변수로 선언한 파일배열을 추가
   }
   
    $.ajax({
        url: 'product_write_proc.carrot',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        enctype : 'multipart/form-data',
        success: function(result){
        	if(result == "success"){
        		alert('물품 등록이 완료 되었습니다');
        		location.href = "http://localhost:9000/daangn/mypage_sales.carrot";
        	}
        }
    
    });
}