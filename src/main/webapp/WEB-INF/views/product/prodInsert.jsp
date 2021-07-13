<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prodInsert</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- <script src="../resources/js/summernote-ko-KR.js"></script> -->
<script src="../resources/js/summernote-lite.js"></script>
<link rel="stylesheet" href="../resources/css/summernote-lite.css">

<style type="text/css">
	#thumbnail {
		width: 200px;
		height: 200px;
		border: solid 1px;
	}
	
</style>

</head>
<body>

	<div class="container">
		<div>
			<h1 class="jumbotron">prodINSERT</h1>
		</div>
	
		<div id="thumbnail">
		</div> <!-- end of thumbnial class -->
			
			
			<form id="thumbnail_insert" class="thumbnail_form" action="/product/prodInsert" enctype="multipart/form-data" method="post">
				<input accept="image/*" class="thumbnail_insert" type="file" name="prodThumbnailFile" onchange="setThumbnail(event)"/>
			</form>
		
		<br>
		<br>

		<div class="product_basic">
			<form id="product" action="/product/prodInsert" method="post">
				prodName: <input id="prodName" type="text" name="prodName" oninput="duplicationCheck()"> 
				prodCategory: 
				<select name="prodCategory">
					<option value="top">TOP</option>
					<option value="bottom">BOTTOM</option>
					<option value="outer">OUTER</option>
					<option value="acc">ACC</option>
				</select>
				prodTitle: <input type="text" name="prodTitle"> 
				prodPrice: <input name="prodPrice">
				(S)SIZE: <input name="prodStockSSize">
				(M)SIZE: <input name="prodStockMSize">
				(L)SIZE: <input name="prodStockLSize">
				<textarea id="summernote" name="prodContent"></textarea>
			</form>
		</div> <!-- end to the row class -->
	</div> <!-- end of the container class -->
	
	<button class="prodInsert_btn">SAVE</button>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#summernote").summernote({
				placeholder: "content",
				minHeight: 500,
				maxHeight: null,
				focus: true,
				
				/* image upload*/
				callbacks : {
					onImageUpload: function(files, editor, welEditable) {
					    sendFile(files[0], this);
					}
				} 
				
			}); /* end of summernote setting */
			
			
			$(".prodInsert_btn").click(function(event) {
				event.preventDefault();
				
				var $children = $("#product").children();
				$("#thumbnail_insert").append($children);

				$("#thumbnail_insert").submit();
				
			}); /* end of submit */
			
			
		}); /* end of document */
		
		/* after callbacks data work with controller */
		function sendFile(file, el) {
			var formData = new FormData();
			formData.append("file", file);
			$.ajax({
				data : formData,
				type: "POST",
				url: "/product/prodFile",
				processData : false,
				contentType : false,
				enctype: "multipart/form-data",
				success : function(data) {
						$(el).summernote("insertImage", data);
						/* $("#summernote").append('<img src='+data.url+'/>'); */
				}
			});
		} /* end of sendFile function */
		
		$(".thumbnail").on("dragenter dragover drop", function(event) {
			event.preventDefault();
		});
		
		/* thumbnail preview */
		function setThumbnail(event) {
			var reader = new FileReader();
			$("#thumbnail").empty();
			reader.onload = function(event) {
				var img = document.createElement("img");
				console.log(img);
				img.setAttribute("src", event.target.result);
				img.setAttribute("width", 200);
				img.setAttribute("height", 200);
				document.querySelector("div#thumbnail").appendChild(img);
			};
			reader.readAsDataURL(event.target.files[0]);
		}

		function duplicationCheck() {
			
			$.ajax({
				type: "post",
				data: { 
					"prodName" : $("#prodName").val() 
					},
				url: "/product/prodNameDupCheck",
				dataType: "text",
				success: function(result) {
					if (result == 1) {
						$("#prodName").css("background-color","#DC143C");
						$("#pordName").css("opacity", "0.5");
					} else {
						$("#prodName").css("background-color", "#BFFF00");
						$("#pordName").css("opacity", "0.5");
					}
				}
				
				
				
				
			});
		}
		
		
	</script>



</body>
</html>