<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kr.co.domain.MemberDTO"%>
<%@page import="java.util.List"%>
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



   <h1>test4.jsp입니다.</h1>
   <div id="getReplyList"></div>
   
   
   <script type="text/javascript" src="/resources/js/test.js"></script>
   <script type="text/javascript">
      $(document).ready(function() {
               
         
         $.ajax({
            type : "post",
            url : "/ajax/test4",
            data : {
               bno : 1
            },
            dataType: "text",
            success : function(result) {
               var arr = JSON.parse(result);
               
               for(var i=0;i<arr.length;i++){
                  var obj = arr[i];
                  
                  var msg = me3(obj.userId, null, null, obj['email']);
                  $("#getReplyList").append(msg);
                  
               }
            }
         });
         
         
      });
      
      
   
   
   
   
   </script>
   
   
   
   
   
   
</body>
</html>