<%@ page language="java" %>
<%@ page import="java.io.*, java.sql.*, java.util.*, java.lang.*,com.sapp.*" %>

<html>
	<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link href="https://fonts.googleapis.com/css?family=Allerta+Stencil|Exo+2|Marcellus+SC&display=swap" rel="stylesheet">
	</head>

<body>
	<table id="tab01">

  <% ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();
    StudentDetail en;Personal p; Marks m;
    list = (ArrayList)session.getAttribute("list");
  int size = list.size();
  for(int i=0;i<size;i++){
    en = list.get(i);
    p = en.getPersonal();
    m = en.getMarks(); %>
    <tr>
    <td><%=en.getStdId()%></td><td><%=en.getName()%></td><td><%=en.getEnroll()%></td><td><%=en.getDept()%></td><td><%=en.getCollege()%></td>
    <td><%=p.getGender()%></td><td><%=p.getEmail()%></td><td><%=p.getPhone()%></td>
    <td><%=m.getMarkPhy()%></td><td><%=m.getMarkChem()%></td><td><%=m.getMarkComp()%></td><td><%=m.getMarkMath()%></td><td><%=m.getMarkEng()%></td>

    <td><form style="display:inline" action="DetailEditor.jsp"><input type="hidden" name="stdid" value= "<%=en.getStdId()%>"><input type="submit" value="Update"></form>
  <form style="display:inline" action="DeleteDetailServlet" method="post"><input type="hidden" name="stdid" value= "<%=en.getStdId()%>"><input type="submit" value="Delete"></form></td>
  </tr>
  <% } %>
  </table>
  </body>
  </html>
