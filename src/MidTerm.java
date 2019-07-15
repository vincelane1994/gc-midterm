import java.util.Scanner;

public class MidTerm {
	
	static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		boolean mainMenu = true;//As long as this is true it will keep looping the program
		
		do {
			System.out.println("===Main Menu===");
			System.out.println("1. Add Member");//
			System.out.println("2. Remove Member");//
			System.out.println("3. Display Member Information");//
			System.out.println("4. Check In");//
			System.out.println("5. Generate Bill");//
			System.out.println("6. Exit");//
			
			System.out.print("What would you like to do?: ");
			int menuChoice = scnr.nextInt();//Declares the choice for the switch
			
			switch(menuChoice) {
			case 1://Add Member
				System.out.println("This will be adding a member");
				break;
			case 2://Remove Member
				System.out.println("This will remove a member");
				break;
			case 3://Display Member
				System.out.println("This will display member information");
				break;
			case 4://Check In
				System.out.println("This will check a member in");
				break;
			case 5://Generate Bill
				System.out.println("This will generate the bill");
				break;
			case 6://Exit
				mainMenu = false;
				break;
			}
		}while(mainMenu);
		System.out.println("Shutting down. Goodbye!");

	}
	
	
	
}
				

