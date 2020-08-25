// Author: Rinkal
// Mediator : Rachana
// Reviewer: Sanjay

package library.fixbook;
import java.util.Scanner;


public class FixBookUI {

//	public static enum uI_sTaTe { INITIALISED, READY, FIXING, COMPLETED };
        public static enum UiState { INITIALISED, READY, FIXING, COMPLETED }; //changed from uI_sTaTe to UiState

//	private fIX_bOOK_cONTROL CoNtRoL;
        private FixBookControl control; //changed class name from fIX_bOOK_cONTROL to FixBookControl and changed control from CoNtRoL
	private Scanner input; //changed input from InPuT
	private UiState state;//changed from uI_sTaTe to UiState and changed from StAtE to state

	
	public FixBookUI(FixBookControl control) { //changed class name from fIX_bOOK_cONTROL to FixBookControl
		this.control = control; //changed control from CoNtRoL
		input = new Scanner(System.in); //changed input from InPuT
		state = UiState.INITIALISED; //changed from uI_sTaTe to UiState and changed from StAtE to state
		control.setUi(this); //changed from SeT_Ui to setUi
	}


	public void SeT_StAtE(UiState state) {
		this.state = state; //changed from StAtE to state
	}

	
	public void RuN() {
		OuTpUt("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) { //changed from StAtE to state
			
			case READY:
				String BoOk_EnTrY_StRiNg = input("Scan Book (<enter> completes): "); //changed input from InPuT
				if (BoOk_EnTrY_StRiNg.length() == 0) 
					control.SCannING_COMplete(); //changed control from CoNtRoL
				
				else {
					try {
						int BoOk_Id = Integer.valueOf(BoOk_EnTrY_StRiNg).intValue();
						control.BoOk_ScAnNeD(BoOk_Id); //changed control from CoNtRoL
					}
					catch (NumberFormatException e) {
						OuTpUt("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String AnS = input("Fix Book? (Y/N) : "); //changed input from InPuT
				boolean FiX = false;
				if (AnS.toUpperCase().equals("Y")) 
					FiX = true;
				
				control.FiX_BoOk(FiX); //changed control from CoNtRoL
				break;
								
			case COMPLETED:
				OuTpUt("Fixing process complete");
				return;
			
			default:
				OuTpUt("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //changed from StAtE to state		
			
			}		
		}
		
	}

	
	private String input(String prompt) { //changed input from InPuT
		System.out.print(prompt);
		return input.nextLine(); //changed input from InPuT
	}	
		
		
	private void OuTpUt(Object object) {
		System.out.println(object);
	}
	

	public void dIsPlAy(Object object) {
		OuTpUt(object);
	}
	
	
}
