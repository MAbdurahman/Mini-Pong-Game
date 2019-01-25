package net.mabdurrahman.gui;

import javax.media.j3d.Sound;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * The Ball Class
 * @author:  MAbdurrahman
 * @date:  September 22, 2014
 */
public class Ball {
    //Instance Variables
    private static final int DIAMETER = 40;//The ball's diameter
    public int xPosition = 0;//The ball's horizontal position
    public int yPosition = 50;//The ball's vertical position
    public int xVelocity = 5;//The ball's horizontal speed
    public int yVelocity = 5;//The ball's vertical speed
    private Color color = new Color(247, 242, 20);//The ball's color
    private GamePanel gamePanel;

    /**
     * Default Ball Constructor - Creates an object of Ball with no parameter
     */
    public Ball() {
        this.gamePanel = new GamePanel();

    }//end of the Default Ball Constructor
    /**
     * Ball Constructor - Creates an object of the Ball class with one parameter of the
     * object GamePanel.
     */
    public Ball(GamePanel g) {
        this.gamePanel = g;

    }//end of the Ball Constructor with one parameter
    /**
     * move Method - Moves the ball along positions X and Y within the dimension of the gamePanel
     * @return Void
     */
    public void move() {
        boolean isChangingDirection = true;

        if (xPosition + xVelocity <= 0) {
            xVelocity = 8;
            net.mabdurrahman.gui.Sound.RETURN_SOUND.play();

        } else if (xPosition + xVelocity > gamePanel.getWidth() - DIAMETER) {
            xVelocity = -8;
            net.mabdurrahman.gui.Sound.RETURN_SOUND.play();

        } else if (yPosition + yVelocity <= 0) {
            yVelocity = 8;
            net.mabdurrahman.gui.Sound.RETURN_SOUND.play();

        } else if (yPosition + yVelocity > gamePanel.getHeight() - DIAMETER) {
            gamePanel.doGameOver();
        } else if(getCollision()) {
            yVelocity = -8;
            yPosition = gamePanel.paddle.getVerticalPosition() - DIAMETER;
            net.mabdurrahman.gui.Sound.PADDLE_SOUND.play();
            gamePanel.score++;

        } else {
            isChangingDirection = false;
        }

        xPosition += xVelocity;
        yPosition += yVelocity;

    }//end of the move Method
    /**
     * paint Method - Paints the ball of the dimensions at positions of the X and Y axis
     * @param - the graphics 2D context
     * @return Void
     */
    public void paint(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(xPosition, yPosition, 40, 40);

    }//end of the paint Method
    /**
     * getBounds Method - Gets the position of the ball at (x,y) and the rectangular dimensions
     * @return Rectangle -A rectangle, with x position, y position, width, and height. In this
     * class width and height will be equal to the diameter of the ball.
     */
    public Rectangle getBounds() {
        return new Rectangle(xPosition, yPosition, DIAMETER, DIAMETER);

    }//end of the getBounds Method
    /**
     * getCollision Method
     * @return Boolean - Returns true, if the rectangular dimensions of the ball contacts the
     * rectangular dimensions of the paddle; otherwise, it returns false.
     */
    private boolean getCollision() {
        return gamePanel.paddle.getBounds().intersects(getBounds());

    }//end of the getCollision Method
}//end of the Ball Class


