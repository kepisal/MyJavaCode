package Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListToDb {
		Scanner sc=new Scanner(System.in);
		DbStore _DbStore=new DbStore();
		ArrayListStore _ArrayListStore=new ArrayListStore();
		ArrayListToDb(){
		}
		
		public void Transfer_Array_To_Db(){
			if(_ArrayListStore.emp.isEmpty()==true){
				System.out.println("There are no records to Inserted");
			}else{
			_DbStore.ConnectToSql();
			for(int i=0; i<_ArrayListStore.ReturnObject().size(); i++){
				_DbStore.InsertData(
									_ArrayListStore.emp.get(i).Get_Id(),
									_ArrayListStore.emp.get(i).Get_Name(), 
									_ArrayListStore.emp.get(i).Get_Gender(),
									_ArrayListStore.emp.get(i).Get_Phone(), 
									_ArrayListStore.emp.get(i).Get_Address()
									);
			}
			System.out.println("Do you want to clear ArrayList? (y/n)");
			if(sc.nextLine().equals("y")){
				_ArrayListStore.emp.clear();
				System.out.println("Temporary Data has been clear");
			}
			}
		}
}
