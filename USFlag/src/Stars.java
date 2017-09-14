import java.awt.Color;
import java.awt.Graphics;
/**
 * The Stars class contains a subclass of type Star, which stores an (x,y) coordinate and runs the draw method, which draws
 * the individual star shape.
 * @author Sophia Vera
 */
public class Stars {
	public static int ROW_STARS = 9;
	public static Star[][] stars = new Star[ROW_STARS][];
	
	public int rectWidth;
	public int rectLength;
	
	public int starOffset;
	public int starLengthInBetween;
	public int starWidthInBetween;
	public int starDiameter;
	
	/**
	 * This is the star Constructor.
	 * @param flagWidth - the first flag width, given by Flag
	 */
	public Stars(int flagWidth){
		setVariables(flagWidth);
		initStars();
	}
	
	/**
	 * This changes the size of the stars based on the size of the flag
	 * @param flagLength - the length of the flag
	 * @param flagWidth - the width of the flag
	 */
	public void setVariables(int flagWidth){
		rectWidth = 7*(int)(flagWidth/13);
		rectLength = (int) (0.76*flagWidth);
		
		starOffset = (int) (flagWidth*0.054);
		
		starLengthInBetween = (int) (0.063 * flagWidth);
		starWidthInBetween = (int) (0.054 * flagWidth);
		starDiameter = (int)(0.0616*flagWidth);
	}
	
	/**
	 * This method creates the 2d array of stars.
	 */
	public void initStars(){
		for (int i = 0; i < ROW_STARS; i++) {
			stars[i]= new Star[makeGoodStarCount(i)];
			for (int j=0; j < makeGoodStarCount(i) ; j++){
				stars[i][j] = new Star(i,j, starDiameter);
			}
		}
	}
	
	/**
	 * This checks if the row is even or odd and returns the correct amount of stars.
	 * @param rowNumber - which row of stars is being created 
	 * @return the proper amount of stars in that row
	 */
	public int makeGoodStarCount(int rowNumber){
		if(rowNumber%2 == 0){
			return 6;
		}
		else{
			return 5;
		}
	}
	
	/**
	 *  This method changes the center of the stars and draws them again.  
	 * This is run inside of the draw() method.
	 * @param g - Graphics
	 */
	public void drawStars(Graphics g){
		for (int i = 0; i < stars.length; i++) {
			for (int j = 0; j < stars[i].length; j++) {
				stars[i][j].setStarDimensions(starDiameter);
				stars[i][j].draw(2, 2, 2, g);
			}
			
		}
	}
	
	/**
	 * This method creates the blue rectangle for the flag and runs the drawStars() method.
	 * @param g - Graphics
	 */
	public void draw(Graphics g){
		//RGB version of "Glory Blue"
		g.setColor(new Color(0,40,104));
		g.fillRect(0, 0, rectLength, rectWidth);
		drawStars(g);
		
	}
	
	
	/**
	 *  Star is a subclass of Stars.  It represents an individual star on the flag, rather than the fifty, for the sake of
	 *  encapsulation.
	 */
	public class Star{
		public int mCol;
		public int mRow;
		public int centerx;
		public int centery;
		
		public int [] xp = new int[10];
		public int [] yp = new int[10]; 
		
		public double starRadius;
		//This is a constant for all five pointed stars
		public double STAR_SCALE = 0.382;
		
		/**
		 * This is the constructor for Star
		 * @param row - the row number of the star
		 * @param col - the column number of the star
		 * @param starDiameter - the star diameter, which is calculated inside of Stars's setVariables().
		 */
		public Star(int row, int col, int starDiameter){
			mCol = col;
			mRow = row;
			setStarDimensions(starDiameter);	
		}
		
		/**
		 * This calculates the new center of the star, based on the diameter and size of the flag
		 */
		public void setStarDimensions(int starDiameter){
			
			//See flag dimensions for the basic algebra needed to create the star centers
			centerx = 2*mCol*starLengthInBetween + ((mRow%2)+1)*starLengthInBetween;
			centery = (1+mRow)* starWidthInBetween;
			
			//divided by 2 so that diameter becomes radius
			starRadius = (int)(0.5*starDiameter);
		}
		
		/**
		 * This method uses trigonometry in order to calculate the positions of the points of the star.
		 */
		public void findPoints(){
			//This for loop runs through all 10 points of the 5 point star (including inner points)
			for(int k = 0; k < 10; k++){
				//This if statement changes the starRadius of the points based on whether k is even or odd, in order to change 
				//between the outer and inner points of the star.
				if(k%2==0){starRadius = starRadius*STAR_SCALE;}
				else{starRadius = starRadius /STAR_SCALE;}
				
				/* This uses trigonometry to create the five points of the stars.
				 * Conceptually, it is as though one is traveling around the unit circle in ten parts-- each interval
				 * progresses one tenth the way (or 36 degrees) further around the circle.  It starts at 18 degrees
				 * because that is the distance the upper right point of the star is above the x-axis(found through 
				 * geometry)
				 * After this the star's center x or y value is added, so that the center of this conceptual unit circle
				 * becomes the center of the star being calculated.
				 */
				xp[k] = (int) (starRadius*(Math.cos(Math.toRadians(18+36*k))) +centerx);
				yp[k] = (int) (starRadius*(Math.sin(Math.toRadians(18+36*k))) + centery);
			}
		}
		
		/**
		 * This function finds the points of the flag, and draws a white star shape based on them
		 * @param col - the column number of the star
		 * @param row - the row number of the star
		 * @param diameter - the diameter of the star
		 * @param g - Graphics
		 */
		public void draw(int col, int row, int diameter,Graphics g){
			findPoints();
			g.setColor(Color.WHITE);
			g.fillPolygon(xp, yp, 10);
		}
		
	}

}
