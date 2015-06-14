package Employee;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class FileStore {
	ArrayListStore alist_store=new ArrayListStore();
	private ArrayList<String> alist_of_SubData;
	private String spl;
	boolean _appendfile=false;
	File _ioFile;
	private String _filename="";
	FileStore(String FileName){
		_filename=FileName;
	}
	FileStore(){}
	public void CreateNewOrExisted(){
		try{
			_ioFile=new File(_filename);
			if(_ioFile.exists()){
				System.out.println("File Exist!!!");
				_appendfile=true;
			}else{
				_ioFile.createNewFile();
				System.out.println("New File Created.");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Read_File_To_Display(){

		try{
			BufferedInputStream bint=new BufferedInputStream(new FileInputStream(_filename));
			int i;
			while((i=bint.read())!=-1){		
				System.out.print((char)i);
				if((int)i==(int)'.'){
					System.out.print("\n");
				}
			}
			bint.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	/*public void Write_File_To_Db(){
			try{
				BufferedInputStream bint=new BufferedInputStream(new FileInputStream(_filename));
				int i;
				while((i=bint.read())!=-1){		
					System.out.print((char)i);
					if((int)i==(int)'.'){
						System.out.print("\n");
					}
					spl+=String.valueOf((char)i);
				}
				bint.close();
				this.SubData(spl);
				
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
	}*/
	public void Write_File_To_Arraylist(){
		//stuck
		String String_of_F_To_A="";
		try{
			BufferedInputStream bint=new BufferedInputStream(new FileInputStream(_filename));
			int i;
			while((i=bint.read())!=-1){		
				System.out.print((char)i);
				if((int)i==(int)'.'){
					System.out.print("\n");
				}
				String_of_F_To_A+=String.valueOf((char)i);
			}
			bint.close();
			int _lastindex=0;
			this.SubData(String_of_F_To_A);
			if(alist_store.emp.size()!=-1){
				_lastindex=alist_store.emp.size()-1;
			}
			for(;_lastindex<alist_store.emp.size(); _lastindex++){
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Write_Arraylist_To_File(){
		Scanner _answerAppend=new Scanner(System.in);
		String _answer;
		if(_appendfile==true){
			System.out.println("Your File's existing, Do you want to append Data or not?(y/n) : ");
			_answer=_answerAppend.nextLine();
			if(_answer.equals("y")){
				_appendfile=true;
			}else if(_answer.equals("n")){
				_appendfile=false;
			}else{
				System.out.println("invalid insertion...");
				Write_Arraylist_To_File();
			}
		}
		ArrayListStore a=new ArrayListStore();
		try{
			
			BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(_filename,_appendfile));
			for(int i=0; i<a.emp.size(); i++){
				bout.write(a.emp.get(i).Get_Information().getBytes());
			}
			bout.flush();
			bout.close();
			System.out.println("Data Wrote!!!");
			a.Clear_List();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	ArrayList<String> SubData(String StringData){
		alist_of_SubData=new ArrayList<>();
		String MyString=StringData.replace('.', ',');
		Pattern pattern = Pattern.compile(": (.*?),");
	    Matcher matcher = pattern.matcher(MyString);
	    while (matcher.find()) {
	        System.out.println(matcher.group(1));
	        alist_of_SubData.add(matcher.group(1));
	    }
	    return alist_of_SubData;
	}
}
