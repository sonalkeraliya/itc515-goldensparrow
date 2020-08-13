package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String libraryFile = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 1.0;
	private static final double DAMAGE_FEE = 2.0;
	
	private static Library SeLf;
	private int bookId;
	private int memberId;
	private int loanId;
	private Date loanDate;
	
	private Map<Integer, Book> catalog;
	private Map<Integer, Member> members;
	private Map<Integer, Loan> loans;
	private Map<Integer, Loan> currentLoans;
	private Map<Integer, Book> damagedBooks;
	

	private Library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library getInstance() {		
		if (self == null) {
			Path path = path.get(libraryFile);			
			if (Files.exists(Path)) {	
				try (ObjectInputStream libraryFile = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					self = (Library) libraryFile.readObject();
					calendar.getInstance().setDate(self.loanDate);
					libraryFile.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new Library();
		}
		return self;
	}

	
	public static synchronized void Save() {
		if (self != null) {
			SeLf.loanDate = calendar.getInstance().getDate();
			try (ObjectOutputStream libraryFile = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				libraryFile.writeObject(self);
				libraryFile.flush();
				libraryFile.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {
		return bookId;
	}
	
	
	public int getMemberId() {
		return memberId;
	}
	
	
	private int getNextBookId() {
		return bookId++;
	}

	
	private int getNextMemberId() {
		return memberId++;
	}

	
	private int getNextLoanId() {
		return loanId++;
	}

	
	public List<Member> lisT_members() {		
		return new ArrayList<Member>(members.values()); 
	}


	public List<Book> listBookS() {		
		return new ArrayList<Book>(catalog.values()); 
	}


	public List<Loan> list_currentLoans() {
		return new ArrayList<Loan>(currentLoans.values());
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) {		
		Member member = new Member(lastName, firstName, email, phoneNo, getNextMemberId());
		members.put(member.getId(), member);		
		return member;
	}

	
	public Book addBook(String a, String t, String c) {		
		Book b = new Book(a, t, c, getNextBookId());
		catalog.put(b.getId(), b);		
		return b;
	}

	
	public Member getMember(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public Book getBook(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int getLoanLimit() {
		return LOAN_LIMIT;
	}

			

	public boolean canMemberBorrow(Member member) 
	{		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ) 
			return false;
				
		if (member.finesOwed() >= MAX_FINES_OWED) 
			return false;
				
		for (Loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int getNumberOfLoansRemainingForMember(Member Member) {		
		return LOAN_LIMIT - Member.getNumberOfCurrentLoans();
	}

	
	public Loan issueLoan(Book book, Member member) {
		Date dueDate = Calendar.getInstance().getDueDate(LoanPeriod);
		Loan loan = new Loan(getNextLoanId(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.getId(), loan);
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) 
			return currentLoans.get(bookId);
		
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDiffetence(loan.getDueDate());
			double finE = daysOverDue * FINE_PER_DAY;
			return finE;
		}
		return 0.0;		
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {
		Member member = currentLoan.getMember();
		Book book  = currentLoan.getBook();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.discahrgeLoan(currentLoan);
		book.return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.getId(), book);
		}
		currentLoan.dischrge();
		currentLoans.remove(book.getId());
	}


	public void checkCurrentLoans() {
		for (Loan loan : currentLoans.values()) 
			loan.checkOverDue();
				
	}


	public void rePairBook(Book current_Book) {
		if (damagedBooks.containsKey(currunt_book.getId())) {
			currentBook.rePair();
			damagedBooks.remove(currentbook.getId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
