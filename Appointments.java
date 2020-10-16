package Ex2;

import java.util.HashMap;

// class Appointments represents a collection of appointments to add to calendar.


public class Appointments {

	
	private HashMap<String, String> appList;
	
	public Appointments() // initialise new hashMap to store the appointments 
	{
		appList= new HashMap<String, String>();
	}


	public void addApp(String date, String text) // adds new appointment by key of date and string of content
	{
		if(appList.get(date)!=null) // if there are already appointments in this date, add the new one in a new line
				appList.put(date, appList.get(date)+"<br>"+text);
		else appList.put(date,text);	
	}
	
	public String getApp(String date) //returns a string that contains all appointments in key date
	{
		return appList.get(date);
		
	}


}

