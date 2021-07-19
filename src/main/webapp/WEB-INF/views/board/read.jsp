<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 자세히 보기</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
   <div class="row">
      <h1 class="jumbotron">글 자세히 보기 </h1>
      
      
      
   </div>
   
   <div class="row">
      <div class="panel panel-success">
         <div class="panel-heading">
            <span>No.${vo.bno}</span> 
            <span><span class="glyphicon glyphicon-user" aria-hidden="true"></span>${vo.writer}</span> 
            <span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${vo.updatedate}</span><br>
            <span class="pull-right"><span class="glyphicon glyphicon-sunglasses" aria-hidden="true"></span>조회수 ${vo.readcnt}</span>
          
          
            <h3>${vo.title }</h3>
            
         </div>
         <div class="panel-body" >
            <textarea class="form-control" readonly style="width: 100%;height: 400px">${vo.content}</textarea>
             <div class="uploadedList">
              
             </div>
       
         </div>
      
         <div class="panel-footer text-center">
            <a class="btn btn-warning update ${vo.writer != login.userId?'disabled':''}">수정</a> 
            <a class="btn btn-danger delete ${vo.writer != login.userId?'disabled':''}">삭제</a> 
            <a class="btn btn-primary reply" ${empty login.userId? 'disabled':''}">댓글</a> 
            <a class="btn btn-info list">목록</a>
         </div>
      </div>
   
   </div>
   
   <form action="/board/delete/${vo.bno}?curPage=${curPage}" method="post"></form>
   
   <div class="row">
      <div class="collapse" id="myCollapse">
         <div class="well">
             <div class="form-group">
                <label for="replyer">작성자</label>
                <input readonly value="${login.userId}" id="replyer" class="form-control">
             </div>
             
             <div class="form-group">
                <label for="replytext">내용</label>
                <input id="replytext" class="form-control">
             </div>
             
             <div class="form-group">
                <button class="btn btn-success reply_insert_btn">댓글 등록</button>
             </div>
         </div>
      </div>   
   
   </div>
   
   <div id="repliesList" class="row">
      
   </div>
   
   
   
  <div class="row">
   
   
   
   <!-- Modal -->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
     <div class="modal-dialog" role="document">
       <div class="modal-content">
         <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
           <h4 class="modal-title" id="modal_rno"></h4>
         </div>
         <div class="modal-body">
        <div class="form-group">
        <label for="replyer_update">작성자</label>
        <input id="replyer_update" value="홍길동" class="form-control">
       
        </div>
         <div class="form-group">
        <label for="replytext_update">댓글내용</label>
        <input id="replytext_update" value="데데데데데데데" class="form-control">
       
        </div>
         </div>
         <div class="modal-footer">
           <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
           <button id="reply_update_btn" type="button" class="btn btn-primary" data-dismiss="modal">댓글 수정 완료</button>
         </div>
       </div>
     </div>
   </div>
   
   
   

   </div>
  
   
   
   
   
   

<script type="text/javascript" src="/resources/js/test.js"></script>   

<script type="text/javascript">
   $(document).ready(function() {
      var bno = ${vo.bno};
      
      
      $("#reply_update_btn").click(function(event) {
          event.preventDefault();
          var rno = $("#modal_rno").text();
          var replyer = $("#replyer_update").val();
          var replytext = $("#replytext_update").val();
          
          $.ajax({
             type : "put",
             url : "/replies/reply",
             headers:{
                "Content-Type" : "application/json",
                "X-HTTP-Method-Override" : "PUT"
             },
             data : JSON.stringify({
                rno : rno,
                replyer : replyer,
                replytext : replytext
             }),
             dataType : 'text',
             success : function(result) {
            	
            	 if(result == "1"){
            		 console.log(result);
                     alert("수정되었습니다.");
                     location.reload("#repliesList");
                  }else{
                	  console.log(result);
                     alert("수정 실패");
                     
                  }
                
             }
          });
          
          
          
       });
      
      
      $("#repliesList").on("click", ".reply_btn_update_form", function(event) {
    	  event.preventDefault();
    	  var that = $(this);
    	  var rno = that.parent().attr("data-rno");
    	  var replyer = that.parent().attr("data-replyer");
    	  var replytest = that.parent().prev().text();
    	  
    	  $("#modal_rno").text(rno);
    	  $("#replyer_update").val(replyer);
    	  $("#replytext_update").val(replytest);
    	  
      });
      
      
      $("#repliesList").on("click", ".reply_btn_delete", function(event) {
         event.preventDefault();
         
         var rno = $(this).parent().attr("data-rno");
         var that = $(this);
         
         $.ajax({
            type : 'delete',
            url : '/replies/reply',
            headers:{
               'Content-Type' : 'application/json',
               'X-HTTP-Method-Override' : 'DELETE'
            },
            data : JSON.stringify({
               rno : rno
            }), 
            dataType : 'text',
            success : function(result) {
               if(result == "1"){
                  alert("삭제되었습니다.");
                  that.parent().parent().parent().remove();
               }else{
                  alert("삭제 실패");
               }
            }
         }); 
         
         
      });
      
      
      
      
      
      
      
      
      
      $(".reply_insert_btn").click(function(event) {
         event.preventDefault();
         
         $.ajax({
            type : "post",
            url : "/replies/reply",
            headers : {
               'Content-Type' : 'application/json',
               'X-HTTP-Method-Override' : 'POST'
            },
            data: JSON.stringify({
               bno : bno,
               replyer : $("#replyer").val(),
               replytext : $("#replytext").val()
            }),
            dataType : 'text',
            success : function(result) {
               alert(result);
               $("#repliesList").html("");
               getReplyList(bno);
               $("#replyer").val("");
               $("#replytext").val("");
               
               /* location.reload("#repliesList"); */
               /* $("#repliesList").load(window.location.href + "#repliesList"); */
               
            }
         });
      });
      
      
      $(".reply").click(function() {
         $("#myCollapse").toggle();
      });
      
      
      $(".delete").click(function() {
         $("form").submit();
      });
      
      $(".update").click(function() {
         location.assign("/board/update/${vo.bno}?curPage=${curPage}")
      });
      
      $(".list").click(function() {
         location.assign("/board/list/${curPage}");
      });
      
      
      
      
      getReplyList(bno);
      getAttach(bno);
      
   });
   
   
   function getReplyList(bno) {
      $.getJSON("/replies/reply/"+bno, function(data) {
         for(var i=0; i<data.length;i++){
            var obj = data[i];
            var msg = me4(obj['rno'], obj['replyer'], obj['updatedate'], obj['replytext'], '${login.userId}');
            $("#repliesList").append(msg);
         }
      });
   }
   
   
   function getAttach(bno){
	   $.getJSON("/board/getAttach/"+bno, function(result) {
		 for(var i=0;i<result.length;i++){
			 var filename = result[i];
			 var msg = uploadViewForm(getLinkText(filename), filename, getOriginalName(filename));
			 $(".uploadedList").append(msg);
		 }
	});
	   
   }


</script>




</div>
</body>
</html>