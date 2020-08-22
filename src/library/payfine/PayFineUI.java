package library.payfine;
import java.util.Scanner;
//Author:Sanjay

//Mediator:Sonal

//Reviewer:Rinkal

public class PayFineUI {


	public static enum UiState  { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; //updated UiState

	private payFineControl control; //updated payFineControl and control 
	private Scanner input;
	private UiState state; //updated UiState and state

	
	public PayFineUI(payFineControl control) { //used updated payFineControl
		this.control = control; //used updated control
		input = new Scanner(System.in);
		state  = UiState.INITIALISED; //used updated UiState and state
		control.setUi(this); //updated setUi
	}
	
	
	public void setState(UiState state) { // updated setState and used updated UiState
		this.state = state; // used updated state
	}


	public void run() { //updated run
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) { // used updated state	
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): "); //updated memStr
				if (memStr.length() == 0) { //used updated memStr
					control.cancel(); //used updated control and updated cancel
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue(); //updated memberId and used updated memStr
					control.cardSwiped(memberId); //used updated control and memberId and updated cardSwiped
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0; //updated amount
				String amtStr = input("Enter amount (<Enter> cancels) : "); //updated amtStr
				if (amtStr.length() == 0) { //used updated amtStr
					control.cancel(); //used updated control and updated cancel
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue(); //used updated amount and amtStr
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) { //used updated amount
					output("Amount must be positive");
					break;
				}
				control.payFine(amount); //used updated control and amount and updated payFine
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state); //used updated state			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) { // updated display
		output(object);
	}


}
