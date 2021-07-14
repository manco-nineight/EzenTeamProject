<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    ﻿<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
 <script>

function back(){

     alert("아이디 또는 비밀번호 값이 맞지 않습니다.");

    /*  history.go(-1); // 방법1 */

   /*   history.back(); //방법 2 */

     location.href  ="/member/loginui.do" ; // 방법3

}

</script>



<body onload ="back()"></body>