package stringCalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringCalculator {

	public int add(String numbers) throws Exception {
		int addResult = 0;
		ArrayList<String> delimiters = new ArrayList<String>();
		String errorMessage = "";
		String[] arrayOfNumbers = numbers.split("\n");

		for (int i = 0; i < arrayOfNumbers.length; i++) {
			if (arrayOfNumbers[i].matches("-*[0-9]+.*")) {
				break;
			} else {
				delimiters.add(arrayOfNumbers[i]);
			}
		}
		Pattern p = Pattern.compile("-?\\d+");
		List<String> numList = Arrays.asList(arrayOfNumbers).stream().filter(x -> !delimiters.contains(x))
				.filter(x -> x.matches("[-]?[0-9]+.*")).collect(Collectors.toList());

		if (numList.size() == 1) {
			String s = numList.get(0);
			Iterator<String> iter = delimiters.iterator();
			while(iter.hasNext()) {
				String delim = iter.next();
				delim = delim.substring(1, delim.length() - 1);
				if(s.contains(delim))
					s = s.replace(delim, ",");
			}
			Matcher m = p.matcher(s);
			int count = 0;
			while (m.find()) {
				count++;
				int temp = Integer.parseInt(m.group());
				if (temp < 0)
					errorMessage += temp + ", ";
				else
					if(temp < 1000)
						addResult += temp;
			}
			if (count == 1)
				throw new Exception("Wrong number of Inputs");
		} else {
			Iterator<String> iter = numList.iterator();
			while (iter.hasNext()) {
				int val = Integer.parseInt(iter.next());
				if (val < 0) {
					errorMessage += val + ", ";
				} else {
					if(val < 1000)
						addResult += val;
				}
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

	// Driver for debugging while development
	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();
		try {
			System.out.println(calculator.add("[***]\n[--]\n1***2---3"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
