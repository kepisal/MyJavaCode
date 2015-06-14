package Employee;

public class employees {
	private String _Name;
	private String _Gender;
	private String _Phone;
	private String _Address;
	private int _Id;
	public employees() {
		// TODO Auto-generated constructor stub
	}
	public employees( int id,String name,String gender,String phone,String address){
		_Name=name; _Gender=gender; _Address=address+"."; _Id= id;
		_Phone=phone;
	}
	int Get_Id(){return _Id;}
	public String Get_Name(){return _Name;}
	public String Get_Gender(){return _Gender;}
	public String Get_Phone(){return _Phone;}
	public String Get_Address(){return _Address;}
	
	public void Set_Id(int newID){_Id=newID;}
	public void Set_Name(String newName){_Name=newName;}
	public void Set_Gender(String newGender){_Gender=newGender;}
	public void Set_Phone(String newPhone){_Phone=newPhone;}
	public void Set_Address(String newAddress){_Address=newAddress;}
	
	public String Get_Information(){return "ID: "+Get_Id()+", Name: "+Get_Name()+", Gender: "+Get_Gender()+", Phone: "+Get_Phone()+", Address: "+Get_Address();}
	

}
