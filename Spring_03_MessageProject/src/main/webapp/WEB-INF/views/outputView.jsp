<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 명단 페이지</title>
</head>
<body>
	<table border="1" align=center> 
		<tr>
			<th colspan=4> 메세지 명단
		</tr>
		<tr>
			<th> 번호 
			<th> 작성자
			<th> 메세지 내용
			<th> 작성일자
		</tr>
		<c:forEach var="msg" items="${ list}">
		<tr>
			<td> ${msg.seq }
			<td> ${msg.writer }
			<td> ${msg.message }
			<td> ${msg.write_date }
		</tr>
		</c:forEach>
		<form action="deleteProc" method="post">
		<tr>
				<td colspan=4 align=center><input type="text" name="seq" placeholder="삭제 할 번호를 입력하세요.">
				<button>delete</button>
		</tr>
		</form>
		<form action="updateProc" method="post">
		<tr>
			<td colspan=3 align="center">
			<input type="text" name="seq"  placeholder="변경 할 번호를 입력하세요."><br>
			<input type="text" name="writer"  placeholder="변경 할 작성자를 입력하세요."><br>
			<input type="text" name="message"  placeholder="변경 할 메세지를 입력하세요."><br>
			<td colspan=1 align="center"><button>update</button><br>
		</tr>
		
		</form>
	</table>
</body>
</html>