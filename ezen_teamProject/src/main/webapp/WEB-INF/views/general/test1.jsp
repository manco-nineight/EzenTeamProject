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
	<h1>test1.jsp입니다</h1>
	<button>click</button>
	<p></p>
	
	<script type="text/javascript">
   $(document).ready(function() {
      var test1 = "안녕";
      
      $("button").click(function() {
         $.ajax({
            type: 'post',
            url : "/rest/test1",
            headers:{
               'Content-Type' : 'application/json',
               'X-HTTP-Method-Override':'POST'
            },
            data: JSON.stringify({
               test1 : test1
            }),
            dataType: "text",
            success: function(result) {
               console.log(result);
               $('p').text(result)
            }
         });
      });
   });
</script>
	
</body>
</html>