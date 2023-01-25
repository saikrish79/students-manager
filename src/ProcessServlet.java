import com.sapp.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.simple.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcessServlet extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{

	int count=0;
	String name,dept="",clg,stdId="",gender,email,phone,phy,chem,comp,math,eng,n,ts,order,col=";";
	HttpSession session = req.getSession();
	Utility ut = new Utility();
	ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();

	StudentDetail en; Marks m; Personal p;
	String srch1,srch2;
	srch1 = req.getParameter("search");
	if(srch1==null)
		srch1="";
	String srt1 = req.getParameter("sort");
	String srt2;
	DBUtil obj = DBUtil.getInstance();
	Connection c = obj.getConnection();
	Statement st = null;
	PrintWriter out = res.getWriter();
	//out.println("<html><head></head><body>");

	String canceladv = req.getParameter("CancelAdvanceSearch");
	if(canceladv==null)
		canceladv="";

		String cancel = req.getParameter("CancelSearch");
		if(cancel==null)
			cancel="";

	if(cancel.equals("Reset")){
		session.setAttribute("id","");session.setAttribute("name","");session.setAttribute("no","");session.setAttribute("dept","");
		session.setAttribute("clg","");session.setAttribute("gen","");session.setAttribute("mail","");session.setAttribute("phn","");
		session.setAttribute("ph","");session.setAttribute("ch","");session.setAttribute("cmp","");session.setAttribute("mat","");
		session.setAttribute("eng","");
		session.setAttribute("search","inactive");
			}

	if(canceladv.equals("Reset")){
		session.removeAttribute("advancesearch");
		session.removeAttribute("advancesession");
		res.sendRedirect("AdvancedSearch.jsp");
	}

	else{

		String login = (String)session.getAttribute("professorlogin");
		if(login == null)
			login="";
		if(login.equals("active")){
			try{
			ts = "SELECT DEPT FROM SALESFORCE WHERE EMAIL='"+(String)session.getAttribute("username")+"';";
			st = c.createStatement();
			ResultSet rs = st.executeQuery(ts);
			while(rs.next()){
			dept = rs.getString("dept");
			session.setAttribute("dept",dept);
			}
			}
			catch(Exception e){}
		}


	Map<String,String> column = new HashMap<String,String>();
	column.put("oid","studentdetail.stdid ");
	column.put("oname","studentdetail.name ");
	column.put("oenroll","studentdetail.enroll ");
	column.put("ocollege","studentdetail.college ");
	column.put("odept","studentdetail.dept ");
	column.put("ophy","marks.phy ");
	column.put("ochem","marks.chem ");
	column.put("ocomp","marks.comp ");
	column.put("omath","marks.math ");
	column.put("oeng","marks.eng ");
	column.put("ogender","personal.gender ");
	column.put("oemail","personal.email ");
	column.put("ophone","personal.phone ");

	Map<String,String> stringcomp = new HashMap<String,String>();
	stringcomp.put("equals","ilike '?' ");
	stringcomp.put("notequals","not like '?' ");
	stringcomp.put("contains","ilike '%?%' ");
	stringcomp.put("startswith","ilike '?%' ");
	stringcomp.put("endswith","ilike '%?' ");

	Map<String,String> intcomp = new HashMap<String,String>();
	intcomp.put("equals"," = ");
	intcomp.put("notequals"," != ");
	intcomp.put("greater"," > ");
	intcomp.put("lesser"," < ");



	String s = "SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
				"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
				"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = studentdetail.stdid "+
				"INNER JOIN personal on marks.stdid = personal.stdid ";

	String advancesession = (String)session.getAttribute("advancesession");
	if(advancesession==null)
	advancesession="";

	String searchcount = (String)session.getAttribute("searchcount");
	if(searchcount==null)
	searchcount="0";
	String t2 = req.getParameter("count");
	if(t2==null)
		t2="0";
	if(t2 != "0"){
	int cnt = Integer.parseInt(t2);
	session.setAttribute("searchcount",String.valueOf(cnt));
	session.setAttribute("searchodr",req.getParameter("divorder"));
	out.println(req.getParameter("divorder"));
	session.setAttribute("advancesession","active");
	String[] divorder = req.getParameter("divorder").split(",");
	for(int a=0;a<cnt;a++){
	int i = Integer.parseInt(divorder[a]);
		if(i==1)
			s = s + "WHERE ";
		if(i > 1){
			s = s + " " +req.getParameter("operator"+i) + " ";
			session.setAttribute("operator"+i,req.getParameter("operator"+i));
		}
		String t1 = req.getParameter("column"+i);
		session.setAttribute("column"+i,t1);
		s = s + column.get(t1);
		if(t1.equals("oenroll") || t1.equals("ophy") || t1.equals("ochem") || t1.equals("omath") || t1.equals("oeng") || t1.equals("ocomp") ){
		s = s + intcomp.get(req.getParameter("comparatorint"+i)) + req.getParameter("value"+i);
		session.setAttribute("comparatorint"+i,(req.getParameter("comparatorint"+i)));
		//session.setAttribute("value"+i,(req.getParameter("value"+i)));
		}
		else{
		s = s + stringcomp.get(req.getParameter("comparatorstring"+i));
		s = s.replace("?",req.getParameter("value"+i));
		session.setAttribute("comparatorstring"+i,(req.getParameter("comparatorstring"+i)));
		//session.setAttribute("value"+i,(req.getParameter("value"+i)));
		}

		session.setAttribute("value"+i,(req.getParameter("value"+i)));
	}
	}
	else
	{
		int cnt = Integer.parseInt(searchcount);
		int i=0;
		String temp = (String)session.getAttribute("searchodr");
		if(temp==null)
		temp="0";
		String[] divorder = temp.split(",");
		for(int a=0;a<cnt;a++){
			if(temp!="0")
		i = Integer.parseInt(divorder[a]);
		if(i==1)
			s = s + "WHERE ";
		if(i > 1){
			s = s + " " +(String)session.getAttribute("operator"+i) + " ";
		}
		String t1 = (String)session.getAttribute("column"+i);
		if(t1==null)t1="";
		s = s + column.get(t1);
		if(t1.equals("oenroll") || t1.equals("ophy") || t1.equals("ochem") || t1.equals("omath") || t1.equals("oeng") || t1.equals("ocomp") ){
		s = s + intcomp.get((String)session.getAttribute("comparatorint"+i))+(String)session.getAttribute("value"+i);
		}
		else{
		s = s + stringcomp.get((String)session.getAttribute("comparatorstring"+i));
		String t3 = (String)session.getAttribute("value"+i);
		if(t3==null)t3="";
		s = s.replace("?",t3);
		}
	}
}



	if(srch1.equals("search")){
			session.setAttribute("search","active");

			stdId = req.getParameter("stdid");
			n = req.getParameter("enroll");
			name = req.getParameter("name");
			gender = req.getParameter("gender");
			dept = req.getParameter("dept");
			clg = req.getParameter("clg");
			email = req.getParameter("email");
			phone = req.getParameter("phone");
			phy = req.getParameter("phy");
			chem = req.getParameter("chem");
			comp = req.getParameter("comp");
			math = req.getParameter("math");
			eng = req.getParameter("eng");
			session.setAttribute("id",stdId);session.setAttribute("name",name);session.setAttribute("no",n);session.setAttribute("dept",dept);
			session.setAttribute("clg",clg);session.setAttribute("gen",gender);session.setAttribute("mail",email);session.setAttribute("phn",phone);
			session.setAttribute("ph",phy);session.setAttribute("ch",chem);session.setAttribute("cmp",comp);session.setAttribute("mat",math);
			session.setAttribute("eng",eng);
	}

	int odr=0;
	if(srt1==null)
		srt1="";
	 if(srt1.equals("sort")){
		session.setAttribute("sort","active");
		session.setAttribute("opt",req.getParameter("option"));
		odr = Integer.parseInt((String)session.getAttribute("order"));
		if(odr==1){
		session.setAttribute("order","2");
		}
		else if(odr==2){
		session.setAttribute("order","1");
	}
	 }

	 srt2 = (String)session.getAttribute("sort");
	 srch2 = (String)session.getAttribute("search");
	 if(!advancesession.equals("active") && (srch2.equals("active") || srt2.equals("active")) )
 		s = s + "WHERE ";


	if(srch2.equals("active")){

		out.println("<br>"+srch2+"  "+stdId+" "+dept);
		stdId = (String)session.getAttribute("id");
			if(!stdId.equals(""))
				s = s+ " AND studentdetail.stdid ilike '%"+stdId+"%' ";
			n = (String)session.getAttribute("no");
			if(!n.equals(""))
				s = s+ " AND studentdetail.enroll = '"+n+"' ";
			name = (String)session.getAttribute("name");
			if(!name.equals(""))
				s = s+ " AND studentdetail.name ilike '%"+name+"%' ";
			gender = (String)session.getAttribute("gen");
			if(!gender.equals(""))
				s = s+ " AND personal.gender ilike '%"+gender+"%' ";
			dept =(String)session.getAttribute("dept");
			if(!dept.equals(""))
				s = s+ " AND studentdetail.dept ilike '%"+dept+"%' ";
			clg = (String)session.getAttribute("clg");
			if(!clg.equals(""))
				s = s+ " AND studentdetail.college ilike '%"+clg+"%' ";
			email = (String)session.getAttribute("mail");
			if(!email.equals(""))
				s = s+ " AND personal.email ilike '%"+email+"%' ";
			phone = (String)session.getAttribute("phn");
			if(!phone.equals(""))
				s = s+ " AND personal.phone ilike '%"+phone+"%' ";
			phy = (String)session.getAttribute("ph");
			if(!phy.equals(""))
				s = s+ " AND marks.phy = '"+phy+"' ";
			chem = (String)session.getAttribute("ch");
			if(!chem.equals(""))
				s = s+ " AND marks.chem = '"+chem+"' ";
			comp = (String)session.getAttribute("cmp");
			if(!comp.equals(""))
				s = s+ " AND marks.comp = '"+comp+"' ";
			math =(String)session.getAttribute("mat");;
			if(!math.equals(""))
				s = s+ " AND marks.math = '"+math+"' ";
			eng = (String)session.getAttribute("eng");
			if(!eng.equals(""))
				s = s+ " AND marks.eng = '"+eng+"' ";

			/*s = s + " studentdetail.stdid ilike '%"+stdId+"%' AND studentdetail.name ilike '%"+name+"%' AND "+
				"studentdetail.enroll ilike '%"+n+"%' AND studentdetail.dept ilike '%"+dept+"%' AND studentdetail.college ilike '%"+clg+"%' AND "+
				"personal.gender ilike '%"+gender+"%' AND personal.email ilike '%"+email+"%' AND personal.phone ilike '%"+phone+"%' AND "+
				"marks.phy ilike '%"+phy+"%' AND marks.chem ilike '%"+chem+"%' AND marks.comp ilike '%"+comp+"%' AND "+
				"marks.math ilike '%"+math+"%' AND marks.eng ilike '%"+eng+"%' ";*/
	}


	 if(srt2.equals("active")){
		 if(!advancesession.equals("active"))
			 s = s + "WHERE ";
		 odr = Integer.parseInt((String)session.getAttribute("order"));
		int ch = Integer.parseInt((String)session.getAttribute("opt"));
		 if(odr==2)
		order="DESC";
		else
		order="ASC";

		switch(ch){
			case 1:
				s = s+"ORDER BY STDID "+order;
				break;

			case 2:
				s = s+"ORDER BY ENROLL "+order;
				break;

			case 3:

				s = s+"ORDER BY NAME "+order;
				break;

			case 4:

				s = s+"ORDER BY DEPT "+order;
				break;

			case 5:

				s = s+"ORDER BY COLLEGE "+order;
				break;

			case 6:

				s = s+"ORDER BY GENDER "+order;
				break;

			case 7:

				s = s+"ORDER BY EMAIL "+order;
				break;

			case 8:

				s = s+"ORDER BY PHONE "+order;
				break;

			case 9:

				s = s+"ORDER BY PHY "+order;
				break;

			case 10:
				s = s+"ORDER BY CHEM "+order;
				break;

			case 11:
				s = s+"ORDER BY COMP "+order;
				break;

			case 12:
				s = s+"ORDER BY MATH "+order;
				break;

			case 13:
				s = s+"ORDER BY ENG "+order;
				break;

			default:
				System.out.println("Invalid option");
		}
	}

	try{
		st = c.createStatement();
		ResultSet rs = st.executeQuery(s+col);
		while(rs.next()){
			count++;
		}

	}catch(Exception e){}

	int off = Integer.parseInt((String)session.getAttribute("offset"));
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
		if(count>5)
			off = count-(count%5);
	}

	session.setAttribute("offset",String.valueOf(off));

	s = s+" LIMIT 5 OFFSET "+String.valueOf(off)+";";
	out.println("<br>"+s);
	System.out.println(s);
	try{

		/* s = "SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
				"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
				"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = studentdetail.stdid "+
				"INNER JOIN personal on marks.stdid = personal.stdid WHERE studentdetail.stdid ilike '%"+stdId+"%' AND studentdetail.name ilike '%"+name+"%' AND "+
				"studentdetail.enroll ilike '%"+n+"%' AND studentdetail.dept ilike '%"+dept+"%' AND studentdetail.college ilike '%"+clg+"%' AND "+
				"personal.gender ilike '%"+gender+"%' AND personal.email ilike '%"+email+"%' AND personal.phone ilike '%"+phone+"%' AND "+
				"marks.phy ilike '%"+phy+"%' AND marks.chem ilike '%"+chem+"%' AND marks.comp ilike '%"+comp+"%' AND "+
				"marks.math ilike '%"+math+"%' AND marks.eng ilike '%"+eng+"%' ;"; */

		st = c.createStatement();
		ResultSet rs = st.executeQuery(s);

		while(rs.next()){
				en = new StudentDetail();
				m = new Marks();
				p = new Personal();
				stdId =rs.getString("stdid");
				n = rs.getString("enroll");
				name = rs.getString("name");
				dept = rs.getString("dept");
				clg = rs.getString("college");
				gender = rs.getString("gender");
				email = rs.getString("email");
				phone = rs.getString("phone");
				phy  = rs.getString("phy");
				chem  = rs.getString("chem");
				comp  = rs.getString("comp");
				math  = rs.getString("math");
				eng = rs.getString("eng");
				en.setStdId(stdId);
				en.setEnroll(Integer.parseInt(n));
				en.setName(name);
				en.setDept(dept);
				en.setCollege(clg);
				p.setGender(gender);
				p.setEmail(email);
				p.setPhone(phone);
				m.setMarkPhy(Integer.parseInt(phy));
				m.setMarkChem(Integer.parseInt(chem));
				m.setMarkComp(Integer.parseInt(comp));
				m.setMarkMath(Integer.parseInt(math));
				m.setMarkEng(Integer.parseInt(eng));

				en.setMarks(m);
				en.setPersonal(p);
				list.add(en);
				en=null;
				p=null;
				m=null;
				count++;
		}
		}
				catch(Exception e){e.printStackTrace();}
			out.println("<br>"+list.isEmpty());
			session.setAttribute("list",list);
			session.setAttribute("advancesearch","nopass");
			//res.sendRedirect("AdvancedSearch.jsp");
	}
			//out.println("</body></html>");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException{
		doPost(req,res);
	}
}
