package stringCalculator;

import org.junit.Assert;

import org.junit.Test;

public class StringCalculatorTest {

	@Test
	public void testAddEmptyString() {
		StringCalculator calculator = new StringCalculator();
		String numbers = "";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 0);
	}
	
	@Test
	public void testAddNormalString() {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1,2";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}
	
	@Test
	public void testAddStringWithMultipleNumbers() {
		StringCalculator calculator = new StringCalculator();
		String numbers = "1,2,3";
		int result = calculator.add(numbers);
		Assert.assertEquals(result, 3);
	}

}
