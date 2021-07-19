<%@ page language="java" contentType="text/html; charset=UTF-8"

pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

​

<c:forEach begin="1" end="${to.totalPage}" var="page">

​

<a class="${page == to.curPage?'active':''}" href="/board/list.do?curPage=${page}">${page}</a>

​

</c:forEach>