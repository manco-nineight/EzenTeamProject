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
			<h1 class="jumbotron text-center">REVIEW</h1>		
		</div>
		
	<a class="btn btn-info review">글작성</a>

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
	    		
	    		<div class="form-group stars" id="reStar"> 
	    		   		
	    		</div>
	    		
	    		<div class="form-group" style="text-align: right;">
	    			<button  class="btn btn-info review_insert_btn ">글등록</button>
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
	var reBno = "${vo.prodBno}";	//상품게시판에서 게시판 번호 가져오기
	var page = 1;	//페이지 값은 1로 정의
		$(document).ready(function(){
			//리뷰 댓글의 수정
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
			//수정 창에 값 불러오기(모달)
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
			//삭제
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
			
			//리뷰 생성 태그 숨기기 및 보이기
			$(".review").click(function() {
				$("#review_reply").toggle();  
			});
			star(".stars");
			
			//
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
		
		//페이지 값 가져오기
		$("#reviewList_page").on("click", ".pageNum", function() {

			var page = $(this).text();
			getReviewList(reBno,page);			
			
		});
		//이전 페이지 기능
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
		//다음 페이지 기능
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
		
		//리뷰 목록 리스트에 별점기능은 리스트가 여러가지이며 동적으로 기능되어 클래스 값으로 배열을 이용
		function getReviewList(reBno,page) {
			$("#reviewList").empty();
			
			
			var url = "/review/reply/"+reBno+"/"+page;
			$.getJSON(url, function(data) {
				
				
				curPage = data.curPage;
				beginPageNum = data.beginPageNum;
				finishPageNum = data.finishPageNum;
				totalPage = data.totalPage;
				
				//페이지 목록 이전/숫자/다음	조건식으로는 첫 페이지일 경우 이전을 숨기고 마지막 페이지일 경우 다음을 숨긴다.
				var rePage = makeReviewPage(data.beginPageNum, data.finishPageNum, data.curPage);				
				$("#reviewList_page").html(rePage);	
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
				reviewList(dummyData);


			});
		}
	
	</script>
</body>
</html>