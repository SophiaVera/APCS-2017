import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JApplet;

/**
 * This class is the applet of the flag.   It contains the method for resizing the flag
 * @author Sophia Vera
 */
@SuppressWarnings("serial")
public class AmericanFlag extends JApplet{
	Flag mFlag;
	
	//The original size of the flag
	int width = 500;
	int length = (int) (1.9*width);
	
	int lastLength = length;
	int lastWidth = width;
	
	/**
	 * This is run when AmericanFlag is created.  This also creates the Flag.
	 */
	public void init(){
		mFlag = new Flag(length, width);
		this.resize(length, width);
	}
	
	/**
	 * This method is run every time that that the flag box is resized.
	 * @param g - Graphics
	 */
	public void paint(Graphics g){
		//This draws a white rectangle in the background of the flag so that the previous flag doesn't overlap.
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, length, width);
		
		//The width and length are set to the current width and length
		length = this.getWidth();
		width = this.getHeight();
		
		/*Technically, there are two possible new flags that could be made in the case the ratio is different:
		 *  one with the applet's width and a different height, and visa versa. If the largest of these
		 *  two flags was made, some of the flag would be hidden.
		 * This if loop checks which ratio is smaller, that is to say, it changes the the width and length
		 * based on which is the smallest flag. 
		 * 
		 */
		if(length/width>1.9){
			mFlag.setflagLength((int) (width*1.9));
			mFlag.setFlagWidth(width);	
		}
		else if(length/width<1.9){
			mFlag.setflagLength(length);
			mFlag.setFlagWidth((int) (length/1.9));		
		}
		
		mFlag.draw(g);
		
		//the current dimensions are switched to be the old ones for the next time paint() is run.
		lastLength = length;
		lastWidth = width;	
	}
}
