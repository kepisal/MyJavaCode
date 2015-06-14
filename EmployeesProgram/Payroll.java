package Employee;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

public class Payroll {
	static ArrayListStore als=new ArrayListStore();
	
	Scanner sc=new Scanner(System.in);
	DbStore _Dbstock;
	Payroll(){
		
	}	
	public void Pay_Operation(){
		try{
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("---------------------- Choose the Opertation ----------------------");
			System.out.println("1.) Insertion");
			System.out.println("2.) Searching");
			System.out.println("3.) Deleted");
			System.out.println("4.) Edition");
			System.out.println("5.) List All of Employees");
			System.out.println("---------------------- Temporary Data On Memory -------------------");
			System.out.println("6.) Go To File");
			System.out.println("7.) Go To Database");
			System.out.println("10.) Clear ArrayList");
			System.out.println("0.) Exit");
			
			System.out.print("please Input here :");
			switch(sc.nextInt()){
			case 1:
					System.out.println("Employee Registration");
					als.Registration();
					break;
			case 2:
					System.out.println("Search Employee");
					als.Searching();
					break;
			case 3:
					System.out.println("Deleted Employee");
					als.Deletting();
					break;
			case 4:
					System.out.println("Edit Employee Information");
					als.Editing();
					break;
			case 5:
					als.listData();
					break;
			case 6 :
					CaseToFile();
					break;
			case 7 :
					CaseToDatabase();
					break;
			case 10 :
					als.Clear_List();
					break;
			case 0:
					System.out.println("Goodbye");
					break;
			default :
					System.out.println("Invalid numbers");
					break;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	public void Operation_again(){
		System.out.print("\n1). Back to Menu\n");
		System.out.print("0). Exit\n");
		switch(sc.nextInt()){
		case 1:
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				Pay_Operation();
				
				break;
		case 0:
				System.out.println("Goodbye");
				break;
		default:
				System.out.println("Invalid numbers");
				break;
		}
	}
	public void Operation_Db_again(){
		System.out.print("\n1). Back to Menu\n");
		System.out.print("0). Exit\n");
		switch(sc.nextInt()){
		case 1:
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				CaseToDatabase();		
				break;
		case 0:
				System.out.println("Goodbye");
				break;
		default:
				System.out.println("Invalid numbers");
				break;
		}
	}
	public void CaseToDatabase(){
		int Id;
		String Name,Gender,Phone,Address;				
		try{
			_Dbstock=new DbStore();
			_Dbstock.ConnectToSql();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println("----------------------- Welcom To SqlServer DB-------------------------");
			System.out.println("(1). Insert");
			System.out.println("(2). Search");
			System.out.println("(3). Deleted");
			System.out.println("(4). Update");
			System.out.println("(5). List Data");
			System.out.println("(6). Write ArrayList To Database");
			System.out.println("(0). Back To Menu");
			switch(sc.nextInt()){
			case 1 :
					_Dbstock.InsertDataToDb();
					Operation_Db_again();
					break;
			case 2 :
					_Dbstock.SearchDataInDb();
					Operation_Db_again();
					break;
			case 3 :
					_Dbstock.DeletedDataInDb();
					Operation_Db_again();
					break;
			case 4 :
					_Dbstock.EditDataInDb();
					Operation_Db_again();
					break;
			case 5 :
					_Dbstock.GetAllDataFromDb();
					Operation_Db_again();
					break;
			case 6 :
					CaseArrayListToDatabase();
					break;
			case 0 :
					Pay_Operation();
					break;
			default :
					System.out.println("Invalid numbers");
					CaseToDatabase();
			}
		}catch(Exception e){
			System.out.println(e.getMessage()+"1");
		}
	}
	public void CaseArrayListToDatabase(){
		ArrayListToDb alstdb=new ArrayListToDb();
		try{
			alstdb.Transfer_Array_To_Db();
			Operation_Db_again();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void Operation_File_again(){
		System.out.print("\n1). Back to Menu\n");
		System.out.print("0). Exit\n");
		switch(sc.nextInt()){
		case 1:
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				CaseToFile();		
				break;
		case 0:
				System.out.println("Goodbye");
				break;
		default:
				System.out.println("Invalid numbers");
				break;
		}
	}
	public void CaseToFile(){
		Scanner sc_filename=new Scanner(System.in);
		FileStore fs;
		String _filename;
		int _answers;
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("1). Write Arraylist To File");
		System.out.print("\n2). Write File To ArrayList");
		System.out.print("\n3). Write File To Database");
		System.out.print("\n4). Read File");
		System.out.print("\n0). Back to Menu");
		System.out.print("\n Your Choice : ");
		_answers=sc.nextInt();
		switch(_answers){
		case 1 :
				//Wirte Arraylist to File
				System.out.print("Please insert File name : ");
				_filename=sc_filename.nextLine();
				//System.out.println(_filename);
				fs=new FileStore(_filename);
				fs.CreateNewOrExisted();
				fs.Write_Arraylist_To_File();
				Operation_File_again();
				break;
			
		case 2 :
				//Write File to Arraylist
				break;
		case 3 :
				//Write File To Database
				break;
		case 4 :
				System.out.print("Please insert File name : ");
				_filename=sc_filename.nextLine();
				fs=new FileStore(_filename);
				fs.Read_File_To_Display();
				Operation_File_again();
				break;
				
		case 0 :
				Pay_Operation();
				break;
		default :
				System.out.println("Invalid numbers");
				CaseToFile();
		}
	}
	public static void main(String[] args) {
		try{
			Payroll p=new Payroll();
			p.Pay_Operation();		
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
	}
	
}
