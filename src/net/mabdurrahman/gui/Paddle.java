package net.mabdurrahman.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 * The Paddle Class
 * @author:  MAbdurrahman
 * @date:  September 22, 2014
 */
public class Paddle {
    //Instance Variables
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static int SCREEN_HEIGHT = (int)SCREEN_SIZE.getHeight();
    private static int SCREEN_WIDTH = (int)SCREEN_SIZE.getWidth();
    private static final int VERTICAL_POSITION = (int)(SCREEN_HEIGHT*.7572);//The racquet's vertical position
    private static final int WIDTH = 150;//The width of the racquet
    private static final int HEIGHT = 50;//The height of the racquet
    protected int paddlePosition = (int)SCREEN_WIDTH/2;//The racquet's horizontal position
    protected int paddleSpeed = 0;
    private Color color = new Color(97, 92, 242);//The racquet's color
    private GamePanel gamePanel;

    /**
     * Default Paddle Constructor - Creates an object of the Paddle
     */
    public Paddle() {
        this.gamePanel = new GamePanel();

    }//end of the Default Paddle Constructor
    /**
     * Paddle Constructor - Creates an object of the Paddle with one parameter
     * @param - the GamePanel
     */
    public Paddle(GamePanel g) {
        this.gamePanel = g;

    }//end of the Paddle Constructor with one parameter
    /**
     * move Method - Moves the paddle within bounds of the gamePanel frame
     * @return Void
     */
    public void move() {
        if (paddlePosition + paddleSpeed > 0 &&
                paddlePosition + paddleSpeed < gamePanel.getWidth() - 150)
            paddlePosition += paddleSpeed;

    }//end of the move Method
    /**
     * paint Method - Paints the paddle with the color to the dimensions at the position
     * @param - the Graphics2D context
     * @return Void
     */
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillRect(paddlePosition, VERTICAL_POSITION, 150, 20);

    }//end of the paint Method
    /**
     * keyPressed Method - Responds to the event of pressing the left and right arrow keys
     * @param - the KeyEvent of pressing a key
     * @return void
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            paddleSpeed = -13;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            paddleSpeed = 13;

    }//end of the keyPressed Method
    /**
     * keyReleased Method - Responds to the event of releasing the left or right arrow keys
     * @param - the KeyEvent of releasing a key
     * @return Void
     */
    public void keyReleased(KeyEvent e) {
        paddleSpeed = 0;

    }//end of the keyReleased Method
    /**
     * getBounds Method - Returns the rectangle's position at points (x,y) and its dimensions of
     * width and height
     * @return Rectangle - Returns the rectangle's position along x, y axis and the dimension of the
     * rectangle's the width and height.
     */
    public Rectangle getBounds() {
        return new Rectangle(paddlePosition, VERTICAL_POSITION, WIDTH, HEIGHT);

    }//end of the getBounds Method
    /**
     * getVerticalPosition Method - Gets the vertical position the paddle
     * @return Int - Returns the vertical position of the paddle, which in the gamePanel's is the
     * screenHeight divided by .77572.  This result is cast to an int.
     */
    public int getVerticalPosition() {
        return VERTICAL_POSITION;

    }//end of the getTopY Method
}//end of the Paddle Class

