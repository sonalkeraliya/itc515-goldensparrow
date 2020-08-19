// Author: Rinkal
// Mediator : Rachana
// Reviewer: Sanjay

package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; //changed UiState from uI_sTaTe

	private ReturnBookControl control; //Changed control from CoNtRoL
	private Scanner input; //Changed input from iNpUt
	private UiState state; //Changed state from StATe

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control; //Change control from CoNtRoL
		input = new Scanner(System.in); //Changed input from iNpUt
		state = UiState.INITIALISED; //changed UiState from uI_sTaTe and Changed state from StATe
		control.setUi(this);
	}


	public void run() { //Changed RuN to run	
		output("Return Book Use Case UI\n"); //Changed output from oUtPuT
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): "); //changed bookInputString from BoOk_InPuT_StRiNg 
				if (bookInputString.length() == 0) { //changed bookInputString from BoOk_InPuT_StRiNg 
					control.scanningComplete(); //Change control from CoNtRoL
                                }
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue(); //Changed bookId from Book_Id and changed bookInputString from BoOk_InPuT_StRiNg 
						control.bookScanned(bookId); //Change control from CoNtRoL and Changed bookId from Book_Id
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");  //Changed output from oUtPuT
					}					
				}
				break;				
				
			case INSPECTING:
				String AnS = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false; //changed isDamaged from Is_DAmAgEd
				if (AnS.toUpperCase().equals("Y")) 					
					isDamaged = true; //changed isDamaged from Is_DAmAgEd
				
				control.dischargeLoan(isDamaged); //Change control from CoNtRoL and changed isDamaged from Is_DAmAgEd
			
			case COMPLETED:
				output("Return processing complete"); //Changed output from oUtPuT
				return;
			
			default:
				output("Unhandled state"); //Changed output from oUtPuT
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) { //Changed input from iNpUt
		System.out.print(prompt);
		return input.nextLine(); //Changed input from iNpUt
	}	
		
		
	private void output(Object object) { //Changed output from oUtPuT
		System.out.println(object);
	}
	
			
	public void display(Object object) { //Changed display from DiSpLaY
		output(object); //Changed output from oUtPuT
	}
	
	public void setState(UiState state) { //changed UiState from uI_sTaTe
		this.state = state;
	}

	
}
