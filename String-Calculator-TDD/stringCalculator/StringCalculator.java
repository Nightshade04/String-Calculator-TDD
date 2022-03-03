package stringCalculator;

public class StringCalculator {

	public int add(String numbers) throws Exception {
		int addResult = 0;
		char defaultDelimiter = ',';
		int index = numbers.indexOf("\n");
		String errorMessage = "";

		if (numbers.length() > 0) {
			if (index != -1) {
				char delimiter = numbers.substring(0, index + 1).charAt(index - 1);
				if(delimiter != defaultDelimiter) {
					defaultDelimiter = delimiter;
					numbers = numbers.substring(index);
				}
			}

			numbers = numbers.replaceAll("\\n", "");
			String[] arrayOfNumbers = numbers.split(defaultDelimiter + "");
			
			if(arrayOfNumbers.length == 1) {
				throw new Exception("Wrong number of Inputs");
			}

			for (int i = 0; i < arrayOfNumbers.length; i++) {
				int num = Integer.parseInt(arrayOfNumbers[i].replaceAll("\\n", ""));
				if (num < 0) {
					errorMessage += num + ", ";
				} else {
					addResult += num;
				}
			}

			// Cleaning error message and throwing exception
			if (errorMessage.contains(",")) {
				errorMessage = errorMessage.substring(0, errorMessage.lastIndexOf(','));
				errorMessage = "negatives not allowed: " + errorMessage;
				throw new Exception(errorMessage);
			}
		}

		// returning sum if all went well
		return addResult;
	}

	// Driver for debugging while development
	public static void main(String[] args) {
		StringCalculator calculator = new StringCalculator();
		try {
			System.out.println(calculator.add("-1,\n-22"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
