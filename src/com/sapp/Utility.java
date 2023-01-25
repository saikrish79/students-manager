package com.sapp;
import java.util.*;
import java.sql.*;
import java.io.*;

public class Utility{
	
	
	//To get the class names from the storage table

public ArrayList<String> getClassName(){
		
		Statement st = null;
		ResultSet distcls = null;
		ArrayList<String> className = new ArrayList<String>();
		
		try{
					DBUtil obj = DBUtil.getInstance();
					Connection c = obj.getConnection();
					st = c.createStatement();
					String s = "SELECT DISTINCT CLASS FROM STORAGE;";
					distcls = st.executeQuery(s);
					while(distcls.next()){
					s = distcls.getString("class");
					className.add(s);
					}
					}
					catch(Exception e){
						e.printStackTrace();
					}
		return className;
	}
	
	//To sort the given list of record
	
	public void sortRecord(ArrayList<StudentDetail> sortList){
		
		
		Scanner sc = new Scanner(System.in);
		StudentDetail en = new StudentDetail();
		System.out.println("Choose the field that needs to be sorted:\n1.Student ID\n2.Enroll no\n3.Name\n4.Dept\n5.College\n6.Gender\n7.Email\n8.Phone No.\n9.Marks");
		int ch = sc.nextInt();
		sc.nextLine();
		try{
				
		switch(ch){
			case 1:
			
				IdCompare ic =new IdCompare();
				Collections.sort(sortList,ic);
				break;
			
			case 2:
			
				Collections.sort(sortList);
				break;
			case 3:
			
				NameCompare nc =new NameCompare();
				Collections.sort(sortList,nc);
				break;
				
			case 4:
			
				DeptCompare dc =new DeptCompare();
				Collections.sort(sortList,dc);
				break;
				
			case 5:
			
				ClgCompare cc =new ClgCompare();
				Collections.sort(sortList,cc);
				break;
				
			case 6:
			
				GenderCompare gc =new GenderCompare();
				Collections.sort(sortList,gc);
				break;
				
			case 7:
			
				EmailCompare ec =new EmailCompare();
				Collections.sort(sortList,ec);
				break;
				
			case 8:
			
				PhoneCompare pc =new PhoneCompare();
				Collections.sort(sortList,pc);
				break;
				
			case 9:
			
				System.out.println("\n1.Physics\t2.Chemistry\t3.Computer\t4.Maths \t5.English\n");
				int ch1 = sc.nextInt(); 
				sc.nextLine();
				
				
				switch(ch1){
					
					case 1:
					PhyComp phc =new PhyComp();
					Collections.sort(sortList,phc);
					break;
					
					case 2:
					ChemComp chc =new ChemComp();
					Collections.sort(sortList,chc);
					break;
					
					case 3:
					CompComp coc =new CompComp();
					Collections.sort(sortList,coc);
					break;
					
					case 4:
					MathComp mc =new MathComp();
					Collections.sort(sortList,mc);
					break;
					
					case 5:
					EngComp enc =new EngComp();
					Collections.sort(sortList,enc);
					break;
					
					default:
					System.out.println("Invalid option");
					
				}
				break;
			
			default:
				System.out.println("Invalid option");
		}
		System.out.println("Choose an option:\n1.Ascending\n2.Descending");
		int temp = sc.nextInt();
		sc.nextLine();
		
		if(temp==2){
			Collections.reverse(sortList);
		}	
		en = null;
			System.out.println("Student ID\tName\t\tEnroll No\tDept\tCollege\t\tGender\t\tEmail\t\tPhone\n");
		for(StudentDetail tmp : sortList)
				tmp.printAllDetails();
			System.out.println("Physics\tChemistry\tComputer\tMaths\t\tEnglish\n");
		for(StudentDetail tmp : sortList)
				tmp.printMarks();
		}
		catch(Exception e){}
		
	}
	
	//To check the given ID is already exists
	
	public boolean checkId(String stdId){
		
		Class cls = null;
		Storage fs = null;
		boolean flag=false;
		ArrayList<String> className = new ArrayList<String>();
		className = this.getClassName();
		try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					flag = fs.searchId(stdId);
					if(flag)
						return false;
					}
					}
					catch(Exception e){}
		
		return true;
		
	}
	
	//To get the list of all the records
	
	public ArrayList<StudentDetail> getList(){
					
			Class cls = null;
			Storage fs = null;
			ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();
			ArrayList<String> className = new ArrayList<String>();
			className = this.getClassName();
		
		try{
					for(String s:className){
					cls = Class.forName(s);
					fs = (Storage)cls.newInstance();
					list.addAll(fs.fetchDetail());
					}
					}
					catch(Exception e){}
		return list;
	}
	
	
	
	
	
	
	//File Utility Functions
	
	//To add the contents from file to a list for manipulations
	
	public ArrayList<StudentDetail> loadList(String name){
			
			FileInputStream fis = null;
			ObjectInputStream in = null;
			StudentDetail en = null;
			ArrayList<StudentDetail> list = new ArrayList<StudentDetail>();
			File file = new File(name);
		try{	
			fis = new FileInputStream(name);
				
			while(true){
				in = new ObjectInputStream(fis);
				en = new StudentDetail();
				en = (StudentDetail) in.readObject();
				list.add(en);
				en = null;
				}
			}catch(EOFException ex){
			}
			catch(StreamCorruptedException a){
			}
			catch(Exception e){
			}
			finally{
			try{
				if(in!=null){
				in.close();
				fis.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//
	public ArrayList<StudentDetail> loadTempList(int count){
		
		ArrayList<StudentDetail> list = null;
		ArrayList<StudentDetail> tempryList = new ArrayList<StudentDetail>();
		try{		
		for(int t = 1;t<=count;t++){
				String s = "student"+t+".txt";
				list = new ArrayList<StudentDetail>();
				list = loadList(s);
				tempryList.addAll(list);
				list = null;
				}
		}
		catch(Exception e){}
		return tempryList;
	}

	//To update the modified contents to the file
	
	public void updateFile(ArrayList<StudentDetail> list,String name){
	
				FileOutputStream fos = null;
				ObjectOutputStream oos = null;
				try{
				
				File file = new File(name);
				file.delete();
				
				fos = new FileOutputStream(name);
				
				for(StudentDetail en : list){
				oos = new ObjectOutputStream(fos);
				oos.reset();				
				oos.writeObject(en);
				}
				}
				catch(IOException e){
				}
				finally{
					try{
						if(oos!=null){
						oos.close();
						fos.close();
						}
					}
					catch(IOException e){	
					}
				}
	
}
	//To check whether the passed file exists or not!

	public boolean checkFile(File file){
		if(file.exists()){
			return true;
		}
		else{
			return false;
		}
	}
	
	// To check the count of records found inside the specified file
	
	public int checkCount(String name,File file)throws IOException{
	
		
		int c=1;
		if(checkFile(file)){
			FileInputStream fis = null;
			ObjectInputStream in = null;
			StudentDetail en = null;
		try{			
				fis = new FileInputStream(name);
			while(true){
				in = new ObjectInputStream(fis);
				en = new StudentDetail();
				en = (StudentDetail) in.readObject();
				c++;
			}
			}catch(EOFException ex){	
			}
			catch(ClassNotFoundException e){
			}
			finally{
			try{
				if(in!=null){
				in.close();
				fis.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		}else{
			c=1;
		}
		return c;	
}

}