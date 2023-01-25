<%@ page language="java" %>
<%@ page import="java.io.*, java.sql.*, java.util.*, java.lang.*,com.sapp.*" %>

<html>
	<head>
	<title>StudentDetail App</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Exo+2|Marcellus+SC&display=swap" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="advsearchscript.js"></script>
	</head>
	<%
	try{
	session.setAttribute("advancesession","active");
	String s = (String)session.getAttribute("advancesearch");
	if(s == null){
		session.setAttribute("searchcount","0");
	}
	if(s!="nopass"){
	Utility ut = new Utility();
	ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();
	session.setAttribute("list",list);
	session.setAttribute("order","1");
	session.setAttribute("id","");session.setAttribute("name","");session.setAttribute("no","");session.setAttribute("dept","");
	session.setAttribute("clg","");session.setAttribute("gen","");session.setAttribute("mail","");session.setAttribute("phn","");
	session.setAttribute("ph","");session.setAttribute("ch","");session.setAttribute("cmp","");session.setAttribute("mat","");
	session.setAttribute("eng","");
	session.setAttribute("sort","inactive");
	session.setAttribute("search","inactive");
	session.setAttribute("offset","0");
	//response.sendRedirect("AdvanceSearchServlet");
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

	<form id="advanceSearchForm">
<!--	<input type="button" value="Add Search Criteria" onclick="addsearchbar(1)" class="sub-button"> -->
	<div id="searchbar"></div>
	<br>
	<input type="hidden" name="count" id="count" value="" >
	<input type="button" onclick="submitsearch()" value="search">
	</form>

	<form action="ProcessServlet" method="post">
	<input type="hidden" value="Reset" name="CancelAdvanceSearch" >
	<input type="submit" value="Cancel"></form>

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
	<form id="sortForm"><input type="hidden" name="search" value=""><input type="hidden" name="sort" value="sort"><input type="hidden" id="val" name="option" value= ""></form>
	</tr>

	<tr>
	<form id="searchForm">
	<td><input type="text" name="stdid" value=""></td>
	<td><input type="text" name="name" value=""></td>
	<td><input type="text" name="enroll" value=""></td>
	<td><input type="text" name="dept" value=""></td>
	<td><input type="text" name="clg" value=""></td>
	<td><input type="text" name="gender" value=""></td>
	<td><input type="text" name="email" value=""></td>
	<td><input type="text" name="phone" value=""></td>
	<td><input type="text" name="phy" value=""></td>
	<td><input type="text" name="chem" value=""></td>
	<td><input type="text" name="comp" value=""></td>
	<td><input type="text" name="math" value=""></td>
	<td><input type="text" name="eng" value=""></td>
	<td><input type="hidden" name="search" value="search"><input type="hidden" name="sort" value=""><input type="button" value="Search" onclick="searchfunc()" ></td></form>
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


	<% session.setAttribute("advancesearch","pass"); %>
	</body>
</html>
