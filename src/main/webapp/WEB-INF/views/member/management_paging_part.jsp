<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="/member/managementlist/${to.curPage-1>0?to.curPage-1:1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    
    <c:forEach begin="${to.beginPageNum}" end="${to.finishPageNum}" var="i">
    
    <li class="${to.curPage == i?'active':''}"><a href="/member/managementlist/${i}">${i}</a></li>
   
    </c:forEach>
    
    <li>
      <a href="/member/managementlist/${to.curPage+1<=to.totalPage?to.curPage+1:to.curPage}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
