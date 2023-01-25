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

public class TestServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
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
	}
	catch(Exception e){}
	}
	res.setContentType("text/plain");
		PrintWriter out = res.getWriter();
	out.println(req.getParameter("updatedept"));
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req,res);
	}
	}