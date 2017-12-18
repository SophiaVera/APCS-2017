import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The board class is the main class in Tetris.  It holds an 2d array for the pieces on the board.  It also contains
 * the code for the falling piece and the user input.
 * @author SophiaVera
 */
@SuppressWarnings("serial")
public class Board extends Applet implements ActionListener, KeyListener{
	final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 22;
    final int SQUARE_WIDTH = 20;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    int curX = 0;
    int curY = 0;
    Shape currentPiece;
    Shape.Tetrominoes[][] board;


    /**
     * Commences the tetris program.  It starts the timer, initializes the 2d array of tetrominoes, and creates the 
     * first piece.
     */
    public void init() {
       setFocusable(true);
       addKeyListener(this);
       setSize(200, 400);
       
       timer = new Timer(400, this);
       timer.start(); 
       
       board = new Shape.Tetrominoes[BOARD_WIDTH][BOARD_HEIGHT];
       isStarted = true;
       isFallingFinished = false;
       clearBoard();
       currentPiece = new Shape();
       newPiece();
    }

    /**
     * Sets board[][] to an array of NoShapes, thereby emptying it.
     */
    private void clearBoard(){
        for (int i = 0; i < BOARD_WIDTH; ++i)
        	for (int j = 0; j < BOARD_HEIGHT; j++) {
        		board[i][j] = Shape.Tetrominoes.NoShape;
			}
    }
    
    /**
     * Initiales the falling piece by placing a random piece at the top of the board. It also checks to
     * see if the player is dead.
     */
    private void newPiece(){
        currentPiece.setRandomShape();
        curX = BOARD_WIDTH / 2 + 1;
        curY = BOARD_HEIGHT - 1 + currentPiece.minY();

        if (!tryMove(currentPiece, curX, curY)) {
            currentPiece.setShape(Shape.Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
        }
    }
    
    /**
     * This method checks to see if the piece can fall any further by iterating through
     * its squares and checking if they can fall.  It then moves the piece.
     * @param newPiece - the falling piece
     * @param newX - its x
     * @param newY - its y
     * @return can the piece fall
     */
    private boolean tryMove(Shape newPiece, int newX, int newY){
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.getX(i);
            int y = newY - newPiece.getY(i);
            if (x < 0 || x >= BOARD_WIDTH || y < 0 || y >= BOARD_HEIGHT)
                return false;
            if (board[x][y] != Shape.Tetrominoes.NoShape)
                return false;
        }

        currentPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }
    
    /**
     * Puts the player down to the lowest place, run by pressing space.
     */
    private void dropDown(){
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(currentPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    /**
     * Moves the player down one line; its run while the player is falling.
     */
    private void oneLineDown(){
        if (!tryMove(currentPiece, curX, curY - 1))
            pieceDropped();
    }

    /**
     * Moves each of the falling pieces squares down.  It runs newPiece() if the pieces can't fall.
     */
    private void pieceDropped(){
        for (int i = 0; i < 4; ++i) {
            int x = curX + currentPiece.getX(i);
            int y = curY - currentPiece.getY(i);
            board[x][y] = currentPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }
    
    /**
     * Checks if any rows are full, and then removes them by shifting all of the upper rows down one.
     */
    private void removeFullLines(){
        boolean isLinesDeleted = false;
        for (int i = BOARD_HEIGHT - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < BOARD_WIDTH; ++j) {
                if (board[j][i] == Shape.Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                isLinesDeleted = true;
                for (int k = i; k < BOARD_HEIGHT - 1; ++k) {
                    for (int j = 0; j < BOARD_WIDTH; ++j)
                         board[j][k] = board[j][k + 1];
                }
            }
        }

        if (isLinesDeleted) {
            isFallingFinished = true;
            currentPiece.setShape(Shape.Tetrominoes.NoShape);
            repaint();
        }
     }
    
    /**
     * The paint method, which is run when the field changes. It draws all of the squares for the fallen tetrominoes, 
     * and then draws the falling piece
     */
    public void paint(Graphics g){ 
    	int preFallDist = -40; //pieces spawn above the visible board

        for (int i = 0; i < BOARD_HEIGHT; ++i) {
            for (int j = 0; j < BOARD_WIDTH; ++j) {
                Shape.Tetrominoes shape = board[j][ BOARD_HEIGHT - i - 1];
                if (shape != Shape.Tetrominoes.NoShape)
                    drawSquare(g, 0 + j * SQUARE_WIDTH, preFallDist + i * SQUARE_WIDTH, shape);
            }
        }

        if (currentPiece.getShape() != Shape.Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + currentPiece.getX(i);
                int y = curY - currentPiece.getY(i);
                drawSquare(g, 0 + x * SQUARE_WIDTH, preFallDist + (BOARD_HEIGHT - y - 1) * SQUARE_WIDTH, currentPiece.getShape());
            }
        }
    }

    /**
     * Draws a square on the board.
     * @param g
     * @param x
     * @param y
     * @param shape
     */
    private void drawSquare(Graphics g, int x, int y, Shape.Tetrominoes shape){
        Color color = Shape.colors[shape.ordinal()];
        g.setColor(color);
        g.fillRect(x, y, SQUARE_WIDTH, SQUARE_WIDTH);
    }
    
    /**
     * Makes the player either fall a line or move down.
     */
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    /**
     * Allows user input.  The arrow keys correspond to rotation and movement, and the space key drops the user completely.
     */
	@Override
	public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        switch (keycode) {
        case KeyEvent.VK_LEFT:
            tryMove(currentPiece, curX - 1, curY);
            break;
        case KeyEvent.VK_RIGHT:
            tryMove(currentPiece, curX + 1, curY);
            break;
        case KeyEvent.VK_DOWN:
            tryMove(currentPiece.rotateRight(), curX, curY);
            break;
        case KeyEvent.VK_UP:
            tryMove(currentPiece.rotateLeft(), curX, curY);
            break;
        case KeyEvent.VK_SPACE:
            dropDown();
            break;
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

}