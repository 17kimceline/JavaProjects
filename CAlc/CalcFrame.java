/*
 *Class CalcFrame constructs a calculate and listens to buttons and keys
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class CalcFrame extends JFrame implements KeyListener
{
	private String[] firstSet = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "*", "0", ".", "/", "="};
	private String[] secondSet = {"Home", "up","page up","NumPad +","left","clear", "right", "NumPad -", "end", "down", "Page Down", "NumPad *","Insert", "Period", "NumPad /",""};

	private JButton buttons[];
	private JButton clearEntry,  clear;

	private GridLayout grid1;

	private JTextArea text;

	private String firstTerm, function, secondTerm;
	private boolean decimal;
	private boolean secondEqn;
	private boolean shift;
	private boolean equals;

	public CalcFrame()
	{
		super("Celine Kim's Calculator");

		firstTerm="";
		secondTerm="";
		function="";

		//Decimal is true if the user may input the decimal
		decimal=true;

		//SecondEqn is true if the user wants to write another equation after pressing enter
		secondEqn=false;

		//Shift is true it the shift key had been pressed
		shift = false;

		//Equals is true if the equal button had been pressed
		equals = false;

		//Creating a Line border for the textBox
		Border border = BorderFactory.createLineBorder(new Color(105,184,182),20);

		//set up listener
		ButtonListener listener = new ButtonListener();

		//A textBox
		text = new JTextArea(5, 5);
		text.setBorder(border);
		text.setEditable(false);

		//A scroll
		JScrollPane scrollText = new JScrollPane(text);
		scrollText.setBounds(50, 5, 200, 135);

		//The panel with scroll
		JPanel gui = new JPanel(new BorderLayout(5,5));
		gui.add(scrollText, BorderLayout.NORTH);
		gui.setBorder(new EmptyBorder(4,4,4,4));
		gui.setBackground(new Color(105,184,182));


		//create buttons array
		buttons = new JButton[firstSet.length];

		//New grid
		grid1 = new GridLayout(4, 4);

		//Panel for buttons
		JPanel firstPanel = new JPanel();
		firstPanel.setLayout(grid1);

		//Line border for buttons
		border = BorderFactory.createLineBorder(new Color(85,134,171),5);

		//Initialize buttons
		for(int count = 0; count < firstSet.length; count++)
		{
			buttons[count] = new JButton(firstSet[count]);

			buttons[count].setPreferredSize(new Dimension(25,70));
			buttons[count].addActionListener(listener);
			buttons[count].setBackground(new Color	(136,199,169));
			buttons[count].setBorder(border);

			firstPanel.add(buttons[count]);
		}

		//Border, grid and panel for C and CE buttons
		border = BorderFactory.createBevelBorder(BevelBorder.RAISED);
		grid1 = new GridLayout(1, 2);

		JPanel secondPanel = new JPanel();
		secondPanel.setLayout(grid1);

		//C and CE buttons
		clear = new JButton("C");
		clear.addActionListener(listener);
		clear.setBorder(border);
		clear.setBackground(new Color(136,199,169));
		secondPanel.add(clear);

		clearEntry = new JButton("CE");
		clearEntry.addActionListener(listener);
		clearEntry.setBackground(new Color(136,199,169));
		clearEntry.setBorder(border);
		secondPanel.add(clearEntry);

		//Adding panels to the JFrame
		gui.add(firstPanel, BorderLayout.SOUTH);
		gui.add(secondPanel, BorderLayout.CENTER);
		add(gui);

		setResizable(false);
		setSize(500, 500);
		setVisible(true);

		setFocusable(true);

		addKeyListener(this);

	}

	//Method returns false if the String is a function, and true otherwise
	public boolean isFunction(String function)
	{
		for(int i = 0; i < firstSet.length-1; i++)
		{
			if(function.equalsIgnoreCase("/")||function.equalsIgnoreCase("+")||function.equalsIgnoreCase("*")||function.equalsIgnoreCase("-"))
				return false;

			if(function.equalsIgnoreCase("slash")||function.equalsIgnoreCase("minus")||function.equalsIgnoreCase("mulitply")||function.equalsIgnoreCase("addition"))
				return false;
		}
		return true;

	}

	//Converts the String name of a key into their symbols
	public String convertFunction(String function)
	{
		if(function.equalsIgnoreCase("SLASH"))
			function = "/";

		else if(function.equalsIgnoreCase("MULTIPLY"))
			function="*";

		else if(function.equalsIgnoreCase("DIVIDE"))
			function="/";

		else if(function.equalsIgnoreCase("PERIOD"))
			function =".";

		else if(function.equalsIgnoreCase("MINUS"))
			function="-";

		for(int i =0; i< secondSet.length; i++)
		{
			if(function.equalsIgnoreCase(secondSet[i]))
				function = firstSet[i];
		}

		return function;
	}


	//Method used to determin key pressed
	public void keyPressed(KeyEvent event)
	{
		//Creating a Calc object to call Calc methods
		Calc a = new Calc();

		//Converting functions into symbols
		String symbol =convertFunction(event.getKeyText(event.getKeyCode()));

		//If shift had been pressed, symbol converted to intended meaning. (Ex. + or *)
		if(shift)
		{
			if(symbol.equalsIgnoreCase("equals"))
				symbol = "+";
			else
				symbol = "*";
			//unpresses shift by setting it false
			shift = false;
		}

		//If key pressed it equals or enter, calculator will calculate
		if((symbol.equalsIgnoreCase("=")|| symbol.equalsIgnoreCase("enter"))||symbol.equalsIgnoreCase("equals"))
		{
			double answer = a.calculate(firstTerm, secondTerm, function);

			//Prints undefined if answer is not possible
			if((answer+"").equalsIgnoreCase("infinity"))
			{
				text.setText("UNDEFINED");
			}
			else
			{
				text.setText(""+answer);
			}

			firstTerm = ""+answer;

			decimal = true;
			secondEqn = true;
			equals = true;
		}

		//Set shift to true if shift had been pressed
		else if(symbol.equalsIgnoreCase("shift"))
		{
			shift = true;
		}

		//If Key pressed is a function
		else if(!isFunction(symbol))
		{
			//Nothing is done if there is a function before numbers
			if(firstTerm.length()<=0)
					function="";

			//Function is saved for later use
			else if(function.length()<=0||secondTerm.length()<=0||equals)
			{
				text.setText(firstTerm);
				function= symbol;

				secondTerm ="";

				secondEqn = false;
				equals = false;
				decimal = true;
			}

			//Will calculate is function is pressed twice
			else
			{
				double answer = a.calculate(firstTerm, secondTerm, function);

				//Prints undefined if answer is not possible
				if((answer+"").equalsIgnoreCase("infinity"))
				{
					text.setText("UNDEFINED");
				}
				else
				{
					text.setText(""+answer);
				}

				//Changing value of function
				function = symbol;

				//Resetting the calculating, but saving first term. Mimicking "clear entry" button
				firstTerm = ""+answer;
				secondTerm="";

				decimal = true;
				secondEqn = false;
			}
		}

		//If key pressed is number, saved for future use
		else
		{
			for(int i =0; i < firstSet.length-1; i++)
			{
				if(symbol.equals(firstSet[i]))
				{
					//If key is first part of equation
					if(firstTerm.length()<=0||secondEqn||equals)
					{
						//resetting terms
						secondEqn=false;
						equals = false;

						secondTerm ="";
						function ="";

						if(firstSet[i].equals("."))
						{
							if(decimal)
							{
								firstTerm=firstSet[i];

								text.setText(firstTerm);

								decimal=false;
							}
						}

						else
						{
							firstTerm=firstSet[i];

							text.setText(firstTerm);
						}

					}

					//If key pressed is part of first part of equation
					else if(function.length()<=0)
					{
						if(firstSet[i].equals("."))
						{
							if(decimal)
							{
								firstTerm+=firstSet[i];

								text.setText(firstTerm);

								decimal=false;
							}
						}
						else
						{
							firstTerm+=firstSet[i];

							text.setText(firstTerm);
						}
					}

					//If key pressed is second part of equation
					else
					{
						if(firstSet[i].equals("."))
						{
							if(decimal)
							{
								secondTerm+=firstSet[i];

								text.setText(secondTerm);

								decimal=false;
							}
						}
						else
						{
							secondTerm+=firstSet[i];

							text.setText(secondTerm);
						}
					}
				}
			}
		}
	}

	//Empty Methods
	public void keyReleased(KeyEvent event){}
	public void keyTyped(KeyEvent event){}

	//Private class to respond to buttons pressed on calculator
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//Object call calculate methods from Calc class
			Calc a = new Calc();

			//Calculate answer if button is equal sign
			if(event.getSource()==buttons[15])
			{
				double answer = a.calculate(firstTerm, secondTerm, function);

				//Prints undefined if answer is not possible
				if((answer+"").equalsIgnoreCase("infinity"))
				{
					text.setText("UNDEFINED");
				}
				else
				{
					text.setText(""+answer);
				}

				firstTerm = ""+answer;

				equals = true;
				secondEqn= true;
				decimal = true;
			}

			//To process numbers and function
			for(int i = 0; i < firstSet.length-1; i++)
			{

				if(event.getSource() == buttons[i])
				{
					//To process numbers

					if(isFunction(firstSet[i]))
					{
						//If number is first part of equation
						if(firstTerm.length()<=0||secondEqn||equals)
						{
							//Set it to false as no longer is second equation
							secondEqn=false;
							equals = false;

							secondTerm= "";
							function ="";

							if(firstSet[i].equals("."))
							{
								if(decimal)
								{
									firstTerm=firstSet[i];

									text.setText(firstTerm);

									decimal=false;
								}
							}
							else
							{
								firstTerm=firstSet[i];

								text.setText(firstTerm);
							}

						}

						//If number is part of first part of equation
						else if(function.length()<=0)
						{
							if(firstSet[i].equals("."))
							{
								if(decimal)
								{
									firstTerm+=firstSet[i];

									text.setText(firstTerm);

									decimal=false;
								}
							}
							else
							{
								firstTerm+=firstSet[i];

								text.setText(firstTerm);
							}

						}

						//If number is second part of equation
						else
						{
							if(firstSet[i].equals("."))
							{
								if(decimal)
								{
									secondTerm+=firstSet[i];

									text.setText(secondTerm);

									decimal=false;
								}
							}
							else
							{
								secondTerm+=firstSet[i];

								text.setText(secondTerm);
							}

						}
					}

					//If the value is a function
					else
					{
						//Nothing will happen is user presses function before numbers
						if(firstTerm.length()<=0)
							function="";

						//Saving a function for when user later calculates
						else if(function.length()<=0||equals||secondTerm.length()<=0)
						{
							function= firstSet[i];

							secondTerm ="";

							text.setText(firstTerm);

							decimal = true;
							equals = false;
							secondEqn = false;
						}

						//Caluclating if function is pressed twice
						else
						{
							double answer = a.calculate(firstTerm, secondTerm, function);

							//Prints undefined if answer is not possible
							if((answer+"").equalsIgnoreCase("infinity"))
							{
								text.setText("UNDEFINED");
							}
							else
							{
								text.setText(""+answer);
							}

							//Function value is changed
							function= firstSet[i];

							//Resetting terms
							firstTerm = ""+answer;
							secondTerm="";

							decimal=true;
							secondEqn = false;

						}
					}
				}
			}

			//Clears all memory
			if(event.getSource()==clear)
			{
				text.setText("");
				firstTerm ="";
				secondTerm ="";
				function="";

				decimal = true;
				secondEqn = false;

			}

			//Clears all, but saves last entry
			if(event.getSource()==clearEntry)
			{
				text.setText("0");
				secondTerm ="";
				function="";

				secondEqn= false;
				equals =false;
			}

			//Allows both listeners to be used
			requestFocusInWindow();
		}
	}

	//Main method
	public static void main(String args[])
	{

		CalcFrame application = new CalcFrame();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

