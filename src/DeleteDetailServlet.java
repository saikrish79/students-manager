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

public class DeleteDetailServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	String stdId = req.getParameter("stdid");
	PrintWriter out = res.getWriter();
	Utility ut = new Utility();
	ArrayList<String> className = new ArrayList<String>();
	className = ut.getClassName();
	Class cls = null; Storage fs = null;
	
	try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					fs.deleteRecord(stdId);
					}
				}
				catch(Exception e){}

				res.sendRedirect("success.html");
			}
	}