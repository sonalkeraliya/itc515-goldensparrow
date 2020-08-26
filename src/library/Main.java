/*
 * Author: Sonal
 * Mediator: Sanjay
  Reviewer : Rachana
 */
package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.BorrowBookControl; // bORROW_bOOK_cONTROL to BorrowBookControl
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.FixBookControl; // fIX_bOOK_cONTROL to FixBookControl
import library.payfine.PayFineUI; // PayFineUI to payFineUI
import library.payfine.payFineControl; // pAY_fINE_cONTROL to payFineControl
import library.returnBook.ReturnBookUI;
import library.returnBook.returnBookControl; // rETURN_bOOK_cONTROL to returnBookControl


public class Main {
	
	private static Scanner input;  // changed IN to input
	private static Library library; // changed LIB to library
	private static String menu; // MENU to menu
	private static Calendar calendar; //  CAL to calendar
	private static SimpleDateFormat sdf; //  to sdf
	
	
	private static String getMenu() { // Get_menu to getMenu
		StringBuilder objstr = new StringBuilder(); // sb to objstr
		
		objstr.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return objstr.toString();
	}


	public static void main(String[] args) {		
		try {			
			input = new Scanner(System.in); // changed IN to input
			library = Library.GeTiNsTaNcE(); // LIB to library
			calendar = Calendar.gEtInStAnCe(); // CAL to calendar
			sdf = new SimpleDateFormat("dd/MM/yyyy"); // SDF to sdf
	
			for (Member member : LIB.lIsT_MeMbErS()) { // m to member
				output(member);
			}
			output(" ");
			for (Book book : library.listBooks()) { // b to book,lIsT_BoOkS to listbooks
				output(book);
			}
						
			menu = getMenu(); // MENU menu,Get_menu to getMenu
			
			boolean exit = false; // e to exit
			
			while (!exit) {
				
				output("\n" + sdf.format(calendar.getDate())); // SDF to sdf,CAL to calendar,gEt_DaTe to getDate
				String char = input(menu); // MENU to menu,c to char
				
				switch (char.toUpperCase()) {
				
				case "M": 
					addMember(); // ADD_MEMBER to addMember
					break;
					
				case "LM": 
					listMembers(); // LIST_MEMBERS to listMembers
					break;
					
				case "B": 
					addBook(); // ADD_BOOK to addBook
					break;
					
				case "LB": 
					listBooks(); // LIST_BOOKS to listBooks
					break;
					
				case "FB": 
					fixBooks(); // FIX_BOOKS to fixBooks
					break;
					
				case "L": 
					borrowBook(); // BORROW_BOOK to borrowBook
					break;
					
				case "R": 
					returnBook(); // RETURN_BOOK to returnBook
					break;
					
				case "LL": 
					listCurrentLoans(); // LIST_CURRENT_LOANS to listCurrentLoans
					break;
					
				case "P": 
					payFines(); // PAY_FINES to payFines
					break;
					
				case "T": 
					incrementDate(); // INCREMENT_DATE to incrementDate
					break;
					
				case "Q": 
					exit = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save(); // SaVe to save
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void payFines() { //  PAY_FINES to payFines
		new PayFineUI(new PayFineControl()).run();	// pAY_fINE_cONTROL to PayFineControl	(class name)
	}


	private static void listCurrentLoans() { //  LIST_CURRENT_LOANS to listCurrentLoans
		output("");
		for (Loan loan : library.listCurrentLoans()) { // LIB to library,lISt_CuRrEnT_LoAnS to listCurrentLoans
			output(loan + "\n");
		}		
	}



	private static void listBooks() { // LIST_BOOKS to listBooks
		output("");
		for (Book book : library.listBooks()) { // lIsT_BoOkS to listBooks
			output(book + "\n");
		}		
	}



	private static void listMembers() { // LIST_MEMBERS to listMembers
		output("");
		for (Member member : library.listMembers()) {
			output(member + "\n");
		}		
	}



	private static void borrowBook() { // BORROW_BOOK to borrowBook
		new BorrowBookUI(new BorrowBookControl()).run();	// bORROW_bOOK_cONTROL to  BorrowBookControl	
	}


	private static void returnbook() { //  RETURN_BOOK to returnbook
		new ReturnBookUI(new ReturnBookControl()).run(); // rETURN_bOOK_cONTROL to ReturnBookControl
	}


	private static void fixBooks() { // FIX_BOOKS to fixBooks 
		new FixBookUI(new FixBookControl()).run();		// fIX_bOOK_cONTROL to FixBookControl,RuN to run
	}


	private static void incrementDate() { // INCREMENT_DATE to incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			calendar.incrementDate(days);
			library.checkCurrentLoans(); // cHeCk_CuRrEnT_LoAnS to checkCurrentLoans
			output(sdf.format(calendar.getDate())); // gEt_DaTe to getDate
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() { // ADD_BOOK to addBook
		
		String author = input("Enter author: "); // AuThOr to author
		String title  = input("Enter title: "); // TiTlE to title
		String callNumber = input("Enter call number: "); // CaLl_NuMbEr to callNumber
		Book book = library.addBook(author, title, callNumber); // BoOk to book,LIB to library, aDd_BoOk to addBook
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() { // ADD_MEMBER to addMember
		try {
			String lastName = input("Enter last name: "); // LaSt_NaMe to lastName
			String firstName  = input("Enter first name: "); //  FiRsT_NaMe to firstName
			String emailAddress = input("Enter email address: "); // EmAiL_AdDrEsS to emailAddress
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue(); // PhOnE_NuMbEr to phoneNumber
			Member member = library.addMember(lastName, firstName, emailAddress, phoneNumber); // MeMbEr to member
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine(); // IN to input
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
