package com.sapp;
import java.util.*;

public class ClgCompare implements Comparator<StudentDetail>{

	public int compare(StudentDetail s1, StudentDetail s2){
		return s1.getCollege().compareTo(s2.getCollege());
	}
}