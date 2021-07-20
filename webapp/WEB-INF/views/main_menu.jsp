<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

​
<h1 style="font-weight: bold;">Test SHOP</h1>
<br>
<c:choose>
	<c:when test="${empty login}">
		<!-- <a href="/member/loginGet">로그인</a> -->
	</c:when>
	<c:otherwise>
            ${login.userName} 님, 환영합니다. <a href="/member/logout">로그아웃</a>
	</c:otherwise>

</c:choose>
<br>
<br>

<div class="dropmenu">
	<ul>
		<li><a href="/">Home</a></li>
		<li><a href="#" id="current">SHOP</a>
			<ul>
				<li><a href="#">ITEM1</a></li>
				<li><a href="#">ITEM2</a></li>
				<li><a href="#">ITEM3</a></li>
			</ul></li>

		<li><a href="#" id="current">COMMUNITY</a>
			<ul>
				<li><a href="#">NOTICE</a></li>
				<li><a href="#">Q&A</a></li>
				<li><a href="#">REVIEW</a></li>
			</ul></li>

		<li><a href="#" id="current">ACCOUNT</a>
			<ul>
				<li><a href="/member/loginGet">LOGIN</a></li>
				<li><a id="moveReadSelf" href="#">MY PAGE</a></li>

				<c:choose>
					<c:when test="${empty login}">
						<li><a id="orderTracking" href="/member/loginGet">ORDER</a></li>
						<script>
						$("a#orderTracking").click(function() {
							alert("로그인이 필요한 작업입니다.")
						});
						</script>
					</c:when>
					<c:otherwise>
						<li><a id="orderTracking"
							href="/member/orderTracking/${login.userId}">ORDER</a></li>
					</c:otherwise>
				</c:choose>

				<li><a href="/member/cartlist/${login.userId}">CART</a></li>
			</ul></li>

		<c:choose>
			<c:when test="${login.userGrade ==1}">
				<li><a href="#" id="current">관리자 MENU</a>
					<ul>
						<li><a href="/member/list">회원관리</a></li>
						<li><a href="/member/managementlist">주문관리</a></li>

					</ul></li>
			</c:when>
			<c:otherwise>

			</c:otherwise>

		</c:choose>




	</ul>
</div>

<hr />

<form action="/member/readSelf" method="post" hidden="" id="readSelf">
	<input type="hidden" id="readuserId" name="readuserId"
		value="${login.userId}"> <input type="submit" value="회원정보 수정1">
</form>

<%-- <form action="/member/orderTracking" method="post" hidden="" id="readOrderTracking">
	<input type="hidden" id="orderUserId" name="orderUserId"
		value="${login.userId}"> <input type="submit" value="회원정보 수정1">
</form> --%>
<script type="text/javascript">
	$(document).ready(function() {

		var userId = $("#readuserId").val();

		$("#moveReadSelf").click(function() {

			$("form#readSelf").submit();

		})

		/* $("#orderTracking").click(function() {

			$("form#readOrderTracking").submit();

		}) */

	})
</script>