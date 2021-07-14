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
<style type="text/css">
	th {
		text-align: center;
	}
	td {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="jumbotron text-center">${userId}님의 장바구니</h1>
		</div>
		
		<div class="row" id="table">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>장바구니번호</th>
						<th>이미지</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>수량</th>
						<th>합계</th>
						<th></th>
					</tr>
				</thead>
				
				<tbody class="tbody">
					<c:forEach items="${list}" var="vo">
						<tr onmouseOver="this.style.background='#50bcdf'" onMouseOut="this.style.backgroundColor=''">
							<td class="cartNo">${vo.cartNo}</td>
							
							<td><img src="/resources/img/${vo.productVO.prodThumbnail}">
								${vo.productVO.prodThumbnail}</td>
							<td class="prodName">${vo.productVO.prodName}</td>
							
							<td>${vo.prodSize}</td>

							<td><fmt:formatNumber value="${vo.productVO.prodPrice}" pattern="#,###.##"/>원</td>
							<td>
								<input type="number" class="amount" value="${vo.amount}" style="text-align:center;" min="1"/>
							</td>
							
							<td class="total">
								<fmt:formatNumber value="${vo.productVO.prodPrice * vo.amount}" pattern="#,###.##"/>원
							</td>
							
							<td class="hide">
								<c:if test="${vo.prodSize eq 'S'}">
									<span class="hide">${vo.productStockVO.prodStockSSize}</span>
								</c:if>
								<c:if test="${vo.prodSize eq 'M'}">
									<span class="hide">${vo.productStockVO.prodStockMSize}</span>
								</c:if>
								<c:if test="${vo.prodSize eq 'L'}">
									<span class="hide">${vo.productStockVO.prodStockLSize}</span>
								</c:if>
							</td>
							
							<td><a class="btn btn-danger btn-xs cart_delete_btn">삭제</a><td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".amount").on("change", function() {
				var that = $(this);
				var tr = that.parent().parent();
				var td = tr.children();
				var cartNo = td.eq(0).text();
				var prodName = td.eq(2).text();
				var prodSize = td.eq(3).text();
				var prodStockSize = td.eq(3).children().val();
				var prodPrice = td.eq(4).text();
				var amount = td.eq(5).children();
				var total = td.eq(6);
				var prodStockSize = td.eq(7).children();
				
				// amount 값 양수, 최소 최대값 제한
				if (amount.val() > Number(prodStockSize.text())) {
					alert(prodName + " " + prodSize + "사이즈의 \n남은 수량은 " + Number(prodStockSize.text()) + "개 입니다.");
					amount.val(Number(prodStockSize.text()));
					
				} else if (amount.val() < 1) {
					alert("최소 1개 이상이어야 합니다");
					amount.val(1);
				}
				
				$.ajax({
					type : 'put',
					headers : {
						'Content-Type' : 'application/json',
						'X-HTTP-Method-Override' : 'PUT'
					},
					url : '/carts/cart',
					data : JSON.stringify({
						cartNo : cartNo,
						userId : '${userId}',
						prodName : prodName,
						prodSize : prodSize,
						amount : amount.val()
					}),
					dataType : 'text',
					success : function(result) {
						if (result == "1") {
							// replace 금액에 ',' '원' 자르기
							total.text(Number(prodPrice.replace(/(,|원)/g, "") * amount.val()).toLocaleString() + "원");
						}
					}
					
				});
				
				
			});
			

			$(".cart_delete_btn").on("click", function() {
				event.preventDefault();
				var that = $(this);
				var tr = that.parent().parent();
				var td = tr.children();
				var cartNo = td.eq(0).text();
	
				var msg = confirm("선택한 항목을 삭제하시겠습니까?");
				
				if (msg) {
					$.ajax({
						type : 'delete',	// 삭제
						headers : {
							'Content-Type' : 'application/json',
							'X-HTTP-Method-Override' : 'DELETE'
						},
						url : '/carts/cart',
						data : JSON.stringify({
							cartNo : cartNo
						}),
						dataType : 'text',
						success : function(result) {
							if (result == "1") {
								tr.remove();
								alert("삭제되었습니다.");
							} else {
								alert("삭제 실패");
							}
						}	
					});
					
				} else {
					event.preventDefault();
				}
			});
			

		});

		
	</script>
</body>
</html>