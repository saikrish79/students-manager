<%@ page language="java" %>
<%@ page import="java.io.*, java.sql.*, java.util.*, java.lang.*,com.sapp.*" %>

<html>
	<head>
	<title>StudentDetail App</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Exo+2|Marcellus+SC&display=swap" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="mainscript.js"></script>
	</head>
	<%! int count,last,prev=0,nxt=0;%>
	<%
	//session.setAttribute("professorlogin","active");
	String searchbox = "";
	String login = (String)session.getAttribute("professorlogin");
	if(login == null)
		login="";
	try{

	String s = (String)session.getAttribute("cntrl");
	if(s!="nopass"){
	Utility ut = new Utility();
	ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();
	//list = ut.getList();
	session.setAttribute("list",list);
	session.setAttribute("order","1");
	session.setAttribute("id","");session.setAttribute("name","");session.setAttribute("no","");session.setAttribute("dept","");
	session.setAttribute("clg","");session.setAttribute("gen","");session.setAttribute("mail","");session.setAttribute("phn","");
	session.setAttribute("ph","");session.setAttribute("ch","");session.setAttribute("cmp","");session.setAttribute("mat","");
	session.setAttribute("eng","");
	session.setAttribute("sort","inactive");
	session.setAttribute("search","inactive");
	session.setAttribute("offset","0");
	//response.sendRedirect("ProcessServlet");
	}
	}
	catch(Exception e){}
	%>
	<body>
	<div class="top-container">
	<h1 class="title">StudentDetail Manager</h1>
	<form action="LogoutServlet" method="post" class="logout"><span style="color:white"><%=(String)session.getAttribute("username")%>	</span><input type="submit"	value="Logout" class="sub-button"></form>
	</div>
	<div class="datatable">

	<br><br>
	<% if(!login.equals("active")){ %>
	<form style="display:inline" action="DetailEditor.jsp" method = "post">
	<input type="hidden" name="stdid" value="null">
	<input type="submit" value="Add Detail" class="sub-button">
	</form>
	<form style="display:inline" action="DeleteAllServlet" method = "post">
	<input type="submit" value="Delete DataBase" class="sub-button">
	</form>
	<form style="display:inline" action="DataCheckServlet" method = "post">
	<input type="submit" value="Professors" class="sub-button">
	</form>
	<form style="display:inline" action="AdvancedSearch.jsp" method = "post">
	<input type="submit" value="Advance Search" class="sub-button">
	</form>
	<% }
	else {
		searchbox = "disabled"; %>
		<form style="display:inline" action="result.jsp" method = "post">
		<input type="submit" value="Professor Detail" class="sub-button">
		</form>
	<% } %>
	<br>
	<table id="tab" class="a">
	<tr>
		<th onclick="sortfunc(1)">StudentID</th>
		<th onclick="sortfunc(3)">Name</th>
		<th onclick="sortfunc(2)">Enroll No</th>
		<th onclick="sortfunc(4)">Dept</th>
		<th onclick="sortfunc(5)">College</th>
		<th onclick="sortfunc(6)">Gender</th>
		<th onclick="sortfunc(7)">Email</th>
		<th onclick="sortfunc(8)">Phone</th>
		<th onclick="sortfunc(9)">Physics</th>
		<th onclick="sortfunc(10)">Chemistry</th>
		<th onclick="sortfunc(11)">Computer</th>
		<th onclick="sortfunc(12)">Mathematics</th>
		<th onclick="sortfunc(13)">English</th>
	<form action="ProcessServlet" method="post"id="sortForm"><input type="hidden" name="search" value=""><input type="hidden" name="sort" value="sort"><input type="hidden" id="val" name="option" value= ""></form>
	</tr>

	<tr>
	<form id="searchForm">
	<td><input type="text" name="stdid" value=""></td>
	<td><input type="text" name="name" value=""></td>
	<td><input type="text" name="enroll" value=""></td>
	<td><input type="text" name="dept" value="" <%=searchbox%> ></td>
	<input type="hidden" name="dept" value="<%= (String)session.getAttribute("dept") %>" >
	<td><input type="text" name="clg" value=""></td>
	<td><input type="text" name="gender" value=""></td>
	<td><input type="text" name="email" value=""></td>
	<td><input type="text" name="phone" value=""></td>
	<td><input type="text" name="phy" value=""></td>
	<td><input type="text" name="chem" value=""></td>
	<td><input type="text" name="comp" value=""></td>
	<td><input type="text" name="math" value=""></td>
	<td><input type="text" name="eng" value=""></td>
	<td><input type="hidden" name="search" value="search"><input type="hidden" name="sort" value=""><<input type="button" value="Search" onclick="searchfunc()" ></td></form>
	<form id="cancelsearchform">
	<td><input type="hidden" value="Reset" name="CancelSearch" ><input type="button" value="Cancel" onclick="cancelSearch()"></td></form>
	</tr>
	</table>
	</div>

 	<iframe id="iframeTable" height="200px" width="2430px" src="table.jsp" frameborder="0" scrolling="no"></iframe>

	<div align="center" class="pagination">
	<input style="display:inline" type="button" value="First" onclick="pagination('first')" >
	<input style="display:inline" type="button" value="Prev" onclick="pagination('prev')" >
	<input style="display:inline" type="button" value="Next" onclick="pagination('next')" >
	<input style="display:inline" type="button" value="Last" onclick="pagination('last')" >
	</div>

	<% session.setAttribute("cntrl","pass"); %>
	</body>
</html>
