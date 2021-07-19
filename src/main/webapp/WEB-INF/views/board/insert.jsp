<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/test.js"></script>
</head>
<style type="text/css">
.fileDrop {
	width: 100%;
	height: 250px;
	border: 1px solid red;
	margin: 20px 0;
}

.ellpsisTarget {
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}
</style>

<body>

	<div class="container">
		<div class="row">
			<h1 class="jumbotron">게시글 작성</h1>

		</div>

		<div class="row">
			<form action="/board/insert" method="post">
				<div class="form-group">
					<label for="writer">writer</label> <input readonly value="${login.userId}" class="form-control"
						type="text" id="writer" name="writer" >
				</div>
				<div class="form-group">
					<label for="title">title</label> <input type="text"
						class="form-control" id="title" name="title">
				</div>
				<div class="form-group">
					<label for="content">content</label>
					<textarea rows="10" class="form-control" id="content"
						name="content"></textarea>
				</div>


				<button type="submit" class="btn btn-default">게시글 입력</button>
				<button type="reset" class="btn info">뒤로가기</button>
			</form>
		</div>
		
		<!-- ------------------------------------------------------------------------------------------------ -->
		<!--  class = row end-->
		<div class="row">
			<div class="fileDrop">파일을 드래그 드랍 하세여</div>
			<div class="uploadedList"></div>


		</div>
        <!-- ------------------------------------------------------------------------------------------------ -->
	</div>
	<!--class container end -->

	<script type="text/javascript">
$(document).ready(function() {
	
	
	window.onpageshow = function(event) {
		alert(222);
	}
   
    
    //5리셋 버튼 클릭 이벤트
	$("button[type='reset']").click(function(evnet) {
		event.preventDefault();
		//각각의 delFile 클래스를 가진 곳에 적용
		$(".delFile").each(function(index) {
			//변수  filename 은 각자 delFile 내에 있는  data-filename
			var filename = $(this).attr("data-filename");
			 $.ajax({
		            type : 'post',
		            url : "/board/deleteFile",
		            data : {
		               filename : filename
		            },
		            dataType : 'text',
		            /* success : function(result) {
		               alert(result);
		               that.parent("p").parent("div").remove();
		            } */
		         });
		});
		
		
	
		history.back();
		
		
	});
	
	//4제출 버튼 클릭
	$("button[type='submit']").click(function(event) {
		event.preventDefault();
		
		//공백 상태의 변수 선언
		var str = '';
		//각각의 delFile 클래스를 가진 곳에 적용
		$(".delFile").each(function(index) {
			//변수  filename 은 각자 delFile 내에 있는  data-filename
			var filename = $(this).attr("data-filename");
			//filename, index를 넣어서 나오는 input객체를 반복작업 삽입
			str += getFileUploadFilenameInput(filename, index);
			
			
		});
		//폼 안에 input 객체를 넣어줌
		$("form").append(str);
		//제출함
		$("form").submit();
		
		
	});
	
	
	//3uploadedList 안에 small 객체 클릭
	 $(".uploadedList").on("click", "small.delFile", function() {
		 //small 객체를 지속적으로 특정 지을 수 있게 객체로 내용 지정
		 var that = $(this);
         
         $.ajax({
            type : 'post',
            //DB해당 값을 지움
            url : "/board/deleteFile",
            data : {
               filename : that.attr("data-filename")
            },
            dataType : 'text',
            success : function(result) {
               alert(result);
               //jsp 객체도 지음(포함된 div 전체를 같이 지움)
               that.parent("p").parent("div").remove();
            }
         });
      });
	
	
	/*  */
	
	//1드래그 드랍 기본 기능을 잠금 ;
	$(".fileDrop").on("dragenter dragover",function(event){
		event.preventDefault();
		
	
		
	});
	
	
	//2파일 드랍 클래스에 드랍 이벤트가 발생하면
	$(".fileDrop").on("drop",function(event){
		//일단 기본 기능을 막고
		event.preventDefault();
		//event.originalEvent 원래 기본기능이 작동할시 받는 파일 객체
		//dataTransfer 객체값을 사용할 수 있도록 전환
		var files = event.originalEvent.dataTransfer.files;
		//받은 파일 객체들 중 1번째 객체
		var file = files[0];
		
		//formData,ajax로 폼을 전송하기 위한 객체 (주로 이미지 전송에 쓰인다고 한다.)
		var formData = new FormData();
		formData.append("file",file);
		
		
		$.ajax({
			type : 'post',
			url : '/board/uploadfile',
			processData : false,
			contentType : false,
			data : formData,
			dataType : 'text',
			success : function(filename) {
				//uploadUpdateForm //getLinkText(썸네일 표시를 뺀 파일 이름 얻기) 
				//getOriginalName앞부분 짜르고 원래 파일 이름 얻기
				//받은 값들로 올라온 파일이 이미지인지 다른파일인지 확인하고 이미지면 해당 이미지로 만든 썸네일
				//아니면 기본적으로 셋팅된 썸네일을 출력홤
				var msg = uploadUpdateForm(getLinkText(filename), filename, getOriginalName(filename));
				
				//uploadUpdateForm 결과를 uploadedList클래스에 값으로 추가
				$(".uploadedList").append(msg);
			}
			
		
		
		});
		
		
	});
	
});




</script>




</body>
</html>