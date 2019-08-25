/**** product_write.jsp ****/
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

	/* 버튼 이벤트 */
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
					
					if(confirm('수정하시려는 물품은 "'+ title	+ '" 입니다. \n다음으로 이동하시겠습니까?')){	
						presentation();
						$('#pre').addClass(' active').siblings().removeClass(' active');
						$('#pre').prev().addClass(' support').siblings().removeClass(' support');
					}
				}
					
				
			/* 사진 올리기 등록 완료 버튼 */
			} else if ($(this).attr('id') == 'pre_btn') {
				if(confirm('물품을 수정하시겠습니까?')){
					
					$("input[name='pprice']").val(uncomma($("input[name='pprice']").val()));

					var pcontent = $("textarea[name='pcontent']").val();
					pcontent = pcontent.replace(/(?:\r\n|\r|\n)/g, '<br/>');
					$("textarea[name='pcontent']").val(pcontent);
					
					console.log($("input[name='pcate']").val());
					console.log($("input[name='ptitle']").val());
					console.log($("input[name='ptype']").val());
					console.log(uncomma($("input[name='pprice']").val()));
					console.log($("textarea[name='pcontent']").val());
					
					// 폼 전송
					updateForm('change');
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

	/* 가격 정규식 */
	$(".sell_type_input").keyup(function(){
		$(this).val($(this).val().replace(/[^0-9]/gi,""));
		//$(this).val($(this).val().replace(/^([1-9]{1}|[0-9])$/gi,""));
		
	});
		
	/* 가격 콤마 찍기 */
	$(function(){
		var $input = $(".sell_type_input");
		
		$input.on('keyup', function() {
			// 입력 값 알아내기
			var _this = this;
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
	console.log("fileUploadAction");
	$("#upFile").trigger("click");
}
// 파일 배열 & 미리보기
function handleImgFileSelect(e) {
	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);
	console.log("filesArr : "+filesArr.length);

	filesArr.forEach(function(f) {

		if (!f.type.match("image.*")) {
			alert("확장자는 이미지 확장자만 가능합니다.");
			return;
		}

		sel_files.push(f);

		var reader = new FileReader();
		reader.onload = function(e) {
			var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
					+ index
					+ ")\" id=\"img_id_"
					+ index
					+ "\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
			$(".item:eq(" + index + ")").find("div").append(html); 
			
			index++;
		}
		reader.readAsDataURL(f);

	});
}
// 파일배열 & 미리보기 삭제
function deleteImageAction(del_idx) {
	sel_files.splice(del_idx, 1);
	
	var img_id = "#img_id_" + del_idx;
	$(img_id).remove();
	
	for(var i = del_idx+1; i<index; i++){
		console.log("for문 : " +i);
		var change = $(".item:eq(" + i + ")");
		var move = i-1;
		
		change.prev().before(change);
		change.find("a").attr("id","img_id_"+move);
		change.find("a").attr("onclick","deleteImageAction(" + move + ")");
	}
	
	index--;
}

// 폼 전송
function updateForm(file_state){
	var pid = $('input[name=pid]').val();
	
	if(file_state == 'change'){
		$.ajax({
			url:"product_file_delete.carrot",
			type:"get",
			data:"pid="+pid,
			dataType:"text",
			success:function(data){
				if(data != null) alert("기존 파일이 삭제되었습니다.");
			}
		});
	}
	
   var form = $('#product_update')[0];
   var formData = new FormData(form);

   
   for(var i=0; i<Object.keys(sel_files).length; i++){
	   formData.append("fileList",sel_files[i]);
   }
   
    $.ajax({
        url: 'product_update_proc.carrot',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        enctype : 'multipart/form-data',
        success: function(result){
        	if(result == "success"){
        		alert('물품 수정이 완료 되었습니다');
        		location.href = "http://localhost:9000/daangn/mypage_sales.carrot";
        	}
        }
    
    });
}