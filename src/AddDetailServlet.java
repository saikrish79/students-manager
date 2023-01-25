import com.sapp.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.lang.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddDetailServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	//res.setContentType("text/html");
	//PrintWriter out = res.getWriter();
	String name,dept,clg,stdId,gender,email,phone,n;
	String[] sub = {"Physics","Chemistry","Computer","Maths","English"};
	Class cls = null; Storage fs = null;
	StudentDetail en = new StudentDetail();
	Marks m = new Marks();
	Personal p = new Personal();
	DBUtil obj = DBUtil.getInstance();
	
	Utility ut = new Utility();
	ArrayList<String> className = new ArrayList<String>();
	className = ut.getClassName();
			
			//out.println("<html><head></head><body>");
			Connection c = obj.getConnection();
			
			stdId = req.getParameter("stdid");
			//out.println("<p>"+stdId+"</p>");
			if(ut.checkId(stdId)){
			en.setStdId(stdId);
						
			int no = Integer.parseInt(req.getParameter("enroll"));
			en.setEnroll(no);
												
			name = req.getParameter("name");
			en.setName(name);
											
			gender = req.getParameter("gender");
			p.setGender(gender);
						
			dept = req.getParameter("dept");
			en.setDept(dept);
												
			clg = req.getParameter("clg");
			en.setCollege(clg);
												
			email = req.getParameter("email");
			p.setEmail(email);					
						
			phone = req.getParameter("phone");
			p.setPhone(phone);						
					
			no = Integer.parseInt(req.getParameter("phy"));
			m.setMarkPhy(no);
											
			no = Integer.parseInt(req.getParameter("chem"));
			m.setMarkChem(no);
												
			no = Integer.parseInt(req.getParameter("comp"));						
			m.setMarkComp(no);
												
			no = Integer.parseInt(req.getParameter("math"));					
			m.setMarkMath(no);
						
			no = Integer.parseInt(req.getParameter("eng"));
			m.setMarkEng(no);
						
			en.setMarks(m);
			en.setPersonal(p);
			String s = null;
						try{
						String tdept = dept.toUpperCase();
						//out.println("<p>"+tdept+"</p><br>");
						//c = DriverManager.getConnection("jdbc:postgresql://localhost/student", "postgres", "123");	
						/* Statement st = c.createStatement();
						s = "SELECT CLASS FROM STORAGE WHERE DEPT = '"+tdept+"';";
						ResultSet rs = st.executeQuery(s); */
						
						PreparedStatement ps = c.prepareStatement("SELECT CLASS FROM STORAGE WHERE DEPT = ?");
						ps.setString(1,tdept);
						ResultSet rs = ps.executeQuery();
						
						while(rs.next()){
						s = rs.getString("class");
						//out.println("<p>"+s+"</p><br>");
						}
						cls = Class.forName(s);
						fs = (Storage)cls.newInstance();
						}
						catch(Exception e){
							//out.println("<p>"+e.toString()+"</p><br>");
							e.printStackTrace();
						}
						//out.println("<p>"+fs.addDetail(en)+"</p><br>");
						fs.addDetail(en);
			}
						else{
							System.out.println("ID Already exists");
						}
						//out.println("</body></html>");
			res.sendRedirect("index.jsp");
	}

}