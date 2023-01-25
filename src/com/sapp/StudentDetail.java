package com.sapp;
import java.io.Serializable;
import java.util.*;
public class StudentDetail implements Serializable, Comparable<StudentDetail>{
	int enrollNo;
	String stdId;
	String name;
	String dept;
	String college;
	Marks m;
	Personal p;
	private static final long SerialVersionUID=1L;
	
	public StudentDetail(){
		this.m = new Marks();
		this.p = new Personal();
	}
	
	public void setStdId(String stdId){this.stdId=stdId;}
	public void setEnroll(int enrollNo){this.enrollNo=enrollNo;}
	public void setName(String name){this.name=name;}
	public void setDept(String dept){this.dept=dept;}
	public void setCollege(String college){this.college=college;}
	
	public String getStdId(){return stdId;}
	public int getEnroll(){return enrollNo;}
	public String getName(){return name;}
	public String getDept(){return dept;}
	public String getCollege(){return college;}
		
	public void setMarks(Marks m){this.m = m;}
	public void setPersonal(Personal p){this.p = p;}
	
	public Marks getMarks(){return m;}
	public Personal getPersonal(){return p;}
	
	
	public void printAllDetails(){
	System.out.println(this.stdId + " \t\t" +this.name + "\t\t" + this.enrollNo + " \t\t" +this.dept+ " \t" + this.college+ " \t\t"+p.getGender() + " \t\t" +p.getEmail()+ " \t\t" + p.getPhone()+"\n");	
	}
	
	public void printMark(){
	System.out.println("\nPhysics : " + m.getMarkPhy() + "\nChemistry : " + m.getMarkChem() + "\nComputer : " + m.getMarkComp()+ "\nMaths : " + m.getMarkMath()+ "\nEnglish : " + m.getMarkEng());	
	}
	
	public void printMarks(){
	System.out.println(m.getMarkPhy() + " \t" +m.getMarkChem() + " \t\t" + m.getMarkComp() + " \t\t" +m.getMarkMath()+ " \t\t" + m.getMarkEng()+"\n");
	}
	
	public int compareTo(StudentDetail s){
		return this.enrollNo - s.enrollNo;
	}
}