package Employee;

public class Occupations extends employees {
	private int _Id;
	private String _Position;
	private double _Salary;
	public Occupations(int id,String position, double salary){
		_Id=id;
		_Position=position;
		_Salary=salary;
	}
	int Get_Id(){return _Id;}
	String Get_Position(){return _Position;}
	double Get_Salary(){return _Salary;}
	
	public void Set_Id(int newId){_Id=newId;}
	void Set_Position(String newPosition){_Position=newPosition;}
	void Set_Salary(double newSalary){_Salary=newSalary;}
	
	public String Get_Information(){return "Position: "+Get_Position()+", Salary: "+Get_Salary();}
	
}
