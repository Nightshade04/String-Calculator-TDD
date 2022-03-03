package stringCalculator;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	// Empty String
	@Test
	public void testAddEmptyString() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 0);
	}

	// 2Numbers
	@Test
	public void testAddNormalString() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1,2";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}

	// 3 Numbers
	@Test
	public void testAddStringWithMultipleNumbers() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1,2,3";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 6);
	}

	// Too many Numbers
	@Test
	public void testAddStringWithRidiculousAmountOfNumbers() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "";
		int expectedResult = 0, result = 0;
		for (int i = 1; i <= 100; i++) {
			if (i == 100) {
				numbers += i;
			} else {
				numbers += i + ",";
			}
			expectedResult += i;
		}
		result = calculator.add(numbers);
		Assert.assertEquals(result, expectedResult);
	}

	// Space Separated Numbers 3 Numbers
	@Test
	public void testAddStringWithMultipleNumbersSpaceSeparated() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1 2 3";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 6);
	}

	// Space Separated Numbers 2 Numbers
	@Test
	public void testAddStringSpaceSeparated() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1 2";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}

	// Space Separated Numbers 2 Numbers
	@Test
	public void testAddStringNewlineSeparated() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1\n2";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}

	// Custom Delimiter Test
	@Test
	public void testAddStringCustomDelimiter() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String delimiter = ";\n";
		String numbers = delimiter + "1;2";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}

	// Wrong Format Test
	@Test
	public void testAddWrongFormat() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1\n";
		exception.expect(Exception.class);
		exception.expectMessage("Wrong number of Inputs");
		calculator.add(numbers);
	}

	// Negatives Test
	@Test
	public void testAddNegatives() throws Exception {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1,-2";
		exception.expect(Exception.class);
		exception.expectMessage("negatives not allowed: -2");
		calculator.add(numbers);
	}

}
