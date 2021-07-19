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
<link rel="stylesheet" href="/resources/css/read.css">

</head>
<body>

	<div class="container">

		<div class="logo">
		<a href="/index.html"><img src="/resources/img/3.jpg" height="16px;"/></a>
		</div>

		<div class="menu_wrap">
		<ul class="dep1">
			<li>
				<a href="/product/prodList?prodCategory=&prodOrder=${prodOrder}">shop</a>
				<ul class="dep2">
					<li><a href="/product/prodList?prodCategory=top&prodOrder=${prodOrder}">top</a></li>
					<li><a href="/product/prodList?prodCategory=bottom&prodOrder=${prodOrder}">bottom</a></li>
					<li><a href="/product/prodList?prodCategory=outer&prodOrder=${prodOrder}">outer</a></li>
					<li><a href="/product/prodList?prodCategory=acc&prodOrder=${prodOrder}">acc</a></li>
				</ul>
			</li>
			<li>
				<a href="#">lookbook</a>
				<ul class="dep2">
					<li><a href="#">21ss</a></li>
					<li><a href="#">20fw</a></li>
					<li><a href="#">20ss</a></li>
					<li><a href="#">19fw</a></li>
				</ul>
			</li>
			<li>
				<a href="#">community</a>
				<ul class="dep2">
					<li><a href="#">notice</a></li>
					<li><a href="#">Q/A</a></li>
					<li><a href="#">review</a></li>
					<li><a href="#">event</a></li>
				</ul>
			</li>
			<li>
				<a href="#">account</a>
				<ul class="dep2">
					<li><a href="#">login</a></li>
					<li><a href="#">order traking</a></li>
					<li><a href="#">mypage</a></li>
					<li><a href="#">cart</a></li>
				</ul>
			</li>
		</ul>
	</div>


		<div class="row left" id="img">
			<img src="${vo.prodThumbnail }">
		</div>


		<div class="row" id="info">
			<p style="font-size: 30px;">${vo.prodName}</p>
			<p>${vo.prodTitle}</p>
			<p>PRICE : &nbsp;&nbsp;&nbsp; ${vo.prodPrice}</p>

		</div> <!-- end of row -->
		<div class="row" id="content">
		<p>${vo.prodContent}</p>
		</div>
		<div class="row" id="button">
		<a class="update">&nbsp;&nbsp;UPDATE&nbsp;&nbsp;</a>|
		<a class="list">&nbsp;&nbsp;LIST&nbsp;&nbsp;</a>|
		<a class="delete">&nbsp;&nbsp;DELETE</a>
		</div>
		<form action="/product/prodDelete/${vo.prodName}?prodBno=${vo.prodBno}" method="post"></form>
	</div> <!-- end of container -->

  <jsp:include page="\qna/qnaPage.jsp" />

  <!-- review 들어올 자리 -->
	<jsp:include page="/WEB-INF/views/qna/qnaPage.jsp" />

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
