// Author: Sonal
// Mediator : Sanjay
// Reviewer: Rachana

package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	// private String LaSt_NaMe;
	private String lastName; // changed name from LaSt_NaMe to lastName
	// private String FiRsT_NaMe;
	private String firstName; // changed name from FiRsT_NaMe to firstName
	//private String EmAiL_AdDrEsS;
	private String emailAddress; // changed name from EmAiL_AdDrEsS to emailAddress
	//private int PhOnE_NuMbEr;
	private int phoneNumber; // changed name from PhOnE_NuMbEr to phoneNumber
	// private int MeMbEr_Id; 
	private int memberId; // changed name from MeMbEr_Id to memberId
	//private double FiNeS_OwInG;
	private double finesOwing; // changed name from FiNeS_OwInG to finesOwing
	private Map<Integer, Loan> currentLoans;   // changed name from cUrReNt_lOaNs to currentLoans
 
	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.memberId = memberId;
		
		this.currentLoans = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Member:  ").append(memberId).append("\n")
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
		  .append("  Email: ").append(emailAddress).append("\n")
		  .append("  Phone: ").append(phoneNumber)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))
		  .append("\n");
		
		for (Loan loan : currentLoans.values()) {
			stringBuilder.append(loan).append("\n");
		}		  
		return stringBuilder.toString();
	}

	
	public int getId() {  // change function name GeT_ID to getId
		return memberId;
	}

	
	public List<Loan> getLoans() { // change function name GeT_LoAnS to getLoans
		return new ArrayList<Loan>(currentLoans.values());
	}

	
	public int getNumberOfCurrentLoans() { //change name gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans
		return currentLoans.size();
	}

	
	public double finesOwed() { // change name FiNeS_OwEd to finesOwed
		return finesOwing;
	}

	
	public void takeOutLoan(Loan loan) { // change TaKe_OuT_LoAn to takeOutLoan and lOaN to loan
		if (!currentLoans.containsKey(loan.getId())) 
			currentLoans.put(loan.getId(), loan);
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String getLastName() { // change GeT_LaSt_NaMe to getLastName
		return lastName; //LaSt_NaMe to lastName
	}

	
	public String getFirstName() {  // change GeT_FiRsT_NaMe to getFirstName
		return firstName;
	}


	public void addFine(double fine) { // change AdD_FiNe to addFine
		finesOwing += fine;
	}
	
	public double payFine(double amount) { // change PaY_FiNe to payFine and AmOuNt to amount
		if (amount < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (amount > finesOwing) {
			change = amount - finesOwing;
			finesOwing = 0;
		}
		else 
			finesOwing -= amount;
		
		return change;
	}


	public void disChargeLoan(Loan loan) { // change dIsChArGeLoAn to disChargeLoan and LoAn to loan
		if (currentLoans.containsKey(loan.getId())) 
			currentLoans.remove(loan.getId());
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
