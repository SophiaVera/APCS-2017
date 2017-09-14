public class Intro {
	public static void main(String[] args) {
		System.out.println(ret());
		System.out.println(stars(10));
		System.out.println(coins(-90));
	}

	/**
	 * Problem 1: Write a method, called ret, that takes in no arguments and returns 17
	 * @return 17
	 */
	public static int ret(){
		return 17;
	}
	
	/**
	 * Problem 2: 
	 * Write a method, that takes in three parameters, and returns the logical and of the parameters together.
	 * @param a
	 * @param b
	 * @param c
	 * @return value of a and b and c
	 */
	public static boolean logic(boolean a, boolean b, boolean c){
		return a && b && c;
	}
	
	/**
	 * Problem 3:
	 * This problem involves printing a left-justified triangle using the * character. 
	 * @param n - number of rows of the star
	 * @return Star structure
	 */
	public static String stars(int n){
		String result = "";
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < i+1; j++){
				result += "*";
			}
			result += "\n";
		}
		return result;	
	}
	
	/**
	 * Jim has a large supply of coins that he likes to maintain.  However, when his friend asked him to exchange coins, he agreed. 
	 * His friend told him that he wanted to exchange for 5 cent and 2 cent coins (the 2 cent coin exists where Jim is). 
	 * Jim wants to exchange as few coins as possible, so return the minimum number of 5c and 2c coins Jim needs to use. 
	 * If the exchange isn’t possible, return -1. 
	 * @param n - value of coins asked to be exchanged
	 * @return The number of coins being exchanged.
	 */
	public static int coins(int n){
		int max5CentCoins = (n-n%5)/5;
		for (int i = max5CentCoins; i >= 0; i--) {
			if((n-i*5)%2 == 0){
				return i + (n-i*5)/2;
			}
		}
		return -1;
	}
}
