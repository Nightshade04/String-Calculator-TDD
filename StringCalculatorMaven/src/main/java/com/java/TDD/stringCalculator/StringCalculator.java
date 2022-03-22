package com.java.TDD.stringCalculator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	private ArrayList<String> extractDelimiters(String numbers) {
		ArrayList<String> delimiters = new ArrayList<String>();
		String delimiterExtractingRegex = "(?<=\\[).+?(?=\\])";
		Pattern pattern = Pattern.compile(delimiterExtractingRegex);
		Matcher matcher = pattern.matcher(numbers);
		while (matcher.find()) {
			delimiters.add(matcher.group());
		}

		return delimiters;
	}

	private ArrayList<Integer> extractNumbersToAdd(ArrayList<String> delimiters, String numbers) {
		
		ArrayList<Integer> numbersToAdd = new ArrayList<Integer>();
		for (int i = 0; i < delimiters.size(); i++) {
			numbers = numbers.replace(delimiters.get(i), ",");
		}
		
		String numberExtractingRegex = "-?([0-9]+)";
		Pattern pattern = Pattern.compile(numberExtractingRegex);
		Matcher matcher = pattern.matcher(numbers);

		while (matcher.find()) {
			numbersToAdd.add(Integer.parseInt(matcher.group()));
		}
		
		return numbersToAdd;
		
	}
	
	private String cleanErrorMessage(String errorMessage) {
		if (errorMessage.contains(",")) {
			errorMessage = errorMessage.substring(0, errorMessage.lastIndexOf(','));
			errorMessage = "negatives not allowed: " + errorMessage;
		}
		
		return errorMessage;
	}

	public int add(String numbers) throws Exception {
		int addResult = 0;
		ArrayList<String> delimiters = extractDelimiters(numbers);
		ArrayList<Integer> numbersToAdd = extractNumbersToAdd(delimiters, numbers);
		String errorMessage = "";

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
		errorMessage = cleanErrorMessage(errorMessage);
		if (errorMessage.contains("negatives")) {
			throw new Exception(errorMessage);
		}

		return addResult;
	}

}
