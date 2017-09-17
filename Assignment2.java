/* Tingyuan ZHANG */
import java.util.HashMap;

public class Assignment2 {
	public static void main(String[] args) {
		//For testing problem 1-3 & 6, uncomment the following lines
		//1.
		//double res = new Assignment2().employeeSalary(47);
		//System.out.println(res);

		//2.
		//int res2 = new Assignment2().addDigits(12345);
		//System.out.println(res2);

		//3.
		//new Assignment2().printPerfectNumbers(10000);
		
		//6. EXTRA CREDIT
		//new Assignment2().printIsoscelesTriangle(7);
		


		//Sum up pizza costs
		//Problem: Since the double and float are not accurate, we could use 
		//BigDecimal under math library to deal with the fraction or simply format the number to 2 decimals
		//Here is just an example of process so that I just use simaple doubles
		Pizza pizza1 =  new Pizza("Pepperoni", 5.5, 100);
		Pizza pizza2 = new Pizza("Garden Fresh", 4.5, 50);
		Pizza pizza3 = new Pizza("The Meats", 6.0, 150);

		HashMap<String, Integer> ordered = new HashMap<String, Integer>();
		ordered.put(pizza1.getPizzaType(), 2);
		ordered.put(pizza2.getPizzaType(), 1);
		ordered.put(pizza3.getPizzaType(), 2);

		Customer ryan = new Customer ("Ryan", ordered);

		double totalSpent = 0;
		if (ryan.getOrderedPizza().get(pizza1.getPizzaType()) != null) {
			int num = ryan.getOrderedPizza().get(pizza1.getPizzaType());
			double rate = pizza1.getUnitPrice();
			totalSpent += num * rate;
			System.out.println(ryan.getName() + " purchased" + " " + num + " "+ pizza1.getPizzaType() 
				+ " for total $" + totalSpent + ".");
		}

		if (ryan.getOrderedPizza().get(pizza2.getPizzaType()) != null) {
			int num = ryan.getOrderedPizza().get(pizza2.getPizzaType());
			double rate = pizza2.getUnitPrice();
			totalSpent += num * rate;
			System.out.println(ryan.getName() + " purchased" + " " + num + " " + pizza2.getPizzaType() 
				+ " for total $" + totalSpent + ".");
		}

		if (ryan.getOrderedPizza().get(pizza3.getPizzaType()) != null) {
			int num = ryan.getOrderedPizza().get(pizza3.getPizzaType());
			double rate = pizza3.getUnitPrice();
			totalSpent += num * rate;
			System.out.println(ryan.getName() + " purchased" + " " + num + " " + pizza3.getPizzaType() 
				+ " for total $" + totalSpent + ".");
		}
		
		System.out.println(ryan.getName() + " spent total $" + totalSpent + ".");
	}

	/* 
		1. Write a java function to calculate the salary of an employee based on the following rules.
		i. function takes input of number of hours an employee worked and returns the salary.
		ii. The first 36 hours worked are paid at a rate of 15.0, then the next 5 hours 
			are paid at a rate of 15 * 1.5. Hours after that up to a max of 48 are paid at a rate of 15 * 2.
	*/
	public double employeeSalary(double hours) {
		if (hours <= 36) return hours * 15.0;
		else if (hours <= 41) return 36 * 15 + (hours - 36) * 15 * 1.5;
		else if (hours <= 48) return 36 * 15 + 5 * 15 * 1.5 + (hours - 41) * 15 * 2;
		else return Integer.MIN_VALUE;
	}

	/* 
		2. Write a java function that adds all the digits of an integer until it is single digit.
		i. function takes an integer as input and returns its sum of digits.
		ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result = 1.
	*/
	public int addDigits(int input) {
		//Recursion
		if (input < 10) return input;

		int temp = input;
		int result = 0;

		while (temp >= 10) {
			result += temp%10;
			temp /= 10;
		}
		result += temp;
		
		return addDigits(result);
	}

	/*
		3. Write a java function to print all perfect number between 1 and n.
		i. Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
		ii. For example: 6 is the first perfect number, Proper divisors of 6 are 1, 2, 3. 
			Sum of its proper divisors = 1 + 2 + 3 = 6.
	*/
	public void printPerfectNumbers(int n) {
		for (int i = 1; i < n; i++) {
			int sum = 0; //for sum of divisors;
			
			//finding divisors
			for (int j = 2; j <= i/2; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			if (sum > 1 && sum + 1 == i)
				System.out.println(i);
		}
	}

	/*
		6. Write a Java program that generates an isosceles right angled triangle made of asterisks. 
		i. function should take input of one equal side as integer. Other than the edges the inner part of the triangle should be empty.
		ii. For example input is 6. the function should print—
		
	   *
	   **
	   * *
	   *  *
	   *   *
	   ******
	*/	
	public void printIsoscelesTriangle(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				//condition for three edges
				if (j == 0 || i == j || i == n - 1) 
					System.out.print("*");
				else System.out.print(" ");
			}
			System.out.println();
		}
	}
}

/*
	4. Write a java class called pizza with (Add detail as needed) : 
	i. At least 3 attributes :pizza type , unit price and loyalty points. More attributes are welcome to have. 
	ii. Constructor is needed. Extra-credit for 0.5 point if you write more than 1 right constructor for this class
	
	Q:
		1. Can loyalty points have fractions? Here I assume it is just an Integer
		2. What is a pizza type? I used String as pizza types
		3. Do I need getter and setters? I implemented getters and setters
*/

class Pizza {
	private String pizzaType;
	private double unitPrice;
	private int loyaltyPoints;

	public Pizza() {
		pizzaType = "";
		unitPrice = 0.0;
		loyaltyPoints = 0;
	}

	public Pizza(String pizzaType, double unitPrice, int loyaltyPoints) {
		this.pizzaType = pizzaType;
		this.unitPrice = unitPrice;
		this.loyaltyPoints = loyaltyPoints;
	}

	//getters and setters
	public String getPizzaType() {
		return pizzaType;
	}

	public void setPizzaType(String pizzaType) {
		this.pizzaType = pizzaType;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}
}

/*
	5. Write a java class called customer (Add detail as needed) : 
	i. Attributes needed: customer name and what pizzas customer has ordered. 
	ii. Think about what kind of data structure will be used to record the pizza name and numbers for each kind of pizza.
	( Give me your thought, Extra credit for 1 point)
	iii. In main method , sum up how many the customer spend. 

	To answer question ii, we can use a hashmap, pizza's name is key and number is value.
	Alternatively, we can set Pizza object as a key, which is also working.
*/
class Customer {
	private String name;
	private HashMap<String, Integer> orderedPizza;

	public Customer() {
		name = "";
		orderedPizza = new HashMap<String, Integer>();
	}

	public Customer(String name, HashMap<String, Integer> orderedPizza) {
		this.name = name;
		this.orderedPizza = orderedPizza;
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashMap<String, Integer> getOrderedPizza() {
		return orderedPizza;
	}

	public void setOrderedPizza(HashMap<String, Integer> orderedPizza) {
		this.orderedPizza = orderedPizza;
	}
}