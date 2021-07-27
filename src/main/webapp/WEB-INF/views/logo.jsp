<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>​
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;500;700&display=swap');
body{
font-family: 'Noto Sans KR', sans-serif;
}

*{
	margin: 0;
	padding: 0;
}
ui , li {
	list-style: none;
}
a {
	text-decoration: none;
	color: inherit;
}
.menu_wrap .dep1 > li {
	display: inline-block;
	width: 100px;
	vertical-align : top;
	text-align: center;
	font-size: 13px;
}
.menu_wrap .dep1 > li:hover > .dep2{
	display: block;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}
.menu_wrap .dep1 > li > a {
	display: block;
	padding: 10px 0;
}
.menu_wrap .dep2{
	display: none;
	padding: 10px 0;
} 
.menu_wrap .dep2 a:hover{
	text-decoration: initial;
}
.menu_wrap .dep1 > li {
	display: inline-block;
	width: 100px;
	vertical-align : top;
	text-align: center;
	font-size: 16px;
	position:relative;
}
.logo > a > img {
  width: 300px;
  height: 150px;
  display : block;
  margin : auto;
}
.dep2{
	position: absolute;
	left:0;
	top:40px;
	z-index: 10000;
	width: 100%;
	background : rgba(255,255,255,0.7);
	}

.dep1{
	text-align:center;
}
.prodOrder .dep3 > li {
	display: inline-block;
	width: 100px;
	vertical-align : top;
	text-align: center;
	font-size: 13px;
}
.prodOrder .dep3 > li:hover > .dep4{
	display: block;
	box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
}
.prodOrder .dep3 > li > a {
	display: block;
	padding: 10px 0;
}
.prodOrder .dep4{
	display: none;
	padding: 10px 0;
} 
.prodOrder .dep4 a:hover{
	text-decoration: initial;
}
.prodOrder .dep3 > li {
	display: inline-block;
	width: 100px;
	vertical-align : top;
	text-align: center;
	font-size: 16px;
	position:relative;
}

.dep4{
	position: absolute;
	left:0;
	top:40px;
	z-index: 10000;
	width: 100%;
	background : rgba(255,255,255,0.7);
	}

.dep3{
	text-align:right;
}
.row > img.main{
	width: 900px;
	display: block;
	margin-left: 150px;
	margin-top: 50px;
}
</style>
</head>
<body>
<div class="container">

	<jsp:include page="main_menu.jsp" />

	<br><br>

	<!-- 최신 시즌 페이지 적용 -->
	<div class="row" style="text-align: center;">
		<a href="/"><img  src="/resources/img/new_main.jpg" class="main" width="720px" style="margin-left: 30px"></a>
	</div>
</div><!--container end  -->

<script type="text/javascript">
	$(document).ready(function() {
		var userId = $("#readuserId").val();
		
		$("#moveReadSelf").click(function() {
			$("#readSelf").submit();
		})
	});

</script>
</body>
</html>