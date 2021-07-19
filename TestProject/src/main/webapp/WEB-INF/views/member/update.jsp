<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	
	<div class="container">
		<div class="row">
			<h1 class="jumbotron">${vo.userId} 회원 정보 수정</h1>
		</div>
		
		<div class="row">
			<form id="form_update" action="/member/update/${vo.userId}?curPage=${curPage}" method="post">
				<div class="form-group">
					<label for="userPw">새 비밀번호</label>
					<input type="password" class="form-control" id="userPw" name="userPw" maxlength="60">
				</div>
				
				<div class="form-group">
					<label for="userPw2">비밀번호 재확인</label>
					<input type="password" class="form-control" id="userPw2" name="userPw2" maxlength="60">
				</div>
			
				<div class="form-group">
					<label for="userName">이름</label>
					<input type="text" class="form-control" id="userName" name="userName" value="${vo.userName}" maxlength="25">
					<span class="hide userName_bytes">0</span>
				</div>
				
				<div class="form-group">
					<div class="userEmail_wrap">
						<label for="userEmail">이메일</label>
						<input type="email" class="form-control" id="userEmail" name="userEmail" value="${vo.userEmail}" maxlength="55" readonly>
					</div>
					<div class="userEmail_check_wrap">
						<input type="text" id="userEmail_check_input" class="form-control" disabled>
						<span id="userEmail_check_msg" class="userEmail_check_success"></span>
					</div>
					<button type="button" class="btn btn-primary hide" id="userEmail_send_btn">인증번호 전송</button>
					<button type="button" class="btn btn-info hide" id="userEmail_check_btn">인증 확인</button>
					<button type="button" class="btn btn-danger" id="userEmail_reset_btn">인증 초기화</button>
				</div>
				
				<div class="form-group">
					<label for="userBirthday">생일</label>
					<input type="date" class="form-control" id="userBirthday" name="userBirthday" value="${vo.userBirthday}">
				</div>
				
				<div class="form-group" id="old_userAddress1">
					<label for="old_userAddress">현재 등록된 주소</label>
					<input type="text" class="form-control" id="userAddress" name="userAddress" value="${vo.userAddress}" readonly> <br>
					<button type="button" id="new_userAddress_btn">새 주소 입력</button>
				</div>
				
				<div class="form-group hide" id="new_userAddress1">
						<label for="new_userAddress">새 주소</label> <br>
						<input type="text" id="postcode" placeholder="우편번호" readonly>
						<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"> <br>
						<input type="text" class="form-control" id="address" placeholder="주소" readonly> <br>
						<input type="text" class="form-control" id="detailAddress" placeholder="상세주소" maxlength="60">
				</div>
				
			</form>
		</div>	<!-- class = row end tag -->
		
		<button type="submit" class="btn btn-success member_update_btn">수정</button>
	</div>
	
	<script type="text/javascript">	
		$(document).ready(function() {
			
			// 새 주소 입력 버튼 클릭 이벤트
			$("#new_userAddress_btn").click(function() {
				$("#old_userAddress1").attr("class", "hide");
				$("#new_userAddress1").removeClass("hide");
			});
			
			
			// 인증번호 버튼 클릭 시 이메일 인증번호 전송
			var code = "";
			
			$("#userEmail_send_btn").click(function() {	
				var userEmail = $("#userEmail").val();
				
				var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
				
				if (userEmail == '' || !emailRegExp.test(userEmail)) {
					alert('올바른 이메일 형식으로 입력하세요.');
					event.preventDefault();
					
				} else {
					$.ajax({
						type : 'get',
						url : "/member/emailcheck?userEmail=" + userEmail,
						success : function(data) {
							//console.log("data : " + data)
							$("#userEmail_check_input").attr("disabled", false);
							code = data;
							alert("이메일을 확인해주세요.");
							$("#userEmail").attr("readonly", true);
							$("#userEmail_check_btn").removeClass("hide");
							$('#userEmail_send_btn').text("인증번호 재전송");
						}
					});
				}
			});
			

			// 이메일 인증번호 확인
			$("#userEmail_check_btn").click(function() {
				var inputCode = $("#userEmail_check_input").val();
				
				if (inputCode == code) {
					$("#userEmail_check_msg").attr("style", "color: blue;");
					$("#userEmail_check_msg").attr("class", "userEmail_check_success");
					
					alert("인증되었습니다.");
					
					$("#userEmail_check_msg").text("인증되었습니다.");
					$("#userEmail_check_input").attr("disabled", true);
					$("#userEmail_check_btn").addClass("hide");
					
					$("#userEmail_send_btn").addClass("hide");
					$("#userEmail_reset_btn").removeClass("hide");
					
				} else {
					$("#userEmail_check_msg").attr("style", "color: red;");
					$("#userEmail_check_msg").attr("class", "userEmail_check_fail");
					alert("인증번호를 다시 확인해주세요.");
					$("#userEmail_check_msg").text("인증번호를 다시 확인해주세요.");
				}
			});
			
			
			// 이메일 재인증
			$("#userEmail_reset_btn").click(function() {
				var msg = confirm("이메일을 변경하시겠습니까?");
				
				if (msg) {
					$("#userEmail").val('');
					$("#userEmail_check_input").val('');
					$("#userEmail").attr("readonly", false);
					$("#userEmail_check_msg").text("");
					$("#userEmail_check_msg").attr("class", "userEmail_check_fail");
					$('#userEmail_send_btn').text("인증번호 전송");
					$("#userEmail_send_btn").removeClass("hide");
					$("#userEmail_reset_btn").addClass("hide");
				} else {
					event.preventDefault();
				}
			});
			
			
			// 이름 bytes 구하기 (한/영 혼용 시 글자 수 바이트 초과 방지)
			$('#userName').keyup(function(){
				var textByte = $("#userName").val();
				var len = 0;	// 글자 바이트

				for (var i = 0; i < textByte.length; i++) {
					if (escape(textByte.charAt(i)).length == 6) {
						len+=2;	// 오라클 UTF-8 한글 1글자당 3byte
					}
					len++;	// 영문 1byte
				}		
				$('span.userName_bytes').text(len);
			});

			// 회원가입 버튼 클릭 이벤트
			$(".member_update_btn").click(function() {
				event.preventDefault();
				
				var msg = confirm("회원 정보를 수정 하시겠습니까?");
				
				if (msg) {
					checkInfo();
				} else {
					event.preventDefault();
				}
			});

			
		}); // (document).ready 종료 구간

		// 비밀번호, 이름 정규표현식 유효성 검사 변수
		var pwRegExp = /^[a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]{4,50}$/;
		
		// 한글, 영어 띄어쓰기 가능(\s)
		var nameRegExp = /^[가-힣a-zA-Z\s]{1,25}$/;
		
		// 이메일 정규표현식 유효성 검사
		var emailRegExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

		
		// 유효성 검사 함수
		var checkInfo = function() {
			if ($("#userPw").val() == '' || !pwRegExp.test($("#userPw").val())) {
				alert('비밀번호는 최소 4자 이상 입력하세요.');
				event.preventDefault();

			} else if ($("#userPw").val() != $("#userPw2").val()) {
				alert('비밀번호 혹은 재확인 부분이 서로 같지 않습니다');
				event.preventDefault();

			} else if ($("#userName").val() == '' || !nameRegExp.test($("#userName").val())) {
				alert('이름은 띄어쓰기 포함하여 \n한글 또는 영문으로 입력하세요.');
				event.preventDefault();
				
			} else if ($("span.userName_bytes").text() > 25) {
				alert('이름은 총 25Byte 이내로 입력해야 합니다. \n(글자당 영문 1Byte, 한글 3Bytes)');
				// 오라클 utf-8 한글 글자당 3byte
				event.preventDefault();
				
			} else if ($("#userEmail").val() == '' || !emailRegExp.test($("#userEmail").val())) {
				alert('올바른 이메일 형식으로 입력하세요.');
				event.preventDefault();
				
			} else if ($("#userEmail_check_msg").hasClass("userEmail_check_fail") == true) {
				alert('이메일 인증을 다시 확인해주세요');
				event.preventDefault();
				
			} else if ($("#userBirthday").val() == '') {
				alert('생일을 입력하세요.');
				event.preventDefault();
				
			} else if ($("#old_userAddress1").hasClass("hide") == true && $("#address").val() == '') {
				alert('새 주소를 입력하세요.');
				event.preventDefault();
				
			} else if ($("#old_userAddress1").hasClass("hide") == true) {
				$("#userAddress").val("(" + $("#postcode").val() + ") " + $("#address").val() + " " + $("#detailAddress").val());
				$("#form_update").submit();
				alert('회원 수정을 완료하였습니다.');
				
			} else {
				$("#form_update").submit();
				alert('회원 수정을 완료하였습니다.');
			}
			
		}; // checkInfo() 종료
		
		
		// 다음 주소 API
		function execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
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
	                    // 조합된 참고항목을 해당 필드에 넣는다.

	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById('address').value = addr + extraAddr;

	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById('detailAddress').focus();
	            }
	        }).open();
	    } // 주소 API 종료

	</script>
	
</body>
</html>