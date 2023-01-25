
package com.sapp;
import java.util.*;

public class CompComp implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		Marks m1 = s1.getMarks();
		Marks m2 = s2.getMarks();
		return m1.getMarkComp()-m2.getMarkComp();
	}
}