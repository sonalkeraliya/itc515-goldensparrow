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
	private enum ControlState { INITIALISED, READY, FIXING }; // CoNtRoL_StAtE to ControlState
	private ControlState state;  // CoNtRoL_StAtE to ControlState
	
	private Library library; // LiBrArY to library
	private Book currentBook;  // CuRrEnT_BoOk to currentBook


	public FixBookControl() {  // fIX_bOOK_cONTROL to FixBookControl
		this.LiBrArY = Library.GeTiNsTaNcE();
		StAtE = CoNtRoL_StAtE.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) { // SeT_Ui to setUI
		if (!StAtE.equals(CoNtRoL_StAtE.INITIALISED)) 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
		this.Ui = ui;
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		StAtE = CoNtRoL_StAtE.READY;		
	}


	public void scanBook(int BoOkId) { // BoOk_ScAnNeD to scanBook
		if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
		CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId);
		
		if (CuRrEnT_BoOk == null) {
			Ui.dIsPlAy("Invalid bookId");
			return;
		}
		if (!CuRrEnT_BoOk.iS_DaMaGeD()) {
			Ui.dIsPlAy("Book has not been damaged");
			return;
		}
		Ui.dIsPlAy(CuRrEnT_BoOk.toString());
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		StAtE = CoNtRoL_StAtE.FIXING;		
	}


	public void fixBook(boolean mUsT_FiX) { // FiX_BoOk to fixBook
		if (!StAtE.equals(CoNtRoL_StAtE.FIXING)) 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mUsT_FiX) 
			LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk);
		
		CuRrEnT_BoOk = null;
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		StAtE = CoNtRoL_StAtE.READY;		
	}

	
	public void scanningComplete() { // SCannING_COMplete to scanningComplete
		if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);		
	}

}