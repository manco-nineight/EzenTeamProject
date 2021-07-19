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

                <form action="/member/" method="post">
					<h3>
						<label for="userPw">비밀번호</label>
					</h3>
					<span class="box int_id" style="width: 400px;"> 
					<input
						type="text" id="userId" class="int" maxlength="20" name="userId"
						style="width: 400px;" required> <br>

					</span>
					<button style="width: 400px;" type="button" class="btn btn-info" id="idCheckButton">아이디 중복확인</button>
					<input type="checkbox" required onclick="return false;"  id="idCheckBox">
                        <!--  -->
                 </form>















</body>
</html>