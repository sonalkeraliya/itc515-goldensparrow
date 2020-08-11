// Author: Rinkal
// Mediator : Rachana
// Reviewer: Sanajay

package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
//	private String tItLe;
        private String title; //Changed title from tItLe
//	private String AuThOr;
        private String author; //Changed author from AuThOr
//	private String CALLNO;
        private String callNo; //Changed callNo from CALLNO
//        private int iD;
	private int id; //Changed id from iD
	
//	private enum sTaTe { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
        private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED }; // enum name changed from sTaTe to State
	private State state;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.author = author;
		this.title = title;
		this.callNo = callNo;
		this.id = id;
		this.state = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")
		  .append("  Title:  ").append(title).append("\n")
		  .append("  Author: ").append(author).append("\n")
		  .append("  CallNo: ").append(callNo).append("\n")
		  .append("  State:  ").append(state);
		
		return sb.toString();
	}

	public Integer getId() { //Changed method name from gEtId() to getId()
		return id;
	}

	public String getTitle() { //Changed method name from gEtTiTlE() to getTitle()
		return title;
	}


	
	public boolean isAvailable() { //Changed method name from iS_AvAiLaBlE() to isAvailable()
		return state == State.AVAILABLE;
	}

	
	public boolean isOnLoan() { //Changed method name from iS_On_LoAn() to isOnLoan()
		return state == State.ON_LOAN;
	}

	
	public boolean isDamaged() { //Changed method name from iS_DaMaGeD() to isDamaged()
		return state == State.DAMAGED;
	}

	
	public void borrow() { //Changed method name from BoRrOw() to borrow()
		if (state.equals(State.AVAILABLE)) {
			state = State.ON_LOAN;
                }		
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void return(boolean damaged) {
		if (state.equals(State.ON_LOAN)) {
			if (damaged) {
				state = State.DAMAGED;
                        }
			else {
				state = State.AVAILABLE;
                        }
                }
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
                }		
	}

	
	public void repair() { //Changed method name from RePaIr() to repair()
		if (state.equals(State.DAMAGED)) {
			state = State.AVAILABLE;
                }
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
                }
	}


}
