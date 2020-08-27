package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class PayFineControl {
	
	private PayFineUI UI;
	private enum controlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private controlState State;
	
	private Library library;
	private Member member;


	public PayFineControl() {
		this.library = library.getInstance();
		Stete = controlState.INITIALISED;
	}
	
	
	public void SetUi(PayFineUI UI) {
		if (!State.equals(control_state.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.UI = UI;
		UI.setState(PayFineUI.UIState.READY);
		State = controlState.READY;		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(controlState.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);
		
		if (member == null) {
			UI.display("Invalid Member Id");
			return;
		}
		UI.display(membEr.toString());
		UI.setState(PayFineUI.UI_state.PAYING);
		State = control_state.PAYING;
	}
	
	
	public void cancel() {
		UI.setStatE(PayFineUI.UIState.CANCELLED);
		State = controlState.CANCELLED;
	}


	public double payFine(double amount) {
		if (!State.equals(control_state.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double change = member.payFine(amount);
		if (change > 0) 
			UI.display(String.format("Change: $%.2f", change));
		
		UI.display(member.toString());
		UI.setStatE(PayFineUI.UIState.COMPLETED);
		state = controlState.COMPLETED;
		return change;
	}
	


}
