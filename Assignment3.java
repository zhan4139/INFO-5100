import java.lang.*;
import java.util.*;

public class Assignment3 {
	public static void main(String[] args) {
		String s = "removeVOwelsFromString";
		String s2 = removeVowelsFromString(s);
		System.out.println(s2);

		String str1 = "Damon Albarn", str2 = "Dan Abnormal";
		System.out.println(checkIfTwoStringAreAnagrams(str1, str2));

		Calculator cal = new Calculator(15);
		System.out.println(Arrays.toString(cal.solutionToQuadratic(1, 2, 2)));
	}

	/**
	 *	Q1.
	 *	1. The first constructor and third constructor have conflict.
	 *	2. The second constructor is calling super() which refers to Book's 
	 *		parent class Object's constructor, which I don't think it is necessary now, 
	 *		probably you want to call this() for constructor chaining.
	 *	3. Setters for size and price is needed. If member variables are private, 
	 *		we might need getters.
	 */

	/**
	 *	Q2.
	 *	1. The getTime method should be String instead of void since it return a string time.	
	 *	2. No constructor
	 */

	/**
	 *  Q3. Remove vowels in a String
	 *	@param input It is the string with vowels
	 *	@return a string without vowels
	 */
	public static String removeVowelsFromString(String input) {
		StringBuffer result = new StringBuffer();
		char[] temp = input.toCharArray();

		for (int i = 0; i < temp.length; i++) {
			if(!isVowel(temp[i])) {
				result.append(temp[i]);
			}
		}

		return result.toString();
	}

	private static boolean isVowel(char c) {
		char[] vowels = {'a', 'e', 'i', 'o', 'u'};
		for (int i = 0; i < vowels.length; i++) {
			if (Character.toLowerCase(c) == vowels[i])
				return true;
		}
		return false;
	}

	/**
	 *  Q4. Check two strings if they are Anagrams
	 *	@param s1 s2 are two input strings
	 *	@return true if they are Anagrams else false
	 */
	public static boolean checkIfTwoStringAreAnagrams(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s1.length(); i++) {
			char c1 = s1.charAt(i);
			char c2 = s2.charAt(i);
			if (map.containsKey(c1))
				map.put(c1, map.get(c1) + 1);
			else map.put(c1, 1);

			if (map.containsKey(c2))
				map.put(c2, map.get(c2) - 1);
			else map.put(c2, -1);
		}

		for (Character c : map.keySet()) {
			if (map.get(c) != 0)
				return false;
		}

		return true;
	}
} 

/**
 *	Q5.
 *	1. Perform addition, Substraction, multiplication, division
 *	2. Perform squareRoot, square, cube
 *	3. Convert Fahrenheit-Celsius, Feet-Inches
 */	
class Calculator{
	private double number;

	public Calculator() {
		this.number = 0.0;
	}

	public Calculator(double number) {
		this.number = number;
	}

	public double getNumber() {
		return number;
	}
	
	public void setNumber(double number) {
		this.number = number;
	}

	public double add(double num) {
		return number + num;
	}

	public double substract(double num) {
		return number - num;
	}

	public double multiply(double num) {
		return number * num;
	}

	public double divide(double num) {
		if (num != 0.0) {
			return number / num;
		} else {
			System.out.println("Divider can't be 0!");
			return Double.MAX_VALUE;
		}  
	}

	public double squareRoot() {
		if (number >= 0.0) {
			return Math.sqrt(number);
		} else {
			System.out.println("Base can't be negative!");
			return -Double.MIN_VALUE;
		}
	}

	public double square() {
		return Math.pow(number, 2);
	}

	public double cube() {
		return Math.pow(number, 3);
	}

	public double fahrenheitToCelsius() {
		return (number - 32) * 5 / 9;
	}

	public double celsiusTofahrenheit() {
		return number * 9 / 5 + 32;
	}

	public double feetToInches() {
		return number * 12;
	}

	public double inchesToFeet() {
		return number / 12;
	}

	public double[] solutionToQuadratic(double A, double B, double C) {
		double delta = B * B - 4 * A * C;
		if (delta < 0) {
			System.out.println("No real roots solutions!");
			return new double[]{Double.MIN_VALUE, Double.MIN_VALUE};
		} else {
			return new double[]{(-B + Math.sqrt(delta)) / (2*A), (-B - Math.sqrt(delta)) / (2*A)};
		}
	}
}