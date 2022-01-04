<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<style>
.container{margin:auto;width: 80%;}
#subject > input { width:100%;margin-top: 1%;margin-bottom: 1%;}
#summernote{height:500px;}
.btns {text-align:right;}
</style>
</head>
<body>
	<form action="/board/saveWrite" method="post" id="saveForm" enctype="multipart/form-data">
		<div class="container">
			<input type="text" value="${loginID}" name="writer" hidden>
			<div id="subject">
				<input type="text" placeholder="제목을 입력하세요." name="title" id="title">
				<input type="file" name="file">
			</div>
			<textarea id="summernote" name="contents"></textarea>
			<div class="btns">
				<button class="btn btn-primary" onclick="history.back()" type="button">목록으로</button>
				<button id="save" class="btn btn-primary" type="submit">저장하기</button>
			</div>
		</div>
	</form>
	<script>
		$(document).ready(function() {
			
		  $('#summernote').summernote({
		  	  height: 500,
		  	  placeholder: '내용을 입력하세요.',
		  	  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
			  lang: "ko-KR"
		  });
		});
		/* $("#save").on("click",function(){
		 	
			if($("#title").val('')){
				alert("제목을 입력하세요.");
				return false;
			}
			if($("#summernote").summernote('insertText') == ''){
				alert("내용을 입력하세요.");
				return false;
			} 
			
			if(!$("#title").val('')&& !$("#summernote").text('')){
				$("#saveForm").submit();
			}
		}) */
		
		
	
	</script>

</body>
</html>