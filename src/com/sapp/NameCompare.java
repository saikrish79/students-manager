package com.sapp;
import java.util.*;

public class NameCompare implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		return s1.getName().compareTo(s2.getName());
	}
}