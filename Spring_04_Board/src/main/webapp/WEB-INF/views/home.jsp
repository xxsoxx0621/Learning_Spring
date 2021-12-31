<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="/css/index.css">
</head>
<body>
	<c:choose>
		<c:when test="${loginID != null }">
			${loginID }님 환영합니다.<br>
			<input type="button" value="게시판" id="toboard">
			<input type="button" id="myPage" value="마이페이지">
			<a href="/member/logout"><input type="button" value="로그아웃"></a>
			<input type="button" id="deleteMem" value="회원탈퇴">
			<script>
			$("#deleteMem").on("click",function(){
				if(confirm("탈퇴 하시겠습니까?")){
					location.href="/member/deleteMem";
				}else{
					loaction.heft="home";
				}
			})
			
			$("#myPage").on("click",function(){
				location.href="/member/myPage";
			})
			
			$("#toboard").on("click",function(){
				location.href="/board/list";
			})
			
			</script>
		</c:when>
		<c:otherwise>
		<form action="/member/login" method="post">
		<table border=1 align=center>
			<tr>
				<th>Login
			</tr>
			<tr>
				<th><img src="/images/img.jpeg">
			</tr>
			<tr>
				<td align=center><input type="text" name=id placeholder="input your id">
			</tr>
			<tr>
				<td align=center><input type=password name=pw placeholder="input your pw">
			</tr>
			<tr>
				<td align=center><button>Login</button>
					<button type="button" id="join">Join</button>
			</tr>
		</table>
	</form>
		</c:otherwise>
	</c:choose>
	
	<script>
		$("#join").on("click", function() {
			location.href = "/member/join";
		})
	</script>
</body>
</html>