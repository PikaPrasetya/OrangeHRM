package Utilities;

import org.apache.commons.text.RandomStringGenerator;

public class randomizer {
	
	public static String randomAlphabet()
	{
		RandomStringGenerator alphabetGenerator = new RandomStringGenerator.Builder()
				.withinRange('a', 'z').build();
		String result = alphabetGenerator.generate(8);
		return result;
	}
	
	public static String randomNumber()
	{
		RandomStringGenerator numberGenerator = new RandomStringGenerator.Builder()
				.withinRange('0', '9').build();
		String result = numberGenerator.generate(8);
		return result;
	}

}
