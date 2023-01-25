import com.sapp.*;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	String username = req.getParameter("username");
	HttpSession session = req.getSession();
	session.setAttribute("username",username);
	String sql;
	PrintWriter out = res.getWriter();
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	Statement st = null;
	String a="";
	
	try{
		sql = "SELECT * FROM APPUSERS WHERE NAME ILIKE '"+username+"';";
		st = c.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
		a = rs.getString("login");
		if(a.equals("sfuser"))
			session.setAttribute("login",a);
		out.println(a);
		}
	}
	catch(Exception e){}
	}
}