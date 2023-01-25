import java.io.*;
import org.json.simple.*;
import java.util.*;
import com.sapp.*;
import java.sql.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class OauthServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		
	String clientId = "3MVG9n_HvETGhr3BzPtvc1N9xjPnbBLekNaC77rhtCZkTZAqFb1rSi5pqi9DcSTTCMiugcrPbGW0tYe_TW42F";
	String clientSecret = "2FF3E5CE8E47BA68CEAB14E3BFBFAD98816C2466D7D3DDC3EAF8F8E8FE904B82";
	String redirectUri = "http://localhost:8080/OauthServlet";
	String environment = "https://login.salesforce.com/services/oauth2/token";
	String accessToken = null,issuedAt=null,serviceUrl=null;
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	String code = req.getParameter("code");
	String result = null;
	out.println("<html><head></head><body>");
	//out.println("<p>nom</p>");
	ArrayList<BasicNameValuePair> urlParameters = new ArrayList<BasicNameValuePair>();
	try{
		//out.println("<p>kiook</p>");
		//CloseableHttpClient httpClient = HttpClients.createDefault();
		//out.println("<p>client open</p>");
		out.println("open");
		HttpPost post = new HttpPost("https://login.salesforce.com/services/oauth2/token");
        urlParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
		urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
		urlParameters.add(new BasicNameValuePair("redirect_uri", redirectUri));
		out.println(code);
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		out.println("<p>entity set</p>");
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(post)){
		out.println("<p>iklmnk</p>");
       // out.println(EntityUtils.toString(response.getEntity()));
		result = EntityUtils.toString(response.getEntity());
		out.println(result);
		}
		catch(Exception a){
			out.println(a.toString());}
	}
	catch(Exception e){}
	
	JSONParser parser = new JSONParser();

	try {
		JSONObject json = (JSONObject) parser.parse(result);
         accessToken = (String)json.get("access_token");
		 serviceUrl = (String)json.get("instance_url");                   
         } catch (Exception e) {
           out.println(e.toString());
         }
		 out.println(serviceUrl+"<br>");
		 out.println(accessToken);
	
	out.println("<br><br>");
	
	try{
		HttpGet get = new HttpGet(serviceUrl+"/services/data/v47.0/query?q=Select+Name+,+Title+,+AccountId+,+Phone+,+Email+FROM+Contact");
		get.addHeader("Authorization", ("Bearer "+accessToken));
		try(CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get)){
		out.println("<p>iklmnk</p>");
        //out.println(EntityUtils.toString(response.getEntity()));
		result = EntityUtils.toString(response.getEntity());
		out.println(result);
		}
		catch(Exception a){
			//out.println(a.toString());}
	}
}
	catch(Exception e){
		//out.println(e.toString());
	}

	long size=0;
	JSONArray results = null;
	
	try {
		JSONObject json = (JSONObject) parser.parse(result);
         size = (long)json.get("totalSize");
         results = (JSONArray)json.get("records");          
         } catch (Exception e) {
            out.println(e.toString());
         }
		 out.println(size);
	
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	
	String name=null,title=null,email=null,phone=null,id=null;
	
	try{
		//Statement st = c.createStatement();
		PreparedStatement ps = c.prepareStatement("INSERT INTO SALESFORCE (ID,NAME,TITLE,EMAIL,PHONE,DEPT) VALUES(?,?,?,?,?,?)");
	for(int i=0;i<size;i++){
		JSONObject temp = (JSONObject)results.get(i);
		name = (String)temp.get("Name");
		title = (String)temp.get("Title");
		email = (String)temp.get("Email");
		phone = (String)temp.get("Phone");
		id = String.valueOf(i+1);
		//String sql = "INSERT INTO SALESFORCE (ID,NAME,TITLE,EMAIL,PHONE,DEPT) VALUES( '"+id+"',  '"+name+"', '" +title+"', '"+email+"', '"+phone+"', '');";
		//st.executeUpdate(sql);
		ps.setString(1,id);
		ps.setString(2,name);
		ps.setString(3,title);
		ps.setString(4,email);
		ps.setString(5,phone);
		ps.setString(6,"");
	}
	}
	catch(Exception e){}
		
		out.println("<p>iklmnk</p>");
		out.println("</body></html>");
		res.sendRedirect("ProfessorDetails.jsp");
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req,res);
	}
	
}
	