import java.awt.Color;
import java.awt.Graphics;

/**
 * Stripes is a class that creates the red and white stripes that form the back layer of the flag.  This class contains a subclass called 
 * Stripe, which has a function to draw a single stripe of the flag.  Stripes has an array of Stripe objects, of length of the constant
 * NUM_STRIPES.  The different data values for the points are calculated based on the the flagLength and flagWidth.
 * @author Sophia Vera
 */
public class Stripes {
	public static int NUM_STRIPES = 13;
	
	public int stripeWidth;
	public int stripeHeight;
	
	public static Stripe[] stripes = new Stripe[NUM_STRIPES];
	
	/**
	 * This is the constructor for Stripes.
	 * @param flagLength -  the first flag length, given by Flag
	 * @param flagWidth -  the first flag width, given by Flag
	 */
	public Stripes(int flagLength, int flagWidth){
		setVariables(flagLength, flagWidth);
		initStripes();
	}
	
	/**
	 * This changes the size of the stripes based on the size of the flag.
	 * @param flagLength - the length of the flag
	 * @param flagWidth - the width of the flag
	 */
	public void setVariables(int flagLength, int flagWidth){
		stripeWidth = flagLength;
		stripeHeight =flagWidth/NUM_STRIPES;
	}
	
	/**
	 * The method creates the array of stripes
	 */
	public void initStripes(){
		for (int i = 0; i < NUM_STRIPES; i++) {
			stripes[i] = new Stripe(i);
		}
	}
	
	/**
	 * The method draws the red and white stripes of the flag.
	 * @param g - Graphics
	 */
	public void draw(Graphics g){
		for (int i = 0; i < NUM_STRIPES; i++) {
			stripes[i].draw(0, stripeHeight*i, stripeWidth, stripeHeight, g);
		}
	}
	
	/**
	 *  Stripe is a subclass of Stripes.  It represents an individual Stripe on the flag, rather than thirteen, for the sake of
	 *  encapsulation.
	 */
	public class Stripe{
		//RGB version of "Glory Red"
		final Color RED = new Color(191, 10, 48);
		final int mRow;
		final Color mColor;
		
		/**
		 * This is the constructor for Stripe.
		 * @param row - the row number of the stripe
		 */
		public Stripe(int row){
			mRow = row;
			
			//Depending on whether the row is even or odd, the stripe is either red or white.
			if(row%2==0){
				mColor = RED;
			}
			else{
				mColor = Color.WHITE;
			}
		}
		
		/**
		 * This function sets the color to the proper color of the stripe and draws the stripe's rectangle.
		 * @param x - x-coordinate of the rectangle
		 * @param y - y-coordinate of the rectangle
		 * @param width - the width of the rectangle
		 * @param height - the height of the rectangle
		 * @param g - Graphics
		 */
		public void draw(int x, int y, int width, int height, Graphics g){
			g.setColor(mColor);
			g.fillRect(x, y, width, height);
		}
		
	}

}
