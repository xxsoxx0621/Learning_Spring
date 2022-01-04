<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet" href="/css/style.css">
</head>
<body>
	세션값 : ${loginID }
	<table border=1 align=center>
	<tr>	
		<th colspan="4">
		<img src="/images/camp4.jpeg">
	</tr>
	<tr>	
		<th colspan="4">Index
	</tr>
	<tr>
		<td><a href="toInput">toInput</a>
		<td><a href="toOutput">toOutput</a>
		<td><a href="toSearch">toSearch</a>
		<td><a href="login">Login</a>
	</tr>
	</table>
</body>
</html>