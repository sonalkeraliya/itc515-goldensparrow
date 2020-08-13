package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self; //Updated variable name self
	private static java.util.Calendar calendar; //Updated variable name calendar
	
	
	private Calendar() 
	{
		calendar = java.util.Calendar.getInstance(); //Used updated variable name
	}
	
	public static Calendar getInstance() //Updated method name getInstance
	{
		if (self == null) //Used updated variable name self
		{
			self = new Calendar(); //Used updated variable name self
		}
		return self; //Used updated variable name self
	}
	
	public void incrementDate(int days) 
	{
		calendar.add(java.util.Calendar.DATE, days); //Used updated variable name calendar	
	}
	
	public synchronized void setDate(Date date) //Updated method name setDate and variable name date
	{
		try 
		{
			calendar.setTime(date); //Used updated variable name calendar and date
	        	calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.MINUTE, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.SECOND, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.MILLISECOND, 0); //Used updated variable name calendar
		}
		
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}
	
	public synchronized Date getDate() //Updated method name getDate
	{
		try 
		{
	        	calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.MINUTE, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.SECOND, 0);  //Used updated variable name calendar
	        	calendar.set(java.util.Calendar.MILLISECOND, 0); //Used updated variable name calendar
			return calendar.getTime();
		}
		
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) //Updated method name getDueDate
	{
		Date now = getDate(); //Updated variable name now and used updated method name getDate
		calendar.add(java.util.Calendar.DATE, loanPeriod); //Used updated variable name calendar
		Date dueDate = calendar.getTime(); //Updated variable name dueDate and used updated variable name calendar
		calendar.setTime(now); //Used updated variable name calendar and variable name now
		return dUeDaTe; //Used updated variable name dueDate
	}
	
	public synchronized long getDaysDifference(Date targetDate) //Updated method name getDaysDifference
	{
		long diffMillis = getDate().getTime() - targetDate.getTime(); //Updated variable name diffMillis and used updated method name getDate
	    	long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS); //Updated variable name diffDays and used updated variable name diffMillis
	    	return Diff_Days; //Used updated variable name diffDays
	}
}
