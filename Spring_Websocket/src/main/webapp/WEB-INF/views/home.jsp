<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<style>
	*{box-sizing:border-box;}
	div{border:1px solid black;}
	#chat-box{
		width:300px;
		height:300px;
		margin:auto;
	}
	#chat-box #chat-contents{
		height:80%;
	}
	#chat-box .chat-input{
		height:20%;
	}
	#chat-box .chat-input div{
		float:left;
	}
	#chat-box .chat-input .input-message{
		width:80%;
		height:100%
	}
	#chat-box .chat-input .input-button{
		width:20%;
		height:100%;
	}
	#chat-box .chat-input .input-message textarea{
		width:100%;
		height:100%;
	}
	#chat-box .chat-input .input-button button{
		width:100%;
		height:100%;
	}
</style>
</head>
<body>
	
	<div id="chat-box">
		<div id="chat-contents">
		</div>
		<div class="chat-input">
		<div class="input-message">
			<textarea id="message"></textarea>
		</div>
		<div class="input-button">
			<button id="send">Send</button>
		</div>
		</div>
	</div>
	<script>
		let ws = new WebSocket("ws://192.168.35.208/chat"); // 서버에 통신을 보내기 위한 통로 ,  url과 웹소켓의 경로가 맞춰줘야 한다.
		
		ws.onmessage = function(e){
			
			console.log("On Message works");
			
			let line = $("<div>");
			line.append(e.data);
			
			$("#chat-contents").append(line);
		}
		$("#send").on("click",function(){
			let text = $("#message").val();
			/* console.log(text); */
			$("#message").val("");
			ws.send(text); // 내용 전송
		})	
		
		
	</script>
</body>
</html>