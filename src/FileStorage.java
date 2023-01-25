import com.sapp.*;
import java.util.*;
import java.io.*;


public class FileStorage implements Storage{

	static int count = 1;
	Utility ut;
	
	public FileStorage(){
		this.ut = new Utility();
		try{
		String fileName = getFileName();
		}
		catch(Exception e){}
	}
	

//To add new record details to the file
	
	public void addDetail(StudentDetail en){
			FileOutputStream fos=null;
			ObjectOutputStream oos=null;
			String fileName = null;
	
		 try {	
			 fileName = getFileName();
			File file = new File(fileName);
			fos = new FileOutputStream(fileName,ut.checkFile(file));
			oos = new ObjectOutputStream(fos);

			oos.reset();		
			oos.writeObject(en);		
			
			} catch (Exception e) {
				//e.printStackTrace();
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

	//To search through the records and display the details of a specified record
	
	public StudentDetail displayIndividualDetail(String n){
		
			FileInputStream fis = null;
			ObjectInputStream in = null;
			StudentDetail en = null;
			
			for(int t = 1;t<=count;t++){			
			try{
			String s = "student"+t+".txt";
			fis = new FileInputStream(s);
			
			while(true){
				in = new ObjectInputStream(fis);
				en = new StudentDetail();
				en = (StudentDetail) in.readObject();
				if(n.equalsIgnoreCase(en.getStdId())){
				return en;
				}
				en = null;
				}
			}catch(EOFException ex){}
			catch(StreamCorruptedException a){}
			catch(Exception e){e.printStackTrace();}
			finally{
			try{
				in.close();
				fis.close();
				}
			catch(IOException e){}
			}
		}
		return en;
	}
	
	
	//To update or modify the contents of the records stored
	
	public void updateRecord(String stdId, StudentDetail en){
			
			ArrayList<StudentDetail> list = null;
			for(int t = count;t>0;t--){
				String s = "student"+t+".txt";
			list = new ArrayList<StudentDetail>();
			list = ut.loadList(s);
			
			for(int i=0;i<list.size();i++){
				String temp = list.get(i).getStdId();
				
				if(temp.equalsIgnoreCase(stdId)){
					String dept = list.get(i).getDept();
					en.setDept(dept);
					list.set(i,en);
					ut.updateFile(list,s);
					t=0;
					break;
				}
	}}}
	
	
	
	public ArrayList<StudentDetail> fetchDetail(){
		
		ArrayList<StudentDetail> tempList = new ArrayList<StudentDetail>();
		tempList = ut.loadTempList(count);
		return tempList;
		
	}
	
	public boolean searchId(String stdId){
		ArrayList<StudentDetail> tempList = new ArrayList<StudentDetail>();
		tempList = ut.loadTempList(count);
		for(StudentDetail en : tempList){
			String temp = en.getStdId();
			if(temp!=null){
			if(temp.equalsIgnoreCase(stdId))
				return true;
			}
		}
		return false;
	}

	
	//To delete a speific record from the stored data
	
	public void deleteRecord(String stdId){	

			Scanner sc = new Scanner(System.in);
			
			ArrayList<StudentDetail> list = null;			
			for(int t = 1;t<=count;t++){
			String s = "student"+t+".txt";
			list = new ArrayList<StudentDetail>();
			list = ut.loadList(s);
			
			for(int i=0;i<list.size();i++){
				String temp = list.get(i).getStdId();
				if(temp.equalsIgnoreCase(stdId)){
					list.remove(i);
					ut.updateFile(list,s);
					t=count+1;
					break;
				}
			}
			list = null;
			}
			list = null;
			
			ArrayList<StudentDetail> tempList = new ArrayList<StudentDetail>();
			
			tempList = ut.loadTempList(count);
			
			
			for(int t = 1,i=0;t<=count;t++){
				String s = "student"+t+".txt";
				list = new ArrayList<StudentDetail>();
				while(i<(5*t)&& i<tempList.size()){
					list.add(tempList.get(i));
					i++;
				}
				ut.updateFile(list,s);
				list = null;
			}
	}
	
	//To delete the entire data that is stored in files

	public void deleteEntireData(){
			
			File file = null;
			for(int i = count;i>0;i--){
				String s = "student"+i+".txt";
				file = new File(s);
				file.delete();
			}
			count = 1;
	}
	
	//To get the name of the File where the data is to be written
	//new file name is returned when the no.of records in a file reaches "Five"
	
	public String getFileName()throws IOException{
	
		String s = "student"+count+".txt";
		int c;
		File file = new File(s);
		if(ut.checkFile(file))
		c = ut.checkCount(s,file);
		else{
			file.createNewFile();
			c=1;
		}
		if(c<5){
			return s;
		}
		else{
			count++;
			getFileName();
		}
		return s;	
	}

		
}