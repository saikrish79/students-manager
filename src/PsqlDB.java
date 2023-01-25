import com.sapp.*;
import java.util.*;
import java.io.*;
import java.sql.*;

public class PsqlDB implements Storage{

		Connection c;

		public PsqlDB(){
			DBUtil obj = DBUtil.getInstance();
			obj.checkTable();
			this.c = obj.getConnection();
		}

	public void addDetail(StudentDetail en){

				//Statement st=null;

				Marks m = en.getMarks();
				Personal p = en.getPersonal();
				String name=en.getName(), dept=en.getDept(), clg=en.getCollege(), stdId=en.getStdId(), gender=p.getGender() ,email=p.getEmail(), phone=p.getPhone();
				int phy = m.getMarkPhy(), chem = m.getMarkChem(), comp = m.getMarkComp(), math = m.getMarkMath(), eng = m.getMarkEng(), n=en.getEnroll();

			try{
			PreparedStatement psdetails = c.prepareStatement("INSERT INTO STUDENTDETAIL (STDID,ENROLL,NAME,DEPT,COLLEGE) VALUES( ? , ? , ? , ? , ? )");
			PreparedStatement psmarks = c.prepareStatement("INSERT INTO MARKS (STDID,PHY,CHEM,COMP,MATH,ENG) VALUES( ? , ? , ? , ? , ? , ? )");
			PreparedStatement pspersonal = c.prepareStatement("INSERT INTO PERSONAL (STDID,GENDER,EMAIL,PHONE) VALUES( ? , ? , ? , ? )");

			psdetails.setString(1,stdId);psdetails.setInt(2,n);psdetails.setString(3,name);psdetails.setString(4,dept);psdetails.setString(5,clg);
			psmarks.setString(1,stdId);psmarks.setInt(2,phy);psmarks.setInt(3,chem);psmarks.setInt(4,comp);psmarks.setInt(5,math);psmarks.setInt(6,eng);
			pspersonal.setString(1,stdId);pspersonal.setString(2,gender);pspersonal.setString(3,email);pspersonal.setString(4,phone);
			psdetails.executeUpdate();
			psmarks.executeUpdate();
			pspersonal.executeUpdate();
			/* st = c.createStatement();
			String sql = "INSERT INTO STUDENTDETAIL (STDID,ENROLL,NAME,DEPT,COLLEGE) VALUES( '"+stdId+"', '" +n+"', '"+name+"', '"+dept+"', '"+clg+"' );";
			st.executeUpdate(sql);
			sql = "INSERT INTO MARKS (STDID,PHY,CHEM,COMP,MATH,ENG) VALUES( '"+stdId+"', '"+phy+"', '" +chem+"', '"+comp+"', '"+math+"', '"+eng+"' );";
			st.executeUpdate(sql);
			sql = "INSERT INTO PERSONAL (STDID,GENDER,EMAIL,PHONE) VALUES( '"+stdId+"', '"+gender+"', '" +email+"', '"+phone+"' );";
			st.executeUpdate(sql); */

			}
			catch(Exception e){e.printStackTrace();}
	}


	public StudentDetail displayIndividualDetail(String n){

			//Statement st = null;
			StudentDetail en = new StudentDetail();
			Marks m = new Marks();
			Personal p = new Personal();
			String name=null,dept=null,clg=null,stdId=null,gender=null,email=null,phone=null;
			int phy=0,chem=0, comp=0, math=0, eng=0, no=0;

		try{

			/* String s = "SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
						"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
						"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = '"+n+"' INNER JOIN personal on personal.stdid = '"+n+"' WHERE studentdetail.stdid = '"+n+"';"; */

			PreparedStatement ps = c.prepareStatement("SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
						"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
						"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = ? INNER JOIN personal on personal.stdid = ? WHERE studentdetail.stdid = ? ");
			ps.setString(1,n);ps.setString(2,n);ps.setString(3,n);

			//st = c.createStatement();
			//ResultSet rs = st.executeQuery(s);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				stdId = rs.getString("stdid");
				no = rs.getInt("enroll");
				name = rs.getString("name");
				dept = rs.getString("dept");
				clg = rs.getString("college");
				gender = rs.getString("gender");
				email = rs.getString("email");
				phone = rs.getString("phone");
				phy  = rs.getInt("phy");
				chem  = rs.getInt("chem");
				comp  = rs.getInt("comp");
				math  = rs.getInt("math");
				eng = rs.getInt("eng");
			}
			rs = null;
			if(stdId!=null){
						en.setStdId(stdId);
						en.setEnroll(no);
						en.setName(name);
						p.setGender(gender);
						en.setCollege(clg);
						en.setDept(dept);
						p.setEmail(email);
						p.setPhone(phone);
						m.setMarkPhy(phy);
						m.setMarkChem(chem);
						m.setMarkComp(comp);
						m.setMarkMath(math);
						m.setMarkEng(eng);

						en.setMarks(m);
						en.setPersonal(p);

			return en;
			}
		}
		catch(Exception e){e.printStackTrace();}
		return null;
	}

	public void updateRecord(String stdId, StudentDetail en){

			//Statement st = null;
			Marks m = en.getMarks();
			Personal p = en.getPersonal();
			String name=en.getName(), clg=en.getCollege(), gender=p.getGender() ,email=p.getEmail(), phone=p.getPhone();
			int phy = m.getMarkPhy(), chem = m.getMarkChem(), comp = m.getMarkComp(), math = m.getMarkMath(), eng = m.getMarkEng(), no=en.getEnroll();

		try{

			PreparedStatement psdetails = c.prepareStatement("UPDATE STUDENTDETAIL SET ENROLL = ? , NAME = ? , COLLEGE = ? WHERE STDID = ?");
			PreparedStatement psmarks = c.prepareStatement("UPDATE MARKS SET PHY = ? , CHEM = ? , COMP = ? , MATH = ? ,ENG = ? WHERE STDID = ?");
			PreparedStatement pspersonal = c.prepareStatement("UPDATE PERSONAL SET GENDER = ? , EMAIL = ? , PHONE = ? WHERE STDID = ?");
			psdetails.setInt(1,no);psdetails.setString(2,name);psdetails.setString(3,clg);psdetails.setString(4,stdId);
			psmarks.setInt(1,phy);psmarks.setInt(2,chem);psmarks.setInt(3,comp);psmarks.setInt(4,math);psmarks.setInt(5,eng);psmarks.setString(6,stdId);
			pspersonal.setString(1,gender);pspersonal.setString(2,email);pspersonal.setString(3,phone);pspersonal.setString(4,stdId);

			psdetails.executeUpdate();
			psmarks.executeUpdate();
			pspersonal.executeUpdate();

			/* st = c.createStatement();
			String sql = "UPDATE STUDENTDETAIL SET ENROLL = '" +no+"', NAME = '"+name+"', COLLEGE = '"+clg+"' WHERE STDID = '"+stdId+"';";
			st.executeUpdate(sql);
			sql = "UPDATE MARKS SET PHY = '"+phy+"', CHEM = '" +chem+"', COMP = '"+comp+"',MATH = '"+math+"',ENG = '"+eng+"' WHERE STDID = '"+stdId+"';";
			st.executeUpdate(sql);
			sql = "UPDATE PERSONAL SET GENDER = '"+gender+"',EMAIL = '" +email+"',PHONE = '"+phone+"' WHERE STDID = '"+stdId+"';";
			st.executeUpdate(sql); */
			}
			catch(Exception e){e.printStackTrace();}
			//c = null;
		}

	public ArrayList<StudentDetail> fetchDetail(){

			//Statement st = null;
			StudentDetail en = null;
			Marks m = null;
			Personal p = null;
			String name=null,dept=null,clg=null,stdId=null,gender=null,email=null,phone=null;
			int phy=0,chem=0, comp=0, math=0, eng=0, no=0;
			ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();

		try{

			/* String s1 = "SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
						"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
						"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = studentdetail.stdid "+
						"INNER JOIN personal on marks.stdid = personal.stdid;";

			st = c.createStatement();
			ResultSet rs = st.executeQuery(s1); */

			PreparedStatement ps = c.prepareStatement("SELECT studentdetail.stdid, studentdetail.name, studentdetail.enroll, studentdetail.dept,"+
						"studentdetail.college, marks.phy, marks.chem, marks.comp, marks.math, marks.eng, personal.gender, personal.email,"+
						"personal.phone FROM studentdetail INNER JOIN marks ON marks.stdid = studentdetail.stdid "+
						"INNER JOIN personal on marks.stdid = personal.stdid");
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				en = new StudentDetail();
				m = new Marks();
				p = new Personal();
				stdId = rs.getString("stdid");
				no = rs.getInt("enroll");
				name = rs.getString("name");
				dept = rs.getString("dept");
				clg = rs.getString("college");
				gender = rs.getString("gender");
				email = rs.getString("email");
				phone = rs.getString("phone");
				phy  = rs.getInt("phy");
				chem  = rs.getInt("chem");
				comp  = rs.getInt("comp");
				math  = rs.getInt("math");
				eng = rs.getInt("eng");
				en.setStdId(stdId);
				en.setEnroll(no);
				en.setName(name);
				en.setDept(dept);
				en.setCollege(clg);
				p.setGender(gender);
				p.setEmail(email);
				p.setPhone(phone);
				m.setMarkPhy(phy);
				m.setMarkChem(chem);
				m.setMarkComp(comp);
				m.setMarkMath(math);
				m.setMarkEng(eng);

				en.setMarks(m);
				en.setPersonal(p);
				list.add(en);
				en=null;
				p=null;
				m=null;
			}

		}
		catch(Exception e){e.printStackTrace();}
		return list;
	}



		public boolean searchId(String stdId){
			boolean flag = false;
			//Statement st = null;
			try{
			//String s = "SELECT * FROM STUDENTDETAIL WHERE STDID = '"+stdId+"';";
			//st = c.createStatement();
			//ResultSet rs = st.executeQuery(s);
			PreparedStatement ps = c.prepareStatement("SELECT * FROM STUDENTDETAIL WHERE STDID = ? ");
			ps.setString(1,stdId);
			ResultSet rs = ps.executeQuery();
			flag = rs.next();
			}
			catch(Exception e){}
			return flag;
		}


	public void deleteRecord(String stdId){

			Statement st = null;

		try{

			/* String s = "DELETE FROM STUDENTDETAIL WHERE STDID = '"+stdId+"';";
			st = c.createStatement();
			st.executeUpdate(s);
			st = null; */
			PreparedStatement ps = c.prepareStatement("DELETE FROM STUDENTDETAIL WHERE STDID = ? ");
			ps.setString(1,stdId);
			ps.executeUpdate();

		}
		catch(Exception e){}
	}

	public void deleteEntireData(){


		Statement st = null;

		try{

			/* st=c.createStatement();
			st.executeUpdate("TRUNCATE TABLE STUDENTDETAIL CASCADE;");
			st = null; */
			PreparedStatement ps = c.prepareStatement("TRUNCATE TABLE STUDENTDETAIL CASCADE");
			ps.executeUpdate();


		}
		catch(Exception e){}
		finally{
			try{
			if(c!=null){
			st.close();
			c.close();
				}
			}
			catch(Exception e){}
		}
	}

}
