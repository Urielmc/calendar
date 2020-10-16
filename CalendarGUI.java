package Ex2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//class CalendarGUI implements MyCalndar with GUI

public class CalendarGUI extends JPanel {
	
	private JTextField typeYear; // text field to insert new year to display
	private JComboBox<String> chooseMonth; // combo box to choose new month to display
	private JLabel [][] daysBoxes; // contains appointments of each date
	private JLabel header1; // shows current month displayed
	private JLabel header2;  // shows current year displayed
	private JLabel [][] daysNum; // shows number of day in the month displayed
	private JButton cmdEnter; // used to set the display to a new month
	private MyCalendar cal; // contains the data of the current month displayed
	private Appointments app; //contains all appointments inserted to calendar
	
	// list of months for the combo box
	private String months[]= {"January","February","March","April","May","June",
									"July","August","September","October","November","December"};
	int curMonth; // stores current month number
	
	
	public CalendarGUI()
	{
		//initialise all components graphics and listeners
		cal=new MyCalendar();
		app=new Appointments();
		chooseMonth=new JComboBox <String> (months);
		curMonth=cal.getMonth();
		chooseMonth.setSelectedItem(months[curMonth]);
		daysBoxes=new JLabel[6][7];
		daysNum=new JLabel[6][7];	
		cmdEnter=new JButton("Enter");
		typeYear=new JTextField(""+cal.getYear());
		header1=new JLabel();
		header2=new JLabel();
		
		header1.setFont(new Font("Serif",Font.BOLD, 20));
		header2.setFont(new Font("Serif",Font.BOLD, 20));
		header1.setHorizontalAlignment(JLabel.RIGHT);
		
		
		MListener m=new MListener();
		
		for(int i=0;i<6;i++)
			for(int j=0;j<7;j++)
			{
				daysBoxes[i][j]=new JLabel();
				daysNum[i][j]=new JLabel();
				daysBoxes[i][j].setOpaque(true);
				daysBoxes[i][j].setBackground(Color.white);
				daysBoxes[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				daysNum[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
				daysNum[i][j].addMouseListener(m);
				daysBoxes[i][j].addMouseListener(m);
			}
		
		setMonthDisplay(); // update the labels to show the month requested

		
		//add components to panel
		
		setLayout(new GridLayout(15,7));
		
		add(header1);
		add(header2);
		for(int i=0;i<5;i++) //add empty lables to end the line in the grid
			add(new JLabel());
		//add days of the week on top of the table
		add(new JLabel("Sunday",SwingConstants.CENTER));
		add(new JLabel("Monday",SwingConstants.CENTER));
		add(new JLabel("Tuesday",SwingConstants.CENTER));
		add(new JLabel("Wednsday",SwingConstants.CENTER));
		add(new JLabel("Thursday",SwingConstants.CENTER));
		add(new JLabel("Friday",SwingConstants.CENTER));
		add(new JLabel("Saturday",SwingConstants.CENTER));
		
		
		
		int k=0,v=0;
		for(int i=0;i<13;i++) //if i is even add a line of date numbers, if odd add a line of empty lables (used to show appointments)
		{
			if(i%2==0&&k<6)
			{
				for(int j=0;j<7;j++)
					add(daysNum[k][j]);
				k++;
			}
			if(i%2==1&&v<6)
			{
				for(int j=0;j<7;j++)
					add(daysBoxes[v][j]);
				v++;
			}	
		}
		
		
		ControlsListener l = new ControlsListener();
		chooseMonth.addActionListener(l);
		cmdEnter.addActionListener(l);
		
		add(typeYear);
		add(chooseMonth);
		add(cmdEnter);
				
	}
	
	
	
	private void setMonthDisplay() //update labels according to values in MyCalendar table using getDay
	{
		String temp="";
		for(int i=0;i<6;i++)
			for(int j=0;j<7;j++)
			{
				temp=cal.getDay(i, j);
				
				if(temp==null)
					daysNum[i][j].setText(temp);
				else
					daysNum[i][j].setText(temp);
				
				temp=daysNum[i][j].getText()+"."+(curMonth+1)+"."+cal.getYear();
				if(app.getApp(temp)==null) // show appointments in the current day if there is
					daysBoxes[i][j].setText("");
				else
					daysBoxes[i][j].setText("<html>"+app.getApp(temp)+"</html>");
				
				
			}
		//update header
		header1.setText(months[curMonth]+" ");
		header2.setText(" "+cal.getYear());
		
	}
	
	
	
	private class MListener extends MouseAdapter
    {
		public void mouseClicked(MouseEvent e) //open dialog to show selected day appointments or add a new one
		{
			String s;
			String currApp=" ";
			String date="";
			for(int i=0;i<6;i++)
				for(int j=0;j<7;j++)
					if((e.getSource()==daysNum[i][j]||e.getSource()==daysBoxes[i][j])&&daysNum[i][j].getText()!=" ")
					{							
						date=daysNum[i][j].getText()+"."+(curMonth+1)+"."+cal.getYear();
						if(app.getApp(date)!=null)
								currApp=app.getApp(date);
						s=JOptionPane.showInputDialog("<html>"+date+" "
											+ "Appointments:<br>"+currApp+"<br><br> Insert new appointment</html>");
						if(s!=null)
						{
							app.addApp(date, s);
							daysBoxes[i][j].setText("<html>"+app.getApp(date)+"</html>");
						}
					}	
				
			
		}
    }
	
	
	private class ControlsListener implements ActionListener
    {
	
		public void actionPerformed(ActionEvent e) // display the month according to the values the user chose
		{
			if(e.getSource()==chooseMonth)
				curMonth=chooseMonth.getSelectedIndex();
			if(e.getSource()==cmdEnter)
			{
				
				if(typeYear.getText().matches("[0-9]+"))
				{	
					cal.setTable(Integer.valueOf(typeYear.getText()), curMonth);
					setMonthDisplay();
				}	
				else
					JOptionPane.showMessageDialog(null, "Year field is Invalid");
				
			}
		}
    }
			
	

}
