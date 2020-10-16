package Ex2;

import javax.swing.JFrame;

public class Tester {
	
	public static void main(String [] args)
	{

		JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        CalendarGUI c= new CalendarGUI();
        frame.add(c);
        frame.setVisible(true);
        
		
	}

}
