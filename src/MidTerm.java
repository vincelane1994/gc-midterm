import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MidTerm {
	
	static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		boolean mainMenu = true;//As long as this is true it will keep looping the program
		Path membersPath = Paths.get("members.txt");
		if(Files.notExists(membersPath)) {
			try {
				Files.createFile(membersPath);
			}catch (IOException e) {
				System.out.println("Could not create file. " + e);
			}
		}
		Path clubsPath = Paths.get("clubs.txt");
		if(Files.notExists(clubsPath)) {
			try {
				Files.createFile(clubsPath);
			}catch (IOException e) {
				System.out.println("Could not create file. " + e);
			}
		}
		
		
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
				addMember(scnr);
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
	
	public static void addMember(Scanner scnr) {//This method is to add new members to the clubs
		boolean addNewMember = true;
		int userChoice = 0;
		do {
			int id;
			String name;
			System.out.println("What type of member are you adding?\n1. Single Club Member\n2. Multi-Club Member\n3. Main Menu");
			try {
				userChoice = scnr.nextInt();
			}catch(InputMismatchException ex) {
				System.out.println("That was not a valid number.(Please enter a number 1-3)");
			}
			switch (userChoice) {
				case 1://Single Club Member
					System.out.print("What is the new member's name?: ");
					name = scnr.nextLine();
					System.out.print("What is the new Members id number?: ");
					id = scnr.nextInt();
					scnr.nextLine();
					//SingleClubMember newSingleClubMember = new SingleClubMember(id, name);//This section is for adding new SingleClub members
					break;
				case 2://Multi-Club Member
					System.out.print("What is the new member's name?: ");
					name = scnr.nextLine();
					System.out.print("What is the new Members id number?: ");
					id = scnr.nextInt();
					scnr.nextLine();
					int points = 0;
					//MultiClubMember newMultiClubMember = new MultiClubMember(id, name, points);//This section is for adding new MultiClub members
					break;
				case 3:
					System.out.println("Exiting to main menu.");
					addNewMember = false;
					break;
				default:
					System.out.println("That was not a valid option");
					break;
					
			}	
		}while(addNewMember);
	}
	
}
				

