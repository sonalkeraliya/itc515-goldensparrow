/*
 * Author: Sonal
 * Mediator: Sanjay
  Reviewer : Rachana

 */
package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class fixBookControl { // fIX_bOOK_cONTROL to fixBookControl
	
	private FixBookUI UI;
	private enum ControlState { INITIALISED, READY, FIXING }; // change CoNtRoL_StAtE to ControlState
	private ControlState state;  //change CoNtRoL_StAtE to ControlState
	
	private Library library; // change LiBrArY to library
	private Book currentBook;  // change CuRrEnT_BoOk to currentBook


	public FixBookControl() {  // change fIX_bOOK_cONTROL to FixBookControl
		this.library = Library.getInstance();  // change LiBrArY to library 
		state = ControlState.INITIALISED; // CoNtRoL_StAtE to ControlState
	}
	
	
	public void setUI(FixBookUI fixBookUI) { //change  SeT_Ui to setUI,ui to fixBookUI
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
		this.UI = fixBookUI;
		fixBookUI.setState(FixBookUI.UIState.READY); // change ui to fixBookUI,uI_sTaTe to UIState
		state = ControlState.READY;	 // change CoNtRoL_StAtE to ControlState
	}


	public void scanBook(int bookId) { // change BoOk_ScAnNeD to scanBook
		if (!state.equals(ControlState.READY))  // change StAtE to state
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
		currentBook = library.getBook(bookId);
		
		if (currentBook == null) { // CuRrEnT_BoOk to currentBook
			UI.display("Invalid bookId");
			return;
		}
		if (!currentBook.isDamaged()) {
			UI.display("Book has not been damaged");
			return;
		}
		UI.display(currentBook.toString());
		UI.setState(FixBookUI.uistate.FIXING);
		state = ControlState.FIXING;		
	}


	public void fixBook(boolean mustFix) { // change FiX_BoOk to fixBook , change mUsT_FiX to mustFix
		if (!state.equals(ControlState.FIXING))  // change StAtE to state , change CoNtRoL_StAtE to ControlState
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mustFix) //  change mUsT_FiX to mustFix
			library.repairBook(currentBook); // LiBrArY to library
		
		currentBook = null;
		UI.setState(FixBookUI.uiState.READY);
		state = ControlState.READY;		
	}

	
	public void scanningComplete() { // SCannING_COMplete to scanningComplete
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		UI.setState(FixBookUI.uiState.COMPLETED);		
	}

}
