/**
 * This method creates exponentiation through successive squaring.
 */
public class Main {
    /**
     * This calculates exponents through successive squaring
     * @param x - number
     * @param n - power
     * @return
     */
    public static double calculateExponent(double x, int n) {
        if(n < 0) {
            return calculateExponent(1/x, -n);
        } else if(n == 0) {
            return 1;
        } else if(n == 1) {
            return x;
        } else if(n % 2 == 0) {
            return calculateExponent(x * x, n / 2);
        } else if(n % 2 == 1) {
            return x * calculateExponent(x * x, (n - 1) / 2);
        }
        return 0;
    }
    

    public static void main(String[] args) {
        System.out.println(calculateExponent(2, 4));
    }
}