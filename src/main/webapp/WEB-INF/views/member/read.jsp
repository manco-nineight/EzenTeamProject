<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.userId} 회원 정보</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="jumbotron text-center">회원 정보</h1>
		</div>
		
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<span>
						아이디 : ${vo.userId}
					</span>
									
					<span class="glyphicon glyphicon-time pull-right">
						가입일자 ${vo.userRegdate}
					</span>
				</div>
				
				<div class="panel-body">
					<p>이름 : ${vo.userName}</p>
					<p>생일 : ${vo.userBirthday}</p>
					<p>이메일 : ${vo.userEmail}</p>
					<p>주소 : ${vo.userAddress}</p>
					<p>정보 수정 : ${vo.userUpdatedate}</p>
				</div>
				
				<%-- ${vo.writer != login.userId?'disabled':''} --%>
				<div class="panel-footer text-center">
					<a class="btn btn-warning update ${vo.userId != login.userId?'disabled':''}">수정</a>
					<a class="btn btn-danger delete ${vo.userId != login.userId?'disabled':''}">삭제</a>
					<a class="btn btn-info cartlist ${vo.userId != login.userId?'disabled':''}">장바구니</a>
					<a class="btn btn-info list">목록</a>
				</div>
			</div>
		</div>
		
		<form action="/member/delete/${vo.userId}?curPage=${curPage}" method="post"></form>
		
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$(".update").click(function() {
				location.assign("/member/update/${vo.userId}?curPage=${curPage}");
			});
			
			$(".delete").click(function() {
				var mDelete = confirm("${vo.userId} 회원을 삭제 하시겠습니까?");
				
				if (mDelete) {
					event.preventDefault();
					$("form").submit();
					alert("삭제되었습니다.");
				} else {
					evenr.preventDefault();
				}
				
			});
			
			
			$(".list").click(function() {
				location.assign("/member/list/${curPage}");
			});
			
			$(".cartlist").click(function() {
				location.assign("/member/cartlist/${vo.userId}");
			});

			
		});	// document.ready 끝 부분
		
	</script>
</body>
</html>