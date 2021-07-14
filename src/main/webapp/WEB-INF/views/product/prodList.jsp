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
	
		<a href="/product/prodInsert">등록</a>
	
	<div class="prodOrder">
		
		<ul class="nav nav-pills">
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodSalesCount">판매순</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodRegdate">신상품</a></li>
  			<li role="presentation"><a href="/product/prodList?prodCategory=${prodCategory}&prodOrder=prodPrice">가격순</a></li>
		</ul>
		
	</div>
	
	<div class="row">
		<c:forEach items="${to.list}" var="item">
			<div class="col-md-3 thumb_list" style="margin: 1px 0;">
				<div class="list_thumbnail">
					<a href="/product/prodRead/${item.prodName}"><img src="${item.prodThumbnail}" style="height:200px;width:200px;"></a>
					<p><a href="/product/prodRead/${item.prodName}">${item.prodName}</a></p>
					<p>${item.prodTitle}</p>
					<p>${item.prodPrice}</p>
				</div>		
			</div>
		</c:forEach>
	</div><!--row end  -->
</div><!--container end-->

<script type="text/javascript">
	
	
	
</script>

</body>
</html>