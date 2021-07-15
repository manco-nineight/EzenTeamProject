<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h1 class="jumbotrion">로그인 화면</h1>
		</div>
	
		<div class="row">
			<form class="form-horizontal" action="/member/loginPost" method="post">
			  <div class="form-group">
			    <label for="userId" class="col-sm-2 control-label">userId</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="userId" name="userId">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="userPw" class="col-sm-2 control-label">userPw</label>
			    <div class="col-sm-10">
			      <input type="password" class="form-control" id="userPw" name="userPw">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <div class="checkbox">
			        <label>
			          <input type="checkbox"> Remember me
			        </label>
			      </div>
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">로그인</button>
			    </div>
			  </div>
			</form>
		</div>
	</div>
</body>
</html>