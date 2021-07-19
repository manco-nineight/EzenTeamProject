//파일 확장자 확인하기
function checkImageType(filename) {
	var idx = filename.lastIndexOf(".") + 1
	var extendName = filename.substring(idx).toUpperCase();
	if (extendName == "PNG" || extendName == "JPEG" || extendName == "GIG" || extendName == "JPG") {
		return true;
	} else {
		return false;
	}

}

//원본 파일 찾아서 보여주기  // 식별코드 +_이름
function getLinkText(filename) {

	if (checkImageType(filename)) {
		var idx = filename.indexOf("_s_");
		var prefix = filename.substring(0, idx);
		var suffix = filename.substring(idx + 2);
		filename = prefix + suffix;
	}

	return "/displayFile?filename=" + filename;
}

//썸네일이나 그냥 파일의 원래 이름 찾기 // 파일명.확장자
function getOriginalName(filename) {
	var idx = -1;

	if (checkImageType(filename)) {
		idx = filename.indexOf("_s_") + 3;
	} else {
		idx = filename.indexOf("_") + 1;
	}

	return filename.substring(idx);
}



//파일 전송하기 위래 인풋테그에 파일값과 인덱스 번호 부여하기
function getFileUploadFilenameInput(filename, index) {


	var msg = `
 <input value="${filename}" type="hidden" name="files[${index}]">
			
 
 
 
`;
	return msg
}

//수정창에서 파일을 업로드하는 창
function uploadUpdateForm(getLinkText, filename, getOriginalName) {
	var src = '';
    
    //파일명에 존재하는 확장자값이 체크파일에서 트루값이 나오면 해당 파일을 디스플레이에 띄움
	if (checkImageType(filename)) {
		src = "/displayFile?filename=" + filename;
		
	//그렇지 않으면 기본썸네일 파일을 대신 띄움	
	}else{
	src = "/resources/img/etc.png";
	}


	var msg = `
    <div class='col-xs-4 delDiv'>
      <a target="_blank" href="${getLinkText}">
         <img src="${src}">
      </a>
      <p class="ellpsisTarget"><small class="delFile" data-filename="${filename}" style="border:1px solid red;cursor:pointer;">x</small>${getOriginalName}</p>
   </div>
    `;

	return msg;
}





function uploadViewForm(getLinkText, filename, getOriginalName) {
	var src = '';

	if (checkImageType(filename)) {
		src = "/displayFile?filename=" + filename;
	} else {
		src = "/resources/img/etc.png";
	}


	var msg = `
    <div class='col-xs-4' style='margin: 20px 0;'>
   <a target="_blank" href="${getLinkText}">
      <img src="${src}">
   </a>
   <p class="ellpsisTarget">${getOriginalName}</p>
</div>
    `;

	return msg;
}



/*---------------------------------------------------------------------------------------------------------------- */
function me4(rno, replyer, updatedate, replytext, userId) {

    var disabled = replyer==userId? '':'disabled';
	var msg = `
   <div class="panel panel-primary">
         <div class="panel-heading"><span>rno: ${rno}</span> <span class="glyphicon glyphicon-user" aria-hidden="true"></span><span>${replyer}</span> <span class="pull-right"><span class="glyphicon glyphicon-time" aria-hidden="true"></span>${updatedate}</span></div>
         <div class="panel-body">
            <p>${replytext}</p>
            <div data-rno='${rno}' data-replyer ='${replyer}'>
               <a class="${disabled} btn btn-warning btn-xs reply_btn_update_form" data-toggle="modal" data-target="#myModal" >수정</a>
               <a class="${disabled} btn btn-danger btn-xs reply_btn_delete">삭제</a>
            </div>
         </div>
      </div>
   `;

	return msg;
}


function me3(userId, userPw, userName, email) {
	var msg = `<div class="panel panel-primary">
      <div class="panel-heading">
         아이디: ${userId} <span class="pull-right">비밀번호: ${userPw}</span>
      </div>
      <div class="panel-body">
         <h3>이름: ${userName}</h3>
         <h5>이메일: ${email}</h5>
      </div>
   </div>`;

	return msg;

}

