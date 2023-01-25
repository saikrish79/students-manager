package com.sapp;

import java.sql.*;

public class DBUtil{

	private Connection c;
	private static DBUtil obj;
	
	private DBUtil(){
		try{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost/student", "postgres", "123");													
			}
		catch(Exception e){
			e.printStackTrace();
			}
	}
	
	public Connection getConnection(){
	return c;
	}

	public static DBUtil getInstance(){
		if(obj == null){
		obj = new DBUtil();
		return obj;}
		else
		return obj;
	}
	
	//To Check the table already exists or create a new table
		
		public void checkTable(){
			
			Statement st=null;
		
		try{
			

		DatabaseMetaData dm = c.getMetaData();
		ResultSet table = dm.getTables(null, null, "studentdetail", null);
		if (table.next()) {
		  //System.out.println("Exists");
		}
		else {
			st = c.createStatement();
			String sql = "CREATE TABLE STUDENTDETAIL " +
			"(ID 			 SERIAL," +
			" STDID          VARCHAR(20) 	PRIMARY KEY," +
            " ENROLL 		 INT 	     	NOT NULL," +
            " NAME           VARCHAR(50)    NOT NULL, " +
            " DEPT           VARCHAR(50)    NOT NULL, " +
            " COLLEGE        VARCHAR(50)	NOT NULL);";
			st.executeUpdate(sql);
			sql = "CREATE TABLE MARKS " +
			"(STDID     VARCHAR(20) 	PRIMARY KEY REFERENCES STUDENTDETAIL(STDID) ON DELETE CASCADE," +
			" PHY 		 INT 	     	NOT NULL," +
			" CHEM 		 INT 	     	NOT NULL," +
            " COMP 		 INT 	     	NOT NULL," +
            " MATH 		 INT 	     	NOT NULL," +
            " ENG 		 INT 	     	NOT NULL);";
			st.executeUpdate(sql);
			sql = "CREATE TABLE PERSONAL " +
			"(STDID        VARCHAR(20) 	  PRIMARY KEY REFERENCES STUDENTDETAIL(STDID) ON DELETE CASCADE," +
			" GENDER       VARCHAR(50)    NOT NULL, " +
            " EMAIL        VARCHAR(50)    NOT NULL, " +
            " PHONE        VARCHAR(50)	  NOT NULL);";
			st.executeUpdate(sql);
			System.out.println("New Table Created");
			st=null;
			c = null;
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		}
	
	public void close(){
	try{
	if(c!=null)
	c.close();
	}
	catch(Exception e){}
	}
}