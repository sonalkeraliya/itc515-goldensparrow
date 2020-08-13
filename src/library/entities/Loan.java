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
        private Date date; //Changed data type from Date to int
//	private lOaN_sTaTe StAtE;
        private String state; //Changed data type from Member to String
        

	
	public Loan(int loanId, Book bOoK, Member mEmBeR, Date DuE_dAtE) {
		this.LoAn_Id = loanId;
		this.BoOk = bOoK;
		this.MeMbEr = mEmBeR;
		this.DaTe = DuE_dAtE;
		this.StAtE = lOaN_sTaTe.CURRENT;
	}

	
	public void cHeCk_OvEr_DuE() {
		if (StAtE == lOaN_sTaTe.CURRENT &&
			Calendar.gEtInStAnCe().gEt_DaTe().after(DaTe)) 
			this.StAtE = lOaN_sTaTe.OVER_DUE;			
		
	}

	
	public boolean Is_OvEr_DuE() {
		return StAtE == lOaN_sTaTe.OVER_DUE;
	}

	
	public Integer GeT_Id() {
		return LoAn_Id;
	}


	public Date GeT_DuE_DaTe() {
		return DaTe;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(LoAn_Id).append("\n")
		  .append("  Borrower ").append(MeMbEr.GeT_ID()).append(" : ")
		  .append(MeMbEr.GeT_LaSt_NaMe()).append(", ").append(MeMbEr.GeT_FiRsT_NaMe()).append("\n")
		  .append("  Book ").append(BoOk.gEtId()).append(" : " )
		  .append(BoOk.gEtTiTlE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(DaTe)).append("\n")
		  .append("  State: ").append(StAtE);		
		return sb.toString();
	}


	public Member GeT_MeMbEr() {
		return MeMbEr;
	}


	public Book GeT_BoOk() {
		return BoOk;
	}


	public void DiScHaRgE() {
		StAtE = lOaN_sTaTe.DISCHARGED;		
	}

}
