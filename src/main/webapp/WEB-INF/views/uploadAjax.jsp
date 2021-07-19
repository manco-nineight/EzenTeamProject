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
<style type="text/css">
   .fileDrop{
      width: 100%;
      height: 250px;
      border: 1px solid red;
      
     margin: auto;
   }
   
   .ellpsisTarget{
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
   }


</style>

</head>
<body>

<div class="fileDrop"></div>
<div class="uploadedList"></div>




<script type="text/javascript" src="/resources/js/test.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
	   
	   $(".uploadedList").on("click", "small.delFile", function() {
	         var that = $(this);
	         
	         $.ajax({
	            type : 'post',
	            url : "/deleteFile",
	            data : {
	               filename : that.attr("data-filename")
	            },
	            dataType : 'text',
	            success : function(result) {
	               alert(result);
	               that.parent("p").parent("div").remove();
	            }
	         });
	      });
	   
	   
	   
	   
      $(".fileDrop").on("dragenter dragover", function(event) {
         event.preventDefault();
      });
      
      
      $(".fileDrop").on("drop", function(event) {
         event.preventDefault();
         
         var files = event.originalEvent.dataTransfer.files;
         var file = files[0];
         
         var formData = new FormData();
         formData.append("file", file);
         
         $.ajax({
            type: 'post',
            url : 'uploadAjax',
            processData : false,
            contentType : false,
            data: formData,
            dataType: 'text',
            success : function(filename) {
               var msg = uploadUpdateForm(getLinkText(filename), 
                                 filename, 
                                 getOriginalName(filename));
               
               $(".uploadedList").append(msg);
            }
         });
         
      });
   });









</script>

</body>
</html>