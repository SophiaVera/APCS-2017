/**
 * The rational class is a representation of a fraction, with its numerator and denominator as two variables.
 * @author Sophia Vera
 */
public class Rational {
	int numerator;
	int denominator;
	
	/**
	 * This is the basic constructor when given no parameters.  It automatically sets the numerator to
	 * 0 and the denominator to 1.
	 */
	Rational(){
		this.numerator = 0;
		this.denominator = 1;
	}
	
	/**
	 * This is the constructor with parameters, which it sets the rational number's values to.
	 * @param numerator 
	 * @param denominator
	 */
	Rational(int numerator, int denominator){
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * This methods prints the rational number in "a reasonable format"
	 */
	public void printRational(){
		System.out.println("numerator is: " + numerator);
		System.out.println("denominator is: " + denominator);
	}
	
	/**
	 * This method switches the sign of the numerator, and thus the entire rational number
	 */
	public void negate(){
		numerator = -1*numerator;
	}
	
	/**
	 * This method inverts the rational-- the numerator becomes the denominator and visa versa.
	 */
	public void invert(){
		int store = numerator;
		numerator = denominator;
		denominator = store;
	}
	
	/**
	 * This methods calculates the double of the fraction
	 * @return the decimal version of the rational number
	 */
	public double toDouble(){
		return 1.0*(numerator/denominator);
	}

	/**
	 * This method simplifies the fraction.
	 */
	public void reduce(){
		int GCD = GCD(numerator, denominator);
		numerator /= GCD;
		denominator /= GCD;
	}
	
	/**
	 * This method calculates the greatest common denominator through the euclidean algorithm
	 * @param a - the numerator
	 * @param b - the denominator
	 * @return GCD
	 */
	public int GCD(int a, int b) {
		   if (b==0){
			   return a;
		   }
		   return GCD(b,a%b);
		}
	
	/**
	 * This is a helper methods that increases the fraction's size without changing the value
	 * @param num - the number being increased by
	 */
	public void scale(int num){
		numerator *= num;
		denominator *= num;
	}
	
	/**
	 * This adds two fractions together
	 * @param a - the first Rational
	 * @param b - the second Rational
	 * @return
	 */
	public Rational add(Rational a, Rational b){
		int store = a.denominator;
		a.scale(b.denominator);
		b.scale(store);
		Rational result = new Rational(a.numerator + b.numerator, a.denominator);
		result.reduce();
		return result;
	}
	
	public static void main(String[] args) {
		Rational number = new Rational(6,8);
		number.printRational();
		number.reduce();
		number.printRational();
		
		Rational number2 = number.add(number, new Rational(25,100));
		number2.printRational();
		
	}


}
