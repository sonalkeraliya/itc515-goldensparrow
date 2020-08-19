// Author: Rinkal
// Mediator : Rachana
// Reviewer: Sanjay

package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; //changed UiState from uI_sTaTe

	private rETURN_bOOK_cONTROL control; //Changed control from CoNtRoL
	private Scanner input; //Changed input from iNpUt
	private UiState state; //Changed state from StATe

	
	public ReturnBookUI(rETURN_bOOK_cONTROL cOnTrOL) {
		this.control = control; //Change control from CoNtRoL
		input = new Scanner(System.in); //Changed input from iNpUt
		state = UiState.INITIALISED; //changed UiState from uI_sTaTe and Changed state from StATe
		cOnTrOL.sEt_uI(this);
	}


	public void RuN() {		
		oUtPuT("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (StATe) {
			
			case INITIALISED:
				break;
				
			case READY:
				String BoOk_InPuT_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				if (BoOk_InPuT_StRiNg.length() == 0) 
					CoNtRoL.sCaNnInG_cOmPlEtE();
				
				else {
					try {
						int Book_Id = Integer.valueOf(BoOk_InPuT_StRiNg).intValue();
						CoNtRoL.bOoK_sCaNnEd(Book_Id);
					}
					catch (NumberFormatException e) {
						oUtPuT("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String AnS = iNpUt("Is book damaged? (Y/N): ");
				boolean Is_DAmAgEd = false;
				if (AnS.toUpperCase().equals("Y")) 					
					Is_DAmAgEd = true;
				
				CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd);
			
			case COMPLETED:
				oUtPuT("Return processing complete");
				return;
			
			default:
				oUtPuT("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String iNpUt(String PrOmPt) {
		System.out.print(PrOmPt);
		return iNpUt.nextLine();
	}	
		
		
	private void oUtPuT(Object ObJeCt) {
		System.out.println(ObJeCt);
	}
	
			
	public void DiSpLaY(Object object) {
		oUtPuT(object);
	}
	
	public void sEt_sTaTe(UiState state) { //changed UiState from uI_sTaTe
		this.StATe = state;
	}

	
}
