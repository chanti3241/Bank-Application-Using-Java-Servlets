<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Statements</title>
</head>
<body>
	<%
	session = request.getSession();
	out.println("debited from");
	out.println(session.getAttribute("sal"));
	out.println("<br>");
	out.println("credited acco");
	out.println(session.getAttribute("ral"));
	out.println("<br>");
	out.println("Amount");
	out.println(session.getAttribute("al"));
	%>
</body>
</html>