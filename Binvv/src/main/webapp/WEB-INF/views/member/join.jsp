f<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/member/join.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
</head>
<body>

<div class="wrapper">
	<form id="join_form" method="post">
	<div class="wrap">
			<div class="subjecet">
				<span>회원가입</span>
			</div>
			<div class="id_wrap">
				<div class="id_name">아이디</div>
				<div class="id_input_box">
					<input class="id_input" name="memberId">
				</div>
				<span class="id_input_re_1">사용 가능한 아이디입니다.</span>
				<span class="id_input_re_2">아이디가 이미 존재합니다.</span>
				<span class="final_id_ck">아이디를 입력해 주세요.</span>
			</div>
			<div class="pw_wrap">
				<div class="pw_name">비밀번호</div>
				<div class="pw_input_box">
					<input class="pw_input" name="memberPw">
				</div>
				<span class="final_pw_ck">비밀번호를 입력해 주세요.</span>
			</div>
			<div class="pwck_wrap">
				<div class="pwck_name">비밀번호 확인</div>
				<div class="pwck_input_box">
					<input class="pwck_input">
				</div>
				<span class="final_pwck_ck">비밀번호 확인을 입력해 주세요.</span>
			</div>
			<div class="user_wrap">
				<div class="user_name">이름</div>
				<div class="user_input_box">
					<input class="user_input" name="memberName">
				</div>
				<span class="final_name_ck">이름을 입력해 주세요.</span>
			</div>
			<div class="mail_wrap">
				<div class="mail_name">이메일</div> 
				<div class="mail_input_box">
					<input class="mail_input" name="memberMail">
				</div>
				<span class="final_mail_ck">이메일을 입력해 주세요.</span>
				<div class="mail_check_wrap">
					<div class="mail_check_input_box" id="mail_check_input_box_false">
						<input class="mail_check_input" disabled="disabled">
					</div>
					<div class="mail_check_button">
						<span>인증번호 전송</span>
					</div>
					<div class="clearfix"></div>
					<span id="mail_check_input_box_warn"></span> <!-- (추가해준 span 태그는 인증번호의 일치 여부를 알려주는 경고글 역할을 합니다. ) -->
				</div>
			</div>
			<div class="address_wrap">
				<div class="address_name">주소</div>
				<div class="address_input_1_wrap">
					<div class="address_input_1_box">
						<input class="address_input_1" name="memberAddr1" disabled="readonly">
						<!--  - 변경 이유 : 속성이 추가된 태그(요소)는 form태그에 쌓여서 Controller로 전송이 되어야 함. 
										 하지만 disabled를 설정할 경우 데이터 자체가 전송이 되지 않기 때문에 readonly로 변경했다. -->
					</div>
					<div class="address_button" onclick="execution_daum_address()">
						<span>주소 찾기</span>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class ="address_input_2_wrap">
					<div class="address_input_2_box">
						<input class="address_input_2" name="memberAddr2" disabled="readonly">
					</div>
				</div>
				<div class ="address_input_3_wrap">
					<div class="address_input_3_box">
						<input class="address_input_3" name="memberAddr3" disabled="readonly">
					</div>
				</div>
				<span class="final_addr_ck">주소를 입력해 주세요.</span>
			</div>
			<div class="join_button_wrap">
				<input type="button" class="join_button" value="가입하기">
			</div>
		</div>
	</form>
</div>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!-- 외부 스크립트 파일 연결 코드 추가 - 로딩 방식 : 통합 로딩 방식 -->
<script type="text/javascript">
	
	var code = "";				// 이메일 전송 인증번호 저장위한 코드
	
	/* 유효성 검사 통과유무 변수 */
	var idCehck = false;					// 아이디
	var idckCheck = false;					// 아이디 중복 검사
	var pwCheck = false;					// 비번
	var pwckCheck = false;					// 비번 확인
	var pwckcorCheck = false;				// 비번 확인 일치 확인
	var nameCheck = false;					// 이름
	var mailCheck = false;					// 이메일
	var mailnumCheck = false;				// 이메일 인증번호 확인
	var addressCheck = false;				// 주소
	
	
	$(document).ready(function() {
		
		// 회원가입 버튼(회원가입 기능 작동)			
		$(".join_button").click(function() {							// 가입하기 버튼 클릭을 했을 때,

			/* 입력값 변수 */
			/* 각 항목에 입력된 값을 편하게 사용하기 위해 변수를 선언하여 각 항목의 입력값으로 초기화함. 
			   해당 변수들은 회원가입 버튼 작동 메서드 내부에 선언함.  */
			var id = $('.id_input').val();						// id 입력란
			var pw = $('.pw_input').val();						// 비밀번호 입력란
			var pwck = $('.pwck_input').val();					// 비밀번호 확인 입력란
			var name = $('.name_input').val();					// 이름 입력란
			var mail = $('.mail_input').val();					// 이메일 입력란
			var addr = $('.addr_input_3').val();				// 주소 입력란
			
			/* 아이디 유효성검사 */
			if(id == ""){
				$('.final_id_ck').css('display','block');
				idCheck = false;
			}else{
				$('.final_id_ck').css('display', 'none');
				idCheck = true;
			}
			
			/*  $("#join_form").attr("action", "/member/join");				// 속성 action(url = /member/join)이 추가 되고, 
				$("#join_form").submit();	 */								// form 태그가 서버에 제출 된다는 의미 (제출 방식은 post form 태그에 미리 추가 돼 있음.)
			
			
		});
	});				
	//아이디 중복검사
	$('.id_input').on("propertychange change keyup paste input", function(){

		/* console.log("keyup 테스트");	 */
		
	var memberId = $('.id_input').val();			// .id_input에 입력되는 값
	var data = {memberId : memberId}				// '컨트롤에 넘길 데이터 이름' : '데이터(.id_input에 입력되는 값)'
	
	$.ajax({
		type : "post",
		url : "/member/memberIdChk",
		data : data,
		success : function(result){				//    - success 메서드에 먼저 controller로부터 데이터가 정상적으로 넘어오는지 확인하기 위해서 console.log()를 작성함.
			/* console.log("성공 여부 : " + result); */
			
			if(result != 'fail'){
				$('.id_input_re_1').css("display","inline-block");
				$('.id_input_re_2').css("display","none");
				idckCheck = true;
			} else {
				$('.id_input_re_2').css("display","inline-block");
				$('.id_input_re_1').css("display","none");
				idckCheck = false;
			}
		}
	}); // ajax 종료
		
	}); // function 종료
	
	
	 
	/* 인증번호 이메일 전송 */
	$(".mail_check_button").click(function(){
	    
	    var email = $(".mail_input").val();            // 입력한 이메일
	    var cehckBox = $(".mail_check_input");        // 인증번호 입력란
	    var boxWrap = $(".mail_check_input_box");    // 인증번호 입력란 박스
	    
	    $.ajax({
	        
	        type:"GET",
	        url:"mailCheck?email=" + email,
	        success:function(data){
	            
	            //console.log("data : " + data);
	            cehckBox.attr("disabled",false);
	            boxWrap.attr("id", "mail_check_input_box_true");
	            code = data;
	            
	        }
	                
	    });
	    
	});
	    
	    

	 
	/* 인증번호 비교 */
	$(".mail_check_input").blur(function(){
	    
	    var inputCode = $(".mail_check_input").val();        // 입력코드    
	    var checkResult = $("#mail_check_input_box_warn");    // 비교 결과     
	    
	    if(inputCode == code){                            // 일치할 경우
	        checkResult.html("인증번호가 일치합니다.");
	        checkResult.attr("class", "correct");        
	    } else {                                            // 일치하지 않을 경우
	        checkResult.html("인증번호를 다시 확인해주세요.");
	        checkResult.attr("class", "incorrect");
	    }    
	     
	});
	    
/* 다음 주소 연동 */

function execution_daum_address(){
	
	new daum.Postcode({
		oncomplete: function(data){	// function(data)에서 data는 팝업창에서 선택한 주소에 대한 정보를 반환받는 객체 변수임.
			// 팝업에서 검색결과 항목을 클릭했을 때 실행할 코드를 작성하는 부분임.
					
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
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
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
                // 주소변수 문자열과 참고항목 문자열 합치기
                addr += extraAddr;
            
            } else {
            	addr += ' ';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            $(".address_input_1").val(data.zonecode);
            //$("[name=memberAdder1]").val(data.zonecode);
            $(".address_input_2").val(addr);
            //$("[name=memberAdder2]").val(addr);
            // 상세주소 입력란 disabled 속성 변경 및 커서를 상세주소 필드로 이동한다.
            $(".address_input_3").attr("readonly", false);
            $(".address_input_3").focus();
		}
	}).open();		// daum.Postcode 끝 
}
	
</script>


</body>
</html>