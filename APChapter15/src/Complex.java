/**
 * The Complex class is a representation of a complex  number, with variables for its real and imaginary values.
 * @author Sophia Vera 
 *
 */
public class Complex {
	double real;
	double imag;
	

	public Complex(double real, double imag){	
		this.real = real;
		this.imag = imag;
	}
	
	/**
	 * This is the non-static version of Allen Downey's abs()
	 * @return the abs of the complex number
	 */
	public double abs() {
		return Math.sqrt(real * real + imag * imag);
	}
	
	/**
	 * This method checks if two complex numbers have the same values, and is based on Allen Downey's
	 * object method equals(Complex b)
	 * @param a - the first complex number
	 * @param b - the second complex number
	 * @return whether the complex numbers are equivalent
	 */
	public static boolean equals(Complex a, Complex b){
		return (a.real == b.real && a.imag == b.imag);
	}
	
	public static void main(String[] args) {
		Complex number = new Complex(6,8);
		System.out.println(number.abs());
		Complex number2 = new Complex(6,8);
		System.out.println(equals(number, number2));
	}
}

