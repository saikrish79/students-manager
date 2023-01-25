import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


public class LogoutServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
	
	HttpSession session = req.getSession();
	session.invalidate();
	//session = req.getSession(false);
	//res.getWriter().println(session.getId());
	res.sendRedirect("loginuser.jsp");
	}
	}