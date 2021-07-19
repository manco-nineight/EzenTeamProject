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
</head>
<body>
	
	<div class='container'>
		
		<table class="table table-striped">
				<thead>
					<tr>
						<th>상품 이름</th>
						<th>상품 번호</th>
						<th>상품 종류</th>
						<th>상품 글 제목</th>
						<th>상품 내용</th>
						<th>상품 글 작성일자</th>
						<th>상품 글 수정일지</th>
						<th>상품 가격</th>
						<th>상품 이미지</th>
					</tr>
				</thead>
				
				<tbody class="tbody">
					<c:forEach items="${list}" var="vo">
						<tr onmouseOver="this.style.background='#50bcdf'" onMouseOut="this.style.backgroundColor=''">
							<td><a class="btn btn-warning" href="/member/testread/${vo.prodName}">${vo.prodName}</a></td>
							<td>${vo.prodBno}</td>
							<td>${vo.prodCategory}</td>
							<td>${vo.prodTitle}</td>
							<td>${vo.prodContent}</td>
							<td>${vo.prodRegdate}</td>
							<td>${vo.prodUpdatedate}</td>
							<td>${vo.prodPrice}</td>
							<td><img src="/resources/img/${vo.prodThumbnail}"><br>${vo.prodThumbnail}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		
	</div>
	
</body>
</html>