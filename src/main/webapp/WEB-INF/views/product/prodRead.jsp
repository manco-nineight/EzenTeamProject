<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<title>prodRead</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">

	.row {
		margin: 20px;
	}

</style>

</head>
<body>

	<div class="contianer">
		<div class="row">
		
			<p>${vo.prodName}</p>
			<p>${vo.prodTitle}</p>
			<p>${vo.prodPrice}</p>
			<p>${vo.prodContent}</p>
	
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
		
		</div> <!-- end of row -->
		<a class="btn btn-warning update">수정</a>
		<a class="btn btn-warning list">목록</a>
		<a class="btn btn-warning delete">삭제</a>
		<form action="/product/prodDelete/${vo.prodName}?prodBno=${vo.prodBno}" method="post"></form>
	</div> <!-- end of container -->
	
<script type="text/javascript">
	$(document).ready(function() {
		
		$(".list").click(function() {
			location.assign("/product/prodList");
		});
		
		
		$(".update").click(function() {
			location.assign("/product/prodUpdate/${vo.prodName}");
		});
		
		$(".delete").click(function() {
			$("form").submit();
		});
		
	});

</script>
	

</body>
</html>