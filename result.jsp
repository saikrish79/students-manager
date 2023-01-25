<%@ page language="java" %>
<%@ page import="java.io.*, java.sql.*, java.util.*, java.lang.*,com.sapp.*" %>

<html>
	<head>
	<title>StudentDetail App</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Exo+2|Marcellus+SC&display=swap" rel="stylesheet">   
	</head>
	
	<body>
	<% String name=null,email=null,username=null,zone=null,id=null;
		name = (String)session.getAttribute("userfullname");
		email = (String)session.getAttribute("useremail");
		zone = (String)session.getAttribute("userzone");
		id = (String)session.getAttribute("userid");
		username = (String)session.getAttribute("username");
	%>
	<div class="top-container">
	<h1 class="title">StudentDetail Manager</h1>
	<form action="LogoutServlet" method="post" class="logout"><span style="color:white"><%=(String)session.getAttribute("username")%>	</span><input type="submit"	value="Logout" class="sub-button"></form>
	</div><br><br>
	<form style="display:inline" action="index.jsp" method = "post">
	<input type="submit" value="HomePage" class="sub-button">
	</form>
	<p>Logged in user : <%=username%> </p><br>
	<p>Name : <%=name%> </p><br>
	<p>Email : <%=email%> </p><br>
	<p>Zone : <%=zone%> </p><br>
	<p>ID : <%=id%> </p><br>
	</body>
</html>