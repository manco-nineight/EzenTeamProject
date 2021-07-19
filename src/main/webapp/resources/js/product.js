/**
 * 
 */

 
function star(parentTagmsg){
	var parentTag = $(parentTagmsg);

	var msg = `

		<span data-idx="0" class="glyphicon glyphicon-star star bright" aria-hidden="true"></span>
		
		<span data-idx="1" class="glyphicon glyphicon-star star" aria-hidden="true"></span>
		
		<span data-idx="2" class="glyphicon glyphicon-star star" aria-hidden="true"></span>
		
		<span data-idx="3" class="glyphicon glyphicon-star star" aria-hidden="true"></span>
		
		<span data-idx="4" class="glyphicon glyphicon-star star" aria-hidden="true"></span>

`;

 

	parentTag.html(msg);	

 
	$(".star").hover(function(event) {

		var idx = $(this).attr("data-idx");
		
		parentTag.attr("data-val", parseInt(idx)+1);
 		
		$(".star").each(function(index) {
		
			if(index <= idx){
				$(this).addClass("bright");
			}else{
				$(this).removeClass("bright");
			}
		
		});
	});
			
			
}
 
 function makeReviewPage(beginPageNum, finishPageNum, curPage){
   var str = '';      
   
    for(var i=beginPageNum; i<=finishPageNum; i++){
      var active = i == curPage? "active" : "" ;
          str += '<li><a class="pageNum '+active+'" href="#">'+i+'</a></li>';
         
    }
      
      
var reMsg =
`
<nav aria-label="Page navigation">
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous" class="prev" id="prev">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>

    ${str}
   
   <li>
      <a href="#" aria-label="Next" class="next" id="next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>

  </ul>
</nav>
`;   
   return reMsg;
}

function reviewList(list){
	
	      for(var i=0; i < list.length; i++){

         $("#reviewList").append("<div></div>");
         var parentTag = $("#reviewList > div:last-child");
         review(list[i], parentTag);
      }
}

function review(obj, parentTag){
	var reRno = obj['reRno'];
	
	var reWriter =  obj['reWriter'];
	
	var reStar = obj['reStar'];
	
	var reUpdatedate =  obj['reUpdatedate'];
	
	var reContent =  obj['reContent'];

	var arrReStar = [reStar >=1?'bright':"" , reStar >=2?'bright':"" , reStar >=3?'bright':"" , reStar >=4?'bright': "" , reStar >=5?'bright':""];

   var msg = `
   <div class="panel panel-info">
         <div class="panel-heading">
         <span>reRno: ${reRno}</span>
           <span class="glyphicon glyphicon-user" aria-hidden="true"></span><span>${reWriter}</span>
           <span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${reUpdatedate}</span> 
          
            <span class="pull-right starList"> 
           <span data-idx="0" class="glyphicon glyphicon-star  ${arrReStar[0]}" aria-hidden="true"></span>
			<span data-idx="1" class="glyphicon glyphicon-star  ${arrReStar[1]}" aria-hidden="true"></span>
			<span data-idx="2" class="glyphicon glyphicon-star  ${arrReStar[2]}" aria-hidden="true"></span>
			<span data-idx="3" class="glyphicon glyphicon-star  ${arrReStar[3]}" aria-hidden="true"></span>
			<span data-idx="4" class="glyphicon glyphicon-star  ${arrReStar[4]}" aria-hidden="true"></span>
	       </span>
	      
         </div>
         <div class="panel-body">
            <p>${reContent}</p>
            <div data-reRno='${reRno}' data-reWriter ='${reWriter}'>
               <a class="btn btn-warning btn-xs review_btn_update_form" data-toggle="modal" data-target="#myModal">수정</a>
               <a class="btn btn-danger btn-xs review_btn_delete">삭제</a>
              
            </div>
         </div>
      </div>
   `;
   
   parentTag.append(msg);
   
   $(".arrReStar").each(function(index) {

	if(index < reStar){
	
		$(this).addClass("bright");
	}else{
		$(this).removeClass("bright");
	
	}
	
	}); 
   

}

  