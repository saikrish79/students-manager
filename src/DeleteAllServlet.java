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

public class DeleteAllServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	PrintWriter out = res.getWriter();
	Utility ut = new Utility();
	ArrayList<String> className = new ArrayList<String>();
	className = ut.getClassName();
	Class cls = null; Storage fs = null;
	try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					fs.deleteEntireData();
					}
				}
				catch(Exception e){}
				HttpSession session = req.getSession();
				res.sendRedirect("success.html");
			}
	}