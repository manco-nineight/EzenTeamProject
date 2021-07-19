<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="/resources/js/test.js" type="text/javascript"></script>

<style type="text/css">
   .fileDrop{
      width: 100%;
      height: 250px;
      border: 1px solid red;
      margin: 20px 0;
   }
   
   .ellpsisTarget{
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
   }   

</style>
</head>
<body>

<div class="container">
   <div class="row">
      <h1 class="jumbotron">게시글 수정</h1>
      
   </div>
   
   <div class="row">
      <form action="/board/update/${vo.bno}?curPage=${curPage}" method="post">
         <div class="form-group">
             <label for="writer">writer</label>
             <input readonly value="${vo.writer}" class="form-control" type="text" id="writer" name="writer">
         </div>
         <div class="form-group">
            <label for="title">title</label>
            <input value="${vo.title}" type="text" class="form-control" id="title" name="title">
         </div>
         <div class="form-group">
            <label for="content">content</label>
            <textarea rows="10" class="form-control" id="content" name="content">${vo.content}</textarea>
         </div>
        
        
         <button type="submit" class="btn btn-danger update_btn_update">게시글 수정</button>
      </form>
   </div>
   
   <div class="row">
      <div class="fileDrop">
         파일을 드래그하여 드랍하세요.
      </div>
      
      <div class="uploadedList"></div>
   
   </div><!--class = row end tag  -->
   
   <script type="text/javascript">
      $(document).ready(function() {
         var bno   = ${vo.bno};
         
         
         
         //게시글 수정 버튼 클릭
         $(".update_btn_update").click(function(event) {
        	 event.preventDefault();
        	 var str = '';
        	 
        	 $(".delFile").each(function(index) {
				var filename =  $(this).attr("data-filename");
				//업로드하기 위해 파일값과 인덱스를 넣어줌
				str += getFileUploadFilenameInput(filename, index);
        		 
        		 
			});
        	 
        	 $("form").append(str);
        	 $("form").submit();
			
		});
         
         
         $(".fileDrop").on("dragenter dragover", function(event) {
             event.preventDefault();
          });
          
         //업데이트 페이지 파일 영역에 파일을 드랍한 경우
          $(".fileDrop").on("drop", function(event) {
             event.preventDefault();
             
             //파일은 기본기능에서 캐치한 파일 값
             var files = event.originalEvent.dataTransfer.files
             //그 중에 첫번째
             var file = files[0];
             
             //에이작스에 파일을 전송하기 위한 폼데이터에 파일 값을 입력
             var formData = new FormData();
             formData.append("file", file);
             
             $.ajax({
                type : 'post',
                url : '/board/uploadfile',
                processData : false,
                contentType : false,
                data : formData,
                dataType : 'text',
                success : function(filename) {
                	
                	
                   var msg = uploadUpdateForm(getLinkText(filename), filename, getOriginalName(filename));
                   $(".uploadedList").append(msg);
                   
                }
             });
             
          });
         
         
         $(".uploadedList").on("click",".delFile",function(event){
        	 
        	 event.preventDefault();
        	 var that = $(this);
        	 var filename = that.attr("data-filename");
        	 var delOk = confirm("[게시글 수정] 버튼과 상관없이 파일은 삭제됩니다.\n 삭제하시겠습니까?")
        	 if(!delOk){
        		 return;
        	 }
        	 
        	 
        	 $.ajax({
        		type : 'post',
        		url : '/board/deleteFile',
        		data : {
        			filename : filename
        		},
        		dataType : 'text',
        		success : function(result) {
					that.parent("p").parent("div").remove();
				}
        		 
        		 
        	 });
        	 
         });
         
         
         
         
         
         
         
         getAttach(bno);
      });
      
      
      
      
      
      
      
      function getAttach(bno){
         $.getJSON("/board/getAttach/"+bno, function(result) {
            for(var i=0;i<result.length;i++){
               var filename = result[i];
               
               var msg = uploadUpdateForm(getLinkText(filename), filename, getOriginalName(filename));
               $(".uploadedList").append(msg);
            }
         });
      }
      
      
      
      
   
   
   </script>
   
</div>
</body>
</html>