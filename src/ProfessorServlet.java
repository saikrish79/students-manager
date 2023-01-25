import com.sapp.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfessorServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	
	HttpSession session = req.getSession();
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	Statement st = null;
	
	String tmp_d = req.getParameter("updatedept");
	String tmp_n = req.getParameter("updatename");
	/* if(tmp==null)
	tmp=""; */
	if(tmp_d!=null && tmp_n!=null){
	try{
	st = c.createStatement();
	String s = "UPDATE SALESFORCE SET DEPT='"+tmp_d+"' WHERE NAME='"+tmp_n+"';";
	st.executeUpdate(s);
	PrintWriter out = res.getWriter();
	out.println(req.getParameter("updatedept"));
	}
	catch(Exception e){}
	}
	else{
		
	ArrayList<Professor> list = new ArrayList<Professor>();
	Professor pf;
	String sql,id,name,title,phone,email,dept=null,order;
	String srch1,srch2;
	String srt1,srt2;
	srt1 = req.getParameter("sort");
	srch1 = req.getParameter("search");
	if(srch1==null)
		srch1="";
	sql = "SELECT * FROM SALESFORCE ";	
		
	if(srch1.equals("search")){
			session.setAttribute("pfsearch","active");
			
			id = req.getParameter("id");				
			name = req.getParameter("name");								
			title = req.getParameter("title");								
			phone = req.getParameter("phone");			
			email = req.getParameter("email");							
			dept = req.getParameter("dept");					
				
		session.setAttribute("pfid",id);session.setAttribute("pfname",name);session.setAttribute("pftitle",title);session.setAttribute("pfemail",email);
		session.setAttribute("pfphone",phone);session.setAttribute("pfdept",dept);
	}
	
	srch2 = (String)session.getAttribute("pfsearch");
	if(srch2.equals("active")){
		id = (String)session.getAttribute("pfid");
		name = (String)session.getAttribute("pfname");
		title = (String)session.getAttribute("pftitle");
		email = (String)session.getAttribute("pfemail");
		phone = (String)session.getAttribute("pfphone");
		dept = (String)session.getAttribute("pfdept");
		 
		sql = sql+"WHERE id ilike '%"+id+"%' AND name ilike '%"+name+"%' AND title ilike '%"+title+"%' AND email ilike '%"+email+"%' "+
				 "AND phone ilike '%"+phone+"%' AND dept ilike '%"+dept+"%' ";
	}
	
	
	int odr=0;
	if(srt1==null)
		srt1="";
	 if(srt1.equals("sort")){ 
		session.setAttribute("pfsort","active");
		session.setAttribute("pfopt",req.getParameter("option"));
		odr = Integer.parseInt((String)session.getAttribute("pforder"));
		if(odr==1){
		session.setAttribute("pforder","2");
		}
		else if(odr==2){
		session.setAttribute("pforder","1");
		}
	 }
	 
	 
	 srt2 = (String)session.getAttribute("pfsort");
	 if(srt2.equals("active")){
		 odr = Integer.parseInt((String)session.getAttribute("pforder"));
		int ch = Integer.parseInt((String)session.getAttribute("pfopt"));
		 if(odr==2)
		order="DESC";
		else
		order="ASC";
	
		switch(ch){
			case 1:
				sql = sql+"ORDER BY ID "+order;
				break;
			
			case 2:			
				sql = sql+"ORDER BY NAME "+order;
				break;
				
			case 3:
			
				sql = sql+"ORDER BY TITLE "+order;
				break;
				
			case 4:
	
				sql = sql+"ORDER BY EMAIL "+order;
				break;
				
			case 5:
			
				sql = sql+"ORDER BY PHONE "+order;
				break;
				
			case 6:
			
				sql = sql+"ORDER BY DEPT "+order;
				break;		
				
			default:
				break;
		}
	}
	
	int count=0;
	try{
		st=c.createStatement();
		ResultSet rs = st.executeQuery(sql+";");
		while(rs.next()){
			count++;}	
	}catch(Exception e){}
	
	
	int off = Integer.parseInt((String)session.getAttribute("pfoffset"));
	String optn = req.getParameter("page");
	if(optn==null)
		optn="";
	if(optn.equals("first"))
		off=0;
	else if(optn.equals("prev")){
		if((off-5)>=0)
			off-=5;
	}
	else if(optn.equals("next")){
		if((off+5)<count)
		off+=5;
	}
	else if(optn.equals("last")){
		if(count>5){
			if((count%5)==0)
				off= count-5;
			else
				off = count-(count%5);
		}	
	}
	
	session.setAttribute("pfoffset",String.valueOf(off));
	
	sql = sql+" LIMIT 5 OFFSET "+String.valueOf(off)+";";	

	try{
	st = c.createStatement();
	ResultSet rs = st.executeQuery(sql);
	while(rs.next())
	{
	pf = new Professor();
	id = rs.getString("ID");
	name = rs.getString("NAME");
	title = rs.getString("TITLE");
	phone = rs.getString("PHONE");
	email = rs.getString("EMAIL");
	dept = rs.getString("DEPT");
	pf.setID(id);
	pf.setName(name);
	pf.setTitle(title);
	pf.setEmail(email);
	pf.setPhone(phone);
	if(dept==null)
	dept="";
	pf.setDept(dept);
	list.add(pf);
	pf = null;
	}
	}
	catch(Exception e){}
	
			session.setAttribute("pflist",list);
			session.setAttribute("pfcntrl","nopass");
			/* PrintWriter out = res.getWriter();
			 if(tmp_d!=null){
			 out.println("<html><head></head><body>"+tmp_d+"</body></html>");}
			 else  */
			res.sendRedirect("ProfessorDetails.jsp");
	}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req,res);
	}
}
	
	