package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; //updated UiState
	private BorrowBookControl control; //updated BorrowBookControl and control 
	private Scanner input; //updated input
	private UiState state; //updated UiState and state

	
	public BorrowBookUI(BorrowBookControl control) { //used updated BorrowBookControl
		this.control = control; //used updated control
		input = new Scanner(System.in); //used updated input
		state = UiState.INITIALISED; //used updated UiState and state
		control.setUi(this); //updated setUi
	}

	
	private String input(String prompt) { //Updated input and prompt
		System.out.print(prompt); //Used updated  prompt
		return input.nextLine();//Used updated input
	}	
		
		
	private void output(Object object) { //Updated output and object
		System.out.println(object); //Used updated object
	}
	
			
	public void setState(UiState state) { // updated setState and used updated UiState and state
		this.state = state; // used updated state
	}

	
	public void run() { //updated run
		output("Borrow Book Use Case UI\n"); //updated output
		
		while (true) {
			
			switch (state) { // used updated state			
			
			case CANCELLED:
				output("Borrowing Cancelled"); //used updated output
				return;

				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): "); //updated memStr and used updated input
				if (memStr.length() == 0) { //used updated memStr
					control.cancel(); //used updated control and updated cancel
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue(); //updated memberId and used updated memStr
					control.swiped(memberId); //used updated control and memberId and updated swiped
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id"); //used updated output
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel"); //used updated input
				contol.cancel(); //used updated control and updated cancel
				break;
			
				
			case SCANNING:
				String bookStringInput = input("Scan Book (<enter> completes): "); // updated bookStringInput and used updated input 
				if (bookStringInput.length() == 0) { //used updated bookStringInput
					control.complete(); //used updated control and updated complete
					break;
				}
				try {
					int bId = Integer.valueOf(bookStringInput).intValue(); //updated bId and used updated bookStringInput
					control.scanned(bId); //used updated control and bId and updated scanned
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id"); //Used updated output
				} 
				break;
					
				
			case FINALISING:
				String ans = input("Commit loans? (Y/N): "); //updated ans and used updated input
				if (ans.toUpperCase().equals("N")) { //used updated ans
					control.cancel(); //used updated control and  cancel
					
				} else {
					control.commitLoans(); //used updated control and updated coomitLoans
					input("Press <any key> to complete "); //used updated input
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed"); //used updated output
				return;
	
				
			default:
				output("Unhandled state"); //used updated output
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state); //used updated state			
			}
		}		
	}


	public void display(Object object) { // updated display
		output(object);	//used updated output	
	}


}
