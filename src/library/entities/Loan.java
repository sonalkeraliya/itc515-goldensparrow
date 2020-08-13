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
        private String book; //Changed data type from Book to String
//	private Member MeMbEr;
        private String member; //Changed data type from Member to String
//	private Date DaTe;
        private int date; //Changed data type from Date to int
//	private lOaN_sTaTe StAtE;
        private String state; //Changed data type from Member to String
        

	
//	public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
        public Loan(int loanId, String book, String member, int dueDate) { // Edit data type
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.date = dueDate;
		this.state = lOaN_sTaTe.CURRENT;
	}

	
	public void checkOverDue() { //changed method name from cHeCk_OvEr_DuE() to checkOverDue()
		if (state == lOaN_sTaTe.CURRENT &&
			Calendar.getInstance().getDate().after(date)) {
			this.state = lOaN_sTaTe.OVER_DUE;
                }		
	}

	
	public boolean isOverDue() { //changed method name from Is_OvEr_DuE() to isOverDue()
		return state == lOaN_sTaTe.OVER_DUE;
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


	public String getMember() { //Changed method name from GeT_MeMbEr() to getMember()
		return member;
	}


	public String getBook() { //changed method name from GeT_BoOk() to getBook()
		return book; 
	}


	public void discharge() { //changed method name from DiScHaRgE() to discharge()
		state = lOaN_sTaTe.DISCHARGED;		
	}

}
