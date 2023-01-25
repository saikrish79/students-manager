package com.sapp;
import java.util.*;

public class DeptCompare implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		return s1.getDept().compareTo(s2.getDept());
	}
}