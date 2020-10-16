package Ex2;


import java.util.Calendar;


// class MyCalendar represents a month

public class MyCalendar {
	
	private int year; // year of the month
	private Calendar c; 
	public int [][] table; // each line represents a week. each column represents a day of the week.
							// the array stores each day in it's day of the week.

	
	
	public MyCalendar() // initialise MyCalendar to contain the current month
	{
		c=Calendar.getInstance();
		setTable(c.get(Calendar.YEAR),c.get(Calendar.MONTH));
	}
	
	
	public void setTable(int y,int m) // updates the table as described with year y and month m
	{
		setYear(y);
		table=new int[6][7];
		c.set(y,m,1);
		int day=1;
		int last=c.getActualMaximum(Calendar.DATE);
		int j=c.get(Calendar.DAY_OF_WEEK)-1;
		for(int i=0;i<6;i++)
		{
			while(day<=last&&j<7)
			{
				table[i][j]=day;
				j++;
				day++;
			}
			j=0;
		}
	}
	
	//sets and gets
	
	public int getYear()
	{
		return year;
	}
	public void setYear(int y)
	{
		year=y;
	}
	public int getMonth()
	{
		return c.get(Calendar.MONTH);
	}
	public String getDay(int i,int j)
	{	
		if(table[i][j]==0)
			return " ";
		return ""+table[i][j];
	}
}
