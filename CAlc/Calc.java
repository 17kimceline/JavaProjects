/**
 *Class Calc is the logics of CalcFrame
 */

public class Calc
{
	//Empty Constructor
	public Calc()
	{
	}

	/*
	 *Method determine which function to calculate and return an answer
	 *@param first is first part of equation
	 *@param second is second part of equation
	 *@param function is the function of the equation
	 *@return the result of using the function on the two numbers
	 */
	public double calculate(String first, String second, String function)
	{
		if(second.equals(""))
			return Double.parseDouble(first);

		else if(function.equalsIgnoreCase("*"))
			return multiply(Double.parseDouble(first), Double.parseDouble(second));

		else if(function.equalsIgnoreCase("/"))
			return divide(Double.parseDouble(first), Double.parseDouble(second));

		else if(function.equalsIgnoreCase("+"))
			return addition(Double.parseDouble(first), Double.parseDouble(second));

		else if(function.equalsIgnoreCase("-"))
			return minus(Double.parseDouble(first), Double.parseDouble(second));

		//Returns 0.0 if all strings are empty
		return 0.0;
	}

	//Method multiplies the first and second parts (x, y) of the equation
	public double multiply(double x, double y)
	{
		return x*y;
	}

	//Method subtracts the first and second parts (x, y) of the equation
	public double minus(double x, double y)
	{
		System.out.println(x+(y));
		return (x-y);
	}

	//Method divides the first and second parts (x, y) of the equation
	public double divide(double x, double y)
	{
		return x/y;
	}

	//Method adds the first and second parts (x, y) of the equation
	public double addition(double x, double y)
	{
		return x+y;
	}

}