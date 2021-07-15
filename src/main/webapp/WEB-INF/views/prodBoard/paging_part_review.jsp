<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="/review/reply/${vo.prodBno}?page=${to.curPage-1 > 0 ? to.curPage-1 : 1}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    
	<c:forEach begin="${to.beginPageNum}" end="${to.finishPageNum}" var="page">
    <li class="${to.curPage == page ? 'active':''}"><a href="/review/reply/${vo.prodBno}?page=${page}">${page}</a></li>	
	</c:forEach>
   
    <li>
      <a href="/review/reply/${vo.prodBno}?page=${to.curPage+1 <= to.totalPage ? to.curPage+1 : to.curPage}"aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>