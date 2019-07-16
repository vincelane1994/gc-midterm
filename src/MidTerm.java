import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MidTerm {
	
	static Scanner scnr = new Scanner(System.in);
	public static void main(String[] args) {
		boolean validChoice;
		int menuChoice = 0;
		ensureAllFilesExist();//Put all of the Lines of code dealing with ensuring the .txt files were there into a single method.
		int som = 0;
		boolean mainMenu = true;//As long as this is true it will keep looping the program
		do {
			System.out.println("===Main Menu===\n1. Add Member\n2. Remove Member\n3. Display Member Information\n4. Check In\n5. Generate Bill\n6. Exit");//
			do {
				System.out.print("What would you like to do?: ");
				try {
					menuChoice = scnr.nextInt();//Declares the choice for the switch
					scnr.nextLine();
					validChoice = false;
				}catch(InputMismatchException ex) {
					System.out.println("That was not a valid option.\n");
					scnr.nextLine();
					validChoice = true;
				}
			}while(validChoice);
			
			switch(menuChoice) {
			case 1://Add Member
				addMember(scnr);
				break;
			case 2://Remove Member
				removeMember(scnr);
				break;
			case 3://Display Member
				printSingleOrMulti(scnr);
				break;
			case 4://Check In
				checkInMaster(scnr);
				break;
			case 5://Generate Bill
				int userID = 0;
				do {
					System.out.print("What is the members id number?: ");
					try {
						userID = scnr.nextInt();
						scnr.nextLine();
						validChoice = false;
					}catch(InputMismatchException ex) {
						System.out.println("That was not valid");
						validChoice = true;
						scnr.nextLine();
					}
				}while(validChoice);
				scnr.nextLine();
				generateBill(userID);
				
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
		int clubChoice =0;
		String whatClub = null;
		int id;
		String name;
		do {
			
			System.out.println("\nWhat type of member are you adding?\n1. Single Club Member\n2. Multi-Club Member\n3. Main Menu");
			try {
				userChoice = scnr.nextInt();
				scnr.nextLine();
			}catch(InputMismatchException ex) {
				System.out.println("That was not a valid number.(Please enter a number 1-3)");
				scnr.nextLine();
				addNewMember = true;
				continue;
			}
			switch (userChoice) {
				case 1://Add Single Club Member
					addSingleClubMember(scnr);
					break;
				case 2://Adds Multi-Club Member
					addMultiClubMember(scnr);
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
	public static void addSingleClubMember(Scanner scnr) {
		boolean addNewMember = true;
		int userChoice = 0;
		int clubChoice =0;
		boolean validChoice = true;
		String whatClub = null;
		int id;
		String name;
		
		System.out.print("What is the new member's name first and last name (ex. Fred Flinstone): ");
		name = scnr.nextLine();
		id = addID(scnr);
		do {
			System.out.println("\nWhat club do you want to add " + name + " to?\n1. Detroit\n2. Ann Arbor\n3. Plymouth\n4. Taylor");
			try {
				clubChoice = scnr.nextInt();
				scnr.nextLine();
				validChoice = false;
			}catch(InputMismatchException ex) {
				System.out.println("That was not valid");
				validChoice = true;
				scnr.nextLine();
			}
		}while(validChoice);
		switch(clubChoice) {
		case 1://Detroit
			whatClub = "Detroit";
			break;
		case 2://Ann Arobr
			whatClub = "Ann Arbor";
			break;
		case 3://Plymouth
			whatClub = "Plymouth";
			break;
		case 4://Taylor
			whatClub = "Taylor";
			break;
		}
		SingleClubMember newSingleClubMember = new SingleClubMember(id, name, whatClub);//This section is for adding new SingleClub members
		System.out.println("Successfully added : " + newSingleClubMember);
		try {
			SingleClubMemberFileUtil.appendToFile(newSingleClubMember);
		} catch (IOException e) {
			System.out.println("Could not edit file.");
		}
	}
	public static void addMultiClubMember(Scanner scnr) {
		int id;
		String name;
		System.out.print("What is the new member's name first and last name (ex. Fred Flinstone): ");
		name = scnr.nextLine();
		id = addID(scnr);
		int points = 0;
		MultiClubMembers newMultiClubMember = new MultiClubMembers(id, name, points);//This section is for adding new MultiClub members
		try {
			MultiClubMemberFileUtil.appendToFile(newMultiClubMember);
		} catch (IOException e) {
			System.out.println("Could not edit file.");
		}
		System.out.println("Successfully added: " + newMultiClubMember);
	}
	public static void printSingleClubMember() {
		List<SingleClubMember> scm = SingleClubMemberFileUtil.readFile();
		int i = 0;
		for (SingleClubMember smc : scm) {
			i++;
			System.out.printf("%-4s%-28s%-10s%-12s\n", i + ". ", "Name: " + smc.getName(), "ID: " + smc.getId(), smc.getClub() );
		}
	}
	public static void printMultiClubMember() {
		List<MultiClubMembers> mcm = MultiClubMemberFileUtil.readFile();
		int i = 0;
		for (MultiClubMembers multiCM : mcm) {
			i++;
			System.out.printf("%-4s%-28s%-10s%-12s\n", i+ ". ", "Name: " + multiCM.getName(), "ID: " + multiCM.getId(), "Points: " + multiCM.getMembershipPoints() );
		}
	}
	public static void printSingleOrMulti(Scanner scnr) {
		boolean goAhead = true;
		int selection = 0;
		boolean validChoice = true;
		do {
			do {
				System.out.print("What group do you want to see?:\n1. Single Club Memebers\n2. Multi Club members");
				try {
					selection = scnr.nextInt();
					validChoice = false;
					scnr.nextLine();
				}catch(InputMismatchException ex) {
					System.out.println("That was not a valid option.");
					scnr.nextLine();
					validChoice = true;
				}
			}while(validChoice);
			switch (selection) {
			case 1:
				printSingleClubMember();
				goAhead = false;
				break;
			case 2:
				printMultiClubMember();
				goAhead = false;
				break;
			default:
				System.out.println("That was not a valid choice! Try again.");
				goAhead = true;
				break;
			}
		}while(goAhead);
	}
	public static void removeSingleClubMember(int removeID) {
		int i = 0;
		List<SingleClubMember> singleMC = SingleClubMemberFileUtil.readFile();
		String goneMember = null;
		for (SingleClubMember smc : singleMC) {
			if(smc.getId() == removeID) {
				goneMember = smc.getName();
				try {
					singleMC.remove(i);
				}catch(IndexOutOfBoundsException ex) {}
				try {
					SingleClubMemberFileUtil.rewriteFile(singleMC);
				} catch (IOException e) {
					System.out.println("Unable to write to file!");
				}
				System.out.println("Successfully removed: " + goneMember);
			}
			i++;
		}
	}
	public static void removeMultiClubMember(int removeID) {
		int i = 0;
		List<MultiClubMembers> multiMC = MultiClubMemberFileUtil.readFile();
		String goneMember = null;
		for (MultiClubMembers mmc : multiMC) {
			if(mmc.getId() == removeID) {
				goneMember = mmc.getName();
				try {
					multiMC.remove(goneMember);
				}catch(IndexOutOfBoundsException ex) {}
				try {
					MultiClubMemberFileUtil.rewriteFile(multiMC);
				} catch (IOException e) {
					System.out.println("Unable to write to file!");
				}
				System.out.println("Successfully removed: " + goneMember);
			}
			i++;
		}
	}
	public static void ensureAllFilesExist() {
		Path scmPath = Paths.get("singleClubMembers.txt");
		if(Files.notExists(scmPath)) {
			try {
				Files.createFile(scmPath);
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
		Path mcmPath = Paths.get("multiClubMembers.txt");
		if(Files.notExists(mcmPath)) {
			try {
				Files.createFile(mcmPath);
			}catch (IOException e) {
				System.out.println("Could not create file. " + e);
			}
		}
	}
	public static void removeMember(Scanner scnr) {
		boolean validChoice = true;
		int removeID = 0;
		do {
		System.out.println("What is the ID of the member you would like to remove.");
		try {
			removeID = scnr.nextInt();
			scnr.nextLine();
		}catch(InputMismatchException ex) {
			System.out.println("That was not a valid ID");
			scnr.nextLine();
		}
		}while(validChoice);
		removeSingleClubMember(removeID);//If the removeID variable matches any of the ID's in the SingleClubMember list it will remove that member. Otherwise it does nothing.
		removeMultiClubMember(removeID);//If the removeID variable matches any of the ID's in the MultiClubMember list it will remove that member. Otherwise it does nothing.
	}
	public static int addID(Scanner scnr) {
		int id;
		boolean goodID = true;
		do {
			goodID = true;
			//System.out.print("What is the new Members id number?: ");
			id = getID();
			//scnr.nextLine();
			List<SingleClubMember> singleMC = SingleClubMemberFileUtil.readFile();//Populates a List with the Single Club Members
			for (SingleClubMember smc : singleMC) {//Cycles through the members
				if(smc.getId() == id) {//Checks each member's id to see if it matches the id entered, if it does the body of the if statement is activated
				goodID = false;	
				System.out.println("That ID number has already been taken try another.");
				break;
				}
				goodID = true;//if no matches were found this stays as a good ID
			}
			if(goodID) {
				List<MultiClubMembers> multiMC = MultiClubMemberFileUtil.readFile();//populates a list with the Multi-Club Members
				for (MultiClubMembers mmc : multiMC) {//Cycles through the members
					if(mmc.getId() == id) {//Checks each member's id to see if it matches the id entered, if it does the body of the if statement is activated
					goodID = false;	
					System.out.println("That ID number has already been taken try another.");
					break;
					}
					goodID = true;//if no matches were found in either test then this ID is not in use
				}
			}
		}while(!goodID);
		return id;
	}
	public static int getID() {
		boolean goodID = true;
		int id = 0;
		do {
			id = 1 + (int)(Math.random() * 9999);
			if(Integer.toString(id).matches("[0-9]{4}")){
				
			}else {
				goodID = false;
			}
		}while (!goodID);
		return id;
		
	}
	public static boolean checkIn(int userId, String clubName) {
		boolean verifyMembership = false;
		if(checkInMulti(userId, clubName) || checkInSingle(userId, clubName)) {
			verifyMembership = true;
		}else {
			verifyMembership = false;
		}
		return verifyMembership;
	}
	public static boolean checkInSingle(int userId, String clubName) {
		boolean verifyMembership = false;
		List<SingleClubMember> singleMc = SingleClubMemberFileUtil.readFile();

		for (SingleClubMember smc : singleMc) {
			if (smc.getId() == userId) {
				if (smc.getClub().equalsIgnoreCase(clubName)) {
					verifyMembership = true;
					System.out.println("Welcome Valued Member ! Upgrade today to get 50 points towards every visit");
				} else {
					verifyMembership = false;
				}
				break;
			}
		}
		return verifyMembership;
	}
	public static boolean checkInMulti(int userId, String clubName) {
		boolean verifyMembership = false;
		List<MultiClubMembers> multiMC = MultiClubMemberFileUtil.readFile();
		for (MultiClubMembers mmc : multiMC) {
			if (mmc.getId() == userId) {
				verifyMembership = true;
				System.out.println("Congratulations, you gained 50 points!");
				int tempPoints = mmc.getMembershipPoints() + 50;
				mmc.setMembershipPoints(tempPoints);
				try {
					MultiClubMemberFileUtil.rewriteFile(multiMC);
				} catch (IOException e) {
					System.out.println("Could not write to file.");
				}
				break;
			} else {
				verifyMembership = false;
			}
		}
		return verifyMembership;
	}
	public static void checkInMaster(Scanner scnr) {
		boolean validChoice = true;
		int userId = 0;
		int location = 0;
		int i = 0 ;
		List<Club> club = ClubUtilFile.readFile();
		for (Club clubs : club) {
			i++;
			System.out.printf("%-4s%-15s%-30s\n" ,i + ". ", "Club " + clubs.getName(), "Address " + clubs.getAdress() );
		}
		do {
			System.out.println("What location would you like to check in?");
			try {
				location = scnr.nextInt();
				validChoice = false;
			}catch(InputMismatchException ex) {
				System.out.println("That was not a valid option.\n");
				scnr.nextLine();
				validChoice = true;
			}
		}while(validChoice);
		do {
			System.out.println("What is your ID?");
			try {
				userId = scnr.nextInt();
				validChoice = false;
			}catch(InputMismatchException ex) {
				System.out.println("That was not a valid option.");
				scnr.nextLine();
				validChoice = true;
			}
		}while(validChoice);
		scnr.nextLine();
		switch (location) {
		case 1:
			if (checkIn(userId, "Detroit")) {
				System.out.println("Welcome to our Detroit facility!");

			} else {
				System.out.println(
						"Sorry but you are not member of this club,ask an associate how to become a member.");
			}
			break;
		case 2:
			if (checkIn(userId, "Ann Arbor")) {
				System.out.println("Welcome to our Ann Arbor facility!");

			} else {
				System.out.println(
						"Sorry but you are not member of this club,ask an associate how to become a member.");
			}
			break;
		case 3:
			if (checkIn(userId, "Plymouth")) {
				System.out.println("Welcome to our Plymouth  facility!");

			} else {
				System.out.println(
						"Sorry but you are not member of this club,ask an associate how to become a member.");
			}
			break;
		case 4:
			if (checkIn(userId, "Taylor")) {
				System.out.println("Welcome to our Taylor  facility!");

			} else {
				System.out.println(
						"Sorry but you are not member of this club,ask an associate how to become a member.");
			}
			break;

		}
		
	}
	public static void generateBillSingle(int userID) {
		List<SingleClubMember> singleMc = SingleClubMemberFileUtil.readFile();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();
		double monthlyMembership = 30.00;
		double cleaningFee = 5.00;
		double maitnenceFee = 10.00;
		double subtotal = monthlyMembership + cleaningFee + maitnenceFee;
		double tax = (subtotal * 0.07);
		for (SingleClubMember smc : singleMc) {
			if (smc.getId() == userID) {
				System.out.println("\nBill for " + smc.getName() + ":");
				System.out.println("Monthly membership: " + defaultFormat.format(monthlyMembership));
				System.out.println("Cleaning fees: " + defaultFormat.format(cleaningFee));
				System.out.println("Mainence fees: " + defaultFormat.format(maitnenceFee));
				System.out.println("Subtotal: " + defaultFormat.format(subtotal));
				System.out.println("Taxes: " + defaultFormat.format(tax));
				System.out.println("Total: " + defaultFormat.format(tax + subtotal) + "\n");
			}
	}
}
	public static void generateBillMulti(int userID) {
		List<MultiClubMembers> multiMc = MultiClubMemberFileUtil.readFile();
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

		double monthlyMembership = 45.00;
		double cleaningFee = 5.00;
		double maitnenceFee = 10.00;
		double subtotal = monthlyMembership + cleaningFee + maitnenceFee;
		double tax = (subtotal * 0.07);
		for (MultiClubMembers mmc : multiMc) {
			if (mmc.getId() == userID) {
				System.out.println("\nBill for " + mmc.getName() + ":");
				System.out.println("Monthly membership: " + defaultFormat.format(monthlyMembership));
				System.out.println("Cleaning fees: " + defaultFormat.format(cleaningFee));
				System.out.println("Mainence fees: " + defaultFormat.format(maitnenceFee));
				System.out.println("Subtotal: " + defaultFormat.format(subtotal));
				System.out.println("Taxes: " + defaultFormat.format(tax));
				System.out.println("Total: " + defaultFormat.format(tax + subtotal));
				System.out.println("Loyalty Points: " + mmc.getMembershipPoints() + "\n");
			}
	}
	}
	public static int checkMember(int userID) {
		List<SingleClubMember> singleMC = SingleClubMemberFileUtil.readFile();
		int som = 0;
		for (SingleClubMember smc : singleMC) {
			if(smc.getId() == userID) {
				som = 1;
				break;
			}else {
				List<MultiClubMembers> multiMC = MultiClubMemberFileUtil.readFile();
				for (MultiClubMembers mmc : multiMC) {
					if(mmc.getId() == userID) {
						som = 2;
						break;
					}else {
						som = 3;
					}
				}
			}
		}
		return som;
				
	}
	public static void generateBill(int userID) {
		int som = checkMember(userID);
		if(som == 1) {
			generateBillSingle(userID);
		}else if(som == 2) {
			generateBillMulti(userID);
		}else {
			System.out.println("That user does not exist.");
		}
	}
}
				

