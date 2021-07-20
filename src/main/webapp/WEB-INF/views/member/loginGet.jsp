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
<link href="../resources/img/mycss.css" rel="stylesheet" type="text/css" />
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

input {
	width: 410px;
	height: 30px;
	
}

.container a{
 font-size: 10px;
 color: black;
}
</style>
</head>
<body style="text-align: center;margin: auto;">

<!-- 주소창 안바꾸고 이동하기 -->
<%-- <jsp:forward page="/board/list"/> --%>

<jsp:include page="../main_menu.jsp"/>
<br><br><br>
<div class="container">
   
      <form class="form-horizontal" action="/member/loginPost" method="post" style="text-align:  center; padding-left: 180px;">
        <div class="form-group">
         
          <div class="col-sm-10">
            <input type="text"  id="userId" name="userId" placeholder="MEMBER ID"  >
          </div>
        </div>
        <div class="form-group">
        
          <div class="col-sm-10">
            <input type="password"  id="userPw" name="userPw"  placeholder="PASSWORD">
          </div>
        </div>
      <a href="/member/sign">JOIN</a> <a href="/member/findId">FORGET ID</a> <a href="/member/findPw">FORGET PW</a> 
      <input type="submit" value="LOGIN" style="width: 60px; border: none; margin-left: 20px;">
      </form>
      
  
</div>


</body>
</html>