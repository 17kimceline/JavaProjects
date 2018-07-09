import java.util.Scanner;

public class PigLatin
{
	// Reads sentences and translates them into Pig Latin
	public static void main(String[] args)
	{
		String sentence, result, another = "y";

		Scanner input = new Scanner(System.in);
		PigLatinTranslator translator = new PigLatinTranslator();

		while(another.equalsIgnoreCase("y"))
		{
			System.out.println();
			System.out.print("Enter a sentence (no punctuation): ");
			sentence = input.nextLine();
			System.out.println();
			result = PigLatinTranslator.translate(sentence);
			System.out.print("That sentence in Pig Latin is: ");
			System.out.println(result);

			System.out.println();
			System.out.print("Translate another sentence? (y/n) ");
			another = input.nextLine();
		}
	}
}

