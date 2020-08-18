// Author: Sonal
// Mediator : Rachana
// Reviewer: Sanjay

package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
//	public static enum lOaN_sTaTe { CURRENT, OVER_DUE, DISCHARGED };
        public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED }; //changed enum name from lOaN_sTaTe to LoanState
	
//	private int LoAn_Id;
        private int loanId; //changed int name loanId from LoAn_Id
//	private Book BoOk;
        private Book book; //Changed variable name from book to BoOk
//	private Member MeMbEr;
        private Member member; //Changed variable name from MeMbEr to member
//	private Date DaTe;
        private Date date; //Changed variable name from DaTe to date
//	private lOaN_sTaTe StAtE;
        private LoanState state; //Changed variable name from StAtE to state
        

	
//	public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
        public Loan(int loanId, String book, String member, Date dueDate) { // Edit data type
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.date = dueDate;
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverDue() { //changed method name from cHeCk_OvEr_DuE() to checkOverDue()
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().getDate().after(date)) {
			this.state = LoanState.OVER_DUE;
                }		
	}

	
	public boolean isOverDue() { //changed method name from Is_OvEr_DuE() to isOverDue()
		return state == LoanState.OVER_DUE;
	}

	
	public int getId() { //changed method name from GeT_Id() to getId()
		return loanId;
	}


	public int getDueDate() { //changed method name from GeT_DuE_DaTe() to getDueDate()
		return date;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Loan:  ").append(loanId).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")
		  .append("  Book ").append(book.gEtId()).append(" : " )
		  .append(book.getTitle()).append("\n") 
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")
		  .append("  State: ").append(state);		
		return stringBuilder.toString();
	}


	public Member getMember() { //Changed method name from GeT_MeMbEr() to getMember()
		return member;
	}


	public Book getBook() { //changed method name from GeT_BoOk() to getBook()
		return book; 
	}


	public void discharge() { //changed method name from DiScHaRgE() to discharge()
		state = LoanState.DISCHARGED;		
	}

}
