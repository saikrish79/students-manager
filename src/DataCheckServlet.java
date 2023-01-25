import com.sapp.*;
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DataCheckServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
 
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	//Statement st = null;
	try{
	/* st = c.createStatement();
	String s = "SELECT * FROM SALESFORCE;";
	ResultSet rs = st.executeQuery(s); */
	
	PreparedStatement ps = c.prepareStatement("SELECT * FROM SALESFORCE");
	ResultSet rs = ps.executeQuery(); 
	
	if(rs.next()){
	res.sendRedirect("ProfessorDetails.jsp");
	}
	else
	{
	res.sendRedirect("https://login.salesforce.com/services/oauth2/authorize?response_type=code&"+
				"client_id=3MVG9n_HvETGhr3BzPtvc1N9xjPnbBLekNaC77rhtCZkTZAqFb1rSi5pqi9DcSTTCMiugcrPbGW0tYe_TW42F&"+
				"redirect_uri=http://localhost:8080/OauthServlet");
	}
	}
	catch(Exception e){}
	}
}