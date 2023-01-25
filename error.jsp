<%@ page language="java" %>

<html>
	<head>
	<title>DataPot</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	</head>
	
	<% String name = (String)session.getAttribute("username"); %>
	
	<body style="background-color:#ededed">
	<div class="user_name" align="center">
	<br>
	<h2>DataPot</h2><br>
	<p>Dear, <%= name %> </p>
	<p>Information you have <br> entered is wrong...<br><br> Please return to Login Page</p><br>
	<a href="loginuser.jsp"> Login Page </a><br>
	</div>
	</body>
</html>