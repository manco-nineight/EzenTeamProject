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

<script src="/resources/js/product.js" type="text/javascript"></script>
<link href="/resources/star.css" type="text/css"  rel="stylesheet">
<style type="text/css" >
	
	 /*@import url("star.css");*/ 

	
</style>
 <!-- !important 우선권으로 적용 -->
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="jumbotron text-center ">REVIEW</h1>		
		</div>
	
	<a class="btn btn-primary review">리뷰 작성</a>

	<div class="row">
		<div class="collapse" id="review_reply">
	  		<div class="well">
	    		<div class="form-group">
	    			<label for="reWriter">작성자</label>
					<input  name="reWriter" id="reWriter" class="form-control">
	    		</div>
	    		
	    		<div class="form-group">
	    			<label for="reContent">내용</label>
	    			<textarea  rows="10" name="reContent" id="reContent" class="form-control"></textarea>
	    		</div>
	    		
	    		<!-- <div class="form-group">
	    			<label for="reviewRank">등급</label> 
		    			<div id="reviewRank" class="btn-group " data-toggle="buttons" style=" margin: 10px 0px;">
						  <label class="btn btn-danger active">
						    <input type="radio" name="reviewRank" id="rank1" checked > 매우나쁨
						  </label>
						  <label class="btn btn-warning">
						    <input type="radio" name="reviewRank" id="rank2" checked> 나쁨
						  </label>
						  <label class="btn btn-success">
						    <input type="radio" name="reviewRank" id="rank3" checked> 보통
						  </label>
						  <label class="btn btn-info">
						    <input type="radio" name="reviewRank" id="rank4" checked> 좋음
						  </label>
						  <label class="btn btn-primary">
						    <input type="radio" name="reviewRank" id="rank5" checked> 매우좋음
						  </label>
						</div>
	    		</div> -->
	    		
	    		<div class="form-group stars" id="reStar">
	    		
	    		</div>
	    		
	    		<div class="form-group">
	    			<button class="btn btn-primary review_insert_btn ">리뷰 등록</button>
	    		</div>
	  		</div>
		</div>
	</div>	
	
	

	
	
	
	<div id="reviewList" class="row" style="margin: 10px 0px;"></div>
	<div id="reviewList_page" class="row" style="text-align: center;"></div>
	</div>

	<div class="row">
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="modal_reRno">reRno</h4>
		      </div>
		      <div class="modal-body">
		      
		      <div class="form-group">
							<label for="reWriter_update">reWriter</label>
							<input id="reWriter_update" name="reWriter" class="form-cotrol">
						</div>
		        <div class="form-group">
							<label for="reContent_update">reContent</label>
							<textarea id="reContent_update" name="reContent" class="form-cotrol"></textarea>
						</div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" data-dismiss="modal" id="review_update_btn">수정</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	
	
	<script type="text/javascript">
	var reBno = "${vo.prodBno}";
	var page = 1;
		$(document).ready(function(){
			
			console.log("${to.curPage}");
			
			$("#review_update_btn").click(function(event) {
				event.preventDefault();
				var reRno = $("#modal_reRno").text();
				var reWriter = $("#reWriter_update").val();
				var reContent = $("#reContent_update").val();
				
				$.ajax({
					type : 'put',
					url : '/review/reply',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'PUT'
					},
					data : JSON.stringify({
						reRno : reRno,
						reWriter : reWriter,
						reContent : reContent
					}),
					dataType : 'text',
					success : function(result) {
						if(result == "1"){
							alert("수정되었습니다.");
							$("#reviewList").html("");
							getReviewList(reBno,page);
						}else{
							alert("수정되지 않았습니다.");
						}
					}
				});
			});
			
			$("#reviewList").on("click", ".review_btn_update_form", function(event) {
				event.preventDefault();
				var that = $(this);
				var reRno = that.parent().attr("data-reRno");
				var reWriter = that.parent().attr("data-reWriter");
				var reContent = that.parent().prev("p").text();
				
				$("#modal_reRno").text(reRno);
				$("#reWriter_update").val(reWriter);
				$("#reContent_update").val(reContent);
			});
			
			$("#reviewList").on("click", ".review_btn_delete", function(event) {
				event.preventDefault();
				var that = $(this)
				var reRno = that.parent().attr("data-reRno");
				
				$.ajax({
					type : 'delete',
					url : '/review/reply',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'DELETE'
					},
					data : JSON.stringify({
						reRno : reRno
					}),
					dataType : 'text',
					success : function(result) {
						if(result == "1"){
							alert("삭제되었습니다.");
							that.parent().parent().remove();
						}else{
							alert("삭제되지 않았습니다.");
						}
						$("#reviewList").html("");
						getReviewList(reBno,page);
					}
				});
			});
			$(".review").click(function() {
				$("#review_reply").toggle();  
			});
			star(".stars");
			
			
			$(".review_insert_btn").click(function(event) {
				event.preventDefault();
				
				$.ajax({
				type : 'post',
				url : '/review/reply',
				headers : {
					'Content-Type' : 'application/json',
		            'X-HTTP-Method-Override' : 'POST'
				},
				data: JSON.stringify({
					reBno : reBno,
					reWriter : $('#reWriter').val(),
					reContent : $('#reContent').val(),
					reStar : $(".stars").attr("data-val")
				}),
				dataType : 'text',
				success : function(result) {
					alert(result);
					$("#reviewList").html("");
					getReviewList(reBno,page);
					}
				});
			});
			
			
			
			getReviewList(reBno,page);
			
		});
		
		var curPage;
		var beginPageNum;
		var finishPageNum;
		var totalPage;
		
		
	$("#reviewList_page").on("click", ".pageNum", function() {

			var page = $(this).text();
			getReviewList(reBno,page);			
			
		});
	
		$("#reviewList_page").on("click", ".prev", function() {

	         if(curPage != beginPageNum)
	         {
	        	 var page = beginPageNum;	
	        	 getReviewList(reBno, page);
	         }
	         else
	         {
	        	 var page = beginPageNum-5;
	        	 getReviewList(reBno, page);
	         }
	         
	      });
		
		$("#reviewList_page").on("click", ".next", function() {

	      if(curPage != finishPageNum)
				{
		        	curPage = finishPageNum;
		        	getReviewList(reBno, curPage);
				}
				else
				{
					curPage = beginPageNum+5;
					getReviewList(reBno, curPage);
				}
	         
	      });
		
		
		function getReviewList(reBno,page) {
			$("#reviewList").empty();
			
		//	console.log(page+"=====page");
		//	console.log(reBno+"=====reBno");
			
			
			var url = "/review/reply/"+reBno+"/"+page;
			$.getJSON(url, function(data) {
				
				
				curPage = data.curPage;
				beginPageNum = data.beginPageNum;
				finishPageNum = data.finishPageNum;
				totalPage = data.totalPage;
				
			
				var rePage = makeReviewPage(data.beginPageNum, data.finishPageNum, data.curPage);				
				$("#reviewList_page").html(rePage);	//이전 /페이징숫자/ 다음
				if(curPage ==1){ 
					$("#prev").hide();
				}else{
					$("#prev").show();
				}
				if(curPage ==totalPage){
					$("#next").hide();
				}else{
					$("#next").show();
				}
			
			
				
				var dummyData = data.list;
				for(var i=0; i<dummyData.length; i++){
					var obj = dummyData[i];
					var msg = reveiwList(obj['reRno'], obj['reWriter'],obj['reStar'], obj['reUpdatedate'], obj['reContent']);
					
				    var reStar = parseInt(obj['reStar']);
				    console.log(reStar+"====reStar");
				    
				    var idx = $(".star").attr("data-idx");
				    console.log(idx+"====idx");
				    console.log(i+"=========================i");
				    
				    $(".star").each(function(index) {
				    	console.log(index+"====index");
					for(var j=index; j<=reStar; j++){
						console.log(j+"=============j");
				        $(".star").addClass("bright");
				        }
					});
					
					$("#reviewList").append(msg);	//	리스트
					
				}
				console.log(dummyData);

			});
		}
	

		
	</script>
</body>
</html>