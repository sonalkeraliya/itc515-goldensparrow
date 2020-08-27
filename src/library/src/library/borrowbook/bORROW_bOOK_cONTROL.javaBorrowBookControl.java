package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl { //class name updated to BorrowBookControl
	
	private BorrowBookUI ui; // variable name updated to ui
	
	private Library library; // variable name updated to library
	private Member member; // variable name updated to member
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; // variable name updated to ControlState
	private ControlState state; // variable name updated to 
	
	private List<Book> pendingList; // variable name updated to pendingList
	private List<Loan> completedList; // variable name updated to completedList
	private Book book; // variable name updated to book
	
	
	public BorrowBookControl() { //constructor name updated to
		this.library = Library.getInstance(); // method name updated to getInstance
		state = ControlState.INITIALISED; // variable name updated to state
	}
	

	public void setUi(BorrowBookUI Ui) { // method name updated to setUi
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;
		ui.setState(BorrowBookUI.uiState.READY); // method name updated to setState
		state = ControlState.READY;		
	}

		
	public void swiped(int memberId) { // method name updated to swiped and variable name updated to memberId
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId); // method name updated to getMember
		if (member == null) {
			ui.display("Invalid memberId"); // method name updated to display
			return;
		}
		if (library.canMemberBorrow(member)) { // method name updated to canMemberBorrow
			pendingList = new ArrayList<>(); // used updated variables
			ui.setState(BorrowBookUI.uiState.SCANNING); // used updated variables
			state = ControlState.SCANNING; // used updated variables
		}
		else {
			ui.display("Member cannot borrow at this time"); // used updated variables
			ui.setState(BorrowBookUI.setState.RESTRICTED); // used updated variables
		}
	}
	
	
	public void scanned(int bookId) { // used updated method name and updated variables
		book = null; // used updated variables
		if (!state.equals(ControlState.SCANNING))  // used updated variables
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.getBook(bOoKiD); // method name updated to getBook and used updated variables
		if (book == null) { // used updated variables
			ui.display("Invalid bookId"); // used updated variables
			return;
		}
		if (!book.isAvailable()) { // method name updated to isAvailable
			ui.display("Book cannot be borrowed"); // used updated variables
			return;
		}
		pendingList.add(book); // used updated variables
		for (Book b : pendingList) // object name changed to b and used updated variables
			ui.display(b.toString()); // used updated variables
		
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) { // method name updated to getNumberOfLoansRemainingForMember and used updated variables
			ui.display("Loan limit reached"); // used updated variables
			complete(); // method name updated to complete
		}
	}
	
	
	public void complete() { // used updated method name
		if (pendingList.size() == 0) // used updated variables
			cancel(); // method name updated to cancel
		
		else {
			ui.display("\nFinal Borrowing List"); // used updated variables
			for (Book book : pendingList) // object name changed to book and used updated variables
				ui.display(book.toString()); // used updated variables
			
			completedList = new ArrayList<Loan>(); // used updated variables
			ui.setState(BorrowBookUI.uiState.FINALISING); // used updated variables
			state = ControlState.FINALISING; // used updated variables
		}
	}


	public void commitLoans() { // method name updated to commitLoans
		if (!state.equals(ControlState.FINALISING)) 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book b : pendingList) { // object name changed to b and used updated variables
			Loan loan = library.issueLoan(b, member); // object name changed to loan and used updated variables
			completedList.add(loan); // used updated variables			
		}
		ui.display("Completed Loan Slip"); // used updated variables	
		for (Loan loan : completedList) // object name changed to loan
			ui.display(loan.toString()); //used updated variables
		
		ui.setState(BorrowBookUI.uiState.COMPLETED); //used updated variables
		state = ControlState.COMPLETED; //used updated variables
	}

	
	public void cancel() { //used updated method name
		ui.setState(BorrowBookUI.uiState.CANCELLED); //used updated variables
		state = ControlState.CANCELLED; //used updated variables
	}
	
	
}
