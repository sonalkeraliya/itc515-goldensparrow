// Author: Sonal
// Mediator : Sanjay
// Reviewer: Rachana

package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl { // change rETURN_bOOK_cONTROL to ReturnBookControl

	private ReturnBookUI objUI;  //Ui to objUI
	private enum ControlState {INITIALISED, READY, RESTRICTED};;  // cOnTrOl_sTaTe to 
	private ControlState state; // cOnTrOl_sTaTe to ControlState and sTaTe to state
	
	private Library library;  //lIbRaRy to library
	private Loan currentLoan;  //CurrENT_loan to currentLoan
	

	public ReturnBookControl() {   // rETURN_bOOK_cONTROL to ReturnBookControl
		this.library = Library.getInstance();
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI objUI) { // sEt_uI to setUI , ui to objUI
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.objUI = objUI;
		objUI.setState(ReturnBookUI.UIState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) { // bOoK_sCaNnEd to  bookScanned, bOoK_iD to bookId
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book currentBook = library.getBook(bookId);
		
		if (currentBook == null) {
			objUI.display("Invalid Book Id");
			return;
		}
		if (!currentBook.isOnLoan()) {
			objUI.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) 
			overDueFine = library.calculateOverDueFine(currentLoan);
		
		objUI.display("Inspecting");
		objUI.display(currentBook.toString());
		objUI.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) 
			objUI.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		
		objUI.setState(ReturnBookUI.UIState.INSPECTING);
		state = ControlState.INSPECTING;			
	}


	public void scanningComplete() { // sCaNnInG_cOmPlEtE to scanningComplete
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		objUI.setState(ReturnBookUI.UIState.COMPLETED);			
	}


	public void dischargeLoan(boolean isDamaged) { // dIsChArGe_lOaN to dischargeLoan , iS_dAmAgEd to isDamaged
		if (!state.equals(ControlState.INSPECTING)) 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		objUI.setState(ReturnBookUI.UIState.READY);
		state = ControlState.READY;					
	}


}
