<%@ page language="java" %>
<%@ page import="com.sapp.*, java.util.*" %>
<html>
	<head>
	<title>StudentDetail App</title>
	<link rel="stylesheet" type="text/css" href="style.css">
	<meta charset="UTF-8" />
	</head>
	<%! String title, stdid=null, vaction, vstdid,vno,vname,vclg,vgender,vdept,control,vphone,vemail,vphy,vchem,vcomp,vmat,veng,vsubmit;%>
	<%stdid = request.getParameter("stdid");
	if(stdid.equals("null")){
		title = "Add Student Detail";
		control = "";
		vsubmit = "Add Details";
		vaction = "AddDetailServlet";
						vstdid = "";
						 vno = "";
						 vname = "";
						 vdept = "";
						 vclg = "";
						 vgender = "";
						 vphone = "";
						 vemail = "";
						 vphy = "";
						 vchem = "";
						 vcomp = "";
						 vmat = "";
						 veng = "";
						 
	}
	else{
		title = "Update Student Detail";
		vaction = "UpdateDetailServlet";
		Utility ut = new Utility();
		ArrayList<String> className = new ArrayList<String>();
		className = ut.getClassName();
		Class cls = null; Storage fs = null;
		StudentDetail en;
		Marks m; Personal p;
			try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					en = fs.displayIndividualDetail(stdid);
					if(en!=null){
						m = en.getMarks();
						p = en.getPersonal();
						 vstdid = en.getStdId();
						 vno = String.valueOf(en.getEnroll());
						 vname = en.getName();
						 vdept = en.getDept();
						 vclg = en.getCollege();
						 vgender = p.getGender();
						 vphone = p.getPhone();
						 vemail = p.getEmail();
						 vphy = String.valueOf(m.getMarkPhy());
						 vchem = String.valueOf(m.getMarkChem());
						 vcomp = String.valueOf(m.getMarkComp());
						 vmat = String.valueOf(m.getMarkMath());
						 veng = String.valueOf(m.getMarkEng());
						 control = "disabled";
						 vsubmit = "Update Details";
					}
					en=null;
				}
			}
				catch(Exception e){e.printStackTrace();}
	}
	%>
	<body>
	<h1><%= title%></h1>
	<br>
	
	<form method="post" action="<%= vaction%>">
	<br><br>
	<p>Please fill the Student details</p>
	<br>
	Student Id:
	<% if(stdid.equals("null")){%>
	<input type="text" name="stdid" value="<%=vstdid%>"><br><%}
	else {%>
	<input type="text" value="<%=vstdid%>" <%=control%> ><br>
	<input type="hidden" name="stdid" value="<%=vstdid%>"><%}%>
	Enroll No:  
	<input type="text" name="enroll" value="<%=vno%>"><br>
	Name:
	<input type="text" name="name" value="<%=vname%>"><br>
	Department:
	<% if(stdid.equals("null")){%>
	<input type="text" name="dept" value="<%=vdept%>"><br><%}
	else {%>
	<input type="text" value="<%=vdept%>" <%=control%> ><br>
	<input type="hidden" name="dept" value="<%=vdept%>"><%}%>
	College:
	<input type="text" name="clg" value="<%=vclg%>"><br>
	Gender:
	<input type="text" name="gender" value="<%=vgender%>"><br>
	Phone:
	<input type="text" name="phone" value="<%=vphone%>"><br>
	Email:
	<input type="text" name="email" value="<%=vemail%>"><br>
	Enter the marks of Subjects:<br>
	Physics:
	<input type="text" name="phy" value="<%=vphy%>"><br>
	Chemistry:
	<input type="text" name="chem" value="<%=vchem%>"><br>
	Computer:
	<input type="text" name="comp" value="<%=vcomp%>"><br>
	Mathematics:
	<input type="text" name="math" value="<%=vmat%>"><br>
	English:
	<input type="text" name="eng" value="<%=veng%>"><br>
<br><br>

	<input type="submit" value="<%=vsubmit%>">
	</form>
	</body>
</html>