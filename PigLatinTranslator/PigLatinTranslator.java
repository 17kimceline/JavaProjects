
// Represents a translation system from English to Pig Latin
// Demonstrates method decomposition - when something is complicated that
// break down into smaller methods

public class PigLatinTranslator
{
	// Translates a sentence of words into Pig Latin using String
	// methods toLowerCase and split
	// Note: split not in the AP subset!

	public static String translate(String sentence)
	{
		String result = "";

		sentence = sentence.toLowerCase();

		String[] holdPieces = sentence.split("\\s");
		for(int i = 0; i < holdPieces.length; i++)
		{
			result += translateWord(holdPieces[i]);
			result += " ";
		}

		return result;
	}

	/** The following methods are declared private.  They are not accessed
	 *	by client outside the class. They only to help the translate
	 *  method.  They cannot be called outside of this class.
	 */

	// Translates one word into Pig Latin.  If the word begins with a vowel,
	// the suffix "yay" is appened to the word.  Otherwise, the first letter
	// or two are moved to the end of the word, and ay is appended.
	private static String translateWord(String word)
	{
		String result = "";

		if(beginsWithVowel(word))
			result = word + "yay";
		else
			if(beginsWithBlend(word))
				result = word.substring(2) + word.substring(0, 2) + "ay";
			else
				result = word.substring(1) + word.substring(0,1) + "ay";

		return result;
	}

	// Determines if the specified word begins with a vowel
	private static boolean beginsWithVowel(String word)
	{
		String vowels = "aeiou";

		String letter = word.substring(0, 1);

		return (vowels.indexOf(letter) != -1);
	}

	// Determines if the specified word begins with a particular
	// two-character consonant blend
	// Uses the String method startsWith
	// Note: startsWith not in the AP subset!
	private static boolean beginsWithBlend(String word)
	{
		return(word.startsWith("bl") || word.startsWith("sc") ||
			   word.startsWith("br") || word.startsWith("sh") ||
			   word.startsWith("ch") || word.startsWith("sk") ||
			   word.startsWith("cl") || word.startsWith("sl") ||
			   word.startsWith("cr") || word.startsWith("sn") ||
			   word.startsWith("dr") || word.startsWith("sm") ||
			   word.startsWith("dw") || word.startsWith("sp") ||
			   word.startsWith("fl") || word.startsWith("sq") ||
			   word.startsWith("fr") || word.startsWith("st") ||
			   word.startsWith("gl") || word.startsWith("sw") ||
			   word.startsWith("gr") || word.startsWith("th") ||
			   word.startsWith("kl") || word.startsWith("tr") ||
			   word.startsWith("ph") || word.startsWith("tw") ||
			   word.startsWith("pl") || word.startsWith("wh") ||
			   word.startsWith("pr") || word.startsWith("wr"));
	}

}
