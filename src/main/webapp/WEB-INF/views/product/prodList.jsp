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

<style type="text/css">
	.thumb_list {
		margin: 20px;
	}
	
	.loading {
	position: fixed;
	left: 50%;
	top: 50%;
	box-shadow : rgba(0,0,0,0.5) 0 0 0 9999px;
	z-index : 100;
	}
	
</style>

</head>
<body>
<div class="container">
	
	<div class="loading">
			<img src="/resources/img/loading.gif">	
	</div>

	<div class="row">
		<ul class="nav nav-pills">
  			<li role="presentation"><a href="/product/prodList?prodCategory=&prodOrder=${prodOrder}">all</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=top&prodOrder=${prodOrder}">top</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=bottom&prodOrder=${prodOrder}">bottom</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=outer&prodOrder=${prodOrder}">outer</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=acc&prodOrder=${prodOrder}">acc</a></li>
		</ul>
		<a href="/product/prodInsert">등록</a>
	</div>
	
	<div class="prodOrder">
		<ul class="nav nav-pills">
			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}">bno</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodSalesCount">sales</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodRegdate">regdate</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodPrice">price</a></li>
		</ul>
	</div>
	
	<div class="search">
		<input class="search_keyword" type="text" placeholder="searchProduct"> <button class="search_btn">search</button>
	</div>

	<div class="row">
		<c:forEach items="${to.list}" var="item">
			<div class="col-md-3 thumb_list">
				<div class="list_thumbnail">
					<img src="${item.prodThumbnail}" style="height:250px;width:250px;">
					<p><a href="/product/prodRead/${item.prodName}">${item.prodName}</a></p>
					<p>${item.prodPrice}</p>
				</div>		
			</div>
		</c:forEach>
	</div><!--row end  -->
	<br>
	<div class="scroll_paging"></div>
</div><!--container end-->
<script type="text/javascript">
	$(document).ready(function() {
		$(".loading").hide();
	});
	
	var curPage = 2;
	
	
	window.onscroll = function(ev) {
	    if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
	    	$(".loading").show();
			$.ajax({
				type: "post",
				url: "/product/prodListScroll?prodCategory=${prodCategory}&prodOrder=${prodOrder}",
				data: {
					"curPage" : curPage,
				},
				dataType: "json",
				success: function(result) {
					for (var i = 0; i < result.length; i++) {
						var str = 
						'<div class="col-md-3 thumb_list">'+
							'<div class="list_thumbnail">'+
								'<img src="' +(result[i].prodThumbnail) +  '"style="height:250px;width:250px;">'+
								'<p><a href="/product/prodRead/' + (result[i].prodName) + '">' + (result[i].prodName) + '</a></p>'+
								'<p>' + (result[i].prodPrice) + '</p>'+
							'</div>'+
						'</div>'
						console.log(str);
						$(".scroll_paging").append(str);
					}
				}
			});
			$(".loading").hide();
			curPage += 1;
		}
			
	};
	
	$(".search_btn").click(function(event) {
		event.preventDefault;
		
		var keyword = $(".search_keyword").val();
		
		$.ajax({
			type: "post",
			url: "/product/prodListSearch?prodCategory=${prodCategory}&prodOrder=${prodOrder}",
			data: {
				"keyword" : keyword
			},
			dataType: "text"
		});
	});
	
	
</script>


</body>
</html>