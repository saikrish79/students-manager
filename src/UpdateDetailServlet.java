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

public class UpdateDetailServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	String name,dept,clg,stdId,gender,email,phone,n;
	String[] sub = {"Physics","Chemistry","Computer","Maths","English"};
	Class cls = null; Storage fs = null;
	StudentDetail en = new StudentDetail();
	Marks m = new Marks();
	Personal p = new Personal();
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	Utility ut = new Utility();
	ArrayList<String> className = new ArrayList<String>();
	className = ut.getClassName();
			stdId = req.getParameter("stdid");
			if(!ut.checkId(stdId)){

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
			try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					fs.updateRecord(stdId,en);
					}
				}
				catch(Exception e){}
				
				res.sendRedirect("success.html");
			}
						else{
							res.sendRedirect("failed.html");
						}
		}
		
}