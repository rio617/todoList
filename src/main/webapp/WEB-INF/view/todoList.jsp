<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%
//リクエストスコープに保存したものを呼び出し
List<String> categoies = (List<String>) request.getAttribute("categories");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo登録</title>
<link rel="stylesheet" href="main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
<center>
	<form action="Register" method="post">
		<h1>ToDo登録</h1>
		
		<p class="title">内 容<br>
			<input class="entry" type="text" name="toDo" placeholder="ここにToDoを入力"></p>
		<p class="title">期 限<br>
			<input class="entry" type="date" name="limit"></p>
		<p class="title">カ テ ゴ リ ー 選 択<br>
			<select class="entry" name="category">
				<%
				for (String s : categoies) {
				%>
				<option value=<%=s%>><%=s%></option>
				<%
				}
				%>
			</select></p>
		<p class="title">新 規 カ テ ゴ リ ー 作 成<br>
			<input  class="entry"type="text" name="newcategory"></p>
			
			<br><input class="btn" type=submit value=登録><br><br>
		
	</form>
	<button class="btn" onclick=location.href="MainTodo">ToDoリストへ</button>
	</center>
	</div>
</body>
</html>