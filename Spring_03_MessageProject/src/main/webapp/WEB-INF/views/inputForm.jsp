<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>inputForm</title>
</head>
<body>
	
	<form action="inputProc" method="post">
		
	<table border=1 align=center>
		<tr>
			<th colspan="2">메세지 작성폼
		</tr>
		<tr>
			<td>writer
			<td><input type="text" name="writer">
		</tr>
		<tr>
			<td>message
			<td><input type="text" name="message">
		</tr>
		<tr>
			<td colspan=2 align=center><button>submit</button> 
		</tr>
	</table>
	</form>
</body>
</html>