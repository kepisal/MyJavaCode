package Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListStore {
	Scanner sc=new Scanner(System.in);
	Payroll py=new Payroll();
	ArrayListStore(){}
	static ArrayList<employees> emp=new ArrayList<>();
	int numberofemp;
	int n=0;
	int id;
	String name,gender,phone,address;
	public void Registration(){
		boolean t=true;
		int i=0,n;

		System.out.print("------------------------Employee Payroll------------------------");
		System.out.print("\nPlease input number of insertion ");
		n=sc.nextInt();
		for(i=0; i<n; i++){
			System.out.print("\n***Employee Index ["+(emp.size())+"] ***\n");
			System.out.print("ID       : "); id=sc.nextInt(); sc.nextLine();
			if(Checked_Id(id)==true){
				System.out.println("Duplicated ID");
				t=false;
			}else{
							System.out.print("Name     : "); name=sc.nextLine();
							System.out.print("Gender   : "); gender=sc.nextLine();
							System.out.print("Phone    : "); phone=sc.nextLine();
							System.out.print("Address  : "); address=sc.nextLine();
							emp.add(new employees(id, name, gender, phone, address));
			}
		}
		if(t==true){
			System.out.println("Registration Completed");
			py.Operation_again();
		}else{
			System.out.println("Uncompleted");
			py.Operation_again();
		}		
	}
	int Get_Id(){return id;}
	String Get_Name(){return name;}
	String Get_Gender(){return gender;}
	String Get_Phone(){return phone;}
	String Get_Address(){return address;}
	public void Searching(){
		int id;
		boolean b=false;
		System.out.println("Please input Employee ID for Searching");
		id=sc.nextInt();
		int i=0;
		if(emp.size()==0 || emp.isEmpty()==true){
			System.out.println("There are no data");
			py.Operation_again();
		}else{
			for(i=0; i<emp.size(); i++){
				if(emp.get(i).Get_Id()==id){
					System.out.println(emp.get(i).Get_Information());
					b=true;
				}
			}
			if(b==false){
				System.out.println("Data not found");
			}
		}
		py.Operation_again();
	}
	public void Deletting(){
		boolean t=true;
		int id;
		System.out.println("Please input Employee ID To Deleted");
		id=sc.nextInt();
		int i=0;
		if(emp.size()==0 || emp.isEmpty()==true){
			System.out.println("There are no data");
		}else{
			while(i<emp.size()){
				if(emp.get(i).Get_Id()==id){
					emp.remove(i);
					t=false;
					break;
				}
				i++;
			}
			if(t==false){
				System.out.println("Deleted");
			}else{
				System.out.println("No Data");
			}
		}
		py.Operation_again();
	}
	
	public void Editing(){
		boolean b=true;
		int id;
		String name,gender, phone,address;
		System.out.println("Please input Employee ID to Edited");
		id=sc.nextInt();
		int i=0;
		if(emp.size()==0||emp.isEmpty()==true){
			System.out.println("No Data");
		}
		else{
			while(i<emp.size()){
				if(emp.get(i).Get_Id()==id){
					System.out.println(emp.get(i).Get_Information());
					System.out.println("-------------------Please Input New Data----------------------");
					sc.nextLine();
					System.out.print("Name     : "); name=sc.nextLine();
					System.out.print("Gender   : "); gender=sc.nextLine();
					System.out.print("Phone    : "); phone=sc.nextLine();
					System.out.print("Address  : "); address=sc.nextLine();
					emp.set(i, new employees(id,name,gender,phone,address));
					System.out.println("Employee ID["+emp.get(i).Get_Id()+"], has been changed");
					b=false;
				}
				i++;
			}
			if(b==true){
				System.out.println("Data not found");
			}else{
				System.out.println("Data Editted");
			}
		}
		py.Operation_again();
	}
	boolean Checked_Id(int newId){
		int i=0;
		while(i<emp.size()){
			if(emp.get(i).Get_Id()==newId){
				return true;
			}
			i++;
		}
		return false;
	}	
	public void listData(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("---------------------- List of Employees ----------------------");
		System.out.println("There are "+emp.size()+" records.");
		int i=0;
		while(i<emp.size()){
			System.out.println("[---"+emp.get(i).Get_Id()+"---]");
			System.out.println(" "+emp.get(i).Get_Information()+" ");
			i++;
		}
		py.Operation_again();
	}
	public void Clear_List(){
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.println("---------------------- List of Employees ----------------------");
		System.out.println("There are "+emp.size()+" records has been clears.");
		emp.clear();
		py.Operation_again();
	}
	ArrayList<employees> ReturnObject(){
		return emp;
	}
}
