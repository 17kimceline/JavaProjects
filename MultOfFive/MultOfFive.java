//Celine Kim P6
import java.util.Scanner;
public class MultOfFive
{
	private int x;
	private int howMany;
	//creating a class that computes the sum of multiples of five from five
	public MultOfFive()
	{
		int x=0;
		howMany = 1;
	}

	public int multiple()
	{
		Scanner num = new Scanner(System.in);
		System.out.println("Enter a positive  integer that is a multiple of five here:");
		int num1 = num.nextInt();
		while(howMany <= num1/5)
		{
			x = x + (5*howMany);
			howMany++;
		}
		return x;
	}

}

