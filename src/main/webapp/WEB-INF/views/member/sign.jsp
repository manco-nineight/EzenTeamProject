<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link href="../resources/img/mycss.css" rel="stylesheet" type="text/css" />
<style>

#mail_check_input_box_false{
    background-color:#ebebe4;
} 
#mail_check_input_box_true{
    background-color:white;
}
.correct{
    color : green;
}
.incorrect{
    color : red;
}
</style>



</head>
<body style="text-align: center;margin: auto;">
<jsp:include page="../main_menu.jsp"/>


	<script type="text/javascript">
		alert("회원 가입 화면");
	</script>

	<h5 style="color: black; font-weight: bold;">
		SIGN
		</h5>
		<br><br><br>


       <div class="container">

			<div id="content" >
				<div>

		<form action="/member/sign" method="post" style="text-align: left; margin:auto; padding-left: 200px;">
					
					
					<span style="font-weight: bold;">아이디</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="text" id="userId" class="underline" maxlength="20" name="userId"
						style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" required placeholder="사용할 ID를 입력해 주세요">

					
					<button  type="button" class="btn btn-default" id="idCheckButton">아이디 중복확인</button>
					
					아이디 중복체크 여부 <input type="checkbox" required onclick="return false;"  id="idCheckBox"><br>
                        <!--  -->
					<br><br>
					
					
					
					비밀번호&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						type="password" class="underline" maxlength="20" name="userPw" id="userPw"
						style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" required placeholder="비밀번호를 입력해 주세요"> <br> 
						
						<br>
					비밀번호 확인&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="password" class="underline" maxlength="20" name="pwcheck" id="pwcheck" style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" required placeholder="비밀번호 확인을 위해 같은 내용을 다시 입력해 주세요"> 
						
						
						
						
						
					
                      <a id="pw_check_result" class="btn btn-default"  >비밀번호확인</a>
                   
                     비밀번호 일치 체크  <input type="checkbox" required onclick="return false;"  id="pwCheckBox">
                      
                      <div class="clearfix"></div>
                      <span id="pw_check_input_box_warn"></span>	
						
						<br><br>
						
						<!--  -->
					
					이름&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input
						type="text" class="underline" maxlength="20" name="userName" id="userName"
						style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" required placeholder="회원님의 이름을 입력해 주세요."> <br>
					<br><br>
					   <!--  -->
					
					생년월일&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input
						type="date" class="underline" maxlength="20" name="userBirthday" id="userBirthday"
						style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" required> <br>
			<br><br>
					    <!--  -->
					<!--  --> 
					주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input style="border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" type="text" id="sample4_postcode"  name="sample4_postcode" placeholder="우편번호" readonly required>
                    <input type="button" onclick="sample4_execDaumPostcode()"  class="btn btn-default" value="우편번호 찾기"><br>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input style="border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" type="text" id="sample4_roadAddress"  name="sample4_roadAddress" placeholder="도로명주소" readonly required>
                    <input style="border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" type="text" id="sample4_detailAddress"  name="sample4_detailAddress" placeholder="상세주소" required><br>
                   <!--  컨트롤러에서 DTO 작업시 adress 값은   우편번호 + 도로명주소+  상세주소 = useradress 로 처리 -->
                    
              
                 <br>
                 <br>
                 
                 
                 
                 
                 
                 
                 
                 
                 <div class="mail_wrap">
                  
                   <div class="mail_input_box">
                   e-mail &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <input type="text" class="mail_input" name="userEmail" id="userEmail" style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" placeholder="본인확인을 위한 이메일을 입력해 주세요.">
                      <button class="mail_check_button btn btn-default" >인증 번호 전송</button>
                   </div>
                   <div class="mail_check_wrap">
                      <div class="mail_check_input_box" id="mail_check_input_box_false">
                   인증번호 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;      <input class="mail_check_input" disabled="disabled" style="width: 400px;border-left-width:0; border-right-width:0; border-top-width:0;border-bottom-width:1;" placeholder="이메일로 전송된 본인확인 코드를 입력해주세요.">
                      <a id="email_check_result" class="btn btn-default disabled" >인증 일치 확인</a>
                      E-mail 인증 완료 여부 <input type="checkbox" required onclick="return false;"  id="emailCheckBox">
                      </div>
                      
                     
                      
                      
                      <!-- <div class="mail_check_result_box" id="mail_check_result_box_false"> -->
                     <!--  </div> -->
                      
                      <div class="clearfix"></div>
                      <span id="mail_check_input_box_warn"></span>
                      <br>
                      
                   </div>
                 </div>
					
					 <!--  -->
					 <br> <br> <br>
					회원등급&nbsp;
					 <select name="userGrade" id="userGrade" class="form-control" style="width: 400px; ">


						<option value="0">사용자</option>
						<option value="1">관리자</option>

					</select><br><br>
					<!--  -->
					
					
					
					
					
					
					
					
					
					
					
					<input style="width: 400px;" class="btn btn-default" type="submit" value="회원 등록"><br>
					
		</form>
		
				</div>
			</div>

</div>
		
		<br>
		
		

		<script type="text/javascript">
			$(document).ready(function() {
				
				
				
				
				var code ="";
				
				$("form").submit(function() {
					
					alert("회원가입 완료");
					
				});
				
				
				$("#pw_check_result").click(function(){
					
					var inputCode = $("#userPw").val();        // 입력코드    
				    var checkResult = $("#pwcheck").val();    // 비교 결과
				    
				    if(inputCode == checkResult){                            // 일치할 경우
				        alert("성공");     
				        $('input:checkbox[id="pwCheckBox"]').is(":checked") == true;
				        $('input:checkbox[id="pwCheckBox"]').attr("checked", true);
				    } else {                                            // 일치하지 않을 경우
				    	alert("실패");
				    }   
				    
				    
				});
				
				
				
				$("#email_check_result").click(function(){
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
				
				
				 $(".mail_check_input").blur(function(){
					var inputCode = $(".mail_check_input").val();        // 입력코드    
				    var checkResult = $("#mail_check_input_box_warn");    // 비교 결과
				    
				    
				    if(inputCode == code){                            // 일치할 경우
				        checkResult.html("인증번호가 일치합니다.");
				        checkResult.attr("class", "correct"); 
				        $('input:checkbox[id="emailCheckBox"]').is(":checked") == true;
				        $('input:checkbox[id="emailCheckBox"]').attr("checked", true);

				       

				       
				    } else {                                            // 일치하지 않을 경우
				        checkResult.html("인증번호를 다시 확인해주세요.");
				        checkResult.attr("class", "incorrect");
				    }   
				    
				    
				}); 
				
				
				$(".mail_check_button").click(function(event){
					
					event.preventDefault
				    
				    var email = $(".mail_input").val();        // 입력한 이메일
				    var cehckBox = $(".mail_check_input");        // 인증번호 입력란
				    var boxWrap = $(".mail_check_input_box");    // 인증번호 입력란 박스
				    
				   
				    
				    
				    $.ajax({
				        
				        type:"GET",
				        url:"mailCheck?email=" + email,
				        async: false,
				        success:function(num){
				        	alert("인증 번호가 전송되었습니다.")
				        	
				        	cehckBox.attr("disabled",false); //입력불가창 해제
				        	boxWrap.attr("id", "mail_check_input_box_true");
				        	$('#email_check_result').attr('class','btn btn-info'); //입력불가창 해제
				       


				        	
				        	
				            code = num;
				        }
				        		
				        
				                
				    });
				    
				});
				
				
				$("button#idCheckButton").click(function(event) {
					
					event.preventDefault();

					var userId = $("input[name='userId']").val();

					$.ajax({
						type : "get",
						url : "userIdCheck?userId=" + userId,
						async: false,
						
						success : function(ckrs) {
							/* $("#idCheckButton").val(result); */
							console.log(ckrs);
							if ($.trim(ckrs) == null || $.trim(ckrs) == ""){
								alert("가용한 아이디입니다");
							 $('input:checkbox[id="idCheckBox"]').is(":checked") == true;
						        $('input:checkbox[id="idCheckBox"]').attr("checked", true);
							
							

							}else{
								alert("중복된 아이디입니다");
							}
						}
					});
				});
			});
		</script>
		
		
		
		
		
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    
    //주소 adress값은 DB에 varckar 200의 문자 값으로 해당
    //주소값은 도로명 +상패주소 str()임시
    
    
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                
                //우편주소 값을 공백 칸에 삽입
                document.getElementById('sample4_postcode').value = data.zonecode;
                //도로명 주소 값을 공백 칸에 삽입
                document.getElementById("sample4_roadAddress").value = roadAddr;
               /*  //지번 주소 값을 공백 칸에 삽입
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress; */
                
            
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }
                
                /*  */
            
                /*  */

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>
		
		
		
		
		
		
		
		
</body>
</html>




