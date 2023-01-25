<%@ page language="java" %>

<html>
	<head>
	<title>DataPot</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<% String name = (String)session.getAttribute("username");
	if(name == null)
	response.sendRedirect("loginuser.jsp");
	String login = (String)session.getAttribute("login");
	if(login == null)
		login="";
	 %>
	</head>

	<body style="background-color:#ededed">
	<% 
	if(login.equals("sfuser")){ %>
		<form action="j_security_check" method="POST" name="sfform">
	<input type="hidden" name="j_password" value="temp"><br>
	<input type="hidden" name="j_username" value="<%=name%>"><br>
	</form>
	<script>
	document.sfform.submit();
	</script>
	<% } else { %>
	<div class="user_name" align="center">
	<br>
	<h2>DataPot</h2><br>
	<p>Hi, <%= name %> </p>
	<p>Password:</p><br>
	<form action="j_security_check" method="POST">
	<input type="password" name="j_password" class="text_box" value=""><br>
	<input type="hidden" name="j_username" value="<%=name%>"><br>
	<input type="submit" value="Login" class="sub-button">
	</form><br><br>
	</div>
	<% } %>
	</body>
</html>