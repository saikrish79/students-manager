package com.sapp;
import java.io.Serializable;
import java.util.*;
public class Personal implements Serializable{

	String gender;
	String email;
	String phone;
	private static final long SerialVersionUID=1L;
	
	public void setGender(String gender){this.gender = gender;}
	public void setEmail(String email){this.email=email;}
	public void setPhone(String phone){this.phone=phone;}
	
	public String getGender(){return gender;}
	public String getEmail(){return email;}
	public String getPhone(){return phone;}
	
}