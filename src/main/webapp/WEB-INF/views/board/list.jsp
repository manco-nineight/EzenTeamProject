<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="../resources/img/mycss.css" rel="stylesheet" type="text/css" />
</head>
<body>


	<div class="container">
	
<jsp:include page="../main_menu.jsp"/>
		
	

			<h1 style="text-align: center; margin-top: 30">게시글 목록</h1>
			<br> <br> <br>
			<form action="/board/search" method="post">
				<div class="row">
					<div class="col-lg-6">
						<div class="input-group">
							<div class="input-group-btn">

								<select id="searchVal" name="searchVal" class="form-control"
									style="width: 100px">
									<option value="1">writer</option>
									<option value="2">title</option>
									<option value="3">content</option>
								</select>

							</div>
							<!-- /btn-group -->
							<input type="text" class="form-control" id="put" name="put">
							<span class="input-group-btn"> <input id="search"
								class="btn btn-default" type="submit" value="Enter">
							</span>

						</div>
					</div>
				</div>
			</form>
			<a href="/board/insert" class="btn btn-default" style="float: right;">글쓰기
				(${login.userName})</a> 

		</div>


	<div class="container">
		<div class="row">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>bno</th>
						<th>writer</th>
						<th>title</th>
						<th>regdate</th>
						<th>updatedate</th>

					</tr>

				</thead>
				<tbody>
					<c:forEach items="${to.list}" var="vo">
						<tr>
							<td>${vo.bno}</td>
							<td>${vo.writer}</td>
							<td><a href="/board/read/${vo.bno}?curPage=${to.curPage}">${vo.title}</a></td>
							<td>${vo.regdate}</td>
							<td>${vo.updatedate}</td>
						</tr>
					</c:forEach>
				</tbody>


			</table>
			<jsp:include page="paging_part.jsp" />
		</div>

	</div>




	

</body>
</html>