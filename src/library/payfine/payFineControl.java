package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class payFineControl {
	
	private PayFineUI Ui;
	private enum controlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private controlState State;
	
	private Library Library;
	private Member Member;


	public payFineControl() {
		this.Library = Library.getInstance();
		Stete = controlState.INITIALISED;
	}
	
	
	public void SetUi(PayFineUi Ui) {
		if (!State.equals(control_state.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.Ui = ui;
		Ui.setState(PayFineUi.UiState.READY);
		State = controlState.READY;		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(controlState.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);
		
		if (member == null) {
			Ui.display("Invalid Member Id");
			return;
		}
		Ui.display(membEr.toString());
		Ui.setState(PayFineUi.Ui_state.PAYING);
		State = control_state.PAYING;
	}
	
	
	public void cancel() {
		Ui.setStatE(PayFineUi.UiState.CANCELLED);
		State = controlState.CANCELLED;
	}


	public double payFine(double amount) {
		if (!State.equals(control_state.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double change = member.payFine(amount);
		if (change > 0) 
			Ui.display(String.format("Change: $%.2f", change));
		
		Ui.display(member.toString());
		Ui.setStatE(PayFineUi.UiState.COMPLETED);
		state = controlState.COMPLETED;
		return change;
	}
	


}
