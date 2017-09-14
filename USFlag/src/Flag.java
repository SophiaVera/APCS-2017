import java.awt.Graphics;

/**
 * The flag class contains an object of Stars and one of Stripe.  It also acts to change the variables for the sizes of 
 * figures when the flag size is changed.
 * @author Sophia Vera
 */
public final class Flag {
	public int flagLength;
	public int flagWidth;
	
	public Stripes mStripes;
	public Stars mStars;
	
	/**
	 * This is the constructor for Flag.
	 * @param length - the length of the flag
	 * @param width - the width of the flag
	 */
	public Flag(int length, int width){		
		flagLength = length;
		flagWidth = width;
		
		mStripes = new Stripes(flagLength, flagWidth);
		mStars = new Stars(flagWidth);
	}
	
	/**
	 * This function sets the variables of the components of the flag to the correct amount.
	 * Then it draws them.
	 * @param g - Graphics
	 */
	public void draw(Graphics g){
		mStripes.setVariables(flagLength, flagWidth);
		mStars.setVariables(flagWidth);
		
		mStripes.draw(g);
		mStars.draw(g);
	}
	
	//Getters and Setters:
	
	public int getFlagWidth() {
		return flagWidth;
	}

	public void setFlagWidth(int flagWidth) {
		this.flagWidth = flagWidth;
	}

	public int getflagLength() {
		return flagLength;
	}

	public void setflagLength(int flagLength) {
		this.flagLength = flagLength;
	}

}
