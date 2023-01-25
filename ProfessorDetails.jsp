<%@ page language="java" %>
<%@ page import="java.io.*, java.sql.*, java.util.*, java.lang.*,com.sapp.*" %>
<html>
	<head>
	<title>StudentDetail App</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Exo+2|Marcellus+SC&display=swap" rel="stylesheet">   
	</head>
	
	<% 
	String s = (String)session.getAttribute("pfcntrl");
	if(s!="nopass"){
	ArrayList<Professor> list = new ArrayList<Professor>();
	session.setAttribute("pflist",list);
	session.setAttribute("pfoffset","0");
	session.setAttribute("pfid","");session.setAttribute("pfname","");session.setAttribute("pftitle","");session.setAttribute("pfemail","");
	session.setAttribute("pfphone","");session.setAttribute("pfdept","");
	session.setAttribute("pfsort","inactive");
	session.setAttribute("pfsearch","inactive");
	session.setAttribute("pforder","1");
	response.sendRedirect("ProfessorServlet");
	}
	%>
	
	<body>
	<div class="top-container">
	<h1 class="title">StudentDetail Manager</h1>
	<form action="LogoutServlet" method="post" class="logout"><span style="color:white"><%=(String)session.getAttribute("username")%>	</span><input type="submit"	value="Logout" class="sub-button"></form>
	</div>
	<div class="datatable">
	<br>
	<h3 align="center" >Professor Details</h3>
	<br>
	<form style="display:inline" action="index.jsp" method = "post">
	<input type="submit" value="Main page" class="sub-button">
	</form>
	
	<table id="tab" class="a">
	<tr>
	<th onclick="func(1)">Professor ID</th>
	<th onclick="func(2)">Name</th>
	<th onclick="func(3)">Title</th>
	<th onclick="func(4)">Email</th>
	<th onclick="func(5)">Phone</th>
	<th onclick="func(6)">Dept</th>
	<th>Dept Update</th>
	<form action="ProfessorServlet" method="post" id="Form"><input type="hidden" name="search" value="">
	<input type="hidden" name="sort" value="sort"><input type="hidden" id="val" name="option" value= ""></form>
	</tr>
	
	<tr>
	<form action="ProfessorServlet" method="post">
	<td><input type="text" name="id" value="<%= (String)session.getAttribute("pfid") %>"></td>
	<td><input type="text" name="name" value="<%= (String)session.getAttribute("pfname") %>"></td>
	<td><input type="text" name="title" value="<%= (String)session.getAttribute("pftitle") %>"></td>
	<td><input type="text" name="email" value="<%= (String)session.getAttribute("pfemail") %>"></td>
	<td><input type="text" name="phone" value="<%= (String)session.getAttribute("pfphone") %>"></td>
	<td><input type="text" name="dept" value="<%= (String)session.getAttribute("pfdept") %>"></td>
	<td></td>
	<td><input type="hidden" name="search" value="search"><input type="hidden" name="sort" value=""><input type="submit" value="Search"></td></form>
	<form action="ProfessorDetails.jsp" method="post">
	<td><input type="submit" value="Cancel"></td></form>
	</tr>
	
	
	<%	ArrayList<Professor> list = new ArrayList<Professor>();
		Professor pf;
		list = (ArrayList)session.getAttribute("pflist");
	int size = list.size();
	for(int i=0;i<size;i++){
		pf = list.get(i);  %>
		
		<tr>
		<td><%=pf.getID()%></td><td><%=pf.getName()%></td><td><%=pf.getTitle()%></td><td><%=pf.getEmail()%></td><td><%=pf.getPhone()%></td>
		<td id="dept<%=pf.getID()%>"><%=pf.getDept()%></td>
		
		<td><form id="form_<%=pf.getID()%>"><select name="updatedept">
		<option value="">Select a Dept:</option><option value="CSE">CSE</option><option value="IT">IT</option><option value="ECE">ECE</option><option value="ME">ME</option>
		</select>
		<input type="hidden" name="updatename" value="<%=pf.getName()%>">
		<input type="button" onclick="department(<%=pf.getID()%>)" value="Update"></td></form>
		</tr> 
	
	<%}%>
	</table>
	<div align="center">
	<% 
		%>
	<form style="display:inline" action="ProfessorServlet" method="post"><input type="hidden" name="page" value="first"><input type="submit" value="First"></form>
	<form style="display:inline" action="ProfessorServlet" method="post"><input type="hidden" name="page" value="prev"><input type="submit" value="Prev"></form>	
	<form style="display:inline" action="ProfessorServlet" method="post"><input type="hidden" name="page" value="next"><input type="submit" value="Next"></form>
	<form style="display:inline" action="ProfessorServlet" method="post"><input type="hidden" name="page" value="last"><input type="submit" value="Last"></form>
		
	</div>	
	<script>
	function func(n){
		document.getElementById("val").value=n;
		document.getElementById("Form").submit();
	}
	function department(str){
	
	 var xhttp = new XMLHttpRequest();
	xhttp.open('POST', 'ProfessorServlet',true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//var tmp="form_"+str;
	var form = document.getElementById("form_"+str);
	var formData = new FormData(form);
	//alert(formData.get("updatedept")+"  "+formData.get("updatename"));
	var txt = "updatedept="+formData.get("updatedept")+"&updatename="+formData.get("updatename");
	xhttp.send(txt);
	xhttp.onreadystatechange = function() {
        
        if(this.readyState === 4 && this.status === 200) {
           
            document.getElementById("dept"+str).innerHTML = this.responseText;
        }
    }; 
	}
	</script>
	<% session.setAttribute("pfcntrl","pass"); %>
	</body>
</html>
	
	
	
	
	
	
	
	
	
	
	