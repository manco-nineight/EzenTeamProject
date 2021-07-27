<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>CartList</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="/resources/img/mycss.css" rel="stylesheet" type="text/css" />
<style>

#mail_check_input_box_false{
    background-color:#ebebe4;
} 
#mail_check_input_box_true{
    background-color:white;
}
.correct{
    color : green;
}
.incorrect{
    color : red;
}
</style>
</head>
<body style="text-align: center;margin: auto;">

	<jsp:include page="../main_menu.jsp"/>
	<br><br><br><br>
	
	<div class="container">
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><input id="allCheck" type="checkbox"/> 전체선택</th>
						<th>이미지</th>
						<th>상품명</th>
						<th>사이즈</th>
						<th>가격</th>
						<th>수량</th>
						<th>합계</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${list}" var="vo">
						<tr onmouseOver="this.style.background='#50bcdf'" onMouseOut="this.style.backgroundColor=''">
							<td><input class="rowCheck" type="checkbox" value="${vo.cartNo}"/></td>
							
							<td>
								<img src="${vo.productVO.prodThumbnail}" style="width: 200px; height: 200px;">
							</td>
								
							<td>${vo.productVO.prodName}</td>
							
							<td>${vo.prodSize}</td>

							<td><fmt:formatNumber value="${vo.productVO.prodPrice}" pattern="#,###"/>원</td>
							
							<td>
								<input type="number" class="amount" value="${vo.amount}" style="text-align:center;" min="1"/>
							</td>
							
							<td class="total" id="total">	<!-- 원가*수량 합계 -->
								<fmt:formatNumber value="${vo.productVO.prodPrice * vo.amount}" pattern="#,###"/>원
							</td>
							
							<td class="hide">	<!-- 사이즈 별 수량 -->
								<c:if test="${vo.prodSize eq 'S'}">
									<span class="hide">${vo.prodStockDTO.prodStockSSize}</span>
								</c:if>
								<c:if test="${vo.prodSize eq 'M'}">
									<span class="hide">${vo.prodStockDTO.prodStockMSize}</span>
								</c:if>
								<c:if test="${vo.prodSize eq 'L'}">
									<span class="hide">${vo.prodStockDTO.prodStockLSize}</span>
								</c:if>
							</td>							
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
			총합 : <span id="cartListTotal"></span>
			<button type="button" class="btn btn-danger btn-xs check_delete_btn" onclick="checkDelete();">선택 삭제</button>
			<a class="btn btn-info">결제(미구현)</a>
		</div>
		
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {			
			cartListTotal();	// 장바구니 리스트 총 합계
			
			// 전체 선택
			$("#allCheck").click(function() {
				for (var i = 0; i < $(".rowCheck").length; i++) {
					$(".rowCheck")[i].checked = this.checked;
				}
			});
			
			// 수동으로 선택 시 allCheck 변화
			$(".rowCheck").click(function() {
				if ($(".rowCheck:checked").length == $(".rowCheck").length) {
					$("#allCheck")[0].checked = true;
					
				} else {
					$("#allCheck")[0].checked = false;
				}
			});
			
			
			// 수량 값이 변화 시 금액 변화
			$(".amount").on("change", function() {
				var that = $(this);
				var tr = that.parent().parent();
				var td = tr.children();
				var cartNo = td.eq(0).children();
				var prodName = td.eq(2).text();
				var prodSize = td.eq(3).text();
				var prodPrice = td.eq(4).text();
				var amount = td.eq(5).children();
				var total = td.eq(6);
				var prodStock = td.eq(7).children();
				
				// amount 값 양수, 최소 최대값 제한
				if (amount.val() > Number(prodStock.text())) {
					alert(prodName + " " + prodSize + "사이즈의 \n남은 수량은 " + Number(prodStock.text()) + "개 입니다.");
					amount.val(Number(prodStock.text()));
					
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
						cartNo : cartNo.val(),
						userId : '${userId}',
						prodName : prodName,
						prodSize : prodSize,
						amount : amount.val()
					}),
					dataType : 'text',
					success : function(result) {
						if (result == "1") {
							// replace = 숫자에 ',' '원' 자르기
							// toLocaleString = 숫자에 ',' 추가
							total.text(Number(prodPrice.replace(/(,|원)/g, "") * amount.val()).toLocaleString() + "원");
							
							cartListTotal(); // 장바구니 리스트 총 합계
						} 
					}
				});	// ajax End
			});	// .amount End

		});	// document End
		
		
		// 장바구니 리스트 총 합계
		function cartListTotal() {
			var sum = 0;
			$(".total").each(function(index){
				sum += Number($(".total").eq(index).text().replace(/(,|원)/g, ""));
			});
			$("#cartListTotal").text(sum.toLocaleString() + "원");
			
		}	// cartListTotal() End
		
		
		// 선택한 행 삭제
		function checkDelete() {
			var checkArr = new Array();
			var rowCheck = $(".rowCheck");
			
			for (var i = 0; i < rowCheck.length; i++) {
				if (rowCheck[i].checked) {
					checkArr.push(rowCheck[i].value);
				}
			}

			if (checkArr.length == 0) {
				alert("선택된 목록이 없습니다.");
				event.preventDefault();
				
			} else {
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
							cartNo : checkArr
						}),
						dataType : 'text',
						success : function(result) {
							if (result == "1") {
								$(".rowCheck:checked").each(function(){
									var td = $(this);
									var tr = td.parent().parent();
									tr.remove();
					            });
								alert("삭제되었습니다.");
								
							} else {
								alert("삭제 실패");
							}
						}	
					});	// ajax End
					
				} else {
					event.preventDefault();
				}	// msg if else End
				
			}	// checkArr.length if else End

		}	// checkDelete() End

	</script>
</body>
</html>