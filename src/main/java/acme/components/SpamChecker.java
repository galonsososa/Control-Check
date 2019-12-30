
package acme.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import acme.entities.customparams.Customparams;

public class SpamChecker {

	public Boolean check(final Customparams configuration, final String text) {

		Boolean result;
		double spamCounter = 0;

		List<String> SpamWordsList = new ArrayList<String>(Arrays.asList(configuration.getSpamWords().split(",")));
		String inputText = text.replaceAll("[^a-zA-Z ]", "").toLowerCase(); //normalize input text
		for (String word : SpamWordsList) {
			word.replaceAll("[^a-zA-Z]", "").toLowerCase();//normalize spam word and compare it
			if (inputText.contains(word)) {
				spamCounter++;
			}
		}
		List<String> textWordsList = new ArrayList<String>(Arrays.asList(text.split(" ")));
		double textCounter = textWordsList.size();
		double spamRatio = spamCounter / textCounter * 100;

		if (spamRatio >= configuration.getSpamThreshold()) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

}
