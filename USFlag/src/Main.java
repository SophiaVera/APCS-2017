/**
 * Main is the outer class of the flag.  It instantiates the applet, which then runs the flag. It could technically be
 * inside another class; however, it is a more logical code structure to have it sepperate.
 * @author Sophia Vera
 */
public class Main {
	static AmericanFlag flag;
	
	public Main (){}
	
	/**
	 * Instantiates the flag applet
	 */
	public static void main(String[] args) {
		flag = new AmericanFlag();
		flag.init();
		flag.repaint();
	}
}
