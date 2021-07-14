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
	<h1>test3.jsp입니다.</h1>
	<button>json data 전송</button>
	<div id="appendResult"></div>
	
	<div class="panel panel-primary">
  <div class="panel-heading">
  아이디 : m001 <span class=" pull-right"> 비밀번호 : m001</span>
  </div>
  <div class="panel-body">
    <h3>이름 : 김유신</h3>
    <h5>이메일 : a@a.com</h5>
  </div>
</div>
	
	<script type="text/javascript" src="/resources/js/test.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$("button").click(function() {
				$.ajax({
					type : 'post',
					url : '/ajax/test3',
					data : {
						'userId' : "m001",
						'userPw' : "m001",
						'userName' : "kim",
						'email' : "a@a.com"
					},
					dataType : 'text',
					success : function(result) {
						var obj = JSON.parse(result);
						var msg = me3(obj.userId,obj.userPw, obj.userName, obj.email)
						$("#appendResult").append(msg);
					}
				});
			});
		});
	</script>
	
</body>
</html>