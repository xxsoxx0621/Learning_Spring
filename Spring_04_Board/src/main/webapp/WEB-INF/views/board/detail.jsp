<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 내용</title>
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
	
		
		<div class="container">
		
		  <c:forEach var="i" items="${seqList }">
		  <form action="updateProc" method="post">
			<div id="subject">
				<input type="text" name="seq" value="${i.seq }" hidden>
				<input type="text" placeholder="제목을 입력하세요." id="title" name="title" value="${i.title }" readonly>
			</div>
			<textarea id="summernote" name="contents" >${i.contents }</textarea>
			<c:choose>
			<c:when test="${loginID == i.writer }">
				<div class="btns">
					<button id="edit" class="btn btn-primary" onclick="edit()" type="button">수정하기</button>
					<script>
						$("#edit").on("click",function(){
								$("#title").prop("readonly",false);
								$('#summernote').summernote('enable');
								$("#delete").attr("style","display:none");
								$("#edit").attr("style","display:none");
								$("#updateDone").attr("style","display:inline-block");
											
						});
					</script>
					<button id="updateDone" class="btn btn-primary" type="submit" style="display:none">수정완료</button>
					<button id="delete" class="btn btn-primary" onclick="delete()" type="button">삭제하기</button>
					<script>
						$("#delete").on("click",function(){
							if(confirm("정말 삭제하시겠습니까?")){
								location.href="/board/deleteProc?seq=${i.seq}";	
							}
							
						})
					</script>
					<button id="back" class="btn btn-primary" type="button">뒤로가기</button>
					
				</div>
			
			</c:when>
			<c:otherwise>
				<div class="btns">
					<button id="back" class="btn btn-primary" type="button">뒤로가기</button>
				</div>
			</c:otherwise>
			</c:choose>
			</form>
			</c:forEach>
		</div>
	<script>
		$(document).ready(function() {
			// 서머노트 쓰기 비활성화
		    $('#summernote').summernote({
		  	  height: 500 });
		    $('#summernote').summernote('disable');
		});
		
		$("#back").on("click",function(){
			location.href ="list";
		})
	
	
	</script>

</body>
</html>