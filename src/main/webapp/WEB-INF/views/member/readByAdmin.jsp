<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="/resources/img/mycss.css" rel="stylesheet" type="text/css" />

<style type="text/css">
input {
	width: 400px;
}
</style>

</head>


<body>
			     <jsp:include page="../main_menu.jsp"/>
</body>

<body style="text-align: center;margin: auto;">
 <!--  -->
						<!-- 회원정보 영역 시작 -->
					<span class="glyphicon glyphicon-user
" aria-hidden="true" style="font-weight: bold; font-size: 20px;"></span>
						<span style="font-weight: bold; font-size: 30px;">회원 관리 모드</span><br>
						<span style="color: green;">관리모드에서 회원등급 조정 및 강퇴처리가 가능합니다.</span>
						
						<form action="" id="readForm" >
						<h3 style="color: gray;">ID</h3><br>
						<input type="text" id="userId" name="userId" readonly value="${dto.userId}" disabled="disabled"><br>
						
						
						<h3 style="color: gray;">PW</h3><br>
						<input type="text" id="userPw" name="userPw" readonly value="${dto.userPw}" disabled="disabled"><br>
						
						<h3 style="color: gray;">이름</h3><br>
						<input type="text" id="userName" name="userName" readonly value="${dto.userName}" disabled="disabled"><br>
						
						
						<h3 style="color: gray;"><span class="glyphicon glyphicon-envelope
" aria-hidden="true" style="font-size: 20px;"></span>이메일</h3><br>
						<input disabled="disabled" type="email" id="userEmail" name="userEmail" readonly value="${dto.userEmail}"><br>
						
						
						<h3 style="color: gray;"><span class="glyphicon glyphicon-calendar
" aria-hidden="true" style="font-size: 20px;"></span>생년월일</h3><br>
						<input type="date" id="userBirthday" name="userBirthday" readonly value="${dto.userBirthday}" disabled="disabled"><br>
						
						
						
						<!--  -->
						<h3 style="color: gray;"><span class="glyphicon glyphicon-home
" aria-hidden="true" style="font-size: 20px;"></span>주소</h3><br>
						<input type="text" id="userAddress" name="userAddress" readonly  value="${dto.userAddress}" disabled="disabled"><br>
						
						<!--  --> 
					<!-- <h3>주소</h3>
					<input type="text" id="sample4_postcode"  name="sample4_postcode" placeholder="우편번호" readonly required>
                    <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
                    <input type="text" id="sample4_roadAddress"  name="sample4_roadAddress" placeholder="도로명주소" readonly required>
                    <input type="text" id="sample4_detailAddress"  name="sample4_detailAddress" placeholder="상세주소" required><br> -->
                   <!--  컨트롤러에서 DTO 작업시 adress 값은   우편번호 + 도로명주소+  상세주소 = useradress 로 처리 -->
						
						<h3 style="color: gray;"><span class="glyphicon glyphicon-time
" aria-hidden="true" style="font-size: 20px;"></span>회원가입 날짜</h3><br>
						<input type="text" id="userRegdate" name="userRegdate" readonly value="${dto.userRegdate}" disabled="disabled"><br>
						
						
						<h3><span class="glyphicon glyphicon-lock
" aria-hidden="true" style="font-size: 20px;"></span>회원등급</h3><br>
						<input type="text" id="userGradeOro" name="userGradeOri" readonly value="${dto.userGrade eq 0? '사용자':'관리자'}"><br><br>
						 <!--  -->
					 <br> <br> <br>
					회원등급&nbsp;
					 <select name="userGrade" id="userGrade" class="form-control" style="width: 400px; text-align: center; margin: auto;">


						<option value="0">사용자</option>
						<option value="1">관리자</option>

					</select><br><br>
					<!--  -->
					
						
						
						<a id="updateGrade" class="btn btn-warning">회원 등급 수정</a>
						<a id="deleteMember" class="btn btn-danger">회원탈퇴</a>
						</form>



<script type="text/javascript">
$(document).ready(function() {
	
	
	
	
	$("#updateGrade").click(function(){
		var userId = $("#userId").val();
		
		
		var userGrade = $("#userGrade").val();
		
		$.ajax({
		     type : 'post',
		     url : '/member/updateGrade',
		     data : {
		    	 userId :userId,
		    	
		    	 userGrade : userGrade
		    	 
		    	 
		    	 
		    	 
		     },
		     dataType : 'text',
		     
		     success : function() {
				alert("등급 수정 완료");
				location.href='/member/list' ;
			}
		     
			
			
			
		});
		
		
		
		
	});
	
	
	
	$("#deleteMember").click(function(){
		var userId = $("#userId").val();
		
		$.ajax({
		     type : 'post',
		     url : '/member/deleteMember',
		     data : {
		    	 userId :userId
		     },
		     dataType : 'text',
		     
		     success : function() {
				alert("회원 탈퇴 완료");
				
				location.href='/member/list' ;
				
			}
		     
			
			
			
		});
		
		
		
		
	});
	
	
	
	
	
	
	$("#pw_check_result").click(function(event){
		event.preventDefault();
		
		var inputCode = $("#loginuserPw").val();        // 입력코드    
	    var checkResult = $("#pwcheck").val();    // 비교 결과
	    
	    if(inputCode == checkResult){                            // 일치할 경우
	        alert("인증되었습니다.");     
	       /*  $('form[id="readForm"]').is("hidden") == false; */
	        $('form[id="readForm"]').attr("hidden", false); //성공시 감춰둔 정보창을 보여줌
	        
	        $('form[id="checkPwForm"]').attr("hidden", true);
	        
	        
	    } else {                                            // 일치하지 않을 경우
	    	alert("비밀번호가 일치하지 않습니다.");
	    }   
	    
	    
	});
});
</script>

	<!-- 
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

              
            }
        }).open();
    }
</script> -->



</body>
</html>