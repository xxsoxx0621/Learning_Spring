<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Free Board</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="//cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<link  rel="stylesheet" href="//cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
</head>
<body>
	<table id="myTable" class="display" style="width:100%">
       <thead>
            <tr>
                <th>
                <th>제목
                <th>작성자
                <th>작성일
                <th>
            </tr>
        </thead>
        <tbody>
         <c:forEach var="i" items="${list }">
         	<tr>
         		<td>${i.seq }
         		<td><a href="/board/detail?seq=${i.seq }">${i.title }</a>
         		<td>${i.writer }
         		<td>${i.write_date }
         		<td>${i.view_count }
         	</tr>
         </c:forEach>
        </tbody>
        <tfoot>
         <tr>
         	<td colspan=5 align="right"><button id="write">글쓰기</button>
         </tr>
        </tfoot>
        
    </table>
   <script>
   	$("#myTable").DataTable({
   		/* sorting:desc */
   	  order: [[0, 'desc']],
   	  ordering: true,
   	  bStateSave: true
   	});
   	$("#write").on("click",function(){
   		location.href="/board/writeForm";
   	})
   </script>
</body>
</html>