package Employee;
import java.util.*;
import java.util.Locale.Category;
import java.awt.font.NumericShaper;
import java.sql.*;

import javax.print.attribute.standard.Finishings;
public class DbStore {
	boolean _valid_ID=false;
	private String _DatabaseName;
	private String _ServerName;
	private int _Port;
	private String _user;
	private String _password;
	private String _ConnectionString;
	Connection _connection;
	Statement _statement;
	Scanner sc=new Scanner(System.in);
	ResultSet _resultset;
	public DbStore(){
		//System.out.println("Connection Defualt on SqlServer");
		_DatabaseName="dbemployees";
		_ServerName="NOKKSAL-PC\\NOKKSAL";
		_Port=2951;
		_user="sa";
		_password="kepisal084498";
	}
	
	//New Parametter Constructor
	public DbStore(String ServerName,int port,String user,String password, String DatabaseName){
		_ServerName=ServerName;
		_Port=port;
		_user=user;
		_password=password;
		_DatabaseName=DatabaseName;		
	}
	
	//============= Getting Function =============
	String Get_DbName(){return _DatabaseName;}
	String Get_ServerName(){return _ServerName;}
	int Get_port(){return _Port;}
	String Get_User(){return _user;}
	String get_Password(){return _password;}
	String Get_Connection(){
		return "jdbc:sqlserver://localhost\\"+_ServerName+":"+_Port+";user="+_user+";password="+_password+";databaseName="+_DatabaseName+"";
	}
	
	//============= Setting Method =============
	public void Set_DatabaseName(String newDbName){
		_DatabaseName=newDbName;
	}
	public void Set_ServerName(String newServerName){
		_ServerName=newServerName;
	}
	public void Set_Port(int newPort){
		_Port=newPort;
	}
	public void Set_User(String newUser){
		_user=newUser;
	}
	public void Set_Password(String newPassword){
		_password=newPassword;
	}
	
	//============= Connection To Database =============
	public void ConnectToSql(){
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			_connection=DriverManager.getConnection(Get_Connection());
			System.out.println("Connected");			
		}catch(Exception e){
			//System.out.println(e.getMessage());
			System.out.println("Fail");
		}
	}
	
	//****************************************** Insertion Data Into Database ******************************************
	public void InsertDataToDb(){
		int Id;
		String Name,Gender,Phone,Address;
		System.out.println("---------------- Insertion To Database ----------------");
		System.out.println("ID			:"); Id=sc.nextInt(); sc.nextLine();
		Check_Valid_ID(Id);
		if(_valid_ID==false){
			System.out.print("\nName		:"); Name=sc.nextLine();
			System.out.print("\nGender		:"); Gender=sc.nextLine();
			System.out.print("\nPhone		:"); Phone=sc.nextLine();
			System.out.print("\nAddress		:"); Address=sc.nextLine();
			InsertData(Id, Name, Gender, Phone, Address);
		}else{
			System.out.println("Duplicate ID");
		}
	}
	
	//****************************************** Searching Data In Database ******************************************
	public void SearchDataInDb(){
		int Id;
		System.out.println("---------------- Search Data In Database ----------------");
		System.out.println("ID			:"); Id=sc.nextInt(); sc.nextLine();
		SearchDb(Id);
	}
	
	//****************************************** Deleteing Data In Database ******************************************
	public void DeletedDataInDb(){
		int Id;
		System.out.println("---------------- Deleted Data In Database ----------------");
		System.out.println("ID			:"); Id=sc.nextInt(); sc.nextLine();
		DeletedData(Id);
	}
	
	//****************************************** Editting Data In Database ******************************************
	public void EditDataInDb(){
		int Id;
		String Name,Gender,Phone,Address;
		System.out.println("---------------- Edit Data In Database ----------------");
		System.out.println("ID			:"); Id=sc.nextInt(); sc.nextLine();
		Check_Valid_ID(Id);
		if(_valid_ID==true){
			System.out.print("\nName		:"); Name=sc.nextLine();
			System.out.print("\nGender		:"); Gender=sc.nextLine();
			System.out.print("\nPhone		:"); Phone=sc.nextLine();
			System.out.print("\nAddress		:"); Address=sc.nextLine();
			EditDb(Id, Name, Gender, Phone, Address);
		}else{
			System.out.println("Invalid ID");
		}
	}
	
	//============= InserData Method ============= 
	public void InsertData(int Id,String Name,String Gender,String Phone, String Address){
	try {
		PreparedStatement _PreStatement=_connection.prepareStatement("insert into tbemployees(id,name,gender,phone,address) values(?,?,?,?,?)");
		_PreStatement.setInt(1, Id);
		_PreStatement.setString(2, Name);
		_PreStatement.setString(3, Gender);
		_PreStatement.setString(4, Phone);
		_PreStatement.setString(5, Address);
		_PreStatement.executeUpdate();
		System.out.println("Inserted");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println(e.getMessage());
	}
	}
	
	//============= ListData Method =============
	public void GetAllDataFromDb(){
		try {
			System.out.println("----------------------- List All Employees From DB ---------------------------");
			PreparedStatement _PreStatement=_connection.prepareStatement("select * from tbemployees");
			_resultset=_PreStatement.executeQuery();
			while(_resultset.next()){
				System.out.println(_resultset.getString(1)+").> "+_resultset.getString(2)+", "+_resultset.getString(3)+", "+_resultset.getString(4)+", "+_resultset.getString(5));
				System.out.println("**************************************************************************************************************");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	
	//============= SearchData Method =============
	public void SearchDb(int FindID){
		int i=0;
		try{
			Check_Valid_ID(FindID);
			if(_valid_ID==true){
				PreparedStatement _PreStatement=_connection.prepareStatement("select * from tbemployees where Id="+FindID);
				_resultset=_PreStatement.executeQuery();
				while(_resultset.next()){
					System.out.println(_resultset.getString(1)+"  "+_resultset.getString(2)+"  "+_resultset.getString(3)+"  "+_resultset.getString(4)+"  "+_resultset.getString(5));
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(_valid_ID==false){
			System.out.println("Data not Found");
		}
	}
	
	//============= EditData Method =============
	public void EditDb(int FindID,String name,String gender,String phone,String address){
		try{
			Check_Valid_ID(FindID);
			if(_valid_ID==true){
				PreparedStatement _PreStatement=_connection.prepareStatement("Update tbemployees set Name=?,Gender=?,Phone=?,Address=? where Id=?");
				_PreStatement.setString(1, name);
				_PreStatement.setString(2, gender);
				_PreStatement.setString(3, phone);
				_PreStatement.setString(4, address);
				_PreStatement.setInt(5, FindID);
				_PreStatement.executeUpdate();
				System.out.println("Data has been updated.");
			}			
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
		if(_valid_ID==false){
			System.out.println("Fail");
		}
	}
	
	//============= DeleteData Method =============
	public void DeletedData(int FindID){
		try{
			Check_Valid_ID(FindID);
			if(_valid_ID==true){
				PreparedStatement _prestatement=_connection.prepareStatement("Delete from tbemployees where Id="+FindID);
				_prestatement.executeUpdate();
				System.out.println("Record has been deleted");
			}			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		if(_valid_ID==false){
			System.out.println("Fail");
		}
	}
	
	//============= Check Valid Id In Database =============
	public void Check_Valid_ID(int newID){
		try{
			Statement stm=_connection.createStatement();
			_resultset=stm.executeQuery("select Id from tbemployees where Id="+newID);
			while(_resultset.next()){
				if(_resultset.getInt(1)==newID){
					_valid_ID=true;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//============= Return Number Of Record In Database =============
	int Get_NumberOfElement(){
		int i=0;
		try{
			Statement stm = _connection.createStatement();
		       _resultset = stm.executeQuery("select count(*) as 'rowcount' from tbemployees");
		       _resultset.next();
		       i=_resultset.getInt(1);
		       _resultset.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return i;
	}

}
