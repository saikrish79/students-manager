package com.sapp;
import java.util.*;

public class IdCompare implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		return s1.getStdId().compareTo(s2.getStdId());
	}
}