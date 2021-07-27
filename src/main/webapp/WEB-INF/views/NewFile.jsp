<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta charset="UTF-8">
<title>prodList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/list.css">
</head>
<body>
<div class="container" style="padding:0px;">

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
	<div class="loading">
			<img src="/resources/img/loading.gif">
	</div>

		<a href="/product/prodInsert">등록</a>
	<div class="prodOrder">
		<ul class="dep3">
			<li>
				<a href="/product/prodList?prodCategory=&prodOrder=${prodOrder}">카테고리</a>
				<ul class="dep4">
					<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodSalesCount">판매순</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodRegdate">신상품</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodPrice">가격순</a></li>
				</ul>
			</li>
			</ul>
			</div>
	<div class="prodOrder">
		<ul class="nav nav-pills">

		</ul>
	</div>
	<div class="search">
		<form action="/product/prodList?prodCategory=${prodCategory}?prodOrder=${prodOrder}">
			<input type="text" name="keyword" placeholder="productSearch">
			<input type="submit" value="search">
		</form>
	</div>

		<c:forEach items="${to.list}" var="item">
			<div class="col-md-3 thumb_list" style="margin: 1px 0; padding;">
				<div class="list_thumbnail">
					<a href="/product/prodRead/${item.prodName}"><img src="${item.prodThumbnail}" style="height:200px;width:200px;" id="scrollImg"></a>
					<p><a href="/product/prodRead/${item.prodName}">${item.prodName}</a></p>
					<p>${item.prodTitle}</p>
					<p>${item.prodPrice}</p>
				</div>
			</div>
		</c:forEach>
	<br>
    <div class="scroll_paging"></div>
    <a href="#" class="scrollToTop"><img src="/resources/img/images.png"></a>
	<div class="scroll_paging"></div>
</div><!--container end-->

<script type="text/javascript">
	$(document).ready(function() {
		var mybutton = document.getElementById("gotoBtn");
    $(".loading").hide();


	$(".scrollToTop").click(function() {
		$("html , body").animate({scrollTop : 0} , 500);

		return false;
	});
	
		$(".loading").hide();
	});

	window.onscroll = function(ev) {
		var curPage = 2;
	    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
	    	$(".loading").show();
			$.ajax({
				type: "post",
				url: "/product/prodListScroll",
				data: JSON.stringify({
					"curPage" : curPage,
					"prodCategory" : "${prodCategory}",
					"prodOrder" : "${prodOrder}",
					"keyword" : "${keyword}"
				}),
				dataType: "json",
				contentType: "application/json;charset=utf-8",
				success: function(result) {
						for (var i = 0; i < result.length; i++) {
							var str = 
							'<div class="col-md-3 thumb_list" style="margin:0px;">'+
								'<div class="list_thumbnail">'+
									'<a href="/product/prodRead/'+ (result[i].prodName) +'"> <img src="' +(result[i].prodThumbnail) +  '"style="height:200px;width:200px;"></a>'+
									'<p><a href="/product/prodRead/' + (result[i].prodName) + '">' + (result[i].prodName) + '</a></p>'+
									'<p>' + (result[i].prodPrice) + '</p>'+
								'</div>'+
							'</div>'
							$(".scroll_paging").append(str);
					}
				}
			});
			$(".loading").hide();
			curPage += 1;
		}
			
	};
</script>


</body>
</html>
