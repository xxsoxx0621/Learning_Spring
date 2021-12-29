<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function(){
		$("#id").on("blur",function(){
			$.ajax({
				url:"/member/idDuplCheck",
				data:{id:$("#id").val()}
			}).done(function(resp){
				if(resp == "1"){
					$("#checkResult").css("color","pink");
					$("#checkResult").text($("#id").val() + "는 이미 사용중인 ID 입니다.");
					$("#id").val("");
					$("#id").focus();
				}else{
					$("#checkResult").css("color","dodgerblue");
					$("#checkResult").text("사용 가능한 ID 입니다.");
				}
			});
		});
	})

</script>
</head>
<body>
	<form action="signUpProc" method="post">
		<table border=1 align=center >
			<tr>
				<th colspan=2>회원가입 
			</tr>
			<tr>
				<td>아이디
				<td><input type="text" name="id" id="id" required>
				<span id="checkResult"></span>
			</tr>
			<tr>
				<td>비밀번호
				<td><input type="text" name="pw" required>
			</tr>
			<tr>
				<td>이름
				<td><input type="text" name="name" required>
			</tr>
			<tr>
				<td>전화번호
				<td><input type="text" name="phone" required>
			</tr>
			<tr>
				<td>이메일
				<td><input type="text" name="email" required>
			</tr>
			<tr>
				<td>우편번호
				<td><input type="text" name="zipcode">
			</tr>
			<tr>
				<td>주소1
				<td><input type="text" name="address1">
			</tr>
			<tr>
				<td>주소2
				<td><input type="text" name="address2">
			</tr>
			<tr>
				<td colspan=2 align=center> <button>Sign up</button>
				 <button type="button"> home </button>
			</tr>
		</table>
	</form>
</body>
</html>