<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 전체 목록</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="../resources/img/mycss.css" rel="stylesheet" type="text/css" />
</head>


<body style="text-align: center; margin: auto;">
	<jsp:include page="../main_menu.jsp"/>
</body>


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
			<a href="/member/sign">회원 가입</a>
		</div>
		
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>userId</th>
						<th>userGrade</th>
						<th>userName</th>
						<th>userBirthday</th>
						<th>userEmail</th>
						<th>userRegdate</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${to}" var="vo">
							<tr>
								
								<td><a href="/member/readByAdmin/${vo.userId}">${vo.userId}</a></td>
								<td>${vo.userGrade}</td>
								<td>${vo.userName}</td>
								<td>${vo.userBirthday}</td>
								<td>${vo.userEmail}</td>
								<td>${vo.userRegdate}</td>
								
							</tr>
					</c:forEach>
				</tbody>
			</table>
			
			
			
		</div>
	</div>
	
	
</body>
</html>