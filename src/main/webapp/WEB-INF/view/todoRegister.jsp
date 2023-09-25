<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.ToDoRegistration"%>
<%
	List<ToDoRegistration> list = (List<ToDoRegistration>)request.getAttribute("list");
	String msg=(String)request.getAttribute("msg");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ToDoリスト</title>
<link rel="stylesheet" href="main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"
        integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        
</head>

<body>
<div class="container">
<center>
	<%if(msg != null){ %>
	<p><%=msg %></p>
	<%} %>
	
	<button class="btn" onclick=location.href="Register">新規ToDoを登録</button>
	<form action="Delete" method="post">
	<table border="2">
	<caption><h1>ToDoリスト</h1></caption>
	
		<tr>
			<th class="thcheckBox"></th>
			<th>内容</th>
			<th>期限</th>
			<th>カテゴリー</th>
			<th>ステータス</th>
		</tr>

		<%for(ToDoRegistration t : list){ %>
		<tr>
			<td><label class="CheckboxLabel">
			<input type="checkbox" class="CheckboxInput" name="check" value="<%=t.getId()%>">
			<span class="CheckboxSpan"></span></td></label>
			<td class="moji1"><%=t.getToDo() %></td>
			<td class="moji2"><%=t.getLimit() %></td>
			<td class="moji3"><%=t.getCategory() %></td>
			<td><button class="compbtn" type="button">完了</button>
			<button  class="deletebtn" onclick=location.href="Delete?id=<%=t.getId()%>">削除</button></td>
		</tr>
		<%}%>
	</table>
<br><button class="btn" type="submit">チェックしたToDoをまとめて削除</button>
</form>
<br><button class="btn" onclick=location.href="Register">新規ToDoを登録</button>
</div>

 <script>
        $(".compbtn").click(($target) => {
            
            let num = $(".compbtn").index($target.target);
            console.log(num);
            $(".moji1").eq(num).toggleClass("line-through");
            $(".moji2").eq(num).toggleClass("line-through");
            $(".moji3").eq(num).toggleClass("line-through");
        });
</script> 
</center>
</body>
</html>