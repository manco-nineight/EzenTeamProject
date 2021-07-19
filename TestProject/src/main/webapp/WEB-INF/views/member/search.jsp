<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 검색 목록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container">
		
		<%-- <div class="row">
			<c:choose>
				<c:when test="${empty login}">
					<a href="/member/loginGet">로그인</a>
				</c:when>
				<c:otherwise>
					(${login.userName})님 반갑습니다. <a href="/member/logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
		</div> --%>
		
		<div class="row">
			<h1 class="jumbotron text-center">회원 목록</h1>
			<a href="/member/insert">회원 가입</a>
		</div>
		
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>userId</th>
						<th>userName</th>
						<th>userBirthday</th>
						<th>userEmail</th>
						<th>userRegdate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${to.list}" var="vo">
							<tr style="cursor:pointer;" 
								onmouseOver="this.style.background='#50bcdf'" 
								onMouseOut="this.style.backgroundColor=''"
								onclick="location.href='/member/read/${vo.userId}?curPage=${to.curPage}'">
								
								<td>${vo.userId}</td>
								<td>${vo.userName}</td>
								<td>${vo.userBirthday}</td>
								<td>${vo.userEmail}</td>
								<td>${vo.userRegdate}</td>
								
							</tr>
					</c:forEach>
				</tbody>
			</table>
			
			<div class="text-center">
				<jsp:include page="search_part.jsp"/>
				<jsp:include page="paging_part.jsp">
					<jsp:param value="search" name="list"/>
				</jsp:include>
			</div>
			
		</div>
	</div>
	
	
</body>
</html>