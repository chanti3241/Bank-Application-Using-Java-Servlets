<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view balance</title>
</head>
<body>
	<%
	session = request.getSession();
	out.println(session.getAttribute("balance"));
	%>
</body>
</html>