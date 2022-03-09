package com.java.TDD.stringCalculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public int add(String numbers) throws Exception {
		int addResult = 0;
		ArrayList<String> delimiters = new ArrayList<String>();
		ArrayList<Integer> numbersToAdd = new ArrayList<Integer>();
		String errorMessage = "";

		// Extracting all delimiters present in the input string
		String regex = "(?<=\\[).+?(?=\\])";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(numbers);
		while (matcher.find()) {
			delimiters.add(matcher.group());
		}

		for (int i = 0; i < delimiters.size(); i++) {
			numbers = numbers.replace(delimiters.get(i), ",");
		}

		// Extracting all the numbers present in the input String
		regex = "-?([0-9]+)";
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(numbers);

		while (matcher.find()) {
			numbersToAdd.add(Integer.parseInt(matcher.group()));
		}

		if (numbersToAdd.size() == 1) {
			throw new Exception("Wrong number of Inputs");
		}

		for (int i = 0; i < numbersToAdd.size(); i++) {

			if (numbersToAdd.get(i) < 0) {
				errorMessage += numbersToAdd.get(i) + ", ";
			} else if (numbersToAdd.get(i) >= 1000) {
			} else {
				addResult += numbersToAdd.get(i);
			}

		}

		// Cleaning error message and throwing exception
		if (errorMessage.contains(",")) {
			errorMessage = errorMessage.substring(0, errorMessage.lastIndexOf(','));
			errorMessage = "negatives not allowed: " + errorMessage;
			throw new Exception(errorMessage);
		}

		return addResult;
	}

}
