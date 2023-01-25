package com.sapp;
import java.io.Serializable;
import java.util.*;
public class Marks implements Serializable{

	int phy;
	int chem;
	int comp;
	int math;
	int eng;
	private static final long SerialVersionUID=1L;
		
	public void setMarkPhy(int phy){this.phy=phy;}
	public void setMarkChem(int chem){this.chem=chem;}
	public void setMarkComp(int comp){this.comp=comp;}
	public void setMarkMath(int math){this.math=math;}
	public void setMarkEng(int eng){this.eng=eng;}
	
	public int getMarkPhy(){return phy;}
	public int getMarkChem(){return chem;}
	public int getMarkComp(){return comp;}
	public int getMarkMath(){return math;}
	public int getMarkEng(){return eng;}
	
	
	
	
	
	
}