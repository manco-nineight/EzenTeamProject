/**
 * 
 */
 function makePage(beginPageNum, finishPageNum, curPage){
	var str = '';      
	var checkActive; 
	for(var i = beginPageNum; i<=finishPageNum; i++){
		checkActive = (curPage == i ? 'active' : '');
		str += '<li><a class="pageNum ' + checkActive + '" href="#" id="' + i + '">' + i + '</a></li>';
	}
      
	var msg =`
				<nav aria-label="Page navigation">
  					<ul class="pagination">
    					<li style="float: left;">
      						<a href="#" aria-label="Previous" class="prev" id="prev">
        						<span aria-hidden="true">&laquo;</span>
      						</a>
    					</li>
						${str}
						<li style="float: right;">
							<a href="#" aria-label="Next" class="next" id="next">
								<span aria-hidden="true">&raquo;</span>
							</a>
						</li>
					</ul>
				</nav>
			`;
			
   return msg;
}

  function getList(qTitle, qWriter, qUpdatedate, qContent, qQno, qRepindent, qUserId)
 {
 	var msg = `
 				<div class="row" style="margin-top: 0px;">
 					<div class="userId" style="display: none;" id="qUserId">${qUserId}</div>
					<a style="float: left; margin-bottom: 0px;" class="toggleText">
					${qTitle}
					</a>
					&nbsp;<img class="included" src="/resources/img/private.png">
					<div style="float: right; margin-right: 30px;">
						<b>${qWriter}</b>&nbsp;&nbsp;&nbsp;&nbsp; 
						${qUpdatedate}
					</div>
				</div>
				<div class="collapse"><hr></div>
				<div class="collapse passwordBox" style="word-break: break-all; margin: auto; width:550px; height: 150px;">
					<br>
					<div style="text-align: center">비밀번호를 입력해 주세요</div>
					<br>
					<div class="inputSet" style="text-align: center; margin-left: 55px; margin-top: 0px;">
						<input type="password" class="insertPw"/>
						<button type="button" class="btn btn-secondary enter" style="margin-left: 10px;  margin-top: -2.5px;">
							<span class="glyphicon glyphicon-ok"></span>
						</button>
					</div> 
				</div>
				<div class="collapse contentBox" style="display: none;word-break: break-all; margin: auto; width:550px; height: 150px; position: relative;">
					<div class="contentText">${qContent}</div>
					<div class="buttonDiv" style="position: absolute; right:0px; bottom: 0px;">
					</div>
					<div class="qQno" style="display: none" id="qQno">${qQno}</div>
					<div class="qRepindent" style="display: none" id="qRepindent">${qRepindent}</div>
				</div>
				<hr>
			`;
			
	return msg;
 }
 
   function getAnswerList(qTitle, qWriter, qUpdatedate, qContent, qQno, qRepindent, qUserId)
 {
 	var msg = `
 				<div class="row" style="margin-top: 0px;">
 					<div class="userId" style="display: none;" id="qUserId">${qUserId}</div>
					<a style="float: left; margin-bottom: 0px;" class="toggleText">
					&nbsp;&nbsp;&nbsp;↪&nbsp;${qTitle}
					</a>
					&nbsp;<img class="included" src="/resources/img/private.png">
					<div style="float: right; margin-right: 30px;">
						<b>${qWriter}</b>&nbsp;&nbsp;&nbsp;&nbsp; 
						${qUpdatedate}
					</div>
				</div>
				<div class="collapse"><hr></div>
				<div class="collapse passwordBox" style="word-break: break-all; margin: auto; width:550px; height: 150px;">
					<br>
					<div style="text-align: center">비밀번호를 입력해 주세요</div>
					<br>
					<div class="inputSet" style="text-align: center; margin-left: 55px; margin-top: 0px;">
						<input type="password" class="insertPw"/>
						<button type="button" class="btn btn-secondary enter" style="margin-left: 10px;  margin-top: -2.5px;">
							<span class="glyphicon glyphicon-ok"></span>
						</button>
					</div> 
				</div>
				<div class="collapse contentBox" style="display: none;word-break: break-all; margin: auto; width:550px; height: 150px; position: relative;">
					<div>${qContent}</div>
					<div class="buttonDiv" style="position: absolute; right:0px; bottom: 0px;">
					</div>
					<div class="qQno" style="display: none" id="qQno">${qQno}</div>
					<div class="qRepindent" style="display: none" id="qRepindent">${qRepindent}</div>
				</div>
				<hr>
			`;
			
	return msg;
 }