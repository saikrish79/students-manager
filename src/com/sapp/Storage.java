package com.sapp;
import java.sql.*;
import java.util.*;

public interface Storage{
		public boolean searchId(String stdId);
		public void addDetail(StudentDetail en);
		public StudentDetail displayIndividualDetail(String n);
		public void updateRecord(String stdId, StudentDetail en);
		public void deleteRecord(String n);
		public void deleteEntireData();
		public ArrayList<StudentDetail> fetchDetail();
}