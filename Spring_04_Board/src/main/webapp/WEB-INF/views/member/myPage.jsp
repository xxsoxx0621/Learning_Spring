<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>

	<c:choose>
		<c:when test="${loginID !=null }">
			<c:forEach var="i" items="${list }">
			<form action="/member/updateMem">
				<table border=1 align=center>
					<tr>
						<th colspan=2>회원가입
					</tr>
					<tr>
						<td>아이디
						<td><input type="text" name="id" id="id" value="${i.id}" disabled> 
						<span id="checkResult"></span>
					</tr>
					<tr>
						<td>비밀번호
						<td><input type="text" name="pw" disabled>
					</tr>
					<tr>
						<td>이름
						<td><input type="text" name="name"  value="${i.name}" >
					</tr>
					<tr>
						<td>전화번호
						<td><input type="text" name="phone" value="${i.phone}" >
					</tr>
					<tr>
						<td>이메일
						<td><input type="text" name="email"value="${i.email}" >
					</tr>
					<tr>
						<td>우편번호
						<td><input type="text" name="zipcode" value="${i.zipcode}" >
					</tr>
					<tr>
						<td>주소1
						<td><input type="text" name="address1" value="${i.address1}" >
					</tr>
					<tr>
						<td>주소2
						<td><input type="text" name="address2" value="${i.address2}" >
					</tr>
					<tr>
						<td colspan=2 align=center>
							<button>회원정보 수정</button>
							<button type="button">home</button>
					</tr>
				</table>
			</form>
			</c:forEach>
		</c:when>
		<c:otherwise>
			접근이 불가합니다.
		</c:otherwise>
	</c:choose>

</body>
</html>