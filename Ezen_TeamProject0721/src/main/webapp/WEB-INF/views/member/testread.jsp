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
      
         <p>상품 이름 : ${vo.prodName}</p>
         <p>상품 글 제목 : ${vo.prodTitle}</p>
         <p>상품 가격 : ${vo.prodPrice}</p>
         <p>상품 설명 : ${vo.prodContent}</p>
   		 <p>S사이즈 수량 : ${stock.prodStockSSize}</p>
   		 <p>M사이즈 수량 : ${stock.prodStockMSize}</p>
   		 <p>L사이즈 수량 : ${stock.prodStockLSize}</p>
         <br>
         <br>
         

		 <div class="form-group">
			<select class="form-select" name="prodSize" id="prodSize">
				<option selected value="${stock.prodStockSSize}">S</option>
				<option value="${stock.prodStockMSize}">M</option>
				<option value="${stock.prodStockLSize}">L</option>
			</select>

			<a class="btn btn-success cart_insert_btn ${empty login.userId?'disabled':''}">장바구니 추가</a>
		</div>
		

		<button type ="button" onclick="amountCount('p')">+</button>
		<input type="number" id="amount" name="amount" value="1" min="1" style="text-align:center;"/>
		<button type="button" onclick="amountCount('m')">-</button>

      
      </div> <!-- end of row -->
      
   </div> <!-- end of container -->
   
<script type="text/javascript">
   $(document).ready(function() {

      $(".cart_insert_btn").click(function() {
    		event.preventDefault();

			$.ajax({
				type : 'post',
				headers : {
					'Content-Type' : 'application/json',
					'X-HTTP-Method-Override' : 'POST'
				},
				url : '/carts/cart',
				data : JSON.stringify({
					userId : '${login.userId}',
					prodName : '${vo.prodName}',
					prodSize : $("#prodSize option:selected").text(),
					amount : $("#amount").val()
				}),
				dataType : 'text',
				success : function(result) {
					if (result == "1") {
						alert("저장하였습니다. \n(중복된 상품이면 상품 개수만 추가됩니다.)");
					} else {
						alert("장바구니 저장에 실패하였습니다.");
					}
				}
			});
		
    	  
      });
      
   });
	
   
   function amountCount(type){
	   var amount = Number($("#amount").val());
	   
	    if(type == 'p') {
	    	if (amount < $('#prodSize option:selected').val()) {
	    		$("#amount").val(Number(amount)+1);
			}
	        
	    } else {
	        if(amount > 1) {
	        	$("#amount").val(Number(amount)-1);
	        }
	    }
	}
</script>
   

</body>
</html>