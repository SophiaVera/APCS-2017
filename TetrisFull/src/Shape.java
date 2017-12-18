import java.util.Random;
import java.awt.Color;
import java.lang.Math;
import java.awt.Color;

/**
 * The Shape represents the pieces in the Tetris game. It contains an enum for all of the different shapes
 * and an array for the locations of all of the different squares of the piece.
 * @author SophiaVera
 */
public class Shape {
	//the list of shapes (or tetrominoes as they are known in the game)
    static enum Tetrominoes { NoShape, ZShape, SShape, LineShape, TShape, SquareShape, LShape, JShape };
    private Tetrominoes myShape;
    
    //list of the individual blocks, corresponding to the enum
    private int coords[][];
    static private int[][][] coordsTable = new int[][][] {
        { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
        { { 0, -1 },  { 0, 0 },   { -1, 0 },  { -1, 1 } },
        { { 0, -1 },  { 0, 0 },   { 1, 0 },   { 1, 1 } },
        { { 0, -1 },  { 0, 0 },   { 0, 1 },   { 0, 2 } },
        { { -1, 0 },  { 0, 0 },   { 1, 0 },   { 0, 1 } },
        { { 0, 0 },   { 1, 0 },   { 0, 1 },   { 1, 1 } },
        { { -1, -1 }, { 0, -1 },  { 0, 0 },   { 0, 1 } },
        { { 1, -1 },  { 0, -1 },  { 0, 0 },   { 0, 1 } }
    };
    
    //the list of colors, corresponding to the enum
    public static Color[]  colors = { 
    	Color.WHITE, Color.RED, Color.GREEN, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.ORANGE, Color.BLUE
    };
    
    /**
     * Creates a new shape, but doesn't set the tetrominoe shape
     */
    public Shape() {
        coords = new int[4][2];
        setShape(Tetrominoes.NoShape);
    }

    /**
     * Sets the shape by setting the indexes of coords[][] to the appropriate numbers based on the the 
     * coordsTable[][]
     * @param shape 
     */
    public void setShape(Tetrominoes shape) {
        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        myShape = shape;
    }


    /**
     * Sets the shape to a random shape.
     */
    public void setRandomShape(){
        int x = (int)(Math.random() * 7 + 1);
        Tetrominoes[] values = Tetrominoes.values(); 
        setShape(values[x]);
    }

    /**
     * Returns the lowest y value, so that when a piece is created, it spawns the appropriate number of units of the ground.
     * @return lowest y value in coords[][]
     */
    public int minY() {
      int m = coords[0][1];
      for (int i=0; i < 4; i++) {
          m = Math.min(m, coords[i][1]);
      }
      return m;
    }
    
    /**
     * Creates and returns a shape where the coords[][] have been rotated 90 degrees to the left
     * @return the rotated shape
     */
    public Shape rotateLeft() {
        if (myShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape();
        result.myShape = myShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, getY(i));
            result.setY(i, -getX(i));
        }
        return result;
    }

    /**
     * Creates and returns a shape where the coords[][] have been rotated 90 degrees to the right
     * @return the rotated shape
     */
    public Shape rotateRight(){
        if (myShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape();
        result.myShape = myShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -getY(i));
            result.setY(i, getX(i));
        }
        return result;
    }
    
    private void setX(int index, int x) { 
    	coords[index][0] = x;
    }
    
    private void setY(int index, int y) {
    	coords[index][1] = y; 
    }
    
    public int getX(int index) {
    	return coords[index][0]; 
    }
    
    public int getY(int index) {
    	return coords[index][1]; 
    }
    
    public Tetrominoes getShape(){ 
    	return myShape; 
    }
}