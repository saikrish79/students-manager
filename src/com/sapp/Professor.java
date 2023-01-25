package com.sapp;
import java.io.Serializable;
public class Professor implements Serializable{
	String ID;
	String name;
	String title;
	String email;
	String phone;
	String dept;
	private static final long SerialVersionUID=1L;
	
	public void setID(String ID){this.ID=ID;}
	public void setName(String name){this.name=name;}
	public void setTitle(String title){this.title=title;}
	public void setEmail(String email){this.email=email;}
	public void setPhone(String phone){this.phone=phone;}
	public void setDept(String dept){this.dept=dept;}
	
	public String getID(){return ID;}
	public String getName(){return name;}
	public String getTitle(){return title;}
	public String getEmail(){return email;}
	public String getPhone(){return phone;}
	public String getDept(){return dept;}
}