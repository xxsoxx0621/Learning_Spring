<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
	<form action="/file/upload" method="post" enctype="multipart/form-data">
		<input type="text" name="title"><br>
		<input type="text" name="contents"><br>
		<input type="file" name="file" multiple><br>
		<button>작성완료</button>
	</form>
</body>
</html>