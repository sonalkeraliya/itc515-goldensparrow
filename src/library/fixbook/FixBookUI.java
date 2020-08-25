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


	public void setState(UiState state) { //changed method name setState from SeT_StAtE
		this.state = state; //changed from StAtE to state
	}

	
	public void run() { //changed method name run from RuN
		output("Fix Book Use Case UI\n"); //changed output from OuTpUt
		
		while (true) {
			
			switch (state) { //changed from StAtE to state
			
			case READY:
				String bookEntryString = input("Scan Book (<enter> completes): "); //changed input from InPuT and changed string name from BoOk_EnTrY_StRiNg to bookEntryString
				if (bookEntryString.length() == 0) //changed string name from BoOk_EnTrY_StRiNg to bookEntryString
					control.SCannING_COMplete(); //changed control from CoNtRoL
				
				else {
					try {
						int BoOk_Id = Integer.valueOf(bookEntryString).intValue(); //changed string name from BoOk_EnTrY_StRiNg to bookEntryString
						control.BoOk_ScAnNeD(BoOk_Id); //changed control from CoNtRoL
					}
					catch (NumberFormatException e) {
						output("Invalid bookId"); //changed output from OuTpUt
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
				output("Fixing process complete"); //changed output from OuTpUt
				return;
			
			default:
				output("Unhandled state"); //changed output from OuTpUt
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //changed from StAtE to state		
			
			}		
		}
		
	}

	
	private String input(String prompt) { //changed input from InPuT
		System.out.print(prompt);
		return input.nextLine(); //changed input from InPuT
	}	
		
		
	private void output(Object object) { //changed method name output from OuTpUt
		System.out.println(object);
	}
	

	public void display(Object object) { //changed method name display from dIsPlAy
		output(object); //changed output from OuTpUt
	}
	
	
}
