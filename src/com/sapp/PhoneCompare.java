package com.sapp;
import java.util.*;

public class PhoneCompare implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		Personal p1 = s1.getPersonal();
		Personal p2 = s2.getPersonal();
		return p1.getPhone().compareTo(p2.getPhone());
	}
}